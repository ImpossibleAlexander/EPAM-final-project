package ua.nure.kaplin.SummaryTask4.DAO;

import java.util.List;

import ua.nure.kaplin.SummaryTask4.db.entity.Ticket;
import ua.nure.kaplin.SummaryTask4.db.entity.User;

public interface DaoUser {
	void insertUser(User user) throws Exception;
	public User findUserByLogin(String login) throws Exception;
	public List<Ticket> findUserTicketsByUserId(long id) throws Exception;
}
