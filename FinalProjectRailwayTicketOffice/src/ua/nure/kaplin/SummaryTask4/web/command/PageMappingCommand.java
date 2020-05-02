package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.exception.AppException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

public class PageMappingCommand extends Command{

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		String page = (String) request.getParameter("page");
		String errorMessage = (String) request.getParameter("errorMessage");
		page = getJsp(page);
		
		if(errorMessage != null && "cannotFindRoute".equals(errorMessage)) {
			throw new AppException("Cannot find route");
		}
		
		LOG.trace("Get path to the jsp: page --> " + page);
		LOG.debug("Command finished");
		return page;
	}
	
	private String getJsp(String page) {
		String url = "";
		switch (page) {
		case "main_page": 
			url = Path.PAGE_MAIN;
			break;
		case "login": 
			url = Path.PAGE_LOGIN;
			break;
		case "registration": 
			url = Path.PAGE_REGISTRATION;
			break;
		case "admin": 
			url = Path.PAGE_ADMIN_MENU;
			break;
		case "basket": 
			url = Path.PAGE_BASKET;
			break;
		case "user_page": 
			url = Path.PAGE_USER_PAGE;
			break;
		case "error_page": 
			url = Path.PAGE_ERROR;
			break;
		case "admin_main_page": 
			url = Path.PAGE_ADMIN_MAIN_PAGE;
			break;
		default:
			break;
		}
		return url;
	}
	
}
