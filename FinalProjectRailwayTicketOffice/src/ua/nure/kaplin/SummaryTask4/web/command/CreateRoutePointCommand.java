package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRouteImpl;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainImpl;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStationImpl;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.exception.AppException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

public class CreateRoutePointCommand extends Command {

	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {

		LOG.debug("Command starts");

		Route route = null;
		DaoRouteImpl daoRoute = null;
		DaoTrainStationImpl daoTrainStation = null;
		DaoTrainImpl daoTrain = null;
		String trainNumber = request.getParameter("trainNumber");
		String departureStationName = request.getParameter("stationName");
		String destinationDateAndTime = request.getParameter("destinationDateAndTime");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
		String page = Path.PAGE_ERROR;

		route = new Route();
		daoTrain = new DaoTrainImpl();
		daoTrainStation = new DaoTrainStationImpl();
		daoRoute = new DaoRouteImpl();

		route.setTrainNumber(Integer.parseInt(trainNumber));
		route.setStationName(departureStationName);
		route.setDestinationDateAndTime(destinationDateAndTime);
		route.setDepartureDateAndTime(departureDateAndTime);

		try {
			route.setTrainId((daoTrain.findTrainByNumber(route.getTrainNumber())).getId());
			route.setStationId((daoTrainStation.findStationByName(departureStationName).getId()));
			daoRoute.insertRoutePoint(route);
			LOG.trace("Insert route in DB: route --> " + route);
			page = Path.PAGE_ADMIN_MENU_REDIRECT;

		} catch (Exception e) {
			request.setAttribute("errorMessage", "Can not create route point");
			LOG.trace("Set the request attribute: errorMessage --> " + "Can not create route point");
			LOG.error("Can not create route point", e);
		}

		LOG.debug("Command finished");
		return page;
	}

}
