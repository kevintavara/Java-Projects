//TODO: JavaDocs

//Don't forget, use inheritDoc to save yourself
//a lot of time for inherited methods from Set!!!

import java.util.Collection;
import java.util.Set;
import java.util.Iterator;

/**
 * This class is used to implement a ThreeTenSet.
 *
 * @param <E> set
 */
class ThreeTenSet<E> implements Set<E> {
	// ********************************************************************************
	// YOU MAY, BUT DON'T NEED TO EDIT THINGS IN THIS SECTION
	// These are some methods we didn't write for you, but you could write.
	// if you need/want them for building your graph. We will not test
	// (or grade) these methods.
	// ********************************************************************************

	/**
	 * Throws exception.
	 * @param c collection
	 * @return boolean
	 */
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * @param c collection
	 * @return boolean
	 */
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * @param a array
	 * @param <T> type
	 * @return T[]
	 */
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * @param c collection
	 * @return boolean
	 */
	public boolean containsAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * @param o object
	 * @return boolean
	 */
	public boolean equals(Object o) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Throws exception.
	 * @return boolean
	 */
	public int hashCode() {
		throw new UnsupportedOperationException();
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
		ThreeTenSet<Integer> set = new ThreeTenSet<>();

		if (set.add(1) && set.add(2) && set.add(-51) && !set.add(1) && set.size() == 3) {
			System.out.println("yay 1");
		}

		if (set.remove(1) && !set.remove(1) && set.size() == 2 && set.remove(-51) && set.toArray()[0].equals(2)) {
			System.out.println("yay 2");
		}

		set.clear();
		ThreeTenSet<Integer> set2 = new ThreeTenSet<>();
		for (int i = -100; i < 100; i += 17)
			set2.add(i);

		if (set.size() == 0 && set.addAll(set2) && set.size() == 12) {
			System.out.println("yay 3");
		}

		System.out.println();
		System.out.println(set);
		/*
		 * [0]: <-32,-32> [2]: <-66,-66> [3]: <2,2> [4]: <-100,-100> [5]: <36,36> [6]:
		 * <70,70> [15]: <-15,-15> [17]: <-49,-49> [19]: <-83,-83> [20]: <19,19> [21]:
		 * <53,53> [23]: <87,87>
		 */
	}

	// ********************************************************************************
	// DO NOT EDIT ANYTHING BELOW THIS LINE (except to add the JavaDocs)
	// We will grade these methods to make sure they still work, so don't break
	// them.
	// ********************************************************************************

	/**
	 * Storage used to store the ThreeTenMap.
	 */
	private ThreeTenMap<E, E> storage = new ThreeTenMap<>(7);

	/**
	 * Method used to add to storage.
	 * 
	 * @param e Object to be added
	 * @return whether object was added or not
	 */
	public boolean add(E e) {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		if (e == null)
			throw new NullPointerException();
		return storage.put(e, e) == null;
	}

	/**
	 * Method add collection c.
	 * 
	 * @param c collection
	 * @return boolean
	 */
	public boolean addAll(Collection<? extends E> c) {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		boolean changedSomething = false;

		for (E e : c) {
			if (e != null) {
				changedSomething = add(e) || changedSomething;
			}
		}

		return changedSomething;
	}

	/**
	 * Method used to clear storage.
	 */
	public void clear() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		storage = new ThreeTenMap<>(7);
	}

	/**
	 * Method used to check if an object is in storage.
	 * 
	 * @param o object to be checked
	 * @return whether object was in storage or not
	 */
	public boolean contains(Object o) {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.get(o) != null;
	}

	/**
	 * Method to check if storage is empty.
	 * 
	 * @return whether storage empty or not
	 */
	public boolean isEmpty() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return size() == 0;
	}

	/**
	 * Method used to remove an object from storage.
	 * 
	 * @param o object to be removed
	 * @return whether object was removed
	 */
	public boolean remove(Object o) {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.remove(o) != null;
	}

	/**
	 * Method used to return size of storage.
	 * 
	 * @return size of storage
	 */
	public int size() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.size();
	}

	/**
	 * Method used to turn storage to an array.
	 * 
	 * @return array of objects
	 */
	@SuppressWarnings("unchecked")
	public Object[] toArray() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		ThreeTenMap<E, E>.Pair<E, E>[] arr = (ThreeTenMap<E, E>.Pair<E, E>[]) storage.toArray();

		Object[] ret = new Object[arr.length];
		for (int i = 0; i < arr.length; i++) {
			ret[i] = arr[i].key;
		}

		return ret;
	}

	/**
	 * Method used to turn storage to string.
	 * 
	 * @return string of storage
	 */
	public String toString() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.toString();
	}

	/**
	 * Method used to turn storage to string.
	 * 
	 * @param showEmpty boolean 
	 * @return string of storage
	 */
	public String toString(boolean showEmpty) {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return storage.toString(showEmpty);
	}

	/**
	 * Method used to iterate through E objects.
	 * 
	 * @return iterator of E
	 */
	public Iterator<E> iterator() {
		// THIS METHOD IS PROVIDED, DO NOT CHANGE IT
		return new Iterator<E>() {
			private Object[] values = toArray();
			private int currentLoc = 0;

			@SuppressWarnings("unchecked")
			public E next() {
				return (E) values[currentLoc++];
			}

			public boolean hasNext() {
				return currentLoc != values.length;
			}
		};
	}
}