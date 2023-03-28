package clientCommunications;

import java.awt.*;
import clientUserInterface.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;

public class GameControl implements ActionListener
{
  // Private data fields for the container and chat client.
  private JPanel container;
  private HangmanClient client;
  
  
  // Constructor for the login controller.
  public GameControl(JPanel container, HangmanClient client)
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
      CreateAccData data = new CreateAccData("test","test");
      
      // Check the validity of the information locally first.
      if (data.getUsername().equals("") || data.getPassword().equals(""))
      {
        displayError("You must enter a username and password.");
        return;
      }
      

      // Submit the login information to the server.
      try {
		client.sendToServer(data);
	    CardLayout cardLayout = (CardLayout)container.getLayout();
	    cardLayout.show(container, "1");
      } catch (IOException e) {
		// TODO Auto-generated catch block
	 	e.printStackTrace();
	  }

     
    }
  }

  // After the login is successful, set the User object and display the contacts screen. - this method would be invoked by 
  // the ChatClient
  public void loginSuccess()
  {
	  
  }

  // Method that displays a message in the error - could be invoked by ChatClient or by this class (see above)
  public void displayError(String error)
  {
    LoginPanel loginPanel = (LoginPanel)container.getComponent(1);
    loginPanel.setError(error);
    
  }
}
