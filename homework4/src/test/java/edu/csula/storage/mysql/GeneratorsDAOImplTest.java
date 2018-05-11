package edu.csula.storage.mysql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import edu.csula.models.Generator;
import edu.csula.storage.Database;
import edu.csula.storage.GeneratorsDAO;

import org.junit.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GeneratorsDAOImplTest {
	private Database context;
	private GeneratorsDAO dao;

	@Test
	public void getAll() throws Exception {
		ResultSet getAllResultSet = mock(ResultSet.class);
		when(getAllResultSet.next()).thenReturn(true).thenReturn(false);
		when(getAllResultSet.getInt(1)).thenReturn(1);
		when(getAllResultSet.getString(2)).thenReturn("Grandma");
		when(getAllResultSet.getString(3)).thenReturn("desc");
		when(getAllResultSet.getInt(4)).thenReturn(5);
		when(getAllResultSet.getInt(5)).thenReturn(10);
		when(getAllResultSet.getInt(6)).thenReturn(10);

		Statement getAllStatement = mock(Statement.class); 
		when(getAllStatement.executeQuery(GeneratorsDAOImpl.getAllQuery)).thenReturn(getAllResultSet);

		Connection getAllConnection = mock(Connection.class);
		when(getAllConnection.createStatement()).thenReturn(getAllStatement);

		context = mock(Database.class);
		when(context.getConnection()).thenReturn(getAllConnection);

		dao = new GeneratorsDAOImpl(context);

		// set up initial mock state
		Collection<Generator> mockGenerators = new ArrayList<>();
		mockGenerators.add(new Generator(1, "Grandma", "desc", 5, 10, 10));

		// actual method execution
		Collection<Generator> actualGenerators = dao.getAll();

		// assert and verify
		assertEquals(1, actualGenerators.size());
	}

	@Test
	public void getById() throws Exception {
		ResultSet resultSet = mock(ResultSet.class);
		when(resultSet.next()).thenReturn(true).thenReturn(false);
		when(resultSet.getInt(1)).thenReturn(1);
		when(resultSet.getString(2)).thenReturn("Grandma");
		when(resultSet.getString(3)).thenReturn("desc");
		when(resultSet.getInt(4)).thenReturn(5);
		when(resultSet.getInt(5)).thenReturn(10);
		when(resultSet.getInt(6)).thenReturn(10);

		PreparedStatement getAllStatement = mock(PreparedStatement.class); 
		when(getAllStatement.executeQuery()).thenReturn(resultSet);

		Connection getAllConnection = mock(Connection.class);
		when(getAllConnection.prepareStatement(GeneratorsDAOImpl.addQuery)).thenReturn(getAllStatement);

		context = mock(Database.class);
		when(context.getConnection()).thenReturn(getAllConnection);

		dao = new GeneratorsDAOImpl(context);

		// actual method execution
		Optional<Generator> actualGenerator = dao.getById(1);

		// assert and verify
		assertTrue(actualGenerator.isPresent());
		assertEquals(actualGenerator.get(), new Generator(1, "Grandma", "desc", 5, 10, 10));
	}

	@Test
	public void set() throws Exception {
		PreparedStatement statement = mock(PreparedStatement.class);
		doNothing().when(statement).setString(anyInt(), anyString());
		doNothing().when(statement).setInt(anyInt(), anyInt());
		when(statement.executeUpdate()).thenReturn(1);

		Connection getAllConnection = mock(Connection.class);
		when(getAllConnection.prepareStatement(GeneratorsDAOImpl.setQuery)).thenReturn(statement);

		context = mock(Database.class);
		when(context.getConnection()).thenReturn(getAllConnection);

		dao = new GeneratorsDAOImpl(context);

		// actual execution
		dao.set(1, new Generator(1, "Grandma", "desc", 5, 10, 10));

		// verify
		verify(getAllConnection, times(1)).prepareStatement(GeneratorsDAOImpl.setQuery);
		verify(statement, times(1)).executeUpdate();
	}

	@Test
	public void add() throws Exception {
		PreparedStatement addStatement = mock(PreparedStatement.class);
		doNothing().when(addStatement).setString(anyInt(), anyString());
		doNothing().when(addStatement).setInt(anyInt(), anyInt());
		when(addStatement.executeUpdate()).thenReturn(1);

		Connection getAllConnection = mock(Connection.class);
		when(getAllConnection.prepareStatement(GeneratorsDAOImpl.addQuery)).thenReturn(addStatement);

		context = mock(Database.class);
		when(context.getConnection()).thenReturn(getAllConnection);

		dao = new GeneratorsDAOImpl(context);

		// actual execution
		dao.add(new Generator(1, "Grandma", "desc", 5, 10, 10));

		// verify
		verify(getAllConnection, times(1)).prepareStatement(GeneratorsDAOImpl.addQuery);
		verify(addStatement, times(1)).executeUpdate();
	}
}
