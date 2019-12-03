<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
    <head>

        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cinema - Adiciona Filme</title>

        <link href="css/bootstrap.min.css" rel="stylesheet">
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>

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

        <h3 class="page-header">Adicionar Filme</h3>

        <form action="processaNovo.jsp">
            <div class="row">
                <div class="form-group col-md-4">
                    <label for="id">Id</label>
                    <input type="text" class="form-control" id="id" value="${formFilmes.id}">
                </div>
                <div class="form-group col-md-4">
                    <label for="id">Titulo</label>
                    <input type="text" class="form-control" id="titulo" value="${formFilmes.titulo}">
                </div>
                <div class="form-group col-md-4">
                    <label for="id">Link</label>
                    <input type="text" class="form-control" id="link" value="${formFilmes.link}">
                </div>

            </div>
            <div class="row"> 
                <p style="font-weight:bold;">Mensagens:</p>
                <p style="color:red;">${msgErro}</p>
            </div>
            <hr />

            <div class="row">
                <div class="col-md-12">
                    <button type="submit" class="btn btn-primary">Salvar</button>
                    <a href="controleFilmes.jsp" class="btn btn-default">Cancelar</a>
                </div>
            </div>

        </form>
    </div>


    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>
