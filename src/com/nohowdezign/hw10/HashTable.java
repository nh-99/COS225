package com.nohowdezign.hw10;

import java.util.*;

public class HashTable {
    
    final int CAPACITY = 23;                        // capacity of hash table 
    ArrayList<LinkedList<String>> h ;               // array of  lists
    int size;                                        // number of items in hash table
    int capacity;                                    // capacity of hash table

    public HashTable( )                                // POST: empty table of CAPACITY with empty LinkedList objects
    {    capacity = CAPACITY;
        size = 0;
        h = new ArrayList < LinkedList<String> > () ;
        for (int k=0; k<capacity; k++)
            h.add(new LinkedList<String> ( ));
    }
    
    public HashTable(int cap)                        // PRE: cap > 0    
    {                                                // POST: empty table of capacity cap with empty LinkedList objects
        capacity = cap;
        size = 0;
        h = new ArrayList < LinkedList<String> > (capacity) ;
        for (int k=0; k<capacity; k++)
            h.add(new LinkedList<String> ( ));
        size = 0;
    }
    
    public boolean isPresent(String target)         // PRE: table is not empty
    {                                               // POST: return true if target is in table; else false
        int index = hash3(target);
        if (h.get(index).contains(target))
            return true;
        else
            return false;
    }
    
    boolean isEmpty ( )                             // POST: return true if table is empty
    {    return size == 0;
    }
    
    public void insert (String word)                // POST: if not found, add word to hash table
    {    int index = hash3(word);
        LinkedList<String> temp = h.get(index);
        if (!temp.contains(word))
        {    temp.add(word);
            h.set(index, temp);
            size++;
        }
    }
    
    public String toString ()                        // POST: return words as a string
    {    String result = "";
        for (int k = 0; k < capacity; k++)
        {    int listLength = h.get(k).size();
            result = result + "Index " + k + " : ";
            for (int j = 0; j < listLength; j++)
                result = result + h.get(k).get(j) + " ";
            result = result + "\n";
            }
        return result;
    }
    
    public int getCapacity( )                        // POST: return size of hash table
    {
        return capacity;
    }
    
    public int getClusterLength (int index)          // POST return length of cluster at index
    {
        return h.get(index).size();                  // Get LinkedList at index, then get the size of it
    }
    
    // first hash function to use on the first run
    private int hash1 (String word)                  // POST: return hash index of word
    {    int sum = 0;
        for (int k = 0; k< word.length(); k++)
            sum = sum + (int)(word.charAt(k));
        return  sum % capacity;   
    }
    
    
    // second hash function to use on the second run
    private int hash2 (String word)                  // POST: return hash index of word
    {  int seed = 131; 
       long sum = 0;
       for(int k = 0; k < word.length(); k++)
             sum = (sum * seed) + (int)(word.charAt(k));
       return (int)(Math.abs(sum) % capacity);
    }

    // Third hash function we use on the third run
    // SOURCE: http://algs4.cs.princeton.edu/34hash/
    public int hash3(String word) {                 // POST: Return hash index of word
        return (word.hashCode() & 0x7fffffff) % getCapacity();
    }
}
