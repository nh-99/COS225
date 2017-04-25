package com.nohowdezign.hw7;
import java.util.Stack;

public class Truck {
	private String license;							// license number
	private String destination;						// destination city
	private int capacity;							// number of refrigerators truck holds
	private int nFrig;								// number of refrigerators on truck
	private Stack<Order> contents;					// stack of contents
		
	public Truck  ( )								// POST: empty truck
	{	license = new String ();
		destination = new String ();
		capacity = 0;
		nFrig = 0;
		contents = new Stack<Order> ();
	}
	
	public Truck  (String lic, int cap)				// POST: empty truck with license and capacity
	{	license = lic;
		capacity = cap;
		nFrig = 0;
		destination = new String ();
		contents = new Stack<Order> ();
	}
	
	public Truck (String lic, String des, int cap)	// POST: empty truck with license, capacity, destination
	{	license = lic;
		destination = des;
		capacity = cap;
		nFrig = 0;
		contents = new Stack<Order> ();
	}
	
	// accessors and mutators
	public int getnFrig() {
		return nFrig;	}
	
	public void setnFrig(int nFrig) {
		this.nFrig = nFrig;	}
	
	public String getLicense() {
		return license;	}
	
	public void setLicense(String license) {
		this.license = license;	}
	
	public String getDestination() {
		return destination;	}
	
	public void setDestination(String destination) {
		this.destination = destination;	}
	
	public int getCapacity() {
		return capacity;	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;	}
	
	public Stack<Order> getContents() {
		return contents;	}
	
	public void setContents(Stack<Order> contents) {
		this.contents = contents;	}
	
	public void addOrder (Order o) 					// PRE: order fits on truck	
	{	contents.push(o);							// POST: add order to truck
		nFrig = nFrig + o.getnRefrigerators();		// update number of refrigerators on truck
	}
	
	public boolean isFull ()						// POST: return true if truck is at capacity
	{	return capacity == nFrig;	}
	
	public String toString ()						// POST: return string representation
	{	return license + "*" + destination + "*" + "" + nFrig;	}
}

