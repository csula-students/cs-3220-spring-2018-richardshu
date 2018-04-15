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

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events/edit")
public class AdminEditEventServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());

		// Connect the servlet with the JSP file
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/admin-events-edit.jsp");
		dispatcher.forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		int id = Integer.parseInt(request.getParameter("id")); // Grab the id from the URL
		String name = request.getParameter("event_name");
		String description = request.getParameter("event_description");
		int triggerAt = Integer.parseInt(request.getParameter("trigger"));

		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();

		// Replace the old event with the new event
		dao.set(id, new Event(id, name, description, triggerAt));

		// Send the user back to the events page
		response.sendRedirect("../../admin/events");
	}
}
