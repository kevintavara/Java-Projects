/**
 * This class is used to iterate through the tree.
 * 
 * @author Kevin Tavara
 *
 * @param <Pixel> stores the color in value
 */
public class QuadTreeImageIterator<Pixel extends Number> {
	/**
	 * Current node in tree.
	 */
	private ImageBlob<Pixel> current;
	
	/**
	 * Constructor method used to set the root of the tree.
	 * 
	 * @param root of the tree to iterate through
	 */
	private QuadTreeImageIterator(ImageBlob<Pixel> root) {
		this.current = root;
	}
	
	/**
	 * Checks whether or not there is a next node.
	 * 
	 * @return whether or not there is a next
	 */
	public boolean hasNext() {
		return current != null;
	}
	
	/**
	 * Goes to the next node of the tree.
	 * 
	 * @return the next ImageBlob
	 */
	public ImageBlob<Pixel> next() {
		ImageBlob<Pixel> temp = current; 
		if (current.NW.value !=  null) {
			current = current.NW;
		}
		if (current.NE.value !=  null) {
			current = current.NE;
		}
		if (current.SE.value !=  null) {
			current = current.SE;
		}
		if (current.SW.value !=  null) {
			current = current.SW;
		}
		return temp;
	}
}