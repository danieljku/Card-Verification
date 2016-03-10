import java.util.*;

/**
 * class P2
 * This program takes in a credit card number and checks its validity. Type long is used for the
 * entered credit card number. Type int is used for the start of the credit card numbers and the
 * min and max size of the number. This program will tell the user if the credit card number inputed
 * meets the requirements of Luhn check and says if it is valid or invalid.
**/

public class CardCheck {
	/**
	    * The main method is to take the user input of the credit card number and validate it.
	    * It checks if the number is negative or not and if it is it asks for the user to input
	    * another number that is positive. It loops again if the user doesn't enter a 'n' or 'N'.
	   **/
	   public static void main( String args[] )
	   {
		char choice;									//Choice for wanting to redo the program and enter another credit card number
		long creditCardNum;								//Declared the credit card number to long
		Scanner scan = new Scanner(System.in);
		String inputStr = null;

		do										//Do loop for the whole program
		{
		   do										//Do loop to check if the number entered is a negative or not
		   {
		      System.out.print("Enter credit card number as a long integer: ");
		      creditCardNum = scan.nextLong();
		      
		      if (creditCardNum < 0)							//If statement to check if the credit card number is negative
		      System.out.println("ERROR! Enter positive number!");

		   }while (creditCardNum < 0);

		   if( isValid(creditCardNum) )							//If the credit card number meets the Luhn's check then it is valid and if not invalid.
		      System.out.println(creditCardNum + " is valid");
		   else
		      System.out.println(creditCardNum + " is invalid");


		   System.out.print("Want to validate credit card number (y/n)? ");		//Asks user if they want to enter another card number
		   inputStr = scan.next();
		   choice = inputStr.charAt(0);

		   System.out.println(" ");

		}while (choice != 'n' && choice != 'N');					//While the user inputs 'n' or 'N' the program will redo
	   }

	   /**
	    * Returns true or false using other methods to check. This method figures out whether the
	    * credit card number meets the Luhn's check by calling other methods.
	   **/
	   public static boolean isValid( long cCardNum )
	   {
	     final int PRE_VISA = 4;								//Final int for PRE_VISA which represents the starting number for Visa which is 4
	     final int PRE_MASTER = 5;								//Final int for Master which is 5
	     final int PRE_DISCOVER = 6;							//Final int for Discover which is 6
	     final int PRE_AMER_EXP = 37;							//Final int for American express which is 37
	     final int MIN = 13;								//Final int for Min and Max which is the minimum and maximum a card number can be
	     final int MAX = 16;
	     int oddSum;									//Initialized oddSum, evenSum, and total which are the sum's of its respective methods and total is the sum of both
	     int evenSum;
	     int total;

	     evenSum = sumOfDblEvenPlace( cCardNum );						//The sum of even and odd
	     oddSum = sumOfOddPlace( cCardNum );
	     total = evenSum + oddSum;

	     return (getSize(cCardNum) >= MIN && getSize(cCardNum) <= MAX) && 			//The || and && statements to check if the credit card number is true or false.
	     (prefixMatched(cCardNum, PRE_VISA) || prefixMatched(cCardNum, PRE_MASTER) || 
	     prefixMatched(cCardNum, PRE_DISCOVER) || prefixMatched(cCardNum, PRE_AMER_EXP))
	     && (total % 10 == 0);
	   }

	   /**
	    * getSize is the method for finding the number of digits the credit card number has
	   **/
	   public static int getSize( long ccNum)
	   {
	     int numberOfDigits = 0;

	     while (ccNum != 0)									//The while loop continues as long as the ammount of digits isn't 0.
	     {
		numberOfDigits++;
		ccNum /= 10;
	     }
	     return numberOfDigits;
	   }

	   /**
	    * prefixMatched is used to return a true or false using the getSize and getPrefix methods.
	   **/
	   public static boolean prefixMatched( long cCardNum, int d)
	   {
	     return getPrefix(cCardNum, getSize(d)) == d;					//Calls getPrefix to check if the starting number is visa, master, discover, American express or none
	   }

	   /**
	    * Figures out the starting number.
	   **/
	   public static long getPrefix( long cCardNum, int k)
	   {
	     int i;
	     long result = cCardNum;								//Initialized the result that will be returned

	     for (i = 0; i < getSize(cCardNum) - k; i++)					//For loop to get rid of digits until the first or first two digits
	     result /= 10;

	     return result;
	   }

	   /**
	    * Doubles every even number from right to left and sums them together.
	   **/
	   public static int sumOfDblEvenPlace( long cCardNum )
	   {
	     int sum = 0;
	     cCardNum /= 10;
	     while (cCardNum != 0) 								//While loop to sum up the even digits from right to left
	     {
		sum += getDigit((int)((cCardNum % 10) * 2));					//Calls the getDigit method to check if the doubled value is two digits or not
		cCardNum = cCardNum / 100;							//Moves two digits over
	     }
	     return sum;
	   }

	   /**
	    * Adds all the odd places from right to left.
	   **/
	   public static int sumOfOddPlace( long cCardNum )
	   {
	     int sum = 0;
	     while (cCardNum != 0) 								//While loop to add all the odd places together
	     {
		sum += (int)(cCardNum % 10);
		cCardNum /= 100;
	     }
	     return sum;
	   }

	   /**
	    * getDigit is used to add the digits for the doubled digit if it
	    * is two digits long. Otherwise it will send the original number back.
	   **/
	   public static int getDigit( int cCardNum )
	   {
	      if (cCardNum < 10)								//If statement to return the original value because it isn't two digits
	      return cCardNum;

	      else if (cCardNum > 9)								//If to send back the value of the digits added
	      return (cCardNum % 10 + cCardNum / 10);
	      
	      return cCardNum;
	   }
}
