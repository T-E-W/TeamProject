package hangmanTests;

import static org.junit.Assert.*;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.BeforeClass;

import clientCommunications.GameControl;
import clientCommunications.HangmanClient;
import clientCommunications.LoginControl;
import clientCommunications.LoginData;
import clientCommunications.StartGameControl;
import clientUserInterface.GamePanel;
import clientUserInterface.HangmanClientGUI;
import clientUserInterface.LoginPanel;
import clientUserInterface.StartGamePanel;

public class LoginControlAndLoginPanelTest {
	private static HangmanClient client;
	private static JPanel container;
	private LoginPanel loginPanel;
	private LoginControl loginControl;

	private LoginData loginData;




	@Before
	public void setUp(){
		JFrame jframe = new JFrame();
		loginPanel = new LoginPanel(loginControl);
		CardLayout cardLayout = new CardLayout();
		container = new JPanel(cardLayout);


		loginControl = new LoginControl(container, client);



		container.add(loginPanel,"1");

		container.add(new LoginPanel(loginControl), "2");
		loginData = new LoginData("Tim", "123123");
		cardLayout.show(container, "1");

		jframe.setLayout(new GridBagLayout());

		jframe.add(container);

		jframe.setSize(550, 500);
		jframe.setVisible(true);
	}

	@Test
	public void testSettersNGetters()
	{

		loginPanel.setPassword("123123");
		assertEquals(loginData.getPassword(), "123123");
		loginPanel.setUsername("Tim");
		assertEquals(loginData.getUsername(), "Tim");

	}

	@Test
	public void testLogin()
	{
		
		loginControl.actionPerformed(new ActionEvent(this, 0, "Submit"));
		
	}

	

}
