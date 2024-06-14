# Documentação das Regras de Implementadas e Histórias de Usuário

Essa documentação descreve as regras implementadas e as histórias de usuário para as funcionalidades de Acomodação e
Reserva. Ela fornece uma visão geral das capacidades do sistema e das interações do usuário com essas funcionalidades.

## Regras implementadas

### Acomodação

* Acomodações podem ter os seguintes estados:
    * DISPONÍVEL: indica que a acomodação está disponível para reserva.
    * RESERVADO: indica que a acomodação foi reservada por um usuário.
* Ao cadastrar uma nova acomodação, ela é automaticamente definida como DISPONÍVEL
* Ao excluir uma acomdação, ela é removida do siste e não estará mais disónível para reserva.

### Reserva:

* Reservas podem ter os seguintes estados:
    * ATIVA: Indica que a reserva está ativa e aguardando confirmação de pagamento.
    * CANCELADA: Indica que a reserva foi cancelada pelo usuário.
    * CONFIRMADA: Indica que a reserva foi confirmada após o pagamento de pelo menos 30% do valor total.
* Para confirmar uma reserva, o usuário deve fazer um pagamento equivalente a pelo menos 30% do valor total.
* Uma reserva pode ser cancelada a qualquer momento antes da confirmação do pagamento.
* Após a confirmação do pagamento, a reserva não pode ser cancelada e é marcada como CONFIRMADA.

## História de Usuário

### Acomodação:

1. Cadastro de Acomodação
    * Como um usuário, eu quero poder cadastrar uma nova acomodação para que os clientes possam reservá-la.
2. Listagem de Acomodações Disponíveis
    * Como um usuário, eu quero listar todas as acomodações disponíveis para que os clientes possam escolher onde
      desejam ficar.
3. Atualização de Informações de Acomodação
    * Como um usuário, eu quero poder atualizar as informações de uma acomodação para manter os dados atualizados.
4. Exclusão de Acomodação
    * Como um usuário, eu quero excluir uma acomodação que não está mais disponível para que ela não apareça mais nas
      buscas.

### Reserva:

1. Realização de Reserva
    * Como um usuário, eu quero poder fazer uma reserva de uma acomodação para garantir minha estadia.
2. Cancelamento de Reserva
    * Como um usuário, eu quero cancelar minha reserva caso não possa mais viajar.
3. Confirmação de Reserva
    * Como um usuário, eu quero confirmar minha reserva fazendo um pagamento de pelo menos 30% do valor total para
      garantir minha estadia.
