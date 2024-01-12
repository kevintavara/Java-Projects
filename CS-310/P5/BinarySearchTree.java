// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// void removeMin( )      --> Remove minimum item
// Comparable find( x )   --> Return item that matches x
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// int size( )            --> Return the number of items
// Iterator<AnyType> iterator( ) --> Returns an iterator
// ******************ERRORS********************************
// Exceptions are thrown by insert, remove, and removeMin if warranted

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> implements Iterable<AnyType>
{
  /**
   * Construct the tree.
   */
  public BinarySearchTree( )
  {
    
  }
  
  /**
   * Insert into the tree.
   * @param x the item to insert.
   * @throws DuplicateItemException if x is already present.
   */
  public void insert( AnyType x )
  {
    root = insert( x, root );
  }
  
  /**
   * Remove from the tree..
   * @param x the item to remove.
   * @throws ItemNotFoundException if x is not found.
   */
  public void remove( AnyType x )
  {
    root = remove( x, root );
  }
  
  /**
   * Remove minimum item from the tree.
   * @throws ItemNotFoundException if tree is empty.
   */
  public void removeMin( )
  {
    root = removeMin( root );
  }
  
  /**
   * Find the smallest item in the tree.
   * @return smallest item or null if empty.
   */
  public AnyType findMin( )
  {
    return elementAt( findMin( root ) );
  }
  
  /**
   * Find the largest item in the tree.
   * @return the largest item or null if empty.
   */
  public AnyType findMax( )
  {
    return elementAt( findMax( root ) );
  }
  
  /**
   * Find an item in the tree.
   * @param x the item to search for.
   * @return the matching item or null if not found.
   */
  public AnyType find( AnyType x )
  {
    return elementAt( find( x, root ) );
  }
  
  /**
   * Make the tree logically empty.
   */
  public void makeEmpty( )
  {
    root = null;
    size = 0;
  }
  
  /**
   * Test if the tree is logically empty.
   * @return true if empty, false otherwise.
   */
  public boolean isEmpty( )
  {
    return root == null;
  }
  
  /**
   * Returns the size of the tree.
   * @return the number of items.
   */
  public int size( )
  {
    return size;
  }
  
  /**
   * Internal method to get element field.
   * @param t the node.
   * @return the element field or null if t is null.
   */
  private AnyType elementAt( BinaryNode<AnyType> t )
  {
    return t == null ? null : t.element;
  }
  
  /**
   * Internal method to insert into a subtree.
   * @param x the item to insert.
   * @param t the node that roots the tree.
   * @return the new root.
   * @throws DuplicateItemException if x is already present.
   */
  protected BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
  {
    if( t == null )
    {
      t = new BinaryNode<AnyType>( x );
      size++;
    }
    else if( x.compareTo( t.element ) < 0 )
      t.left = insert( x, t.left );
    else if( x.compareTo( t.element ) > 0 )
      t.right = insert( x, t.right );
    else
      throw new DuplicateItemException( );  // Duplicate
    return t;
  }
  
  /**
   * Internal method to remove from a subtree.
   * @param x the item to remove.
   * @param t the node that roots the tree.
   * @return the new root.
   * @throws ItemNotFoundException if x is not found.
   */
  protected BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
  {
    if( t == null )
      throw new ItemNotFoundException( );
    if( x.compareTo( t.element ) < 0 )
      t.left = remove( x, t.left );
    else if( x.compareTo( t.element ) > 0 )
      t.right = remove( x, t.right );
    else if( t.left != null && t.right != null ) // Two children
    {
      t.element = findMin( t.right ).element;
      t.right = removeMin( t.right );
    }
    else
    {
      t = ( t.left != null ) ? t.left : t.right;
      size--;
    }
    return t;
  }
  
  /**
   * Internal method to remove minimum item from a subtree.
   * @param t the node that roots the tree.
   * @return the new root.
   * @throws ItemNotFoundException if t is empty.
   */
  protected BinaryNode<AnyType> removeMin( BinaryNode<AnyType> t )
  {
    if( t == null )
      throw new ItemNotFoundException( );
    else if( t.left != null )
    {
      t.left = removeMin( t.left );
      return t;
    }
    else
    {
      size--;
      return t.right;
    }
  }    
  
  /**
   * Internal method to find the smallest item in a subtree.
   * @param t the node that roots the tree.
   * @return node containing the smallest item.
   */
  protected BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
  {
    if( t != null )
      while( t.left != null )
      t = t.left;
    
    return t;
  }
  
  /**
   * Internal method to find the largest item in a subtree.
   * @param t the node that roots the tree.
   * @return node containing the largest item.
   */
  private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
  {
    if( t != null )
      while( t.right != null )
      t = t.right;
    
    return t;
  }
  
  /**
   * Internal method to find an item in a subtree.
   * @param x is item to search for.
   * @param t the node that roots the tree.
   * @return node containing the matched item.
   */
  private BinaryNode<AnyType> find( AnyType x, BinaryNode<AnyType> t )
  {
    while( t != null )
    {
      if( x.compareTo( t.element ) < 0 )
        t = t.left;
      else if( x.compareTo( t.element ) > 0 )
        t = t.right;
      else
        return t;    // Match
    }
    
    return null;         // Not found
  }
  
  /**
   * Obtains an Iterator object used to traverse the collection.
   * @return an iterator positioned prior to the first element.
   */
  public java.util.Iterator<AnyType> iterator( )
  {
    return new BinaryTreeIterator<AnyType>( root );
  }
  
  /** The tree root. */
  protected BinaryNode<AnyType> root;
  
  /** The tree size. */
  protected int size;
}
