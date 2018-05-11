package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;

public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "";
	protected static final String getByIdQuery = "";
	protected static final String setQuery = "";
	protected static final String addQuery = "";
	protected static final String removeQuery = "";

	public GeneratorsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get all generators from jdbc
		return new ArrayList<>();
	}

	@Override
	public Optional<Generator> getById(int id) {
		// TODO: get specific generator by id
		return Optional.empty();
	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: update specific generator by id
	}

	@Override
	public void add(Generator generator) {
		// TODO: implement jdbc logic to add a new generator
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove generator by id
	}
}
