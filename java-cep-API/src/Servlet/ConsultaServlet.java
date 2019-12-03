/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

import Controller.ConsultaController;

/**
 *
 * @author joao
 */
@WebServlet("/consultaServlet")
public class ConsultaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");

		String codigo = req.getParameter("cep");
		ConsultaController consulta = new ConsultaController();

		try {

			req.setAttribute("RetornoCep", consulta.consulta(codigo));

		} catch (Exception ex) {
			Logger.getLogger(ConsultaServlet.class.getName()).log(Level.SEVERE, null, ex);
		}

		RequestDispatcher Dispatcher = req.getRequestDispatcher("consultaRetorno.jsp");
		Dispatcher.forward(req, resp);

	}

}
