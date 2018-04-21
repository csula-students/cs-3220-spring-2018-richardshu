package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

@WebServlet("/admin/generators")
public class AdminGeneratorsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// TODO: render the generators page HTML
		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		Collection<Generator> generators = dao.getAll();

		// Connect the servlet with the JSP file
		request.setAttribute("generators", generators);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin-generators.jsp");
		dispatcher.forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		
	}
}
