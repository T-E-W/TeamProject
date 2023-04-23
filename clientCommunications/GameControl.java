package clientCommunications;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;

import clientUserInterface.*;

public class GameControl implements ActionListener
{
	// Private data fields for the container and chat client.
	private JPanel container;
	private HangmanClient client;
	private JTextField guessTextField;
	private int counter;

	// Constructor for the login controller.
	public GameControl(JPanel container, HangmanClient client)
	{
		this.container = container;
		this.client = client;
		this.counter = 0;
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
		counter += 1;
		Boolean isGameOver = gamePanel.printGallows();

		if(isGameOver)
		{
			try
			{
				client.sendToServer("Lose:" + isGameOver);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				displayError("Error connecting to the server.");
			}
		}
		else if(counter >= 11)
		{
			try
			{
				client.sendToServer("Lose:");
			}
			catch (IOException e)
			{
				e.printStackTrace();
				displayError("Error connecting to the server.");
			}
		}

		//gamePanel.set
	}

	public void displayLetter(String string, String indexes) {
		// TODO Auto-generated method stub
		GamePanel gamePanel = (GamePanel)container.getComponent(5);
		boolean winFlag = false;
		winFlag = gamePanel.printLetters(string, indexes);
		
		
		if (winFlag == true) {
			try
			{
				client.sendToServer("Win:" + winFlag);
			}
			catch (IOException e)
			{
				e.printStackTrace();
				displayError("Error connecting to the server.");
			}
		}

	}


	public void winScenario() {
		// TODO Auto-generated method stub
		GamePanel gamePanel = (GamePanel)container.getComponent(5);
		displayError("Winner Winner!");
		try {
			client.sendToServer("Win:");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loseScenario() {
		// TODO Auto-generated method stub
		GamePanel gamePanel = (GamePanel)container.getComponent(5);
		displayError("Loser!!!");
		gamePanel.setGallows("11");
		try {
			client.sendToServer("Lose:");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
