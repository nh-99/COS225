package com.nohowdezign.hw12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @author Noah Howard
 * This class allows the user to change their password. The password must be A-Z,a-z,0-9,or one of the 5 allowed special
 * characters. It also cannot be one of the previous 3 passwords.
 */
public class PasswordManager {
    private TreeMap<String, String[]> userMap = new TreeMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        PasswordManager passwordManager = new PasswordManager();
        passwordManager.loadData();
        passwordManager.printUserMap();

        System.out.print("User ID: ");
        Scanner s = new Scanner(System.in);
        boolean quit = false;
        while(!quit && s.hasNext()) {                                     // Scan for user input
            String userid = s.next();                                     // Get user id input
            if(userid.equals("quit")) {                                   // quit condition, to quit and display map
                quit = true;
            }
            if(!passwordManager.userMap.containsKey(userid)) {            // if user not in map
                System.out.println("Invalid user id");
            } else {
                System.out.print("\nCurrent password: ");
                String password = s.next();
                if(!passwordManager.userMap.get(userid)[0].equals(password)) { // If password is incorrect
                    System.out.println("The password you entered was incorrect.");
                } else {
                    System.out.print("\nNew Password: ");
                    String newPass = s.next();
                    newPass = passwordManager.checkPass(newPass, s);
                    if(passwordManager.wasPassLastThree(userid, newPass)) {   // If password has been used within last 3 times
                        System.out.println("You need to enter a password that you haven't used the past three times.");
                    } else {
                        passwordManager.updatePass(userid, newPass);      // If all of these pass, update the password
                        System.out.println("\nYour password has been changed successfully.");
                    }
                }
            }

            if(!quit)
                System.out.print("User ID: ");
        }                                                                 // Once the user has quit, display the final map
        System.out.println("\n##################\n# Final User Map #\n##################");
        passwordManager.printUserMap();
    }

    private void loadData() throws FileNotFoundException {                // POST: Fills up user map
        File f = new File("data/hw12/userids.txt");
        Scanner scanner = new Scanner(f);
        while(scanner.hasNextLine()) {
            String[] lineParts = scanner.nextLine().split("\\s");
            String[] oldPassword = new String[lineParts.length - 1];      // Create array of old passwords
            for(int i = 1; i < lineParts.length; i++) {
                oldPassword[i-1] = lineParts[i];
            }
            userMap.put(lineParts[0], oldPassword);                       // Store passwords with user id
        }
        scanner.close();
    }

    private String checkPass(String newPass, Scanner s) {                 // POST: Checks that a user's password is valid
        try {
            Password newPassObject = new Password();
            newPassObject.setPassword(newPass);
            return newPass;
        } catch (InvalidPasswordException e) {
            System.out.print(e.toString() + "\nNew Password: ");
            checkPass(s.next(), s);
        }
        return null;
    }

    private boolean wasPassLastThree(String userid, String newPassword) {  // POST: Checks if user password was used in
        boolean toReturn = false;                                          // the last 3 times, returns true if yes
        String[] passwords = userMap.get(userid);
        for(String password : passwords) {
            if(password.equals(newPassword)) {
                toReturn = true;
            }
        }
        return toReturn;
    }

    private void updatePass(String userid, String newPass) {               // POST: Updates the password to the specified
        String[] passwords = userMap.get(userid);                          // new pass
        if(passwords.length < 3) {
            String[] newPasswords = new String[passwords.length + 1];
            newPasswords[0] = newPass;
            for(int i = 0; i < passwords.length; i++) {
                newPasswords[i + 1] = passwords[i];
            }
            userMap.put(userid, newPasswords);
        } else {
            passwords[0] = newPass;
            for(int i = 0; i < passwords.length - 1; i++) {
                passwords[i + 1] = passwords[i];
            }
            userMap.put(userid, passwords);
        }
    }

    private void printUserMap() {                                           // POST: Prints out all the users in the map
        for(String key : userMap.keySet()) {
            System.out.print("User: " + key + " Passwords: ");
            for(String value : userMap.get(key)) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }

}
