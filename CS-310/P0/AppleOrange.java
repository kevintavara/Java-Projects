/**
 *  This is the AppleOrange class that accepts a number X
 *  @author S. Kevin Tavara
 */
public class AppleOrange{
   
	//Accepts args argument
	public static void main(String[] args){
   
    	//Initialize X and i
    	int X = 0;
    	int i = 1;
    
    	//Make sure some argument was given
    	if (args.length != 1){
      		System.err.println("One positive number required as a command line argument.\nExample Usage: java AppleOrange [number]");
      		return;
    	}
    
    	//Check if argument received is int
    	try{
      		X = Integer.parseInt(args[0]);
    	}
    	catch (NumberFormatException nfe){
      		System.err.println("One positive number required as a command line argument.\nExample Usage: java AppleOrange [number]");
      	return;
    	}
    
    	//Check if X is positive
    	if (X < 1){
      		System.err.println("One positive number required as a command line argument.\nExample Usage: java AppleOrange [number]");
      		return;
    	}
    
    	//While loop used for printing current value i
    	while (i <= X){
      
      		//Multiple if statements used to catch all special cases
      		if (i%21==0){
        		System.out.print("appleorange ");
        		i = i + 1;
      		}
      		else if (i%3==0){
        		System.out.print("apple ");
        		i = i + 1;
      		}
      		else if (i%7==0){
        		System.out.print("orange ");
        		i = i + 1;
      		}
      		else{
        		System.out.print(i + " ");
        		i = i + 1;
      		}
    	}
	}
}