package net.ukr.shyevhen;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registration")
public class RegisterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UsersList usrList = new UsersList();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		RequestDispatcher disp;
		if (usrList.addUser(login, password)) {
			disp = getServletContext().getRequestDispatcher("/login");
		} else {
			disp = getServletContext().getRequestDispatcher("/registration.html");
		}
		disp.forward(req, resp);
	}

}
