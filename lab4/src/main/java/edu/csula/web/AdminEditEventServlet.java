package edu.csula.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.csula.storage.servlet.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

@WebServlet("/admin/events/edit")
public class AdminEditEventServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();

		// Form for editing the events
		StringBuilder html = new StringBuilder();
		html.append("<form method='POST'>");
		html.append("	<label for='event_name'></label>");
		html.append("	<input name='event_name' class='input-box left-margin' placeholder='Enter the event name:' required><br/>");
		html.append("	<label for='event_description'></label>");
		html.append("	<input name='event_description' class='input-box left-margin' placeholder='Enter the event description:' required><br/>");
		html.append("	<label for='trigger'></label>");
		html.append("	<input name='trigger' class='input-box left-margin' placeholder='Trigger at:' required><br/>");
		html.append("	<button class='btn'><h4><a>Edit</a></h4></button>");
		html.append("</form>");

		// Render the table
		out.println(html);
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
