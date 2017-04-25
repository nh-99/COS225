package com.nohowdezign.hw9;

import java.util.Random;

/**
 * @author Noah Howard
 * This class contains all of the logic for taking a forest and setting it on fire in a simulation. The fire recursively
 * spreads throughout the forest to all of the neighboring trees.
 */
public class Forest {
    private Tree forest[][];                                                 // The 2d array of trees in the forest
    private Random rand = new Random();                                      // Random class for use in generating forest
    private int randomWidth;                                                 // Width of array to randomly fill forest
    private int randomHeight;                                                // Height of array to randomly fill forest
    private boolean shouldContinue = true;                                   // Whether forest fire recursion should continue
    private boolean housesEndangered = false;                                // If fire spread to edge of forest

    public Forest(int width, int height, double density) {                   // PRE: width >0, height >0,
        forest = new Tree[height][width];                                    // 0 <= density <= 1
        fillForest((int) ((width * height) * density), width, height);       // Generate the forest
    }

    public void printForest(double density, boolean before) {                // PRE: 0 <= desnity <= 1
        if(before) {                                                         // POST: Prints out visual of the forest
            System.out.println("----------------------------------------------------");
            System.out.println("|         Forest Fire Simulator - Before           |");
            System.out.println("----------------------------------------------------");
        } else {
            System.out.println("----------------------------------------------------");
            System.out.println("|          Forest Fire Simulator - After           |");
            System.out.println("----------------------------------------------------");
        }
        for(int i = 0; i < forest.length; i++) {                              // Loop through forest to print rows
            for(int z = 0; z < forest[i].length; z++) {
                if(forest[i][z] != null) {
                    System.out.print(String.format("%s\t", forest[i][z].toString()));
                } else {
                    System.out.print(".\t");
                }
            }
            System.out.println();
        }
        if(before)                                                             // Print initial amount of trees
            System.out.println(String.format("\nInitial Amount of Trees: %.3f percent", (density)));
    }

    public void printReport(int totalTrees) {                                  // PRE: totalTrees > 0
        int treeFireCount = 0;                                                 // POST: Prints report on trees (stats)
        for(int i = 0; i < forest.length; i++) {                               // Loop through rows to count trees on fire
            for(int z = 0; z < forest[i].length; z++) {
                if(forest[i][z] != null && forest[i][z].isOnFire()) {
                    treeFireCount += 1;
                }
            }
        }

                                                                               // Calculate % of trees burnt down
        double burndownPercent = ((double) treeFireCount / (double) totalTrees);
                                                                               // Print reports
        System.out.print(String.format("%.3f percent of the trees burnt down. ", burndownPercent));
        System.out.print(
                burndownPercent > 0.3 ?
                        "This is above the acceptable loss.\n" :
                        "This is within the acceptable loss.\n"
        );
        System.out.println(
                housesEndangered ?
                        "The fire spread to the edge of the forest" :
                        "The fire was contained inside of the forest"
        );
    }

    public void lightTree() {                                                   // POST: Lights a random tree on fire to
        randomWidth = rand.nextInt(forest[0].length);                           // start the simulation
        randomHeight = rand.nextInt(forest.length);

        if(forest[randomHeight][randomWidth] == null) {
            lightTree();
        } else {
            System.out.println(String.format("Ignite at row: %d col: %d", randomWidth, randomHeight));
            forest[randomHeight][randomWidth].setOnFire(true);
            lightForest();                                                      // We have our tree lit, so we light the
        }                                                                       // rest of the forest on fire.
    }

    private void lightForest() {                                                // POST: Recursively lights neighbor
        boolean continueAgain = false;                                          // trees on fire
        if(shouldContinue) {
            for (int i = 0; i < forest.length; i++) {
                for (int z = 0; z < forest[i].length; z++) {
                    if (forest[i][z] != null && forest[i][z].isOnFire()) {
                        if(!continueAgain) {                                    // Set continue to true if it isn't already
                            continueAgain = lightNeighbors(i, z);               // Light neighbors on fire
                        } else {
                            lightNeighbors(i, z);
                        }
                    }
                }
            }
            shouldContinue = continueAgain;
            lightForest();                                                      // Keep calling until the function is done
        }
    }

    private boolean lightNeighbors(int i, int z) {                              // POST: Lights all trees around a tree
        boolean hasLit = false;                                                 // on fire
        try {
            if (forest[i - 1][z] != null && !forest[i - 1][z].isOnFire()) {     // Check to the top
                forest[i - 1][z].setOnFire(true);
                hasLit = true;                                                  // Check to the left
            } else if (forest[i][z - 1] != null && !forest[i][z - 1].isOnFire()) {
                forest[i][z - 1].setOnFire(true);
                hasLit = true;                                                  // Check to the bottom
            } else if (forest[i + 1][z] != null && !forest[i + 1][z].isOnFire()) {
                forest[i + 1][z].setOnFire(true);
                hasLit = true;                                                  // Check to the right
            } else if (forest[i][z + 1] != null && !forest[i][z + 1].isOnFire()) {
                forest[i][z + 1].setOnFire(true);
                hasLit = true;
            }
        } catch(IndexOutOfBoundsException e) {                                  // Index out of bounds means that houses
            housesEndangered = true;                                            // are endangered, because the fire spread
        }                                                                       // out of the forest.
        return hasLit;
    }

    private void fillForest(int totalFill, int width, int height) {             // PRE: width > 0, height > 0
        randomWidth = rand.nextInt(width);                                      // POST: Fills the forest array with random
        randomHeight = rand.nextInt(height);                                    // trees

        if(totalFill > 0) {
            if (forest[randomHeight][randomWidth] == null) {
                forest[randomHeight][randomWidth] = new Tree();
                fillForest(totalFill - 1, width, height);
            } else {
                fillForest(totalFill, width, height);
            }
        }
    }
}