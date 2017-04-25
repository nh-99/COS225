package com.nohowdezign.hw5;

/**
 * @author Noah Howard
 * This is the parent class for all the books used. It holds the methods all books will need to have.
 */
public abstract class Book {
    private String title;                                                 // Title of the book
    private String author;                                                // Author of the book
    private String isbn;                                                  // The books ISBN
    private double price;                                                 // The raw price of the book

    public Book() {                                                       // POST: Empty book object is created
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.price = 0.0d;
    }

    public Book(String title, String author, String isbn, double price) { // POST: Book object is created with params
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
    }

    public abstract double retailPrice();                                 // POST: Returns retail price (abstract so
                                                                          // other classes must implement their own)
    public String getTitle() {                                            // POST: Returns value of title
        return title;
    }

    public void setTitle(String title) {                                  // POST: Sets title of book
        this.title = title;
    }

    public String getAuthor() {                                           // POST: Returns author of book
        return author;
    }

    public void setAuthor(String author) {                                // POST: Sets author of book
        this.author = author;
    }

    public String getIsbn() {                                             // POST: Returns book isbn
        return isbn;
    }

    public void setIsbn(String isbn) {                                    // POST: Sets book isbn
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
