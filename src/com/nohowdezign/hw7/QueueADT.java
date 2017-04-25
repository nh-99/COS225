package com.nohowdezign.hw7;

public interface QueueADT<T>
{  public void enqueue(T element);					// POST: add element to rear of queue
    public T dequeue( );							// PRE: queue is not empty
													// POST: remove and return front element
    public T getFront( );							// PRE: queue is not empty
													// POST: return front element
    public boolean isEmpty( );						// POST: return true if queue is empty
    public int size( );	 							// POST: return number of elements
}

