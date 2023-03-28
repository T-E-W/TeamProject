package serverCommunications;

import ocsf.server.AbstractServer;
import database.*;
import ocsf.server.ConnectionToClient;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class HangmanServer extends AbstractServer
{
	private JTextArea log;
	private JLabel status;
	private DatabaseFile accounts = new DatabaseFile();
	// database object 

	public HangmanServer()
	{
		super(8300);
	}

	public void setLog(JTextArea log)
	{
		this.log = log;
	}

	public JTextArea getLog()
	{
		return log;
	}

	public void setStatus(JLabel status)
	{
		this.status = status;
	}

	public JLabel getStatus()
	{
		return status;
	}



	protected void serverStarted() 
	{
		System.out.println("Server Started");
		log.setText("Server Started\n");
		status.setText("<html>Status: <font color='green'>LISTENING</font></html>");
	}

	protected void serverStopped() 
	{
		System.out.println("Stopped button Pressed");
		log.append("Server stopped Accepting New Clients - Press Listen to Start Accepting New Clients.\n");
		status.setText("<html>Status: <font color='red'> STOPPED </font></html>");
	}

	protected void serverClosed() 
	{
		System.out.println("Closed Button Pressed");
		log.append("Server and all current clients are closed - Press Listen To Restart.\n");
		status.setText("<html>Status: <font color='red'> CLOSED </font></html>");
	}


	protected void clientConnected(ConnectionToClient client) 
	{

		try {
			client.sendToClient("INITIAL username:" + "client-" + client.getId());
			log.append("Client " + "client-" + client.getId() + " Connected\n");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	@Override
	protected void handleMessageFromClient(Object arg0, ConnectionToClient client)
	{

		// If the argument is login data, do this below
		System.out.println(arg0);
		if (arg0 instanceof clientCommunications.LoginData)
		{
			clientCommunications.LoginData loginData = (clientCommunications.LoginData) arg0;

			System.out.println("Login Data Recieved By Server.");
			Object contents;
			// test here to see if data matches database
			if (accounts.isValid(loginData.getUsername(),loginData.getPassword())) 
			{
				log.append("Good Account Data: " + loginData.getUsername() + ":" + loginData.getPassword() + "\n");
				contents = "loginaccepted";
			}
			else
			{
				log.append("Bad Login Data: " + loginData.getUsername() + ":" + loginData.getPassword() + "\n");
				contents = "logindenied";
			}
			
			
			try {
				client.sendToClient(contents);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (arg0 instanceof CreateAccData)

		{
			System.out.println("Create Data Recieved By Server.");
			CreateAccData createData = (CreateAccData)arg0;
			//write new data to database
			Object contents;
			if (accounts.createAcc(createData.getUsername(), createData.getPassword()))
			{
				log.append("Account Created: " + createData.getUsername() + ":" + createData.getPassword() + "\n");
				contents = "createaccepted";
			}
			else
			{
				log.append("Account NOT Created: " + createData.getUsername() + ":" + createData.getPassword() + "\n");
				contents = "createdenied";

			}

			//send results to client
			try {
				client.sendToClient(contents);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}

		}
		else if (arg0 instanceof ContactData)
		{
			ContactData contactData = (ContactData)arg0;
			//write new data to database
			String contact = contactData.getUsername() + ":" + contactData.getPassword();

			System.out.println("Adding (" + contact + ") to contact list.");
		}

		else 

		{
			System.out.println("Client Message sent to Server");

			String contents = arg0.toString();

			log.append("Client " + client.getId() + ": " + contents + "\n");

			try {
				client.sendToClient(contents);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	protected void listeningException(Throwable exception) 
	{
		//Display info about the exception
		System.out.println("Listening Exception:" + exception);
		exception.printStackTrace();
		System.out.println(exception.getMessage());

		if (!this.isListening())
		{
			log.append("Server not Listening\n");
			status.setText("Not Connected");
			status.setForeground(Color.RED);
		}

	}





}
