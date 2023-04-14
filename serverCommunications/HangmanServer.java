package serverCommunications;

import java.awt.*;
import javax.swing.*;

import database.Database;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class HangmanServer extends AbstractServer
{
	// Data fields for this chat server.
	private JTextArea log;
	private JLabel status;
	private boolean running = false;
	//private DatabaseFile database = new DatabaseFile();
	//Create the database object.
	private Database database;// = new Database();
	private String dml;
	private GameData gData;

	// Constructor for initializing the server with default settings.
	public HangmanServer()
	{
		super(12345);
		this.setTimeout(500);
	}

	// Getter that returns whether the server is currently running.
	public boolean isRunning()
	{
		return running;
	}

	// Setters for the data fields corresponding to the GUI elements.
	public void setLog(JTextArea log)
	{
		this.log = log;
	}
	public void setStatus(JLabel status)
	{
		this.status = status;
	}
	public void setDatabase(Database database)
	{
		this.database = database;
	}

	// When the server starts, update the GUI.
	public void serverStarted()
	{
		running = true;
		status.setText("Listening");
		status.setForeground(Color.GREEN);
		log.append("Server started\n");
	}

	// When the server stops listening, update the GUI.
	public void serverStopped()
	{
		status.setText("Stopped");
		status.setForeground(Color.RED);
		log.append("Server stopped accepting new clients - press Listen to start accepting new clients\n");
	}

	// When the server closes completely, update the GUI.
	public void serverClosed()
	{
		running = false;
		status.setText("Close");
		status.setForeground(Color.RED);
		log.append("Server and all current clients are closed - press Listen to restart\n");
	}

	// When a client connects or disconnects, display a message in the log.
	public void clientConnected(ConnectionToClient client)
	{
		log.append("Client " + client.getId() + " connected\n");
	}

	// When a message is received from a client, handle it.
	public void handleMessageFromClient(Object arg0, ConnectionToClient arg1)
	{
		// If we received LoginData, verify the account information.
		if (arg0 instanceof clientCommunications.LoginData)
		{
			// Check the username and password with the database.
			clientCommunications.LoginData data = (clientCommunications.LoginData) arg0;
			
			//LoginData data = (LoginData) arg0;
			Object result = "";
			dml = "select username, aes_decrypt(password,'key') from User Where username = '" + 
					data.getUsername() + "'and aes_decrypt(password, 'key')='"+ data.getPassword()+"'";

			// Do the query.
			ArrayList<String> results = database.query(dml);

			// Print the result.
			if (results != null)
			{
				//for (String row : results)
				//{
				//	System.out.println(row);  
				//}
				result = "LoginSuccessful";
				log.append("Client " + arg1.getId() + " successfully logged in as " + data.getUsername() + "\n");

			}
			else
			{
				System.out.println("Error executing query.");
				result = new Error("The username and password are incorrect.", "Login");
				log.append("Client " + arg1.getId() + " failed to log in\n");
			}

			// Send the result to the client.
			try
			{
				arg1.sendToClient(result);
			}
			catch (IOException e)
			{
				return;
			}
		}

		// If we received CreateAccountData, create a new account.
		else if (arg0 instanceof clientCommunications.CreateAccountData)
		{
			// Try to create the account.
			CreateAccountData data = (CreateAccountData)arg0;
			Object result = "";

			//create the dml statement for the insert
			dml = "insert into user values('" + data.getUsername() + "',aes_encrypt('" +data.getPassword()+"','key'))";

			// Run the DML.
			try
			{
				database.executeDML(dml);
				result = "CreateAccountSuccessful";
				log.append("Client " + arg1.getId() + " created a new account called " + data.getUsername() + "\n");
			}
			catch(SQLException sql)
			{
				System.out.println("Error executing DML.");
				result = new Error("The username is already in use.", "CreateAccount");
				log.append("Client " + arg1.getId() + " failed to create a new account\n");
			}

			// Send the result to the client.
			try
			{
				arg1.sendToClient(result);
			}
			catch (IOException e)
			{
				return;
			}
		}
		else if (arg0 instanceof String)
		{
			String fromClient = (String) arg0;
			if(fromClient.contains("Guess:"))
			{
				// if this string is a guess, we'll save it under guess
				String guess = fromClient.replace("Guess:","");
				
				if(guess.length() ==1) 
				{
					gData.setGuessedLetters(guess);
				}
				else if (guess.length() > 1) 
				{
					gData.guessWord(guess);
				}
				
				
			}
		}
	}

	// Method that handles listening exceptions by displaying exception information.
	public void listeningException(Throwable exception) 
	{
		running = false;
		status.setText("Exception occurred while listening");
		status.setForeground(Color.RED);
		log.append("Listening exception: " + exception.getMessage() + "\n");
		log.append("Press Listen to restart server\n");
	}
}
