package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.db.entity.Ticket;
import ua.nure.kaplin.SummaryTask4.db.entity.User;
import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTickets;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class BuyTicketCommand extends Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DaoTickets dao = null;
		Ticket ticket = null;
		String place = null;
		StringBuilder builder = null;
		int price = 0;

		HttpSession session = request.getSession();
		List<Route> routes = (List<Route>) session.getAttribute("routesForBasket");
		
		String trainNumber = request.getParameter("trainNumber");
		String departureStation = request.getParameter("departureStation");
		String destinationStation = request.getParameter("destinationStation");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
		String destinationDateAndTime = request.getParameter("destinationDateAndTime");
		String coupePrice = request.getParameter("coupePrice");
		String reservedSeatPrice = request.getParameter("reservedSeatPrice");
		String commonPrice = request.getParameter("commonPrice");

		User user = (User) session.getAttribute("user");
		builder = new StringBuilder();
		builder.append(user.getId()).append(trainNumber);

		if (coupePrice != null) {
			price = Integer.parseInt(coupePrice);
			place = "coupe";
		}

		if (reservedSeatPrice != null) {
			price = Integer.parseInt(reservedSeatPrice);
			place = "reserved";
		}

		if (commonPrice != null) {
			price = Integer.parseInt(commonPrice);
			place = "common";
		}

		ticket = new Ticket();

		ticket.setTrainNumber(Integer.parseInt(trainNumber));
		ticket.setTicketNumber(Integer.parseInt(builder.toString()));
		ticket.setDepartureStation(departureStation);
		ticket.setDestinationDateAndTime(destinationDateAndTime);
		ticket.setDestinationStation(destinationStation);
		ticket.setDepartureDateAndTime(departureDateAndTime);
		ticket.setPlace(place);
		ticket.setPrice(price);

		try {
			dao = new DaoTickets();
			dao.insertTicket(ticket);
			dao.insertUserTicket((int)(user.getId()), ticket.getTicketNumber());
			
			int counter = 0;
			for(Route route: routes) {
				if(route.getTrainNumber() == ticket.getTrainNumber()) {
					break;
				}
				counter++;
			}
			
			routes.remove(counter);
			session.setAttribute("routesForBasket", routes);
			
		} catch (Exception e) {
			e.printStackTrace();
		}


		return Path.PAGE_BASKET_REDIRECT;
	}

}
