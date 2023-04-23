package clientCommunications;

import java.awt.*;
import javax.swing.*;

import clientUserInterface.*;

import java.awt.event.*;
import java.io.IOException;

public class ChooseGameControl implements ActionListener
{
  // Private data field for storing the container.
  private JPanel container;
  private HangmanClient client;
  // Constructor for the initial controller.
  public ChooseGameControl(JPanel container, HangmanClient client)
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
    if (command.equals("New Game"))
    {
      //GamePanel GamePanel = (GamePanel)container.getComponent(4);
      //chooseGamePanel.setGuessStatus("");
      CardLayout cardLayout = (CardLayout)container.getLayout();
      cardLayout.show(container, "5");
      
      try {
		client.sendToServer("NewGame:");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
    }
    
    // The Create button takes the user to the create account panel.
    //cuurrently mimics "New Game"
    else if (command.equals("Join Game"))
    {
    	CardLayout cardLayout = (CardLayout)container.getLayout();
        cardLayout.show(container, "5");
        
        try {
    		client.sendToServer("JoinGame:");
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }
  }
}
