
module "code_pipeline" {
  source                      = "./modules/code_pipeline"
  repository_url_core         = module.ecs.repository_url_core
  repository_url_web          = module.ecs.repository_url_web
  region                      = var.region
  ecs_service_name_web        = module.ecs.service_name_web
  ecs_service_name_core       = module.ecs.service_name_core
  ecs_cluster_name            = module.ecs.cluster_name
  run_task_subnet_id          = module.net.private_subnets_id[0]
  run_task_security_group_ids = flatten([ module.net.security_groups_ids, module.ecs.security_group_id])
  catwareserver		      = "http://www.catwarebank.tk/"
  catwarecoreserver	      = "core.catwarebank.tk"
}
