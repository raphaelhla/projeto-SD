# projeto-SD

## Sobre o projeto
Sistema onde o usuário envia uma proposta para solicitar um emprestimo em uma empresa. 

O projeto possui 3 microsserviços que se comunicam entre si de forma assíncrona através do RabbitMQ:
- proposta-app
- notificacao
- analise-credito

### Fluxo de execucao
1. Usuário envia a proposta.
2. Após o envio da proposta, o usuário receberá uma notificação SMS informando que a proposta está em análise.
3. A proposta é analisada.
4. Após concluir a análise, o usuário receberá uma notificação via SMS informando se a proposta foi aprovada ou não.

## Tecnologias utilizadas
- Java 17
- Spring 
- PostgreeSQL
- Docker
- RabbitMQ
- Amazon SNS

## Subindo a aplicação
Antes de subir a aplicação é necessário construir a imagem docker de cada microsserviço a partir do seu Dockerfile disponibilizado nos diretórios.
Após gerar a imagem Docker dos microsserviços, execute o comando: 
```sh
docker compose up
```

