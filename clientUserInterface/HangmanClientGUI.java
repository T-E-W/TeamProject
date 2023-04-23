package clientUserInterface;

import javax.swing.*;

import clientCommunications.*;

import java.awt.*;
import java.io.IOException;

public class HangmanClientGUI extends JFrame
{
  
  
  // Constructor that creates the client GUI.
  public HangmanClientGUI(String serverAddress)
  {
  	getContentPane().setBackground(new Color(255, 255, 255));
  	setIconImage(Toolkit.getDefaultToolkit().getImage(HangmanClientGUI.class.getResource("/clientUserInterface/teamLogo.png")));
    // Set up the chat client.
   HangmanClient client = new HangmanClient();
    client.setHost(serverAddress);
    client.setPort(8300);
    try
    {
      client.openConnection();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    
    
    
    // Set the title and default close operation.
    this.setTitle("Hangman Client");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    // Create the card layout container.
    CardLayout cardLayout = new CardLayout();
    JPanel container = new JPanel(cardLayout);
    container.setBackground(new Color(255, 255, 255));
    
    //Create the Controllers next
    //Next, create the Controllers
    InitialControl ic = new InitialControl(container,client);
    LoginControl lc = new LoginControl(container,client);
    CreateAccountControl cac = new CreateAccountControl(container,client);
    GameControl gc = new GameControl(container, client);
    ChooseGameControl cGc = new ChooseGameControl(container, client);
    StartGameControl sGc = new StartGameControl(container, client);
    
    //Set the client info
    client.setLoginControl(lc);
    client.setCreateAccountControl(cac);
    client.setGameControl(gc);
    client.setChooseGameControl(cGc);
    client.setStartGameControl(sGc);
   
    
    // Create the four views. (need the controller to register with the Panels
    JPanel view1 = new InitialPanel(ic);
    JPanel view2 = new LoginPanel(lc);
    JPanel view3 = new CreateAccountPanel(cac);
    //JPanel view4 = new ContactsPanel();
    JPanel view4 = new ChooseGamePanel(cGc);
    JPanel view5 = new StartGamePanel(sGc);
    JPanel view6 = new GamePanel(gc);
    
    // Add the views to the card layout container.
    container.add(view1, "1"); //component 0 InitialPanel
    container.add(view2, "2"); //component 1 LoginPanel
    container.add(view3, "3"); //component 2 CreateAccountPanel
    container.add(view4, "4"); //component 3 ChooseGamePanel
    container.add(view5, "5"); //component 4 StartGamePanel
    container.add(view6, "6"); //component 5 GamePanel
      
    // Show the initial view in the card layout.
    cardLayout.show(container, "1"); //change here to quickly see a specific GUI
    
    // Add the card layout container to the JFrame.
    // GridBagLayout makes the container stay centered in the window.
    getContentPane().setLayout(new GridBagLayout());
    getContentPane().add(container);

    // Show the JFrame.
    this.setSize(750, 650);
    this.setVisible(true);
  }

  // Main function that creates the client GUI when the program is started.
  public static void main(String[] args)
  {
	// Get the server address from a dialog box.
		  JFrame frame = new JFrame("Hangman Client");
	      String serverAddress = JOptionPane.showInputDialog(
	          frame,
	          "Enter IP Address of the Server:",
	          "Welcome to Hangman the game",
	          JOptionPane.QUESTION_MESSAGE);
	      frame.setVisible(true);
	    new HangmanClientGUI(serverAddress);
	    frame.setVisible(false);
  }
}
