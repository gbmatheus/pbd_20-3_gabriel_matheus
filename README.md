# Projeto de Banco de Dados - Eniatus

## ReadMe em desenvolvimento.

Projeto desenvolvido em Java com o framework Spring Boot.

### Documentation

A pasta documentation era conter toda a documentação relacionada ao projetos. O arquivo "der" será possível abrir pela https://draw.io.

## Usuário Cadastrados

São criando assim que a api é inicilizada, se não houve o cadrastro de nenhum usuário

### usuario: admin, senha: admin

### usuario: contador, senha: contador

### Postman

O postman é uma ferramenta que permite simular requisições. Nas pasta docomentation/postman, tem um arquivo .json, nele tem todos os endpoits e objetos das requisições simuladas. Basta importa o arquivo na ferramenta.

### SQL

A pasta SQL, contém os scripts do banco e o backup.

### Endpoints

A base da url(http://localhost:8080/api), por padrão está configurada para roda na porta 8080
base_url + endpoint + conteudo_do_metodo

### Lista com endpoits e métodos http(post, get, update, delete).

Lista de endpoints pesonalizados

- /login - {post:''} -> fazer login
- /conta/3/alterar-senha - {post:''} -> alterar senha do usuário
- /folha-de-pagamento/funcionario/:id - {post:''} -> criar folha de pagamento do usuário
- /folha-de-pagamento/funcionario/:id - {post:''} -> criar folha de pagamento do usuário
- /folha-de-pagamento/funcionario/:id - {post:''} -> criar folha de pagamento do usuário
- /folha-de-pagamento/:id/outros-acrescimos {post: ''} -> adicionar acrescimo para aquela folha
- /folha-de-pagamento/:id/outros-descontos - {post: ''}-> adicionar descontos para aquela folha

Lista de endpoints padrões

- /usuarios - {post:'', get:'', get:'', update:'', delete:''}
- /funcionario - {post:'', get:'', get:'', update:'', delete:''}
- /inss - {post:'', get:'', get:'', update:'', delete:''}
- /irrf - {post:'', get:'', get:'', update:'', delete:''}
- /salario-minimo - {post:'', get:'', get:'', update:'', delete:''}
- /salario-familia - {post:'', get:'', get:'', update:'', delete:''}
- /usuarios - {post:'', get:'', get:'', update:'', delete:''}
