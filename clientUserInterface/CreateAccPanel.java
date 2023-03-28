package clientUserInterface;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import clientCommunications.*;

public class CreateAccPanel extends JPanel
{
  // Private data fields for the important GUI components.
  private JTextField usernameField;
  private JPasswordField passwordField;
  private JPasswordField passwordField2;
  private JLabel errorLabel;
  
  // Getter for the text in the username field.
  public String getUsername()
  {
    return usernameField.getText();
  }
  
  // Getter for the text in the password field.
  public String getPassword()
  {
	
    return new String(passwordField.getPassword());
  }
  
  public String getPassword2()
  {
	
    return new String(passwordField2.getPassword());
  }
  
  // Setter for the error text.
  public void setError(String error)
  {
    errorLabel.setText(error);
  }
  
  // Constructor for the create panel.
  public CreateAccPanel(JPanel container, HangmanClient client)
  {
        
    // Create a panel for the labels at the top of the GUI.
    JPanel labelPanel = new JPanel(new GridLayout(3, 1));
    errorLabel = new JLabel("123", JLabel.CENTER);
    errorLabel.setForeground(Color.RED);
    JLabel instructionLabel = new JLabel("Enter your NEW username and password.", JLabel.CENTER);
    
    CreateAccControl controller = new CreateAccControl(container, client);
    client.setCreateControl(controller);
    
    labelPanel.add(errorLabel);
    labelPanel.add(instructionLabel);

    // Create a panel for the Create information form.
    JPanel CreatePanel = new JPanel(new GridLayout(3, 2, 5, 5));
    JLabel usernameLabel = new JLabel("Username:", JLabel.RIGHT);
    usernameField = new JTextField(10);
    JLabel passwordLabel = new JLabel("Password:", JLabel.RIGHT);
    passwordField = new JPasswordField(10);
    JLabel passwordLabel2 = new JLabel("Re-enter Password:", JLabel.RIGHT);
    passwordField2 = new JPasswordField(10);
    CreatePanel.add(usernameLabel);
    CreatePanel.add(usernameField);
    CreatePanel.add(passwordLabel);
    CreatePanel.add(passwordField);
    CreatePanel.add(passwordLabel2);
    CreatePanel.add(passwordField2);
    
    // Create a panel for the buttons.
    JPanel buttonPanel = new JPanel();
    JButton submitButton = new JButton("Submit");
    submitButton.addActionListener(controller);
    JButton cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(controller);    
    buttonPanel.add(submitButton);
    buttonPanel.add(cancelButton);

    // Arrange the three panels in a grid.
    JPanel grid = new JPanel(new GridLayout(5, 1, 1, 5));
    grid.add(labelPanel);
    grid.add(CreatePanel);
    grid.add(buttonPanel);
    this.add(grid);
  }
}
