package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.*;

import edu.csula.storage.GeneratorsDAO;
import edu.csula.storage.Database;
import edu.csula.models.Generator;

public class GeneratorsDAOImpl implements GeneratorsDAO {
	private final Database context;

	// TODO: fill the Strings with the SQL queries as "prepated statements" and
	//       use these queries variable accordingly in the method below
	protected static final String getAllQuery = "SELECT * FROM Generators;";
	protected static final String getByIdQuery = "SELECT * FROM Generators WHERE id = (?)";
	protected static final String setQuery = "UPDATE Generators SET name = (?), description = (?), rate = (?), base_cost = (?), unlock_at = (?) WHERE id = (?)";
	protected static final String addQuery = "INSERT INTO Generators (name, description, rate, base_cost, unlock_at) VALUES (?, ?, ?, ?, ?, ?)";
	protected static final String removeQuery = "DELETE FROM Generators WHERE id = (?)";

	public GeneratorsDAOImpl(Database context) {
		this.context = context;
	}

	@Override
	public List<Generator> getAll() {
		// TODO: get all generators from jdbc
		List<Generator> generators = new ArrayList<>();
		try (Connection c = context.getConnection(); Statement stmt = c.createStatement()) {
			ResultSet rs = stmt.executeQuery(getAllQuery);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String description = rs.getString(3);
				int rate = rs.getInt(4);
				int baseCost = rs.getInt(5);
				int unlockAt = rs.getInt(6);
				generators.add(new Generator(id, name, description, rate, baseCost, unlockAt));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return generators;
	}

	@Override
	public Optional<Generator> getById(int id) {
		// TODO: get specific generator by id
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(getByIdQuery)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				if (rs.getInt(1) == id) {
					String name = rs.getString(2);
					String description = rs.getString(3);
					int rate = rs.getInt(4);
					int baseCost = rs.getInt(5);
					int unlockAt = rs.getInt(6);
					return Optional.of(new Generator(id, name, description, rate, baseCost, unlockAt));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void set(int id, Generator generator) {
		// TODO: update specific generator by id
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(setQuery)) {
			stmt.setString(1, generator.getName());
			stmt.setString(2, generator.getDescription());
			stmt.setInt(3, generator.getRate());
			stmt.setInt(4, generator.getBaseCost());
			stmt.setInt(5, generator.getUnlockAt());
			stmt.setInt(6, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void add(Generator generator) {
		// TODO: implement jdbc logic to add a new generator
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(addQuery)) {
			stmt.setString(2, generator.getName());
			stmt.setString(3, generator.getDescription());
			stmt.setInt(4, generator.getRate());
			stmt.setInt(5, generator.getBaseCost());
			stmt.setInt(6, generator.getUnlockAt());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(int id) {
		// TODO: implement jdbc logic to remove generator by id
		try (Connection c = context.getConnection(); PreparedStatement stmt = c.prepareStatement(removeQuery)) {
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
