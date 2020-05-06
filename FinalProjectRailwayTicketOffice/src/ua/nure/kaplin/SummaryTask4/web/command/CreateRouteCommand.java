package ua.nure.kaplin.SummaryTask4.web.command;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRouteImpl;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoTrainStationImpl;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;
import ua.nure.kaplin.SummaryTask4.exception.AppException;

public class CreateRouteCommand extends Command{
	
	private static final Logger LOG = Logger.getLogger(CommandContainer.class);

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException, AppException {
		
		LOG.debug("Command starts");
		String page = Path.PAGE_ERROR;
		DaoRouteImpl daoRoute = null;
		DaoTrainStationImpl daoStation = null;
		Route route = null;
		TrainStation departureStation = null;
		TrainStation destinationStation = null;
		
		String trainNumber = request.getParameter("trainNumber");
		String departureStationName = request.getParameter("stationName");
		String destinationStationName = request.getParameter("destinationStationName");
		String departureDateAndTime = request.getParameter("departureDateAndTime");
		String destinationDateAndTime = request.getParameter("destinationDateAndTime");
		
		String coupe = request.getParameter("coupe");
		String reservedSeat = request.getParameter("reservedSeat");
		String common = request.getParameter("common");
		
		String coupePrice = request.getParameter("coupePrice");
		String reservedSeatPrice = request.getParameter("reservedSeatPrice");
		String commonPrice = request.getParameter("commonPrice");
		
		if(trainNumber == null || trainNumber.isEmpty()
			|| departureStationName == null || departureStationName.isEmpty()
			||destinationStationName == null || destinationStationName.isEmpty()
			||departureDateAndTime == null || departureDateAndTime.isEmpty()
			||destinationDateAndTime == null || destinationDateAndTime.isEmpty()
			||coupe == null || coupe.isEmpty()
			||reservedSeat == null || reservedSeat.isEmpty()
			||common == null || common.isEmpty()
			||coupePrice == null || coupePrice.isEmpty()
			||reservedSeatPrice == null || reservedSeatPrice.isEmpty()
			||commonPrice == null || commonPrice.isEmpty()) {
			throw new AppException("empty_fields");
		}
		try {
			route = new Route();
			daoStation = new DaoTrainStationImpl();
			daoRoute = new DaoRouteImpl();
			departureStation = new TrainStation();
			destinationStation = new TrainStation();
			departureStation = daoStation.findStationByName(departureStationName);
			destinationStation = daoStation.findStationByName(destinationStationName);
			LOG.trace("Select departure and destination stations from DB: departure & destination --> " + departureStation + " & " + destinationStation);
			
			route.setTrainNumber(Integer.parseInt(trainNumber));
			route.setCoupe(Integer.parseInt(coupe));
			route.setReservedSeat(Integer.parseInt(reservedSeat));
			route.setCommon(Integer.parseInt(common));	
			route.setCoupePrice(Integer.parseInt(coupePrice));
			route.setReservedSeat(Integer.parseInt(reservedSeatPrice));
			route.setCommonPrice(Integer.parseInt(commonPrice));
			route.setStationId(departureStation.getId());
			route.setDepartureDateAndTime(departureDateAndTime);
			route.setDestinationStationId(destinationStation.getId());
			route.setDestinationDateAndTime(destinationDateAndTime);
			LOG.trace("Set route: route --> " + route);
			
			daoRoute.insertRoute(route);
			LOG.trace("Insert route in DB: route --> " + route);
			page = Path.PAGE_MAIN_FOR_ADMIN_REDIRECT;
		} catch (Exception e) {
			request.setAttribute("errorMessage",  "route_already_exist");
			LOG.trace("Set the request attribute: errorMessage --> " + "Route already exist");
			LOG.error("Route already exist: ", e);
		}
		LOG.debug("Command finished");
		return page;
	}
	
}
