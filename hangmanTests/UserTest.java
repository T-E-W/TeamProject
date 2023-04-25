package hangmanTests;

import org.junit.*;

import clientCommunications.User;

public class UserTest {
	private User user;
	
	@Before
	public void setUp() {
		user = new User(null, null, null);
	}

	@Test
	public void testSetUsername() {
		user.setUsername("Dog");
	}
	
	@Test
	public void testSetPassword() {
		user.setPassword("Banana");
	}
	
	@Test
	public void testGetUsername() {
		user.setUsername("Dog");

		assert(user.getUsername().equals("Dog"));
	}
	
	@Test
	public void testGetPassword() {
		user.setPassword("Banana");

		assert(user.getPassword().equals("Banana"));
	}

}