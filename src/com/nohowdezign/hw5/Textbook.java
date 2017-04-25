package com.nohowdezign.hw5;

/**
 * @author Noah Howard
 * Textbook class for holding information about textbooks for specific classes.
 */
public class Textbook extends Book {
    private String course;                                       // Course the textbook is for (ex. COS225)

    public Textbook() {                                          // POST: Creates empty textbook object
        super();
        this.course = "";
    }

                                                                 // POST: Creates textbook with specified values
    public Textbook(String title, String author, String isbn, double price, String course) {
        super(title, author, isbn, price);
        this.course = course;
    }

    @Override
    public double retailPrice() {                                // POST: Returns price that we sell the book for (10%
        return this.getPrice() * 1.1;                            // extra of the raw cost)
    }

    public String getCourse() {                                  // POST: Returns the course the book is for
        return course;
    }

    public void setCourse(String course) {                       // POST: Sets the course the book is for
        this.course = course;
    }
}
