package serverCommunications;

import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable
{
	private ArrayList<String> guessedLetters;
	
	/*
	 * HashMap that will Store Words based on ID. Unsure if this is necessary. 
	 */
	private HashMap<Long, String> words;
	
	
	public void setWord(long l, String word) 
	{
		if(!words.containsKey(l)) 
		{
			words.put(l, word);
		}
	}
	
	public boolean setGuessedLetters(long l, String c)
	{
		//still need to differentiate between word and single chars
		if(words.get(l).contains(c)) 
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
