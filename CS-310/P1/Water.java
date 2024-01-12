import java.awt.Color;

/**
 * Representation of a bit of water.
 * 
 * @author Kevin Tavara
 */
public class Water extends Element {
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public Color getColor() {
		return Color.BLUE;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public int getWeight() {
		return 150;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		// Check for valid row and columns
		if ( row < 0 || row >= Simulation.INIT_ROWS) return;
		if ( col < 0 || col > Simulation.INIT_COLS) return;
		
		// Find out which element is below
		Element below = grid.get(row+1).get(col);
		
		// Swap this and below 
		if(this.compareTo(below) >= 0 && below.push(grid, row+1, col)) {
			grid.get(row).set(col, below);
			grid.get(row+1).set(col, this);
		}
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		// Check for valid row and columns
		if ( row < 0 || row > Simulation.INIT_ROWS) return false;
		if ( col < 0 || col > Simulation.INIT_COLS) return false;
		
		// Find out which element is in location
		Element cur = grid.get(row).get(col);
	
		// Check which is heavier
		if(this.compareTo(cur) >= 0) {
			return true;
		}
		return false;
	}
}