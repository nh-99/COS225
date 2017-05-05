package com.nohowdezign.hw12;

import java.util.TreeSet;

/**
 * @author Noah Howard
 * This class contains a user's password. It allows for easy manipulation of the user's password and also isolated
 * storage.
 */
public class Password {
    private String password;
    private TreeSet validOptions = new TreeSet();

    public Password() {                                        // Initialize password object
        this.password = "";
        validOptions.add("?");                                 // Create tree set of valid options
        validOptions.add("!");
        validOptions.add("_");
        validOptions.add("%");
        validOptions.add("#");
    }

    public String getPassword() {                              // POST: Returns a user's password
        return password;
    }
                                                               // POST: Sets a user's password to the specified new pass
                                                               // if it is valid, otherwise throws InvalidPasswordException
    public void setPassword(String password) throws InvalidPasswordException {
        if(isValid(password)) {
            this.password = password;
        } else {
            throw new InvalidPasswordException();
        }
    }

    public boolean isValid(String password) {                  // POST: Returns true or false if a user's pass is valid or not
        boolean containsLowercaseChar = false;                 // Booleans for checking all pieces of password
        boolean containsUppercaseChar = false;
        boolean containsDigit = false;
                                                               // Return false if it's too short
        if(password.toCharArray().length < 6) {
            return false;
        }

        for(char c : password.toCharArray()) {                 // Check characters for password necessities
            if(!(Character.isDigit(c) || Character.isLetter(c)) && !validOptions.contains(String.valueOf(c))) {
                return false;                                  // If character is not in valid options
            }
            if(Character.isLetter(c)) {
                if(Character.isUpperCase(c)) {
                    containsUppercaseChar = true;
                } else {
                    containsLowercaseChar = true;
                }
            } else if(Character.isDigit(c)) {
                containsDigit = true;
            } else if(validOptions.contains(String.valueOf(c))) {
                containsDigit = true;
            }
        }                                                      // Only return true if all conditions are met
        return containsLowercaseChar && containsUppercaseChar && containsDigit;
    }
}
