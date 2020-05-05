package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class ChangeLanguageCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		String language = request.getParameter("language");
		
		if("en".equals(language)) {
			session.setAttribute("language", "en");
		}
		else {
			session.setAttribute("language", "ru");
		}
		
		return Path.PAGE_MAIN;
	}
	
}
