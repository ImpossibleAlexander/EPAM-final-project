package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRouteImpl;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainImpl;
import ua.nure.kaplin.SummaryTask4.db.Role;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.db.entity.Train;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class GetRouteCommand extends Command {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		
		HttpSession session = request.getSession();
		
		DaoRouteImpl daoRoute = null;
		DaoTrainImpl daoTrain = null;
		String departureStation = request.getParameter("departureStation");
		String trainNumber = request.getParameter("trainNumber");
		String arriveStation = request.getParameter("arrivalStation");
		List<Route> routes = new ArrayList<Route>();
		List<Route> routesBuf;
		List<Train> trains = new ArrayList<Train>();
		daoTrain = new DaoTrainImpl();
		daoRoute = new DaoRouteImpl();
		
		String page = null;
		
		try {
			if (trainNumber != null && !trainNumber.isEmpty() && arriveStation.isEmpty() && departureStation.isEmpty()) {
				Route route = setRoute(
						daoRoute.findRouteByTrainNumber(Integer.parseInt(trainNumber)));
				if(route == null) {
					page = Path.PAGE_ERROR;
					request.setAttribute("errorMessage", "cannot_find_route");
					throw new AppException("cannot_find_route");
				}
				routes.add(route);
				LOG.trace("Found in DB: route --> " + routes);
			}
			else if (trainNumber != null 
					&& departureStation != null 
					&& arriveStation != null 
					&& !departureStation.isEmpty()
					&& !trainNumber.isEmpty() 
					&& !arriveStation.isEmpty()) {
					Train train =daoTrain.findTrainByNumber(Integer.parseInt(trainNumber));
					if(train == null) {
						page = Path.PAGE_ERROR;
						request.setAttribute("errorMessage", "cannot_find_route");
						LOG.trace("Set the request attribute: errorMessage --> " + "Cannot find route");
						throw new AppException("cannot_find_route");
					}
					routes = new ArrayList<Route>(setRouteDestinationDeparture(
							daoRoute.findRouteByTrainNumber(Integer.parseInt(trainNumber)), departureStation, arriveStation));
			}
			else {
				trains = daoTrain.findTrainNumberByStationName(departureStation, arriveStation);
				for (Train train : trains) {
					routesBuf = new ArrayList<Route>(setRouteDestinationDeparture(
							daoRoute.findRouteByTrainNumber(train.getTrainNumber()), departureStation, arriveStation));
					routes.addAll(routesBuf);
				}
				LOG.trace("Found in DB: routes --> " + routes);
			}
					
			if(session.getAttribute("userRole") != null && session.getAttribute("userRole").equals(Role.ADMIN)) {
				page = Path.PAGE_ADMIN_MAIN_PAGE;
			}
			else {
				page = Path.PAGE_MAIN;
			}
			
			if (routes.size() == 0) {
				page = Path.PAGE_ERROR;
				request.setAttribute("errorMessage", "cannot_find_route");
				LOG.trace("Set the request attribute: errorMessage --> " + "Cannot find route");
				throw new AppException("cannot_find_route");
			}
			
			request.setAttribute("routes", routes);
			LOG.trace("Set the request attribute: routes --> " + routes);
			
		} 
		catch (Exception e) {
			LOG.error("Find route from DB: ", e);
		}
		LOG.debug("Command finished");
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
		route.setTrainStatus(routePoints.get(0).getTrainStatus());
		resultRoute.add(route);
		return resultRoute;
	}

	private Route setRoute(List<Route> routePoints) {
		if(routePoints.size() != 0) {
			Route routeEnd = routePoints.get(routePoints.size()-1);				
			routePoints.get(0).setDestinationStationName(routeEnd.getStationName());
			routePoints.get(0).setDestinationDateAndTime(routeEnd.getDestinationDateAndTime());
			return routePoints.get(0);
		}
		return null;
	}

}
