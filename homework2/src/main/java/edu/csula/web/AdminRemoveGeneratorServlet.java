package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

@WebServlet("/admin/generators/remove")
public class AdminRemoveGeneratorServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		GeneratorsDAO dao = new GeneratorsDAOImpl(getServletContext());
		Collection<Generator> generators = dao.getAll();

		// Grab the generator id from the URL
		int id = Integer.parseInt(request.getParameter("id"));
		dao.remove(id);

		// Decrease the id of everything after the deleted event by 1
		for (Generator g : generators) {
			if (g.getId() >= id) {
				g.setId(g.getId() - 1);
			}
		}

		// Send the user back to the generators page
		response.sendRedirect("../../admin/generators");
	}
}
