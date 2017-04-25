package com.nohowdezign.hw3;						// change to your package name

public class Card {
	private int cardNumber;					// card number 0 .. 51
	
	public Card ( ) 						// POST: card number set to 0 (2 Diamonds)
	{	cardNumber = 0;	
	}

	public Card (int n)						// PRE: 0 <= n <= 51
	{	cardNumber = n;						// POST: card number set to n
	}
	
	public int getCardNumber( )				// POST: return card number
	{	return cardNumber;
	}
	
	public void setCardNumber (int n)		// PRE: 0 <= n <= 51
	{	cardNumber = n;						// POST: card number set to n
	}
	public String getRank ( )				// POST: return card rank "2" .. "A"
	{	int rank = cardNumber % 13;			
		if (rank == 0) return "2";
		else if (rank == 1) return "3";
		else if (rank == 2) return "4";
		else if (rank == 3) return "5";
		else if (rank == 4) return "6";
		else if (rank == 5) return "7";
		else if (rank == 6) return "8";
		else if (rank == 7) return "9";
		else if (rank == 8) return "10";
		else if (rank == 9) return "J";
		else if (rank == 10) return "Q";
		else if (rank == 11) return "K";
		else return "A";
	}
	
	public String getSuit ( )				// POST: return card suit 
	{	int suit = cardNumber / 13;
		if (suit == 1) return "\u2660";			// spade
		else if (suit == 2) return "\u2663";	// club
		else if (suit == 3) return "\u2665";	// heart
		else return "\u2666";					// diamond
		
	}
	
	public String toString()				// POST: return rank/suit
	{	return getRank() + getSuit();
	}
}
