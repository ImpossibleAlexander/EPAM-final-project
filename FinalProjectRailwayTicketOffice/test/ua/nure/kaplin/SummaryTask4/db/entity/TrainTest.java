package ua.nure.kaplin.SummaryTask4.db.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TrainTest {
	private Train train;
	public TrainTest() {
		train = new Train();
		train.setId(1);
		train.setTrainNumber(1);
		train.setTrainStatus("active");
		train.setReservedSeatPrice(1);
		train.setReservedSeat(1);
		train.setCoupePrice(1);
		train.setCoupe(1);
		train.setCommonPrice(1);
		train.setCommon(1);
	}
	
	@Test
	public void getTrainId() {
		assertTrue(train.getId() == 1);
	}
	@Test
	public void getTrainNumber() {
		assertTrue(train.getTrainNumber() == 1);
	}
	@Test
	public void getReservedSeatPrice() {
		assertTrue(train.getReservedSeatPrice() == 1);
	}
	@Test
	public void getCoupePrice() {
		assertTrue(train.getCoupePrice() == 1);
	}
	@Test
	public void getCommonPrice() {
		assertTrue(train.getCommonPrice() == 1);
	}
	@Test
	public void getReservedSeat() {
		assertTrue(train.getReservedSeat() == 1);
	}
	@Test
	public void getCoupe() {
		assertTrue(train.getCoupe() == 1);
	}
	@Test
	public void getCommon() {
		assertTrue(train.getCommon() == 1);
	}
	@Test
	public void getTrainStatus() {
		assertTrue("active".equals(train.getTrainStatus()));
	}
}
