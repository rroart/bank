resource "aws_secretsmanager_secret" "bankkey" {
  name = "bankkey"
}

resource "aws_secretsmanager_secret" "password" {
  name = "password"
}
