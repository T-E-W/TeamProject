package clientCommunications;

import java.io.Serializable;

public class User implements Serializable 
{
	// Private data fields for the username and password.
	private String username;
	private String password;
	private Long userID;
	private String word;


	// Getters for the username and password.
	public String getUsername()
	{
		return username;
	}
	public String getPassword()
	{
		return password;
	}
	public Long getID()
	{
		return userID;
	}
	public String getWord()
	{
		return word;
	}

	// Setters for the username and password.
	public void setUsername(String username)
	{
		this.username = username;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public void setID(Long id)
	{
		this.userID = id;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	// Constructor that initializes the username and password.
	public User(String username, String password, Long id)
	{
		setUsername(username);
		setPassword(password);
		setID(id);
	}
	public User() {
		// TODO Auto-generated constructor stub
	}

}