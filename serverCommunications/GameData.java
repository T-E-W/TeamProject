package serverCommunications;

import java.io.Serializable;
import java.util.*;

public class GameData implements Serializable
{
	private ArrayList<String> guessedLetters;
	
	public void setGuessedLetters(String c)
	{
		//still need to differentiate between word and single chars
		this.guessedLetters.add(c);
	}
	
	public String getGuessedLetters()
	{
		return null;
		
	}
	
	public boolean guessWord(String guess)
	{
		
		
		return false;
		
	}
}
