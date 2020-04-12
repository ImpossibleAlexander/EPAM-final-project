package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRoute;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrain;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStation;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class CreateRoutePointCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		Route route = null;
		DaoRoute daoRoute = null;
		DaoTrainStation daoTrainStation = null;
		DaoTrain daoTrain = null;
		String trainNumber = request.getParameter("trainNumber");
		String departureStationName = request.getParameter("stationName");
		String destinationDateAndTime = request.getParameter("destinationDateAndTime");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
		
		
		String page = Path.PAGE_ERROR_PAGE;
		
		if(trainNumber == null || departureStationName == null || trainNumber.isEmpty() || departureStationName.isEmpty()) {
			throw new AppException("Train number/station name cannot be empty");
		}
		
		route = new Route();
		daoTrain = new DaoTrain();
		daoTrainStation = new DaoTrainStation();
		daoRoute = new DaoRoute();
		
		route.setTrainNumber(Integer.parseInt(trainNumber));
		route.setStationName(departureStationName);
		route.setDestinationDateAndTime(destinationDateAndTime);
		route.setDepartureDateAndTime(departureDateAndTime);
		try {
			route.setTrainId((daoTrain.findTrainByNumber(route.getTrainNumber())).getId());
			route.setStationId((daoTrainStation.findStationByName(departureStationName).getId()));
			daoRoute.insertRoutePoint(route);
			System.out.println(route.getTrainId() + route.getStationId());
			page = Path.PAGE_ADMIN_MENU_REDIRECT;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return page;
	}
	
}
