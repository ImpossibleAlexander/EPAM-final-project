package ua.nure.kaplin.SummaryTask4.web.command;


import static org.mockito.Matchers.intThat;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class SetValuesForRouteUpdate extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		Route route = null;
		String trainId = request.getParameter("trainId");
		String trainNumber = request.getParameter("trainNumber");
		String departureStation = request.getParameter("departureStation");
		String destinationStation = request.getParameter("destinationStationName");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
		String destinationDateAndTime = request.getParameter("destinationDateAndTime");
		String coupe = request.getParameter("coupe");
		String reservedSeat = request.getParameter("reservedSeat");
		String common = request.getParameter("common");
		String coupePrice = request.getParameter("coupePrice");
		String reservedSeatPrice = request.getParameter("reservedSeatPrice");
		String commonPrice = request.getParameter("commonPrice");
		String trainStatus = request.getParameter("trainStatus");

		route = new Route();
		route.setTrainId(Integer.parseInt(trainId));
		route.setTrainNumber(Integer.parseInt(trainNumber));
		route.setDestinationDateAndTime(destinationDateAndTime);
		route.setDepartureDateAndTime(departureDateAndTime);
		route.setStationName(departureStation);
		route.setDestinationStationName(destinationStation);
		route.setCoupe(Integer.parseInt(coupe));
		route.setReservedSeat(Integer.parseInt(reservedSeat));
		route.setCoupe(Integer.parseInt(coupe));
		route.setReservedSeat(Integer.parseInt(reservedSeat));
		route.setCommon(Integer.parseInt(common));	
		route.setCoupePrice(Integer.parseInt(coupePrice));
		route.setReservedSeatPrice(Integer.parseInt(reservedSeatPrice));
		route.setCommonPrice(Integer.parseInt(commonPrice));
		route.setTrainStatus(trainStatus);
		request.setAttribute("route", route);

		
		return Path.PAGE_ADMIN_MAIN_PAGE;
	}

}
