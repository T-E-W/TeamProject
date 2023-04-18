package clientUserInterface;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import clientCommunications.*;

public class GamePanel extends JPanel
{
	private String[] letterLabels = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","X","Y","Z"};
	private String gameWord = "TESTWORD";
	private JLabel gallowStatusLabel;
	private String gallowStatus = "Your Gallow Status";
	private JLabel gallows;
	private JLabel guessStatusLabel;
	private String guessStatus = "Guess Status here";
	private JTextField guessTextField;
	private String currentGallowPic;
	private String guess = "";
	private JButton[] letterButtons;

	public void setGuessStatus(String msg)
	{
		guessStatusLabel.setText(msg);
	}
	public String getGuess()
	{
		return guessTextField.getText();
	}
	public void setGuessTextField(String letter)
	{
		guess = guess + letter;
		guessTextField.setText(guess);
	}
	public void correctGuess(String c, int index)
	{
		/*
		 * This is required to show the correct img of a letter once you correctly guess. 
		 * The index is for referencing which position the correct letter is in to display
		 * the correct letter in the correct spot.
		 */
	}


	public GamePanel(GameControl gc)
	{
		setBackground(new Color(255, 255, 255));
		int i = 0;
		setLayout(new BorderLayout(0, 0));

		//panel for title
		JPanel northPanel = new JPanel();
		northPanel.setBackground(new Color(255, 255, 255));
		add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new GridLayout(2, 1, 5, 5));
		JLabel titleLabel = new JLabel("", JLabel.CENTER);
		titleLabel.setIcon(new ImageIcon(ChooseGamePanel.class.getResource("/clientUserInterface/HangmanTitle.png")));
		northPanel.add(titleLabel);

		//game word to guess
		JPanel gameWordPanel = new JPanel(new GridLayout(1, 8, 5, 5));
		gameWordPanel.setBackground(new Color(255, 255, 255));
		JLabel[] gameWordLabels = new JLabel[gameWord.length()];
		for (i = 0; i < gameWord.length(); i++)
		{
			//JLabel[] gameWordLabels = new JLabel[gameWord.length()];//("", JLabel.CENTER);
			gameWordLabels[i] = new JLabel("", JLabel.CENTER);
			gameWordLabels[i].setIcon(new ImageIcon(ChooseGamePanel.class.getResource("/clientUserInterface/cover.png")));
			gameWordPanel.add(gameWordLabels[i]);
		}

		JPanel centerPanel = new JPanel(new GridLayout(1, 3, 15, 5));
		centerPanel.setBackground(new Color(255, 255, 255));
		//FlowLayout flowLayout_3 = (FlowLayout) centerPanel.getLayout();
		add(centerPanel, BorderLayout.CENTER);

		//panel builder for guess options
		JPanel guessOptionPanel = new JPanel(new GridLayout(4, 1, 5, 10));
		guessOptionPanel.setBackground(new Color(255, 255, 255));
		guessStatusLabel = new JLabel(guessStatus);
		guessStatusLabel.setVerticalAlignment(SwingConstants.TOP);
		guessStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		guessTextField = new JTextField();
		guessTextField.setHorizontalAlignment(SwingConstants.CENTER);
		JButton guessButton = new JButton("Guess");
		guessButton.addActionListener(gc);
		guessOptionPanel.add(guessStatusLabel);
		guessOptionPanel.add(guessTextField);
		guessOptionPanel.add(guessButton);

		//panel builder for letter buttons
		letterButtons = new JButton[26];
		JPanel buttonPanel = new JPanel(new GridLayout(7, 4, 5, 5));
		buttonPanel.setBackground(new Color(255, 255, 255));
		for (i = 0; i < letterLabels.length; i++)
		{
			letterButtons[i] = new JButton(letterLabels[i]);
			buttonPanel.add(letterButtons[i]);

			//updates the guessTextField
			letterButtons[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					JButton letterBut = (JButton)e.getSource();
					String guessText = guessTextField.getText() + letterBut.getText();
					guessTextField.setText(guessText);
				}
			});
		}

		JButton clearButton = new JButton("CLR");
		clearButton.setFont(new Font("Tahoma", Font.BOLD, 10));
		buttonPanel.add(clearButton);
		clearButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				guessTextField.setText("");
			}
		});

		//hangman status
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(new Color(255, 255, 255));
		buttonPanel.setBackground(new Color(255, 255, 255));
		gallowStatusLabel = new JLabel(gallowStatus);
		gallowStatusLabel.setVerticalAlignment(SwingConstants.TOP);
		gallowStatusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		gallows = new JLabel();
		gallows.setHorizontalAlignment(SwingConstants.CENTER);
		gallows.setIcon(new ImageIcon(ChooseGamePanel.class.getResource("/clientUserInterface/0.jpg")));
		statusPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
		statusPanel.add(gallowStatusLabel);
		statusPanel.add(gallows);

		//testing
		centerPanel.add(buttonPanel);
		centerPanel.add(guessOptionPanel);
		centerPanel.add(statusPanel);


		//south panel stuff
		JPanel southPanel = new JPanel();
		southPanel.setBackground(new Color(255, 255, 255));
		FlowLayout flowLayout_2 = (FlowLayout) southPanel.getLayout();
		flowLayout_2.setVgap(70);
		add(southPanel, BorderLayout.SOUTH);
		//southPanel.add(buttonPanel);
		southPanel.add(gameWordPanel);



	}

}
