package testClasses;

import static org.junit.Assert.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import clientCommunications.HangmanClient;
import clientCommunications.StartGameControl;

import clientUserInterface.HangmanClientGUI;
import clientUserInterface.StartGamePanel;

public class TestStartGame {
	private static HangmanClient client;
	private static StartGamePanel startGamePanel;
	private static StartGameControl startGameControl;
	private static JPanel container;
	private static CardLayout cardLayout;
	private JButton startButton;
	private JTextField guessWordField;

	@BeforeClass
	public static void setUp()
	{

		client = new HangmanClient();
		cardLayout = new CardLayout();
		container = new JPanel(cardLayout);
		container.setBackground(new Color(255, 255, 255));
		startGameControl = new StartGameControl(container, client);

		client.setStartGameControl(startGameControl);

		startGamePanel = new StartGamePanel(startGameControl);

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
