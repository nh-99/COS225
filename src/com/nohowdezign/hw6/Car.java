package com.nohowdezign.hw6;

/**
 * @author Noah Howard
 * This class contains the attributes of a Car, so that we can reuse it in our stack for each car and call the
 * generic methods from here.
 */
public class Car implements Comparable<Car> {
    private String license;                                              // The car's license plate
    private Time time;                                                   // Time the car was parked

    public Car() {                                                       // POST: Car object with blank values
        license = "";
        time = new Time();
    }

    public Car(String license, Time time) {                              // POST: Car object with supplied license and time
        this.license = license;
        this.time = time;
    }

    public Car(Car car) {                                                // POST: Car object with values from supplied car
        this.license = car.getLicense();
        this.time = car.getTime();
    }

    public String getLicense() {                                         // POST: Returns the license as a string
        return license;
    }

    public void setLicense(String license) {                             // POST: Sets the license to a new value
        this.license = license;
    }

    public Time getTime() {                                              // POST: Get the time the car was parked as a
        return time;                                                     // Time object
    }

    public void setTime(Time time) {                                     // POST: Set the time with a supplied time object
        this.time = time;
    }

    public String toString() {                                           // POST: Return the Car's important values as a string
        return String.format("License: %s Time parked: %s", this.license, this.time.toString());
    }

    @Override
    public int compareTo(Car other) {                                     // POST: 1 or 0 depending on if the license
        return other.getLicense().equals(license) ? 1 : 0;                // plates of each car are the same or not
    }
}
