/* This is the class for a generic Stack (FILO)
 * @Author Toan Tran
 * @Version April 08, 2018
*/

public class Stack<AnyType>{

	// You decide internal attributtes or additional classes to implement a stack
	// All those must be private

	/**
	 * double linked list class
	 * @param <AnyType> type of data to be stored inside list
	 */
	private class Node <AnyType>{
		//fields used to store data
		private AnyType data;
		private Node<AnyType> next,prev;
		public Node(AnyType value){
			this.data = value;
			this.prev.next = null;
		}
		public Node(AnyType value,Node<AnyType> prev,Node<AnyType> next){
			this.data = value;
			this.prev = prev;
			this.next = next;
		}
	}
	// fields used to keep data inside stack
	private int size;
	private Node<AnyType> head;
	private Node<AnyType> tail;

	/**
	 * constructor used to initiliaze fields.
	 */
	public Stack() {
		// Stack Constructor
		// use this to initialize internal attributes
		// could be empty
		head = new Node<AnyType>(null,null,null);
		tail = new Node<AnyType>(null,head,null);
		head.next = tail;
		this.size = 0;
	}

	/**
	 * checks if stack is empty
	 * @return if stack is empty or not
	 */
	public boolean isEmpty() {
		// check if stack is empty
		// return true if empty, false otherwise
		// O(1)
		return size == 0;
	}

	/**
	 * look at the what's at the top of the stack
	 * @return return what value is at the top of the stack
	 */
	public AnyType peek() {
		// peek the stack top
		// return stack top but do not pop
		// return null for empty stack
		// O(1)
		if(size == 0)
			return null;
		return tail.prev.data;
	}

	/**
	 * pushes a new object with value to the stack
	 * @param value the type of data of the stack, String,Integer, other objects
	 */
	public void push(AnyType value) {
		// push an element to the stack
		// O(1)
		Node<AnyType> node = new Node(value,tail.prev,tail);
		tail.prev.next = node;
		tail.prev = node;
		this.size++;
	}

	/**
	 * removes the object at the top of the stack
	 * @return	returns the object that was removed
	 */
	public AnyType pop(){
		// pop from the top
		// remove and return the stack top
		// return null for empty stack
		// O(1)
		if(size ==0)
			return null;
		Node<AnyType> toReturn = tail.prev;
		tail.prev.prev.next=tail;
		tail.prev = tail.prev.prev;
		this.size--;
		return toReturn.data;
	}

	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!

	public static void main(String[] args){
		Stack<Integer> iStack = new Stack<Integer>();
		if (iStack.isEmpty() && iStack.peek()==null){
			System.out.println("Yay 1");
		}
		
		iStack.push(new Integer(12));
		iStack.push(new Integer(20));
		iStack.push(new Integer(-233));
//		if(iStack.pop()== 20 && iStack.peek() == 12)
//			System.out.println("Yay 2");
		if (iStack.pop()==-233 && iStack.peek()==20 ){
			System.out.println("Yay 2");
		}
		
		if (iStack.pop()==20 && iStack.pop()==12 && iStack.isEmpty() ){
			System.out.println("Yay 3");
		}

		
	}

}