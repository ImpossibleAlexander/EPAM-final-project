package ua.nure.kaplin.SummaryTask4.DAO;

import java.util.List;

import ua.nure.kaplin.SummaryTask4.db.entity.Route;

public interface DaoRoute {
	List<Route> findRouteByTrainNumber(int trainNumber) throws Exception;
	boolean updateRoutePoints(Route route) throws Exception;
	void insertRoute(Route route) throws Exception;
	void insertRoutePoint(Route route) throws Exception;
}
