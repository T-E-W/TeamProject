package clientCommunications;

import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable
{
	private ArrayList<String> guessedLetters;

	/*
	 * HashMap that will Store Words based on ID. But then somehow we have to figure out how to 
	 */
	private HashMap<Long, String> words;

	private User player1;
	private User player2;


	public GameData()
	{
		
	}
	
	public ArrayList<String> getGuessedLetters()
	{
		return guessedLetters;
	}


	public void setWord(long l, String word) 
	{
		if(player1.getID() == l) 
		{
			player1.setWord(word);
		}
		else if(player2.getID() == l)
		{
			player2.setWord(word);
		}
	}
	public boolean guessLetter(long l, String c)
	{
		//still need to differentiate between word and single chars
		if(player1.getID() == l) 
		{
			//return true
			if(player1.getWord().contains(c))
				return true;
			else
				return false;
		}
		else if (player2.getID() == l)
		{
			//return false
			if(player1.getWord().contains(c))
				return true;
			else
				return false;
		}


		//add letter to guessed list
		return false;
	}


	public boolean guessWord(long l, String guess)
	{
		if(words.get(l).equals(guess))
		{
			//show victory screen for player who guessed
		}
		else
		{
			//draw gallows piece. 
		}

		return false;

	}
}
