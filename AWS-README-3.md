# AWS Regions / Regioes
  - Como escolher a melhor regiao ?
    - Compliance / Conformidade
      - Os governos querem que os dados sejam local do país
    
    - Proximity/Proximidade
      - Proximidade para reduzir latencia/lag

    - Available Services / Serviços disponiveis
      - A regiao escolhida precisa ter o serviço que sera utilizado

    - Princing / Preço
      - Preço varia de regiao para regiao

# AWS Availability Zones / Zonas de disponibilidade 
  - Cada regiao tem muitas zonas de disponibilidade
  - No minimo 2 e no maximo 6 - normalmente tem 3
  - Cada zona de disponibilidade pode ter N data centers e sao separadas para evitar qualquer tipo de desastre

# AWS Points of Presence ( Edge Locations ) 
  - 216 pontos 
  - 205 edge locations e regional caches em 84 cidades sobre 42 paises

# IAM
  - Identity Access Management
  - Criacao de grupos e usuarios
  - Grupos apenas contem usuarios e nao podem conter outros grupos

# IAM Permissions
  - Usuarios ou Grupos podem ser atribuidos atraves de um documento do tipo JSON chamado policies/politicas
  - Estas politicas define as permissoes que o usuario vai ter 
  - na AWS se mante o principio do menor privilegio - nao dar mais permissoes que um usuario precisa
  - usuario root tem acesso a tudo, a melhor solucao é criar uma conta de administrador e usa-la para criar as proximas contas

# IAM User & Groups
  - Criacao de um usuario admin
  - Criar o usuario
  - Criar um grupo
  - Associar este grupo a uma politica - politica de Administrador
  - Associar este usuario ao grupo
  - Criar uma alias para a conta e atraves desta alias o usuario admin devera conseguir acessar o console AWS

# IAM Policies
  - Grupos podem ter regras assim como um usuario especifico
  - Regra para um usuario especifico se chama InLine
  - Uma estrutura de Policie consiste em
    - Versao
    - ID
    - Statement ( demonstracao )
      - Sid ( id do statement )
      - Effect - diz se vai permitir ou nao
      - Principal - para quem a regra é aplicada - para qual conta
      - Action - listas de acoes que sao permitidas ou nao para o servico aplicado a regra
      - Resource - lista de servicos a qual a regra sera aplicada
      - Condition - condicao para quando esta regra for aplicada

# IAM Password Policy 
  - coisas que podem serem feitas
  - Inserir regra com tamanho minimo para senha
  - Requerir caracteres especiais para criacao da senha
  - Permitir que todos os usuarios do IAM possam mudar sua propria senha
  - Requerir a altercao da senha de tempos em tempos
  - Bloquear que o usuario reutlize a mesma senha

# IAM - MFA  - Multi Factor Authentication
  - Com MFA voce tem muito mais seguranca com a conta
  - Usuario + Senha + Token
  - Opcoes de MFA
    - Virtual MFA device
    - Google Authenticator
    - Authy
  
  - Universal 2nd Factor ( U2F ) Security Key
    - YubiKey
  
  - Hardware Key Fob MFA Device
    - Thales - Gemalto

  - Hardware Key Fob MFA Device for AWS GovCloud ( US )
    - SurePassID

# IAM +
  - Para acessar a AWS voce tem 3 opcoes
    - AWS Management Console - password + MFA
    - AWS Command Line Interface ( CLI ) - access key
    - AWS Software Developer Kit ( SDK ) - access key
  - Access Key ( Chaves de Acesso ) são geradas atraves do console da AWS
  - Usuarios gerenciam suas prorpias access keys - sao iguais a uma senha - nao deve ser compartilhada

# AWS Cloud Shell
  - É um terminal na nuvem da AWS 

# IAM - Roles - Regras/Funções 
  - Permissoes para serviços dentro da AWS
  - EX.: Criamos uma Role para que uma instancia EC2 tenah acesso a um serviço do S3
   
# IAM Security Tools
  - IAM Credentials Report
    - Relatorio que lista todas suas contas e os status
  
  - IAM Access Advisor 
    - Exibe quais servicos o usuario tem acesso e quais foram os ultimos acessados
    - Favorece para obter informacao de acessos e assim poder revisar as politicas

# EC2
  - Elastic Computing Cloud
  - EC2 - Maquinas virtuais
  - EBS - Armazenamento de dados virtual ( HD )
  - ELB - Elastic Load Balancer - Distribuicao de carga entre maquinas 
  - ASG - Escalonar serviços de um grupo de escalonamento 
  - Sistemas Operacionais disponiveis - Linux, Windows e Mac
  
  - Em uma EC2 vai
    - Sistema operacional
    - CPU
    - RAM
    - HD
    - Network
    - Firewall
    - Bootstrap script - script inicial ao subir uma instancia EC2
  
  - AMI - Amazon Machine Image - Biblioteca com imagens para subir uma instancias EC2
  
  - m5.2xlarge
    - m - instancia da classe - General Purpose
    - 5 - geracao da instancia ( a proxima sera m6 )
    - 2xlarge - o tamanho de uma instancia

# EC2 Types  
  - 7 tipos de EC2
    - General Purpose
      - Bons para diversidade de workloads como webservers ou repositorios de codigo
      - Bom balanceamente entre Computacao > Memoria > Networking
      - T2

    - Compute Optimized
      - Bom para tasks com alta intensidade que requerem alta performance de processadores
      - Processamento em lotes - Batchs workloads
      - Trancsconding media
      - Servidores web de alta performance / machine learning / servidores de game
      - C6

    - Memory Optimized
      - Bom para processamento para grande dados em memoria
      - Boa performance para banco de dados relacional/nao relacional
      - Bom para cache distribuido na web - Elastic Cache
      - Memoria otimizada para BI - Business Inteligence
      - Processamento performatico de dado nao estruturado
      - R6g
    
    - Storage Optimized
      - Bom para acesso de muitos dados no armazenamento local
      - Alta frequencia online transacional - OLPT
      - Relacional e NoSql Databases
      - Cache em memoria - Redis
      - Armazenagem de aplicativos 
      - Sistema de arquivos distribuidos
      - I3

    - Accelereted Computing
    - Instance Features   
    - Measuring Instance Performance
  
  - Security Groups em EC2
    - Acesso a portas
    - Autoriza IP's
    - Controla o que entra e o que sai
    - Pode ser anexado em varias instancias
    - Amarrado a regiao 
    - Grupo de seguranca separado para SSH
    - Por default 
      - Todo trafego de entrada é bloqueado
      - Todo trafego de saida é liberado
  
  - Portas
    - 22 - SSH - Secure Shell - Linux
    - 21 - FTP - File Transfer Protocol
    - 22 - SFTP - Secure File Transfer Protocol
    - 80 - HTTP - Websites sem seguranca
    - 443 - HTTPS - Websites com seguranca
    - 3389 - RDP - Remote Desktop Protocol - acesso a uma instancia Windows
  
  - Conectando via Shell 
    - Dentro da pasta com o arquivo .pem ou .rsa executar o comando
      - chmod 0400 CHAVE_EC2.pem
      - ssh -i CHAVE_EC2.pem ec2-user@11.111.111.111

  - Nunca usar access e secret key em uma instancia EC2
  - Usar e associar uma Role chamada IAM Role criada anteriormente para que vc tenha acesso as informacoes por exemplo lista de usuarios
  
  - EC2 Instancias - Opcoes de compra
    - On-Demand - curto trabalho - preço fixo
      - Paga pelo o que usa
      - Maior custo
      - Sem termo de comprometimento
      - Recomendado para short-term e un-interrupted workloads - curto tempo e trabalhos ininterruptos
    
    - Reserved - contrato de pelo menos 1 ano
        - Reserved Instances - longas cargas de trabalho - banco de dados
        - Convertible Reserve Instances - longa carga de trabalho com flexibilidade de instancia
        - Schedule Reserved Instances - instancia que sera executada a partir de um tempo pre determinado
      - 75% de desconto comparado com On-Demand
      - Reserva de perido de 1 ano + desconto / 3 anos +++ desconto
      - Pagamento parcial antecipado ou total adiantado
      - Reservar uma instancia especifica
      - Usado para Banco de Dados

    - Spot Instances -  curto trabalhos, mais barato, pode perder instancias
      - Maior desconto 90% de desconto
      - Pode perder instancias
      - Bom para 
        - Batch Jobs 
        - Data analysis
        - Distributed workloads
    
    - Dedicated Hosts 
      - Servidor fisico
      - Compliance Requirements  
      - Use Your Existing Server-Bound Software Licenses
      - Mais caro
      - Uso exclusivo / usa suas proprias licenças
    
    - Dedicades Instances
      - Instgancias executadas para voce
      - Pode compartilhar o hardware com outras instancias na mesma conta
      - Nao tem controle de como a instancia é colocada ( pode apenas parar e iniciar )

# EC2 +
  - On Demand - Fica no hotel sempre que quiser e paga o preço integral
  - Reserved  - Se formos ficar por muito tempo podemos ter um bom disconto
  - Spot Instances - O Hotel permite que as pessoas deem ofertas pelos quartos vazios, a maior oferta fica com ele. Voce pode ser kikado a qualquer momento
  - Dedicated Host - Voce aluga o hotel inteiro
  - Storage Optimized - OLTP
  - Dedicated Hosts - compliance - license bills you based on the physical cores and underlying network socket visibility

# EC2 - Questions
  - Which EC2 Purchasing Option can provide you the biggest discount, but it is not suitable for critical jobs or databases?
    - Spot Instances
  - What should you use to control traffic in and out of EC2 instances?
    - Security Groups
  - How long can you reserve an EC2 Reserved Instance?
    - 1 or 3 years only.
  - You would like to deploy a High-Performance Computing (HPC) application on EC2 instances. Which EC2 instance type should you choose?
    - Compute Optimized
  - Which EC2 Purchasing Option should you use for an application you plan to run on a server continuously for 1 year?
    - Reserved Instances - Dica do Hotel
  - You are preparing to launch an application that will be hosted on a set of EC2 instances. This application needs some software installation and some OS packages need to be updated during the first launch. What is the best way to achieve this when you launch the EC2 instances?
    - EC2 User Data is used to bootstrap your EC2 instances using a bash script.
  - Which EC2 Instance Type should you choose for a critical application that uses an in-memory database?
    - Memory Optimized EC2
  - You have an e-commerce application with an OLTP database hosted on-premises. This application has popularity which results in its database has thousands of requests per second. You want to migrate the database to an EC2 instance. Which EC2 Instance Type should you choose to handle this high-frequency OLTP database?
    - Storage Optimized EC2 i
  - Security Groups can be attached to only one EC2 instance ?
    - False
  - You're planning to migrate on-premises applications to AWS. Your company has strict compliance requirements that require your applications to run on dedicated servers. You also need to use your own server-bound software license to reduce costs. Which EC2 Purchasing Option is suitable for you?
    - Dedicated Hosts
  You would like to deploy a database technology on an EC2 instance and the vendor license bills you based on the physical cores and underlying network socket visibility. Which EC2 Purchasing Option allows you to get visibility into them?
    - Dedicated Hosts

# EBS
  - Elastic Block Store
  - Armazenamento mesmo depois que a instancia é encerrada
  - CCP Level - só podem ser montadas apenas a uma instancia EC2
  - Associate Level - multi-attach feature para alguns EBS
  - Vinculado bound para uma regiao especifica
  - Nao é um hd fisico 
  - Delete on Terminate Attribute - deletar o HD ao fim da instancia - default

  - Snapshots
    - Backup do seu EBS
    - Podemos fazer copias e transferir os dados para outras regioes
    - Para ter o mesmo hd em outra regiao é necessario fazer um snapshot do EBS
  
  - AMI 
    - Amazon Machine Image - construida para uma regiao especifica e pode ser copiada atraves das regioes 
    - Sua propria AMI - vc mantem e cuida
    - Marketplace AMI - outras imagens pre montadas gratuitas / compra
  
  - EC2 Instance Store
    - EBS performance limitada
    - Bom para buffer / cache / scratch data / conteudos temporarios
    - Sua responsabilidade em fazer backup e replications 

  - EBS Volume Type
    - gp2 / gp3 (SSD) - General Purpose - preço equilibrado para performance e vasta variedade de workloads
    - io 1 / io 2 (SSD) - Hightes- Performance - latencia baixa - grande cargas de trabalho
    - st 1 (HDD) - Valor baixo para acesso frequente
    - sc 1 (HDD) - Menor valor para baixo acesso
    - Caracteristicas
      - Size
      - Throughput
      - IOPS
    - como volume para boot apenas gp2/gp3 e io1/io2 podem ser usadas

    - General Porpouse SSD
      - Custo bazeado no armazenamento, baixa latencia
      - Tem sistema para boots, desktop virtual, desenvolvimento e ambientes de teste
      - 1GB a 16TB
      - gp3 
        - Baseline de 3000IOPS ~ 16000IOPS | 125MB/s 1000MB/s
      - gp2 
        - 3000 IOPS max 16000
      - Detalhes
        - Latencia para armazenamento economico
    
    - Provisioned IOPS (PIOPS) SSD
      - Aplicacoes de negocio que precisam manter o melhor desempenho
      - Aplicacoes que precisam de mais de 16000 IOPS
      - Bom para Databases Workload 
      - io1 / io2 - 4GB ~ 16TB
        - MAX PIOPS 64000 Nitro EC2 max 32000
      - io2 Block Express - o mais foda
        - 4GB ~ 64TB

    - Hard Disk Drives ( HDD )
      - nao podem ser hd de boot
      - 125 MB to 16TB
      - st1
        - big data, datawarehouses, log processing
        - max 500MB - 500IOPS
      - sc1
        - para pouco acesso
        - o menor valor é o mais importante
        - max 250MB IOPS 250

# EBS Multi-Attach
  - Mesmo EBS para multiplas instancias na mesma zona disponivel
  - Apenas para familia io 1 / io 2 

# EFS - Elastic File System
  - O Amazon Elastic File System (EFS) aumenta e diminui automaticamente conforme você adiciona e remove arquivos, sem a necessidade de gerenciamento ou provisionamento.
  - Compartilhamento de arquivos sem a necessidade de um HD 
  - Usado para gerenciamento de conteudo, serviços de web, compartilhamento de dados, wordpress
  - Usa o protocolo NFSv4.1
  - Para acesso é necessario a criacao de um grupo de seguranca 
  - Compativel com Linux baseado no AMI ( Windows nao )
  - Para encriptografia vc pode usar KMS
  - Paga apenas conforme usa
  - Performance & Storage Classes
    - EFS Scale
    - Performance Mode 
    - Throughput mode
    - Storage Tiers

# EBS vs EFS 
  - EBS 
    - Podem ser associados apenas uma instancia de cada vez ( exceto a muth-attach ) na mesma zona disponivel
    - Para migrar o EBS criado para outra zona é ncessario criar um snapshot para compartilhamento
  
  - EFS
    - Pode ser associado a N instancias
    - Compartilhamento de dados / arquivos
    - Apenas funciona LINUX
    - Mais caro que o EBS

 # EBS Question
  - You have just terminated an EC2 instance in us-east-1a, and its attached EBS volume is now available. Your teammate tries to attach it to an EC2 instance in us-east-1b but he can't. What is a possible cause for this?
    - EBS Volumes are created for a specific AZ
  - You have launched an EC2 instance with two EBS volumes, the Root volume type and the other EBS volume type to store the data. A month later you are planning to terminate the EC2 instance. What's the default behavior that will happen to each EBS volume?
    - By default, the Root volume type will be deleted as its Delete On Termination
  - You can use an AMI in N.Virginia Region us-east-1 to launch an EC2 instance in any AWS Region.
    - AMIs are built for a specific AWS Region, they're unique for each AWS Region
  - Which of the following EBS volume types can be used as boot volumes when you create EC2 instances?
    - When creating EC2 instances, you can only use the following EBS volume types as boot volumes: gp2, gp3, io1, io2 ( TEM st1 e oi sc1 ja nao pode )
  - What is EBS Multi-Attach?
    - Using EBS Multi-Attach, you can attach the same EBS volume to multiple EC2 instances in the same AZ
  - You have provisioned an 8TB gp2 EBS volume and you are running out of IOPS. What is NOT a way to increase performance?
    - Increase the EBS Volume size
  - You have a fleet of EC2 instances distributes across AZs that process a large data set. What do you recommend to make the same data to be accessible as an NFS drive to all of your EC2 instances?
    - Use EFS
  - You would like to have a high-performance local cache for your application hosted on an EC2 instance. You don't mind losing the cache upon the termination of your EC2 instance. Which storage mechanism do you recommend as a Solutions Architect?
    - Instance Store  
  - You are running a high-performance database that requires an IOPS of 310,000 for its underlying storage. What do you recommend?
    - EC2 Instance Store

# ELB + ASG
  - Scalability
    - Vertical
      - Increase instance size ( up / down )
    - Horizontal
      - Increase number of instances ( in / out )
      - Auto Scaling Group
      - Load Balancer
  - High Availability 
    - Instancias em funcionamento para a mesma aplicacao em diversas zonas disponiveis 

  - ELB - Elastic Load Balancer
    - Usar o loadbalancer para:
      - Soread load across multiple instances 
      - Expor um unico ponto de acesso DNS para sua aplicacao
      - Lidar com falhas de instancias 
      - Healty Check 
      - Stickiness with cookies
      - Muitas zonas disponveis
      - trafego separado para public e privado
  
  - 4 Tipos de Load Balancer
    - Classic 
      - Http / Https / TCP / SSL 
      - Cross Zone - Desativado por default
      - Suporta apenas um certificado SSL - para mais sera ncessarios mais CLB ( Classic Load Balancer's )
    
    - Application
      - http / https / websocket
      - bom para micro servico e containers ( doker / amazon ecs )
      - host name fixo
      - aplicacao nao ve o ip do cliente diretamente, isso vem no header x-forwarded-for
      - Cross Zone - Sempre ativo
      - Multiplo SSL com multiplos certificados

    - Network Load Balancer - NLB 
      - TCP, UDP, TLS
      - Lida com milhoes de requisicoes por segundo
      - Tem um IP estatico por zona disponivel
      - Nao tem free tier ( é pago )
      - TCP Layer 4
      - Sabe direcionar por
        - IP 
        - EC2 Instances 
      - Cross Zone - Desativo por default
      - Multiplo SSL com multiplos certificados

    - Gateway GWLB
      - Operates at layer 3 - network
      - Firewall, Deteccao de intrusos, prevencao de sistemas, inspençao de pacote
      - Analisa o trafego
      - GENEVE protocol 6081

  - Sticky Sessions
    - Classic e Application
       
       - 
        Cliente 1 --- \                     / EC2 INSTANCE ( Cliente 1 faz duas requisicoes - gerenciado por cookie)
        Cliente 2 --- -  -->  LOAD BALANCER 
        Cliente 3 --- /                     \ EC2 INSTANCE ( Cliente 2 / 3 uma requisicao )
      
    - Se caso o cookie expirar o Cliente 1 pode ser redirecionado para outra instancia 
    - Garantir que o usuario nao perca sua sessao
    
    - Cookies
      - Application-based Cookie 
        - Custom cookie
          - gerado por um alvo - target
        - Application Cookie
          - Gerado pelo loadbalancer
      - Duration-based Cookies
        - gerado pelo load balancer
        - nome do Cookie é AWSALB para ALB, AWSELB para CLB
  
  - SSL/TLS 
    - Certificado SSL permite o trafego entre o cliente e seu load balancer para que seja encriptado em transicao 
    - SSL - Secure Sockets Layer
    - TLS - Transpor Layer Security - Mais usado
  
  - Connection Draining
    - Connection Draining - CLB
    - Deregistration Delay - ALB e NLB
    - Se a instancias esta em Draining ela vai finalizar todas as requiscoes em andamento, para as proximas requisicoes sera redirecionado para outra instancia ec2
    -           
# Auto Scaling Group
  - Add or remove instances EC2 to match decrease or increase load
  - Garantir que temos o numero maximo ou minimo de maquinas sendo executadas
  - Automaticamente registar novas instancias a um load balancer
  - Dynamic Scaling Policies
    - Target Tracking Scaling
    - Simple / Step Scaling
    - Scheduled Actions
  - Predictive Scaling
    - O historico vai sar analisado e as acoes vao ser executadas a partir dessa analise
    - Metricas para analise 
      - CPU Utilization
      - RequestCountPerTarget
      - Average Network
      - Any custom metric
    - Scaking Downs 
      - defalut 300 seconds - 5 min
      - durante esse periodo nao sera iniciado ou terminado uma instancia ec2

# ELB - Questions
  - Scaling an EC2 instance from r4.large to r4.4xlarge is called
    - Vertical Scalability
  - Running an application on an Auto Scaling Group that scales the number of EC2 instances in and out is called
    - Horizontal Scalability
  - Elastic Load Balancers provide a
    - Static DNS name we can use in our application
  - You are running a website on 10 EC2 instances fronted by an Elastic Load Balancer. Your users are complaining about the fact that the website always asks them to re-authenticate when they are moving between website pages. You are puzzled because it's working just fine on your machine and in the Dev environment with 1 EC2 instance. What could be the reason?
    - ELB Sticky Session feature ensures traffic for the same client is always redirected to the same target (e.g., EC2 instance). 
  - You are using an Application Load Balancer to distribute traffic to your website hosted on EC2 instances. It turns out that your website only sees traffic coming from private IPv4 addresses which are in fact your Application Load Balancer's IP addresses. What should you do to get the IP address of clients connected to your website?
    - To get the client's IP address, ALB adds an additional header called X-Forwarded-For contains the client's IP address.
  - You hosted an application on a set of EC2 instances fronted by an Elastic Load Balancer. A week later, users begin complaining that sometimes the application just doesn't work. You investigate the issue and found that some EC2 instances crash from time to time. What should you do to protect users from connecting to the EC2 instances that are crashing?
    - When you enable ELB Health Checks, your ELB won't send traffic to unhealthy
  - You are working as a Solutions Architect for a company and you are required to design an architecture for a high-performance, low-latency application that will receive millions of requests per second. Which type of Elastic Load Balancer should you choose?
    - Network Load Balancer 
  - Application Load Balancers support the following protocols, EXCEPT:
    - TCP ( TCP ( and UDP ) Network Balance and Classic TCP )
  - Application Load Balancers can route traffic to different Target Groups based on the following, EXCEPT:
    - Clients Location ( Geography ) ALBs can route traffic to different Target Groups based on URL Path, Hostname, HTTP Headers, and Query Strings.
  - Registered targets in a Target Groups for an Application Load Balancer can be one of the following, EXCEPT:
    - Network Load Balancer
  - For compliance purposes, you would like to expose a fixed static IP address to your end-users so that they can write firewall rules that will be stable and approved by regulators. What type of Elastic Load Balancer would you choose?
     - Network Load Balancer has one static IP address per AZ and you can attach an Elastic IP address to it. Application Load Balancers and Classic Load Balancers as a static DNS name.
  - You want to create a custom application-based cookie in your Application Load Balancer. Which of the following you can use as a cookie name?
    - The following cookie names are reserved by the ELB (AWSALB, AWSALBAPP, AWSALBTG).
  - You have a Network Load Balancer that distributes traffic across a set of EC2 instances in us-east-1. You have 2 EC2 instances in us-east-1b AZ and 5 EC2 instances in us-east-1e AZ. You have noticed that the CPU utilization is higher in the EC2 instances in us-east-1b AZ. After more investigation, you noticed that the traffic is equally distributed across the two AZs. How would you solve this problem?
    - When Cross-Zone Load Balancing is enabled, ELB distributes traffic evenly across all registered EC2 instances in all AZs.
  - Which feature in both Application Load Balancers and Network Load Balancers allows you to load multiple SSL certificates on one listener?
    - Server Name Indication - SNI
  - You have an Application Load Balancer that is configured to redirect traffic to 3 Target Groups based on the following hostnames: users.example.com, api.external.example.com, and checkout.example.com. You would like to configure HTTPS for each of these hostnames. How do you configure the ALB to make this work?
    - Server Name Indication (SNI) allows you to expose multiple HTTPS applications each with its own SSL certificate on the same listener. Read more here: https://aws.amazon.com/blogs/aws/new-application-load-balancer-sni/
  - You have an application hosted on a set of EC2 instances managed by an Auto Scaling Group that you configured both desired and maximum capacity to 3. Also, you have created a CloudWatch Alarm that is configured to scale out your ASG when CPU Utilization reaches 60%. Your application suddenly received huge traffic and is now running at 80% CPU Utilization. What will happen?
    - Nothing - The Auto Scaling Group can't go over the maximum capacity (you configured) during scale-out events.
  - You have an Auto Scaling Group fronted by an Application Load Balancer. You have configured the ASG to use ALB Health Checks, then one EC2 instance has just been reported unhealthy. What will happen to the EC2 instance?
    - You can configure the Auto Scaling Group to determine the EC2 instances' health based on Application Load Balancer Health Checks instead of EC2 Status Checks (default). When an EC2 instance fails the ALB Health Checks, its marked unhealthy and will be terminated while the ASG launches a new EC2 instance.
  - Your boss asked you to scale your Auto Scaling Group based on the number of requests per minute your application makes to your database. What should you do?
    - There's no CloudWatch Metric for "requests per minute" for backend-to-database connections. You need to create a CloudWatch Custom Metric, then create a CloudWatch Alarm.
  - A web application hosted on a fleet of EC2 instances managed by an Auto Scaling Group. You are exposing this application through an Application Load Balancer. Both the EC2 instances and the ALB are deployed on a VPC with the following CIDR 192.168.0.0/18. How do you configure the EC2 instances' security group to ensure only the ALB can access them on port 80?
    - This is the most secure way of ensuring only the ALB can access the EC2 instances. Referencing by security groups in rules is an extremely powerful rule and many questions at the exam rely on it. Make sure you fully master the concepts behind it! Add an InBound Rule with port 80 and ALB's Security Group as the source
  - An application is deployed with an Application Load Balancer and an Auto Scaling Group. Currently, you manually scale the ASG and you would like to define a Scaling Policy that will ensure the average number of connections to your EC2 instances is around 1000. Which Scaling Policy should you use?
    - Target Tracking Policy
  - Your application hosted on EC2 instances managed by an Auto Scaling Group suddenly receives a spike in traffic which triggers your ASG to scale out and a new EC2 instance has been launched. The traffic continuously increases but the ASG doesn't launch any new EC2 instances immediately but after 5 minutes. What is a possible cause for this behavior?
    - CoolDown period
  
# RDS 
  - Relational Database Service
  - Usa SQL query Language
  - Tipos de Bancos  
    - Postgres
    - MySql
    - Marida DB
    - Oracle
    - Microsoft SQL
    - Aurora 
  - Vantagens
    - OS patching
    - Backups 
      - Restore para um tempo especifico
      - Backups automaticos
      - Logs de transacao RDS a cada 5 minutos
      - 7 dias de retencao pode ir ate 35
      - DB Snapshots
        - Ativado manulamente
        - Retencao o tempo que vc determinar
    - Dashboards de monitoramento
    - Ler replicas para melhor performance
    - Multi AZ ( Zonas disponiveis )
    - Manutencao para windows
    - Escalabilidade Horizontal e Vertical
    - Armazenamento pela EBS - gp2 io1
    - Nao pode SSH nas instancias

# RDS - Storage Auto Scaling 
  - Ajuda a voce melhorar seu armazenamento em sua instancia RDS DB dinamicamente
  - Escala automaticamente mais espacao assim que o RDS detectar que voce esta ficando sem espaco 
  - Voce precisa incluir Maximum Storage Threshold - limite maximo para o armazenmaneto DB
  - Modificacao automatica se
    - o espaco livre é menos de 10%
    - o espaco baixo dura pelo menos 5 minutos
    - 6 horas se passaram desde que houve uma modificacao
  - Util para aplicacoes que tem trabalhos imprevistos
  - Suporta todos os bancos de dados

# RDS - Multi AZ x Replicas
  - Replicas
    - Acima de 5 Replicas
    - Zona disponivel, Cross AZ, Cross Region
    - Replicacao é ASYNC
      - Leituras sao consistente
        - EX.:
                RDS DB ( instance read replica ) <---ASYNC replicaction---> RDS DB Instance <---ASYNC replicaction---> RDS DB ( instance read replica )
    - Podem ser promvidas como sua propria DB 
    - Aplicacoes precisam atualizar a linha de conexao para aproveitar a leitura de outras replicas
    - Use Case
       - Voce tem uma aplicacao principal que faz leituras em um banco. E agora vc quer ter uma outra aplicacao de relatorios. Ao inves de vc sobrecarregar o banco principal vc cria uma replica dele e aponta a aplicacao secundaria para ele. A atualizacao da replica é assincrona
    - REPLICAS sao apenas para leitura SELECT
    - Network Cost
      - Mesma regiao - de graça
      - Regiaos diferente - tem que pagar
  
  - Multi AZ 
    - Replicacao Sincrona
    - Apenas um nome DNS - automatic app failover to standby
    - Aumenta a disponibilidade
    - Failover em caso de perda de zona disponivel, sem conexao, instance ou falha no armazenamento
    - Nao precisa de intervencao em caso de falha pois a instancia em standby sera promovida a master
    - Nao pode ser lida ou consultada pois é uma instancia em standby
    - Ex.:
          RDS DB  <------------- SYNC replication -------   RDS MASTER DB
          instance standby                                  instance 
          AZ - B                                            AZ - A
    - Voce pode ter replicas configuradas em multiplas AZ para recuperacao de desatre ? SIM 

  - Indo para uma Single AZ para uma Multi AZ
    - Zero downtime operation, nao precisa parar nenhum banco para isso
    - Apenas um clique em modify para a database em configuracoes
    - Basicamente o que acontece é
      - RDS DB Master > ( snapshot ) DB snapshot ( restore ) > Standby DB em uma nova AZ
  
# RDS Security - Encryption
  - Em repouso - at rest
    - Pode se encriptografar data bases masters e replicas com AWS KMS - AES-256
    - A encriptacao vc configura no momento da configuracao inicial
    - Se a master nao for encriptada as replicas tambem nao serao
    - TDE
      -  Transparent Data Encryption - alternativa para encriptografar sua database disponivel para Oracle e SQL Server
  - Em execucao
    - Certificados SSL para encryptografar 
    - Prove opcoes de SSL com certificados de confianca quando ha conexao com a database
    - Para forçar SSL
      - PostgreSQL - rds.force_ssl=1 ---> AWS RDS Console ( Parameter Groups )
      - MySql - GRANT USAGE ON *.* TO 'mysqlyser'@'%'REQUIRE SSL;

  - Operacoes
    - Snapshots tirados de uma data base nao criptografada seu backup sera nao criptografado
    - Snapshots tirados de uma data base criptografada seu backup sera criptografado
    - Pode copiar um snapshot em uma database encriptografada
    - Como encriptografar uma data base nao criptografada
      - Primeiro criamos um snapshot da data base nao criptografada
      - Copiar esse snapshot e no momento da copia vamos ativar a encriptografacao 
      - Depois migrar as aplicacoes para a nova data base e deletar a antiga  
  
  - Network & IAM
    - RDS sao geralmente criadas em uma rede privada
    - Controle de acesso é pelo IP / Grupo de Seguranca que tera acesso
  
  - Access Management
    -  IAM Polices é para quem pode CRIAR ou DELETAR a database Criar uma replica
    - Para conectar precisa de um usuario e senha
    - IAM-base auth pode ser usado para login em MySql e PostgresSQL

  - IAM
    - Para conexao IAM vc precisa apenas de um token em IAM & RDS API calls
    - token life time de 15 minutos
    - Conexoes de dentro e fora precisam se autenticar SSL
    - IAM gerencia os usuarios
    - Pode aproveitar regras do IAM 
    - EX.:
            3                                                                         1                                                                           2
            [ RDS Security Gropu [ MySql ]] <--- SSL enccryption Pass Auth Token ---> [[EC2 Security Group - IAM Role ] EC2 ] <--- API CAll - Get Auth Token ---> RDS Service
    - Encriptografia é feita apenas quando vc cria sua primeira instancia DB
    - Sua responsabilidade 
      - verificar Portas / IP / Grupos de seguranca e regras no DB's SG
      - Criacao de usuario e permisoes no IAM
      - Criar uma database com acesso publico ou privado
      - Garantir que os grupos acessem apenas com conexoes SSL
    - Reponsabilidade da AWS 
      - Sem acesso SSH
      - Atualizacao DB
      - Atualizacao OS

# Aurora
  - Da AWS - nao é open source
  - MySql e Postgress sao suportados pela Aurora
  - Aurora tem 5x mais performance que MySql e 3x mais que o PostgresSQL
  - Shared Storage Volume - Aumenta automaticamente de 10GB para 64TB
  - Pode ter 15 replicas ( MySql so pode ter 5)
  - Failouver é instantaneo e tem muitas zondas diposnivel
  - Custo de 20% a mais
  - 6 copias atraves de 3 zonas disponiveis 
  - Self healing
  - Storage is striped acrsso 100 volumes
  - Apenas uma instancia de Aurora pode escrever WRITE ( MASTER )
  - Automaticamente failover e menos de 30 segundos
  - Em cima da Master vc pode ter 15 replicas que leem READ
  - Qualquer uma das replicas pode virar master em caso de falha da master
  - Suporta Cross Region Replication
  - EX.:
     - Client > DNS Write Endpoint > Aurora Master ( Aurora Replica 1 + Aurora Replica 2 + Aurora Replica 3 + ... Aurora Replica 15 ) - AutoScaling
     - Client > DNS Read Endpoint ( connection level Load Balancing )>  Aurora Replica 1 + Aurora Replica 2 + Aurora Replica 3 + ... Aurora Replica 15
  - Automatica fail-over 
  - Backup and Recovery
  - Isolation and Security
  - Industry Compliance
  - Push-button scalling
  - Automated Patching with Zero Downtime
  - Advanced Monitoring
  - Routine Mainantence
  - Backtrack - restore any time without backup
  - Encypt usa KMS 
  - Em uso usa SSL
  - Possibilidade de se autentciar via IAM token
  - Sua responsabilidade proteger com grupos de seguranca
  - voce nao pode conectar via ssh
  - para conexao a data base é importante vc usar os endpoints de leitura e escrita uma acesso para cada um

# Elastic Cache
  - Cache em memoria com alta performance e baixa latencia
  - Reduz o carramento de dados de leituras
  - AWS Cuida da atualizacao
  - Elastic Cache Solution Architec
    - App Procura info no Cache
    - Nao encontra
    - Procura no Banco
    - Recebe informacao e salva no cache
    - Procura no cache novamente
    - Recebe informacao
  - Com o cache seu usuario nao perde a sessao de login pois os dados estao salvo no cache e as aplicacoes batem no mesmo cache
  - Redis vs Memcached
    - Redis
      - Multi AZ
      - Read Replicas
      - Data Durability
      - Backup restore features
      - Lembra RDS
    - MEMCACHED
      - Multi-node 
      - Nao tem alta disponibilidade
      - Nao é persistido
      - Nao tem backup e nem restore
      - Multi thread archteture

# Elastic Cache Strategies
  - Is it safe to cache data ? Yes
  - Is caching effective for that data ? If data changing slowly and the same keys are frequently needed
  - Tipos de padroes de Cache
    
    - Lazy Loading - Cache Aside - Lazy Population ( leitura )
      - Bate no Cache > Nao tem Cache > Bate no banco > Retorna dado do banco > Escreve no cache
      - Pros
        - Apenas a data requisitada é cacheada
        - falhas de no nao sao fatais
      - Contras
        - 3 requests
        - data pode ser atualizada na database e desatualizada no cache

    - Write Through - Add or Update cache ( salvar )
    - Bate no cache > Escreve no banco > escreve no cache
      - Pros
        - Data no cache nunca é antiga
        - leituras rapidas
      - Contras
        - Nao ha data ate a atualizacao do banco
        - Cache Churn - muitas datas nunca serao lida

    - Cache Evictions and Time to live ( TTL ) ( tempo )
      - deletar o item explicitamente no cache
      - item é evitado pois a memoria esta cheia
      - set de tempo para time-to-live TTL
      - TTL é bom para
        - Leaderboard
        - Comments
        - Activity Streams
        - pode ir de segundo a horas ou dias de duracao

      - Lazy Loading - Cache Aside - Lazy Population + Write Through - Add or Update cache ---> podem ser combinados
      - Usar TTL é bom excepto para Write Through

# Elastic Cache Replication
  - Cluster Mode Disabled
    - REDIS 
      - Um no primario para 5 replicas
      - Replicacao assincrona
      - O no master é leitura e escrita os demais é leitura
      - um Shard com todos os nos 

      | SHARD                 / ---> Cache secundario   | 
      |       Cache master --                           | 
      |                       \ ---> Cache secundario   | 

      - Multi AZ

  - Cluster Mode Enable
    - Igual ao disable mas agora vc tem um numero N de shards
    - Multi AZ capability
    - Up to 500 nodes per cluster 

# RDS Questions
  - Amazon RDS supports the following databases, EXCEPT:
    - MongoDB
  - You're planning for a new solution that requires a MySQL database that must be available even in case of a disaster in one of the Availability Zones. What should you use?
    - Enable Multi - AZ
  - We have an RDS database that struggles to keep up with the demand of requests from our website. Our million users mostly read news, and we don't post news very often. Which solution is NOT adapted to this problem?
    - RDS Multi - AZ
  - You have set up read replicas on your RDS database, but users are complaining that upon updating their social media posts, they do not see their updated posts right away. What is a possible cause for this?
    - Read Replicas have Asynchronous Replication, therefore it's likely your users will only read Eventual Consistency
  - Which RDS (NOT Aurora) feature when used does not require you to change the SQL connection string?
    - Multi-AZ
  - Your application running on a fleet of EC2 instances managed by an Auto Scaling Group behind an Application Load Balancer. Users have to constantly log back in and you don't want to enable Sticky Sessions on your ALB as you fear it will overload some EC2 instances. What should you do?
    - Store session data in ElastiCache
  - An analytics application is currently performing its queries against your main production RDS database. These queries run at any time of the day and slow down the RDS database which impacts your users' experience. What should you do to improve the users' experience?
    - Setup a Read Replica
  - You are running an ElastiCache Redis cluster which you want to ensure it is high available. What should you do?
    - Enable Multi AZ
  - How can you enhance the security of your ElastiCache Redis Cluster by forcing users to enter a password when they connect?
    - Use Redis Auth
  - Your company has a production Node.js application that is using RDS MySQL 5.6 as its database. A new application programmed in Java will perform some heavy analytics workload to create a dashboard on a regular hourly basis. What is the most cost-effective solution you can implement to minimize disruption for the main application?
    - Create a Read Replica in a different AZ and run the analytics workload on the replica database
  - You would like to create a disaster recovery strategy for your RDS PostgreSQL database so that in case of a regional outage the database can be quickly made available for both read and write workloads in another AWS Region. The DR database must be highly available. What do you recommend?
    - Create a Read Replica in a different region and enable Multi-AZ on the Read Replica
  - You have migrated the MySQL database from on-premises to RDS. You have a lot of applications and developers interacting with your database. Each developer has an IAM user in the company's AWS account. What is a suitable approach to give access to developers to the MySQL RDS DB instance instead of creating a DB user for each one?
    - Enable IAM Database Auth
  - Which of the following statement is true regarding replication in both RDS Read Replicas and Multi-AZ?
    - Read Replica uses Asynchronous Replication and Multi-AZ uses Synchronous Replication
  - How do you encrypt an unencrypted RDS DB instance?
    - Create a snapshot of the unencrypted RDS DB instance, copy the snapshot and tick "Enable encryption", then restore the RDS DB instance from the encrypted snapshot
  - For your RDS database, you can have up to ............ Read Replicas.
    - 5
  - Which RDS database technology does NOT support IAM Database Authentication?
    - Oracle
  - You have an un-encrypted RDS DB instance and you want to create Read Replicas. Can you configure the RDS Read Replicas to be encrypted?
    - No
  - How many Aurora Read Replicas can you have in a single Aurora DB Cluster?
    - 15
  - Amazon Aurora supports both .......................... databases.
    - Postgres MySql
  - What is the maximum number of Read Replicas you can add in an ElastiCache Redis Cluster with Cluster-Mode Disabled?
    - 5
  - You have an ElastiCache Redis Cluster that serves a popular application. You have noticed that there are a large number of requests that go to the database because a large number of items are removed from the cache before they expire. What is this called and how to solve it?
    - Cache Evictions, Scale up or out your ElastiCache Redis Cluster
  - You have a MySQL RDS database instance on which you want to enforce SSL connections. What should you do?
    - Execute a REQUIRE SSL SQL statment to all your DB users
  - You have an ElastiCache cluster with small cache size, so you want to ensure that only the data that's requested will be loaded into the cluster. Which caching strategy should you use?
    - Lazy Loading
  - You're hosting a dynamic website fronted by an ElastiCache Cluster. You have been instructed to keep latency to a minimum for all read requests for every user. Also, writes can take longer to happen. Which caching strategy do you recommend?
    - Write Through

# Route 53
  - DNS - Domain Name System
  - Para associar um DNS a uma instancia basta colocar o IP publico criado pela instancia associado ao DNS no momento de criar um Record ( CNAME ) 
  - DNS Terminologies
    - Domain Registrar
    - DNS Records
    - Zone file
    - Name Server
    - Top Level Domain TLD
    - Second Level Domain SLD
    - Ex.:
      - http:     //  api           .www          .example                .com  .
        protocol      domain name   sub domain    SLD / Apex Zone         TLD   root
      \_____________________________________________________________________________/
                                              |
                            FQDN ( Fully Qualified Domain Name )

  - Como funciona ?
    - Web Browser   > Local DNS Server ( example.com ?... apos achar "!" armazena no cache )  > ? Root DNS Server ( responde .com 1.2.3.4 )
                                                                                              > ? TLD DNS Server  ( respomde .example.com NS 5.6.7.8 )
                                                                                              > ! SLD DNS Server  (responde example.com IP 9.10.11.12 )
  - Amazon Route 53
    - Super disponivel, escalavel, super gerenciado e Authoritative DNS
    - Authoriative -> Voce pode atualizar os registros de DNS
    - Controle total ao DNS
    - Pode registar seu proprio dominio
    - Abilidade de checar healty check
    - O unico servico da AWS que oferece 100% de disponibilidade SLA
    - 53 é a porta tradicional do DNS
  
  - Records
    - Como vc deseja que seja a rota do seu trafego
    - Cada Record contem
      - Domain/subdomain Name
      - Record Type
      - Value
      - Routing Policy - como a rota 53 vai responder a queries
      - TTL - tempo do chache registrado no DNS Resolvers
      - Suport para 
        - A / AAAA / CNAME / NS
      - Tipos de Records
        - A - maps a hostname to IPV4
        - AAAA - hostname to IPV6
        - CNAME - hostname to another hostname
          - para CNAME é preciso 
            - A ou AAAA
            - Nao pode criar um CNAME record for the top node of a DNS namespace ( Zone Apex )
            - Ex.: Vc pode criar www.example.com mas nao pode criar example.com
        - NS - Name Servers fot the Hosted Zone
          - Controla como o trafego é roteado para seu dominio
  
  - Hosted Zones
    - Container para os registros que define como a rota do trafego para um dominio e subdominio
    - Public Hosted Zones
      - Dominio Publico
        - Ex.: application | .mypublicdomain.com 
    - Private Hosted Zones
      - Acesso apenas atraves de uma VPC
      - Compania privada ou rede privada
      - application | .company.internal
    - Paga 0.50 por mes 
    - Paga por dominio
  
  - DNS - TTL 
    - Configura o tempo
    - Client > Amazon Route 53 > Devolve o endereco ip com um TTL pre determinado > Cliente armazena no cache com o tempo determinado pelo ttl o endreco IP
    - TTL Alto 
      - menos trafego na rota 53
      - Possibilidade de dados desatualizados ( outdateds )
    - TTL Baixo
      - Vai ter mais trafego na rota 
      - Dados mais tempo atualizados
      - Facil de trocar os registros 
    - Exceto para ALIAS Records, TTL é obrigatorio ( mandatory ) para cada registro de DNS
    - Comando no shell para verificar o DNS criado e qual sua resposta 
      - nslookup demo.example.com
      - dig demo.example.com
  
  - CNAME vs Alias
    - CNAME 
      - aponta de um hostname para outro hostname ( app.mydomain.com -> blabla.anything.com )
      - Apenas para NON ROOT DOMAIN ( aka.something.mydomain.com )
      - Para apontar para um recurso da AWS vc insere o valor do IP ou enderco DNS no campo Value
      - Ex.:
        - Voce nao pode direcionar sua rota para o site example.com inserindo o valor do ELB pois nao é permitido na zona apex
        - Para que issso funcione vc precisaria criar um nome antes do dominio principal
          - Ex.: my-app.example.com 
        - Ou criando um ALIAS ficaria dessa maneira
          - pode usar apenas o example.com
          - Seleciona LoadBalancer
          - A regiao
          - E o LoadBalancer criado para aquela regiao
        
    - Alias
      - Especifico para Route 53 -> hostname para um recurso da aws 
      - Funciona para ROOT DOMAIN e NON ROOT DOMAIN
      - Sem cobrancas
      - Healty Check nativo
  
  - Alias Records
    - Mapeia um hostname para um recurso da aws 
    - Uma extensao de funcionalidade DNS
    - EX.: Amzon Route 53 ( ALIAS Record Enable ) > RecordName: example.com > Type: A > Value: MyLab-123456789.us-east-1-elb.amazonaws.com
    - Reconhece alteracoes automaticamente no recurso do endereco IP
    - Pode ser usado no top do no ( top node ) de um DNS namespace Ex.: example.com
    - Alias Records Type
      - A / AAAA - IPv4 / IPv6
    - Nao pode inserir TTL ( e setado automatico pelo S3 )
    - Alias Records Targets
      - Elastic Load Balancer
      - Cloudfront
      - API Gateway
      - Elastic Beanstal Enviroments
      - S3 Ebsites
      - VPC Interface Endpoints
      - Global Accelerator
      - Route 53 Record ( SAME HOSTED ZONE )
    - Voce nao pode setar um ALIAS para o DNS de um EC2
    - Para atribuir uma Alias, selecionar a opcao ALIAS, escolher a opcao de loadbalancer, selecionar a regiao e por fim a o loadbalancer criado para aquela regiao selecionada

  - Routing Policies
    - Define como a Route 53 vai responder ao DNS
    - DNS nao roteia qualquer trafego, apenas responde a queries de DNS
    - Route 53 Support as seguintes Routing Policies
      
      - Simple
        - Route trafic to a single resource
        - Pode especificar multiplos valores para o mesmo registro
        - Se multiplos valores forem retornados um valor randomico é escolhido pelo client side
        - Se Alias estiver enable apenas é necessario especificar um recurso aws
        - Nao pode ser associado a um Healt Check
      
      - Weighted
        - Controla a porcentagem de requisicao para cada recurso
                    Weight for a specific record
        - traffic % -----------------------------
                    Sum of all the weights for all records
        - Weights nao precisam somar acima de 100
        - DNS Records precisam ter o mesmo nome e tipo ( RecordName: example.com - Type: A ou RecordName: my-app.example.com Type: A)
        - Pode Associar com Healt Check
        - Dar o valor de 0 para um registro para parar de enviar o trafego para o recurso
        - If all records have weight of 0, then all records will be returned equally


      - Latency Based
        - Redireciona para o recurso que tem a menor latencia
        - Util para quando a latencia para o usuario for prioridade 
        - Latencia é baseada no trafego entre usuarios e Regioes da AWS
        - Pode ser combinado com Healt Check
        - Ao adicionar o ip de redirecionamento devemos saber qual a regiao em que este ip esta
        - Mesmo nome e mesmo tipo
      
      - Failover ( Atcive - Passive )
        -  É obrigatorio ter uma Health Check para que se uma instancia cair automaticamente seja redirecionado o trafego para a proxima
        - Pode ter apenas dois tipos de Failover record type
          - primary - com health check obrigatorio
          - secondary

      - Geolocation
        - Baseado aonde o usuario esta localizado
        - Deve criar um Default para sem localizacoes aonde esta aplicacao

      - Geoproximity
        - Trafego de rota baseado na localizacao geografica dos usuarios e recursos
        - Ability to shift traffic to resources based on the defined bias
        - Resources can be
          - AWS Resources
          - Latitude Longitude+

      - Multi-Value Answer
        - Usado para multiplos recursos
        - Quando Route 53 retorna multiplos valores / recursos
        - Pode ser associado a um Health Check
        - Acima de 8 healthy records sao retornado para cada Multi Value query
        - Multi Value nao e um substituto para Elastic Load Balancer

  - Route 53 Traffic Flow ( advantage )
      - Simples processo de criar e manter registros 
      - Editor visual
      - Configuracoes podem ser salvas como Trafic Flow Policy
      - Pode ser aplicado a diferentes Route 53 Hosted Zones ( diferente nome de dominios )
      - Suporta versionamento

  - Healt Check Route 53
    - Http Healty Checks sao apenas para recursos publicos
    - Criar um Healt Check para avaliar se um servico esta fora para redirecionar o cliente para outro
    - Automed DNS Failover
      - Tipos de Healt Check
        - Monitoramento do Endpoint
        - Monitoramento de outros HealtChecks
        - Monitoramneto do CloudWatch
    - 15 global healt checkers irao verificar o endpoint
      - Healty/Unhealthy - 3
      - Interval - 30 segundos - 10 segundos
      - Suporta Http Https e TCP
      - Se 18% dos verificadores disser que o endpoint esta saudavel o Route 53 considera entao saudabel, se nao nao esta saudavel.
    - Apenas OK se retornar stauts 2xx e 3xx 5120 bytes
    - Tem que configurar o router/firewall para receber as requiscioes do Healt Checker Route 53 pelo endereco de IP

    - Calculated Health Check
      - Combina p resultado de multiplos healty checks em apenas um só
      - Voce pode usar OR AND e NOT para combinacoes
      - Pode monitorar 256 Child Healt Checks
      - Especificar quantos healty checks precisam para fazer o Helty Check master dar OK

    - Private Hosted Zones
      - Route 53 health checker esta fora da VPC ( VPC ---> Zona privada )
      - Nao podem acessar private endpoints
      - Para verificacao vc pode criar um alarme no cloudWathc e fazer o health checker verificar este alarme no CW

  - Domain Registar vs DNS Service
    - Voce pode usar dominios fora do amazon e associalos ao nome do servidor do DNS
    - Voce pode usar Route 53 se caso tiver outro dominio
    - Para isso vc precisa
      - Criar um Public Hosted Zone na Route 53
      - Update NS Records on 3rd prty website to use Route 53 Name Servers
      - Domain Registrar é diferente de DNS Service

# Route 53 Questions
  - You have purchased mycoolcompany.com on Amazon Route 53 Registrar and would like the domain to point to your Elastic Load Balancer my-elb-1234567890.us-west-2.elb.amazonaws.com. Which Route 53 Record type must you use here?
    - Alias
  - You have deployed a new Elastic Beanstalk environment and would like to direct 5% of your production traffic to this new environment. This allows you to monitor for CloudWatch metrics and ensuring that there're no bugs exist with your new environment. Which Route 53 Record type allows you to do so?
    - Weighted
  - You have updated a Route 53 Record's myapp.mydomain.com value to point to a new Elastic Load Balancer, but it looks like users are still redirected to the old ELB. What is a possible cause for this behavior?
    - Because of the TTL
  - You have an application that's hosted in two different AWS Regions us-west-1 and eu-west-2. You want your users to get the best possible user experience by minimizing the response time from application servers to your users. Which Route 53 Routing Policy should you choose?
    - Latency
  - You have a legal requirement that people in any country but France should NOT be able to access your website. Which Route 53 Routing Policy helps you in achieving this?
     - Geolocation
  - You have purchased a domain on GoDaddy and would like to use Route 53 as the DNS Service Provider. What should you do to make this work?
    - Public Hosted Zones are meant to be used for people requesting your website through the Internet. Finally, NS records must be updated on the 3rd party Registrar.
  - Which of the following are NOT valid Route 53 Health Checks?
    - SQS Queue  

# VPC - Virtual Private CLoud
  - VPC, Subnets, Internet Gateway & NAT Gateways
  - Security Groups, Network ACL (NACL), VPC Flow Logs
  - VPC Peering, VPC Endpoints
  - Site to Site VPN & Direct Connect
  - VPC 
    - Rede privada para fazer deploy do seu recurso
  - Subnets 
    - Possibilita particionar sua rede dentro da VPC
    - Publica - acesso externo www
    - Privada - acesso apenas interno
  - Para definir acesso da internet e entre as subnets usamos Route Tables
  - Redes que a subnet privada usa para se comunicar 
    - NAT Gateway
      - AWS-managed
    - NAT Instances 
      - self-managed
  - Ex.:
    - Private Subnet > NAT > Gateway > www

# Network ACL & Security Groups
  - NACL - Network ACL
    - firewall que controla o trafego da subnet
    - pode ter regras de bloquear e permitir
    - sao anexadas ao nivel de Subnet
    - regras incluem apenas endereços de ip
  - Security Groups
    - fireal que controla o trafego de um ENI / EC2 instance
    - pode ter apenas regras de permitir 
    - regras incluem ip e outros grupos de seguranca
  
# VPC Flow Logs
  - captura todo o trafego das interfaces
  - vpc flow log
  - subnet flow logs
  - elastic network interface flow logs
  - ajuda a monitorar e problemas de conexao 
  - pode ser enviado para s3 / cloud watch logs

# VPC Peering / Endpoints
  - conecta duas vpc privadas usando aws network
  - age como se estivessem na mesma rede
  - nao tem overlapping CIDR ( IP Address Range )
  - VPC Peering connection is not transitive 
    - para se comunicar uma VPC com a outra é necessario criar um VPC peering
  - VPC Endpoints permitem vc se conectar a serviços da aws em uma rede privada
  - seguranca e baixa latencia para acesso
  - Ex.: para que sua instancia EC2 estando dentro de uma private subnet e queira acessar um S3 ou um Dynamo é necessario criar um VPC Endpoint Gateway
  - Ex.: EC2 > Private Subnet ( VPC ) > VPC Endpoint Gateway > S3 ou Dynamo DB

# Site to Site VPN & Direct Connect
  - Ex.: On-premises DC > pulblic www > Site-to-Site VPN > www > VPC
  - Direct Cpnnect DC
    - Conexao fisica, privada e rapida 
    - Leva pelo menos um mes para se estabelecer 
  - VPN nao pode acessar VPC endpoints

# VPC Closing Comments
  -  VPC - Virtual Private Cloud - uma vpc por default em cada regiao criada
  - Subnets - vinculada a uma zona disponivel, representa particao de rede da VPC
  - Internet Gateway - acesso a internet
  - NAT Gateways / Instances - acesso a rede privada
  - NACL - statless, subnet rules inboud outbound
  - Security Groups - opera a nivel EC2
  - VPC Peering - comunicador de vpcs sem overlapping de IP
  - VPC Endpoints - acesso privado aos servicos da aws
  - VPC Flow Logs - Logs do trafego na rede
  - Site to Site VPN - Acesso via VPN
  - Direct Connection - conexao direta

# Solucao Arquitetural
  - ELB > Private Subnet ( instancias EC2 ) > Data Subnet ( Amazon RDS ) ou Cache
  - LAMP Stack - Linux - Apache - MySql - PHP
    - pode ter Redis
  - Wordpress
    - para blogs
    - EX.: Elastic Load Balancer > Instancias EC2 > ENI > EFS ( para imagens )

# Questions - VPC
  - Security Groups operate at the ................. level while NACLs operate at the ................. level.
    - EC2 Instances, Subnet
  - You have attached an Internet Gateway to your VPC, but your EC2 instances still don't have access to the internet. What is NOT a possible issue?
    - The security group does not allow trafic in
  - You would like to provide Internet access to your EC2 instances in private subnets with IPv4 while making sure this solution requires the least amount of administration and scales seamlessly. What should you use?
    - NAT Gateway
  - When using VPC Endpoints, what are the only two AWS services that have a Gateway Endpoint instead of an Interface Endpoint?
    - Amazon S3 DynamoDB
  - You have 3 VPCs A, B, and C. You want to establish a VPC Peering connection between all the 3 VPCs. What should you do?
    - Estabalish 3 VPC ( A-B A-C B-C)
  - How can you capture information about IP traffic inside your VPCs?
    - VPC FLow Logs
  - You need to set up a dedicated connection between your on-premises corporate datacenter and AWS Cloud. This connection must be private, consistent, and traffic must not travel through the Internet. Which AWS service should you use?
      - AWS DIrect Connect 

# Amazon S3
  - Escalonamento infinito 
  - Cada Bucket tem um nome global e unico
  - Sao definidos por level de regiao
  - CHAVE ( KEY ) ----> s3://my-bucket/
  - VALOR ( VALUE ) --> my_folder/another_folder/my_file.txt
  - FULL -------------> s3://my-bucket/my_folder/another_folder/my_file.txt
  - Max upload - 5TB ( 5000 GB ) 

# S3 - Versionamento
  - Pode versionar seus arquivos
  - Mesma chave incrementa uma nova versao 
  - Excluir um arquivo versionado inicialmente nao exclui, apenas deixa como o arquivo pai como marcado para ser excluido

# S3 - Encriptografia
  - 4 metodos para encriptografar os objetos dentro do Amazon S3
    -  SSE-S3 - Deafult
      - criptografia usando chaves e gerenciados pelo Amazon S3
      - criptografia server side
      - AES-256
      - precisa de um header "x-amz-server-side-encryption": "AES256"
      - Objeto > Http/s + header >  ( Objeto + S3 Managed Data Key ) > Encriptografia > Bucket

    - SSE-KMS
      - criptografia usando chaves e gerenciados pelo KMS
      - vantagens: user control + audit trail
      - criptografado pelo server side
      - header "x-amz-server-side-encryption": "aws:kms"
      - Objeto > Http/s + Header > ( Objeto + KMS Customer Master Key ) > Encriptografia  > Bucket
    
    - SSE-C - Apenas por console CLI
      - criptografia usando chaves fornecidas pelo cliente
      - amazon s3 nao armazena a chave criptograda que vc fornece
      - usa apenas HTTPS !!!
      - ( Objeto + Client Side Data Key ) > ( HTTPS + Data Key Header )  >  ( Object + Chave fornecida pelo Client ) > Encriptografia > Bucket


    - Client Side Encyption - 
      - criptografia apos enviar os dados para o S3
      - clientes precisam encriptografar e decriptografar por eles mesmo
      - Object + CLient side data key > encriptografia > HTTP/s > bucket

  - S3 expoe
    - Http - nao é encriptografado
    - Https -  é encriptografado
    - usa certificados SSL/TLS ( criptografia in flight )

# S3 - Security
  - User Based
    - IAM Policies
  - Resource Based
    - Bucket Polices
    - Object Access Control List 
    - Bucket Access Control List 
  - IAM principal so pode acessar o S3 se
    - o usuario IAM esta permitido ou o recusro da politica permite e nao ha um NEGADO explicito
  - JSON Policie
    - bkuckets e objetos
    - permite ou nega acesso
  - Neworking - suport VPC endpoints
  - Loggin and Audit - S3 Access Logs / AWS CLoud Trail
  - MFA Delete ( multi factor auth )
  - Pre Signed URLs

# S3 Cors
  - Pode permitir para uma origem especifica ou para todas "*"
  - para acessar um bucket para outro em uma aplicacao web, é necessario a configuracao CORS 
  

# S3 Quiz
  - You have a 25 GB file that you're trying to upload to S3 but you're getting errors. What is a possible cause for this?
    - Use Multi Part upload for files bigget than 5 GB
  - You're getting errors while trying to create a new S3 bucket named dev. You're using a new AWS Account with no S3 buckets created before. What is a possible cause for this?
    - s3 buckets name must be globaly unique
  - You have enabled versioning in your S3 bucket which already contains a lot of files. Which version will the existing files have?
    - null
  - Your client wants to make sure that file encryption is happening in S3, but he wants to fully manage the encryption keys and never store them in AWS. You recommend him to use
    - SSE-C
  - A company you're working for wants their data stored in S3 to be encrypted. They don't mind the encryption keys stored and managed by AWS, but they want to maintain control over the rotation policy of the encryption keys. You recommend them to use
    - SSE-KMS
  - Your company does not trust AWS for the encryption process and wants it to happen on the application. You recommend them to use
    - Client Side Encryption
  - You have updated an S3 bucket policy to allow IAM users to read/write files in the S3 bucket, but one of the users complain that he can't perform a PutObject API call. What is a possible cause for this?
    - The IAM user must have an explicit DENY
  - You have a website that loads files from an S3 bucket. When you try the URL of the files directly in your Chrome browser it works, but when the website you're visiting tries to load these files it doesn't. What's the problem?
    - The CORS is wrong
  -   Which S3 encryption method mandates that you use HTTPS while uploading/download objects?
    - SSE-C

# AWS - CLI, SDK, IAM Roles & Policies
  - 