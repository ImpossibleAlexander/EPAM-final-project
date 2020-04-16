package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class PageMappingCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		String page = (String) request.getParameter("page");
		page = getJsp(page);
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
		default:
			url = "";
			break;
		}
		return url;
	}
	
}
