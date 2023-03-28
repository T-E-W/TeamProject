package database;

import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

public class DatabaseFile
{
	FileInputStream inputStream;
	FileOutputStream outputStream;

	// To store accounts
	HashMap<String, String> accounts;

	/**
	 * Validate Acc Info
	 * @param un
	 * @param pw
	 * @return
	 */
	public boolean isValid(String un, String pw)
	{

		readFile();

		if (accounts.get(un) == null) { return false; }

		if (accounts.get(un).equals(pw)) { return true; }

		else { return false; }

	}

	/**
	 * Create Accounts
	 * @param username
	 * @param password
	 * @return bool
	 */
	public boolean createAcc(String un, String pw)
	{
		readFile();

		if (accounts.get(un) != null) { return false; }

		// Add acc
		accounts.put(un, pw);

		writeFile();
		return true;
	}

	/**
	 * Read / Write file from geek4geeks.com
	 */
	public synchronized void readFile()
	{

		accounts = new HashMap<String, String>();

		try
		{
			inputStream = new FileInputStream("accounts.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

			String line = reader.readLine();
			while(line != null)
			{
				String[] data = line.split("\\|");

				if (data.length == 2)
					accounts.put(data[0], data[1]);

				line = reader.readLine();
			}

			inputStream.close();
		}

		catch (Exception exception)
		{
			return;
		}
	}

	/**
	 * Write to file
	 */
	public synchronized void writeFile()
	{
		String output = "";
		for (Entry<String, String> data : accounts.entrySet())
		{
			output += data.getKey() + "|" + data.getValue() + "\n";
		}

		try
		{
			outputStream = new FileOutputStream("accounts.txt");
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
			
			writer.write(output);
			writer.flush();

			outputStream.close();
		}

		catch (Exception exception)
		{
			return;
		}
	}
}