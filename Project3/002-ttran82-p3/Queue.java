/* This is the class for a generic queue (FIFO)
 *
 * @Author Toan Tran
 *@Version April 08, 2018
*/

public class Queue<AnyType>{

	// You decide internal attributtes or additional classes to implement a queue
	// All those must be private

	/**
	 * private linked list class
	 *
	 */
	private class Node<AnyType>{
		Node<AnyType> next;
		AnyType data;
		Node(AnyType value){
			this.data = value;
			next = null;
		}
	}

	//fields used to make the queue
	private int size =0;
	private Node<AnyType> head;
	private Node<AnyType> tail;

	/**
	 * constructor used to instantiate queue
	 */
	public Queue() {
		// Queue Constructor: should not need any argument
		// initialize your internal attributes, could be empty
	
	}

	/**
	 * method used to check if queue is empty
	 * @return returns true is empty,false if not
	 */
	public boolean isEmpty() {
		// check if queue is empty 
		// return true if empty, false otherwise
		// O(1)
		return size ==0;
	}

	/**
	 * method used to see what is at the head of the queue
	 * @return return the data at the head of the queue
	 */
	public AnyType getFront() {
		// peek the front element
		// return the front element if there is any but do not dequeue
		// return null if queue is empty
		// O(1)
		if(isEmpty())
			return null;
		return head.data;
	}

	/**
	 * method adds object of certain value to queue
	 * @param value used to add, String,Integer,Objects etc
	 */
	public void enqueue(AnyType value) {
   		// add an element to the back
		// O(1)
		Node<AnyType> toEnqueue = new Node(value);
		if(size ==0) {
			head = toEnqueue;
		}else {
			tail.next = toEnqueue;
		}
		tail = toEnqueue;
		this.size++;
	}

	/**
	 * removes the opject at the front of the queue
	 * @return returns the object that was removed
	 */
	public AnyType dequeue(){
   		// remove and return an element from the front
   		// return null if queue empty
		// O(1)
		if(isEmpty())
			return null;
		Node<AnyType> toReturn = head;
		head = head.next;
		this.size--;
		return toReturn.data;
	}

	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!

	public static void main(String[] args){
		Queue<Integer> iq = new Queue<Integer>();
		if (iq.isEmpty() && iq.getFront()==null){
			System.out.println("Yay 1");
		}
		
		iq.enqueue(new Integer(12));
		iq.enqueue(new Integer(20));
		iq.enqueue(new Integer(-233));
		
		if (iq.dequeue()==12 && iq.getFront()==20 ){
			System.out.println("Yay 2");
		}
		
		if (iq.dequeue()==20 && iq.dequeue()==-233 && iq.isEmpty() ){
			System.out.println("Yay 3");
		}

	}

  }