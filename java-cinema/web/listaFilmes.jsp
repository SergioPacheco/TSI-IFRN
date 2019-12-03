<%@page contentType="text/html" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Lista Filmes</title>

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

        <div id="main" class="container-fluid" style="margin-top: 50px">

            <div id="top" class="row">
                <div class="col-sm-3">
                    <h2>FILMES</h2>
                </div>
                <div class="col-sm-6">

                    <div class="input-group h2">
                        <input name="data[search]" class="form-control" id="search" type="text" placeholder="Pesquisar Itens">
                        <span class="input-group-btn">
                            <button class="btn btn-primary" type="submit">
                                <span class="glyphicon glyphicon-search"></span>
                            </button>
                        </span>
                    </div>

                </div>
                <div class="col-sm-3">
                    <a href="controleNovo.jsp" class="btn btn-primary pull-right h2">Novo Filme</a>
                </div>
            </div> <!-- /#top -->


            <hr />
            <div id="list" class="row">

                <div class="table-responsive col-md-12">
                    <table class="table table-striped" cellspacing="0" cellpadding="0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Titulo</th>
                                <th>Link</th>
                                <th class="actions">Ações</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${filmes}" var="filmes"> 
                                <tr>
                                    <td>${filmes.id}</td>
                                    <td>${filmes.titulo}</td>
                                    <td>${filmes.link}</td>
                                    <td class="actions">
                                        <a class="btn btn-success btn-xs" href="controleMostra.jsp?id=${filmes.id}">Visualizar</a>
                                        <a class="btn btn-warning btn-xs" href="controleEdita.jsp?id=${filmes.id}">Editar</a>
                                        <a class="btn btn-danger btn-xs"  href="controleApaga.jsp?id=${filmes.id}">Excluir</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>

            </div> <!-- /#list -->

            <div id="bottom" class="row">
                <div class="col-md-12">
                    <ul class="pagination">
                        <li class="disabled"><a>&lt; Anterior</a></li>
                        <li class="disabled"><a>1</a></li>
                        <li><a href="#">2</a></li>
                        <li class="next"><a href="#" rel="next">Próximo &gt;</a></li>
                    </ul><!-- /.pagination -->
                </div>
            </div> <!-- /#bottom -->
        </div> <!-- /#main -->

        
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>