package ua.nure.kaplin.SummaryTask4.db.entity;

public class Ticket {
	private int id;
	private int trainNumber;
	private int ticketNumber;
	private String departureStation;
	private String destinationStation;
	private String departureDateAndTime;
	private String destinationDateAndTime;
	private String place;
	private int price;
	
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
	public int getTicketNumber() {
		return ticketNumber;
	}
	public void setTicketNumber(int ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	public String getDepartureStation() {
		return departureStation;
	}
	public void setDepartureStation(String departureStation) {
		this.departureStation = departureStation;
	}
	public String getDestinationStation() {
		return destinationStation;
	}
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}
	public String getDepartureDateAndTime() {
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
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
}
