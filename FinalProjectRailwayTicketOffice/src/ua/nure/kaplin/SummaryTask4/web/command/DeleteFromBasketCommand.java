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

public class DeleteFromBasketCommand extends Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		String trainNumber = request.getParameter("trainNumber");

		HttpSession session = request.getSession();
		List<Route> routes = (List<Route>) session.getAttribute("routesForBasket");

		int counter = 0;
		for (Route route : routes) {
			if (route.getTrainNumber() == Integer.parseInt(trainNumber)) {
				break;
			}
			counter++;
		}

		routes.remove(counter);
		session.setAttribute("routesForBasket", routes);

		return Path.PAGE_BASKET;
	}

}
