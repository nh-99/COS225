package com.nohowdezign.hw11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Noah Howard
 * This class will take text and encode it into morse code. It will then convert the morse code back to text using a
 * binary tree.
 */
public class MorseCode {
    private TreeNode<String> rootNode = new TreeNode<>();                          // The root node of the binary tree
    private String[] morseCodeIndexer = new String[27];                            // Array to store index of alphabet to
                                                                                   // binary in
    public static void main(String[] args) throws FileNotFoundException {          // main method
        MorseCode morseCode = new MorseCode();                                     // Instantiate main class
        morseCode.loadData();                                                      // Load data from file
        System.out.print("Preorder Tree Contents: ");
        morseCode.preorderPrint(morseCode.rootNode);                               // Print preordered data
        System.out.print("\n");

        morseCode.populateIndexer(morseCode.rootNode, "");              // Add morse code -> alphabet lookups to
                                                                                   // array
        System.out.print("Enter English message: ");
        Scanner s = new Scanner(System.in);                                        // Read user input message
        String m = morseCode.encode(s.nextLine());                                 // Convert to morse code
        System.out.println("m = " + m);
        String e = morseCode.decode(m.replace("| ", "").split("\\s"));
        System.out.println("Message: " + e);                                       // Convert back to english and print
    }

    public void preorderPrint(TreeNode<String> node) {                             // POST: Prints out elements of tree
        if(node == null) return;                                                   // in pre order
        if(node != rootNode)
            System.out.print(node.getElement() + " ");
        preorderPrint(node.getLeft());
        preorderPrint(node.getRight());
    }

    private void loadData() throws FileNotFoundException {                         // POST: Loads data into root node
        Scanner s = new Scanner(new File("data/hw11/morsecode.txt"));
        while(s.hasNextLine()) {
            String[] dataLine = s.nextLine().split("\\s");
            TreeNode<String> currentNode = rootNode;                               // Call to recursive function for each
            createLeaf(currentNode, dataLine, 1, dataLine.length, dataLine[0]);// line of morse code
            rootNode = currentNode;                                                // Update root node with new values
        }
        s.close();
    }
                                                                                    // POST: Creates a leaf in current node
    private TreeNode<String> createLeaf(TreeNode<String> currentNode, String[] data, int count, int max, String value) {
        if(count == max) {
            currentNode = new TreeNode<>(value);
            return currentNode;
        }

        if(data[count].equals("o")) {                                                // Recursively call self to create new
            currentNode.setLeft(createLeaf(currentNode.getLeft(), data, count + 1, max, value));// leaf nodes
        } else if(data[count].equals("-")) {
            currentNode.setRight(createLeaf(currentNode.getRight(), data, count + 1, max, value));
        }
        return currentNode;
    }

    private void populateIndexer(TreeNode<String> node, String morseCode) {          // POST: Populates indexer array with
        if(node == null) return;                                                     // morse code values
        if(node != rootNode) {
            morseCodeIndexer[(int) node.getElement().charAt(0) - 97] = morseCode;
        }
        populateIndexer(node.getLeft(), morseCode + "o");
        populateIndexer(node.getRight(), morseCode + "-");
    }

    private String encode(String next) {                                             // POST: Returns message in morse code
        String m = "";
        for(String s : next.replace(" ", "").split("")) {
            m += morseCodeIndexer[(int)s.charAt(0) - 97] + " | ";
        }
        return m;
    }

    private String decode(String[] morseCode) {                                      // POST: Decodes message from morse
        String s = "";                                                               // code to english using binary tree
        for(int i = 0; i < morseCode.length; i++) {
            String[] morseDigits = morseCode[i].split("");                     // Each digit of morse code
            TreeNode<String> currentNode = rootNode;
            for(int z = 0; z < morseDigits.length + 1; z++) {                        // Loop through digits
                if(morseDigits.length == z) {
                    s += currentNode.getElement() + " ";                             // Add current node to return string
                } else {
                    if(morseDigits[z].equals("o")) {                                 // Not at the end, so get next node
                        currentNode = currentNode.getLeft();
                    } else {
                        currentNode = currentNode.getRight();
                    }
                }
            }
        }

        return s;
    }

}
