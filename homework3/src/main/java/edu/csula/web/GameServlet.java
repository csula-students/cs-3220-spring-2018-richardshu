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

import edu.csula.storage.servlet.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.models.State;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@WebServlet("/game")
public class GameServlet extends HttpServlet {
	
	public void init() {
		System.out.println("Hello world");
	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// Get the events
		EventsDAO eventsDao = new EventsDAOImpl(getServletContext());
		Collection<Event> events = eventsDao.getAll();

		// Get the generators
		GeneratorsDAO generatorsDao = new GeneratorsDAOImpl(getServletContext());
		Collection<Generator> generators = generatorsDao.getAll();

		// Send the state to the front-end as a JSON object
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String state = gson.toJson(new State(events, generators));
		System.out.println(state);

		// Connect the servlet with the JSP file
		request.setAttribute("state", state);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/game.jsp");
		dispatcher.forward(request, response);
	}


	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Do nothing
	}
}
