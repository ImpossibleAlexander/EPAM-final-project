package ua.nure.kaplin.SummaryTask4.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import ua.nure.kaplin.SummaryTask4.DAO.DaoTicket;
import ua.nure.kaplin.SummaryTask4.db.DBManager;
import ua.nure.kaplin.SummaryTask4.db.entity.Ticket;
import ua.nure.kaplin.SummaryTask4.db.entity.User;
import ua.nure.kaplin.SummaryTask4.exception.DBException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

public class DaoTicketsImpl implements DaoTicket {

	private static final Logger LOG = Logger.getLogger(DBManager.class);

	private static final String SQL_INSERT_TICKET = "INSERT INTO tickets (train_number,"
			+ "ticket_number, destinationStation, departureStation, arrive_datetime, depart_datetime, place, price, user_id) VALUES (?,?,?,?,?,?,?,?,?)";

	public void insertTicket(Ticket ticket, User user) throws Exception {
		DBManager db = DBManager.getInstance();
		Connection connection = null;
		PreparedStatement preparedStatement2 = null;
		ResultSet resultSet = null;
		try {
			connection = db.getConnection();
			connection.setAutoCommit(false);

			preparedStatement2 = connection.prepareStatement(SQL_INSERT_TICKET);
			int k = 1;
			preparedStatement2.setInt(k++, ticket.getTrainNumber());
			preparedStatement2.setInt(k++, ticket.getTicketNumber());
			preparedStatement2.setString(k++, ticket.getDestinationStation());
			preparedStatement2.setString(k++, ticket.getDepartureStation());
			preparedStatement2.setString(k++, ticket.getDestinationDateAndTime());
			preparedStatement2.setString(k++, ticket.getDepartureDateAndTime());
			preparedStatement2.setString(k++, ticket.getPlace());
			preparedStatement2.setInt(k++, ticket.getPrice());
			preparedStatement2.setLong(k++, user.getId());
			preparedStatement2.executeUpdate();
			connection.commit();

		} catch (SQLException e) {
			db.rollback(connection);
			LOG.error(Messages.ERR_CANNOT_INSERT_TICKET, e);
			throw new DBException(Messages.ERR_CANNOT_INSERT_TICKET, e);
		} finally {
			db.close(connection, preparedStatement2, resultSet);
		}
	}
}
