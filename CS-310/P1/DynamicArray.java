// TO DO: add your implementation and JavaDocs.

import java.util.Collection;
import java.util.Iterator;
/**
 * This class creates a dynamic array object to implement the grid.
 * 
 * @author Kevin Tavara
 *
 * @param <T> used for dynamic array of generic objects
 */
public class DynamicArray<T> implements Iterable<T> {
	/**
	 * INITCAP is the initial capacity for dynamic array should one not be provided.
	 */
	private static final int INITCAP = 2; //default initial capacity
	
	/**
	 * storage is the dynamic array that will be initialized in constructor.
	 */
	private T[] storage; //underlying array, you MUST use this for credit (do not change the name or type)
	
	/**
	 * elements is used as a counter to keep track of current elements in dynamic array.
	 */
	private int elements;
	
	/**
	 * Constructor class to initialize storage.
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(){
		// Initial capacity of the storage should be INITCAP
		storage = (T[]) new Object[INITCAP];
	}

	/**
	 * Constructor class to initialize storage to size initCapacity.
	 * 
	 * @param initCapacity is length of storage.
	 */
	@SuppressWarnings("unchecked")
	public DynamicArray(int initCapacity) {
		// Check if valid initCapacity
		if (initCapacity < 1) {
			throw new IllegalArgumentException("Capacity cannot be zero or negative.");
		}
		// The initial capacity of the storage should be initCapacity
		storage = (T[]) new Object[initCapacity];
	}

	/**
	 * Gets amount of elements in array.
	 * 
	 * @return the integer elements
	 */
	public int size() {	
		// Report the current number of elements.
		return elements;
	}  
		
	/**
	 * Gets the possible size of the dynamic array.
	 * 
	 * @return maximum capacity of array
	 */
	public int capacity() {
		// Report maximum capacity.
		return storage.length;
	}
	
	/**
	 * This method is used to change an object in the array.
	 * Note: You cannot add new items with this method.
	 * 
	 * @param index is location of object
	 * @param value is object to be set
	 * @return object previously in location
	 */
	public T set(int index, T value) {
		// Local Variable
		T tmp;
		// Check if valid index
		if (index < 0 || index > capacity()) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		// Return the old item at that index.
		tmp = storage[index];
		// Change the item at the given index to be the given value.
		storage[index] = value;
		return tmp;
	}

	/**
	 * Gets object at specified location in dynamic array.
	 * 
	 * @param index is location of object
	 * @return object at location
	 */
	public T get(int index) {
		// Check if valid index
		if (index < 0 || index > capacity()) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		return storage[index];
	}

	/**
	 * Attempts to add object at end of array. 
	 * Doubles capacity if not space.
	 * 
	 * @param value object to be added
	 * @return whether object was inserted or not
	 */
	@SuppressWarnings("unchecked")
	public boolean add(T value) {
		// Local Variable
		T[] tmp;
		// Worst case where expansion needed
		if (elements == capacity()) {
			// Double the capacity if no space available.
			tmp = (T[]) new Object[capacity()*2];
			for (int i = 0; i < capacity(); i++) {
				tmp[i] = storage[i];
			}
			// Add to end
			tmp[elements] = value;
			storage = tmp;
			elements++;
			return true;
		}
		// Best case
		storage[elements] = value;
		elements++;
		// Amortized O(1)
		return true;
	}
	
	/**
	 * Attempts to insert object in array.
	 * Shifts elements and doubles capacity if no space.
	 * 
	 * @param index is specified location in array
	 * @param value object to be inserted
	 */
	@SuppressWarnings("unchecked")
	public void add(int index, T value) {
		// Check if valid index
		if (index < 0 || index > capacity()) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		
		// Check if value should be added to end of a full list
		if (index == capacity()) {
			add(value);
			return;
		} 
		
		// Local Variable
		T[] tmp;
		
		// Check if enough space
		if (elements == capacity()) {
			tmp = (T[]) new Object[capacity()*2];
		}
		else {
			tmp = (T[]) new Object[capacity()];
		}
		
		// Double the capacity if no space available.
		int j = 0;
		for (int i = 0; i < storage.length; i++) {
			if (i == index) {
				tmp[index] = value;
				continue;
			}
			tmp[i] = storage[j];
			j++;
		}
		storage = tmp;
		elements++;
		// O(N) where N is the number of elements currently in the list
	}
	
	/**
	 * Removes an object in the array at specified index.
	 * 
	 * @param index is location of object in array
	 * @return object that was removed
	 */
	@SuppressWarnings("unchecked")
	public T remove(int index) {
		// Remove and return the element the given index. Shift elements
		// to remove the gap. Throw an exception when there is an invalid
		// index (see set(), get(), etc. above).
		// Check if valid index
		if (index < 0 || index > capacity()) {
			throw new IndexOutOfBoundsException("Index " + index + " out of bounds!");
		}
		// Local Variables
		T[] tmp;
		T temp;
		temp = storage[index];
		// Index valid so decrement
		elements--;
		
		// Check whether dynamic array needs to shrink
		if (elements < capacity()/3.0) {
			tmp = (T[]) new Object[capacity()/2];
		}
		else {
			tmp = (T[]) new Object[capacity()];
		}
		// Double the capacity if no space available.
		int j = 0;
		for (int i = 0; i < tmp.length; i++) {
			// storage[index] skipped
			if (i == index) {
				continue;
			}
			tmp[j] = storage[i];
			j++;
		}
		storage = tmp;
		// Halve capacity of the storage if the number of elements falls 
		// below 1/3 of the capacity.		
		return temp;
	}
	
	/**
	 * Iterator method used to iterate through the dynamic array easily.
	 * 
	 * @return iterator of dynamic array
	 */
	public Iterator<T> iterator() {
		// Uses an anonymous class style, complete the iterator code
		// below. Note that this uses the "diamond syntax" which is
		// only available for nested classes from Java 9 forward.
		// If you get an error on the next line you can add a <T>
		// between the <> or you can (and should) update your 
		// version of the JDK.
		
		return new Iterator<>() {
			// Local Variable
			int increment = 0;
			
			/**
			 * Increments i.
			 * 
			 * @return next object in array
			 */
			public T next() {
				T tmp = storage[increment];
				increment++;
				return tmp;
			}
			
			/**
			 * Checks if end of dynamic array has been reached.
			 * 
			 * @return whether element in array is null
			 */
			public boolean hasNext() {
				return (storage[increment] != null);
			}
		};
	}
	
	//******************************************************
	//*******     BELOW THIS LINE IS TESTING CODE    *******
	//*******      Edit it as much as you'd like!    *******
	//******************************************************
	
	/**
	 * Method used for a spring representation of dynamic array.
	 * 
	 * @return dynamic array in string
	 */
	public String toString() {
		//This method is provided for debugging purposes
		//(use/modify as much as you'd like), it just prints
		//out the list for easy viewing.
		StringBuilder s = new StringBuilder("Dynamic array with " + size()
			+ " items and a capacity of " + capacity() + ":");
		for (int i = 0; i < size(); i++) {
			s.append("\n  ["+i+"]: " + get(i));
		}
		return s.toString();
		
	}
	
	/**
	 *  Main method used for testing of dynamic array.
	 *  
	 *  @param args not used
	 */
	public static void main(String args[]){
		//These are _sample_ tests. If you're seeing all the "yays" that's
		//an excellent first step! But it might not mean your code is 100%
		//working... You may edit this as much as you want, so you can add
		//own tests here, modify these tests, or whatever you need!
		
		DynamicArray<Integer> ida = new DynamicArray<>();
		if ((ida.size() == 0) && (ida.capacity() == 2)){
			System.out.println("Yay 1");
		}

		boolean ok = true;
		for (int i=0; i<3;i++)
			ok = ok && ida.add(i*5);
		
		if (ok && ida.size()==3 && ida.get(2) == 10 && ida.capacity() == 4 ){
			System.out.println("Yay 2");
		}
		
		ida.add(1,-10);
		ida.add(4,100);
		if (ida.set(1,-20)==-10 && ida.get(2) == 5 && ida.size() == 5 
			&& ida.capacity() == 8 ){
			System.out.println("Yay 3");
		}
		if (ida.remove(0) == 0 && ida.remove(0) == -20 && ida.remove(2) == 100 
			&& ida.size() == 2 && ida.capacity() == 4 ){
			System.out.println("Yay 4");
		}
		
		// using for each to print each element
		
		System.out.print("Printing values: ");
		for(Integer i : ida) {
			System.out.print(i);
			System.out.print(" ");
		}
		System.out.println("");
		
		Iterator<Integer> iterator = ida.iterator();
		Integer element = 0;
		while ( iterator.hasNext() )
		{
			element = iterator.next();
			System.out.print(element + " " );
		}
		System.out.println("");
	}
}