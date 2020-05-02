package ua.nure.kaplin.SummaryTask4.web.command;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Test;
import org.mockito.Mockito;

import ua.nure.kaplin.SummaryTask4.Path;
import ua.nure.kaplin.SummaryTask4.DAO.DaoRoute;
import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoRouteImpl;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;

public class GetRouteCommandTest extends Mockito{
	
	@Test
	public void getRoute() throws NumberFormatException, Exception {
		Route route1 = new Route();
		Route route2 = new Route();
		List<Route> routes = new ArrayList<Route>();
		route1.setTrainNumber(5186);
		route1.setDestinationDateAndTime("2020-02-02 14:14:00");
		route1.setDepartureDateAndTime("2020-01-01 14:14:00");
		route1.setStationName("Харьков");
		route1.setCoupe(1);
		route1.setReservedSeat(2);
		route1.setCommon(3);
		route1.setCoupePrice(4);
		route1.setReservedSeatPrice(5);
		route1.setCommonPrice(6);

		
		route2.setTrainNumber(5186);
		route2.setDestinationDateAndTime("2020-03-02 14:14:00");
		route2.setDepartureDateAndTime("2020-02-02 16:14:00");
		route2.setStationName("Киев");
		route2.setCoupe(1);
		route2.setReservedSeat(2);
		route2.setCommon(3);
		route2.setCoupePrice(4);
		route2.setReservedSeatPrice(5);
		route2.setCommonPrice(6);
		
		routes.add(route1);
		routes.add(route2);
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		HttpServletResponse response = mock(HttpServletResponse.class);
		HttpSession session = mock(HttpSession.class);
		
		DaoRoute dao = mock(DaoRouteImpl.class);
		
		when(request.getParameter("trainNumber")).thenReturn("5186");
		when(request.getParameter("arrivalStation")).thenReturn("");
		when(request.getParameter("departureStation")).thenReturn("");
		when(session.getAttribute("userRole")).thenReturn("ADMIN");
		String trainNumber = "5186";
		
		GetRouteCommand command = new GetRouteCommand();
		
		when(dao.findRouteByTrainNumber(Integer.parseInt(trainNumber))).thenReturn(routes);
		assertTrue(Path.PAGE_ADMIN_MAIN_PAGE.equals(command.execute(request, response)));
		//assertTrue(request.getAttribute("routes").equals(routes));
	}
}
