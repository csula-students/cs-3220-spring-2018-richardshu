package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

import edu.csula.storage.EventsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Event;

public class EventsDAOImpl implements EventsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepared statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "";
	protected static final String getByIdQuery = "";
	protected static final String setQuery = "";
	protected static final String addQuery = "";
	protected static final String removeQuery = "";

	public EventsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Event> getAll() {
		// TODO: get all events from jdbc
		List<Event> events = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery("SELECT * FROM Events;");
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				int triggerAt = rs.getInt(4);
				events.add(new Event(id, name, description, triggerAt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
		return events;
	}

	@Override
	public Optional<Event> getById(int id) {
		// TODO: get specific event by id
		return Optional.empty();
	}

	@Override
	public void set(int id, Event event) {
		// TODO: update specific event by id
	}

	@Override
	public void add(Event event) {
		// TODO: implement jdbc logic to add a new event
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove event by id
	}
}
