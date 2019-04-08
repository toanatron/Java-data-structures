// your implementation of PriorityQueue interface
// @Author Toan Tran
//@Version 05/01/2018

// One additional requirement is the value type T must be Comparable.
// When two items are compared to determine the order in priority queue, follow the rules below:
//  1. use the priority of them to determine the order 
//  2. if they are of the same priority, use their values (of type T) to determine the order
//  3. if they are of the same priority and same value, any order is fine



public class MyPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T>
{
	
	// you decide the internal design of your class:
	//  - it must implement the provided PriorityQueue interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in PriorityQueue interface


	static final int INF = Integer.MAX_VALUE; // the max priority to use: infinity
	private int size;		//number of elements
	private Helper<T> [] list;	//arraylist of Helpers
	/**
     * Construct an empty PriorityQueue.
	 * initialize size and list array
     */
    public MyPriorityQueue()
    {
		size = 0;
		list = new Helper[20];
    }

	/**
	 *count how many items present in priority queue
	 * O(1)
	 * @return return the amount of elements
	 */
	public int size(){
		return size;
	}

	/**
	 * return the top item (of the max priority) in the priority queue
	 *O(1)
	 * @return null if empty
	 */
	public T peek(){
		if(size ==0 ) return null;
		return list[1].element;
	}


	/**
	 *remove the top item (of the max priority) from the priority queue
	 * O(logN): N is the number of items in priority queue
	 * @return that item to be deleted
	 * O(logN): N is the number of items in priority queue
	 */
	public T remove(){
		if(isEmpty()) return null;
		Helper<T> toReturn = list[1];		//Node to be returned
		ex(1,size--);						//swap
		list[size+1] = null;
		descend(1);
		return toReturn.element;
	}

	/**
	 *checks to see if parent is smaller than its children, if it is the larger of the children is the new parent
	 * @param index
	 */
	private void descend(int index){
		while(2 * index <= size){
			int left = 2 * index;
			//check if next index is greater than left
			if(left < size && less(left,left++)){
				left++;
			}
			//if left is not smaller than its parent break
			if(!less(index,left)){
				break;
			}
			ex(index,left);
			index = left;			//replace index with left iterate again
		}
	}

	/**
	 *checks to see if a child is larger than it's parent, if it is swap them
	 * @param index
	 */
	private void ascend(int index){
		while(index > 1 && less(index/2,index)){
			ex(index,index/2);
			index = index/2;
		}
	}

	/**
	 * checks if the priority of first index is less than second's
	 * @param first	location of first index
	 * @param second	location of second index
	 * @return
	 */
	private boolean less(int first, int second){
		if(list[first].p < list[second].p){
			return true;
		}return false;
	}

	/**
	 *checks if the queue is empty
	 * @return	if queue is empty return true, else false
	 */
	private boolean isEmpty(){
		return size ==0;
	}
	/**
	 *add an item v with priority p into the priority queue
	 * no checking whether a duplicate value is already in queue
	 * @param v	data to be added
	 * @param p	priority of data
	 * O(N): N is the number of items in priority queue
	 */
	public void insert(T v, int p){
		if(size == list.length-1){
			Helper<T> [] newArray = new Helper[list.length*2];
			for(int i =0; i<list.length;i++){
				newArray[i] = list[i];
			}
			list = newArray;
		}
		Helper<T> newObject = new Helper<T>(v,p);
		size++;
		list[size]= newObject;
		if(list[size].p == list[1].p){
			if(list[1].element.compareTo(v)<0){
				ex(1,size);
			}
		}ascend(size);


	}

	/**
	 * method swaps Helper nodes of first index and second index in list
	 * @param first	location of first Helper
	 * @param second location of second Helper
	 */
	private void ex(int first, int second){
		Helper<T> temp = list[first];
		list[first] = list[second];
		list[second] = temp;
	}
	/** perform a priority update for items in the priority queue based on the following rules
	*  - If the item is the same as v: set the priority as p
	*  - If the item is different from v, compare their priorities
	*      - For any item x with a priority <= v's priority, decrement x's priority by 1
	*      - Otherwise, do not change x's priority
	*
	* Hint: perform necessary adjustment to ensure a valid priority queue after updating
	*@param v T data to be stored
	 @param p priority of v
	* O(N): N is the number of items in priority queue
	 */
	public void updatePriority(T v, int p){
		for(int i =0; i<size+1;i++){
			if(list[i] == null) continue;
			if(list[i].element.compareTo(v)!= 0){
				if(list[i].p <= p){
					list[i].p--;
					continue;
				}
				continue;
			}
			list[i].p = p;
			ascend(size);
		}
	}

	/**
	 * check whether there is a value v associated with priority p in the priority queue
	 * @param v	data stored
	 * @param p	priority of data
	 * @return true if present, false otherwise
	 *O(N): N is the number of items in priority queue
	 */
	public boolean contains(T v, int p){
		for(int i =1; i< size + 1;i++){
			if(list[i].element.compareTo(v)==0 && list[i].p == p){
				return true;
			}
		}
		return false;
	}
	public void print() {
		for (int i = 1; i < size + 1; i++) {
			System.out.println(list[i].element + " | " + list[i].p);
		}
	}
	/**
	 * inner data structure keeps track of priority and its data
	 * @param <T>
	 */

	private class Helper<T extends Comparable>{
		T element;
		int p;

		/**
		 * constructor that initialize element and priority
		 * @param element
		 */
		public Helper(T element, int priority){
			this.element = element;
			this.p = priority;
		}



	}
	// make sure you implement all methods in PriorityQueue interface

	//------------------------------------
	// example test code... edit this as much as you want!
	// note: you might want to add a method like printPQ() for debugging purpose

	public static void main(String[] args){
		MyPriorityQueue<String> pq = new MyPriorityQueue<String>();
		
		if(pq.size()==0 && pq.remove()==null && !pq.contains("a", 4)) 	{
			System.out.println("Yay 1");
		}
		
		pq.insert("a",4);
		pq.insert("b",10);
		pq.insert("h",2);
		
		if(pq.size()==3 && (pq.peek()).equals("b") && pq.contains("a", 4) && pq.contains("h", 2)
			&& pq.contains("b",10)) {
			System.out.println("Yay 2");
		}
		//pq.print();

		if((pq.remove()).equals("b") && !pq.contains("b",10) & pq.size()==2 
			&& (pq.peek().equals("a")) ) {
			System.out.println("Yay 3");
		}

		pq.insert("d",4);
		if ((pq.peek()).equals("d")) {System.out.println("Yay 4");}
				
		pq.insert("b",10);
		pq.insert("f",3);
		pq.updatePriority("a",3);
		if (pq.size() == 5 && pq.contains("a",3) && pq.contains("b",10) && pq.contains("d",4) 
			&& pq.contains("f",2) && pq.contains("h",1)) {
			System.out.println("Yay 5");
		}


	}


}




