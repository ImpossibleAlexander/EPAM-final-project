package ua.nure.kaplin.SummaryTask4.db.entity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.booleanThat;

import org.junit.Assert;
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

	@Test
	public void equalsTest() {
		User user2 = user;
		assertTrue(user.equals(user2));
		assertFalse(user.equals(null));

		user2 = new User();
		user2.setLogin("log");
		assertFalse(user.equals(user2));

		user2.setLogin(null);
		assertFalse(user.equals(user2));

		user.setLogin(null);
		user2.setLogin("log");
		assertFalse(user.equals(user2));

		TrainStation station = new TrainStation();
		assertFalse(user.equals(station));

		user.setLogin("log");
		user2.setLogin("log");
		assertTrue(user.equals(user2));
	}

	@Test
	public void hashCodeTest1() {
		assertTrue(user.hashCode() == 3208);
	}
	
	@Test
	public void hashCodeTest2() {
		user.setLogin(null);
		assertTrue(user.hashCode() == 31);
	}

	@Test
	public void toStringTest() {
		String expected = "User [login=" + user.getLogin() + ", firstName=" + user.getFirstName() + ", lastName="
				+ user.getLastName() + ", roleId=" + user.getRoleId() + "]";
		Assert.assertEquals(expected, user.toString());
	}
}
