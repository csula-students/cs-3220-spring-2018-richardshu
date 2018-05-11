package edu.csula.storage.servlet;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import edu.csula.models.User;
import edu.csula.storage.UsersDAO;

/**
 * To abstract the storage access from the business layer using HttpSession
 */
public class UsersDAOImpl implements UsersDAO {
	private final HttpSession context;
	protected static final String CONTEXT_NAME = "users";

	public UsersDAOImpl(HttpSession context) {
		this.context = context;
	}

	@Override
	public boolean authenticate(String username, String password) {
		// TODO: check if username/password combination is valid and store the
		//       username/password into the session
		User user = new User(0, username, password);
		User admin = new User(0, "admin", "cs3220password");
		if (user.equals(admin)) {
			context.setAttribute(CONTEXT_NAME, user);
			return true;
		}
		return false;
	}

	@Override
	public Optional<User> getAuthenticatedUser() {
		// TODO: return the authenticated user if there is any
		Object data = context.getAttribute(CONTEXT_NAME);
		if (data == null) {
			return Optional.empty();
		}
		return Optional.of((User) data);
	}

	@Override
	public void logout() {
		// TODO: log user out using `invalidate`
		context.invalidate();
	}
}
