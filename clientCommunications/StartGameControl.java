package clientCommunications;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import clientUserInterface.*;

public class StartGameControl implements ActionListener
{
	// Private data field for storing the container.
	private JPanel container;
	private HangmanClient client;
	// Constructor for the initial controller.
	public StartGameControl(JPanel container, HangmanClient client)
	{
		this.container = container;
		this.client = client;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		// The Login button takes the user to the login panel.
		if (command.equals("Start"))
		{
			//GamePanel GamePanel = (GamePanel)container.getComponent(4);
			//chooseGamePanel.setGuessStatus("");
			
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "6");

		}

		// The Create button takes the user to the create account panel.
		//cuurrently mimics "New Game"
		else if (command.equals("Logout"))
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "2");
		}

		else if (command.equals("Quit"))
		{
			CardLayout cardLayout = (CardLayout)container.getLayout();
			cardLayout.show(container, "4");
		}
	}

}
