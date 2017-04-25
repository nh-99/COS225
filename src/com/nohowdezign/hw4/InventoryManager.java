package com.nohowdezign.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by Noah on 2/19/2017.
 */
public class InventoryManager {
    private Inventory inventory = new Inventory();                           // Create a new inventory at the class level,
                                                                             // so all methods have access.
    public static void main(String[] args) throws FileNotFoundException {
        InventoryManager manager = new InventoryManager();                   // Initialize this class to call non-static
        manager.loadData("products.txt");                   // methods.
        System.out.println("Inventory Reorder Reports by Noah Howard\n");    // Print out main menu options
        System.out.println("Select menu option:");
        System.out.println("M  Report by Model");
        System.out.println("C  Report by Category");
        System.out.println("Q  Quit\n");
        manager.mainMenu();                                                  // Run the main menu function
    }

    public void loadData(String dataFile) throws FileNotFoundException {     // PRE: Valid string location of file
        File f = new File(dataFile);                                         // Load the data file
        Scanner s = new Scanner(f);                                          // Create the Scanner
        s.nextLine();                                                        // Skip the line with the date on it
        while(s.hasNextLine()) {                                             // While the CSV file still has data
            String[] line = s.nextLine().split(",");                  // Split line at commas
            Product p = new Product();
            p.setCategory(line[0].trim());                                   // Set the product variables to their relevant
            p.setModel(line[1].trim());                                      // values in the CSV file.
            p.setDescription(line[2].trim());
            p.setQuantity(Integer.valueOf(line[3]));
            int[] history = new int[12];                                     // Variable to store product history
            for (int i = 0; i < 12; i++) {
                history[i] = Integer.valueOf(line[4 + i]);                   // Push each value from CSV file into array
            }
            p.setHistory(history);
            inventory.insert(p);                                             // Insert our product into the inventory
        }
        s.close();
    }

    public void mainMenu() {
        Scanner s = new Scanner(System.in);                                  // Scan for user input
        boolean quit = false;
        while(!quit && s.hasNext()) {                                        // User hasn't quit & another value inputted
            String line = s.next();
            if(line.equalsIgnoreCase("m")) {                     // Check user input against valid input and
                reportByModel(s);                                             // then run the function for the menu option.
            } else if(line.equalsIgnoreCase("c")) {
                reportByCategory(s);
            } else if(line.equalsIgnoreCase("q")) {
                quit = true;
            }
        }
        s.close();
    }

    public void reportByModel(Scanner s) {
        System.out.print("Enter model: ");
        boolean quit = false;
        while(!quit && s.hasNext()) {                                        // User hasn't quit & another value inputted
            int index = inventory.sequentialSearch(s.next());                // Run sequential search for user inputted
            if(index != -1) {                                                // model #.
                Product p = inventory.getProduct(index);
                System.out.println(String.format("Model: %s", p.getModel()));// Print out product values
                System.out.println(String.format("Description: %s", p.getDescription()));
                System.out.println(String.format("Quantity: %d", p.getQuantity()));
                System.out.println(String.format("Reorder: %d", reorder(p)));
            } else {
                System.out.println("Product not found in the inventory.");   // -1 was returned so product doesn't exist
            }
            quit = true;
        }
    }

    public void reportByCategory(Scanner s) {
        System.out.print("Enter category: ");
        boolean quit = false;
        while(!quit && s.hasNext()) {                                        // User hasn't quit & another value inputted
            String line = s.next() + s.nextLine();
            Product[] products = inventory.getProductsByCategory(line);      // Run inventory function to get products by
            for(Product p : products) {                                      // category with a sequential search.
                if(p != null) {
                    System.out.println(String.format("Model:\t\t%s\t\t\tQuantity:\t%d\t\t\tReorder:\t%d",
                            p.getModel(), p.getQuantity(), reorder(p)));     // Print out formatted products
                }
            }
            quit = true;
        }
    }
                                                                             // POST: Returns number of items to reorder
    private int reorder(Product p) {                                         // for a given product, rounded up to 50
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH);       // Use java builtin to get current month
                                                                             // This formula rounds to nearest 50
                                                                             // ((x+n-1)/n)*n
        int rounded = (int) (((Math.ceil(p.getHistory()[currentMonth] - p.getQuantity())) + 49) / 50) * 50;
        return rounded;
    }

}
