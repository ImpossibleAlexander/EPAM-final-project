package ua.nure.kaplin.SummaryTask4.db.bean;

import java.util.List;

import ua.nure.kaplin.SummaryTask4.DAO.mysql.DaoUser;
import ua.nure.kaplin.SummaryTask4.db.entity.Ticket;
import ua.nure.kaplin.SummaryTask4.db.entity.User;

public class UserOrderBean {
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Ticket> findUserTickets() {
		List<Ticket> tickets = null;
		DaoUser dao = new DaoUser();
		System.out.println(getUser().getId());
		try {
			tickets = dao.findUserTicketsByUserId(getUser().getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tickets;
	}
}
