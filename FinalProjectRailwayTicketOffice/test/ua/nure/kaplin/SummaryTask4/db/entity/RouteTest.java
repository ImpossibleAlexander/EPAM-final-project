package ua.nure.kaplin.SummaryTask4.db.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class RouteTest {
	private Route route;

	public RouteTest() {
		route = new Route();
		route.setTrainId(1);
		route.setTrainNumber(1);
		route.setTrainStatus("active");
		route.setStationName("test");
		route.setStationId(1);
		route.setReservedSeatPrice(1);
		route.setReservedSeat(1);
		route.setDestinationStationName("test1");
		route.setDestinationStationId(1);
		route.setDestinationDateAndTime("2000-12-20");
		route.setDepartureDateAndTime("2000-12-20");
		route.setCoupePrice(1);
		route.setCoupe(1);
		route.setCommonPrice(1);
		route.setCommon(1);
	}

	@Test
	public void getTrainId() {
		assertTrue(route.getTrainId() == 1);
	}

	@Test
	public void getTrainNumber() {
		assertTrue(route.getTrainNumber() == 1);
	}

	@Test
	public void getStationId() {
		assertTrue(route.getStationId() == 1);
	}

	@Test
	public void getTrainStatus() {
		assertTrue("active".equals(route.getTrainStatus()));
	}

	@Test
	public void getStationName() {
		assertTrue("test".equals(route.getStationName()));
	}

	@Test
	public void getReservedSeatPrice() {
		assertTrue(route.getReservedSeatPrice() == 1);
	}

	@Test
	public void getReservedSeat() {
		assertTrue(route.getReservedSeat() == 1);
	}

	@Test
	public void getDestinationStationName() {
		assertTrue("test1".equals(route.getDestinationStationName()));
	}

	@Test
	public void getDestinationStationId() {
		assertTrue(route.getDestinationStationId() == 1);
	}

	@Test
	public void getDestinationDateAndTime() {
		assertTrue("2000-12-20".equals(route.getDestinationDateAndTime()));
	}

	@Test
	public void getDepartureDateAndTime() {
		assertTrue("2000-12-20".equals(route.getDepartureDateAndTime()));
	}

	@Test
	public void getCoupePrice() {
		assertTrue(route.getCoupePrice() == 1);
	}

	@Test
	public void getCoupe() {
		assertTrue(route.getCoupe() == 1);
	}

	@Test
	public void getCommonPrice() {
		assertTrue(route.getCommonPrice() == 1);
	}

	@Test
	public void getCommon() {
		assertTrue(route.getCommon() == 1);
	}

	@Test
	public void equalsTest() {
		Route route2 = route;
		assertTrue(route.equals(route2));
		assertFalse(route.equals(null));

		route2 = new Route();
		route2.setTrainNumber(2);
		assertFalse(route.equals(route2));

		route2.setTrainNumber(1);
		assertTrue(route.equals(route2));

		TrainStation station = new TrainStation();
		assertFalse(route.equals(station));
	}

	@Test
	public void hashCodeTest() {
		assertTrue(route.hashCode() == 993);
	}

	@Test
	public void toStringTest() {
		String expected = "Route [trainId=" + route.getTrainId() + ", stationId=" + route.getStationId() + ", destinationStationId="
				+ route.getDestinationStationId() + ", trainNumber=" + route.getTrainNumber() + ", stationName=" + route.getStationName()
				+ ", destinationStationName=" + route.getDestinationStationName() + ", departureDateAndTime="
				+ route.getDepartureDateAndTime() + ", destinationDateAndTime=" + route.getDestinationDateAndTime() + ", coupe=" + route.getCoupe()
				+ ", reservedSeat=" + route.getReservedSeat() + ", common=" + route.getCommon() + ", coupePrice=" + route.getCoupePrice()
				+ ", reservedSeatPrice=" + route.getReservedSeatPrice() + ", commonPrice=" + route.getCommonPrice() + ", trainStatus="
				+ route.getTrainStatus() + "]";
		Assert.assertEquals(expected, route.toString());
	}
}
