<%@page contentType="text/html" pageEncoding="utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html lang="pt-br">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CRUD com Bootstrap 3</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

        <!-- Modal -->
        <div class="modal fade" id="delete-modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title" id="modalLabel">Excluir Item</h4>
                    </div>
                    <div class="modal-body">
                        Deseja realmente excluir este Filme?
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary">Sim</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">N&atilde;o</button>
                    </div>
                </div>
            </div>
        </div>

    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Programação Web I</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#">Início</a></li>
                    <li><a href="#">Opções</a></li>
                    <li><a href="#">Perfil</a></li>
                    <li><a href="#">Ajuda</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div id="main" class="container-fluid">
        <h3 class="page-header">Visualizar Filme</h3>

        <div class="row">
            <div class="col-md-4">
                <p><strong>Id</strong></p>
                <p>${filmes.id}</p>
            </div>

            <div class="col-md-4">
                <p><strong>Título</strong></p>
                <p>${filmes.titulo}</p>
            </div>

            <div class="col-md-4">
                <p><strong>Link</strong></p>
                <p>${filmes.link}</p>
            </div>

        </div>

        <hr />
        <div id="actions" class="row">
            <div class="col-md-12">
                <a href="controleFilmes.jsp" class="btn btn-primary">Fechar</a>
                <a href="controleEdita.jsp?id=${filmes.id}" class="btn btn-default">Editar</a>
                <a href="controleApaga.jsp?id=${filmes.id}" class="btn btn-default" data-toggle="modal" data-target="#delete-modal">Excluir</a>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </body>
</html>
