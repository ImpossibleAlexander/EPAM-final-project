package ua.nure.kaplin.SummaryTask4.DAO;

import ua.nure.kaplin.SummaryTask4.db.entity.Ticket;
import ua.nure.kaplin.SummaryTask4.db.entity.User;

public interface DaoTicket {
	void insertTicket(Ticket ticket, User user) throws Exception;
}
