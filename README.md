# E_commercer 

## Serviços
1. API Gateway
Função: Centralizar as requisições externas. 

2. Serviço de Clientes (Sincrono)
Banco de dados: MySQL.
Função: Gerenciar cadastro, autenticação e dados dos clientes.
Arquitetura: Usar Spring MVC tradicional devido à necessidade de interação mais rápida e direta (pesquisa por dados de clientes pode ser síncrona).
Chamadas síncronas: O front-end se comunica diretamente para o cadastro e consulta de clientes.

3. Serviço de Produtos (Sincrono)
Banco de dados: MySQL.
Função: Gerenciar catálogo de produtos, incluindo CRUD de produtos e consulta de estoque.
Arquitetura: Spring MVC. Chamadas síncronas para permitir uma busca eficiente de produtos no front-end.

4. Serviço de Carrinho (Assíncrono e Event-driven)
Banco de dados: MySQL.
Função: Adicionar produtos ao carrinho, calcular subtotal, aplicar cupons de desconto.
Arquitetura: Spring WebFlux com Kafka para processar eventos de adição e remoção de itens no carrinho de forma assíncrona.
Evento: Quando o cliente adiciona ou remove um produto do carrinho, um evento é emitido e processado para refletir a ação no serviço de Carrinho.

5. Serviço de Pagamento (Assíncrono e Event-driven)
Função: Processar pagamento de forma segura e eficiente.
Arquitetura: Spring WebFlux para assíncronia total. Eventos de confirmação de pagamento podem ser emitidos via Kafka e, após confirmação, o serviço de pagamento aciona o serviço de contabilidade.
Fluxo:
Recebe a solicitação de pagamento.
Emite evento para processar o pagamento.
Retorna resposta assíncrona após conclusão do pagamento.

6. Serviço de Contabilidade (Assíncrono e Event-driven)
Banco de dados: MongoDB (devido à natureza não relacional e flexível para dados de transações).
Função: Gerenciar o registro de transações financeiras.
Arquitetura: Consumidor de eventos via Kafka. Quando um pagamento é confirmado, a contabilidade é atualizada em tempo real com os dados da transação.

7. Serviço de Email (Assíncrono e Event-driven)
Função: Enviar notificações por email, como confirmação de compra, atualizações de pedidos, etc.
Arquitetura: Consumidor de eventos via Kafka. Eventos disparados por pagamento ou outras ações desencadeiam o envio de notificações por email.
