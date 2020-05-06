package ua.nure.kaplin.SummaryTask4.db.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TrainStationTest {
	private TrainStation trainStation;
	public TrainStationTest () {
		trainStation = new TrainStation();
		trainStation.setId(1);
		trainStation.setStationName("test");
	}
	
	@Test
	public void getTrainStationId() {
		assertTrue(trainStation.getId() == 1);
	}
	@Test
	public void getTrainStationName() {
		assertTrue("test".contentEquals(trainStation.getStationName()));
	}
}
