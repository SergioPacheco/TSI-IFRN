<%@page import="java.util.ArrayList" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="ifrn.modelo.Filmes" %>
<%@page import="ifrn.persistencia.FilmesDAO" %>
<%@page import="ifrn.persistencia.HorariosDAO" %>
<%@page import="ifrn.view.FormFilmes" %>

<%
    // Obtem dados do formulario
    System.out.println("1");
    FormFilmes formFilmes = new FormFilmes();
    System.out.println("2");
    int i = Integer.parseInt(request.getParameter("id").trim());
    System.out.println("3");
    formFilmes.setId(i);
    System.out.println("4");
    formFilmes.setTitulo(request.getParameter("titulo").trim());
    System.out.println("5");
    formFilmes.setLink(request.getParameter("link").trim());

    // Mensagem de erro e proxima pagina
    String msgErro = formFilmes.validaDados(FormFilmes.ALTERACAO);
    String proximaPagina;

    // Monta Filme com dados validos ou monta mensagens de erro
    if (msgErro.equals("")) {
        System.out.println("erro");
        Filmes filme = new Filmes();
        filme.setId(formFilmes.getId());
        filme.setTitulo(formFilmes.getTitulo());
        filme.setLink(formFilmes.getLink());

        FilmesDAO.getInstancia().altera(filme);
        request.removeAttribute("msgErro");
        request.removeAttribute("formFilmes");
        proximaPagina = "controleFilmes.jsp";
    } else {
        System.out.println("Sem erro");
        request.setAttribute("msgErro", msgErro);
        request.setAttribute("formFilmes", formFilmes);
        proximaPagina = "controleEdita.jsp";
    }

    RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
    rd.forward(request, response);

%>
