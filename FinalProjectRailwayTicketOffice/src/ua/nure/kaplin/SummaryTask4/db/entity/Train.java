package ua.nure.kaplin.SummaryTask4.db.entity;

public class Train {
	private int id;
	private int trainNumber;
	private int coupe;
	private int reservedSeat;
	private int common;
	private int coupePrice;
	private int reservedSeatPrice;
	private int commonPrice;
	private String trainStatus;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTrainNumber() {
		return trainNumber;
	}
	public void setTrainNumber(int trainNumber) {
		this.trainNumber = trainNumber;
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
	public void setReservedSeatPrice(int reserved_seat_price) {
		this.reservedSeatPrice = reserved_seat_price;
	}
	public int getCommonPrice() {
		return commonPrice;
	}
	public void setCommonPrice(int commonPrice) {
		this.commonPrice = commonPrice;
	}
	
	public String getTrainStatus() {
		return trainStatus;
	}
	public void setTrainStatus(String trainStatus) {
		this.trainStatus = trainStatus;
	}
	@Override
	public String toString() {
		return "Train [id=" + id + ", trainNumber=" + trainNumber + ", coupe=" + coupe + ", reservedSeat="
				+ reservedSeat + ", common=" + common + ", coupePrice=" + coupePrice + ", reservedSeatPrice="
				+ reservedSeatPrice + ", commonPrice=" + commonPrice + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Train other = (Train) obj;
		if (trainNumber != other.trainNumber)
			return false;
		return true;
	}
}
