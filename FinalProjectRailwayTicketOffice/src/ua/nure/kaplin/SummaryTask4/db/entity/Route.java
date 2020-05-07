package ua.nure.kaplin.SummaryTask4.db.entity;

public class Route {
	private int trainId;
	private int stationId;
	private int destinationStationId;
	private int trainNumber;
	private String stationName;
	private String destinationStationName;
	private String departureDateAndTime;
	private String destinationDateAndTime;
	private int coupe;
	private int reservedSeat;
	private int common;
	private int coupePrice;
	private int reservedSeatPrice;
	private int commonPrice;
	private String trainStatus;

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
	}

	public int getDestinationStationId() {
		return destinationStationId;
	}

	public void setDestinationStationId(int destinationStationId) {
		this.destinationStationId = destinationStationId;
	}

	public int getTrainNumber() {
		return trainNumber;
	}

	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public void setDepartureDateAndTime(String departureDateAndTime) {
		this.departureDateAndTime = departureDateAndTime;
	}

	public String getDestinationDateAndTime() {
		return destinationDateAndTime;
	}

	public void setDestinationDateAndTime(String destinationDateAndTime) {
		this.destinationDateAndTime = destinationDateAndTime;
	}

	public int getCoupe() {
		return coupe;
	}

	public void setCoupe(int coupe) {
		this.coupe = coupe;
	}

	public int getReservedSeat() {
		return reservedSeat;
	}

	public void setReservedSeat(int reservedSeat) {
		this.reservedSeat = reservedSeat;
	}

	public int getCommon() {
		return common;
	}

	public void setCommon(int common) {
		this.common = common;
	}

	public int getCoupePrice() {
		return coupePrice;
	}

	public void setCoupePrice(int coupePrice) {
		this.coupePrice = coupePrice;
	}

	public int getReservedSeatPrice() {
		return reservedSeatPrice;
	}

	public void setReservedSeatPrice(int reservedSeatPrice) {
		this.reservedSeatPrice = reservedSeatPrice;
	}

	public int getCommonPrice() {
		return commonPrice;
	}

	public void setCommonPrice(int commonPrice) {
		this.commonPrice = commonPrice;
	}

	public String getDestinationStationName() {
		return destinationStationName;
	}

	public void setDestinationStationName(String destinationStationName) {
		this.destinationStationName = destinationStationName;
	}

	public String getDepartureDateAndTime() {
		return departureDateAndTime;
	}

	public String getTrainStatus() {
		return trainStatus;
	}

	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}

	@Override
	public String toString() {
		return "Route [trainId=" + trainId + ", stationId=" + stationId + ", destinationStationId="
				+ destinationStationId + ", trainNumber=" + trainNumber + ", stationName=" + stationName
				+ ", destinationStationName=" + destinationStationName + ", departureDateAndTime="
				+ departureDateAndTime + ", destinationDateAndTime=" + destinationDateAndTime + ", coupe=" + coupe
				+ ", reservedSeat=" + reservedSeat + ", common=" + common + ", coupePrice=" + coupePrice
				+ ", reservedSeatPrice=" + reservedSeatPrice + ", commonPrice=" + commonPrice + ", trainStatus="
				+ trainStatus + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + trainId;
		result = prime * result + trainNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Route other = (Route) obj;
		if (trainNumber != other.trainNumber)
			return false;
		return true;
	}

}
