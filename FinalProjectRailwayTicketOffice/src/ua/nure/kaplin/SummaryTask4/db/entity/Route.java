package ua.nure.kaplin.SummaryTask4.db.entity;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	public String DepartureDateAndTime() {
		return departureDateAndTime;
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
}
