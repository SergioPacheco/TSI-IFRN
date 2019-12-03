package Servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.mail.EmailException;

import Modelo.Email;

@WebServlet("/emailServlet")
public class EmailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		
		
		String cep = req.getParameter("cep");
		String tipoDeLogradouro = req.getParameter("tipoDeLogradouro");
		String logradouro = req.getParameter("logradouro");
		String bairro = req.getParameter("bairro");
		String cidade = req.getParameter("cidade");
		String estado = req.getParameter("estado");
		
		String emailDestino = req.getParameter("emailDestino");
		String nomeDestino = req.getParameter("nomeDestino");

		try {

	        Email email = new Email();
	        
	        email.setHostName("smtp.googlemail.com");
	        email.setPorta(465);
	        email.setAssunto("Envio de Email Usando Servlet JSON");
	        email.setEmailDestino(emailDestino);
	        email.setNomeDestino(nomeDestino);
	        email.setEmailOrigem("joaorobertof@gmail.com");
	        email.setNomeOrigem("João Roberto");
	        String msg = "<html><p>"+"<h3>Retorno Cep: "+cep+"</h3>"+"<p>Tipo Logradouro: "+tipoDeLogradouro+"</p>"+"<p>Logradouro: "+logradouro+"</p>"+"<p>Bairro: "+bairro+"</p>"+"<p>Cidade: "+cidade+"</p>"+"<p>Estado: "+estado+"</p>"+"</p></html>";
	        email.setMsg(msg);
            email.setUserName("joaorobertof@gmail.com");
            email.setPassword("swUxa5u-ud");
	        try {
	            email.CriarEmail(true);
	            
	        } catch (EmailException ex) {
	            Logger.getLogger(EmailServlet.class.getName()).log(Level.SEVERE, null, ex);
	        }


		} catch (Exception ex) {
			Logger.getLogger(EmailServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

		RequestDispatcher Dispatcher = req.getRequestDispatcher("index.jsp");
		Dispatcher.forward(req, resp);

	}

}
