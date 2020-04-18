package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRoute;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrain;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.db.entity.Train;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class GetRouteCommand extends Command {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DaoRoute daoRoute = null;
		DaoTrain daoTrain = null;
		String departureStation = request.getParameter("departureStation");
		String trainNumber = request.getParameter("trainNumber");
		String arriveStation = request.getParameter("arrivalStation");
		List<Route> routes = new ArrayList<Route>();
		List<Route> routesBuf;
		List<Train> trains = new ArrayList<Train>();
		daoTrain = new DaoTrain();
		daoRoute = new DaoRoute();

		String page = Path.PAGE_ERROR_PAGE;
		String errorMessage = "Cannot find route between: " + departureStation + " and " + arriveStation;

		try {

			if (!trainNumber.isEmpty()) {
				Route route = setRoute(
						daoRoute.findRouteByTrainNumber(Integer.parseInt(trainNumber)));
				routes.add(route);
			} else {
				trains = daoTrain.findTrainNumberByStationName(departureStation, arriveStation);
				for (Train train : trains) {
					routesBuf = new ArrayList<Route>(setRouteDestinationDeparture(
							daoRoute.findRouteByTrainNumber(train.getTrainNumber()), departureStation, arriveStation));
					routes.addAll(routesBuf);
				}
			}
			if (routes.size() == 0) {
				request.setAttribute("errorMessage", errorMessage);
				throw new AppException(errorMessage.toString());
			}
			request.setAttribute("routes", routes);
			page = Path.PAGE_MAIN;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

	private List<Route> setRouteDestinationDeparture(List<Route> routePoints, String departureStation,
			String arriveStation) {
		List<Route> resultRoute = new ArrayList<Route>();
		Route route = new Route();

		for (Route route2 : routePoints) {
			if (route2.getStationName().contains(departureStation)) {
				route.setStationName(departureStation);
				route.setDepartureDateAndTime(route2.getDepartureDateAndTime());
			} else if (route2.getStationName().contains(arriveStation)) {
				route.setDestinationStationName(arriveStation);
				route.setDestinationDateAndTime(route2.getDestinationDateAndTime());
			}
		}
		route.setTrainNumber(routePoints.get(0).getTrainNumber());
		route.setCoupe(routePoints.get(0).getCoupe());
		route.setReservedSeat(routePoints.get(0).getReservedSeat());
		route.setCommon(routePoints.get(0).getCommon());
		route.setCoupePrice(routePoints.get(0).getCoupePrice());
		route.setReservedSeatPrice(routePoints.get(0).getReservedSeatPrice());
		route.setCommonPrice(routePoints.get(0).getCommonPrice());
		resultRoute.add(route);
		return resultRoute;
	}

	private Route setRoute(List<Route> routePoints) {
		Route routeEnd = routePoints.get(routePoints.size()-1);				
		routePoints.get(0).setDestinationStationName(routeEnd.getStationName());
		routePoints.get(0).setDestinationDateAndTime(routeEnd.getDestinationDateAndTime());	
		return routePoints.get(0);
	}

}
