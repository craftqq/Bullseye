package io.github.craftqq.Bullseye;	// the package the class is in

import edu.hws.math.TextIO.TextIO;	// importing the TextIO class since it is needed to get the user input

public class Bullseye
{
	
	public static void main(String[] args)
	{
		String 	number 	= 	"";		// the number the user has to guess
		String 	hits 	= 	"";		/* how many hits the user scored
									*  pattern:
									*  "X" for a Bullseye
									*  "O" for a hit
									*  first "X", than "O"
									*  examples: "XX", "O", "XOO"
									*/ 
		int 	round 	= 	0;		// which round the game is in 	/ How many tries the user needs	; game ends after round 20
		boolean running = 	true;	// true if the program is active/ false if the program ends		
		boolean victory = 	false;	// true if the user won 		/ false if the user lost		
		boolean end 	= 	false;	// true if the game ended 		/ false if the game is running	
		
		System.out.println("");															// 
		System.out.println("▓▓▓▓   ▓   ▓  ▓      ▓       ▓▓▓▓  ▓▓▓▓▓  ▓   ▓  ▓▓▓▓▓  ");	// this
		System.out.println("▓   ▓  ▓   ▓  ▓      ▓      ▓      ▓      ▓   ▓  ▓      ");	// is
		System.out.println("▓▓▓▓   ▓   ▓  ▓      ▓       ▓▓▓   ▓▓▓▓    ▓ ▓   ▓▓▓▓   ");	// the
		System.out.println("▓   ▓  ▓   ▓  ▓      ▓          ▓  ▓        ▓    ▓      ");	// start
		System.out.println("▓▓▓▓    ▓▓▓   ▓▓▓▓▓  ▓▓▓▓▓  ▓▓▓▓   ▓▓▓▓▓    ▓    ▓▓▓▓▓  ");	// screen
		System.out.println("");															// 
		try						
		{							//
			Thread.sleep(200);		// Waits
		}							// for
		catch (Exception e)			// a
		{							// bit
									//
		}
		System.out.println("Write \"exit\" to exit Bullseye");	// tells the user how to exit the program
		try
		{							//
			Thread.sleep(200);		// waits
		}							// for
		catch (Exception e)			// a
		{							// bit
									//
		}
		number  = generateNumber();	// generates a new number
		while(running)				// the main loop; active while the program is running
		{
			while(!end)				// the game loop; active while the game is running
			{
				round++;									// increases the round counter
				System.out.println("");						// prints an empty line to leave some space
				System.out.println("Try #" + round + ":");	// prints out which round the user is in
				System.out.println("");						// prints an empty line
				hits = compareNumber(number, getInput());	// gets the input; compares the input with the generated number
				System.out.println(hits);					// prints how many hits the user scored
				if(hits.compareTo("XXXX") == 0)				// checks if the user won
				{											// 
					end 	= true;							// ends the game if the user won
					victory = true;							// sets victory = true to show that the user won
				}											//
				if(round == 20)								// checks if round 20 ended
				{											// 
					end = true;								// ends the game after round 20
				}
			}
			if(victory)												// checks if the user won
			{														// 
				System.out.println("Congratulations! You won!");	// prints out the victory message
				System.out.println("Rounds needed: " + round);		// prints out the amount of tries the user needed to win the game
			}
			else
			{
				System.out.println("Game over. You lost.");			// prints out the defeat message
			}
			while(end)										// the "end" loop; runs after the game ended
			{
				System.out.println("");						// prints out an empty line
				System.out.println("Try again? (Y/N)");		// asks if the user wants to try again
				String input = TextIO.getlnString();		// gets the user input
				if(input.compareToIgnoreCase("exit") == 0)	// checks if the user wants to exit the program
				{											
					System.out.println("Bye!");				// prints the exit message
					System.exit(0);							// exits the program
				}
				else if((input.compareToIgnoreCase("y") == 0) || (input.compareToIgnoreCase("yes") == 0))	// checks if the user wants to play another game
				{
					end 	= false;	// resets the
					victory = false;	// game and starts
					round 	= 0;		// another one
				}
				else if((input.compareToIgnoreCase("n") == 0) || (input.compareToIgnoreCase("no") == 0))	//checks if the user doesn't want to try again
				{
					end 	= false;	// ends the end loop
					running = false;	// and the program
				}
				else					//runs if the input is invalid
				{
					System.out.println(input + " is not an option!");	// informs the user that the specified input is invalid
				}
			}
		}
		System.out.println("Bye!");		// prints the exit message after the main loop ended
		System.exit(0);					// exits the program
	}
	
	static String generateNumber()			// generates a new number
	{
		String newNumber 	= "";			// the new number
		int digit[] 		= new int[4];	// the individual digits
		for(int i = 0; i < 4; i++)			// runs four times; once for each digit
		{
			boolean assigned = false;		// true if a valid digit has been found / false if not
			while (!(assigned))				// runs while the digit is invalid
			{
				assigned = true;			// sets assigned = true; gets set to false if the digit is invalid
				digit[i] = (int) ((Math.random() * 8) + 1);	// generates a new random digit
				for(int j = 0; j < i; j++)	// runs once for every digit generated before the current digit
				{							
					if(digit[i] == digit[j])// checks if the number is already used by another digit (turning the current digit invalid
					{
						assigned = false;	// sets assigned to false if the digit is invalid
					}
				}
			}
		}
		for(int i = 0; i < 4; i++)			// runs four times
		{
			newNumber = newNumber + String.valueOf(digit[i]);	// constructs the new number out of the individual digits
		}
		return newNumber;					// returns the generated number
	}
	
	static String compareNumber(String number1, String number2)	// compares two numbers
	{
		String result 		= "";								// the result of the comparison / the scored hits
		int countBullseye 	= 0;								// how many Bullseyes got scored
		int countHit 		= 0;								// how many hits got scored
		String part1[] 		= new String[4];					// the individual digits of the first number
		String part2[] 		= new String[4];					// the individual digits of the second number
		for(int i = 0; i < 4; i++)								// runs four times
		{
			part1[i] = number1.substring(i, (i + 1));			// creates the separate digits of the first number
			part2[i] = number2.substring(i, (i + 1));			// creates the separate digits of the second number
		}
		for(int i = 0; i < 4; i++)								// runs four times
		{
			for(int j = 0; j < 4; j++)							// runs another four times
			{
				if(part2[j].compareTo(part1[i]) == 0)			// checks if two digits are equal (one of number one and two each)
				{
					if(i == j)									// checks if the digits are at the same position
					{
						countBullseye++;						// increases the Bullseye count if the digits are equal and at the same position
					}
					else										// runs if the digits are at different positions
					{
						countHit++;								// increases the hit count if two digits are equal but not at the same position
					}
				}
			}
		}
		for(int i = 0; i < countBullseye; i++)					// runs as often as the user scored a Bullseye
		{
			result = result + "X";								// adds an "X" to indicate a Bullseye for every Bullseye
		}
		for(int i = 0; i < countHit; i++)						// runs as often as the user scored hits
		{
			result = result + "O";								// adds an "O" for every hit
		}
		return result;											// returns the result/the amount of hits
	}
	
	static String getInput()		// gets the user input
	{
		String input 	= "";		// the input
		boolean inputOK = false;	// true if the input is valid; false if not
		while(!inputOK)				// runs while the input is invalid
		{
			inputOK = true;			// sets the input = true; gets set to false if the input is invalid
			input = TextIO.getlnString();						// uses the TextIO class to get the input
			if(input.compareToIgnoreCase("exit") == 0)			// checks if the user wants to exit the program
			{
				System.out.println("Bye!");						// prints out the exit message
				System.exit(0);									// exits the program
			}
			try													// 
			{													// checks if the input is a number by
				Integer.parseInt(input);						// trying to parse it into an Int;
			}													// an exception is thrown if this isn't possible
			catch(Exception e)									// catches the exception and
			{													// 
				inputOK = false;								// sets inputOK = false to indicate that the input is invalid
				System.out.println("Please write a number!");	// tells the user to input numbers only
			}
			if(inputOK)											// runs if the input is a number
			{
				if((input.length()) != 4)						// checks if the input has less/more digits than required
				{
					System.out.println("The number must consist of 4 digits!");	// tells the user to write a number with four digits
					inputOK = false;							// sets inputOK = false to indicate that the input is invalid
				}
				else											// runs if the number has exactly four digits
				{
					String inputSubS[] = new String[4];			// the individual digits of the input number
					for(int i = 0; i < 4; i++)					// runs four times
					{
						inputSubS[i] = input.substring(i, (i + 1));			// separates the number into the digits
						for(int j = 0; j < i; j++)				// runs once four every digit created before the current one
						{
							if(inputSubS[i].compareTo(inputSubS[j]) == 0)	// checks if two digits are equal
							{
								inputOK = false;				// sets inputOK = false to indicate that the input is invalid
								break;							// ends the current loop since the other digits don't have to be checked
							}
						}
						if(!inputOK)							// checks if the input is invalid
						{
							System.out.println("The number shouldn't contain 2 or more identical digits!");	// tells the user that the number must consist of unique digits
							break;								// ends the current loop since the other digits don't have to be checked
						}
					}
				}
			}
		}
		return input;											// returns the input if it is valid
	}
}
