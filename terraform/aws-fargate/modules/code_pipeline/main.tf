resource "aws_secretsmanager_secret" "p12keyfile" {
  name = "p12keyfile"
  recovery_window_in_days = 0
}

resource "aws_secretsmanager_secret" "p12password" {
  name = "p12password"
  recovery_window_in_days = 0
}

resource "aws_s3_bucket" "source-bank" {
  bucket        = "catware-source"
  acl           = "private"
  force_destroy = true
}

resource "aws_s3_bucket" "source-catwarereact" {
  bucket        = "catware-source"
  acl           = "private"
  force_destroy = true
}

resource "aws_iam_role" "codepipeline_role" {
  name               = "codepipeline-role"

  assume_role_policy = file("${path.module}/policies/codepipeline_role.json")
}

/* policies */
data "template_file" "codepipeline_policy_bank" {
  template = file("${path.module}/policies/codepipeline.json")

  vars = {
    aws_s3_bucket_arn = aws_s3_bucket.source-bank.arn
  }
}

data "template_file" "codepipeline_policy_catwarereact" {
  template = file("${path.module}/policies/codepipeline.json")

  vars = {
    aws_s3_bucket_arn = aws_s3_bucket.source-catwarereact.arn
  }
}

resource "aws_iam_role_policy" "codepipeline_policy_bank" {
  name   = "codepipeline_policy"
  role   = aws_iam_role.codepipeline_role.id
  policy = data.template_file.codepipeline_policy_bank.rendered
}

resource "aws_iam_role_policy" "codepipeline_policy_catwarereact" {
  name   = "codepipeline_policy"
  role   = aws_iam_role.codepipeline_role.id
  policy = data.template_file.codepipeline_policy_catwarereact.rendered
}

/*
/* CodeBuild
*/
resource "aws_iam_role" "codebuild_role" {
  name               = "codebuild-role"
  assume_role_policy = file("${path.module}/policies/codebuild_role.json")
}

data "template_file" "codebuild_policy_bank" {
  template = file("${path.module}/policies/codebuild_policy.json")

  vars = {
    aws_s3_bucket_arn = aws_s3_bucket.source-bank.arn
  }
}

data "template_file" "codebuild_policy_catwarereact" {
  template = file("${path.module}/policies/codebuild_policy.json")

  vars = {
    aws_s3_bucket_arn = aws_s3_bucket.source-catwarereact.arn
  }
}

resource "aws_iam_role_policy" "codebuild_policy_bank" {
  name        = "codebuild-policy-bank"
  role        = aws_iam_role.codebuild_role.id
  policy      = data.template_file.codebuild_policy_bank.rendered
}

resource "aws_iam_role_policy" "codebuild_policy_catwarereact" {
  name        = "codebuild-policy-catwarereact"
  role        = aws_iam_role.codebuild_role.id
  policy      = data.template_file.codebuild_policy_catwarereact.rendered
}

data "template_file" "buildspeccore" {
  template = file("${path.module}/buildspeccore.yml")

  vars = {
    repository_url     = var.repository_url_core
    region             = var.region
    cluster_name       = var.ecs_cluster_name
    subnet_id          = var.run_task_subnet_id
    security_group_ids = join(",", var.run_task_security_group_ids)
    password 	       = aws_secretsmanager_secret_version.p12password.secret_string
    keyfile	       = aws_secretsmanager_secret_version.p12keyfile.secret_binary
  }
}

data "template_file" "buildspecweb" {
  template = file("${path.module}/buildspecweb.yml")

  vars = {
    repository_url     = var.repository_url_web
    region             = var.region
    cluster_name       = var.ecs_cluster_name
    subnet_id          = var.run_task_subnet_id
    security_group_ids = join(",", var.run_task_security_group_ids)
  }
}

resource "aws_codebuild_project" "bank_build" {
  name          = "bank-codebuild"
  build_timeout = "10"
  service_role  = aws_iam_role.codebuild_role.arn

  artifacts {
    type = "CODEPIPELINE"
  }

  environment {
    compute_type    = "BUILD_GENERAL1_SMALL"
    // https://docs.aws.amazon.com/codebuild/latest/userguide/build-env-ref-available.html
    image           = "aws/codebuild/docker:17.09.0"
    type            = "LINUX_CONTAINER"
    privileged_mode = true
  }

  source {
    type      = "CODEPIPELINE"
    buildspec = data.template_file.buildspeccore.rendered
  }
}

resource "aws_codebuild_project" "catwarereact_build" {
  name          = "catwarereact-codebuild"
  build_timeout = "10"
  service_role  = aws_iam_role.codebuild_role.arn

  artifacts {
    type = "CODEPIPELINE"
  }

  environment {
    compute_type    = "BUILD_GENERAL1_SMALL"
    // https://docs.aws.amazon.com/codebuild/latest/userguide/build-env-ref-available.html
    image           = "aws/codebuild/docker:17.09.0"
    type            = "LINUX_CONTAINER"
    privileged_mode = true
  }

  source {
    type      = "CODEPIPELINE"
    buildspec = data.template_file.buildspecweb.rendered
  }
}

/* CodePipeline */

resource "aws_codepipeline" "pipeline_bank" {
  name     = "bank-pipeline"
  role_arn = aws_iam_role.codepipeline_role.arn

  artifact_store {
    location = aws_s3_bucket.source-bank.bucket
    type     = "S3"
  }

  stage {
    name = "Source"

    action {
      name             = "Source"
      category         = "Source"
      owner            = "ThirdParty"
      provider         = "GitHub"
      version          = "1"
      output_artifacts = ["source"]

      configuration = {
        Owner      = "rroart"
        Repo       = "bank"
        Branch     = "master"
	OAuthToken = var.github_token
      }
    }
  }

  stage {
    name = "Build"

    action {
      name             = "Build"
      category         = "Build"
      owner            = "AWS"
      provider         = "CodeBuild"
      version          = "1"
      input_artifacts  = ["source"]
      output_artifacts = ["imagedefinitions"]

      configuration = {
        ProjectName = "bank-codebuild"
      }
    }
  }

  stage {
    name = "Production"

    action {
      name            = "Deploy"
      category        = "Deploy"
      owner           = "AWS"
      provider        = "ECS"
      input_artifacts = ["imagedefinitions"]
      version         = "1"

      configuration = {
        ClusterName = var.ecs_cluster_name
        ServiceName = var.ecs_service_name_core
        FileName    = "imagedefinitions.json"
      }
    }
  }
}

resource "aws_codepipeline" "pipeline-catwarereact" {
  name     = "catwarereact-pipeline"
  role_arn = aws_iam_role.codepipeline_role.arn

  artifact_store {
    location = aws_s3_bucket.source-catwarereact.bucket
    type     = "S3"
  }

  stage {
    name = "Source"

    action {
      name             = "Source"
      category         = "Source"
      owner            = "ThirdParty"
      provider         = "GitHub"
      version          = "1"
      output_artifacts = ["source"]

      configuration = {
        Owner      = "rroart"
        Repo       = "catwarereact"
        Branch     = "master"
	OAuthToken = var.github_token
      }
    }
  }

  stage {
    name = "Build"

    action {
      name             = "Build"
      category         = "Build"
      owner            = "AWS"
      provider         = "CodeBuild"
      version          = "1"
      input_artifacts  = ["source"]
      output_artifacts = ["imagedefinitions"]

      configuration = {
        ProjectName = "catwarereact-codebuild"
      }
    }
  }

  stage {
    name = "Production"

    action {
      name            = "Deploy"
      category        = "Deploy"
      owner           = "AWS"
      provider        = "ECS"
      input_artifacts = ["imagedefinitions"]
      version         = "1"

      configuration = {
        ClusterName = var.ecs_cluster_name
        ServiceName = var.ecs_service_name_web
        FileName    = "imagedefinitions.json"
      }
    }
  }
}

