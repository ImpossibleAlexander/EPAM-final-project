package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.db.entity.Ticket;
import ua.nure.kaplin.SummaryTask4.db.entity.User;
import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTickets;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class BuyTicketCommand extends Command {
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		DaoTickets dao = null;
		Ticket ticket = null;
		String place = null;
		StringBuilder builder = null;
		int price = 0;

		HttpSession session = request.getSession();
		List<Route> routes = (List<Route>) session.getAttribute("routesForBasket");
		LOG.trace("Get the session attribute: routesForBasket --> " + routes);
		
		String trainNumber = request.getParameter("trainNumber");
		String departureStation = request.getParameter("departureStation");
		String destinationStation = request.getParameter("destinationStation");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
		String destinationDateAndTime = request.getParameter("destinationDateAndTime");
		String coupePrice = request.getParameter("coupePrice");
		String reservedSeatPrice = request.getParameter("reservedSeatPrice");
		String commonPrice = request.getParameter("commonPrice");
		String placeFromRequest = request.getParameter("place");
	
		User user = (User) session.getAttribute("user");
		builder = new StringBuilder();
		builder.append(user.getId()).append(trainNumber);
		LOG.trace("Generate ticket number --> " + builder.toString());
		
		if (placeFromRequest.contains("coupe")) {
			price = Integer.parseInt(coupePrice);
			place = "coupe";
		}

		if (placeFromRequest.contains("reserved")) {
			price = Integer.parseInt(reservedSeatPrice);
			place = "reserved";
		}

		if (placeFromRequest.contains("common")) {
			price = Integer.parseInt(commonPrice);
			place = "common";
		}
		
		LOG.trace("Choose place & price --> " + place + " & " + price);

		ticket = new Ticket();
		ticket.setTrainNumber(Integer.parseInt(trainNumber));
		ticket.setTicketNumber(Integer.parseInt(builder.toString()));
		ticket.setDepartureStation(departureStation);
		ticket.setDestinationDateAndTime(destinationDateAndTime);
		ticket.setDestinationStation(destinationStation);
		ticket.setDepartureDateAndTime(departureDateAndTime);
		ticket.setPlace(place);
		ticket.setPrice(price);
		LOG.trace("Set ticket: ticket --> " + ticket);
		
		try {
			dao = new DaoTickets();
			dao.insertTicket(ticket, user);
			LOG.trace("Insert user ticket in DB: ticket & user --> " + ticket + " & " + user);
			
			int counter = 0;
			for(Route route: routes) {
				if(route.getTrainNumber() == ticket.getTrainNumber()) {
					break;
				}
				counter++;
			}
			routes.remove(counter);
			
			session.setAttribute("routesForBasket", routes);
			LOG.trace("Set the session attribute: routes --> " + routes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOG.debug("Command finished");
		return Path.PAGE_BASKET_REDIRECT;
	}

}
