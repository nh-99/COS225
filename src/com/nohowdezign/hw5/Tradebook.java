package com.nohowdezign.hw5;

/**
 * @author Noah Howard
 * A type of book that is major specific, but not specific to a class. This might be a book that could be useful to all
 * COS majors.
 */
public class Tradebook extends Book {
    private String major;                                          // The major the book is for

    public Tradebook() {                                           // POST: Creates empty Tradebook
        super();
        this.major = "";
    }
                                                                   // POST: Creates Tradebook with data
    public Tradebook(String title, String author, String isbn, double price, String major) {
        super(title, author, isbn, price);
        this.major = major;
    }

    @Override
    public double retailPrice() {                                  // POST: Returns raw price with the 15% markup
        return this.getPrice() * 1.15;
    }

    public String getMajor() {                                     // POST: Returns major tradebook is for
        return major;
    }

    public void setMajor(String major) {                           // POST: Sets major tradebook is for
        this.major = major;
    }
}
