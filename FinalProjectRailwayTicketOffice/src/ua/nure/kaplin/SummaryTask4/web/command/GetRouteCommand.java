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

public class GetRouteCommand extends Command{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		DaoRoute daoRoute = null;
		DaoTrain daoTrain = null;
	    String departureStation = request.getParameter("departureStation");
	    String arriveStation = request.getParameter("arrivalStation");
	    List<Route> routes = new ArrayList<Route>();
	    List<Route> routesBuf;
	    List<Train> trains = new ArrayList<Train>();
	    daoTrain = new DaoTrain();
	    daoRoute = new DaoRoute();
	    
	    
	    System.out.println(departureStation + arriveStation);
	    
	    String page = Path.PAGE_ERROR_PAGE;
	    String errorMessage = "Cannot find route between: " + departureStation + " and " + arriveStation;
	    try {
	    	trains = daoTrain.findTrainNumberByStationName(departureStation, arriveStation);
	    	
	    	for(Train train: trains) {
	    		System.out.println(train.getTrainNumber());
	    		routesBuf = new ArrayList<Route>(Route.setRouteDestinationDeparture(daoRoute.findRouteByTrainNumber(train.getTrainNumber()), departureStation, arriveStation));
	    		routes.addAll(routesBuf);
	    	}
	    	if(routes.size() == 0) {
	    		request.setAttribute("errorMessage", errorMessage);
	    		throw new AppException(errorMessage.toString());
		    }
			request.setAttribute("routes", routes);
			page =  Path.PAGE_MAIN;
		} catch (Exception e) {
			e.printStackTrace();
		}	    
		return page;
	}

}
