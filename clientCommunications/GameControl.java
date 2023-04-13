package clientCommunications;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
	    	GamePanel gamePanel = (GamePanel)container.getComponent(4);
	        String guessString = gamePanel.getGuess();
	    	System.out.println(guessString);
	      //send to server
	    	 try
	         {
	           client.sendToServer(guessString);
	         }
	         catch (IOException e)
	         {
	           displayError("Error connecting to the server.");
	         }
	    }
	  }
	  
	// Method that displays a message in the error label.
	  public void displayError(String error)
	  {
	    GamePanel gamePanel = (GamePanel)container.getComponent(4);
	    gamePanel.setGuessStatus(error);
	  }
}
