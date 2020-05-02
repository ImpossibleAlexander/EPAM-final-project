package ua.nure.kaplin.SummaryTask4.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.DAO.DaoTrain;
import ua.nure.kaplin.SummaryTask4.db.DBManager;
import ua.nure.kaplin.SummaryTask4.db.entity.Train;
import ua.nure.kaplin.SummaryTask4.exception.DBException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

public class DaoTrainImpl implements DaoTrain{
	
	private static final Logger LOG = Logger.getLogger(DBManager.class);

	private static final String SQL_SELECT_TRAIN_BY_STATION_NAME = "SELECT train_number, count(1) as 'repeats'\r\n"
			+ "	FROM route_point\r\n" + "	INNER JOIN train\r\n" + "	ON train.id = route_point.train_id \r\n"
			+ "	INNER JOIN train_station ON train_station.id = route_point.train_station_id \r\n"
			+ "	WHERE name = ? OR name = ? GROUP BY train_number;";

	private static final String SQL_SELECT_TRAIN_BY_NUMBER = "SELECT * FROM train WHERE train_number = ?";
	
	private static final String SQL_UPDATE_TRAIN_BY_NUMBER = "UPDATE train SET train_number = ?, coupe = ?, reserved_seat = ?, common = ?, coupe_price = ?, reserved_seat_price = ?, common_price = ?, status_of_train = ? WHERE id = ?";

	@Override
	public List<Train> findTrainNumberByStationName(String departureStation, String arriveStation) throws Exception {
		List<Train> trains = new ArrayList<Train>();
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(SQL_SELECT_TRAIN_BY_STATION_NAME);
			preparedStatement.setString(1, departureStation);
			preparedStatement.setString(2, arriveStation);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				if (resultSet.getInt(2) > 1) {
					Train train = new Train();
					train.setTrainNumber(resultSet.getInt(1));
					trains.add(train);
				}
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_SELECT_TRAIN_NUMBER_BY_STATION_NAME, e);
			throw new Exception(Messages.ERR_CANNOT_SELECT_TRAIN_NUMBER_BY_STATION_NAME, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return trains;
	}

	@Override
	public Train findTrainByNumber(int number) throws Exception {
		DBManager db = DBManager.getInstance();
		Train train = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(SQL_SELECT_TRAIN_BY_NUMBER);
			preparedStatement.setInt(1, number);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				train = new Train();
				train.setId(resultSet.getInt(2));
				train.setTrainNumber(resultSet.getInt(2));
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_CANNOT_SELECT_TRAIN_BY_TRAIN_NUMBER, e);
			throw new Exception(Messages.ERR_CANNOT_SELECT_TRAIN_BY_TRAIN_NUMBER, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return train;
	}

	@Override
	public boolean updateTrain(Train train) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(SQL_UPDATE_TRAIN_BY_NUMBER);
			int k = 1;
			preparedStatement.setInt(k++, train.getTrainNumber());
			preparedStatement.setInt(k++, train.getCoupe());
			preparedStatement.setInt(k++, train.getReservedSeat());
			preparedStatement.setInt(k++, train.getCommon());
			preparedStatement.setInt(k++, train.getCoupePrice());
			preparedStatement.setInt(k++, train.getReservedSeatPrice());
			preparedStatement.setInt(k++, train.getCommonPrice());
			preparedStatement.setString(k++, train.getTrainStatus());
			preparedStatement.setInt(k++, train.getId());
			if (preparedStatement.executeUpdate() != 1) {
				return false;
			}
		} catch (SQLException e) {
			LOG.error(Messages.ERR_UPDATE_TRAIN, e);
			throw new DBException(Messages.ERR_UPDATE_TRAIN, e);
		}
		return true;
	}
	
}
