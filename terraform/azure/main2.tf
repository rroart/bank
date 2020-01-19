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
    yml_path              = "terraform/azure/azure-pipeline3.yml"
    service_connection_id = azuredevops_serviceendpoint_github.github_serviceendpoint.id
  }

  variable_groups = [azuredevops_variable_group.vg.id]
}

resource "azuredevops_variable_group" "vg" {
  project_id   = azuredevops_project.project.id
  name         = "Sample VG 1"
  description  = "A sample variable group."
  allow_access = true

  variable {
    name      = "vmImageName"
    value     = "ubuntu-latest"
    is_secret = false
  }

  variable {
    name      = "imageRepository"
    value     = azurerm_container_registry.acr.name
    is_secret = false
  }

  variable {
    name      = "dockerfilePath"
    value     = "."
    is_secret = false
  }

  variable {
    name      = "dockerRegistryServiceConnection"
    value     = "ubuntu-latest"
    is_secret = false
  }

  variable {
    name      = "tag"
    value     = "latest"
    is_secret = false
  }

}