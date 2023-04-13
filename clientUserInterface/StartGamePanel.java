package clientUserInterface;

import java.awt.*;
import javax.swing.*;

import clientCommunications.*;

public class StartGamePanel extends JPanel
{
	private int gameWordLength = 8; //will need updating from server
	private JTextField myWordTextField;
	private JLabel errorLabel;
	
	// Setter for the error text.
	  public void setError(String error)
	  {
	    errorLabel.setText(error);
	  }
	
	StartGamePanel(StartGameControl sGc)
	{
		setBackground(new Color(255, 255, 255));
		
		//build items
		JLabel titleLabel = new JLabel("", JLabel.CENTER);
	    titleLabel.setIcon(new ImageIcon(ChooseGamePanel.class.getResource("/clientUserInterface/HangmanTitle.png")));
	    JButton logoutButton = new JButton("Logout");
	    JButton quitButton = new JButton("Quit");
	    JLabel instruction = new JLabel("Enter a word containing "+ gameWordLength + " letters.", JLabel.CENTER);
	    JLabel instruction2 = new JLabel("Press Start when finished.", JLabel.CENTER);
	    myWordTextField = new JTextField();
	    myWordTextField.setHorizontalAlignment(SwingConstants.CENTER);
	    JButton startButton = new JButton("Start");
	    errorLabel = new JLabel("");
	    
	    //add to control
	    logoutButton.addActionListener(sGc);
	    quitButton.addActionListener(sGc);
	    startButton.addActionListener(sGc);
	    
	    //build layout
	    JPanel header = new JPanel();
	    header.setBackground(new Color(255, 255, 255));
	    header.add(titleLabel);
	    
	    JPanel body = new JPanel(new GridLayout(5,0,10,15));
	    body.setBackground(new Color(255, 255, 255));
	    body.add(instruction);
	    body.add(instruction2);
	    body.add(myWordTextField);
	    body.add(startButton);
	    body.add(errorLabel);
	    
	    JPanel footer = new JPanel();
	    footer.setBackground(new Color(255, 255, 255));
	    footer.add(logoutButton);
	    footer.add(quitButton);
	    
	    //organize panels
	    JPanel grid = new JPanel();
	    grid.setLayout(new GridLayout(0, 1, 0, 0));
	    grid.setBackground(new Color(255, 255, 255));
	    grid.add(header);
	    grid.add(body);
	    grid.add(footer);
	    this.add(grid);
	}

}
