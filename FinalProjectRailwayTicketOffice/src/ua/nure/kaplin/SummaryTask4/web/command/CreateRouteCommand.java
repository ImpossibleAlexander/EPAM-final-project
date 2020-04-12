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

public class CreateRouteCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		DaoRoute daoRoute = null;
		DaoTrainStation daoStation = null;
		Route route = null;
		TrainStation departureStation = null;
		TrainStation destinationStation = null;
		
		String trainNumber = request.getParameter("trainNumber");
		String departureStationName = request.getParameter("stationName");
		String destinationStationName = request.getParameter("destinationStationName");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
		
		String coupe = request.getParameter("coupe");
		String reservedSeat = request.getParameter("reservedSeat");
		String common = request.getParameter("common");
		
		String coupePrice = request.getParameter("coupePrice");
		String reservedSeatPrice = request.getParameter("reservedSeatPrice");
		String commonPrice = request.getParameter("commonPrice");
		
		try {
			route = new Route();
			daoStation = new DaoTrainStation();
			daoRoute = new DaoRoute();
			departureStation = new TrainStation();
			destinationStation = new TrainStation();
			departureStation = daoStation.findStationByName(departureStationName);
			destinationStation = daoStation.findStationByName(destinationStationName);
			
			route.setTrainNumber(Integer.parseInt(trainNumber));
			route.setCoupe(Integer.parseInt(coupe));
			route.setReservedSeat(Integer.parseInt(reservedSeat));
			route.setCommon(Integer.parseInt(common));	
			route.setCoupePrice(Integer.parseInt(coupePrice));
			route.setReservedSeat(Integer.parseInt(reservedSeatPrice));
			route.setCommonPrice(Integer.parseInt(commonPrice));
			
			route.setStationId(departureStation.getId());
			route.setDepartureDateAndTime(departureDateAndTime);
			
			route.setDestinationStationId(destinationStation.getId());
			route.setDestinationDateAndTime(departureDateAndTime);
			
			daoRoute.insertRoute(route);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Path.PAGE_MAIN_REDIRECT;
	}
	
}
