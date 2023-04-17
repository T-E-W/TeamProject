package serverCommunications;

import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable
{
	private ArrayList<String> guessedLetters;
	private String word;
	public boolean setGuessedLetters(String c)
	{
		//still need to differentiate between word and single chars
		if(word.contains(c)) 
		{
			//display the letter, do not draw any gallows
		}
		else
		{
			//do not display any letter, draw a gallow piece
		}
		
		
		//add letter to guessed list
		this.guessedLetters.add(c);
		return false;
	}
	
	
	
	public ArrayList<String> getGuessedLetters()
	{
		return guessedLetters;
	}
	
	public boolean guessWord(String guess)
	{
		if(guess.equals(word))
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
