/**
 * This class creates a circular queue using a dynamic array.
 * 
 * @author Kevin Tavara
 *
 * @param <T> generic parameter 
 */
public class Queue<T> {
	
	/**
	 * This is where the queue will be stored.
	 */
	@SuppressWarnings("unchecked")
	private T[] circularQueue = (T[]) new Object[1];
	
	/**
	 * This index represents the next available location to insert.
	 */
	private int index = 0;
	
	/**
	 * Checks whether the queue is empty.
	 * 
	 * @return boolean whether or not queue is empty
	 */
	boolean isEmpty() {
		return (circularQueue[0] == null);
	}
	
	/**
	 * Insert value at the end of the queue.
	 * 
	 * @param value waiting to be enqueued in the queue
	 */
	@SuppressWarnings("unchecked")
	void enqueue(T value) {
		// Queue full
		if (value == null) {
			return;
		}
		if (index == circularQueue.length) {
			T[] tempQueue = (T[]) new Object[circularQueue.length*2];
			// Copy rest of array to tempQueue
			for (int i = 0; i < circularQueue.length; i++) {
				tempQueue[i] = circularQueue[i];
			}
			circularQueue = tempQueue;
		}
		circularQueue[index] = value;
		index++;
	}
	
	/**
	 * Removes and returns the value at the front of the queue.
	 * 
	 * @return T object that was removed
	 */
	@SuppressWarnings("unchecked")
	T dequeue() {
		// Ensure queue is not empty
		if (isEmpty()) 
			throw new RuntimeException("Queue Empty!");
		
		T[] tempQueue = (T[]) new Object[circularQueue.length];
		T temp = circularQueue[0];
		
		// Copy rest of array to tempQueue
		for (int i = 0; i < circularQueue.length-1; i++) {
			tempQueue[i] = circularQueue[i+1];
		}
		circularQueue = tempQueue;
		index--;
		return temp;
	}
	
	/**
	 * Checks the front of the queue without removing value.
	 * 
	 * @return T object waiting to be dequeued
	 */
	T peek() {
		// Ensure queue is not empty
		if (isEmpty()) 
			throw new RuntimeException("Queue Empty!");
		
		return circularQueue[0];
	}
}