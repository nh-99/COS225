package com.nohowdezign.hw4;

public class Inventory {
	
	public Product [ ] inventory;				// array of Product
	private int capacity;						// capacity of array
	private int size;							// number of elements used
	
	public Inventory ( )						// POST: empty inventory with capacity 100
	{
		capacity = 100;
		size = 0;
		inventory = new Product[capacity];
	}
	
	public Inventory (int cap)					// PRE:  cap > 0
	{											// POST: empty inventory with capacity cap
		capacity = cap;
		size = 0;
		inventory = new Product[capacity];
	}
	
	public int getSize( ) 						// POST: return number of elements
	{
		return this.size;
	}
		
	public Product getProduct (int index)		// PRE:  0 <= index < size
	{											// POST: return Product at index
		return inventory[index];
	}

	public void setProduct (Product p, int index)	// PRE:  0 <= index < size
	{												// POST: set Product at index
		inventory[index] = p;
	}
	
	public void insert (Product p)				// POST: value is added to array
	{
	    if(capacity == size) {                  // If array is too small resize it
	        resize();
        }
        inventory[size] = p;
	    size++;
	}

	public int sequentialSearch (String model)			
	// POST: return index of first occurrence of model id, else -1
	// examine array elements sequentially and return index
	{
	    for(int i = 0; i < size; i++) {          // Iterate through each value in array and see if it equals our model #
	        if(inventory[i].getModel().equals(model)) {
	            return i;
            }
        }
	    return -1;
	}
	
	public int binarySearch (String model)				
	// PRE: inventory is sorted by product model id
	// POST: return index of first occurrence of model id in inventory
	//       return negative value if not found, which is insert location
	//       uses binary search algorithm
	{
	    int first = 0;                           // Leftmost index
	    int last = size - 1;                     // Rightmost index
	    boolean found = false;                   // Found status
	    int mid = -1;                            // Middle index

	    while(!found && last >= first) {         // While still searching
	        mid = (first + last) / 2;            // Compute middle index
	        if(inventory[mid].getModel().equals(model)) { // Check if found
	            found = true;
            } else if(Integer.valueOf(model) < Integer.valueOf(inventory[mid].getModel())) {  // Prep to go left
	            last = mid - 1;
            } else {                             // Prep to go right
	            first = mid + 1;
            }
        }
        if(found) {
	        return mid;
        } else {
	        return (-1 - first);                 // Return negative value if not found
        }
	}
	
	public void selectionSort ( )				// POST: inventory is sorted by ascending model id			
	{  											// uses selection sort algorithm
        int mindex;
        for(int i = 0; i < inventory.length; i++) {
            mindex = i;                         // Index of current minimum
            for(int j = i+1; j < inventory.length; j++) { // Outer loop moves an element into place
                if(Integer.valueOf(inventory[j].getModel()) < Integer.valueOf(inventory[mindex].getModel())) {
                    mindex = j;                 // Inner loop finds index of minimum
                }
            }
            swap(mindex, i);                    // Swap current mimimum into place
        }
	}

										        // POST: Returns list of products of a given category
	public Product[] getProductsByCategory(String category) {
		Product[] products = new Product[capacity];// List of products to return
		int index = 0;                          // Current index in return array
		for(int i = 0; i < size; i++) {
			if(inventory[i].getCategory().equalsIgnoreCase(category)) {
				products[index] = inventory[i]; // If they are equal, add to return array
				index += 1;
			}
		}
		return products;
	}

	private void resize ( )						// POST: inventory is doubled in capacity
	{
	    capacity = capacity * 2;                // Double the capacity
	    Product[] temp = new Product[capacity]; // Temp array that is double the size
	    for(int i = 0; i < size; i++) {
	        temp[i] = inventory[i];             // Push each element from old array into temp array
        }
        inventory = temp;
	}
	
	private void swap (int mindex, int index)	// POST: products at indexes are exchanged
	{
	    Product temp = inventory[mindex];       // Temp variable to store product
	    inventory[mindex] = inventory[index];   // Swap second product into first ones place
	    inventory[index] = temp;                // Put temp into second products place
	}
	
}