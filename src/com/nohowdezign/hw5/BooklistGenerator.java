package com.nohowdezign.hw5;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Noah Howard
 * This class is what gets run and ingests the books. It then allows the user to find books for their class and also
 * books that might be useful for their major. It keeps running until the user quits.
 */
public class BooklistGenerator {
    private ArrayList<Book> availableBooks = new ArrayList<>();

    public static void main(String[] args) {
        BooklistGenerator generator = new BooklistGenerator();         // Instantiate class to call non-static methods
        generator.ingestBooks();
        Scanner s = new Scanner(System.in);
        generator.enterCourses(s);
    }

    private void ingestBooks() {                                       // POST: Populates array of books with data
        File f = new File("data/hw5/books.txt");             // Get data file
        int count = 0;                                                 // Increment count for pushing to String array
        String[] toProcess = new String[7];                            // Array to store data in for processing
        try {
            Scanner s = new Scanner(f);                                // Scan file
            while(s.hasNextLine()) {
                count++;                                               // Increment count
                toProcess[count%7] = s.nextLine();                     // Push line into array

                if(count % 6 == 0) {                                   // If we have the whole book, process it
                    processBook(toProcess);
                    count = 0;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Could not find book data. Exiting.");  // Exit on file read error
            System.exit(0);
        }
    }

    private void processBook(String[] book) {                         // POST: Adds a single book to array
        Book b;
        switch (book[1]) {
            case "Textbook":                                          // If book is textbook, create textbook with values
                b = new Textbook(book[2], book[3], book[4], Double.valueOf(book[5]), book[6]);
                break;
            case "Tradebook":                                         // If book is tradebook, create tradebook with values
                b = new Tradebook(book[2], book[3], book[4], Double.valueOf(book[5]), book[6]);
                break;
            default:
                b = new Textbook();                                   // By default, create empty textbook
                break;
        }
        availableBooks.add(b);                                        // Push new book to arraylist
    }
                                                                      // PRE: System.in scanner
    private void enterCourses(Scanner s) {                            // POST: Scans user input and prints out courses
        System.out.print("Enter your major: ");
        ArrayList<Textbook> booksForCourses = new ArrayList<>();      // Create arrays for books, one per course one per major
        ArrayList<Tradebook> booksForMajor = getBooksByMajor(s.next());
        System.out.print("\nEnter course name: (xxx to quit): ");
        boolean quit = false;                                         // Allow user to quit
        while(!quit && s.hasNext()) {
            String input = s.next();                                  // Get line of input
            if(input.equals("xxx")) {                                 // If it's xxx, we quit
                quit = true;
            } else {
                if (getBookByCourse(input) != null) {                 // Get book for course name and push into array
                    booksForCourses.add(getBookByCourse(input));
                }                                                     // Run again
                System.out.print("Enter course name: (xxx to quit): ");
            }
        }
        printBooks(booksForCourses, booksForMajor);                   // Print out books after quit
    }
                                                                      // PRE: 3 letter major code (eg. COS)
    private ArrayList<Tradebook> getBooksByMajor(String major) {      // POST: Gets tradebooks for major
        ArrayList<Tradebook> toReturn = new ArrayList<>();            // Initialize return array
        for(Book b : availableBooks) {                                // Iterate
            try {
                Tradebook book = (Tradebook) b;                       // Cast book to tradebook
                if(book.getMajor().equals(major)) {                   // Compare majors of book and add to return array
                    toReturn.add(book);
                }
            } catch (ClassCastException e) {}                         // Catch non-fatal exception, but no need to do
        }                                                             // anything with it.
        return toReturn;
    }
                                                                      // PRE: 6 digit course name (eg. COS221)
    private Textbook getBookByCourse(String input) {                  // POST: The Textbook object for the course specified
        for(Book b : availableBooks) {
            try {
                Textbook book = (Textbook) b;                         // Cast book to textbook
                if(book.getCourse().equals(input)) {                  // Check that book course is the course to find
                    return book;                                      // Return the book
                }
            } catch (ClassCastException e) {}                         // Catch non-fatal exception, but no need to do
        }
        return null;
    }
                                                                      // POST: Prints out the info about the books
    private void printBooks(ArrayList<Textbook> booksForCourses, ArrayList<Tradebook> booksForMajor) {
        System.out.println("\nList of textbooks: ");
        double bookPriceSum = 0;                                      // Sum of book prices
        NumberFormat nf =  NumberFormat.getCurrencyInstance();        // Currency formatter
        for(Textbook b : booksForCourses) {                           // Iterate through textbooks
            String p = nf.format(b.retailPrice());                    // Format price and print book info with formatting
            System.out.println(String.format("%-10s %-50s %-30s %-8s", b.getCourse(), b.getTitle(), b.getAuthor(), p));
            bookPriceSum += b.retailPrice();                          // Add new book price to sum
        }                                                             // Print out sum of book prices
        System.out.println(String.format("%-92s %-8s\n", "Sum of retail book prices:", nf.format(bookPriceSum)));

        System.out.println("List of tradebooks:");
        for(Tradebook b : booksForMajor) {                            // Iterate through tradebooks
            String p = nf.format(b.retailPrice());                    // Format currency and print book info with formatting
            System.out.println(String.format("%-61s %-30s %-8s", b.getTitle(), b.getAuthor(), p));
        }
    }

}
