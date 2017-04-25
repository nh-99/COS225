package com.nohowdezign.hw4;

public class Product {
	
	private String category;					// category (ex. Microscopes)			
	private String model;						// unique model number (ex. 490620)
	private String description;					// text description
	private int quantity;						// quantity on hand
	private int [ ] history;					// 12 month history of number sold last year starting in Jan
	
	public Product ( )							// POST: empty product
	{	category = new String ();
		model = new String ();
		description = new String ();
		quantity = 0;
		history = new int [12];
	}

	// accessors and mutators
	public String getCategory() 
	{	return category;	}

	public void setCategory(String category) 
	{	this.category = category;	}

	public String getModel() 
	{	return model;	}

	public void setModel(String model) 
	{	this.model = model;	}

	public String getDescription() 
	{	return description; 	}

	public void setDescription(String description) 
	{	this.description = description;	}

	public int getQuantity() 
	{	return quantity;	}

	public void setQuantity(int quantity) 
	{	this.quantity = quantity;	}

	public int [] getHistory( ) 			
	{	return history; 	}					

	public void setHistory(int [] history) 	
	{	this.history = history;						
	}
	
	public String toString ()						// POST: return data member in blank separated string
	{	String result =  category + " " + model + " " + description + " " + quantity + " ";
		for (int k=0; k<12; k++)
			result = result.concat(history[k] + " ");
		return result;
	}

}
