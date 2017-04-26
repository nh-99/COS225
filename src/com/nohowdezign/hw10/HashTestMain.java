package com.nohowdezign.hw10;

import java.io.*;

/**
 * @author Noah Howard
 * This class loads in the data and tests out the hashing functions. It imports the data from the jumbledwords file and
 * prints the length of each cluster in the hashtable to an out.txt file.
 */
public class HashTestMain {
    private HashTable hashTable = new HashTable(1009);             // HashTable to store data in

    public static void main(String[] args) throws IOException {
        HashTestMain hashTestMain = new HashTestMain();                 // Declare main class
        hashTestMain.loadData();                                        // Import data
        hashTestMain.writeResults();                                    // Write results
    }

    private void loadData() {                                           // POST: Fills the hashTable
        BufferedReader reader = null;                                   // File reader

        try {
            File file = new File("data/hw10/jumbledwords.txt");// Open jumbled words file
            reader = new BufferedReader(new FileReader(file));          // Read file

            String lineText;
            while ((lineText = reader.readLine()) != null) {            // While we have text to read, insert into table
                hashTable.insert(lineText);
            }

        } catch (IOException e) {
            System.out.println("The file does not exist.");             // Exception here means file doesn't exist
        } finally {
            try {
                reader.close();
                System.out.println("Importing the data was successful.");
            } catch (IOException | NullPointerException e) {}
        }
    }

    private void writeResults() throws IOException {                    // POST: Creates out.txt, a file with the length
        String toWrite = "";                                            // of each cluster in the table
        for(int i = 0; i < hashTable.getCapacity(); i++) {              // Loop through hashTable and create string to write
            toWrite += String.valueOf(hashTable.getClusterLength(i)) + "\n";
        }
        FileWriter fw = new FileWriter("out.txt");             // Open out.txt
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(toWrite);                                              // Write to file
        bw.close();
        fw.close();
    }

}
