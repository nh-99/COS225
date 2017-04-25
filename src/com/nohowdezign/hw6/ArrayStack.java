package com.nohowdezign.hw6;

public class ArrayStack<T> implements StackADT<T> {

	private int capacity;
	private int top;
	private T[ ] stack;
	
	@SuppressWarnings("unchecked")
	public ArrayStack ( )							// POST: empty stack of capacity 10
	{	capacity = 10;
		top = 0;
		stack = (T [ ]) new Object [capacity];
	}
	
	@SuppressWarnings("unchecked")					// PRE: cap > 0
	public ArrayStack (int cap)						// POST: empty stack of capacity cap
	{	capacity = cap;
		top = 0;
		stack = (T [ ]) (new Object [capacity]); 	
	}
	
	public void push (T element)					// POST: element pushed onto stack
	{	if (top == capacity)
			resize();
		stack[top] = element;
		top++;
	}
	
	@SuppressWarnings("unchecked")
	private void resize()							// POST: array is doubled
	{
		T [ ] temp = (T[ ]) new Object [capacity*2];
		for (int k = 0; k < capacity; k++)			
			temp[k] = stack[k];
		capacity = capacity * 2;					
		stack = temp;								
	}
	
	public T peek ( ) throws EmptyCollectionException	// POST: return top
	{	if (isEmpty())
			throw new EmptyCollectionException ( );
		return stack[top-1];
	}
	
	public T pop ( ) throws EmptyCollectionException	// POST: return and remove top
	{	if (isEmpty())
				throw new EmptyCollectionException ( );
			top--;
			T result = stack[top];
			stack[top] = null;
			return result;
	}
	
	public boolean isEmpty ( )							// POST: return true if empty
	{	return top == 0;  }
		
	public int size( )									// POST: return size of stack
	{	return top;   }




}


