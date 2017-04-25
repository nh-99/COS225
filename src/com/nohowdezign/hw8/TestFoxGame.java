package com.nohowdezign.hw8;

import java.util.Scanner;

/**
 * @author Noah Howard
 * This class tests a run of the FoxGame. It can be interacted with via the console.
 */
public class TestFoxGame {

    public static void main(String[] args) {
        boolean quit = false;                                                  // If the game is done
        FoxGame foxGame = new FoxGame();                                       // New instance of FoxGame
        TestFoxGame testFoxGame = new TestFoxGame();                           // Instance of this class
        Scanner inputScanner = new Scanner(System.in);                         // Scan for user input

        testFoxGame.printReport(foxGame);
        System.out.println("Enter the item to transport (fox, chicken, grain, none) : ");
        while(!quit && inputScanner.hasNext()) {                               // While the user hasn't quit and theres input
            String input = inputScanner.next();
                                                                               // If input is not valid
            if(!(input.equals("fox") || input.equals("chicken") || input.equals("grain") || input.equals("none"))) {
                System.out.println(input + " is not a valid item.");
                System.out.println("Enter the item to transport (fox, chicken, grain, none) : ");
            } else {
                if(foxGame.found(input) || input.equals("none")) {             // If item is on the current bank or none
                    foxGame.transport(input);                                  // Transport item

                    if(foxGame.lost() || foxGame.won()) {                      // Print game over statement
                        System.out.println("Game is over.\n");
                        quit = true;
                    }

                    testFoxGame.printReport(foxGame);                           // Print report of all locations

                    if (foxGame.lost()) {
                        System.out.println("You lost.");                        // Win, loss, run again
                    } else if (foxGame.won()) {
                        System.out.println("You won!");
                    } else {
                        System.out.println("Enter the item to transport (fox, chicken, grain, none) : ");
                    }
                } else {
                    System.out.println(input + " does not exist on the " + foxGame.getFarmerBank() + " bank.");
                    System.out.println("Enter the item to transport (fox, chicken, grain, none) : ");
                }
            }
        }
    }


    private void printReport(FoxGame game) {                                     // POST: Prints out info about farmer
                                                                                 // location and items locations
        System.out.println(String.format("Farmer location: %s", game.getFarmerBank()));
        System.out.println(String.format("South bank contains: %s", game.displaySouthBank()));
        System.out.println(String.format("North bank contains: %s\n", game.displayNorthBank()));
    }

}
