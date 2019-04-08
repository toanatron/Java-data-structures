import java.util.Iterator;
import java.util.NoSuchElementException;
/**************************************************************************
 * @author <TOAN TRAN>
 * CS310 Spring 2018
 * Project 2
 * George Mason University
 * 
 * File Name: SimpleList.java
 *
 * Description: a linked list class that is used for separate chaining.
 * 
 ***************************************************************************/

class SimpleList < T > implements Iterable < T > {

 // a linked list class 
 // you decide the internal attributes and node structure
 // but they should all be private

 private class Node < T > {
  //a private class that is used to initiate the linked list
  T value;
  Node < T > next;

  Node(T value) {
   this.value = value;
   this.next = null;
  }
 }

 //private attributes used for linked list
 private Node < T > head;
 private Node < T > tail;
 private int size;

 //constructor used to instantiate an object
 //used to initialize size 	
 public SimpleList() {
  //constructor
  this.size = 0;
 }

 //adds a new value to the linkedlist, increment the size of the table
 //@param a value that needs to be added to the table
 public void add(T value) {
  // add a new node to the end of the linked list to hold value
  // O(1) 
  Node < T > node = new Node(value);
  if (size == 0) {
   head = node;
  } else {
   tail.next = node;
  }
  tail = node;
  size++;
 }

 //remove a certain value from the linked list
 //@param a value that is to be removed from thelist
 //@return if value cannot be remove return false, otherwise remove and return true	
 public boolean remove(T value) {
  // given a value, remove the first occurrence of that value
  // return true if value removed
  // return false if value not present
  // O(N) where N is the number of nodes returned by size()
  // Node<T> node = new Node(value);
  if (contains(value) == false) {
   return false;
  }
  //if first node is the value, remove and return true
  if (head.value.equals(value)) {
   head = head.next;
   size--;
   return true;
  }
  //loop through the linked list to find the value, if value is inside linked list return true
  Node < T > cur = head.next;
  Node < T > prev = head;
  for (int i = 0; i < size() - 1; i++) {
   if (cur.value.equals(value)) {
    prev.next = cur.next;
    size--;
    return true;
   }
   prev = cur;
   cur = cur.next;
  }

  return false;
 }

 //method determines the index of a certain value
 //@param a value that needs to be found inside the linked list
 //@return the index of the value, return -1 if it's not inside the list
 public int indexOf(T value) {
  // return index of a value (0 to size-1)
  // if value not present, return -1
  // O(N)
  Node < T > cur = head;
  //loop through the list to find if there's the current node is equal to value.
  for (int i = 0; i < size; i++) {
   if (cur.value.equals(value)) {
    return i;
   }
   cur = cur.next;
  }
  return -1;
 }

 //method determines if list contains value
 //@param value that needs to be found inside list
 //@return if list contains value return true, else return false
 public boolean contains(T value) {
  // return true if value is present
  // false otherwise
  // O(N) where N is the number of nodes returned by size()
  if (indexOf(value) == -1) {
   return false;
  }
  return true;
 }

 //method search for certain value inside list
 //@param value to be searched for inside list
 //@return the value stored inside the list, not the value passed inside parameter. As two values might be equal, but not identical
 public Tget(T value) {
  // search for the node with the specified value:
  // if not found, return null;
  // if found, RETURN VALUE STORED from linked list, NOT the incoming value
  // Note: two values might be considered "equal" but not identical
  //       example: Pair <k,v1> and <k,v2> "equal" for different v1 and v2 
  // O(N) where N is the number of nodes returned by size()
  if (contains(value) == false) {
   return null;
  }
  int index = indexOf(value);

  //loop through list until the index of the value and return that value
  Node < T > cur = head;
  for (int i = 0; i < index; i++) {
   cur = cur.next;
  }
  return cur.value;
 }

 //method returns the size of the list
 //@return how many nodes are inside the list
 public int size() {
  //return how many nodes are there
  //O(1)
  return size;
 }

 //method instantiate a SimpeListIterator that implements Iterator
 //contains a .hasNext() and .next()
 @Override
 public Iterator < T > iterator() {
  // return a basic iterator
  // .hasNext() and .next() required 
  // both should be of O(1)
  return new SimpleListIterator(head, size());

 }
 private class SimpleListIterator implements Iterator < T > {
  //class implements Iterator

  //attributes used to determine .hasNext() and .next()
  private int start = 0;
  private int end;
  private Node < T > head;
  SimpleListIterator(Node < T > head, int end) {
   this.head = head;
   this.end = end;
  }

  //method determines whether or not there's another node 
  //@returns if there's another node return true, otherwise false
  public boolean hasNext() {
   if (start == 0) {
    return head != null;
   } else {
    return head.next != null;
   }
  }

  //method moves to the next node.
  //@return the value of the next node.
  public T next() {
   if (hasNext() == true) {
    if (start == 0) {
     start++;
     return head.value;
    } else {
     head = head.next;
     start++;
     return head.value;
    }
   }
   throw new NoSuchElementException();
  }

 }

 /*public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    Iterator<T> iter = iterator();
    while (iter.hasNext()) {
        sb.append(iter.next());
    }

    sb.append("]");
    return sb.toString();
   }
   */

 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!
 // also, consider add a toString() for your own debugging

 public static void main(String[] args) {
  SimpleList < Integer > ilist = new SimpleList < Integer > ();
  ilist.add(new Integer(11));
  ilist.add(new Integer(20));
  ilist.add(new Integer(5));
  ilist.add(new Integer(6));
  if (ilist.size() == 4 && ilist.contains(new Integer(5)) &&
   !ilist.contains(new Integer(2)) && ilist.indexOf(new Integer(20)) == 1 && ilist.indexOf(new Integer(5)) == 2) {
   System.out.println("Yay 1");
  }

  if (!ilist.remove(new Integer(16)) && ilist.remove(new Integer(11)) &&
   !ilist.contains(new Integer(11)) && ilist.get(new Integer(20)).equals(new Integer(20))) {
   System.out.println("Yay 2");
  }

  Iterator iter = ilist.iterator();
  if (iter.hasNext() && iter.next().equals(new Integer(20)) && iter.hasNext() &&
   iter.next().equals(new Integer(5)) && iter.next().equals(6) && !iter.hasNext()) {
   System.out.println("Yay 3");


  }

 }

}