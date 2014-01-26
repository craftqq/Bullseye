package io.github.craftqq.Bullseye;

public class Bullseye //class for the game Bullseye
{
	protected static boolean running = true; //true if the program is running
	protected static String number = ""; //the generated number
	protected static boolean victory = false; //true if you won the game
	protected static boolean end = false; //true if the game ended
	protected static String hits = ""; /*how many hits/bullseyes you scored
	"X" for one bullseye; "O" for one hit;
	*"XX" for two bullseyes;"OO" for two hits; 
	*"XO" for one bullseye and one hit;etc...
	*/
	protected static String numberPart[] = new String[4]; //part of the generated number
	protected static int round = 1; //which round you are in/number of guesses + 1
	
	public static void main(String[] args) //main function
	{
		number = newNumber(); //generates a new number
		while(running) //"runtime-loop" (program exits after it ended)
		{
			while (! end) //runs as long as the game hasn't ended
			{
				System.out.println("try number " + round + ":"); //prints out which round you are on
				if(!(checkNumber())) //checks if the entered number has not four digits
				{
					System.out.println("Please input a 4-digit number."); //asks the user to input a 4-digit number
				}
				else //runs if the number has exactly four digits
				{
					System.out.println(hits); //prints out how many hits/bullseyes you scored
					round++; //increases the round you are on (since you can only try once per round)
				}
				if(round > 20) //Game ends
				{			   //after round
					end = true;// 20.
				}
			}
			if (victory) //checks if you have won
			{
				System.out.println("Congratulations! You won!");			  //Prints out the congratulations
				System.out.println("You needed " + round + " rounds to win.");//and the number of rounds if you won;
				System.out.println("Try again?( y/n)");						  //asks you if you want to try again
			}
			else //runs if you lost
			{
				System.out.println("Game over. You lost.");//Prints out the game over "screen" if you lost;
				System.out.println("Try again? (y/n)");	   //asks you if you want to try again
			}
			while(end) //runs while the game is over ("endloop")
			{
				String confirm = TextIO.getlnString(); //gets user input
				if ((confirm.compareTo("y") == 0) || (confirm.compareTo("yes") == 0)) //checks if the user wants to play another game
				{
					number = newNumber();//resets the game
					round = 1; 			 // -//-
					victory = false;	 // -//-
					end = false;		 // -//-
				}
				else if((confirm.compareTo("n") == 0) || (confirm.compareTo("no") == 0)) //checks if the user doesn't want to play another game
				{
					running = false; //ends the "runtime-loop"
					end = false;	 //ends the "endloop"
				}
				else //runs if the input is not y/yes or n/no
				{
					System.out.println(confirm + " is not an option!"); //tells the user that the input is wrong
					System.out.println("Try again? (y/n)");				//asks again
				}
			}
		}
		System.exit(0); //ends the program "normally"
	}
	protected static String newNumber() //generates a new number
	{
		String newNumberPart[] = new String[4]; //the four digits the number consists of
		String newNumber = ""; //the new number
		for(int i = 0; i < 4; i++) //runs four times
		{
			newNumberPart[i] =String.valueOf(((int)(Math.random() * 9))); //assigns each digit a value from 0 - 9
			numberPart[i] = newNumberPart[i]; //sets the individual digits off the number
		}
		for(int j = 0; j < 4; j++) //runs four times
		{
			newNumber = newNumber + newNumberPart[j]; //"chains" the digits together
		}
		return newNumber; //returns the generated number
	}
	protected static boolean checkNumber() //compares the input and the generated number
	{
		String inputNumber = TextIO.getlnString(); //gets the input
		String inputNumberPart[] = new String[4]; //the digits off the input
		int hitCount = 0; //how many hits ("O") you scored
		int bullseyeCount = 0; //hoe many bullseyes ("X") you scored
		if (inputNumber.compareTo(number) == 0) //checks if the input equals the number
		{
			victory = true; //the game has been won
			end = true;		//the game ended
			hits = "XXXX";  //every digit "is" a bullseye
			return true; 	//the input has four digits
		}
		else if (inputNumber.length() == 4) //checks if the input has four digits
		{
			String checkHits = ""; //will be turned into hits later on; "counts" the hits/bullseyes (for example:"XOO")
			boolean s[] = new boolean[4]; /*true if the digit of the generated number has not been "found" yet
										   * (e.g. a bullseye/hit)
										   */
			for(int i = 0; i < 4; i++) //runs four times
			{
				inputNumberPart[i] = inputNumber.substring(i, (i + 1)); //"creates" the four digits of the input
				s[i] = true; //sets all booleans to true
			}
			for (int i = 0; i< 4; i++) //runs four times (as always)
			{
				if(inputNumberPart[i].compareTo(numberPart[i]) == 0) //checks if the digit is a bullseye
				{
					inputNumberPart[i] = ""; //"empties" the input digit since it is no longer needed
					s[i] = false; /*sets the corresponding boolean to false since
								   *the digit of the generated number has been "found".
								   */
					bullseyeCount++; //increases the amount of "found" bullseyes
				}
			}
			for(int i = 0; i < 4; i++) //runs four times (once every digit of the generated number)
			{
				for(int j = 0; j < 4; j++) //runs four times (once per input digit) for every generated digit
				{
					if((inputNumberPart[j].compareTo(numberPart[i]) == 0)&&(s[i])) //checks if the input digit equals any of the generated digits
					{															   //(except those already "found"[generated]/"emptied"[input]
						inputNumberPart[j] = ""; //"empties" the input digit since it is no longer needed
						s[i] = false;/*sets the corresponding boolean to false since
						   			  *the digit of the generated number has been "found".
						   			  */
							hitCount++; //increases the amount of hits
					}
				}
			}
			for(int i = 0; i< bullseyeCount; i++)//adds an "X" to
			{									 //the count for
				checkHits = checkHits + "X";	 //every bullseye
			}
			for(int i = 0; i< hitCount; i++)//adds an "O" to
			{								//the count for
				checkHits = checkHits + "O";//every hit
			}
			hits = checkHits; //passes the "amount" of bullseyes/hits to the global count
			return true; //returns true since the input had four digits
		}
		else //runs if the input doesn't have four digits
		{
			return false; //returns false since the input has less than or more than four digits
		}
	}
}
