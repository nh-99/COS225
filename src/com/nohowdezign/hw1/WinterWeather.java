package com.nohowdezign.hw1;

import java.util.Scanner;

/**
 * @author Noah Howard
 * This class runs a set of simple statistics on user-supplied weather values. It will return the temperature in celcius,
 * fahreheit, a histogram of the windchill, and the numeric windchill values, as well as the average and standard deviation
 * of the temperature to the user.
 */
public class WinterWeather {

    public static void main(String[] args) {
        System.out.println("Programmer: Noah Howard");
        System.out.println("Winter Temperature Statistics\n");

        System.out.print("Enter number of temperatures: "); // Read user input to get variables for temp count, windspeed, and temperatures
        Scanner sc = new Scanner(System.in);
        String count = sc.nextLine();

        System.out.print("Enter wind speed (mph): ");
        String windspeed = sc.nextLine();

        System.out.print("Enter list of Celsius temperatures and press ENTER: ");
        String temperatures = sc.nextLine();

        double sum = 0.0;
        double sumOfSquares = 0.0;

        // Loop through our user supplied temperatures, splitting at the space character
        for(String temperature : temperatures.split("\\s")) {
            System.out.printf("\nC: %-12.2f F: %-12.2f WC: %-12.2f",
                    Double.valueOf(temperature),
                    fahrenheit(Double.valueOf(temperature)),
                    windchill(fahrenheit(Double.valueOf(temperature)), Double.valueOf(windspeed))
            ); // Print out a line of statistics showing the temperature in celsius, the temperature in fahrenheit, and the wind chill

            // Draw a histogram with *'s
            histogram((int) Math.ceil(windchill(fahrenheit(Double.valueOf(temperature)), Double.valueOf(windspeed))));

            // Add the temperature values to our sums for cumulative statistics
            sum += fahrenheit(Double.valueOf(temperature));
            sumOfSquares += Math.pow(fahrenheit(Double.valueOf(temperature)), 2);
        }

        // Print out some cumulative statistics (count, average, standard deviation)
        System.out.println(String.format("\n\nNumber of Temperatures: %d", Integer.valueOf(count)));
        System.out.println(String.format("Fahrenheit Average: %.2f", average(sum, Integer.valueOf(count))));
        System.out.println(String.format("Fahrenheit StDev: %.2f", stddev(sumOfSquares, sum, Integer.valueOf(count))));
    }

    // PRE: Count must be >0
    // POST: return arithmetic mean
    public static double average(double sum, int count) {
        return sum / count;
    }

    // PRE: Count must be >0
    // POST: return standard deviation
    public static double stddev(double sumofsquares, double sum, int count) {
        return Math.sqrt((sumofsquares - Math.pow(sum, 2) / count) / count);
    }

    // PRE: n must be >0
    // POST: display row of n stars
    public static void histogram(int n) {
        for(; n > 0; n--) {
            System.out.print("*");
        }
    }

    // PRE: None
    // POST: return Fahrenheit equivalent of temp
    public static double fahrenheit(double temp) {
        return temp * 1.8 + 32.0;
    }

    // PRE: None
    // POST: return wind chill based on Fahrenheit temperature and wind speed in miles/hour
    public static double windchill(double temp, double windspeed) {
        return 35.74 + 0.6215 * temp - 35.75 * Math.pow(windspeed, 0.16) + 0.4275 * temp * Math.pow(windspeed, 0.16);
    }

}
