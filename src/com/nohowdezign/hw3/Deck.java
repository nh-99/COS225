package com.nohowdezign.hw3;								// change to your package
import java.util.Random;							// import Random class

public class Deck {
	
	private Card [ ] deck = new Card [52];			// array of 52 Card references
	private int topCard = 0;						// top card of deck
	
	public Deck ( )									// POST: deck is initialized by suit and rank
	{	for (int k=0; k<52; k++)
			deck[k] = new Card(k);					// card object created with parameterized constructor setting card number
	}
		
	public void shuffle ( )							// POST: deck is randomly shuffled
	{	int index;									// random index 0 .. 51
		int temp;									// temporary variable for swap
		Random rand = new Random();					// Random number object

		for (int k = 0; k < 52; k++)				// process each card number
		{	index = rand.nextInt(52);				// generate random new location
			temp = deck[k].getCardNumber();			// swap card numbers in two Card objects
			deck[k].setCardNumber(deck[index].getCardNumber());	
			deck[index].setCardNumber(temp);			
		}
	}
	
	public String toString ( )						// POST: return string of 4 lines of deck
	{	String result = new String ();
		for (int k = 0; k < 52; k++)				// process each card number
		{	if (k % 13 == 0) 						// append newline so 4 lines of card values
				result = result + "\n";				
													// use field width of 4 spaces 
			result = result.concat(String.format("%4s",deck[k].toString()));
		}
		result = result.concat("\n");				// append newline
		return result;
	}

	public Card deal ( )							// POST:  return the top card
	{	topCard++;									// increase top card marker
		return deck[topCard-1];						// return previous top card
	}												// code could be shortened to: return deck[topCard++] using postfix ++ operator
}
