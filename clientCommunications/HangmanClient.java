package clientCommunications;

import clientCommunications.*;
import ocsf.client.AbstractClient;

public class HangmanClient extends AbstractClient
{
	// Private data fields for storing the GUI controllers.
	private LoginControl loginControl;
	private CreateAccountControl createAccountControl;
	private GameControl gameControl;
	private ChooseGameControl chooseGameControl;
	private StartGameControl startGameControl;
	private boolean loseFlag;

	// Setters for the GUI controllers.
	public void setLoginControl(LoginControl loginControl)
	{
		this.loginControl = loginControl;
	}
	public void setCreateAccountControl(CreateAccountControl createAccountControl)
	{
		this.createAccountControl = createAccountControl;
	}
	public void setGameControl(GameControl gameControl)
	{
		this.gameControl = gameControl;
	}
	public void setChooseGameControl(ChooseGameControl chooseGameControl)
	{
		this.chooseGameControl = chooseGameControl;
	}
	public void setStartGameControl(StartGameControl startGameControl)
	{
		this.startGameControl = startGameControl;
	}

	// Constructor for initializing the client with default settings.
	public HangmanClient()
	{
		super("localhost", 8300);
		loseFlag = true;
	}

	// Method that handles messages from the server.
	public void handleMessageFromServer(Object arg0)
	{
		// If we received a String, figure out what this event is.
		if (arg0 instanceof String)
		{
			// Get the text of the message.
			String message = (String)arg0;

			// If we successfully logged in, tell the login controller.
			if (message.equals("LoginSuccessful"))
			{
				loginControl.loginSuccess();
			}

			// If we successfully created an account, tell the create account controller.
			else if (message.equals("CreateAccountSuccessful"))
			{
				createAccountControl.createAccountSuccess();
			}
			else if (message.contains("GuessResult:"))
			{
				// message comes in as "GuessResult:guess:true:indexes"
				// First, remove GuessResult:, then split at ':', then if msgParts[0] is a single character, and msgParts[1] is
				// equal to true, then it's a letter to be displayed.
				
				// if true and more than 1 char, it's a win scenario
				// else, its a draw gallows scenario
				
				// msgParts[2] is now the occuring indexes of the letter guessed if it does exist in the word.
				// If the letter is not in the word, then the index value will be "99"
				
				// "GuessResult:guess:true:indexes"
				// "GuessResult:a:true:3,4,6,7"
				
				message = message.replace("GuessResult:", "");
				
				// "msgParts[0] = a   
				//  true      
				//  2,3,4,5"
				
				String[] msgParts = message.strip().split(":");
				
				// msgParts[0] = guess ('a', or "apple")
				// msgParts[1] = true/false
				
				if(msgParts[0].length() == 1 && msgParts[1].equals("true"))
				{
					// display letter, we need to know the index of this letter. 
					gameControl.displayLetter(msgParts[0], msgParts[2]);
				}
				else if(msgParts[0].length() > 1 && msgParts[1].equals("true"))
				{
					gameControl.winScenario();
					loseFlag = false;
				}
				else
				{
					gameControl.displayGallows();
				}
			}
			else if (message.contains("Lose"))
			{
				// lose game screen
				loseFlag = true;
			}
			else if (message.contains("GameOver"))
			{
				// Game over screen
				if(loseFlag != true)
				{
					gameControl.winScenario();
				}
				else
				{
					gameControl.loseScenario();
				}
			}
			
		}

		// If we received an Error, figure out where to display it.
		else if (arg0 instanceof Error)
		{
			// Get the Error object.
			Error error = (Error)arg0;

			// Display login errors using the login controller.
			if (error.getType().equals("Login"))
			{
				loginControl.displayError(error.getMessage());
			}

			// Display account creation errors using the create account controller.
			else if (error.getType().equals("CreateAccount"))
			{
				createAccountControl.displayError(error.getMessage());
			}
			else if (error.getType().equals("Game"))
			{
				gameControl.displayError(error.getMessage());
			}
		}
	}  
}
