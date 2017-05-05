package com.nohowdezign.hw12;

/**
 * @author Noah Howard
 * This is thrown if the password that the user tries to enter is invalid.
 */
public class InvalidPasswordException extends Exception {

    public String toString() {
        return "** Invalid Password **";
    }

}
