package com.nohowdezign.hw3;

import java.util.Arrays;

/**
 * @author Noah Howard
 * This class tests the War game with a more static card deck than the other.
 */
public class TestWarGame {

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

        playerOneCards = WarGame.dealCards(playerOneCards, cardDeck); // Deal cards to players, but ensure war
        playerTwoCards = WarGame.dealCards(playerTwoCards, cardDeck);
        playerOneCards[1] = new Card(0);
        playerTwoCards[1] = new Card(13);

                                                                      // Code from here down is the same as WarGame.java

        System.out.println(String.format("Player 1 hand: %s", WarGame.printHand(playerOneCards)));
        System.out.println(String.format("Player 2 hand: %s\n", WarGame.printHand(playerTwoCards)));

        while(playerOneCards.length > 0 || playerTwoCards.length > 0) {
            int playerOneCardNumber = playerOneCards[0].getCardNumber() % 13;
            int playerTwoCardNumber = playerTwoCards[0].getCardNumber() % 13;
            playerOneCards = WarGame.showCard(1, playerOneCards);
            playerTwoCards = WarGame.showCard(2, playerTwoCards);
            if(playerOneCardNumber > playerTwoCardNumber) {
                System.out.println("** Player 1 wins **");
                playerOneWinPile[playerOneWinPileCount] = new Card(playerOneCardNumber);
                playerOneWinPile[playerOneWinPileCount + 1] = new Card(playerTwoCardNumber);
                playerOneWinPileCount += 2;
            } else if(playerOneCardNumber < playerTwoCardNumber) {
                System.out.println("** Player 2 wins **");
                playerTwoWinPile[playerTwoWinPileCount] = new Card(playerOneCardNumber);
                playerTwoWinPile[playerTwoWinPileCount + 1] = new Card(playerTwoCardNumber);
                playerTwoWinPileCount += 2;
            } else {
                int playerToWin = WarGame.doTieWar(playerOneCards, playerTwoCards);
                Card[] playerOneFirstFive = Arrays.copyOfRange(playerOneCards, 0, 5);
                Card[] playerTwoFirstFive = Arrays.copyOfRange(playerTwoCards, 0, 5);
                if(playerToWin == 1) {
                    System.out.println("** Player 1 wins **");
                    for(int i = 0; i < 5; i++) {
                        playerOneWinPile[playerOneWinPileCount] = new Card(playerOneFirstFive[i].getCardNumber());
                        playerOneWinPileCount += 1;
                        playerOneWinPile[playerOneWinPileCount] = new Card(playerTwoFirstFive[i].getCardNumber());
                        playerOneWinPileCount += 1;
                    }
                } else if(playerToWin == 2) {
                    System.out.println("** Player 2 wins **");
                    for(int i = 0; i < 5; i++) {
                        playerTwoWinPile[playerTwoWinPileCount] = new Card(playerOneFirstFive[i].getCardNumber());
                        playerTwoWinPileCount += 1;
                        playerTwoWinPile[playerTwoWinPileCount] = new Card(playerTwoFirstFive[i].getCardNumber());
                        playerTwoWinPileCount += 1;
                    }
                }
            }
            System.out.println(String.format("Player 1 win pile: %s", WarGame.printHand(playerOneWinPile)));
            System.out.println(String.format("Player 2 win pile: %s\n", WarGame.printHand(playerTwoWinPile)));
        }

        System.out.println(String.format("\nFinal winner is Player %d", playerOneWinPileCount > playerTwoWinPileCount ? 1 : 2));
    }

}
