<%@page import="java.util.ArrayList" %>
<%@page import="ifrn.modelo.Filmes" %>
<%@page import="ifrn.persistencia.FilmesDAO" %>

<%

        int id = Integer.parseInt(request.getParameter("id"));
        int ret = FilmesDAO.getInstancia().apaga(id);
        System.out.println("Apagando filme id="+id);
        
        RequestDispatcher rd = request.getRequestDispatcher("controleFilmes.jsp");
        rd.forward(request, response);

%>
