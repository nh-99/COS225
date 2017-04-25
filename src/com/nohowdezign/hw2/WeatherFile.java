package com.nohowdezign.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormatSymbols;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Noah Howard
 * This program will scan 2 input files, averages.txt and weather.txt, and ingest the values. After that, it will pretty
 * print all of them as well as run some simple statistics on the data. It outputs all of this to an output.txt file.
 */
public class WeatherFile {

    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        // Initialize file output and file scanner
        PrintWriter writer = initOutputWriter();
        Scanner weatherFileScanner = initWeatherFileScanner();

        // Initialize all of the variables for storing values in that will need to be accessed by multiple methods
        double sumOfTemps = 0;
        double sumOfWinds = 0;
        int count = 0;
        HashMap<String, Integer> numberOfReadings = new HashMap<>();
        String month = "";
        double monthValue = 0;

        // Scan through the weather file
        while(weatherFileScanner.hasNext()) {
            String[] lineParts = weatherFileScanner.nextLine().split("\t"); // Split file at tabs
            writeData(writer, lineParts); // Write out the (essentially) raw data from the files
            sumOfTemps += Double.valueOf(lineParts[2]); // Sum up temps and wind speeds
            sumOfWinds += Double.valueOf(lineParts[4]);
            numberOfReadings.put(lineParts[0], numberOfReadings.getOrDefault(lineParts[0], 0) + 1); // Put reading count per town
            month = parseMonth(lineParts[1].split("/")[0]); // Get the month that this data is in
            count += 1;
        }

        writeOveralls(writer, sumOfTemps, sumOfWinds, count); // Write overall data trends
        writeNumberOfReadings(writer, numberOfReadings); // Write the number of readings taken

        weatherFileScanner = initAverageFileScanner(); // Scan the averages file
        writer.println("Historical Averages:");
        while(weatherFileScanner.hasNext()) {
            String[] lineParts = weatherFileScanner.nextLine().split("\\s"); // Split at spaces
            writer.println(String.format("%-11s%s", lineParts[0], lineParts[1])); // Write out raw averages
            if(lineParts[0].equals(month)) {
                monthValue = Double.valueOf(lineParts[1]); // Set historical average for the month that we're looking at
            }
        }

        // Print out the historical average
        writer.println();
        double average = (average(sumOfTemps, count) - monthValue);
        writer.print(String.format("Difference between this %s from historical average is %.2f", month, average));

        writer.close();
    }

    // PRE: None
    // POST: None
    public static PrintWriter initOutputWriter() throws FileNotFoundException, UnsupportedEncodingException {
        // Initialize printwriter and write out first few lines
        PrintWriter writer = new PrintWriter("output.txt", "UTF-8");
        writer.println("Weather Watch by Noah Howard\n");
        writer.println("Town\t\tDate\t\tTemp\tDirection\tWind Speed\tReporter");
        return writer;
    }

    // PRE: None
    // POST: Scanner for the weather.txt file
    public static Scanner initWeatherFileScanner() throws FileNotFoundException {
        File weatherFile = new File("weather.txt");
        return new Scanner(weatherFile);
    }

    // PRE: String list lineParts must have a length of 6
    // POST: None
    public static void writeData(PrintWriter writer, String[] lineParts) {
        writer.print(lineParts[0].toUpperCase() + "\t\t"); // Print out town name
        writer.print(lineParts[1] + "\t"); // Print out date
        writer.print(String.format("%.2f", Double.valueOf(lineParts[2])) + "\t"); // Print out temp
        writer.print(lineParts[3] + "\t\t\t"); // Print out wind direction
        writer.print(String.format("%.2f", Double.valueOf(lineParts[4])) + "\t\t"); // Print out wind speed
        writer.println(condenseName(lineParts[5])); // Print out reporter initials
    }

    // PRE: None
    // POST: The first and last initial in uppercase
    public static String condenseName(String name) {
        // Convert string to uppercase to return initials in uppercase & split string at space character
        char[] partsOfName = name.toUpperCase().toCharArray();
        // Concat first and last to get the first and last initials
        return String.valueOf(partsOfName[0]) + String.valueOf(partsOfName[partsOfName.length - 1]);
    }

    // PRE: None
    // POST: None
    private static void writeOveralls(PrintWriter writer, double sumOfTemps, double sumOfWind, int count) {
        // Print out averages of temperature and wind speed
        writer.println(String.format("\nOverall Average Temperature: %.2f", average(sumOfTemps, count)));
        writer.println(String.format("Overall Average Wind Speed: %.2f\n", average(sumOfWind, count)));
    }

    // PRE: None
    // POST: None
    private static void writeNumberOfReadings(PrintWriter writer, HashMap<String, Integer> numberOfReadings) {
        writer.println("Number of readings:");
        for(String city : numberOfReadings.keySet()) {
            // Print out each city name, followed by two tabs, then the total count of occurances in the data file
            writer.println(String.format("%s\t\t%d", city.toUpperCase(), numberOfReadings.get(city)));
        }
        writer.println();
    }

    // PRE: None
    // POST: Scanner of averages file
    public static Scanner initAverageFileScanner() throws FileNotFoundException {
        File weatherFile = new File("averages.txt");
        return new Scanner(weatherFile);
    }

    // PRE: Count must be >0
    // POST: return arithmetic mean
    public static double average(double sum, int count) {
        return sum / count;
    }

    // PRE: Date is a string with a number in it between 1 and 12
    // POST: A string containing the value of the month (e.g 1 returns January)
    public static String parseMonth(String date) {
        // Format the month number as a string month using a built in Java class
        return new DateFormatSymbols().getMonths()[Integer.valueOf(date)-1];
    }

}
