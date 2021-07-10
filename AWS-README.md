# AWS - Amazon Certification - Developer Associate 2021

## Create an account
[Credit Card Generator](https://www.vccgenerator.com/result/)

## IAM

  - IAM - Identity Access Management
 
  - Local dentro da Amazon aonde se cria e configura usuarios, grupos, permissoes...
 
  - Multifactor Authentication
    - User / Password / Token-Auth - Acessando atraves de um token - Ex.: Google Token que dura 30 secs
 
  - Identity Federation
    - Link da plataforma aws com 
      - facebook
      - linkedin
      - AD ( active directory )
      - Twitter
 
  - Permissions
    - Configurar para que o usuario consiga por exemplo criar maquinas virtuais ( EC2 ) do tipo: SMALL/ 12G Ram / 1T Disk
 
  - Passwords
    - consegue criar uma politica de rotatividade de senha para que ela expire para realizar sua troca constante
 
  - Shared
    - compartilhamento de serviços
 
  - PCI DSS Compliance ( VISA / MASTER )
    - Segurança que prove que os dados do cliente como forma de pagamento estejam bem protegidos

  - Users
    - Contas
  - Groups
    - Grupos de acesso a Data Base / S3 / EC2 / etc...
  - Roles
    - Regras que podem ser aplicadas a Users e/ou Groups
      - Ex.: Regras de acesso/serviço
  - Polices
    - Politicas do que o usuario pode fazer
      - Police para EC2 -> Read DB1
      - Police para EC2 -> Read and Write DB2
      - Pode ser aplicado para Users / Groups / Roles