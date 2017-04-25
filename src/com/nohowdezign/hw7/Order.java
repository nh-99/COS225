package com.nohowdezign.hw7;

public class Order {
	private int orderID;					// order identification
	private int custID;						// customer identification
	private int nRefrigerators;				// number of refrigerators in order
	
	// accessors and mutators
	public int getorderID() {
		return orderID;
	}
	public void setorderID(int ordrID) {
		this.orderID = ordrID;
	}
	public int getcustID() {
		return custID;
	}
	public void setcustID(int c) {
		this.custID = c;
	}
	public int getnRefrigerators() {
		return nRefrigerators;
	}
	public void setnRefrigerators(int nRefrigerators) {
		this.nRefrigerators = nRefrigerators;
	}
	public String toString()				// POST: return string representation
	{	return (orderID + " " + custID + " " + nRefrigerators);
	}
}


