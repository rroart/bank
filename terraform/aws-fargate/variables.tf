variable "region" {
  description = "Region that the instances will be created"
}

/*====
environment specific variables
======*/

variable "production_secret_key_base" {
  description = "The Rails secret key for production"
}

variable "domain" {
  default = "The domain of your application"
}

