package com.nohowdezign.hw9;

import java.util.Scanner;

/**
 * @author Noah Howard
 * A test suite for the forest. Simulates all of the homework conditions.
 */
public class TestForest {

    public static void main(String[] args) {
        System.out.print("Enter forest capacity and density: ");            // Get user input
        Scanner s = new Scanner(System.in);
        String str = s.nextLine();
        String[] parts = str.split("\\s");                            // Run simulation for supplied options
        runForest(Integer.valueOf(parts[0]), Integer.valueOf(parts[0]), Double.valueOf(parts[1]));
    }

    private static void runForest(int width, int height, double density) {  // POST: Runs forest simulation for specs
        Forest forest = new Forest(width, height, density);
        forest.printForest(density, true);
        forest.lightTree();
        System.out.print("\n\n\n");
        forest.printForest(density, false);
        System.out.print("\n\n\n");
        forest.printReport((int) ((width * height) * density));
    }

}
