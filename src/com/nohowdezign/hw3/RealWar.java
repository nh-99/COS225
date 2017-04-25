package com.nohowdezign.hw3;

/**
 * Created by Noah on 2/15/2017.
 */
public class RealWar {

    public static void main(String[] args) {
        System.out.println("WarGame Game by Noah Howard\n");
        Deck cardDeck = new Deck();
        cardDeck.shuffle();
    }

    public static String printHand(Card[] hand) {
        String theHand = "";
        for (Card card : hand) {
            if(card != null) {
                theHand += card.toString() + " ";
            }
        }
        return theHand;
    }

}
