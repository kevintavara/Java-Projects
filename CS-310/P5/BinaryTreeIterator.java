/**
 * This is the implementation of the TreeSetIterator.
 * It maintains a notion of a current position and of
 * course the implicit reference to the TreeSet.
 */
public class BinaryTreeIterator<AnyType> implements java.util.Iterator<AnyType>
{
  private java.util.ArrayList<BinaryNode<AnyType>> path = new java.util.ArrayList<>( );
  
  public BinaryTreeIterator( BinaryNode<AnyType> root )
  {
    if( root == null )
      return;
    
    java.util.LinkedList<BinaryNode<AnyType>> q = new java.util.LinkedList<>( );
    q.add( root );
    while( !q.isEmpty( ) ) {
      BinaryNode<AnyType> n = q.remove( 0 );
      
      if( n.left != null )
        q.add( n.left );
      if( n.right != null )
        q.add( n.right );
      
      path.add( n );
    }
  }
  
  public boolean hasNext( )
  {
    return !path.isEmpty( );
  }
  
  public AnyType next( )
  {
    if( !hasNext( ) )
      throw new java.util.NoSuchElementException( );     
    
    return path.remove( 0 ).element;
  }
}