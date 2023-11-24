# Baby Care Rest 👶🩺
_Totalmente desenvolvido por: [VitorRT](https://github.com/VitorRT) 🖖_

## Agradecimentos
_Gostaria de agradecer ao grupo da Smash Code que se esforçou para realizar este maravilhoso projeto._

_Embora fui eu quem desenvolveu toda a API da baby care, todo o CD na Azure Cloud, toda modelagem e criação do banco de dados, e toda essa documentação, não posso esquecer de todos os outros sistemas desenvolvidos pelos desenvolvedores da Smash Code._

_Sem eles não teriamos o aplicativo mobile, não teriamos o nosso dispositivo IOT que se comunica com a aplicação em nuvem e não teriamos nosso sistema ERP de controle._

## Sobre a Baby Care! 👶👍
_A Baby Care foi pensada para a família que deseja monitorar e garantir a saúde e o nascimento de seu bebê! Com a Baby Care você mãe terá acesso aos batimentos cardíacos do seu bebê, tudo em tempo real! Sem contar análises e insights sobre os dados coletados, tudo de forma simples e interativa._

_A mãe pode conectar o nosso dispositivo através do aplicativo mobile, basta criar uma conta de forma rápido e fácil, e pronto._

## Visão Geral - Baby Care Rest 
_A Baby Care Rest é a API Rest oficial do aplicativo. Esta API foi criada integralmente utilizando a linguagem Java em conjunto com o framework Spring. Inicialmente, o projeto adotou a arquitetura monolítica, mas à medida que a API crescer em complexidade e detalhamento, estou planejando uma transição gradual para a arquitetura de microserviços._

_Essa abordagem nos permite manter a flexibilidade necessária para atender às necessidades em constante evolução do nosso aplicativo, à medida que continuamos aprimorando e expandindo os recursos oferecidos aos nossos usuários._

_Além da API utilizei a ferramenta docker para subir outros containers que complementam a API, como por exemplo o servidor broker MQTT chamado 'Mosquitto' e um script node.js._


## Tecnologias utilizadas
- [Linguagem Java.](https://www.java.com/pt-BR/)
- [Maven.]()
- [Spring Boot.](https://spring.io/projects/spring-boot)
- [Docker.]()
- [Env Configuration.]()
- [H2 Database.]()
- [MQTT Broker Mosquitto]()
- [WebSocket]()
- [SMTP Mail Service]()
- [Azure SQL Server DB (Cloud API)]()

<small>A API em produção (nuvem) está conectada em um banco de dados em nuvem na azure cloud. Porém como queremos manter esse projeto open source optamos por deixar o banco em memória (H2 Database) nessa API do github. Mesmo assim você consegue rodar e testar a API localmente, basta seguir as instruções.</small>


## Dependências do projeto (Spring Boot)
1. Spring Web.
2. Spring Data JPA.
3. DevTools.
4. Lombok.
5. Spring Boot Tests.
6. H2 Driver.
7. Spring Boot Validation.
8. Spring Mail
9. Dotenv Java
10. Spring Integration Mqtt
11. Spring WebSocket
12. Spring Security 6
13. JWT Token


## Boas práticas usadas
1. Estrutura do projeto organizada.
2. Versionamento de API com o git flow.
3. Uso dos verbos HTTP corretos.
4. Uso dos status codes adequados.
5. Uso de DTO's.
6. Tratamento de erros constantes
7. Testes unitários e automatizados.
8. Padrões de retorno.
9. SRP e bom acoplamento das classes
10. Injeção de dependência.


## Pré-requisitos
- JDK 17 instalado.
- Docker instalado. **IMPORTANTE**
- Docker Compose instalado. **IMPORTANTE**
- Porta 8080 disponível para uso.
- Porta 1883 disponível para uso.
- Porta 9001 disponível para uso.

<small>O docker e o docker compose são requisitos porque a API se comunica com um servidor MQTT do Mosquitto, e ela faz isso pelo docker.</small>

## Configurando variáveis de ambiente 🔒
_Para rodar a api localmente essa é uma etapa MUITO importante._

_Na raiz do projeto adicione um arquivo `.env` com essas configurações:_

```
#######################################
# Datasource Database Dev Config
#######################################
SPRING_DATASOURCE_DB_DEV_URL=jdbc:h2:mem:babycare
SPRING_DATASOURCE_DB_DEV_USERNAME=sa
SPRING_DATASOURCE_DB_DEV_PASSWORD=
SPRING_DATASOURCE_DB_DEV_DRIVER=org.h2.Driver


#######################################
# Java Mail Sender Config
#######################################
SPRING_MAIL_USERNAME=project.smashcode@gmail.com
SPRING_MAIL_PASSWORD=gudrxzryvohhtorp

#################################
# JWT Config
#################################
JWT_SECRET=batatinhafrita
```

## Instalação e uso 
_Para subir o projeto não tem segredo, basta apenas rodar um comando._

Basta entrar na raiz do projeto e rodar o seguinte comando no terminal:

<small>Rodar a API em background</small>
```bash
docker-compose up -d
```
<small>Rodar a API com os logs no terminal</small>
```bash
docker-compose up
```


## Endpoints 🪧

### Endpoints para Recursos de Conta de Usuário
Aqui estão os endpoints disponíveis para recursos relacionados à conta de usuário:

1. Registrar uma conta de usuário
    - Método: **POST**
    - Bearer Token: FALSE
    - Endpoint: `/account/signup`
    - Possíveis Status: 201 (Criado), 400 (Requisição Inválida)

<small>Body:</small>
```json
{
    "email": "dragonxdgames@gmail.com",
    "password": "eusouoadminbitch",
    "fullName": "Vitor",
    "birthDate": "2004-03-24",
    "bloodType": "O+",
    "username": "@VitorRT"
}
```
<small>Importante: A api vai mandar uma mensagem de agradecimentos para o seu email, então para ter uma experiência use uma conta válida.</small>


2. Fazer login 
    - Método: **POST**
    - Bearer Token: FALSE
    - Endpoint: `/account/signin`
    - Possíveis Status: 200 (Logado), 403 (Dados inválidos)
      <small>Body:</small>
```json
{
    "email": "dragonxdgames@gmail.com",
    "password": "eusouoadminbitch"
}
```

3. Alterar nome de perfil
   - Método: **PATCH**
   - Bearer Token: TRUE
   - Endpoint: `/account/fullname/change?fullName=${newName}&accountId=${accountId}`
   - Possíveis Status: 200 (Successo), 403 (Não Autenticado), 400 (Error)


4. Alterar username da conta
   - Método: **PATCH**
   - Bearer Token: TRUE
   - Endpoint: `/account/username/change?username=${newUsername}&accountId=${accountId}`
   - Possíveis Status: 200 (Successo), 403 (Não Autenticado), 400 (Error)


5. Alterar email da conta
   - Método: **PATCH**
   - Bearer Token: TRUE
  - Endpoint: `/account/email/change?email=${newEmail}&accountId=${accountId}`
  - Possíveis Status: 200 (Successo), 403 (Não Autenticado), 400 (Error)


6. Alterar senha da conta
   - Método: **PATCH**
   - Bearer Token: TRUE
   - Endpoint: `/account/email/change?email=${newEmail}&accountId=${accountId}`
   - Possíveis Status: 200 (Successo), 403 (Não Autenticado), 400 (Error)


6. Deletar conta
   - Método: **PATCH**
   - Bearer Token: TRUE
   - Endpoint: `http://localhost:8080/account/delete?accountId=${accountId}`
   - Possíveis Status: 204 (Sem conteudo), 403 (Não Autenticado), 400 (Error)


7. [ADMIN] Listar contas cadastradas
   - Método: **GET**
   - Bearer Token: TRUE
   - Endpoint: `http://localhost:8080/account/list`
   - Possíveis Status: 200 (Sucesso), 403 (Não Autenticado), 400 (Error)


<hr />


### Endpoints para Recursos de Bebê (Projetos)
1. Registrar Bebê
   - Método: **POST**
   - Bearer Token: TRUE
   - Endpoint: `/baby/project/create?accountId=${accountId}`
   - Possíveis Status: 201 (Criado), 403 (Dados inválidos), 400 (Error)
   
<small>Body:</small>
```json
{
   "babyName": "Isabella",
   "genre": "Female"
}
```


### Endpoints para Recursos de Conexão com Dispositivo
1. Conectar dispositivo
   - Método: **POST**
   - Bearer Token: TRUE
   - Endpoint: `/device/connect`
   - Possíveis Status: 200 (Conectado), 403 (Dados inválidos), 400 (Error)

<small>Body:</small>
```json
{
   "projectId": "${projectId}",
   "deviceId": "${deviceId}"
}	
```


2. Disconectar dispositivo
   - Método: **GET**
   - Bearer Token: TRUE
   - Endpoint: `/device/disconnect`
   - Possíveis Status: 200 (Disconectado), 403 (Dados inválidos), 400 (Error)
