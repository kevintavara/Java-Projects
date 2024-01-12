import java.util.Map;
import java.util.Set;

import java.util.Collection; //for returning in the values() function only

/**
 * This class implements a ThreeTenMap that could be used in ThreeTenGraph.
 * 
 * @author Kevin Tavara
 *
 * @param <K> key
 * @param <V> value
 */
class ThreeTenMap<K, V> implements Map<K, V> {

	/**
	 * Used to store hash table.
	 */
	private Pair<K, V>[] storage;

	/**
	 * Used to track current number of elements.
	 */
	private int numElements = 0;

	/**
	 * This class is used to create a new Pair to be used in storage.
	 *
	 * @param <K> key
	 * @param <V> value
	 */
	public class Pair<K, V> {
		/**
		 * Key of the pair.
		 */
		K key;

		/**
		 * Value of the pair.
		 */
		V value;

		/**
		 * Creates new Pair with a key and value.
		 * 
		 * @param key   of the pair
		 * @param value of the pair
		 */
		Pair(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * Method used to turn pair to string.
		 * 
		 * @return string of pair
		 */
		public String toString() {
			return "<" + key + "," + value + ">";
		}
	}

	/**
	 * Original size of storage.
	 */
	private int originalSize;

	/**
	 * Maximum load of the storage.
	 */
	private double maxLoad;

	/**
	 * ThreeTenMap constructor used to initialize storage.
	 * 
	 * @param size of storage
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenMap(int size) {
		int n = 1;
		while (n < size)
			n *= 2;
		this.storage = (Pair<K, V>[]) new Pair[n];
		this.maxLoad = 0.5;
	}

	/**
	 * ThreeTenMap constructor used to initialize storage and maxLoad.
	 * 
	 * @param size    of storage
	 * @param maxLoad maximum load
	 */
	@SuppressWarnings("unchecked")
	public ThreeTenMap(int size, double maxLoad) {
		int n = 1;
		while (n < size)
			n *= 2;
		this.storage = (Pair<K, V>[]) new Pair[n];
		this.originalSize = n;
		this.maxLoad = maxLoad;
	}

	/**
	 * Method to clear ThreeTenMap.
	 */
	@SuppressWarnings("unchecked")
	public void clear() {
		// O(1)
		this.storage = (Pair<K, V>[]) new Pair[this.originalSize];
		this.numElements = 0;
	}

	/**
	 * Method to get if ThreeTenMap is empty.
	 * 
	 * @return true if ThreeTenMap is empty
	 */
	public boolean isEmpty() {
		// O(1)
		return this.size() == 0;
	}

	/**
	 * Method to get capacity of ThreeTenMap.
	 * 
	 * @return capacity of ThreeTenMap
	 */
	public int capacity() {
		// return how many "slots" are in the table
		// O(1)
		return this.storage.length;
	}

	/**
	 * Method to get size of ThreeTenMap.
	 * 
	 * @return size of ThreeTenMap
	 */
	public int size() {
		// return the number of elements in the table
		// O(1)
		return this.numElements;
	}

	/**
	 * Method to check if slot contains value in ThreeTenMap.
	 * 
	 * @param index of storage
	 * @return false if slot is empty
	 */
	private boolean slotContainsValue(int index) {
		// O(1)
		return this.storage[index] != null;
	}

	/**
	 * Method to get map value with key in ThreeTenMap.
	 * 
	 * @param key to check
	 * @return value with corresponding key in ThreeTenMap
	 */
	public V get(Object key) {
		// Worst case: O(n), Average case: O(1)
		if (key == null) 
			throw new NullPointerException("Key cannot be null");
		int index = Math.abs(key.hashCode()) % this.storage.length;
		int i = 0;
		Pair<K, V> pair = this.storage[index];
		while (pair != null) {
			if (pair.key.equals(key))
				return pair.value;
			i++;
			pair = this.storage[(index + (int) (0.5 * i + 0.5 * i * i)) % this.storage.length];
		}
		return null;
	}

	/**
	 * Method to get key set of ThreeTenMap.
	 * 
	 * @return key set of ThreeTenMap
	 */
	public Set<K> keySet() {
		// max of O(m) where m = number of slots in the table
		ThreeTenSet<K> set = new ThreeTenSet<K>();
		for (int i = 0; i < this.storage.length; i++) {
			Pair<K, V> pair = this.storage[i];
			if (pair != null)
				set.add(pair.key);
		}
		return set;
	}

	/**
	 * Method to remove value with key in ThreeTenMap.
	 * 
	 * @param key to remove value of in ThreeTenMap
	 * @return V value with key in ThreeTenMap
	 */
	public V remove(Object key) {
		// Worst case: O(n), Average case: O(1)
		if (key == null)
			throw new NullPointerException("Key cannot be null");
		int index = Math.abs(key.hashCode()) % this.storage.length;
		int i = 0;
		if (this.storage[index] != null && this.storage[index].key.equals(key)) {
			V result = this.storage[index].value;
			this.storage[index] = null;
			this.numElements--;
			return result;
		}
		Pair<K, V> pair = this.storage[index];
		while (pair != null) {
			if (pair.key.equals(key))
				return pair.value;
			i++;
			pair = this.storage[(index + (int)(0.5*i + 0.5*i*i)) % this.storage.length];
		}
		return null;
	}

	/**
	 * Method to put key and value in ThreeTenMap.
	 * 
	 * @param key   to put in ThreeTenMap
	 * @param value to put in ThreeTenMap
	 * @return V value replaced in ThreeTenMap
	 */
	public V put(K key, V value) {
		// Worst case: O(n), Average case: O(1) if no rehashing
		// O(m) if rehashing is needed.
		if (this.numElements == this.storage.length)
			rehash(2 * this.storage.length);
		if (key == null)
			throw new NullPointerException("Key cannot be null");
		V result = this.get(key);
		int index = Math.abs(key.hashCode()) % this.storage.length;
		int i = 0;
		Pair<K, V> pair = this.storage[index];
		while (pair != null) {
			if (pair.key.equals(key)) {
				pair.value = value;
				return result;
			}
			i++;
			pair = this.storage[(index + (int) (0.5 * i + 0.5 * i * i)) % this.storage.length];
		}
		this.storage[(index + (int) (0.5 * i + 0.5 * i * i)) % this.storage.length] = new Pair<K, V>(key, value);
		this.numElements++;
		double load = this.numElements * 1.0 / this.storage.length;
		if (load > maxLoad)
			rehash(2 * this.storage.length);
		return result;
	}

	/**
	 * Method to rehash ThreeTenMap to size.
	 * 
	 * @param size to rehash ThreeTenMap to
	 * @return boolean result of rehash ThreeTenMap to size
	 */
	@SuppressWarnings("unchecked")
	public boolean rehash(int size) {
		// Return true if you were able to rehash.
		double newLoad = this.numElements * 1.0 / size;
		if (newLoad > maxLoad)
			return false;
		Pair<K, V>[] temp = (Pair<K, V>[]) new Pair[this.storage.length];
		for (int i = 0; i < this.storage.length; i++)
			temp[i] = this.storage[i];
		int n = 1;
		while (n < size)
			n *= 2;
		this.storage = (Pair<K, V>[]) new Pair[n];
		this.numElements = 0;
		for (int i = 0; i < temp.length; i++) {
			Pair<K, V> pair = temp[i];
			if (pair != null)
				this.put(pair.key, pair.value);
		}
		return true;
	}

	// --------------------------------------------------------
	// testing code goes here... edit this as much as you want!
	// --------------------------------------------------------
	/**
	 * Method used to test class.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {
		// main method for testing, edit as much as you want
		ThreeTenMap<Integer, Integer> t1 = new ThreeTenMap<>(3);
		ThreeTenMap<String, String> t2 = new ThreeTenMap<>(4, 1);
		
		t1.put(0, 1);
		t1.put(4, 1);
		if (t1.toString().equals("[0]: <0,1>\n[1]: <4,1>")
				&& t1.toString(true).equals("[0]: <0,1>\n[1]: <4,1>\n[2]: null\n[3]: null")) {
			System.out.println("Yay 1");
		}

		t1.put(8, 2);
		t1.put(20, 2);
		if (t1.toString().equals("[0]: <0,1>\n[1]: <8,2>\n[4]: <4,1>\n[5]: <20,2>") && t1.toString(true).equals(
				"[0]: <0,1>\n[1]: <8,2>\n[2]: null\n[3]: null\n[4]: <4,1>\n[5]: <20,2>\n[6]: null\n[7]: null")) {
			System.out.println("Yay 2");
		}

		if (t1.put(4, 10) == 1 && t1.size() == 4 && t1.capacity() == 8 && t1.get(4) == 10) {
			System.out.println("Yay 3");
		}

		t2.put("a", "apple");
		t2.put("b", "banana");
		t2.put("banana", "b");
		t2.put("b", "butter");
		t2.put("apple", "a");

		if (t2.toString().equals("[0]: <apple,a>\n[1]: <a,apple>\n[2]: <b,butter>\n[3]: <banana,b>")
				&& t2.toString(true).equals("[0]: <apple,a>\n[1]: <a,apple>\n[2]: <b,butter>\n[3]: <banana,b>")) {
			System.out.println("Yay 4");
		}

		if (t2.put("c", "carrot") == null && t2.size() == 5 && t2.capacity() == 8 && t2.rehash(4) == false
				&& t2.rehash(5) == true && t2.size() == 5 && t2.capacity() == 8) {
			System.out.println("Yay 5");
		}

		// This is NOT all the testing you need... several methods are not
		// being tested here! Some method return types aren't being tested
		// either. You need to write your own tests!
	}

	// ********************************************************************************
	// YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// These are some methods we didn't write for you, but you could write,
	// if you need/want them for building your graph. We will not test
	// (or grade) these methods.
	// ********************************************************************************

	/**
	 * Throws exception.
	 * 
	 * @return Collection
	 */
	public Collection<V> values() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * 
	 * @param m is map
	 */
	public void putAll(Map<? extends K, ? extends V> m) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * 
	 * @param value object
	 * @return boolean
	 */
	public boolean containsValue(Object value) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * 
	 * @return Set
	 */
	public Set<Map.Entry<K, V>> entrySet() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * 
	 * @param o object
	 * @return boolean
	 */
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * 
	 * @return hash code
	 */
	public int hashCode() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * 
	 * @param key object
	 * @return boolean
	 */
	public boolean containsKey(Object key) {
		throw new UnsupportedOperationException();
	}

	// ********************************************************************************
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	// We will check these things to make sure they still work, so don't break them.
	// ********************************************************************************

	/**
	 * Method used to turn storage to string.
	 * 
	 * @return string
	 */
	public String toString() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return toString(false);
	}

	/**
	 * Method used to turn storage to string.
	 * 
	 * @param showEmpty boolean
	 * @return string of storage
	 */
	public String toString(boolean showEmpty) {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < storage.length; i++) {
			if (showEmpty || slotContainsValue(i)) {
				s.append("[");
				s.append(i);
				s.append("]: ");
				s.append(storage[i]);
				s.append("\n");
			}
		}
		s.deleteCharAt(s.length() - 1);
		return s.toString();
	}

	/**
	 * Method used to turn storage to array.
	 * 
	 * @return array of objects
	 */
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		Pair<K, V>[] ret = (Pair<K, V>[]) new Pair[numElements];
		for (int i = 0, j = 0; i < storage.length; i++) {
			if (slotContainsValue(i)) {
				ret[j++] = new Pair<>(storage[i].key, storage[i].value);
			}
		}
		return (Object[]) ret;
	}
}