package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import ifrn.modelo.Filmes;
import ifrn.persistencia.FilmesDAO;
import ifrn.persistencia.HorariosDAO;
import ifrn.view.FormFilmes;

public final class processaEdita_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

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


      out.write('\n');
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
