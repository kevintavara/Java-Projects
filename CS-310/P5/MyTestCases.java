import java.io.*;
import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class MyTestCases {
  @Test(timeout=2000)
  public void testBinaryNode() {
    BinaryNode<String> node = new BinaryNode<String>("a");
    node.left = new BinaryNode<String>("b");
    node.right = new BinaryNode<String>("c");
    assertEquals(node.element, "a");
    assertEquals(node.left.element, "b");
    assertEquals(node.right.element, "c");
  }
  
  @Test(timeout=2000)
  public void testBinarySearchTree() {
    BinarySearchTree<String> tree = new BinarySearchTree<String>();
    assertEquals(tree.findMin(), null);
    assertEquals(tree.findMax(), null);
    assertEquals(tree.find("a"), null);
    assertEquals(tree.find("b"), null);
    assertEquals(tree.isEmpty(), true);
    assertEquals(tree.size(), 0);
    tree.insert("a");
    assertEquals(tree.findMin(), "a");
    assertEquals(tree.findMax(), "a");
    assertEquals(tree.find("a"), "a");
    assertEquals(tree.find("b"), null);
    assertEquals(tree.isEmpty(), false);
    assertEquals(tree.size(), 1);
    tree.insert("b");
    assertEquals(tree.findMin(), "a");
    assertEquals(tree.findMax(), "b");
    assertEquals(tree.find("a"), "a");
    assertEquals(tree.find("b"), "b");
    assertEquals(tree.isEmpty(), false);
    assertEquals(tree.size(), 2);
    tree.removeMin();
    assertEquals(tree.findMin(), "b");
    assertEquals(tree.findMax(), "b");
    assertEquals(tree.find("a"), null);
    assertEquals(tree.find("b"), "b");
    assertEquals(tree.isEmpty(), false);
    assertEquals(tree.size(), 1);
    tree.insert("a");
    tree.remove("a");
    assertEquals(tree.findMin(), "b");
    assertEquals(tree.findMax(), "b");
    assertEquals(tree.find("a"), null);
    assertEquals(tree.find("b"), "b");
    assertEquals(tree.isEmpty(), false);
    assertEquals(tree.size(), 1);
    tree.insert("a");
    tree.remove("b");
    assertEquals(tree.findMin(), "a");
    assertEquals(tree.findMax(), "a");
    assertEquals(tree.find("a"), "a");
    assertEquals(tree.find("b"), null);
    assertEquals(tree.isEmpty(), false);
    assertEquals(tree.size(), 1);
    tree.insert("b");
    tree.makeEmpty();
    assertEquals(tree.size(), 0);
    tree.insert("a");
    try {
      tree.insert("a");
    } 
    catch (DuplicateItemException e) {
    }
    assertEquals(tree.size(), 1);
    try {
      tree.remove("b");
    } 
    catch (ItemNotFoundException e) {
    }
    assertEquals(tree.size(), 1);
    tree.makeEmpty();
    try {
      tree.remove("d");
    } 
    catch (ItemNotFoundException e) {
    }
    assertEquals(tree.size(), 0);
    try {
      tree.removeMin();
    } 
    catch (ItemNotFoundException e) {
    }
    tree.insert("d");
    tree.insert("b");
    tree.insert("f");
    tree.insert("a");
    tree.insert("c");
    tree.insert("e");
    tree.insert("g");
    assertEquals(tree.findMin(), "a");
    tree.removeMin();
    assertEquals(tree.find("a"), null);
    assertEquals(tree.find("b"), "b");
    assertEquals(tree.find("c"), "c");
    assertEquals(tree.find("d"), "d");
    assertEquals(tree.find("e"), "e");
    assertEquals(tree.find("f"), "f");
    assertEquals(tree.find("g"), "g");
    assertEquals(tree.size(), 6);
    tree.remove("f");
    assertEquals(tree.size(), 5);
    assertEquals(tree.find("b"), "b");
    assertEquals(tree.find("c"), "c");
    assertEquals(tree.find("d"), "d");
    assertEquals(tree.find("e"), "e");
    assertEquals(tree.find("f"), null);
    assertEquals(tree.find("g"), "g");
    tree.makeEmpty();
    tree.insert("a");
    tree.insert("b");
    tree.insert("c");
    tree.remove("b");
    assertEquals(tree.size(), 2);
    assertEquals(tree.find("a"), "a");
    assertEquals(tree.find("b"), null);
    assertEquals(tree.find("c"), "c");
    tree.makeEmpty();
    tree.insert("c");
    tree.insert("b");
    tree.insert("a");
    tree.remove("c");
    assertEquals(tree.size(), 2);
    assertEquals(tree.find("a"), "a");
    assertEquals(tree.find("b"), "b");
    assertEquals(tree.find("c"), null);
    tree.remove("a");
    tree.remove("b");
    assertEquals(tree.isEmpty(), true);
  }
  
  @Test(timeout=2000)
  public void testBinaryTreeIterator() {
    BinarySearchTree<String> tree = new BinarySearchTree<String>();
    Iterator<String> iterator = tree.iterator();
    assertEquals(iterator.hasNext(), false);
    tree.insert("d");
    tree.insert("b");
    tree.insert("f");
    tree.insert("a");
    tree.insert("c");
    tree.insert("e");
    tree.insert("g");
    iterator = tree.iterator();
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), "d");
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), "b");
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), "f");
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), "a");
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), "c");
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), "e");
    assertEquals(iterator.hasNext(), true);
    assertEquals(iterator.next(), "g");
    assertEquals(iterator.hasNext(), false);
    try {
      iterator.next();
    } 
    catch (NoSuchElementException e) {
    }
    assertEquals(iterator.hasNext(), false);
  }
  
  //run the unit tests
  public static void main(String args[]) {
    org.junit.runner.JUnitCore.main("MyTestCases");
  }
}