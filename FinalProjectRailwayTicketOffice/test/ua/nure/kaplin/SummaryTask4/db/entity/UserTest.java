package ua.nure.kaplin.SummaryTask4.db.entity;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class UserTest {
	private User user;
	public UserTest() {
		user = new User();
		user.setId(1);
		user.setLogin("cl");
		user.setPassword("1");
		user.setEmail("test@ukr.net");
		user.setRoleId(1);
		user.setFirstName("cl1");
		user.setLastName("cl1");
	}
	
	@Test
	public void getUserId() {
		assertTrue(user.getId() == 1);
	}
	@Test
	public void getUserRoleId() {
		assertTrue(user.getRoleId() == 1);
	}
	@Test
	public void getUserLogin() {
		assertTrue("cl".equals(user.getLogin()));
	}
	@Test
	public void getUserPassword() {
		assertTrue("1".equals(user.getPassword()));
	}
	@Test
	public void getUserEmail() {
		assertTrue("test@ukr.net".equals(user.getEmail()));
	}
	@Test
	public void getFirstName() {
		assertTrue("cl1".equals(user.getFirstName()));
	}
	@Test
	public void getLastName() {
		assertTrue("cl1".equals(user.getLastName()));
	}
}
