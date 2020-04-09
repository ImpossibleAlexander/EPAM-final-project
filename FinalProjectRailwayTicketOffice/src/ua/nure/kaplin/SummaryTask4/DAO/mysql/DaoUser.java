package ua.nure.kaplin.SummaryTask4.DAO.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ua.nure.kaplin.SummaryTask4.db.DBManager;
import ua.nure.kaplin.SummaryTask4.db.entity.User;
import ua.nure.kaplin.SummaryTask4.exception.DBException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

public class DaoUser {
	
	private static final String INSERT_USER = "INSERT INTO users (login, email, password, first_name, last_name, role_id) VALUE(?,?,?,?,?,?)";
	private static final String SELECT_USER_BY_LOGIN = "select * from users WHERE login = ?";
	
	
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
			throw new Exception("Can_not_insert_user", e);
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
		} catch (SQLException ex) {
			db.rollback(connection);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_USER_BY_LOGIN, ex);
		} finally {
			db.close(connection, preparedStatement, resultSet);
		}
		return user;
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
	
}
