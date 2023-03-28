package clientUserInterface;

import java.awt.*;
import clientCommunications.*;
import javax.swing.*;
import java.awt.event.*;

public class StartGamePanel extends JPanel
{
	// Private data fields for the important GUI components.

	private JTextArea contactArea;
	private JTextField usernameField;
	private HangmanClient client;
	private JPanel container;
	private JLabel errorLabel;

	// Getter for the text in the username field.
	public String getUsername()
	{
		return usernameField.getText();
	}


	// Setter for the error text.
	public void setError(String error)
	{
		errorLabel.setText(error);
	}

	// Constructor for the create panel.
	public StartGamePanel(JPanel container, HangmanClient client)
	{
		this.client = client;
		this.container = container;
	    // Create the controller and set it in the chat client.
	    ContactControl contactControl = new ContactControl(container, client);
	    // client.setContactControl(contactControl);
	    
		// Create a panel for the labels at the top of the GUI.
		JPanel labelPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		errorLabel = new JLabel("", JLabel.CENTER);
		errorLabel.setForeground(Color.RED);
		JLabel contactLabel = new JLabel("Contacts", JLabel.CENTER);
		labelPanel.add(errorLabel);
		labelPanel.add(contactLabel);

		// Create a panel for the Contacts List.
		JPanel contactPanel = new JPanel(new FlowLayout());
		contactArea = new JTextArea("", 5, 25);
		JScrollPane contactScrollPane = new JScrollPane( contactArea );
		contactPanel.add(contactScrollPane);


		// Create a panel for the buttons.
		JPanel buttonPanel = new JPanel();
		JButton deleteButton = new JButton("Delete Contact");
		//deleteButton.addActionListener(coc);
		JButton addContact = new JButton("Add Contact");
		//addContact.addActionListener(coc);  
		JButton logOut = new JButton("Log Out");
		//logOut.addActionListener(coc);    
		buttonPanel.add(deleteButton);
		buttonPanel.add(addContact);
		buttonPanel.add(logOut);

		// Arrange the three panels in a grid.
		JPanel grid = new JPanel(new GridLayout(3, 1, 0, 10));
		grid.add(labelPanel);
		grid.add(contactPanel);
		grid.add(buttonPanel);
		this.add(grid);
	}
}
