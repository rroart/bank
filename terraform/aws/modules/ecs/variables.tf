variable "environment" {
  description = "The environment"
}

variable "vpc_id" {
  description = "The VPC id"
}

variable "availability_zones" {
  type        = list(string)
  description = "The azs to use"
}

variable "security_groups_ids" {
  type        = list(string)
  description = "The SGs to use"
}

variable "subnets_ids" {
  type        = list(string)
  description = "The private subnets to use"
}

variable "public_subnet_ids" {
  type        = list(string)
  description = "The private subnets to use"
}

variable "repository_name" {
  description = "The name of the repisitory"
}

variable "secret_key_base" {
  description = "The secret key base to use in the app"
}
