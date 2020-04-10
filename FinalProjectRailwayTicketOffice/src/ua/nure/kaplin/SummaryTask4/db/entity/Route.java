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
	
	public static List<Route> setRouteDestinationDeparture (List<Route> routePoints, String departureStation, String arriveStation) {
		int counter = 0;
		List<Route> resultRoute = new ArrayList<Route>();
		Route route = new Route();
		
		for(Route route2: routePoints) {
			if(route2.getStationName().contains(departureStation)) {
				route.setStationName(departureStation);
				route.setDepartureDateAndTime(route2.getDepartureDateAndTime());
			}
			else if (route2.getStationName().contains(arriveStation)) {
				route.setDestinationStationName(arriveStation);
				route.setDestinationDateAndTime(route2.getDestinationDateAndTime());
			}
		}
		
		route.setTrainNumber(routePoints.get(0).getTrainNumber());
		route.setCoupe(routePoints.get(0).getCoupe());
		route.setReservedSeat(routePoints.get(0).getReservedSeat());
		route.setCommon(routePoints.get(0).getCommon());
		route.setCoupePrice(routePoints.get(0).getCoupePrice());
		route.setReservedSeatPrice(routePoints.get(0).getReservedSeatPrice());
		route.setCommonPrice(routePoints.get(0).getCommonPrice());
		resultRoute.add(route);
		return resultRoute;
	}
	
	public static List<Route> setRouteBetweenRoutePoints (List<Route> routePoints, String departureStation, String destinationStation) {
		int indexOfDepSt = 0;
		int indexOfDestSt = 0;
		for (int i = 0; i < routePoints.size(); i++) {
			if(routePoints.get(i).getStationName().contains(departureStation) && !departureStation.isEmpty()) {
				indexOfDepSt = i;
			}
			else if (routePoints.get(i).getStationName().contains(destinationStation) && !destinationStation.isEmpty()) {
				indexOfDestSt = i+1;
			}
		}
		routePoints = routePoints.subList(indexOfDepSt, indexOfDestSt);
		for(int i = 0; i < routePoints.size()-1; i++) {
			if( i < routePoints.size()) {
				routePoints.get(i).setDestinationStationName(routePoints.get(i+1).getStationName());
			}
		}
		
		return routePoints;
	}
}
