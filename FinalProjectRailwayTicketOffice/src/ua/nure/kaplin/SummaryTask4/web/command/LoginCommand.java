package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.kaplin.SummaryTask4.exception.AppException;
import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoUser;
import ua.nure.kaplin.SummaryTask4.db.Role;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.db.entity.User;

import org.apache.log4j.Logger;

public class LoginCommand extends Command {

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		StringBuilder builder = null;
		MessageDigest md5 = null;
		HttpSession session = request.getSession();
		List<Route> routes = null;
		
		// obtain login and password from a request
		DaoUser dao = new DaoUser();
		String login = request.getParameter("login");
		LOG.trace("Request parameter: login --> " + login);
		String page = Path.PAGE_MAIN_REDIRECT;
		String password = request.getParameter("password");
		if (login == null || password == null || login.isEmpty() || password.isEmpty()) {
			page = Path.PAGE_ERROR;
			request.setAttribute("errorMessage",  "Login/password cannot be empty");
			throw new AppException("Login/password cannot be empty");
		}

		User user = null;
		try {
			user = dao.findUserByLogin(login);
		} catch (Exception e) {
			LOG.error("Insert user exception: ", e);
			e.printStackTrace();
		}
		LOG.trace("Found in DB: user --> " + user);

		try {
			md5 = MessageDigest.getInstance("MD5");
			byte[] bytes = md5.digest(password.getBytes());
			builder = new StringBuilder();
			for (byte b : bytes) {
				builder.append(String.format("%02X", b));
			}
			password = builder.toString();
		} catch (NoSuchAlgorithmException e) {
			LOG.error("Problem with password encryption: ", e);
		}
		
		
		if (user == null || !password.equals(user.getPassword())) {
			request.setAttribute("errorMessage",  "Cannot find user with such login/password");
			page = Path.PAGE_ERROR;
			throw new AppException("Cannot find user with such login/password");
		}

		Role userRole = Role.getRole(user);
		LOG.trace("userRole --> " + userRole);

		session.setAttribute("user", user);
		LOG.trace("Set the session attribute: user --> " + user);

		session.setAttribute("userRole", userRole);
		LOG.trace("Set the session attribute: userRole --> " + userRole);

		if (userRole == Role.CLIENT) {
			routes = new ArrayList<Route>();
			session.setAttribute("routesForBasket", routes);
		}

		LOG.info("User " + user + " logged as " + userRole.toString().toLowerCase());

		LOG.debug("Command finished");
		return page;
	}

}
