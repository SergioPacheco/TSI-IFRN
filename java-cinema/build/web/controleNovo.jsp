<%@page import="java.util.ArrayList" %>
<%@page import="ifrn.view.FormFilmes" %>
<%@page import="ifrn.persistencia.FilmesDAO" %>
<%@page import="ifrn.persistencia.HorariosDAO" %>

<%

        String msgErro = (String) request.getAttribute("msgErro");

        // Acionamento novo pelo controle geral de filmes
        if (msgErro == null || msgErro.equals("")) {

            FormFilmes formFilmes = new FormFilmes();
            // TODO: CARREGA TODOS OS HORARIOS
            request.setAttribute("formFilmes", formFilmes);
        }

        RequestDispatcher rd = request.getRequestDispatcher("novoFilmes.jsp");

        rd.forward(request, response);

%>
