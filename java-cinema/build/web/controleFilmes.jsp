<%@page import="java.util.List" %>
<%@page import="ifrn.modelo.Filmes" %>
<%@page import="ifrn.persistencia.FilmesDAO" %>

<%
    List<Filmes> filmes = FilmesDAO.getInstancia().leTodos();
    request.setAttribute("filmes", filmes);
    RequestDispatcher rd = request.getRequestDispatcher("listaFilmes.jsp");
    rd.forward(request, response);
%>
