package ua.nure.kaplin.SummaryTask4.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.DelegatingCallableStatement;
import ua.nure.kaplin.SummaryTask4.exception.DBException;
import ua.nure.kaplin.SummaryTask4.exception.Messages;

public class DBManager {

	private static final Logger LOGGER = Logger.getLogger(DBManager.class);
	private static DBManager instance;
	private DataSource dataSource;
	
	
	public static synchronized DBManager getInstance() throws Exception {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}
	
	public DBManager() throws Exception {
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context) initContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/final_project");
			LOGGER.trace("Data source ==> " + dataSource);
		} catch (NamingException ex) {
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_DATA_SOURCE, ex);
		}
	}
	
	public Connection getConnection() throws Exception {
		Connection connnection = null;
		try {
			connnection = dataSource.getConnection();
		} catch (SQLException ex) {
			LOGGER.error(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
			throw new DBException(Messages.ERR_CANNOT_OBTAIN_CONNECTION, ex);
		}
		return connnection;
	}
	
	public void close(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException ex) {
				LOGGER.error(Messages.ERR_CANNOT_CLOSE_CONNECTION, ex);
			}
		}
	}
	
	public void close(DelegatingCallableStatement cstmt) {
		if (cstmt != null) {
			try {
				cstmt.close();
			} catch (SQLException ex) {
				LOGGER.error(Messages.ERR_CANNOT_CLOSE_DELEGATING_CALLABLE_STATEMENT, ex);
			}
		}
	}

	public void close(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException ex) {
				LOGGER.error(Messages.ERR_CANNOT_CLOSE_STATEMENT, ex);
			}
		}
	}

	public void close(ResultSet resultSet) {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException ex) {
				LOGGER.error(Messages.ERR_CANNOT_CLOSE_RESULTSET, ex);
			}
		}
	}
	
	public void close(Connection connection, Statement statement, ResultSet resultSet) {
		close(resultSet);
		close(statement);
		close(connection);
	}

	public void close(Connection connection, DelegatingCallableStatement cstmt) {
		close(cstmt);
		close(connection);
	}
	
	public void rollback(Connection connection) {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				LOGGER.error(Messages.ERR_CANNOT_ROLLBACK_TRANSACTION, ex);
			}
		}
	}
}