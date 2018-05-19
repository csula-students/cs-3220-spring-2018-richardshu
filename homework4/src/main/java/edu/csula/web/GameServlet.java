package edu.csula.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import edu.csula.storage.mysql.EventsDAOImpl;
import edu.csula.storage.EventsDAO;
import edu.csula.models.Event;

import edu.csula.storage.mysql.GeneratorsDAOImpl;
import edu.csula.storage.GeneratorsDAO;
import edu.csula.models.Generator;

import edu.csula.models.State;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.csula.storage.mysql.Database;

@WebServlet("/game")
public class GameServlet extends HttpServlet {

	public void init() {
		EventsDAO eventsDao = new EventsDAOImpl(new Database());
		Collection<Event> events = eventsDao.getAll();
		if (events.size() == 0) {
			eventsDao.add(new Event(0, "Clicker", "Clicker is now available", 10));
			eventsDao.add(new Event(1, "Farmer", "The farmers have arrived", 100));
			eventsDao.add(new Event(2, "Tractor", "Tractors for sale", 1000));
		}

		GeneratorsDAO generatorsDao = new GeneratorsDAOImpl(new Database());
		Collection<Generator> generators = generatorsDao.getAll();
		if (generators.size() == 0) {
			generatorsDao.add(new Generator(0, "Clicker", "Click click click! Harvest strawberries one click at a time", 5, 10, 10));
			generatorsDao.add(new Generator(1, "Farmer", "Old McDonald had a strawberry farm...", 10, 100, 100));
			generatorsDao.add(new Generator(2, "Tractor", "Vroom vroom...tractors coming through!", 20, 1000, 1000));
		}
	}

	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		EventsDAO eventsDao = new EventsDAOImpl(new Database());
		Collection<Event> events = eventsDao.getAll();

		GeneratorsDAO generatorsDao = new GeneratorsDAOImpl(new Database());
		Collection<Generator> generators = generatorsDao.getAll();

		// Send the state to the front-end as a JSON object
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		String state = gson.toJson(new State(events, generators));

		// Connect the servlet with the JSP file
		request.setAttribute("state", state);
		request.setAttribute("lastGeneratorIndex", generators.size() - 1);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/game.jsp");
		dispatcher.forward(request, response);
	}
}
