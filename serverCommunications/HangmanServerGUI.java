package serverCommunications;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;


public class HangmanServerGUI extends JFrame
{
	// Components
	private JLabel status; //Initialized to “Not Connected”
	private String[] labels = {"Port #", "Timeout"};
	private JTextField[] textFields = new JTextField[labels.length];
	private JTextArea log;

	//Buttons
	private JButton listen;
	private JButton close;
	private JButton stop;
	private JButton quit;

	//ChatServer obj
	private HangmanServer server;

	Boolean listening = false;


	// ServerGUI constructor
	public HangmanServerGUI()
	{

		//Instantiating the ChatServer Obj
		server = new HangmanServer();


		// Creating the label and adding it to the north panel
		status = new JLabel("<html>Status: <font color='red'>NOT CONNECTED</font></html>", JLabel.LEFT);
		server.setStatus(status);
		JPanel north = new JPanel();
		north.add(status);



		// Labels & Text Fields --------------
		JPanel centerGrid = new JPanel(new GridLayout(labels.length,1,10,10));
		int i = 0;
		for(i = 0; i < labels.length; i++)
		{

			JLabel j1 = new JLabel(labels[i], JLabel.RIGHT);
			if (labels[i] == "Port #")
			{
				textFields[i] = new JTextField("", 10);
			}
			else
			{
				textFields[i] = new JTextField("",10);
			}


			centerGrid.add(j1);
			centerGrid.add(textFields[i]);
		}

		JPanel centerFlow1 = new JPanel(new FlowLayout());
		centerFlow1.add(centerGrid);

		JLabel jl1 = new JLabel("Server Log Below", JLabel.CENTER);
		log = new JTextArea(5,25);
		log.setSize(new Dimension(105,20));
		server.setLog(log);

		JScrollPane scrollPane = new JScrollPane( log );


		// ---------------------------------

		JPanel center2 = new JPanel(new GridLayout(2,1,5,5));
		center2.add(jl1);
		center2.add(scrollPane);

		JPanel centerFlow2 = new JPanel(new FlowLayout());
		centerFlow2.add(center2);

		JPanel centerMain = new JPanel(new BorderLayout());
		centerMain.add(centerFlow1,BorderLayout.NORTH);
		centerMain.add(centerFlow2,BorderLayout.SOUTH);

		// Buttons -------------------------
		listen = new JButton("Listen");
		close = new JButton("Close");
		stop = new JButton("Stop");
		quit = new JButton("Quit");

		JPanel south = new JPanel();

		south.add(listen);
		south.add(close);
		south.add(stop);
		south.add(quit);
		EventHandler eh = new EventHandler();

		listen.addActionListener(eh);
		close.addActionListener(eh);
		stop.addActionListener(eh);
		quit.addActionListener(eh);
		// ---------------------------------

		this.add(north, BorderLayout.NORTH);
		this.add(centerMain, BorderLayout.CENTER);
		this.add(south,BorderLayout.SOUTH);
		this.setTitle("Server");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setSize(400,450);
		setVisible(true);

	}


	public static void main(String[] args)
	{
		new HangmanServerGUI(); //args[0] represents the title of the GUI
	}



	class EventHandler extends JFrame implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) {

			String command = e.getActionCommand();

			// LISTEN BUTTON ---------------------------------------------
			if (command.equals("Listen")) 
			{
				//Testing Statements
				//System.out.println(textFields[0].getText());
				//System.out.println(textFields[1].getText());
				//String tf1 = textFields[0].getText();
				//String tf2 = textFields[1].getText();

				if (textFields[0].getText().isEmpty() || textFields[1].getText().isEmpty())
				{
					log.append("Port number / timout not entered before pressing Listen\n");
				}
				else
				{

					listening = true;

					try {
						//setting port and timeout from user input
						server.setPort(Integer.parseInt(textFields[0].getText()));
						server.setTimeout(Integer.parseInt(textFields[1].getText()));

						//starting server
						server.listen();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				System.out.println("Listen Button Pressed");

			}
			// CLOSE BUTTON ----------------------------------------------
			else if (command.equals("Close"))
			{
				if (!listening)
				{
					log.append("Server not currently started\n");
				}
				else
				{

					try {
						server.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					listening = false;

				}

				System.out.println("Close Button Pressed");

			}
			// QUIT BUTTON -----------------------------------------------
			else if (command.equals("Quit"))
			{

				try {
					server.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				System.exit(0);
			}
			// CLOSE BUTTON ----------------------------------------------
			else if (command.equals("Stop")) 
			{

				if (!listening)
				{
					log.append("Server not currently started\n");
				}
				else
				{
					server.stopListening();
					listening = false;
				}

				System.out.println("Stop Button Pressed");
			}
		}
	}
}

