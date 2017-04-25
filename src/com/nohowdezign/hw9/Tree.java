package com.nohowdezign.hw9;

/**
 * @author Noah Howard
 * This class contains variables specific to the tree. Makes it easier to access certain data about trees.
 */
public class Tree {
    private boolean onFire = false;                                      // Whether the tree is on fire or not

    public boolean isOnFire() {                                          // POST: Returns true if tree is on fire
        return onFire;
    }

    public void setOnFire(boolean onFire) {                              // POST: Set the tree to on fire or not
        this.onFire = onFire;
    }

    public String toString() {                                           // POST: Returns a pretty tree graphic if the tree
        return isOnFire() ? "\uD83D\uDD25" : "\uD83C\uDF32";                         // hasn't turned into ash.
    }
}
