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

@WebServlet("/admin/events")
public class AdminEventsServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		// TODO: render the events page HTML
		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();
		StringBuilder html = new StringBuilder();

		// Add the page header
		html.append("<h1 id='title' class='text-center'>Incremental Game Framework</h1>");
		html.append("<nav class='text-center'>");
		html.append("	<ul>");
		html.append("		<li><a href='#'><h3>Game Information</h3></a></li>");
		html.append("		<li><a href='#'><h3>Generators</h3></a></li>");
		html.append("		<li><a href='#''><h3>Events</h3></a></li>");
		html.append("	</ul>");
		html.append("</nav>");
		html.append("<form method='POST'>");
		html.append("	<label for='event_name'></label>");
		html.append("	<input name='event_name' class='input-box left-margin' placeholder='Enter the event name:' required><br/>");
		html.append("	<label for='event_description'></label>");
		html.append("	<input name='event_description' class='input-box left-margin' placeholder='Enter the event description:' required><br/>");
		html.append("	<label for='trigger'></label>");
		html.append("	<input name='trigger' class='input-box left-margin' placeholder='Trigger at:' required><br/>");
		html.append("	<button class='btn'><h4>Add</h4></button>");
		html.append("</form>");

		// Create the table header
		html.append("<table>");
		html.append("	<tr>");
		html.append("		<th>ID</th>");
		html.append("		<th>Name</th>");
		html.append("		<th>Description</th>");
		html.append("		<th>Trigger At</th>");
		html.append("	</tr>");

		// Add each event to the table
		for (Event e : events) {
			html.append("<tr>");
			html.append("	<td>" + e.getId() + "</td>");
			html.append("	<td>" + e.getName() + "</td>");
			html.append("	<td>" + e.getDescription() + "</td>");
			html.append("	<td>" + e.getTriggerAt() + "</td>");
			html.append("</tr>");
		}

		// Add the closing table tag
		html.append("</table");

		// Render the table
		out.println(html);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction

		String name = request.getParameter("event_name");
		String description = request.getParameter("event_description");
		int triggerAt = Integer.parseInt(request.getParameter("trigger"));

		EventsDAO dao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = dao.getAll();

		dao.add(new Event(events.size(), name, description, triggerAt));

		response.sendRedirect("../admin/events");
	}
}
