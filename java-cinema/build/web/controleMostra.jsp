<%@page import="java.util.ArrayList" %>
<%@page import="ifrn.modelo.Filmes" %>
<%@page import="ifrn.persistencia.FilmesDAO" %>

<%

    Filmes filmes = FilmesDAO.getInstancia().le(Integer.parseInt(request.getParameter("id")));
    request.setAttribute("filmes", filmes);
    RequestDispatcher rd = request.getRequestDispatcher("mostraFilmes.jsp");
    rd.forward(request, response);

%>
