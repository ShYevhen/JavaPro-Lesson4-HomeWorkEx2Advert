package net.ukr.shyevhen;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UsersList usrList = new UsersList();

	@Override
	public void init() throws ServletException {
		usrList.addUser("admin", "admin");
		usrList.addUser("alladin", "admin2");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String login = req.getParameter("login");
		String password = req.getParameter("password");
		if (usrList.checkUser(login, password)) {
			HttpSession session = req.getSession(true);
			session.setAttribute("user_login", login);
			resp.sendRedirect("adverts");
		} else {
			resp.sendRedirect("registration.html");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String a = req.getParameter("a");
		HttpSession session = req.getSession(false);
		if (a.equals("exit") && session != null) {
			session.removeAttribute("user_login");
		}
		resp.sendRedirect("index.html");
	}

}
