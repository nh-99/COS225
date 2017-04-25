package com.nohowdezign.hw7;

import java.util.Random;

/**
 * @author Noah Howard
 * This class is derivce from the Truck class and costs a bit more since we have to rent it when all of our companies
 * trucks are in use.
 */
public class RentalTruck extends Truck {
    private double cost;                                                             // Fee of a rental truck

    public RentalTruck(double cost) {
        super();
        this.setCapacity(6);
        this.cost = cost;
        this.setLicense(String.valueOf(new Random().nextInt(99999) + 11111)); // Create random licence between
    }                                                                                 // 11111 and 99999

    public double getCost() {                                                        // POST: Returns cost value of rental
        return this.cost;
    }
}
