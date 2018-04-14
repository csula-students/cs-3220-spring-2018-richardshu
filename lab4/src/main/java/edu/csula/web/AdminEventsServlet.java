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

		// Add test data
		dao.add(new Event(1, "name", "desc", 10));
		dao.add(new Event(2, "name2", "desc2", 20));

		Collection<Event> events = dao.getAll();
		StringBuilder html = new StringBuilder();

		// Create the table header
		html.append(
			"<table>" +
				"<tr>" +
					"<th>ID</th>" +
					"<th>Name</th>" +
					"<th>Description</th>" +
					"<th>Trigger At</th>" +
				"</tr>"
		);

		// Add each event to the table
		for (Event e : events) {
			html.append(
				"<tr>" +
					"<td>" + e.getId() + "</td>" +
					"<td>" + e.getName() + "</td>" +
					"<td>" + e.getDescription() + "</td>" +
					"<td>" + e.getTriggerAt() + "</td>" +
				"</tr>"
			);
		}

		// Add the closing table tag
		html.append("</table");

		// Render the table
		out.println(html);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle upsert transaction
		
	}
}
