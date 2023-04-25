package startGameTests;

import static org.junit.Assert.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import clientCommunications.GameControl;
import clientCommunications.HangmanClient;
import clientCommunications.StartGameControl;
import clientUserInterface.GamePanel;
import clientUserInterface.HangmanClientGUI;
import clientUserInterface.StartGamePanel;

public class StartGameTest {
	private static HangmanClientGUI gui;
	private static HangmanClient client;
	private static StartGamePanel startGamePanel;
	private static StartGameControl startGameControl;
	private static GamePanel gamePanel;
	private static GameControl gameControl;
	private static JPanel container;
	private static CardLayout cardLayout;
	private JButton startButton;
	private JTextField guessWordField;

	@BeforeClass
	public static void setUp()
	{
		gui = new HangmanClientGUI(null);
		client = new HangmanClient();
		client.setStartGameControl(startGameControl);

		startGamePanel = new StartGamePanel(startGameControl);
		gamePanel = new GamePanel(gameControl);
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		container.setBackground(new Color(255, 255, 255));
		container.add(startGamePanel, "4");
		container.add(gamePanel, "5");
		startGameControl = new StartGameControl(container, client);
		gameControl = new GameControl(container, client);


	}
	@Before
	public void setupBeforeTest()throws Exception
	{

		startButton = startGamePanel.getStartButton();
		guessWordField = startGamePanel.getGuessWordField();
		Thread.sleep(50);
	}

	@Test
	public void testGetButtonAt()
	{
		
		cardLayout = (CardLayout)container.getLayout();
		cardLayout.show(container, "4");

		startButton = startGamePanel.getStartButton();
		startButton.doClick();
		assertEquals("Check Button Start", "Start", startButton.getActionCommand());
		
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Test
	public void testStartGamePanel(){
		assertNotNull(startGamePanel);
	}

	@Test
	public void testGetWord() {
		guessWordField.setText("Test");
		String word = startGamePanel.getWord();
		assertNotNull(word);
	}

	@Test
	public void testLogout() {
		StartGameControl sgc = new StartGameControl(startGamePanel, null);
		ActionEvent ae = new ActionEvent(startGamePanel, 0, "Logout");
		String command = ae.getActionCommand();
		assertEquals("check logout action command", "Logout", command);

	}
	
	@Test
	public void testGuessLetter() {
		guessWordField.setText("Test");
		String word = startGamePanel.getWord();
		
		gamePanel.setGuessTextField("Test");
		String guess = gamePanel.getGuess();
		
		JButton guessButton = gamePanel.getGuessButton();
		
		guessButton.doClick();
		
		assertEquals("Check Button Guess", "Guess", guessButton.getActionCommand());
	}

	@Test
	public void testStart() {
		StartGameControl sgc = new StartGameControl(startGamePanel, null);
		ActionEvent ae = new ActionEvent(startGamePanel, 0, "Start");
		String command = ae.getActionCommand();
		assertEquals("check start action command", "Start", command);
	}


	@Test
	public void testQuit() {
		StartGameControl sgc = new StartGameControl(startGamePanel, null);
		ActionEvent ae = new ActionEvent(startGamePanel, 0, "Quit");
		String command = ae.getActionCommand();
		assertEquals("check quit action command", "Quit", command);

	}





}
