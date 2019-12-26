output "repository_url_core" {
  value = aws_ecr_repository.catwarebank_app_core.repository_url
}

output "repository_url_web" {
  value = aws_ecr_repository.catwarebank_app_web.repository_url
}

output "cluster_name" {
  value = aws_ecs_cluster.cluster.name
}

output "service_name_web" {
  value = aws_ecs_service.web.name
}

output "service_name_core" {
  value = aws_ecs_service.core.name
}

output "alb_dns_name_web" {
  value = aws_alb.alb_catwarebankweb.dns_name
}

output "alb_zone_id_web" {
  value = aws_alb.alb_catwarebankweb.zone_id
}

output "alb_dns_name_core" {
  value = aws_alb.alb_catwarebankcore.dns_name
}

output "alb_zone_id_core" {
  value = aws_alb.alb_catwarebankcore.zone_id
}

output "security_group_id" {
  value = aws_security_group.ecs_service.id
}

