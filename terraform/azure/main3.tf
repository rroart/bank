# Create an App Service Plan with Linux
resource "azurerm_app_service_plan" "appserviceplan" {
  name                = azurerm_resource_group.example.name
  location            = azurerm_resource_group.example.location
  resource_group_name = azurerm_resource_group.example.name
  reserved = true # Mandatory for Linux plans

  # Define Linux as Host OS
  kind = "Linux"

  # Choose size
  sku {
    tier = "Standard"
    size = "S1"
  }

}

# Create an Azure Web App for Containers in that App Service Plan
resource "azurerm_app_service" "coredockerapp" {
  name = "corecatwarebank"
  location = azurerm_resource_group.example.location
  resource_group_name = azurerm_resource_group.example.name
  app_service_plan_id = azurerm_app_service_plan.appserviceplan.id

  # Do not attach Storage by default
  app_settings = {
    WEBSITES_ENABLE_APP_SERVICE_STORAGE = false
    WEBSITES_PORT = 8080

    # Settings for private Container Registires  
    DOCKER_REGISTRY_SERVER_URL = azurerm_container_registry.acr.login_server
    DOCKER_REGISTRY_SERVER_USERNAME = azurerm_container_registry.acr.admin_username
    DOCKER_REGISTRY_SERVER_PASSWORD = azurerm_container_registry.acr.admin_password
  }

  # Configure Docker Image to load on start
  site_config {
    linux_fx_version = "DOCKER|${azurerm_container_registry.acr.login_server}/bankimage:latest"
    always_on = "true"
  }

  identity {
    type = "SystemAssigned"
  }
}

resource "azurerm_app_service" "wwwdockerapp" {
  name = "wwwcatwarebank"
  location = azurerm_resource_group.example.location
  resource_group_name = azurerm_resource_group.example.name
  app_service_plan_id = azurerm_app_service_plan.appserviceplan.id

  # Do not attach Storage by default
  app_settings = {
    WEBSITES_ENABLE_APP_SERVICE_STORAGE = false
    #WEBSITES_PORT = 8080

    # Settings for private Container Registires
    DOCKER_REGISTRY_SERVER_URL = azurerm_container_registry.acr.login_server
    DOCKER_REGISTRY_SERVER_USERNAME = azurerm_container_registry.acr.admin_username
    DOCKER_REGISTRY_SERVER_PASSWORD = azurerm_container_registry.acr.admin_password
  }

  # Configure Docker Image to load on start
  site_config {
    linux_fx_version = "DOCKER|${azurerm_container_registry.acr.login_server}/wwwimage:latest"
    always_on = "true"
  }

  identity {
    type = "SystemAssigned"
  }
}

