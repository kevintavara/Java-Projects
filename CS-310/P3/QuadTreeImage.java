import java.util.Iterator;

/**
 * This class is used to create a QuadTreeImage.
 * 
 * @author Kevin Tavara
 *
 * @param <Pixel> holds the color of the node
 */
public class QuadTreeImage<Pixel extends Number> {
	// Class Constants
	/**
	 * NW represents quadrant 0.
	 */
	private final int NW = 0;

	/**
	 * NE represents quadrant 1.
	 */
	private final int NE = 1;

	/**
	 * SE represents quadrant 2.
	 */
	private final int SE = 2;

	/**
	 * SW represents quadrant 3.
	 */
	private final int SW = 3;

	// Class Variables
	/**
	 * The root of the tree.
	 */
	private ImageBlob<Pixel> root = null;

	/**
	 * The maximum width.
	 */
	private int gridRow = 0;

	/**
	 * The maximum height.
	 */
	private int gridCol = 0;

	/**
	 * Counter for the columns when building NW side of tree.
	 */
	int colNW = 0;

	/**
	 * Counter for the columns when building NE side of tree.
	 */
	int colNE = 0;

	/**
	 * Counter for the columns when building SE side of tree.
	 */
	int colSE = 0;

	/**
	 * Counter for the columns when building SW side of tree.
	 */
	int colSW = 0;

	/**
	 * Counter for the rows when building NW side of tree.
	 */
	int rowNW = 0;

	/**
	 * Counter for the rows when building NE side of tree.
	 */
	int rowNE = 0;

	/**
	 * Counter for the rows when building SE side of tree.
	 */
	int rowSE = 1;

	/**
	 * Counter for the rows when building SW side of tree.
	 */
	int rowSW = 1;

	/**
	 * Counter used for incrementing the rows.
	 */
	int count = 0;

	/**
	 * Constructor method that builds the tree.
	 * 
	 * @param array used to build the tree
	 */
	public QuadTreeImage(Pixel[][] array) {
		// Local Variables
		int level = 1;

		// Instantiate the root
		root = new ImageBlob<Pixel>();

		// Keep track of size of array
		gridRow = array.length;
		gridCol = array[0].length;

		// Building the tree o ImageBlobs
		// Pixel, NW or NE or SW or SE
		if (gridRow < 4) {
			if (gridCol < 2) {
				root.value = array[0][0];
				return;
			}
			root.NW = new ImageBlob<Pixel>(array[0][0]);
			root.NE = new ImageBlob<Pixel>(array[0][1]);
			root.SE = new ImageBlob<Pixel>(array[1][1]);
			root.SW = new ImageBlob<Pixel>(array[1][0]);
			if (root.NW.value.equals(root.NE.value) && root.NW.value.equals(root.SE.value)
					&& root.NW.value.equals(root.SW.value)) {
				root = new ImageBlob<Pixel>(root.NW.value);
			}
			return;
		}
		root.NW = recNWfindPixel(level, NW, array);
		count++;
		root.NE = recNEfindPixel(level, NE, array);
		count++;
		root.SE = recSEfindPixel(level, SE, array);
		count++;
		root.SW = recSWfindPixel(level, SW, array);
		count++;
		if (root.NW.value != null) {
			if (root.NW.value.equals(root.NE.value) && root.NW.value.equals(root.SE.value)
					&& root.NW.value.equals(root.SW.value)) {
				root = new ImageBlob<Pixel>(root.NW.value);
			}
		}
	}

	/**
	 * Uses recursion to build the NW ImageBlob.
	 * 
	 * @param level determines the level of the tree
	 * @param q     determines the quadrant
	 * @param array used to build the NW tree
	 * @return the NW ImageBlob
	 */
	private ImageBlob<Pixel> recNWfindPixel(int level, int q, Pixel[][] array) {
		// Local Variables
		int lastLevel = gridRow / 2;
		ImageBlob<Pixel> newBlob = new ImageBlob<Pixel>();

		// Base case = stop recursion --
		if (level == lastLevel) {
			int beginRow = calculateRow(level, q);
			int beginCol = calculateCol(level, q);
			newBlob.value = array[beginRow][beginCol];
		}

		// General case -- continue recursion
		else {
			newBlob.NW = recNWfindPixel(level + 1, NW, array);
			newBlob.NE = recNEfindPixel(level + 1, NE, array);
			newBlob.SE = recSEfindPixel(level + 1, SE, array);
			newBlob.SW = recSWfindPixel(level + 1, SW, array);
			if (newBlob.NW.value.equals(newBlob.NE.value) && newBlob.NW.value.equals(newBlob.SE.value)
					&& newBlob.NW.value.equals(newBlob.SW.value)) {
				newBlob = new ImageBlob<Pixel>(newBlob.NW.value);
			}
		}
		return newBlob;
	}

	/**
	 * Uses recursion to build the NE ImageBlob.
	 * 
	 * @param level determines the level of the tree
	 * @param q     determines the quadrant
	 * @param array used to build the NE tree
	 * @return the NE ImageBlob
	 */
	private ImageBlob<Pixel> recNEfindPixel(int level, int q, Pixel[][] array) {
		// Local Variables
		int lastLevel = gridRow / 2;
		ImageBlob<Pixel> newBlob = new ImageBlob<Pixel>();

		// Base case = stop recursion --
		if (level == lastLevel) {
			int beginRow = calculateRow(level, q);
			int beginCol = calculateCol(level, q);
			newBlob.value = array[beginRow][beginCol];
		}

		// General case -- continue recursion
		else {
			newBlob.NW = recNWfindPixel(level + 1, NW, array);
			newBlob.NE = recNEfindPixel(level + 1, NE, array);
			newBlob.SE = recSEfindPixel(level + 1, SE, array);
			newBlob.SW = recSWfindPixel(level + 1, SW, array);
			if (newBlob.NW.value.equals(newBlob.NE.value) && newBlob.NW.value.equals(newBlob.SE.value)
					&& newBlob.NW.value.equals(newBlob.SW.value)) {
				newBlob = new ImageBlob<Pixel>(newBlob.NW.value);
			}
		}
		return newBlob;
	}

	/**
	 * Uses recursion to build the SE ImageBlob.
	 * 
	 * @param level determines the level of the tree
	 * @param q     determines the quadrant
	 * @param array used to build the SE tree
	 * @return the SE ImageBlob
	 */
	private ImageBlob<Pixel> recSEfindPixel(int level, int q, Pixel[][] array) {
		// Local Variables
		int lastLevel = gridRow / 2;
		ImageBlob<Pixel> newBlob = new ImageBlob<Pixel>();

		// Base case = stop recursion --
		if (level == lastLevel) {
			int beginRow = calculateRow(level, q);
			int beginCol = calculateCol(level, q);
			newBlob.value = array[beginRow][beginCol];
		}

		// General case -- continue recursion
		else {
			newBlob.NW = recNWfindPixel(level + 1, NW, array);
			newBlob.NE = recNEfindPixel(level + 1, NE, array);
			newBlob.SE = recSEfindPixel(level + 1, SE, array);
			newBlob.SW = recSWfindPixel(level + 1, SW, array);
			if (newBlob.NW.value.equals(newBlob.NE.value) && newBlob.NW.value.equals(newBlob.SE.value)
					&& newBlob.NW.value.equals(newBlob.SW.value)) {
				newBlob = new ImageBlob<Pixel>(newBlob.NW.value);
			}
		}
		return newBlob;
	}

	/**
	 * Uses recursion to build the SW ImageBlob.
	 * 
	 * @param level determines the level of the tree
	 * @param q     determines the quadrant
	 * @param array used to build the SW tree
	 * @return the SW ImageBlob
	 */
	private ImageBlob<Pixel> recSWfindPixel(int level, int q, Pixel[][] array) {
		// Local Variables
		int lastLevel = gridRow / 2;
		ImageBlob<Pixel> newBlob = new ImageBlob<Pixel>();

		// Base case = stop recursion --
		if (level == lastLevel) {
			int beginRow = calculateRow(level, q);
			int beginCol = calculateCol(level, q);
			newBlob.value = array[beginRow][beginCol];
		}

		// General case -- continue recursion
		else {
			newBlob.NW = recNWfindPixel(level + 1, NW, array);
			newBlob.NE = recNEfindPixel(level + 1, NE, array);
			newBlob.SE = recSEfindPixel(level + 1, SE, array);
			newBlob.SW = recSWfindPixel(level + 1, SW, array);
			if (newBlob.NW.value.equals(newBlob.NE.value) && newBlob.NW.value.equals(newBlob.SE.value)
					&& newBlob.NW.value.equals(newBlob.SW.value)) {
				newBlob = new ImageBlob<Pixel>(newBlob.NW.value);
			}
		}
		return newBlob;
	}

	/**
	 * Calculates the row needed to build the tree.
	 * 
	 * @param level    determines the level of the tree
	 * @param quadrant determines what quadrant to calculate for
	 * @return the row for the quadrant
	 */
	private int calculateRow(int level, int quadrant) {
		int beginRow = 0;
		if (count == gridRow / 2) {
			rowNW++;
			rowNW++;
			rowNE++;
			rowNE++;
			rowSE++;
			rowSE++;
			rowSW++;
			rowSW++;
			count = 0;
		}
		switch (quadrant) {
			case NW:
				beginRow = rowNW;
				break;
			case NE:
				beginRow = rowNE;
				break;
			case SE:
				beginRow = rowSE;
				break;
			case SW:
				beginRow = rowSE;
				break;
		}
		return beginRow;
	}

	/**
	 * Calculates the column value needed to build the tree.
	 * 
	 * @param level    determines the level of the tree
	 * @param quadrant determines what quadrant to calculate for
	 * @return the column to build the tree
	 */
	private int calculateCol(int level, int quadrant) {
		int beginCol = 0;
		switch (quadrant) {
			case NW:
				beginCol = colNW;
				if (colNW == gridCol) {
					colNW = 0;
					beginCol = 0;
				}
				colNW++;
				colNW++;
				break;
			case NE:
				if (colNE == 0) {
					colNE++;
				} else if (colNE == gridCol - 1) {
					colNE = 1;
				} else {
					colNE++;
					colNE++;
				}
				beginCol = colNE;
				break;
			case SE:
				if (colSE == 0) {
					colSE = 1;
				} else if (colSE == gridRow - 1) {
					colSE = 1;
				} else {
					colSE++;
					colSE++;
				}
				beginCol = colSE;
				break;
			case SW:
				beginCol = colSW;
				if (colSW == gridRow) {
					colSW = 0;
					beginCol = 0;
				}
				colSW++;
				colSW++;
				break;
		}
		return beginCol;
	}

	/**
	 * This method gets the value stores in the node ImageBlob.
	 * 
	 * @param w is the width of the pixel's location
	 * @param h is the height of the pixel's location
	 * @return the pixel at that location
	 */
	public Pixel getColor(int w, int h) {
		if (w > gridRow) {
			throw new RuntimeException("Width is greater than the size of the Tree!");
		}
		if (h > gridCol) {
			throw new RuntimeException("Height is greater than the size of the Tree!");
		}
		return root.value;
	}

	/**
	 * This method sets the sets of a pixel location at (w,h).
	 * 
	 * @param w     is the width of the pixel's location
	 * @param h     is the height of the pixel's location
	 * @param value is the color that needs to be set
	 */
	public void setColor(int w, int h, Pixel value) {
		if (w > gridRow) {
			throw new RuntimeException("Width is greater than the size of the Tree!");
		}
		if (h > gridCol) {
			throw new RuntimeException("Height is greater than the size of the Tree!");
		}
		root.value = value;
	}

	/**
	 * This method returns the ratio between the Pixels and Pointers.
	 * 
	 * @param bytesPerPixel   amount of bytes per pixel
	 * @param bytesPerPointer amount of bytes per pointer
	 * @return the ratio between these
	 */
	public float sizeRatio(int bytesPerPixel, int bytesPerPointer) {
		return bytesPerPixel / bytesPerPointer;
	}

	/**
	 * This method converts the tree to a string.
	 * 
	 * @return the string of the Tree
	 */
	public String toString() {
		if (this.root == null) {
			return "";
		}
		return "";
	}

	/**
	 * This methods values the difference in two trees brightness.
	 * 
	 * @param other tree to be compared
	 * @return the difference in brightness
	 */
	public int compareTo(QuadTreeImage<Pixel> other) {
		if (other == null) {
			return 0;
		}
		return (int) root.value - (int) other.root.value;
	}

	/**
	 * This method finds the similar nodes between two trees.
	 * 
	 * @param other is the tree to be compared
	 * @return the nodes that intersect
	 */
	public QuadTreeImage<Pixel> intersectionMask(QuadTreeImage<Pixel> other) {
		if (other == null) {
			throw new RuntimeException("Other is null!");
		}
		return other;
	}

	/**
	 * This method is used to iterate through the tree.
	 * 
	 * @return iterator used to iterate through the tree
	 */
	public Iterator<ImageBlob<Pixel>> iterator() {

		return new Iterator<ImageBlob<Pixel>>() {
			ImageBlob<Pixel> currentNode = root;

			public boolean hasNext() {
				return currentNode != null;
			}

			public ImageBlob<Pixel> next() {
				ImageBlob<Pixel> temp = currentNode;
				if (currentNode.NW.value != null) {
					currentNode = currentNode.NW;
				}
				if (currentNode.NE.value != null) {
					currentNode = currentNode.NE;
				}
				if (currentNode.SE.value != null) {
					currentNode = currentNode.SE;
				}
				if (currentNode.SW.value != null) {
					currentNode = currentNode.SW;
				}
				return temp;
			}

		};
	}

}