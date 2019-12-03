<%@page import="java.util.ArrayList" %>
<%@page import="ifrn.modelo.Filmes" %>
<%@page import="ifrn.persistencia.FilmesDAO" %>
<%@page import="ifrn.persistencia.HorariosDAO" %>
<%@page import="ifrn.view.FormFilmes" %>

<%

    String msgErro = (String) request.getAttribute("msgErro");

    // Acionamento novo pelo controle geral de filmes
    if (msgErro == null || msgErro.equals("")) {

        Filmes filmes = FilmesDAO.getInstancia().le(Integer.parseInt(request.getParameter("id")));
        FormFilmes formFilmes = new FormFilmes();
        formFilmes.setId(filmes.getId());
        formFilmes.setTitulo(filmes.getTitulo());
        formFilmes.setLink(filmes.getLink());

        request.setAttribute("formFilmes", formFilmes);

    }

    RequestDispatcher rd = request.getRequestDispatcher("editaFilmes.jsp");
    rd.forward(request, response);

%>
