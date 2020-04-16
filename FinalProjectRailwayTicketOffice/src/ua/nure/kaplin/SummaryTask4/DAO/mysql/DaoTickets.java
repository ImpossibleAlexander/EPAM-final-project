package ua.nure.kaplin.SummaryTask4.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nure.kaplin.SummaryTask4.db.DBManager;
import ua.nure.kaplin.SummaryTask4.db.entity.Ticket;

public class DaoTickets {
	private static final String SQL_INSERT_TICKET = "INSERT INTO tickets (train_number,"
			+ "ticket_number, destinationStation, departureStation, arrive_datetime, depart_datetime, place, price) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_INSERT_USER_TICKET = "INSERT INTO user_tickets (user_id, tickets_id) VALUES (?, (SELECT id FROM tickets WHERE ticket_number = ?))";
	
	public void insertTicket(Ticket ticket) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_INSERT_TICKET);
			
			int k = 1;
			preparedStatement.setInt(k++, ticket.getTrainNumber());
			preparedStatement.setInt(k++, ticket.getTicketNumber());
			preparedStatement.setString(k++, ticket.getDestinationStation());
			preparedStatement.setString(k++, ticket.getDepartureStation());
			preparedStatement.setString(k++, ticket.getDestinationDateAndTime());
			preparedStatement.setString(k++, ticket.getDepartureDateAndTime());
			preparedStatement.setString(k++, ticket.getPlace());
			preparedStatement.setInt(k++, ticket.getPrice());
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			throw new Exception("Can_not_insert_ticket", e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
	
	
	public void insertUserTicket(int userId, int ticketNumber) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(SQL_INSERT_USER_TICKET);
			
			int k = 1;
			preparedStatement.setInt(k++, userId);
			preparedStatement.setInt(k++, ticketNumber);
			
			preparedStatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			db.rollback(connection);
			throw new Exception("Can_not_insert_user_ticket", e);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
	}
}
