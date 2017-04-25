package com.nohowdezign.hw7;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Noah Howard
 * This program simulates trucks delivering orders for customers. It uses a FIFO stack to put orders in and lists to store
 * trucks and customers. It ingests data from data files that have customer and order info.
 */
public class TruckScheduler {
    final static double RENTALCOST = 50.0;                                 // Daily cost of rental truck
    final static int TRUCKCAP = 6;                                         // Truck and rental truck capacity

    public static void main(String[] args) throws FileNotFoundException {
        TruckScheduler truckScheduler = new TruckScheduler();              // This class for calling private methods
        ArrayList<Customer> customers = truckScheduler.ingestCustomers();  // List of customers
        ArrayList<Truck> loadingZone = new ArrayList<>();                  // List of trucks

        CircularArrayQueue<Order> orders = truckScheduler.ingestOrders();  // Daily queue of orders
        CircularArrayQueue<Truck> garage = new CircularArrayQueue<>();     // Queue of trucks

        garage.enqueue(new Truck("aaaaa", TRUCKCAP));
        garage.enqueue(new Truck("bbbbb", TRUCKCAP));
        garage.enqueue(new Truck("ccccc", TRUCKCAP));

        truckScheduler.processOrders(orders, customers, loadingZone, garage);
    }

                                                                           // POST: Returns list of customers from data file
    private ArrayList<Customer> ingestCustomers() throws FileNotFoundException {
        ArrayList<Customer> customers = new ArrayList<>();                 // List of customers to return
        File f = new File("data/hw7/customers.txt");
        Scanner s = new Scanner(f);                                        // Scan file of customer data
        int count = 1;
        Customer customerToQueue = new Customer();                         // Current customer we are putting in the list

        while(s.hasNextLine()) {
            String line = s.nextLine();                                    // Get line data
            if(count % 4 == 1) {                                           // If conditions for each line of the customer
                customerToQueue.setCustID(Integer.valueOf(line));          // info (id, name, address, town)
            } else if(count % 4 == 2) {
                customerToQueue.setName(line);
            } else if(count % 4 == 3) {
                customerToQueue.setAddress(line);
            } else {
                customerToQueue.setTown(line);                             // We have all of this customer's data, so we
                customers.add(customerToQueue);                            // add them to the list the reset the temp var
                customerToQueue = new Customer();
            }
            count++;
        }

        return customers;
    }

                                                                           // POST: Returns orders in queue imported from file
    private CircularArrayQueue<Order> ingestOrders() throws FileNotFoundException {
        CircularArrayQueue<Order> orders = new CircularArrayQueue<>();     // Array to return
        File f = new File("data/hw7/orders.txt");
        Scanner s = new Scanner(f);                                        // Construct scanner for file
        while(s.hasNextLine()) {
            String line = s.nextLine();
            String[] lineParts = line.split("\\s");                  // Split each line at spaces

            Order order = new Order();                                     // Create a new order object and populate it
            order.setorderID(Integer.valueOf(lineParts[0]));               // from our scanned data values
            order.setcustID(Integer.valueOf(lineParts[1]));
            order.setnRefrigerators(Integer.valueOf(lineParts[2]));

            orders.enqueue(order);                                         // Add our order to the queue
        }
        return orders;
    }

                                                                           // POST: Runs all the logic associated with
                                                                           // order processing and display
    private void processOrders(CircularArrayQueue<Order> orders, ArrayList<Customer> customers,
                               ArrayList<Truck> loadingZone, CircularArrayQueue<Truck> garage) {
        double cost = 0.0;                                                 // Cost of rental cars
        while(orders.size() > 0) {                                         // Loop through orders
            Order currentOrder = orders.dequeue();                         // Get the next order
            Customer currentCustomer = null;                               // Initialize variable to store customer
            Truck currentTruck = null;                                     // Initialize variable to store truck

            for(Customer customer : customers) {                           // Get the current customer
                if(customer.getCustID() == currentOrder.getcustID()) {
                    currentCustomer = customer;
                }
            }
                                                                           // Print out order to process
            System.out.println(String.format("Processing order %d %s %s %d refrigerators.", currentOrder.getcustID(),
                    currentCustomer.getName(), currentCustomer.getTown(), currentOrder.getnRefrigerators()));

            if(!loadingZone.isEmpty()) {                                   // Check the loading zone for trucks that can
                for(Truck loadingTruck : loadingZone) {                    // store the amount of fridges needed for our
                                                                           // current order & correct destination
                    if(loadingTruck.getDestination().equals(currentCustomer.getTown()) &&
                            (loadingTruck.getnFrig() + currentOrder.getnRefrigerators()) < TRUCKCAP) {
                        currentTruck = loadingTruck;
                        System.out.println(String.format("Using truck %s from loading zone.", currentTruck.getLicense()));
                    }
                }
            }

            if(currentTruck == null) {                                     // If there is no truck from the loading zone,
                System.out.println("No truck available in loading zone."); // either get one from the garage or get a rental
                if(garage.size() > 0) {
                    currentTruck = garage.dequeue();
                    System.out.println(String.format("Getting truck %s from garage.", currentTruck.getLicense()));
                } else {
                    System.out.println("Garage is empty.");
                    RentalTruck truck = new RentalTruck(RENTALCOST);
                    System.out.println("Got rental truck " + truck.getLicense());
                    cost += RENTALCOST;

                    currentTruck = truck;
                }
            }

            currentTruck.addOrder(currentOrder);                           // Add our current order to the truck

            if(currentTruck.getDestination().equals("")) {                 // If our truck has no destination yet, set it
                currentTruck.setDestination(currentCustomer.getTown());
                loadingZone.add(currentTruck);                             // Add to loading zone and print info
                if(currentTruck instanceof RentalTruck) {
                    System.out.println(String.format("Adding rental truck %s to city %s with %d refrigerators.", currentTruck.getLicense(),
                            currentCustomer.getTown(), currentOrder.getnRefrigerators()));
                } else {
                    System.out.println(String.format("Adding truck %s to city %s with %d refrigerators.", currentTruck.getLicense(),
                            currentCustomer.getTown(), currentOrder.getnRefrigerators()));
                }
            }
                                                                           // If the truck is full, send it off and create
            if(currentTruck.getnFrig() == TRUCKCAP) {                      // a report, otherwise print it's fridge count
                System.out.println(String.format("Truck %s is now full.", currentTruck.getLicense()));
                reportDeliveriesByTruck(currentTruck, customers);
                loadingZone.remove(currentTruck);
            } else if((currentTruck.getnFrig() - currentOrder.getnRefrigerators() > 0)) {
                System.out.println(String.format("Truck %s now holds %d refrigerators.", currentTruck.getLicense(),
                        currentTruck.getnFrig()));
            }

            System.out.println();
        }

        System.out.println();                                               // Generate a report for all the partial trucks
        System.out.println();                                               // that are still in the loading bay
        System.out.println("Remaining Truck Delivery Report");
        for(Truck t : loadingZone) {
            reportDeliveriesByTruck(t, customers);
        }

        System.out.println();                                               // Print the total cost of rental trucks
        System.out.println(String.format("Cost of %d rental trucks is %s", (int) (cost/RENTALCOST),
                NumberFormat.getCurrencyInstance().format(cost)));
    }
                                                                            // POST: Prints a report of deliveries for a
                                                                            // given truck
    private void reportDeliveriesByTruck(Truck truck, ArrayList<Customer> customers) {
        System.out.println();                                               // Print out basic info
        System.out.println(String.format("Report of deliveries by truck: %s", truck.getLicense()));
        System.out.println("Destination: " + truck.getDestination());

        for(Order o : truck.getContents()) {                                // Print out info for each order (customer ID
            Customer customer = null;                                       // customer name and fridge count)
            for(Customer c : customers) {
                if(c.getCustID() == o.getcustID()) {
                    customer = c;
                }
            }
            System.out.println(String.format("Customer: %s\t%s\t\t\t%d refrigerators", customer.getCustID(),
                    customer.getName(), o.getnRefrigerators()));
        }
    }

}
