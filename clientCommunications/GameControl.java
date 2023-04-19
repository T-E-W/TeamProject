package clientCommunications;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JTextField;

import clientUserInterface.*;

public class GameControl implements ActionListener
{
	// Private data fields for the container and chat client.
	private JPanel container;
	private HangmanClient client;
	private JTextField guessTextField;

	// Constructor for the login controller.
	public GameControl(JPanel container, HangmanClient client)
	{
		this.container = container;
		this.client = client;
		//this.guessTextField = guessTextField;
	}

	// Handle button clicks.
	public void actionPerformed(ActionEvent ae)
	{
		// Get the name of the button clicked.
		String command = ae.getActionCommand();

		if (command == "Guess")
		{


			GamePanel gamePanel = (GamePanel)container.getComponent(5);

			if(gamePanel.getGuess().length() < 1) 
			{
				displayError("Guess must be atleast 1 character long.");
				return;
			}

			String guessString = "Guess:" + gamePanel.getGuess();
			System.out.println(guessString);



			//send to server
			try
			{
				client.sendToServer(guessString);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				displayError("Error connecting to the server.");
			}
		}
	}



	// Method that displays a message in the error label.
	public void displayError(String error)
	{
		GamePanel gamePanel = (GamePanel)container.getComponent(5);
		gamePanel.setGuessStatus(error);
	}

	public void displayGallows() {
		// TODO Auto-generated method stub
		GamePanel gamePanel = (GamePanel)container.getComponent(5);
		//gamePanel.set
	}
	
	public void displayLetter(String string, String indexes) {
		// TODO Auto-generated method stub
		GamePanel gamePanel = (GamePanel)container.getComponent(5);
		gamePanel.printLetters(string, indexes);	
	}
	
	
	public void winScenario() {
		// TODO Auto-generated method stub
		GamePanel gamePanel = (GamePanel)container.getComponent(5);
	}

	public void loseScenario() {
		// TODO Auto-generated method stub
		GamePanel gamePanel = (GamePanel)container.getComponent(5);
	}



}
