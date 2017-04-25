package com.nohowdezign.hw3;

import java.util.Arrays;

/**
 * @author Noah Howard
 * This class runs a game called War, in which users put down cards from a deck and the highest card value wins. If the
 * users put down the same card value, they then have to lay down five cards and whoever's fifth card is higher wins.
 */
public class WarGame {

    public static void main(String[] args) {
        Deck cardDeck = new Deck();
        cardDeck.shuffle();
        System.out.println("War Game Game by Noah Howard\n");

        Card[] playerOneCards = new Card[26];                         // Stores the 1st players hand
        Card[] playerTwoCards = new Card[26];                         // Stores the 2nd players hand
        Card[] playerOneWinPile = new Card[52];                       // Stores the cards that player 1 won
        Card[] playerTwoWinPile = new Card[52];                       // Stores the cards that player 2 won
        int playerOneWinPileCount = 0;                                // A count to increment for player 1 winnings
        int playerTwoWinPileCount = 0;                                // A count to increment for player 2 winnings

        playerOneCards = dealCards(playerOneCards, cardDeck);         // Deal cards to players
        playerTwoCards = dealCards(playerTwoCards, cardDeck);
                                                                      // Print hands
        System.out.println(String.format("Player 1 hand: %s", printHand(playerOneCards)));
        System.out.println(String.format("Player 2 hand: %s\n", printHand(playerTwoCards)));

        while(playerOneCards.length > 0 || playerTwoCards.length > 0) {
                                                                      // While loop until someone runs out of cards
                                                                      // Card number without suit for comparison
            int playerOneCardNumber = playerOneCards[0].getCardNumber() % 13;
            int playerTwoCardNumber = playerTwoCards[0].getCardNumber() % 13;
            playerOneCards = showCard(1, playerOneCards);// Each player puts a card down
            playerTwoCards = showCard(2, playerTwoCards);
                                                                      // Start checking numbers
            if(playerOneCardNumber > playerTwoCardNumber) {
                System.out.println("** Player 1 wins **");
                playerOneWinPile[playerOneWinPileCount] = new Card(playerOneCardNumber);
                playerOneWinPile[playerOneWinPileCount + 1] = new Card(playerTwoCardNumber);
                playerOneWinPileCount += 2;                           // Add players cards to win pile & increment
            } else if(playerOneCardNumber < playerTwoCardNumber) {
                System.out.println("** Player 2 wins **");
                playerTwoWinPile[playerTwoWinPileCount] = new Card(playerOneCardNumber);
                playerTwoWinPile[playerTwoWinPileCount + 1] = new Card(playerTwoCardNumber);
                playerTwoWinPileCount += 2;                           // Add players cards to win pile & increment
            } else {
                                                                      // Start a war
                                                                      // Calculate which player will win
                int playerToWin = doTieWar(playerOneCards, playerTwoCards);
                                                                      // Create arrays for each players first 5 cards
                Card[] playerOneFirstFive = Arrays.copyOfRange(playerOneCards, 0, 5);
                Card[] playerTwoFirstFive = Arrays.copyOfRange(playerTwoCards, 0, 5);
                if(playerToWin == 1) {                                // Check which player won
                    System.out.println("** Player 1 wins **");
                    for(int i = 0; i < 5; i++) {                      // Add all cards to player win pile
                        playerOneWinPile[playerOneWinPileCount] = new Card(playerOneFirstFive[i].getCardNumber());
                        playerOneWinPileCount += 1;
                        playerOneWinPile[playerOneWinPileCount] = new Card(playerTwoFirstFive[i].getCardNumber());
                        playerOneWinPileCount += 1;
                    }
                } else if(playerToWin == 2) {
                    System.out.println("** Player 2 wins **");
                    for(int i = 0; i < 5; i++) {                      // Add all cards to player win pile
                        playerTwoWinPile[playerTwoWinPileCount] = new Card(playerOneFirstFive[i].getCardNumber());
                        playerTwoWinPileCount += 1;
                        playerTwoWinPile[playerTwoWinPileCount] = new Card(playerTwoFirstFive[i].getCardNumber());
                        playerTwoWinPileCount += 1;
                    }
                }
            }
                                                                      // Print out win piles
            System.out.println(String.format("Player 1 win pile: %s", printHand(playerOneWinPile)));
            System.out.println(String.format("Player 2 win pile: %s\n", printHand(playerTwoWinPile)));
        }

                                                                      // Report final winner
        System.out.println(String.format("\nFinal winner is Player %d", playerOneWinPileCount > playerTwoWinPileCount ? 1 : 2));
    }

    public static Card[] dealCards(Card[] playerCards, Deck cardDeck) // POST: Returns half the deck of cards for player
    {
        for(int i = 0; i < playerCards.length; i++) {
            playerCards[i] = cardDeck.deal();
        }
        return playerCards;
    }

    public static String printHand(Card[] hand)
    {                     // POST: Returns string of hand for printing
        String theHand = "";
        for (Card card : hand) {
            if(card != null) {
                theHand += card.toString() + " ";
            }
        }
        return theHand;
    }

    public static Card[] showCard(int playerNumber, Card[] hand)      // POST: Returns Card array minus the top card
    {
        System.out.println(String.format("Player %d card: %s", playerNumber, hand[0]));
        return Arrays.copyOfRange(hand, 1, hand.length);
    }

                                                                      // POST: Returns which player won the war, or -1
    public static int doTieWar(Card[] playerOneCards, Card[] playerTwoCards)
    {
        System.out.println("** Tie - WAR **");
                                                                      // Construct arrays of first 5 cards to compare
        Card[] playerOneFirstFive = Arrays.copyOfRange(playerOneCards, 0, 5);
        Card[] playerTwoFirstFive = Arrays.copyOfRange(playerTwoCards, 0, 5);

                                                                      // Print cards
        System.out.println(String.format("Player 1 war cards: %s", printHand(playerOneFirstFive)));
        System.out.println(String.format("Player 2 war cards: %s", printHand(playerTwoFirstFive)));

                                                                      // Check who won & return
        if(playerOneFirstFive[4].getCardNumber() > playerTwoFirstFive[4].getCardNumber()) {
            return 1;
        } else if(playerOneFirstFive[4].getCardNumber() < playerTwoFirstFive[4].getCardNumber()) {
            return 2;
        } else {
            return -1;
        }
    }

}
