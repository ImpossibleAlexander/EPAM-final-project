package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRoute;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class RoutePointUpdateCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DaoRoute daoRoute = null;
		Route route = null;
		
		String trainId = request.getParameter("trainId");
		String departureStationName = request.getParameter("stationName");
		String destinationDateAndTime = request.getParameter("destinationDateAndTime");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
				
			try {
				daoRoute = new DaoRoute();
				route = new Route();
				route.setTrainId(Integer.parseInt(trainId));
				route.setStationName(departureStationName);
				if(destinationDateAndTime.isEmpty()) {
					destinationDateAndTime = null;
				}
				route.setDestinationDateAndTime(destinationDateAndTime);
				if(departureDateAndTime.isEmpty()) {
					departureDateAndTime = null;
				}
				route.setDepartureDateAndTime(departureDateAndTime);
				daoRoute.updateRoutePoints(route);
			} catch (Exception e) {
				e.printStackTrace();
			}
		return Path.PAGE_ADMIN_MENU_REDIRECT;
	}

}
