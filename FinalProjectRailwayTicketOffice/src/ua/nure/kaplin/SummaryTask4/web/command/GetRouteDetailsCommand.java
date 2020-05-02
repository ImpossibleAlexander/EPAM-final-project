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
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.exception.AppException;
import ua.nure.kaplin.SummaryTask4.db.Role;

public class GetRouteDetailsCommand extends Command {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		LOG.debug("Command starts");
		HttpSession session = request.getSession();
		Role userRole = (Role) session.getAttribute("userRole");
		String page = Path.PAGE_ROUTE_DETAILS;
		String trainNumber = request.getParameter("trainNumber");
		String stationName = request.getParameter("stationName");
		String departureStation = request.getParameter("departureStation");
		String destinationStation = request.getParameter("destinationStation");
		List<Route> routes = null;
		DaoRouteImpl dao = new DaoRouteImpl();

		try {
			if (userRole == Role.ADMIN) {
				routes = new ArrayList<Route>();
				if(trainNumber != null) {
					request.setAttribute("trainNumber", trainNumber);
					routes = setRouteBetweenRoutePointsForAdmin(dao.findRouteByTrainNumber(Integer.parseInt(trainNumber)));
					LOG.trace("Found in DB: routes --> " + routes);
					request.setAttribute("routes", routes);
					LOG.trace("Set the request attribute: routes --> " + routes);
				}
				if(stationName != null) {
					request.setAttribute("oldStationName", stationName);
					LOG.trace("Set the request attribute: oldStationName --> " + stationName);
				}
				page = Path.PAGE_ADMIN_MENU;
			} else {
				routes = new ArrayList<Route>();
				routes = setRouteBetweenRoutePoints(dao.findRouteByTrainNumber(Integer.parseInt(trainNumber)),
						departureStation, destinationStation);
				request.setAttribute("routes", routes);
				LOG.trace("Set the request attribute: routes --> " + routes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOG.debug("Command finished");
		return page;
	}
	
	private List<Route> setRouteBetweenRoutePoints (List<Route> routePoints, String departureStation, String destinationStation) {
		int indexOfDepSt = 0;
		int indexOfDestSt = 0;
		for (int i = 0; i < routePoints.size(); i++) {
			if(routePoints.get(i).getStationName().contains(departureStation) && !departureStation.isEmpty()) {
				indexOfDepSt = i;
			}
			else if (routePoints.get(i).getStationName().contains(destinationStation) && !destinationStation.isEmpty()) {
				indexOfDestSt = i+1;
			}
		}
		routePoints = routePoints.subList(indexOfDepSt, indexOfDestSt);
		for(int i = 0; i < routePoints.size()-1; i++) {
			if( i < routePoints.size()) {
				routePoints.get(i).setDestinationStationName(routePoints.get(i+1).getStationName());
			}
		}
		
		return routePoints;
	}
	
	private List<Route> setRouteBetweenRoutePointsForAdmin (List<Route> routePoints) {
		for(int i = 0; i < routePoints.size()-1; i++) {
			if( i < routePoints.size()) {
				routePoints.get(i).setDestinationStationName(routePoints.get(i+1).getStationName());
			}
		}
		return routePoints;
	}

}
