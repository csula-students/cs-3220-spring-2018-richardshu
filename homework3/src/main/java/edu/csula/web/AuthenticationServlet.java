package edu.csula.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

import edu.csula.storage.servlet.UsersDAOImpl;
import edu.csula.storage.UsersDAO;
import edu.csula.models.User;

@WebServlet("/admin/auth")
public class AuthenticationServlet extends HttpServlet {
	@Override
	public void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		// This is necessary for the log out feature to work
		doDelete(request, response);

		// Connect the servlet with the JSP file
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/user-authentication.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO: handle login
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		UsersDAO dao = new UsersDAOImpl(request.getSession());
		if (dao.authenticate(username, password)) {
			response.sendRedirect("../admin/events");
		}
		else {
			// The user entered the incorrect login and password
			response.sendRedirect("../admin/auth");
		}
	}

    @Override
    public void doDelete( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO: handle logout
        UsersDAO dao = new UsersDAOImpl(request.getSession());
        dao.logout();
    }
}
