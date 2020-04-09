package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRoute;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.exception.AppException;
import ua.nure.kaplin.SummaryTask4.db.Role;

public class GetRouteDetailsCommand extends Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		HttpSession session = request.getSession();
		Role userRole = (Role) session.getAttribute("userRole");
		String page = Path.PAGE_ROUTE_DETAILS;
		String trainNumber = request.getParameter("trainNumber");
		String stationName = request.getParameter("stationName");
		String departureStation = request.getParameter("departureStation");
		String destinationStation = request.getParameter("destinationStation");
		List<Route> routes = new ArrayList<Route>();
		DaoRoute dao = new DaoRoute();

		try {
			if (userRole == Role.ADMIN) {
				
				if(trainNumber != null) {
					request.setAttribute("trainNumber", trainNumber);
					routes = dao.findRouteByTrainNumber(Integer.parseInt(trainNumber));
					request.setAttribute("routes", routes);
				}
				if(stationName != null) {
					request.setAttribute("oldStationName", stationName);
				}
				page = Path.PAGE_ADMIN_MENU;
			} else {
				routes = Route.setRouteBetweenRoutePoints(dao.findRouteByTrainNumber(Integer.parseInt(trainNumber)),
						departureStation, destinationStation);
				request.setAttribute("routes", routes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}

}
