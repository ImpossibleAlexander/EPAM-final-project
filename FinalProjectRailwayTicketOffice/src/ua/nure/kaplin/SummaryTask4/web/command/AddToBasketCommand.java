package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class AddToBasketCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		HttpSession session = request.getSession();
		List<Route> routes = (List<Route>) session.getAttribute("routesForBasket");
		Route route = new Route();
	
		
		String trainNumber = request.getParameter("trainNumber");
		String departureStation = request.getParameter("departureStation");
	    String destinationStation = request.getParameter("destinationStationName");
	    String departureDateAndTime = request.getParameter("departureDateAndTime");
	    String destinationDateAndTime = request.getParameter("destinationDateAndTime");
	    String coupePrice = request.getParameter("coupePrice");
	    String reservedSeatPrice = request.getParameter("reservedSeatPrice");
	    String commonPrice = request.getParameter("commonPrice");
	    
	    route.setTrainNumber(Integer.parseInt(trainNumber));
	    route.setStationName(departureStation);
	    route.setDestinationStationName(destinationStation);
		route.setDepartureDateAndTime(departureDateAndTime);
		route.setDestinationDateAndTime(destinationDateAndTime);
		route.setCoupePrice(Integer.parseInt(coupePrice));
		route.setReservedSeatPrice(Integer.parseInt(reservedSeatPrice));
		route.setCommonPrice(Integer.parseInt(commonPrice));
	    routes.add(route);
	    

	    session.removeAttribute("routesForBasket");
		session.setAttribute("routesForBasket", routes);
		return Path.PAGE_MAIN_REDIRECT;
	}
}
