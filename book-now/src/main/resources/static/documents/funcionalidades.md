# Funcionalidades

1. Cadastro de usuário (User)
2. Cadastro de acomodações (Accommodation)
3. Pesquisa e filtragem de acomodações
4. Realização de reservas (Reservation)
5. Autenticação e autorização

## Cadastro de Usuário

* User
    * ID
    * Username
    * Password
    * Email

* Accommodation
    * `id` (`Long`)
    * `name` (`String`)
    * `location` (`String`)
    * `price` (`BigDecimal`)
    * `maxGuests` (`int`) - número de hóspedes permitido
    * `amenities` (`String`) - características específicas (ex.: "WiFi, Pool, Parking")
    * `owner` (`User`) - dono da acomodação (anfitrião) - `@ManyToOne`
    * `reviews` (`List<Review>`) - lista de reservas - `@OneToMany(mappedBy = "accommodation")`

* Review
    * `id` (`Long`)
    * `user` (`User`) - `@ManyToOne`
    * `accommodation` (`Accommodation`) - `@ManyToOne`
    * `rating` (`int`)
    * `comment` (`String`)

* Reservation
    * User (Many to One)
    * Accommodation (Many to One)
    * startDate
    * endDate

## Pesquisa e filtro avanaçado de acomodações

1. Expandir as entidades para incluir mais detalhes
2. Criar DTOs (Data Transfer Objects) para simplificar a transferência de dados entre o cliente e o servidor.
3. Implementar consultas personalizadas no repositório.
4. Desenvolver o serviço para realizar a pesquisa avançada.
5. Criar endpoints no controlador para expor a funcionalidade.


- Um usuário pode se cadastrar no sistema e adicionar acomodações para que seja divulgados para serem reservadas.
- Um usuário pode se cadastrar no sistema e buscar por acomodações (filtros/pesquisa avançada) para efetuar uma reserva.
- O usuário cadastrado, para que possa cadastrar acomodações ou para que realizar uma reserva deve efetuar o login (
  estar autenticado).
- Qualquer usuário poderá realizar o cadastro de acomodação e/ou realizar a reserva.