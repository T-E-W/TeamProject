package clientCommunications;

import clientUserInterface.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;


/**
 * LoginControl Class implementing ActionListener (event handling)
 * @author timew
 *
 */
public class LoginControl implements ActionListener
{
	// Private data fields for the container and chat client.
	private JPanel container;
	private HangmanClient client;
	// set clients logincontrol to this login control


	/**
	 * Parameterized LoginControl Constructor. Called from LoginPanel.
	 * @param container
	 * @param client
	 */
	public LoginControl(JPanel container, HangmanClient client)
	{
		this.container = container;
		this.client = client;

	}



	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Cancel button takes the user back to the initial panel.
		if (command == "Cancel")
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "1");
		}

		// The Submit button submits the login information to the server.
		else if (command == "Submit")
		{
			System.out.println("Submit Button Pressed");
			// Get the username and password the user entered.
			LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
			LoginData data = new LoginData(loginPanel.getUsername(), loginPanel.getPassword());

			// Check the validity of the information locally first.
			if (data.getUsername().equals("") || data.getPassword().equals(""))
			{
				displayError("You must enter a username and password.");
				return;
			}
			else 
			{
				// Submit the login information to the server.
				try {
					client.sendToServer(data);
					System.out.println("Login Information sent to server");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	// After the login is successful, set the User object and display the contacts screen. - this method would be invoked by 
	//the ChatClient
	public void loginSuccess()
	{
		//go to contact panel here
		System.out.println("Hello");
		CardLayout cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, "4");

	}

	// Method that displays a message in the error - could be invoked by ChatClient or by this class (see above)
	public void displayError(String error)
	{
		LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
		loginPanel.setError(error);

	}
}
