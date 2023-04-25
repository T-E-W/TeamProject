package hangmanTests;

import static org.junit.Assert.*;
import org.junit.*;
import serverCommunications.*;
import clientCommunications.*;
import database.*;

public class HangmanServerTest {
	private Database db;
	private HangmanServer server;

	@Before
	public void setUp() {
		db = new Database();
		server = new HangmanServer();
	}



	@Test
	public void testIsRunning() {
		assertNotNull(server.isRunning());
	}

	@Test (expected = Exception.class)
	public void testServerStarted() {
		server.run();
		server.serverStarted();
	}

	@Test (expected = Exception.class)
	public void testServerStopped() {
		server.serverStopped();
	}

	@Test (expected = Exception.class)
	public void testServerClosed() {
		server.serverClosed();
	}

	@Test (expected = Exception.class)
	public void testClientConnected() {
		server.clientConnected(null);
	}
	
	@Test
	public void testSetDatabase() {
		server.setDatabase(db);
	}
	
	@Test
	public void testHandleMessageFromClient() {

	}
}