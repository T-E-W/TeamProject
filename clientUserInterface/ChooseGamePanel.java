package clientUserInterface;

import java.awt.*;
import javax.swing.*;

import clientCommunications.*;

public class ChooseGamePanel extends JPanel
{
	public ChooseGamePanel(ChooseGameControl cGc)
	{
		setBackground(new Color(255, 255, 255));
		// Create the information label.
		JLabel titleLabelIntro = new JLabel("Welcome to", JLabel.CENTER);
	    JLabel titleLabel = new JLabel("", JLabel.CENTER);
	    //titleLabel.setVerticalAlignment(SwingConstants.TOP);
	    titleLabel.setIcon(new ImageIcon(ChooseGamePanel.class.getResource("/clientUserInterface/HangmanTitle.png")));
	    JPanel titleBufferPanel = new JPanel(new GridLayout(2,0,5,5));
	    titleBufferPanel.setBackground(new Color(255, 255, 255));
	    titleBufferPanel.add(titleLabelIntro);
	    titleBufferPanel.add(titleLabel);
	    
	    // Create the login button.
	    JButton newGameButton = new JButton("New Game");
	    //newGameButton.setVerticalAlignment(SwingConstants.BOTTOM);
	    newGameButton.addActionListener(cGc);
	    JPanel newGameButtonBuffer = new JPanel();
	    newGameButtonBuffer.setBackground(new Color(255, 255, 255));
	    //newGameButtonBuffer.add(newGameButton);
	    
	    // Create the create account button.
	    JButton joinGameButton = new JButton("Join Game");
	    //joinGameButton.setVerticalAlignment(SwingConstants.TOP);
	    joinGameButton.addActionListener(cGc);
	    JPanel joinGameButtonBuffer = new JPanel(new GridLayout(2,0,5,25));
	    joinGameButtonBuffer.setBackground(new Color(255, 255, 255));
	    joinGameButtonBuffer.add(newGameButton);
	    joinGameButtonBuffer.add(joinGameButton);
	    

	    // Arrange the components in a grid.
	    JPanel upperFill = new JPanel();
	    upperFill.setBackground(new Color(255, 255, 255));
	    setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    JPanel grid = new JPanel(new GridLayout(4, 1, 10, 30));
	    grid.setBackground(new Color(255, 255, 255));
	    //grid.add(upperFill);
	    grid.add(titleBufferPanel);
	    //grid.add(newGameButtonBuffer);
	    grid.add(joinGameButtonBuffer);
	    this.add(grid);
	}

}
