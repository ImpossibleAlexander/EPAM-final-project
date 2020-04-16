package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoUser;
import ua.nure.kaplin.SummaryTask4.db.entity.User;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class RegistrationCommand extends Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		User user = null;
		DaoUser dao = null;
		String login = request.getParameter("login");	
		String email = request.getParameter("email");	
		String password = request.getParameter("password");	
		String passwordConfirm = request.getParameter("passwordConfirm");	
		String surname = request.getParameter("surname");	
		String name = request.getParameter("name");	
		
		if(password.contains(passwordConfirm)) {
			user = new User();
			dao = new DaoUser();
			user.setLogin(login);
			user.setEmail(email);
			user.setPassword(password);
			user.setLastName(surname);
			user.setFirstName(name);
			user.setRoleId(1);
			
			try {
				dao.insertUser(user);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return Path.PAGE_LOGIN_REDIRECT;
	}

}
