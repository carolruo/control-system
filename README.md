# RP control-system
Sistema para Controle de Manutenção de Equipamentos


API REST desenvolvida com a finalidade de controlar todos os estágios da execução de um serviço.


[Clique aqui para a documentação no Swagger](https://app.swaggerhub.com/apis/carolruo/control-system/1.0.0#/)

[Clique aqui para a documentação no Postman](https://documenter.getpostman.com/view/21616155/2s8YekQudH)


Utilizando essa API é possível:

1. Criação de ordem de serviço;

2. Criação de contas de clientes;

3. Consulta das ordens pendentes e clientes;

4. Registro de início e término do serviço pelo responsável;

5. Acompanhamento de todos os registros sobre as ordens de serviço.


Os seguintes endpoints estão implementados:

- `GET /ordens/{id}` obtém os detalhes de uma ordem de serviço
- `GET /ordens` obtém todas as ordens de serviço cadastradas
- `POST /ordens` adiciona uma nova ordem de serviço
- `PUT /ordens/{id}` atualiza uma ordem de serviço
- `DELETE /ordens/{id}` deleta uma ordem de serviço
- `GET /ordens/pendentes` obtém todas as ordens de serviço com status pendente
- `GET /ordens/{id}/reports` obtém todos os registros de andamento dentro de uma ordem de serviço
- `POST /ordens/{id}/reports` adiciona um registro dentro de uma ordem de serviço
- `GET /clientes/{id}` obtém os detalhes de um cliente
- `GET /clientes` obtém todos os clientes cadastrados
- `POST /clientes` adiciona um novo cliente
- `PUT /clientes/{id}` atualiza um cliente
- `DELETE /clientes/{id}` deleta um cliente