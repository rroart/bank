provider "azuredevops" {  
#    organization = "catware"
    version = ">= 0.0.1"
}

resource "azuredevops_project" "project" {
	 project_name                = "bank"
}