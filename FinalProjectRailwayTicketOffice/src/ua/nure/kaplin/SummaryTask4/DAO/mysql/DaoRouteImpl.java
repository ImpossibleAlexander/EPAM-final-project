package ua.nure.kaplin.SummaryTask4.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.nure.kaplin.SummaryTask4.DAO.DaoRoute;
import ua.nure.kaplin.SummaryTask4.db.DBManager;
import ua.nure.kaplin.SummaryTask4.db.entity.Route;
import ua.nure.kaplin.SummaryTask4.exception.DBException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

import org.apache.log4j.Logger;

public class DaoRouteImpl implements DaoRoute{
	
	private static final Logger LOG = Logger.getLogger(DBManager.class);
	
	private static final String SQL_SELECT_ROUTE_BY_TRAIN_NUMBER = "SELECT train_number, arrive_datetime, depart_datetime, name, coupe, reserved_seat, common, coupe_price, reserved_seat_price, common_price, train.id, train_station.id, status_of_train\r\n"
			+ "FROM route_point\r\n" + "INNER JOIN train\r\n" + "ON train.id = route_point.train_id \r\n"
			+ "INNER JOIN train_station ON train_station.id = route_point.train_station_id WHERE train_number = ? ORDER BY arrive_datetime";

	private static final String SQL_UPDATE_ROUTE_POINTS = "UPDATE route_point SET arrive_datetime = ?, depart_datetime = ? WHERE train_id = ? AND train_station_id IN (SELECT id FROM train_station WHERE name = ?)";
	
	
	private static final String SQL_INSERT_TRAIN = "insert into train (train_number, coupe, reserved_seat, common, coupe_price, reserved_seat_price, common_price) \r\n" + 
			"values (?, ?, ?, ?, ?, ?, ?);";
	
	private static final String SQL_INSERT_DEPARTURE_AND_DESTINATION_STATIONS_OF_ROUTE = "insert into route_point (train_id, train_station_id, arrive_datetime, depart_datetime) values ((SELECT id FROM train WHERE train_number = ?), ?, ?, ?)";

	
	public List<Route> findRouteByTrainNumber(int trainNumber) throws Exception {
		List<Route> routes = new ArrayList<Route>();
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(SQL_SELECT_ROUTE_BY_TRAIN_NUMBER);
			preparedStatement.setInt(1, trainNumber);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				Route route = new Route();
				route.setTrainNumber(resultSet.getInt(1));
				route.setDestinationDateAndTime(resultSet.getString(2));
				route.setDepartureDateAndTime(resultSet.getString(3));
				route.setStationName(resultSet.getString(4));
				route.setCoupe(resultSet.getInt(5));
				route.setReservedSeat(resultSet.getInt(6));
				route.setCommon(resultSet.getInt(7));
				route.setCoupePrice(resultSet.getInt(8));
				route.setReservedSeatPrice(resultSet.getInt(9));
				route.setCommonPrice(resultSet.getInt(10));
				route.setTrainId(resultSet.getInt(11));
				route.setStationId(resultSet.getInt(12));
				System.out.println(resultSet.getString(13) + "    wafmmamvmamvmklamlfmlamdl-----------");
				route.setTrainStatus(resultSet.getString(13));
				routes.add(route);
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_SELECT_ROUTE_POINT_BY_TRAIN_NUMBER, e);
			throw new DBException(Messages.ERR_CANNOT_SELECT_ROUTE_POINT_BY_TRAIN_NUMBER, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return routes;
	}

	public boolean updateRoutePoints(Route route) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(SQL_UPDATE_ROUTE_POINTS);
			preparedStatement.setString(1, route.getDestinationDateAndTime());
			preparedStatement.setString(2, route.getDepartureDateAndTime());
			preparedStatement.setInt(3, route.getTrainId());
			preparedStatement.setString(4, route.getStationName());
			if (preparedStatement.executeUpdate() != 1) {
				return false;
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_UPDATE_ROUTE_POINTS, e);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_ROUTE_POINTS, e);
		}
		return true;
	}

	public void insertRoute(Route route) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatement2 = null;
		PreparedStatement preparedStatement3 = null;
		ResultSet resultSet = null;
		String date = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_INSERT_TRAIN);
			preparedStatement2 = connection.prepareStatement(SQL_INSERT_DEPARTURE_AND_DESTINATION_STATIONS_OF_ROUTE);
			preparedStatement3 = connection.prepareStatement(SQL_INSERT_DEPARTURE_AND_DESTINATION_STATIONS_OF_ROUTE);
			int k = 1;
			preparedStatement.setInt(k++, route.getTrainNumber());
			preparedStatement.setInt(k++, route.getCoupe());
			preparedStatement.setInt(k++, route.getReservedSeat());
			preparedStatement.setInt(k++, route.getCommon());
			preparedStatement.setInt(k++, route.getCoupePrice());
			preparedStatement.setInt(k++, route.getReservedSeatPrice());
			preparedStatement.setInt(k++, route.getCommonPrice());
			k = 1;
			preparedStatement2.setInt(k++, route.getTrainNumber());
			preparedStatement2.setInt(k++, route.getStationId());
			preparedStatement2.setString(k++, date);
			preparedStatement2.setString(k++, route.getDepartureDateAndTime());
			k = 1;
			preparedStatement3.setInt(k++, route.getTrainNumber());
			preparedStatement3.setInt(k++, route.getDestinationStationId());
			preparedStatement3.setString(k++, route.getDestinationDateAndTime());
			preparedStatement3.setString(k++, date);
			
			preparedStatement.executeUpdate();
			preparedStatement2.executeUpdate();
			preparedStatement3.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOG.error(Messages.ERR_CANNOT_INSERT_ROUTE, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_ROUTE, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	public void insertRoutePoint(Route route) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String date = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_INSERT_DEPARTURE_AND_DESTINATION_STATIONS_OF_ROUTE);
				
			int k = 1;
			preparedStatement.setInt(k++, route.getTrainNumber());
			preparedStatement.setInt(k++, route.getStationId());
			
			if(route.getDestinationDateAndTime() == null || route.getDestinationDateAndTime().isEmpty()) {
				preparedStatement.setString(k++, date);
				System.out.println("preparedStatement.setString(k++, date)");
			}
			else {
				preparedStatement.setString(k++, route.getDestinationDateAndTime());
				System.out.println("preparedStatement.setString(k++, route.getDestinationDateAndTime());");
			}
			if(route.getDepartureDateAndTime() == null || route.getDepartureDateAndTime().isEmpty()) {
				preparedStatement.setString(k++, date);
				System.out.println("preparedStatement.setString(k++, date);");
			}
			else {
				preparedStatement.setString(k++, route.getDepartureDateAndTime());
				System.out.println("preparedStatement.setString(k++, route.getDepartureDateAndTime());");
			}			
			preparedStatement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			db.rollback(connection);
			LOG.error(Messages.ERR_CANNOT_INSERT_ROUTE_POINT, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_ROUTE_POINT, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}


}
