package ua.nure.kaplin.SummaryTask4.db.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class TrainStationTest {
	private TrainStation trainStation;

	public TrainStationTest() {
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

	@Test
	public void equalsTest() {
		TrainStation station2 = trainStation;
		assertTrue(trainStation.equals(station2));
		assertFalse(trainStation.equals(null));
		station2 = new TrainStation();
		station2.setStationName("test1");
		assertFalse(trainStation.equals(station2));
		station2.setStationName("test");
		assertTrue(trainStation.equals(station2));
		Train train = new Train();
		assertFalse(trainStation.equals(train));
		trainStation.setStationName(null);
		assertFalse(trainStation.equals(station2));
	}

	@Test
	public void hashCodeTes1t() {
		assertTrue(trainStation.hashCode() == 3556529);
	}
	
	@Test
	public void hashCodeTest2() {
		trainStation.setStationName(null);
		assertTrue(trainStation.hashCode() == 31);
	}

	@Test
	public void toStringTest() {
		String expected = "TrainStation [stationName=" + trainStation.getStationName() + "]";
		Assert.assertEquals(expected, trainStation.toString());
	}
}
