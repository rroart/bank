data "aws_availability_zones" "available" {
 }

 resource "aws_vpc" "demo" {
   cidr_block = "10.0.0.0/16"

   tags = {
     "Name"                                      = "terraform-eks-demo-node"
     "kubernetes.io/cluster/${var.cluster-name}" = "shared"
   }
 }

 resource "aws_subnet" "demo" {
   count = 2

   availability_zone = data.aws_availability_zones.available.names[count.index]
   cidr_block        = "10.0.${count.index}.0/24"
   vpc_id            = aws_vpc.demo.id

   tags = {
     "Name"                                      = "terraform-eks-demo-node"
     "kubernetes.io/cluster/${var.cluster-name}" = "shared"
   }
 }

 resource "aws_internet_gateway" "demo" {
   vpc_id = aws_vpc.demo.id

   tags = {
     Name = "terraform-eks-demo"
   }
 }

 resource "aws_route_table" "demo" {
   vpc_id = aws_vpc.demo.id

   route {
     cidr_block = "0.0.0.0/0"
     gateway_id = aws_internet_gateway.demo.id
   }
 }

 resource "aws_route_table_association" "demo" {
   count = 2

   subnet_id      = aws_subnet.demo[count.index].id
   route_table_id = aws_route_table.demo.id
 }

resource "aws_iam_role" "demo-node" {
  name = "terraform-eks-demo-cluster"

  assume_role_policy = <<POLICY
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Principal": {
        "Service": "eks.amazonaws.com"
      },
      "Action": "sts:AssumeRole"
    }
  ]
}
POLICY
}

resource "aws_iam_role_policy_attachment" "demo-cluster-AmazonEKSClusterPolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"
  role       = aws_iam_role.demo-node.name
}

resource "aws_iam_role_policy_attachment" "demo-cluster-AmazonEKSServicePolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSServicePolicy"
  role       = aws_iam_role.demo-node.name
}

resource "aws_security_group" "demo-cluster" {
  name        = "terraform-eks-demo-cluster"
  description = "Cluster communication with worker nodes"
  vpc_id      = aws_vpc.demo.id

  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "terraform-eks-demo"
  }
}

# OPTIONAL: Allow inbound traffic from your local workstation external IP
#           to the Kubernetes. You will need to replace A.B.C.D below with
#           your real IP. Services like icanhazip.com can help you find this.
resource "aws_security_group_rule" "demo-cluster-ingress-workstation-https" {
  cidr_blocks       = ["109.189.17.200/32"]
  description       = "Allow workstation to communicate with the cluster API Server"
  from_port         = 443
  protocol          = "tcp"
  security_group_id = aws_security_group.demo-cluster.id
  to_port           = 443
  type              = "ingress"
}

resource "aws_eks_cluster" "demo" {
  name            = var.cluster-name
  role_arn        = aws_iam_role.demo-node.arn

  vpc_config {
    security_group_ids = ["${aws_security_group.demo-cluster.id}"]
    subnet_ids         = flatten(["${aws_subnet.demo.*.id}"])
  }

  depends_on = [
    aws_iam_role_policy_attachment.demo-cluster-AmazonEKSClusterPolicy,
    aws_iam_role_policy_attachment.demo-cluster-AmazonEKSServicePolicy,
  ]
}

# from https://medium.com/@alex.veprik/setting-up-aws-eks-cluster-entirely-with-terraform-e90f50ab7387

data "aws_iam_policy_document" "kubectl_assume_role_policy" {
  statement {
    actions = [
      "sts:AssumeRole",
    ]
    principals {
      type        = "AWS"
      identifiers = ["arn:aws:iam::656227074020:root"]
      # YOUR ACCOUNT
    }
  }
}

resource "aws_iam_role" "eks_kubectl_role" {
  name               = "example-kubectl-access-role"
  assume_role_policy = data.aws_iam_policy_document.kubectl_assume_role_policy.json
}

resource "aws_iam_role_policy_attachment" "eks_kubectl-AmazonEKSClusterPolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSClusterPolicy"
  role       = aws_iam_role.eks_kubectl_role.name
}

resource "aws_iam_role_policy_attachment" "eks_kubectl-AmazonEKSServicePolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSServicePolicy"
  role       = aws_iam_role.eks_kubectl_role.name
}

resource "aws_iam_role_policy_attachment" "eks_kubectl-AmazonEKSWorkerNodePolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
  role       = aws_iam_role.eks_kubectl_role.name
}

# for automating inside terraform

data "aws_eks_cluster_auth" "cluster_auth" {
  name = var.cluster-name
}

provider "kubernetes" {
  host                   = aws_eks_cluster.demo.endpoint
  cluster_ca_certificate = base64decode(aws_eks_cluster.demo.certificate_authority.0.data)
  token                  = data.aws_eks_cluster_auth.cluster_auth.token
  load_config_file       = false
}

resource "kubernetes_config_map" "aws_auth_configmap" {
  metadata {
    name      = "aws-auth"
    namespace = "kube-system"
  }
  data = {
    mapRoles = <<YAML
- rolearn: data.aws_iam_role.eks_cluster_iam_role.arn
  username: system:node:{{EC2PrivateDNSName}}
  groups:
    - system:bootstrappers
    - system:nodes
- rolearn: data.aws_iam_role.demo-node.arn
  username: kubectl-access-user
  groups:
    - system:masters
YAML
  }
}

resource "kubernetes_pod" "bank" {
  metadata {
    name = "bank-example"
    labels = {
      App = "bank"
    }
  }
  spec {
    container {
      image = "catwarebank-core:latest"
      name  = "bank-core"

      #args = ["-listen=:80", "-text='Hello World'"]
      port {
        container_port = 8080
      }
    }
  }
}

resource "kubernetes_service" "bank" {
  metadata {
    name = "bank-example"
  }
  spec {
    selector = {
      App = kubernetes_pod.bank.metadata[0].labels.App
    }
    port {
      port        = 8080
      target_port = 8080
    }
    type = "LoadBalancer"
  }
}

output "lb_ip" {
  value = kubernetes_service.bank.load_balancer_ingress[0].ip
}

resource "aws_eks_node_group" "demo" {
  cluster_name    = var.cluster-name
  node_group_name = "demo"
  node_role_arn   = aws_iam_role.demo-group.arn
  #node_role_arn   = arn:aws:iam::656227074020:role/terraform-eks-demo-cluster
  subnet_ids      = aws_subnet.demo[*].id

  scaling_config {
    desired_size = 1
    max_size     = 1
    min_size     = 1
  }

  # Ensure that IAM Role permissions are created before and deleted after EKS Node Group handling.
  # Otherwise, EKS will not be able to properly delete EC2 Instances and Elastic Network Interfaces.
  depends_on = [
    aws_iam_role_policy_attachment.demo-AmazonEKSWorkerNodePolicy,
    aws_iam_role_policy_attachment.demo-AmazonEKS_CNI_Policy,
    aws_iam_role_policy_attachment.demo-AmazonEC2ContainerRegistryReadOnly,
  ]
}

resource "aws_iam_role" "demo-group" {
  name = "eks-node-group-demo"

  assume_role_policy = jsonencode({
    Statement = [{
      Action    = "sts:AssumeRole"
      Effect    = "Allow"
      Principal = {
        Service = "ec2.amazonaws.com"
      }
    }]
    Version = "2012-10-17"
  })
}

resource "aws_iam_role_policy_attachment" "demo-AmazonEKSWorkerNodePolicy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKSWorkerNodePolicy"
  role       = aws_iam_role.demo-group.name
}

resource "aws_iam_role_policy_attachment" "demo-AmazonEKS_CNI_Policy" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEKS_CNI_Policy"
  role       = aws_iam_role.demo-group.name
}

resource "aws_iam_role_policy_attachment" "demo-AmazonEC2ContainerRegistryReadOnly" {
  policy_arn = "arn:aws:iam::aws:policy/AmazonEC2ContainerRegistryReadOnly"
  role       = aws_iam_role.demo-group.name
}