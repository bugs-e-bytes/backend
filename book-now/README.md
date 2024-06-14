# Book Now

## Prerequisites

* Java 21
* Apache Maven 3.8.6
* Spring Boot 3.2.6
* Database MySQL
* HTTP Client: Postman, Insomnia, cURL etc.

## Tools

* IDE IntelliJ
* Postman
* Swagger
* MySQL

## Executando a aplicação

No terminal insira o comando: `mvn spring-boot:run`

* URL: http://localhost:8080/booknow

| Método | URL                              | Descrição                | Tipo permissão  |
|:-------|:---------------------------------|:-------------------------|:----------------|
| POST   | /admins/create-admin             | Cadastrar Administrador  | SUPER_ADMIN     |
| POST   | /auth/signup                     | Cadastrar Usuário        | Não autenticado |
| POST   | /auth/login                      | Autenticação             | Não autenticado |
| GET    | /users/me                        | Detalhes do usuário      | Autenticado     |
| PUT    | /users/update                    | Atualizar dados          | Autenticado     |
| DELETE | /users/delete                    | Desativar conta          | Autenticado     |
| POST   | /password/update-password        | Atualizar Senha          | Autenticado     |
| POST   | /password/reset-password         | Solicitar reset de senha | Não Autenticado |
| POST   | /password/reset-password/confirm | Nova senha               | Não Autenticado |
| POST   | /accommodations                  | Cadastrar Acomodação     | OWNER           |
| GET    | /accommodations                  | Listar Acomodação        | OWNER           |
| GET    | /accommodations/{id}             | Buscar Acomodação        | OWNER           |
| PUT    | /accommodations/{id}             | Atualizar Acomodação     | OWNER           |
| DELETE | /accommodations/{id}             | Deletar Acomodação       | OWNER           |
| GET    | /accommodations/list             | Listar Acomodação        | Não Autenticado |

### Erro de Autenticação

| Erro de Autenticação                | Exceção lançada         | Código de Status HTTP |
|:------------------------------------|:------------------------|:----------------------|
| Credenciais de Login incorretas     | BadCredentialsException | 401 - Unauthorized    |
| Account locked                      | AccountStatusException  | 403 - Forbidden       |
| Not authorized to access a resource | AccessDeniedException   | 403 - Forbidden       |
| Invalid JWT                         | SignatureException      | 401 - Unauthorized    |
| JWT has expired                     | ExpiredJwtException     | 401 - Unauthorized    |

