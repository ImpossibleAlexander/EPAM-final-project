package ua.nure.kaplin.SummaryTask4.db.entity;

public class TrainStation {
	private int id;
	private String stationName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	@Override
	public String toString() {
		return "TrainStation [stationName=" + stationName + "]";
	}
	
}
