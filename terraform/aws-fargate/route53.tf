resource "aws_route53_delegation_set" "main" {
  reference_name = "DynDNS"
}

resource "aws_route53_zone" "primary_route" {
  name              = var.domain
  delegation_set_id = aws_route53_delegation_set.main.id
}

resource "aws_route53_record" "www-prod" {
  zone_id = aws_route53_zone.primary_route.id
  name    = "www.${var.domain}"
  type    = "A"

  alias {
    name                   = module.ecs.alb_dns_name_web
    zone_id                = module.ecs.alb_zone_id_web
    evaluate_target_health = true
  }
}

resource "aws_route53_record" "core-prod" {
  zone_id = aws_route53_zone.primary_route.id
  name    = "core.${var.domain}"
  type    = "A"

  alias {
    name                   = module.ecs.alb_dns_name_core
    zone_id                = module.ecs.alb_zone_id_core
    evaluate_target_health = true
  }
}

