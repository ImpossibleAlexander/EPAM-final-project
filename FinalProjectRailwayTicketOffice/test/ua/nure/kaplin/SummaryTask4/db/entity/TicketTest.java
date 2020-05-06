package ua.nure.kaplin.SummaryTask4.db.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TicketTest {
	private Ticket ticket;
	public TicketTest() {
		ticket = new Ticket();
		ticket.setId(1);
		ticket.setTicketNumber(1);
		ticket.setTrainNumber(1);
		ticket.setPlace("coupe");
		ticket.setPrice(1);
		ticket.setDepartureDateAndTime("2000-12-20");
		ticket.setDestinationDateAndTime("2000-12-20");
		ticket.setDepartureStation("test");
		ticket.setDestinationStation("test1");
	}
	
	@Test
	public void getTicketId() {
		assertTrue(ticket.getId() == 1);
	}
	@Test
	public void getTicketNumber() {
		assertTrue(ticket.getTicketNumber() == 1);
	}
	@Test
	public void getTrainNumber() {
		assertTrue(ticket.getTrainNumber() == 1);
	}
	@Test
	public void getPrice() {
		assertTrue(ticket.getPrice() == 1);
	}
	@Test
	public void getPlace() {
		assertTrue("coupe".equals(ticket.getPlace()));
	}
	@Test
	public void getDepartureDateAndTime() {
		assertTrue("2000-12-20".equals(ticket.getDepartureDateAndTime()));
	}
	@Test
	public void getDestinationDateAndTime() {
		assertTrue("2000-12-20".equals(ticket.getDestinationDateAndTime()));
	}
	@Test
	public void getDepartureStation() {
		assertTrue("test".equals(ticket.getDepartureStation()));
	}
	@Test
	public void getDestinationStation() {
		assertTrue("test1".equals(ticket.getDestinationStation()));
	}
}
