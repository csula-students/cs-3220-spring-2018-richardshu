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

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;

@WebServlet("/admin/generators/edit")
public class AdminEditGeneratorServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		UsersDAO userDao = new UsersDAOImpl(request.getSession());

		// If the user is not logged in, redirect to the login page
		if (!userDao.getAuthenticatedUser().isPresent()) {
			response.sendRedirect("../../admin/auth");
		}

		// TODO: render the events page HTML
		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());

		// Connect the servlet with the JSP file
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin-generators-edit.jsp");
		dispatcher.forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab the generator ID from the URL
		int id = Integer.parseInt(request.getParameter("id"));
		
		// Grab the generator information from the form
		String name = request.getParameter("generator_name");
		String description = request.getParameter("description");
		int rate = Integer.parseInt(request.getParameter("generator_rate"));
		int baseCost = Integer.parseInt(request.getParameter("base_cost"));
		int unlockAt = Integer.parseInt(request.getParameter("unlock_at"));

		// Replace the old generator with the new generator
		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		Collection<Generator> generators = dao.getAll();
		dao.set(id, new Generator(generators.size(), name, description, rate, baseCost, unlockAt));

		// Send the user back to the events page
		response.sendRedirect("../../admin/generators");
	}
}
