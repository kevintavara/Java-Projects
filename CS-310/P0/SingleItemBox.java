/**
 *  This is the SingleItemBox generic class
 *  @author S. Kevin Tavara
 */
public class SingleItemBox<T>{
   
   	//Declares item private cause of procedures
   	private T Item;
  
   	//Constructor makes a box
   	public SingleItemBox(T Item){
    
     	//Puts item in box
      	this.Item = Item; 
   	}
   	
   	//Getter method to get back item
   	public T getItem(){
    
      		//Returns item in Box
      		return this.Item;
   	}
}