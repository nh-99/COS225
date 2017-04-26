package com.nohowdezign.hw10;

/**
 * Created by Noah on 4/25/2017.
 */
public class HashTrace {

    public static void main(String[] args) {
        HashTable testTable = new HashTable(100);
        String testWord = "diverse";
        System.out.println("Test Word Location: " + testWord.hashCode());
        testTable.insert(testWord);
        System.out.println("Test Word Hash: " + testTable.hash3(testWord));
    }

}
