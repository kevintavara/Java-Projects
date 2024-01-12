import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class is used to load and write PGM files.
 * 
 * @author Kevin Tavara
 */
final class Utilities {
	/**
	 * P2 seems to be the default format.
	 */
	private static String fileFormat = null;
	
	/**
	 * Maximum value allowed for the pixel.
	 */
	private static int intMaxPixel;
	
	/**
	 * Number of rows in array.
	 */
	private static int row = 0;
	
	/**
	 * Number of columns in array.
	 */
	private static int col = 0;
	
	/**
	 * Loads the PGM file into 2D Short array.
	 *   
	 * @param pgmFile name of the file to load
	 * @return Short[][] 2D array of pixels
	 */
	static Short[][] loadFile(String pgmFile) {
		// Local Variables
		Scanner inStream = null;
		Short [][] pixels = null;
		String strTemp    = null;
		String strTempFields[]  = null;
		String strMaxPixel = null;
		
		// Read the file
		try {
			// Open the input file stream
			inStream = new Scanner(new File(pgmFile));
			
			// Read in the 1st line which contains the file format
			fileFormat = inStream.nextLine();
			
			// Read in the 2nd line that contains the row x col grid
			strTemp = inStream.nextLine();
			strTempFields = strTemp.split(" ");
			row = Integer.parseInt(strTempFields[0]);
			col = Integer.parseInt(strTempFields[1]);
			
			// Read in 3rd line that contains the maximum pixel
			strMaxPixel = inStream.nextLine();
			intMaxPixel = Integer.parseInt(strMaxPixel);
		
			// Instantiate the 2D array of pixels
			pixels = new Short[row][col];

			// Read in the pixels and store in 2D array
			row = 0;
			col = 0;
			while (inStream.hasNext()) {
				strTemp = inStream.next();
				pixels[row][col] = Short.parseShort(strTemp);
				col++;
				if (col == pixels[0].length) {
					row++;
					col = 0;
 				}
			}
			return pixels;
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}
		catch (Exception e) {
			System.out.println("Exception error: " + e.getMessage());			
		}
		return null;
	}

	/**
	 * Writes the 2D array into a PGM file format (text file).
	 *   
	 * @param image is the Tree that needs to be written in PGM file
	 * @param fileName is the name of the file being written to
	 */	
	static void exportImage(QuadTreeImage<Number> image, String fileName) {
		if (fileName == null) {
			throw new RuntimeException("fileName not valid!");
		}
		if (image == null) {
			throw new RuntimeException("Tree is null!");
		}
		fileName = fileFormat;
		fileName = Integer.toString(intMaxPixel);
	}
}