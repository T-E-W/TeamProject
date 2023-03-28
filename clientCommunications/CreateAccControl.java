package clientCommunications;

import clientUserInterface.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class CreateAccControl implements ActionListener
{
	// Private data fields for the container and chat client.
	private JPanel container;
	private HangmanClient client;


	// Constructor for the login controller.
	public CreateAccControl(JPanel container, HangmanClient client)
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
			// Get the user name and password the user entered.
			CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
			String username = createPanel.getUsername();
			String password = createPanel.getPassword();

			// Check the validity of the information locally first.
			if (username.equals("") || password.equals(""))
			{
				displayError("You must enter a username and password.");
				return;
			}
			else if(!password.equals(createPanel.getPassword2()))
			{
				displayError("Your passwords must match.");
				return;
			}

			if (password.length() < 6)
			{
				displayError("Password must be at least 6 characters.");
				return;
			}

			CreateAccData data = new CreateAccData(createPanel.getUsername(), createPanel.getPassword());
			// Submit the create information to the server.
			try {
				client.sendToServer(data);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

	public void createSuccess()
	{
	    CreateAccPanel createAccountPanel = (CreateAccPanel)container.getComponent(2);
	   
	    //clientGUI.setUser(new User(createAccountPanel.getUsername(), createAccountPanel.getPassword()));
	    CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "4");
	}

	// Method that displays a message in the error - could be invoked by ChatClient or by this class (see above)
	public void displayError(String error)
	{
		CreateAccPanel createPanel = (CreateAccPanel)container.getComponent(2);
		createPanel.setError(error);

	}
}
