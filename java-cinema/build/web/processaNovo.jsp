<%@page import="java.util.ArrayList" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@page import="ifrn.modelo.Filmes" %>
<%@page import="ifrn.persistencia.FilmesDAO" %>
<%@page import="ifrn.persistencia.HorariosDAO" %>
<%@page import="ifrn.view.FormFilmes" %>

<%
    // Obtem dados do formulario
    FormFilmes formFilmes = new FormFilmes();
    formFilmes.setId(Integer.parseInt( request.getParameter("id").trim()) );
    formFilmes.setTitulo(request.getParameter("titulo").trim());
    formFilmes.setLink(request.getParameter("link").trim());

    // Mensagem de erro e proxima pagina
    String msgErro = formFilmes.validaDados(FormFilmes.INCLUSAO);
    String proximaPagina;

    // Monta Filmes com dados validos ou monta mensagens de erro
    if (msgErro.equals("")) {
        Filmes filmeNovo = new Filmes();
        filmeNovo.setId(formFilmes.getId());
        filmeNovo.setTitulo(formFilmes.getTitulo());
        
     
        FilmesDAO.getInstancia().grava(filmeNovo);
        request.removeAttribute("msgErro");
        request.removeAttribute("formFilmes");
        proximaPagina = "controleFilmes.jsp";
    } else {
        request.setAttribute("msgErro", msgErro);
        // TODO: CARREGA HORARIOS AQUI
        request.setAttribute("formFilmes", formFilmes);
        proximaPagina = "controleNovo.jsp";
    }

    RequestDispatcher rd = request.getRequestDispatcher(proximaPagina);
    rd.forward(request, response);

%>
