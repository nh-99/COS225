package com.nohowdezign.hw7;

public class CircularArrayQueue<T> implements QueueADT<T> {
	private final static int CAPACITY = 5;					// initial capacity of queue
	private int front, rear;								// index to front and rear
	private int count;										// number of items in queue
	private T[] queue;										// reference to array that implements circular queue
	
	public CircularArrayQueue ()							// POST: empty queue of default capacity
	{	this (CAPACITY);	}
	
	@SuppressWarnings("unchecked")	
	public CircularArrayQueue (int cap)						// PRE: cap > 0
	{	front = rear = count = 0;							// POST: empty queue of capacity cap
		queue = (T[]) (new Object[cap]);
	}
	
	public void enqueue (T element)							// POST: add element to rear of queue
	{	if (size() == queue.length)
			expandCapacity ( );
		queue[rear] = element;
		rear = (rear + 1) % queue.length;
		count++;
	}
	
	public T dequeue ( ) throws EmptyCollectionException	// PRE: queue is not empty
	{	if (isEmpty())										// POST: remove and return front element
			throw new EmptyCollectionException ();
		T result = queue[front];
		queue[front] = null;
		front = (front+1) % queue.length;
		count--;
		return result;
	}
	
	public T getFront ( ) throws EmptyCollectionException	// PRE: queue is not empty
	{	if (isEmpty())										// POST: return front element
			throw new EmptyCollectionException ();
		T result = queue[front];
		return result;
	}
	
	@SuppressWarnings("all")
	private void expandCapacity ()							// POST: resize array to double the capacity
	{	T[] larger = (T[]) (new Object [queue.length*2]);
		for (int k=0; k<count; k++)
		{	larger[k] = queue[front];
			front = (front+1) % queue.length;
		}
		front = 0;
		rear = count;
		queue = larger;
	}
	
	public boolean isEmpty ()								// POST: return true if queue is empty, else false
	{	return count == 0;	}
	
	public int size ()										// POST: return number of elements
	{	return count;	}
	
	
		
		
	
	
}
