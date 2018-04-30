package edu.csula.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		UsersDAO userDao = new UsersDAOImpl(request.getSession());

		// If the user is not logged in, redirect to the login page
		if (!userDao.getAuthenticatedUser().isPresent()) {
			response.sendRedirect("../admin/auth");
		}

		// Render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();

		// Connect the servlet with the JSP file
		request.setAttribute("events", events);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin-events.jsp");
		dispatcher.forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Grab the event information from the form
		String name = request.getParameter("event_name");
		String description = request.getParameter("event_description");
		int triggerAt = Integer.parseInt(request.getParameter("trigger"));

		// Add the new generator and store it in the server
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		dao.add(new Event(events.size(), name, description, triggerAt));

		// Stay on the events page after the form is submitted
		response.sendRedirect("../admin/events");
	}
}
