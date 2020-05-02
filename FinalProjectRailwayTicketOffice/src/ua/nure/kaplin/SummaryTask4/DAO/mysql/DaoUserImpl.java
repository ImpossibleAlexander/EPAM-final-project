package ua.nure.kaplin.SummaryTask4.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.DAO.DaoUser;
import ua.nure.kaplin.SummaryTask4.db.DBManager;
import ua.nure.kaplin.SummaryTask4.db.entity.Ticket;
import ua.nure.kaplin.SummaryTask4.db.entity.Train;
import ua.nure.kaplin.SummaryTask4.db.entity.User;
import ua.nure.kaplin.SummaryTask4.exception.DBException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

public class DaoUserImpl implements DaoUser{
	
	private static final Logger LOG = Logger.getLogger(DBManager.class);
	
	private static final String INSERT_USER = "INSERT INTO users (login, email, password, first_name, last_name, role_id) VALUE(?,?,?,?,?,?)";
	
	private static final String SELECT_USER_BY_LOGIN = "select * from users WHERE login = ?";
	
	private static final String SELECT_USER_TICKETS_BY_USER_ID= "SELECT tickets.id, ticket_number, train_number, departureStation, destinationStation, arrive_datetime, depart_datetime, place,price FROM tickets WHERE user_id = ?";
	
	
	public void insertUser(User user) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(INSERT_USER);
			int k = 1;
			preparedStatement.setString(k++, user.getLogin());
			preparedStatement.setString(k++, user.getEmail());
			preparedStatement.setString(k++, user.getPassword());
			preparedStatement.setString(k++, user.getFirstName());
			preparedStatement.setString(k++, user.getLastName());
			preparedStatement.setInt(k++, user.getRoleId());
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOG.error(Messages.ERR_CANNOT_INSERT_USER, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_USER, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	public User findUserByLogin(String login) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		User user = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SELECT_USER_BY_LOGIN);
			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = extractUser(resultSet);
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOG.error(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return user;
	}
	
	public List<Ticket> findUserTicketsByUserId(long id) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Ticket ticket = null;
		List <Ticket> tickets = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SELECT_USER_TICKETS_BY_USER_ID);
			preparedStatement.setLong(1, id);
			resultSet = preparedStatement.executeQuery();
			
			tickets = new ArrayList<Ticket>();
			
			while (resultSet.next()) {
				ticket = extractTicket(resultSet);
				tickets.add(ticket);
			}
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			LOG.error(Messages.ERR_CANNOT_FIND_USER_BY_USER_ID, e);
			throw new DBException(Messages.ERR_CANNOT_FIND_USER_BY_USER_ID, e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return tickets;
	}
	
	private User extractUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setId(resultSet.getLong(1));
		user.setLogin(resultSet.getString(2));
		user.setEmail(resultSet.getString(3));
		user.setPassword(resultSet.getString(4));
		user.setFirstName(resultSet.getString(5));
		user.setLastName(resultSet.getString(6));
		user.setRoleId(resultSet.getInt(7));
		return user;
	}
	
	private Ticket extractTicket(ResultSet resultSet) throws SQLException {
		Ticket ticket = new Ticket();
		ticket.setId(resultSet.getInt(1));
		ticket.setTicketNumber(resultSet.getInt(2));
		ticket.setTrainNumber(resultSet.getInt(3));
		ticket.setDepartureStation(resultSet.getString(4));
		ticket.setDestinationStation(resultSet.getString(5));
		ticket.setDestinationDateAndTime(resultSet.getString(6));
		ticket.setDepartureDateAndTime(resultSet.getString(7));
		ticket.setPlace(resultSet.getString(8));
		ticket.setPrice(resultSet.getInt(9));
		return ticket;
	}
}
