package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoUserImpl;
import ua.nure.kaplin.SummaryTask4.db.entity.User;
import ua.nure.kaplin.SummaryTask4.exception.AppException;
import ua.nure.kaplin.SummaryTask4.validator.FieldsValidator;

public class RegistrationCommand extends Command {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");
		String page = Path.PAGE_ERROR;
		request.setAttribute("errorMessage", "Passwords do not match");
		User user = null;
		DaoUserImpl dao = null;
		StringBuilder builder = null;
		MessageDigest md5 = null;
		FieldsValidator validator= null;
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String surname = request.getParameter("surname");
		String name = request.getParameter("name");

		if (password.equals(passwordConfirm)) {
			if(password.length() <6) {
				throw new AppException("password_less_than_6_characters");
			}
			validator = new FieldsValidator();
			if(!validator.validateFieldEmail(email)) {
				throw new AppException("invalid_email");
			}
			user = new User();
			dao = new DaoUserImpl();
			user.setLogin(login);
			user.setEmail(email);
			user.setLastName(surname);
			user.setFirstName(name);
			user.setRoleId(1);

			try {
				md5 = MessageDigest.getInstance("MD5");
				byte[] bytes = md5.digest(password.getBytes());
				builder = new StringBuilder();
				for (byte b : bytes) {
					builder.append(String.format("%02X", b));
				}
				user.setPassword(builder.toString());

			} catch (NoSuchAlgorithmException e1) {
				request.setAttribute("errorMessage", "Can not create user, contact with the administrator");
				LOG.trace("Set the request attribute: errorMessage --> "
						+ "Can not create user, contact with the administrator");
				LOG.error("Problem with password encryption: ", e1);
			}

			try {
				dao.insertUser(user);
				LOG.trace("Insert user in DB: user --> " + user);
				page = Path.PAGE_REG_SUCCESS;
			} catch (Exception e) {
				request.setAttribute("errorMessage", "user_already_exists");
				LOG.trace("Set the request attribute: errorMessage --> " + "A user already existss");
				LOG.error("A user already exists: ", e);
			}
		} else {
			throw new AppException("different_passwords");
		}
		LOG.debug("Command finished");
		return page;
	}

}
