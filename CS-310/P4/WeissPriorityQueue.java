import java.util.Iterator;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * PriorityQueue class implemented via the binary heap.
 * 
 * @author Professor and Kevin Tavara
 * 
 * @param <T> object type
 */
public class WeissPriorityQueue<T> extends WeissAbstractCollection<T> {

	/**
	 * Used for testing.
	 * 
	 * @param args not used
	 */
	public static void main(String[] args) {

		/**
		 * This class creates a GMU student object.
		 */
		class Student {
			String gnum;
			String name;

			/**
			 * Student constructor to initialize gnum and name. 
			 * 
			 * @param gnum g number
			 * @param name name of student
			 */
			Student(String gnum, String name) {
				this.gnum = gnum;
				this.name = name;
			}

			/**
			 * Check whether objects are equal.
			 * 
			 * @param o object to compare
			 * @return whether equal or not
			 */
			public boolean equals(Object o) {
				if (o instanceof Student)
					return this.gnum.equals(((Student) o).gnum);
				return false;
			}

			/**
			 * Method used to turn student to string.
			 * 
			 * @return string of student
			 */
			public String toString() {
				return name + "(" + gnum + ")";
			}

			/**
			 * Method used to turn gnum to hash code.
			 * 
			 * @return hash code
			 */
			public int hashCode() {
				return gnum.hashCode();
			}
		}

		Comparator<Student> comp = new Comparator<Student>() {
			public int compare(Student s1, Student s2) {
				return s1.name.compareTo(s2.name);
			}
		};

		WeissPriorityQueue<Student> q = new WeissPriorityQueue<>(comp);
		q.add(new Student("G00000000", "Robert"));
		q.add(new Student("G00000001", "Cindi"));

		for (Student s : q)
			System.out.print(s.name + " ");
		System.out.println(); // Cindi Robert

		Student bobby = new Student("G00000000", "Bobby");
		q.update(bobby);

		for (Student s : q)
			System.out.print(s.name + " ");
		System.out.println(); // Bobby Cindi
		
		bobby.name = "Robert";
		
		q.update(bobby);

		for (Student s : q)
			System.out.print(s.name + " ");
		System.out.println(); // Cindi Robert
	}

	/**
	 * Method to update WeissPriorityQueue with item.
	 * 
	 * @param x to update WeissPriorityQueue with
	 * @return boolean result of update WeissPriorityQueue with item
	 */
	@SuppressWarnings("unchecked")
	public boolean update(T x) {
		boolean result = false;
		T[] temp = (T[]) new Object[this.size()];
		for (int i = 0; i < temp.length; i++) {
			temp[i] = this.remove();
			if (temp[i].equals(x)) {
				result = true;
				temp[i] = x;
			}
		}
		for (int i = 0; i < temp.length; i++) {
			this.add(temp[i]);
		}
		return result;
	}

	/**
	 * Construct an empty PriorityQueue.
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue() {
		currentSize = 0;
		cmp = null;
		array = (T[]) new Object[DEFAULT_CAPACITY + 1];
	}

	/**
	 * Construct an empty PriorityQueue with a specified comparator.
	 * 
	 * @param c to set compare
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue(Comparator<? super T> c) {
		currentSize = 0;
		cmp = c;
		array = (T[]) new Object[DEFAULT_CAPACITY + 1];
	}

	/**
	 * Construct a PriorityQueue from another Collection.
	 * 
	 * @param coll to set size
	 */
	@SuppressWarnings("unchecked")
	public WeissPriorityQueue(WeissCollection<? extends T> coll) {
		cmp = null;
		currentSize = coll.size();
		array = (T[]) new Object[(currentSize + 2) * 11 / 10];

		int i = 1;
		for (T item : coll)
			array[i++] = item;
		buildHeap();
	}

	/**
	 * Compares lhs and rhs using comparator if provided by cmp, or the default
	 * comparator.
	 * 
	 * @param rhs right object
	 * @param lhs left object
	 * @return whether the same or not
	 */
	@SuppressWarnings("unchecked")
	private int compare(T lhs, T rhs) {
		if (cmp == null)
			return ((Comparable) lhs).compareTo(rhs);
		else
			return cmp.compare(lhs, rhs);
	}

	/**
	 * Adds an item to this PriorityQueue.
	 * 
	 * @param x any object.
	 * @return true.
	 */
	public boolean add(T x) {
		if (currentSize + 1 == array.length)
			doubleArray();

		// Percolate up
		int hole = ++currentSize;
		array[0] = x;

		for (; compare(x, array[hole / 2]) < 0; hole /= 2)
			array[hole] = array[hole / 2];
		array[hole] = x;

		return true;
	}

	/**
	 * Returns the number of items in this PriorityQueue.
	 * 
	 * @return the number of items in this PriorityQueue.
	 */
	public int size() {
		return currentSize;
	}

	/**
	 * Make this PriorityQueue empty.
	 */
	public void clear() {
		currentSize = 0;
	}

	/**
	 * Returns an iterator over the elements in this PriorityQueue. The iterator
	 * does not view the elements in any particular order.
	 * 
	 * @return iterator of AnyType
	 */
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int current = 0;

			public boolean hasNext() {
				return current != size();
			}

			@SuppressWarnings("unchecked")
			public T next() {
				if (hasNext())
					return array[++current];
				else
					throw new NoSuchElementException();
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * Returns the smallest item in the priority queue.
	 * 
	 * @return the smallest item.
	 * @throws NoSuchElementException if empty.
	 */
	public T element() {
		if (isEmpty())
			throw new NoSuchElementException();
		return array[1];
	}

	/**
	 * Removes the smallest item in the priority queue.
	 * 
	 * @return the smallest item.
	 * @throws NoSuchElementException if empty.
	 */
	public T remove() {
		T minItem = element();
		array[1] = array[currentSize--];
		percolateDown(1);

		return minItem;
	}

	/**
	 * Establish heap order property from an arbitrary arrangement of items. Runs in
	 * linear time.
	 */
	private void buildHeap() {
		for (int i = currentSize / 2; i > 0; i--)
			percolateDown(i);
	}

	/**
	 * Default capacity.
	 */
	private static final int DEFAULT_CAPACITY = 100;

	/**
	 * Number of elements in heap.
	 */
	private int currentSize;

	/**
	 * The heap array.
	 */
	private T[] array;

	/**
	 * Which compare to use.
	 */
	private Comparator<? super T> cmp;

	/**
	 * Internal method to percolate down in the heap.
	 * 
	 * @param hole the index at which the percolate begins.
	 */
	private void percolateDown(int hole) {
		int child;
		T tmp = array[hole];

		for (; hole * 2 <= currentSize; hole = child) {
			child = hole * 2;
			if (child != currentSize && compare(array[child + 1], array[child]) < 0)
				child++;
			if (compare(array[child], tmp) < 0)
				array[hole] = array[child];
			else
				break;
		}
		array[hole] = tmp;
	}

	/**
	 * Internal method to extend array.
	 */
	@SuppressWarnings("unchecked")
	private void doubleArray() {
		T[] newArray;

		newArray = (T[]) new Object[array.length * 2];
		for (int i = 0; i < array.length; i++)
			newArray[i] = array[i];
		array = newArray;
	}
}
