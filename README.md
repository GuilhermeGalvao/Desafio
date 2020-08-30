# Desafio, Desenvolvedor Júnior

Desafio java proporcionado pelo Banco Inter.
  - Java 8
  - Spring Boot
  - Maven
  - Lombok
  - Spring Boot JPA
  - Swagger
  - H2

# Premissas

  - A solução foi desenvolvida, baseada em uma premissa  na qual cada usuário pode ver apenas os própios dados através da criptografia com sua chave pública através do método RSA.
  - Uso de UUID como chave única de usuário, como garantia de segurança de dados, no intuito de impedir a adição ou atualização de chave pública por usuários terceiros.
  - Utilização de lista circular como implementação da cache.


# Como dar Build e rodar
Este projeto contém uma versão minimizada do maven. Todas as dependencias serão baixadas através do mesmo.
Executar o "maven Build":
Nesta fase serão executados todos os testes para uma pré inicialização.
```sh
$  ./mvnw clean install
```
Após a mensagem "BUILD SUCCESS", a aplicação estará pronta para rodar:
    
```sh
$ ./mvnw spring-boot:run
```

# API
CRUD completo de usuário através da porta 8080
 - POST /user/addNew com um corpo apresentando um novo usuário a ser inserido.
 - GET /user/get/Id com um parametro para requisição de um usuário.
 - PUT /user/update/Id com um parametro do id de um usuário existente e um corpo contendo a atualização do usuário.
 - DELETE /user/delete/Id com um parametro de id de um usuário existente para remoção.
 - PUT /user/insertPublicKey/Id com um parametro de id de um usuário existente e um corpo contendo uma string texto da chave pública.
 
Dígito único
 - GET /uniqueType/save/n&k&id com os parametros de um numero n, numero de repetições k e opcionalmente o id do usuário que será ligado à função e retornando o valor do dígito único para a operação.
 - GET /uniqueType/get/Id com parametro de id pertencente a um usuário do sistema, para retorno de todos os dígitos únicos já calculados pelo mesmo.
    
# Swagger
Para encontrar a documentação da api através do swagger:
```sh
http://localhost:8080/swagger-ui.html#/
```

# H2
Para acesso ao banco de dados em memória:
Com usuário: sa e senha: aaa
```sh
http://localhost:8080/h2-console/
```
    

 
   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
