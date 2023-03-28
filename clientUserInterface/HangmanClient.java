package clientUserInterface;

import javax.swing.*;
import clientCommunications.*;
import ocsf.client.AbstractClient;
//import ocsf.server.AbstractServer;

/**
 * Chat Client Class, Extends AbstractClient from OCSF
 * @author timew
 *
 */
public class HangmanClient extends AbstractClient{

	private LoginControl loginControl;
	private CreateAccControl createControl;

	/**
	 * ChatClient Constructor (Parameterized)
	 * @param host
	 * @param port
	 */
	public HangmanClient(String host, int port)
	{
		super(host, port);
	}

	/**
	 * ChatClient Constructor (Default)
	 */
	public HangmanClient() 
	{		
		super("localhost", 8300);
	}
	/**
	 * Set the LoginControl object in chatclient
	 * @param loginControl
	 */
	public void setLoginControl(LoginControl loginControl)
	{
		this.loginControl = loginControl;
	}

	/**
	 * Set the CreateControl object instantiated in chatclient
	 * @param cc
	 */
	public void setCreateControl(CreateAccControl cc)
	{
		this.createControl = cc;
	}

	/**
	 *  Handle Messages From Server
	 *  Object is message from Server
	 */
	protected void handleMessageFromServer(Object arg0)
	{

		/*
		 * arg0 = "bad login"
		 * 
		 * if arg0 contains bad login
		 * flag = false;
		 */

		//parse the username from the value and display id in the jtextfield
		if (arg0 instanceof String)
		{
			// Get the text of the message.
			String content = (String)arg0;

			// If we successfully logged in, tell the login controller.
			if (content.equals("loginaccepted"))
			{
				loginControl.loginSuccess();
			}
			// If we successfully created an account, tell the create account controller.
			else if (content.equals("createaccepted"))
			{
				createControl.createSuccess();
			}
			// If we entered in the wrong account details, send that error to loginControl
			else if (content.equals("logindenied"))
			{
				loginControl.displayError("Inavlid Login Credentials");
			}
			// If we entered in an existing Username, send that error to createControl
			else if (content.equals("createdenied"))
			{
				createControl.displayError("Username Exists Already.");
			}
		}
	}
}

