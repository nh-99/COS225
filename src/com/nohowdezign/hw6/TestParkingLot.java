package com.nohowdezign.hw6;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * @author Noah Howard
 * This class tests out our stack to see if everything works. It ingests the data file and then creates a report for all
 * of the cars in the lot.
 */
public class TestParkingLot {
    private ArrayStack carStack = new ArrayStack(5);                       // Store the cars in a stack
    private double parkingMeterTotal = 0.0;                                     // Store the total cost for parking meters

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Parking Report\nBy Programmer: Noah Howard\n");     // Print out program info

        TestParkingLot parkingLot = new TestParkingLot();
        parkingLot.ingestData("data/hw6/lot.txt");                      // Run the program
    }

    private void ingestData(String filePath) throws FileNotFoundException {     // POST: Ingests the data and prints a report
        File f = new File(filePath);                                            // Get the lot file
        Scanner s = new Scanner(f);                                             // Scan lot file
        double parkingFeeSum = 0.0;                                             // Sum of parking fees

        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] entryParts = line.split("\\s");                      // Split at space
            String[] timeParts = entryParts[2].split(":");                // Split time at : to extract hours and mins
                                                                                 // Construct car object from data values
            Car car = new Car(entryParts[1], new Time(Integer.valueOf(timeParts[0]), Integer.valueOf(timeParts[1])));
            if(entryParts[0].equals("A")) {                                      // Car arrived
                if(carStack.size() < 5) {                                        // Push car into stack
                    System.out.println(entryParts[1] + " parked at " + entryParts[2]);
                    carStack.push(car);
                } else {                                                         // Lot full
                    System.out.println(entryParts[1] + " was turned away at " + entryParts[2] + " - LOT IS FULL");
                }
            } else {
                Car initialCar = removeCarFromStack(car);                        // Remove car from parking lot
                if(initialCar != null) {                                         // Get parking fee from arrive and depart times
                    double price = getParkingFee(initialCar.getTime(), car.getTime());
                    parkingFeeSum += price;                                      // Format price and print info
                    String formattedPrice = NumberFormat.getCurrencyInstance().format(price);
                    System.out.println(car.getLicense() + " left at " + car.getTime().toString() + " paying " + formattedPrice);
                } else {                                                         // Person departed but never arrived
                    System.out.println(car.getLicense() + " does not have a car in the lot");
                }
            }
        }
                                                                                 // Print out totals
        System.out.println("\nParking fees generated: " + NumberFormat.getCurrencyInstance().format(parkingFeeSum));
        System.out.println("Street meter fees paid: " + NumberFormat.getCurrencyInstance().format(parkingMeterTotal));
    }

    private Car removeCarFromStack(Car car) {                                    // POST: Returns the car that was removed
        boolean done = false;                                                    // from the stack, or null
        Car toReturn = null;
        ArrayStack tempStack = new ArrayStack(5);                           // This is the street the cars get parked
        while(!done) {                                                           // on, or temporary storage while we get
            try {                                                                // a specific car object out
                Car tempCar = (Car) carStack.pop();
                if(tempCar.compareTo(car) > 0) {
                    done = true;                                                 // We found our car so we exit the for
                    toReturn = tempCar;                                          // loop
                } else {
                    parkingMeterTotal += 0.25;
                    tempStack.push(tempCar);
                }
            } catch(EmptyCollectionException e) {
                done = true;                                                     // No more cars left to find, so we're done
            }
        }

        while(tempStack.size() > 0) {                                             // Push cars back into carstack from
            carStack.push(tempStack.pop());                                       // the temp stack
        }

        return toReturn;
    }
                                                                                  // POST: Returns cost of parking given
    private double getParkingFee(Time timeParked, Time timeLeft) {                // two times
        int timeParkedHours = (int) Math.ceil((timeParked.subtract(timeLeft) / 60.0f));
        return timeParkedHours * 4.5;
    }

}
