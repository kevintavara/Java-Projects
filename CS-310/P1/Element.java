//TO DO: Complete one method and the JavaDocs.
//Don't forget to add your name to the author list!

import java.awt.Color;

/**
 *  Abstract class that outlines what elements should look like.
 *  
 *  @author K. Raven Russell, and Kevin Tavara
 */
public abstract class Element implements Comparable<Element> {
	//******************************************************
	//*******  DO NOT EDIT ANYTHING IN THIS SECTION  *******
	//*******        But do read this section!       *******
	//******************************************************
	
	/**
	 *  Returns the color of the element.
	 *  
	 *  @return the current color of the element
	 */
	public abstract Color getColor();
	
	/**
	 *  Returns the weight of the element. Weights
	 *  are used for determining which elements
	 *  are heavier/lighter.
	 *  
	 *  @return the weight of the element
	 */
	public abstract int getWeight();
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public int compareTo(Element e) {
		//remember how this works:
		//if this > e, return something > 0
		//if this < e, return something < 0
		//if this = e, return something = 0
		return this.getWeight() - e.getWeight();
	}
	
	/**
	 *  The element moves one step, if possible. The
	 *  step should be appropriate for the falling
	 *  animation.
	 *  If the element successfully falls, then this element
	 *  is swapped with the element below it.
	 *  
	 *  @param grid the grid that needs to be updated if the element moved
	 *  @param row the current row the element is in
	 *  @param col the current column the element is in
	 */
	public abstract void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col);
	
	/**
	 *  The element moves one step, if possible. The
	 *  step should be appropriate for the animation
	 *  when being pushed.
	 *  If the element is successfully pushed, then this element
	 *  will end up in a different space.
	 *  
	 *  @param grid the grid that needs to be updated if the element moved
	 *  @param row the current row the element is in
	 *  @param col the current column the element is in
	 *  @return whether or not the element was successfully pushed
	 */
	public abstract boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col);
	
	/**
	 *  Tries to push this element to the left.
	 *  If the element is successfully pushed, then
	 *  the element is swapped with the element
	 *  to the left (like falling swaps with the element
	 *  below).
	 *  
	 *  @param grid the grid that needs to be updated if the element moved
	 *  @param row the current row the element is in
	 *  @param col the current column the element is in
	 *  @return whether or not the element was successfully pushed
	 */
	public boolean pushLeft(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		if(col == 0) return false; //definitely can't push left
		
		//find out which element is to the left
		Element left = grid.get(row).get(col-1);
		//If that element is lighter than this element
		//try to push it, and if successful, move this element
		//into that vacated space (swap with the element that
		//is now there).
		
		if(this.compareTo(left) >= 0 && left.push(grid, row, col-1)) {
			// Swamp elements
			grid.get(row).set(  col, left);
			grid.get(row).set(col-1, this);
			return true;
		}
		
		return false;
	}
	
	//******************************************************
	//* END DO-NOT-EDIT SECTION -- ONE METHOD TO COMPLETE: *
	//******************************************************
	/**
	 * This method will attempt to push a element up.
	 * 
	 * @param grid the grid to be updated
	 * @param row element is in
	 * @param col element is in
	 * @return whether pushed or not
	 */
	public boolean pushUp(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		//This method should be a lot like the pushLeft() method above,
		//except it tries to push this item up instead of left.
		if ( row <= 0 || row > Simulation.INIT_ROWS) return false;
		if ( col < 0 || col > Simulation.INIT_COLS) return false;
		
		//find out which element is above
		Element up = grid.get(row-1).get(col);
		
		//If that element is lighter than this element
		//try to push it, and if successful, move this element
		//into that vacated space (swap with the element that
		//is now there).
		if(this.compareTo(up) <= 0 && up.push(grid, row-1, col)) {
			grid.get(row).set(col, up);
			grid.get(row-1).set(col, this);
			return true;
		}
		
		return false;
	}
	
}