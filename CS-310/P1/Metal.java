//TO DO: Nothing required here.

//******************************************************
//*******  DO NOT EDIT ANYTHING BELOW THIS LINE  *******
//*******  But do read this code (it will help!) *******
//******************************************************

import java.awt.Color;

/**
 *  Representation of a bit of metal.
 *  
 *  @author K. Raven Russell
 */
public class Metal extends Element {
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public Color getColor() {
		return Color.RED;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public int getWeight() {
		return Integer.MAX_VALUE;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		//metal doesn't fall down
	}
	
	/**
	 *  {@inheritDoc}
	 */
	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		//metal can't be pushed
		return false;
	}
	
	/**
	 *  Main method that shows an example of testing an element.
	 *  
	 *  @param args not used
	 */
	public static void main(String args[]){
		//This is an outline of how to test that an element
		//is doing what you want it to (without using the
		//simulator. You should definitely write similar tests
		//for Sand and Water.
		
		//All tests work like this:
		//    (1) setup a scenario
		//    (2) call some method(s) that will change (or not change) the scenario
		//    (3) check that what should happen did happen
		//Example:
		
		//(1) setup a scenario
		
		//create a grid and a piece of Metal
		DynamicArray<DynamicArray<Element>> grid = new DynamicArray<>( 3 );
		//TO DO: initialize the grid to a 3x3 area
		
		for ( int i = 0; i < 3; i++ )
		{
			grid.set( i, new DynamicArray<Element>( 3 ));
		}
		
		
		Metal m = new Metal();
		
		//put the element in the middle
		
		grid.get(0).set(0,  new Water() );
		grid.get(0).set(1,  new Metal() );
		grid.get(0).set(2,  new Water() );
		
		grid.get(1).set(0,  new Metal() );
		grid.get(1).set(1,  m );
		grid.get(1).set(2,  new Water() );
		
		grid.get(2).set(0,  new Metal() );
		grid.get(2).set(1,  new Water() );
		grid.get(2).set(2,  new Water() );
		
		
		//(2) call some method(s) that will change (or not change) the scenario
		//m.fall(grid, 1, 1);
		
		
		//(3) check that what should happen did happen
		
		//in this case, the metal should not have moved
		//if it was sand, it would be at grid.get(2).get(1)
		//if it was water, it might be at grid.get(1).get(0), grid.get(1).get(2), or grid.get(2).get(1)
		
		if(grid.get(1).get(1) == m) {
			//^ careful about == here, that's checking for the same memory location...
			System.out.println("Yay");
		}
		
		//make more scenarios or continue this one!
	}
}