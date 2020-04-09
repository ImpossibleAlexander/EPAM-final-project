package ua.nure.kaplin.SummaryTask4.DAO.mysql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;

import ua.nure.kaplin.SummaryTask4.DAO.DaoInterfaceTrainStation;
import ua.nure.kaplin.SummaryTask4.db.DBManager;
import ua.nure.kaplin.SummaryTask4.db.entity.TrainStation;

public class DaoTrainStation implements DaoInterfaceTrainStation{

	
	private static final String INSERT_STATION = "INSERT INTO train_station (name) VALUE(?)";
	
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
			throw new Exception("Can_not_insert_station", e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	@Override
	public TrainStation findStationByName(String login) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TrainStation findStationById(int id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TrainStation> getStations() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
