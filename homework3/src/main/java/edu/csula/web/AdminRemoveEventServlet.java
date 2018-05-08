package edu.csula.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;

@WebServlet("/admin/events/remove")
public class AdminRemoveEventServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		UsersDAO userDao = new UsersDAOImpl(request.getSession());

		// If the user is not logged in, redirect to the login page
		if (!userDao.getAuthenticatedUser().isPresent()) {
			response.sendRedirect("../../admin/auth");
		}

		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();

		// Grab the id from the URL
		int id = Integer.parseInt(request.getParameter("id"));
		dao.remove(id);

		// Decrease the id of everything after the deleted event by 1
		for (Event e : events) {
			if (e.getId() >= id) {
				e.setId(e.getId() - 1);
			}
		}

		// Send the user back to the events page
		response.sendRedirect("../../admin/events");
	}
}
