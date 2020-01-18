provider "azuredevops" {  
#    organization = "catware"
    version = ">= 0.0.1"
}

resource "azuredevops_project" "project" {
	 project_name                = "bank"
}

resource "azuredevops_serviceendpoint_github" "github_serviceendpoint" {
  project_id             = azuredevops_project.project.id
  service_endpoint_name  = "GitHub Service Connection"
  github_service_endpoint_pat = var.github_token
}

resource "azuredevops_build_definition" "build" {
  project_id      = azuredevops_project.project.id
  agent_pool_name = "Hosted Ubuntu 1604"
  name            = "Build"
  path            = "\\"

  repository {
    repo_type             = "GitHub"
    repo_name             = "rroart/bank"
    branch_name           = "master"
    yml_path              = "terraform/azure/azure-pipeline2.yml"
    service_connection_id = azuredevops_serviceendpoint_github.github_serviceendpoint.id
  }
}
