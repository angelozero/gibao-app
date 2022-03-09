# EC2
    - uso de capacidade coputacional 
    - pague pelo o que usar
- Tipos de instancia EC2
    - Uso Geral
        - Recursos equilibrados
    - Otimizada para computacao
        - Processadores para alta performance
        - Uso intenso
    - Otimizada para memória
        - Alta performance
        - Adeequado para banco de dados
    - Computacao Acelerada
        - Ideal para streaming
        - ideal para banco de dados de alta performance
    - Otimizada para armazenamento
        - baixa latencia e operacoes com IOPS
        - arquivos distribuidos
        - data warehousing
- Preços EC2
    - Sob demanda
        - nao se aplica custo inicial ou contrato minimo
        - ideal para carga de trabalho irregulares de curto prazo
        - ex.: black friday
    - Spot
        - ideal para carga de trabalho com horario flexivel
        - economia de preco sob demanda
    - Reservada
        - desconto de faturamento sobre preços sob demanda
        - compromisso entre um ano ou tres anos
    - Compute Savings Plan
        - oferece 72% de economia 
        - utiliza quando precisa
        - compromisso e um ou tres anos 
- Comutaçao dedicada
    - Instancia dedicada
        - executado em uma VPC em hardware para um unico cliente
        - custo mais alto comparado com instancias padroes
    - Host dedicado 
        - servidor fisico 
        - opcao mais cara da amazon ec2
- Qual a diferenca entre Compute Savings Plans e Instancias Spots
    - Compute Saving Plan = tipo pacote pré-pago de recurso que compramos, recurso computacional. Instancia SPOT = recurso computacional mais barato e que pode ser desativado pela AWS a qualquer momento
- Auto Scaling EC2
    - usa escalonamento dinamico e preditivo
    - dimensiona capacidade conforme os requisitos de computacao sao mudados 

# Elastic Load Balancer
    - Distribui automaticamente o trafego 
    - fornece unico ponto de contato para grupo de auto scaling
    - tem um endereco de ip especifico 
    - distribui o trafego para as instancias
    - Ex.:
        - Remover instancias desnecessarias - Auto Scaling
        - Adicionar instancia - Auto Scaling
        - Distribuicao de carga em varias instancias - Load Balancer
        - Ajustar automaticamente o numero de instancias - Auto Scaling
        - Unico ponto de contato para trafego - Load Balancer

# SNS -  Amazon Simple Notification Service
    - mensagens publicadas em topicos

 # SQS - Amazon Simple Queue Service
    - envia, armazena e recebe mensagens dos componentes de software
    - armazena mensagens na fila ate que os serviços estejam disponiveis

# AWS Lambda
    - pode executar codigo sem provisionar servidores
    - configurado para ser disparado, executado via trigger
    - paga pelo tempo que ele executou
    
# ECS - Amazon Elastic Container Service
# EKS - Amazon Elastic 
# AWS Fargate
 
# AWS Infrasctrutre
    - Selecionar uma melhor regiao
        - conformidade com dados e requisitos legais
        - proximo com os clientes
        - servico de regiao disponivel
        - preço
    - qual a relacao entre regioes e zonas de disponibilidade ?
        -  Regiões são como diversas áreas geográficas. E as AZs como datacenters dentro das regiões

# Amazon Cloud Front
    - Entrega de conteudo para a EdgeLocation ( bolinha azul ) e o conteudo fica armazenado em um cache para que quem esteja acessando nao tenha delay
    - servico global de entrega de conteudo
    - local que entrega o conteudo - ponto de presença - edge location

# AWS Outposts
    - Atende estrutura para datacenter local

# Acessar API - AWS
    - Browser 
    - CLI
    - Kits de desenvolvimento SDKs

# VPC - Amazon Privte Cloud
    - Rede virtual definida por vc
    - Sub-Rede
        - VPC aonde vc pode colocar grupos isoldados 
        - pode ser publicas ou privadas
        - Numvem AWS > VPC > Gateway Internet > Sub-Rede Pulbica ( instancias ec2 ) > Sub-Rede Privada
    - Necessario um internet gateway para que possa ser acessado sua sub-rede publica
    - Gateway Privado
        - Consegue acessar a sub-rede privada mas necessario conexao via VPN e o gateway privado virtual

# AWS Direct Conect
    - Roteador configurado para o Gateway Privado Virtual
    - conexao dedicada
    - nuvem hibrida
    - isola banco de dados com info de cliente - Sub-rede Privada
    - cria conexao vpn entre vpc e a rede internea gateway privado
    - estabelece conexado dedicada ao data center - aws direct conect
    - Trafego de rede em uma vpc
        - Gateway da internet > ACL ( black list ) > Grupo de seguranca, amarrado a instancia ec2, porta http > Sub-Rede pulblica
    - Network ACL
        - permite todo trafego de entrada e saida 
        - controle de acesso
        - stateless ( sem estado )
    - Grupo de Seguranca 
        - amarrado em instancia EC2
        - por padrao é negado todo trafego, tem que configurar
        - statefull ( com estado )
        
# Amazon Route 53 e Cloud Front
    - Servico de DNS
    - direciona usuarios para aplicacoes internet 
    
# EBS - Amazon Elastic Block Store
    - Armazenamento de instancia EC2
    - em uma unica zona de disponibilidade
    - Snapshot
        - Dia 1 - todos sao copiados
        - Dia 2 - somente dado que foram alterados 
    - qual diferença entre armazenamento de instancia e volumes EBS
        - O Armazenamento da instância é perdido quando ela é encerrada, já os EBS não, assim podemos utilizá-los novamente em outras instâncias se desejarmos
 
 # Amazon Simple Storage Service   
    - Classes de Armazenamento
        - S3 Standard
            - acesso a dados com frequencia
            - replicado no min a 3 zonas de disponibilidade
        - S3 Standard - IA
            - acesso a dados com pouca frequencia
            - igual ao S3 Standard mas com um custo menor
        - S3 One Zone - IA
            - apenas uma zona
            - tem o preço mais baixo
        - S3 Intelligent Tiering
            - acesso a dados com desconhecimento da quantidade de acesso
            - taxa de monitoramento
        - S3 Glacier
            - armazenamento de backup , para dados nao acessados
            - para recuperar leva-se de minutos a horas
        - S3 Glacier Deep Archive
            - armazenamento de backup , para dados nao acessados
            - para recuperar leva-se ate 12 horas

# EFS - Amazon Elastic File System    
    - Armazenamento de arquivos em rede
    - Fornece dados para instancia EC2
    - Pode compartilhar arquivos para fora 
    - armazenam em varias zonas de disponibilidade 

# RDS - Amazon Relational Database Service ( SQL )
    - Amazon Aurora
        - Relacional
        - Entripese Class
        - Baixo Custo

# Amazon DynamoDB ( NoSQL )
    - Serveless ( nao precisa de instancia ) 
    - dimensionado automaticamente 
    - chave valor
    - 10 trilhoes de solicitacoes por dia

# AWS DMS - Database Migration Service 
    - migracao de banco de dados       
    - Banco de Dados SQL > DMS > AuroraDB

# Amazon Bancos
    - Redshift - consulta e analiza dados em um data warehouse - S3
    - DocumentDB - usa mongoDb - banco de dados de documento
    - Neptune - para dados altamente conectados - estrutura de grafo
    - QLDB - historico completo das alteracoes de dados na sua aplicacao
    - Managed Blockchain - registros descentralizados
    - ElasticCache ( REDIS / MEMCACHE ) - armazenamento em cache para melhorar o tempo de leitura do banco de dados
    - DynamoDB Accelerator - melhora o tempo de resposta de mili para microssegundos

# Modelo de responsabilidade compartilhada
    - AWS 
        - Responsavel pela parte fisica / sofware / hardware / atualizacao
        - seguranca fisica dos datacenters
        - manutencao infra de rede e virtualizacao

    - Cliente 
        - Dados / configuracaoes de acesso e rede 
        - Seguranca na Nuvem
        - sistema operacional da instancia
        - aplicacoes 
        - grpos de seguranca 
        - fireawalls baseados em hosts
        - atualizacao de software
        - grupo de seguranca
        - definicao de permissoes para objetos do amazon s3 

# IAM - 
    -  Documento que concede ou nega permisssoes para servicos e recursos da AWS
    - Recursos 
        - Usuario
            - pode ser pessoa ou aplicacao
            - interage com produtos e recursos aws
            - boa pratica: criar usuarios individuais
        - Politicas 
            - documento que concede ou nao permissoes para servico e/ou recurso
            - boa pratica: principio do privilegio minimo
        - Grupos
            - associar politica ao grupo ao inves para um usuario individual 
        - Funcoes
            - acesso temporario as permissoes
            - nao precisa alterar politica de grupo e/ou usuario
            - gerenciamento de permissoes temporarias
        - Autenticacao Multifator
            - fornece camada extra de protecao para sua conta aws

# AWS Organizations
    - consolida diversas contas em um local central
    - Politica de Controle de Servico - SCP - controlar centralmente as permissoes para contas em sua organizacao
    - se aplica conta individual  e ou uma unidade organizacional

# AWS Artifact
    - relatorios de seguranca e conformidade e contratos online selecionados
    - compliance
    - Compliance Center - recursos que ajudam a entender melhor 

# AWS WAF - Seguranca de aplicacoes
    - bloqueio ataque hacker
    - bloqueia de um ip invalido

# AWS Shield
    - oferece protecao contra ataques distribuidos DDoS
    - escreve regras de ACL da web personalizadas com AWS WAF

# Amazon Inspector
    - permite avaliacoes de seguranca automatizadas em suas aplicacoes
    - gera relatorios 
    - identifica vunerabilidades

# AWS KMS - Key Management Service
    - cria chaves croptograficas
    - executa ooperacoes de criptografia por meio do uso de chaves critptograficas

# Amazon GuardDuty
    - fornece detectcao inteligente de ameacas para servicos e produtos da AWS 
    - 

# Cloud Watch 
    - monitora metricas

# Cloud Trail
    - rastreia atividades 
    - filtra logs 
    - detecta atividades incomum na conta 
    - o que aconteceu ? quem fez a solicitacao ? quando isso aconteceu ? e como foi essa solicitacao ? 

# Trusted Advisor 
    - consultar de confianca que analisa a estrutura do seu servico e faz recomendacoes
    - receber orientacoes em tempo real para melhorar o ambiente
    - verifica permissoes de acesso aberto
    - Performance / Tolerancia a falhas

# Precificacao e suporte
    - Free Tier
        - sempre gratuito / 12 meses
    - Pagamento conforme o uso
    - pague menos ao fazer reserva
    - pague menos com descontos baseados em volumes

# Faturamento consolidado
# AWS Budgets
    - definir limites para uso e custos dos servicos

# AWS Cost Explorer 
    - gerenciar custos de uso AWS durante ao longo do tempo

# Suporte Basico
    - Gratuito
    - Developer
    - Busines - contem todas as categorias do Trusted Advisor e é o mais barato
    - Enterprise

# AWS Market Place
    - canal de vendas

# AWS CAF - Cloud Adoption Framework
    - perspectivas de negocios

# AWS Snow - dispotivos de transferencias de dados
    - AWS Snowcone
    - AWS Snowball
    - AWS Snowmobile