package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class ChangeLanguageCommand extends Command{

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		String language = request.getParameter("language");
		String url = request.getParameter("url");
		
		if("en".equals(language)) {
			session.setAttribute("language", "en");
			LOG.trace("Language: " + "en");
		}
		else {
			session.setAttribute("language", "ru");
			LOG.trace("Language: " + "ru");
		}
		LOG.debug("Command finished");
		return url;
	}
	
}
