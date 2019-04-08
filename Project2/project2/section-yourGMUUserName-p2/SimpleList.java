import java.util.Iterator;

// your header comments

class SimpleList<T> implements Iterable<T>{
	
	// a linked list class 
	// you decide the internal attributes and node structure
	// but they should all be private

		
	public SimpleList(){
		//constructor
	}
	
	public void add(T value){
		// add a new node to the end of the linked list to hold value
		// O(1) 
			
	}
	
		
	public boolean remove(T value){
		// given a value, remove the first occurrence of that value
		// return true if value removed
		// return false if value not present
		// O(N) where N is the number of nodes returned by size()

		return false;
	}
	
	public int indexOf(T value){
		// return index of a value (0 to size-1)
		// if value not present, return -1
		// O(N)
		
		return 0;
	}

	public boolean contains(T value){
		// return true if value is present
		// false otherwise
		// O(N) where N is the number of nodes returned by size()
		return false;
	}

	public T get(T value){
		// search for the node with the specified value:
		// if not found, return null;
		// if found, RETURN VALUE STORED from linked list, NOT the incoming value
		// Note: two values might be considered "equal" but not identical
		//       example: Pair <k,v1> and <k,v2> "equal" for different v1 and v2 
		// O(N) where N is the number of nodes returned by size()
		return null;
	}
	
	
	public int size(){
		//return how many nodes are there
		//O(1)
		return 0;
	}


	public Iterator<T> iterator(){
		// return a basic iterator
		// .hasNext() and .next() required 
		// both should be of O(1)
		return null;

	}


	
	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!
	// also, consider add a toString() for your own debugging

	public static void main(String[] args){
		SimpleList<Integer> ilist = new SimpleList<Integer>();
		ilist.add(new Integer(11));
		ilist.add(new Integer(20));
		ilist.add(new Integer(5));
		
		if (ilist.size()==3 && ilist.contains(new Integer(5)) && 
			!ilist.contains(new Integer(2)) && ilist.indexOf(new Integer(20)) == 1){
			System.out.println("Yay 1");
		}

		if (!ilist.remove(new Integer(16)) && ilist.remove(new Integer(11)) &&
			!ilist.contains(new Integer(11)) && ilist.get(new Integer(20)).equals(new Integer(20))){
			System.out.println("Yay 2");			
		} 
		
		Iterator iter = ilist.iterator();
		if (iter.hasNext() && iter.next().equals(new Integer(20)) && iter.hasNext() &&
			iter.next().equals(new Integer(5)) && !iter.hasNext()){
			System.out.println("Yay 3");						
		}
		
	}

}