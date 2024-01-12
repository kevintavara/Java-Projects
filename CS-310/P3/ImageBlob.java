/**
 * This class creates an ImageBlob that will be used as a node for the Tree.
 * 
 * @author Kevin Tavara
 *
 * @param <Pixel> stores the color of the pixel
 */
public class ImageBlob<Pixel extends Number> {
    
	/**
	 * NW is the child that represents the 1st quadrant.
	 */
	ImageBlob<Pixel> NW; 
    
    /**
	 * NE is the child that represents the 1st quadrant.
	 */
    ImageBlob<Pixel> NE;
    
    /**
	 * SE is the child that represents the 1st quadrant.
	 */
    ImageBlob<Pixel> SE;
    
    /**
	 * SW is the child that represents the 1st quadrant.
	 */
    ImageBlob<Pixel> SW;
    
    /**
     * The value sets the color of the ImageBlob.
     */
    Pixel value;                         
    
    /**
     * Constructor method to initialize value.
     * 
     * @param pixel is used to set the color
     */
    public ImageBlob(Pixel pixel) {
    	this.NW = null;
    	this.NE = null;
    	this.SE = null;
    	this.SW = null;
    	this.value = pixel;
    }
    
    /**
     * Constructor method used when ImageBlob's value is unknown.
     */
    public ImageBlob() {
    	this.NW = null;
    	this.NE = null;
    	this.SE = null;
    	this.SW = null;
    	this.value = null;
    }
}