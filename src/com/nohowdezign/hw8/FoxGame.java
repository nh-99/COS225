package com.nohowdezign.hw8;

import java.util.LinkedList;

/**
 * @author Noah Howard
 * This stores the state of the game. Where the farmer is located and what items are on either bank.
 */
public class FoxGame {
    private String farmerLocation;                                          // location of farmer: north or south
    private LinkedList<String> southBank = new LinkedList<>();              // list of items on south bank
    private LinkedList<String> northBank = new LinkedList<>();              // list of items on north bank

    public FoxGame() {                                                      // POST: Add the 3 items to the south bank
        southBank.add("chicken");                                           // and put the farmer on the south bank. The
        southBank.add("fox");                                               // north bank is empty.
        southBank.add("grain");
        farmerLocation = "south";
    }

    public boolean won() {                                                  // POST: return true if game won
        if(northBank.size() == 3) {
            return true;
        }
        return false;
    }

    public boolean lost() {                                                 // POST: return true if game lost
        if(farmerLocation.equals("south")) {
            if(northBank.contains("fox") && northBank.contains("chicken") ||// If chicken & grain or fox & chicken are at
                    northBank.contains("grain") && northBank.contains("chicken")) {// north bank
                return true;
            }
        } else if(farmerLocation.equals("north")) {
            if(southBank.contains("fox") && southBank.contains("chicken") ||// If chicken & grain or fox & chicken are at
                    southBank.contains("grain") && southBank.contains("chicken")) {// south bank
                return true;
            }
        }
        return false;
    }

    public boolean found(String item) {                                     // POST: Return true if item is on current
        if(farmerLocation.equals("south")) {                                // bank or item is none
            if(southBank.contains(item)) {
                return true;
            }
        } else if(farmerLocation.equals("north")) {
            if(northBank.contains(item)) {
                return true;
            }
        }
        return false;
    }

    public void transport(String item) {                                    // PRE: item is on current bank or string none
        if(!item.equals("none")) {                                          // POST: item removed from current bank, added
            if(farmerLocation.equals("south")) {                            // to opposite bank, and farmer is on opposite
                northBank.add(item);                                        // bank
                southBank.remove(item);
            } else if(farmerLocation.equals("north")) {
                southBank.add(item);
                northBank.remove(item);
            }
        }
        farmerLocation = getOtherBank();
    }

    public String getFarmerBank() {                                         // POST: Return bank holding farmer: north/south
        return farmerLocation;
    }

    public String getOtherBank() {                                          // POST: Return bank not holding farmer
        return farmerLocation.equals("south") ? "north" : "south";
    }

    public String displaySouthBank() {                                      // POST: Return contents of south bank
        return southBank.toString();
    }

    public String displayNorthBank() {                                      // POST: Return contents of north bank
        return northBank.toString();
    }

}
