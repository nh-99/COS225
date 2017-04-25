package com.nohowdezign.hw7;

public class Customer {
	
	private int custID;								// customer identification
	private String name;							// customer name
	private String address;							// customer address
	private String town;							// customer town
	
	public Customer ( )								// POST: empty customer
	{	custID = 0;
		name = new String ( );
		address = new String ( );
		town = new String ( );
	}
	
	// accessors and mutators
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTown() {
		return town;
	}
	public void setTown(String town) {
		this.town = town;
	}
	public String toString ( )						// POST: return string representation
	{	return custID + " " + name + " " + town;
	}

}
