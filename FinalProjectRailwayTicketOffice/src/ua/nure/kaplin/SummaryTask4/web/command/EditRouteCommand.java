package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRoute;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStation;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class EditRouteCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DaoRoute daoRoute = null;
		Route route = null;
		StringBuilder errorMessageUpdate = new StringBuilder();
		StringBuilder errorMessageInsertRoute = new StringBuilder();

				
		String action = request.getParameter("action");
		String trainId = request.getParameter("trainId");
		
		String trainNumber = request.getParameter("trainNumber");
		String departureStationName = request.getParameter("stationName");
		String destinationStationName = request.getParameter("destinationStationName");
		String destinationDateAndTime = request.getParameter("destinationDateAndTime");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
			
		String page = Path.PAGE_ERROR_PAGE;
	    
		errorMessageUpdate.append("Cannot update route point with station name - ")
						  .append(departureStationName);
		
		if("update".equals(action)) {
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
				page = Path.PAGE_ADMIN_MENU_REDIRECT;
			} catch (Exception e) {
				request.setAttribute("errorMessage", errorMessageUpdate.toString());
				e.printStackTrace();
			}
		}
		
		if("delete".equals(action)) {
			System.out.println("Delete");
		}
			
		errorMessageInsertRoute.append("Cannot add new route with train number - ")
					.append(trainNumber)
					.append("; departure station - ")
					.append(departureStationName)
					.append("; destination station - ")
					.append(destinationStationName);
																
		
		return page;
	}
	
	
}
