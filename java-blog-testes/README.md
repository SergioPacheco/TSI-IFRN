# Spring Boot Blog - Ferramentas de Testes

## About

Este é um projeto de testes ** Spring Boot + Thymeleaf + Junit **.
A ideia era construir uma plataforma básica de blogs.

Foi feito usando ** Spring Boot **, ** Spring Security **, ** Thymeleaf **, ** Spring Data JPA **, ** Spring Data REST ** e ** Docker **.
Banco de dados está na memória ** H2 **.

Há uma funcionalidade de login e registro incluída.

O usuário tem sua própria página de blog, onde ele pode adicionar novas postagens de blog.
Cada usuário autenticado pode comentar em postagens feitas por outros usuários.
Home page é uma lista paginada de todas as mensagens.
Usuários não autenticados podem ver todas as postagens do blog, mas não podem adicionar novas postagens ou comentários.

### Mapeamento 

|CONTROLLER 			|URL				|HTTP   |MÉTODO										|
|-----------------------|-------------------|-------|-------------------------------------------|
|homeController 		|/home				|GET	|home(Page Model)							|
|BlogErrorController 	|/error 			|GET	|error()									|	
|BlogErrorController	|/403				|GET 	|error403()									|
|LoginController		|/login 			|GET	|login(Principal)							|
|RegistrationController	|/registration		|GET 	|registration(Model)						|
|RegistrationController	|/registration		|POST 	|createNewUser(User, BindResult, Model)		|
|PostController			|/newPost			|GET	|newPost(Principal, Model)					|
|PostController			|/newPost			|POST	|createNewPost(Post, BindingResult)			|
|PostController			|/edit/Post/{id}	|GET	|editPostWithId(Id, Principal, Model)		|	
|PostController			|/post/{id}			|GET	|getPostWithId(Id, Principal, Model)		|
|PostController			|/post/{id}			|DELETE	|deletePostWithId(Id, Principal)			|	
|CommentController		|/createComment 	|POST	|createComment(Comment, BindResult)			|
|CommentController		|/commentPost/{id}	|GET 	|commentPostWithId(Id, Principal, Model)	|



## Configuração

### Arquivos de Configuração

Pasta ** src / resources / ** contém arquivos de configuração para ** blog ** Spring Boot application.

* ** src / resources / application.properties ** - arquivo de configuração principal. Aqui é possível alterar o nome de usuário / senha do administrador,
bem como alterar o número da porta.

## Como executar

Existem várias maneiras de executar o aplicativo. Você pode executá-lo a partir da linha de comando com o Maven Wrapper, Maven ou Docker.

Quando o aplicativo for iniciado, acesse o navegador da web e visite `http://localhost:8090/home`

Nome de usuário do administrador: ** admin **

Senha do administrador: ** admin **

Nome de usuário do usuário: ** usuário **

Senha do usuário: ** senha **

### Maven Wrapper

#### Using the Maven Plugin

Vá para a pasta raiz do aplicativo e digite:
```bash
$ chmod +x scripts/mvnw
$ scripts/mvnw spring-boot:run
```

#### Usando um JAR executável

Ou você pode construir o arquivo JAR com
```bash
$ scripts/mvnw clean package
``` 

Então você pode executar o arquivo JAR:
```bash
$ java -jar target/blog-0.0.1-SNAPSHOT.jar
```

### Maven

Open a terminal and run the following commands to ensure that you have valid versions of Java and Maven installed:

```bash
$ java -version
java version "1.8.0_102"
Java(TM) SE Runtime Environment (build 1.8.0_102-b14)
Java HotSpot(TM) 64-Bit Server VM (build 25.102-b14, mixed mode)
```

```bash
$ mvn -v
Apache Maven 3.3.9 (bb52d8502b132ec0a5a3f4c09453c07478323dc5; 2015-11-10T16:41:47+00:00)
Maven home: /usr/local/Cellar/maven/3.3.9/libexec
Java version: 1.8.0_102, vendor: Oracle Corporation
```

#### Usando o plugin Maven

O plug-in Spring Boot Maven inclui uma meta de execução que pode ser usada para compilar e executar rapidamente seu aplicativo.
Os aplicativos são executados de forma explodida, como fazem no seu IDE.
O exemplo a seguir mostra um comando típico do Maven para executar um aplicativo Spring Boot:
 
```bash
$ mvn spring-boot:run
``` 

#### Usando o JAR executável

Para execução de um JAR:

```bash
$ mvn clean package
``` 

Para executar esse aplicativo, use o comando java -jar, da seguinte maneira:

```bash
$ java -jar target/blog-0.0.1-SNAPSHOT.jar
```

Para sair do aplicativo, pressione ** ctrl-c **.

### Docker

É possível executar o ** blog ** usando o Docker:

Construir imagem do Docker:
```bash
$ mvn clean package
$ docker build -t blog:dev -f docker/Dockerfile .
```

Execute o contêiner do Docker:
```bash
$ docker run --rm -i -p 8090:8090 \
      --name blog \
      blog:dev
```

##### Script de ajuda

É possível executar todos os itens acima com script de ajuda:

```bash
$ chmod +x scripts/run_docker.sh
$ scripts/run_docker.sh
```

## Docker 

Pasta ** docker ** contém:

* ** docker / blog / Dockerfile ** - Arquivo de compilação do Docker para executar a imagem do Docker de demonstração de blogs.
Instruções para construir artefatos, copiar artefatos de compilação na imagem do docker e, em seguida, executar o aplicativo na porta apropriada com o arquivo de configuração adequado.

## Util Scripts

* ** scripts / run_docker.sh.sh ** - script util para executar o contêiner do Docker do blog usando o ** docker / Dockerfile **

## Tests

Os testes podem ser executados executando o seguinte comando a partir da raiz do projeto:

```bash
$ mvn test
```

## Ferramentas auxiliares

### HAL REST Browser

Vá para o navegador da web e visite `http://localhost:8090/`

Você precisará ser autenticado para poder ver esta página.

### Interface da Web do banco de dados H2

Vá para o navegador da web e visite `http://localhost:8090/h2-console`

No campo ** URL do JDBC **, coloque
```
jdbc:h2:mem:blog_simple_db
```

No arquivo `/src/main/resources/application.properties` é possível alterar
caminho da URL da interface da web, bem como o URL da fonte de dados.
