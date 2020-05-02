package ua.nure.kaplin.SummaryTask4.DAO.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ua.nure.kaplin.SummaryTask4.DAO.DaoTrainStation;
import ua.nure.kaplin.SummaryTask4.db.DBManager;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;
import ua.nure.kaplin.SummaryTask4.exception.DBException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

public class DaoTrainStationImpl implements DaoTrainStation {

	private static final Logger LOG = Logger.getLogger(DBManager.class);
	
	private static final String INSERT_STATION = "INSERT INTO train_station (name) VALUE(?)";
	
	private static final String SELECT_STATIONS = "SELECT * FROM train_station";
	
	private static final String SELECT_STATION_BY_NAME = "SELECT * FROM train_station WHERE name = ?";
	
	private static final String SQL_UPDATE_TRAIN_STATION = "UPDATE train_station SET name=? WHERE name=?";
	
	private static final String SQL_DELETE_TRAIN_STATION = "DELETE FROM train_station WHERE name =?";

	public void insertStation(TrainStation station) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(INSERT_STATION);
			int k = 1;
			preparedStatement.setString(k++, station.getStationName());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOG.error(Messages.ERR_CANNOT_INSERT_STATION, e);
			throw new Exception(Messages.ERR_CANNOT_INSERT_STATION, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}

	@Override
	public TrainStation findStationByName(String name) throws Exception {
		TrainStation station = new TrainStation();
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SELECT_STATION_BY_NAME);
			preparedStatement.setString(1, name);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				station.setId(resultSet.getInt(1));
				station.setStationName(resultSet.getString(2));
			}
			
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_SELECT_STATION_BY_NAME, e);
			throw new DBException(Messages.ERR_CANNOT_SELECT_STATION_BY_NAME, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return station;
	}

	@Override
	public TrainStation findStationById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean updateTrainStation(TrainStation trainStationBeforeUpdate, TrainStation trainStationAfterUpdate) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAIN_STATION);
			preparedStatement.setString(1, trainStationAfterUpdate.getStationName());
			preparedStatement.setString(2, trainStationBeforeUpdate.getStationName());
			if (preparedStatement.executeUpdate() != 1) {
				connection.commit();
				return false;
			}
			connection.commit();
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_UPDATE_STATION, e);
			throw new DBException(Messages.ERR_CANNOT_UPDATE_STATION, e);
		}
		 finally {
				db.close(connection, preparedStatement, resultSet);}
		return true;
	}

	@Override
	public List<TrainStation> getStations() throws Exception {
		List<TrainStation> stations = new ArrayList<TrainStation>();
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			statement = connection.createStatement();
			resultSet = statement.executeQuery(SELECT_STATIONS);
			connection.commit();
			while (resultSet.next()) {
				TrainStation station = new TrainStation();
				station.setStationName(resultSet.getString(2));
				stations.add(station);
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_SELECT_STATIONS, e);
			throw new Exception(Messages.ERR_CANNOT_SELECT_STATIONS, e);
		} finally {
			db.close(connection, statement, resultSet);
		}
		return stations;
	}
	
	public boolean deleteTrainStation(String name) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		boolean status = false;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_DELETE_TRAIN_STATION);
			preparedStatement.setString(1, name);
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			status = false;
			LOG.error(Messages.ERR_DELETE_STATION, e);
			throw new DBException(Messages.ERR_DELETE_STATION, e);
		}
		 finally {
				db.close(connection, preparedStatement, resultSet);
		 }
		return status;
	}
}
