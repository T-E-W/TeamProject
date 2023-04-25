package databaseTests;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.*;
import org.junit.Test;

public class DatabaseTest {

	String[] users = {"jsmith@uca.edu","msmith@uca.edu","tjones@yahoo.com","jjones@yahoo.com"};
	String[] passwords = {"hello123","pass123","123456","hello1234"};

	private Database db; 
	private int rando;

	@Before
	public void setUp() throws Exception 
	{
		db = new Database(); 
		db.setConnection("testHangman/db.properties");
		rando = ((int)Math.random()*users.length); 
	}

	@Test
	public void testSetConnection() throws FileNotFoundException
	{
		//1. call setConnection() 
		db.setConnection("testHangman/db.properties");

		//2. call getConnection() and return a Connection object (conn)
		Connection conn = db.getConnection();

		//3. make sure Connection object returned by getConnection is not null

		assertNotNull("Check setConnection", conn); //Place object here 

	}

	@Test
	public void testQuery() throws IOException 
	{
		int i = 0;
		for (i = 0; i < users.length; i++)
		{
			//db = new Database(); 
			db.setConnection("testHangman/db.properties");
			Connection conn = db.getConnection();

			//Use Random # to extract username/ and expected password		
			String username = users[rando]; 
			String expected = passwords[rando];

			//get actual result (invoke query with username
			ArrayList<String> results = db.query("select aes_decrypt(password,'key') from Users Where username = '" + 
					username + "';");

			String actual = results.get(0).replace(",","");

			//compare expected with actual using assertEquals
			assertEquals("testQuery: input " + username, expected, actual);	
		}

	}

	@Test
	public void testExecuteDML() throws SQLException
	{
		db.setConnection("testHangman/db.properties");
		Connection conn = db.getConnection();
		String username = "cbaugh@cub.uca.edu";
		String password = "555555";
		
		db.executeDML("insert into users values('" + username + "',aes_encrypt('" +password+"','key'))");
	}

}

