import java.util.Iterator;

// your header comment

class HashTable<T> {
	// this is the class that you need to write to implement a simple hash table 
	// with separate chaining
	
	// you decide which additional attributes to include in this class but they should all be private
	
	public HashTable(){
		//constructor
	}
	
	@SuppressWarnings("unchecked")
	private SimpleList<T>[] table = new SimpleList[11];
	
	public boolean add(T value) {
		// adds an item to the hash table
		// returns true if you successfully add value
		// returns false if the value can not be added
		// (i.e. the value already exists in the set)
		
		// note: if the average chain length is > 1.2
		// must rehash to a prime number that is larger
		// than twice the size before returning. 
		// You should keep expanding until rehash succeeds.

		// Overhead not considering rehash:
		// O(M) worst case, where M =  size returned by size()
		// O(1) or O(M/N) average case (where M/N is the load)
		// the average case can be amortized Big-O
		
		return true;		
	}
	
	public boolean remove(T value) {
		// removes a value from the hash table
		// returns true if you remove the item
		// returns false if the item could not be found
		
		// O(M) worst case, where M =  size returned by size()
		// O(1) or O(M/N) average case (where M/N is the load)
		return false;
	}
	
	public boolean contains(T value) {
		// returns true if the item can be found in the table
		
		// O(M) worst case, where M = size returned by size()
		// O(1) or O(M/N) average case (where M/N is the load)
		return false;
	}
	

	public T get(T value) {
		// return null if the item could not be found in hash table;
		// return the item FROM THE HASH TABLE if it was found.
		// NOTE: do NOT return the parameter value!!
		//       While "equal" they may not be the same.
		//       For example, When value is a PAIR<K,V>, 
		//       its "equals" methods returns true if just the keys are equal.

		// O(M) worst case, where M = size returned by size()
		// O(1) or O(M/N) average case (where M/N is the load)
		return null;
				
	}
	
	@SuppressWarnings("unchecked")
	public boolean rehash(int newCapacity) {
		// rehash to a larger table size (specified as the
		// parameter to this method)

		// O(N) when N>M; O(M) otherwise
		// where N is the table length and M = size returned by size()
		
		// - return true if table gets resized
		// - if the newCapacity will make the load to be more than 0.7, do not resize
		//   and return false 
		
		return true;
				
	}
	
	public int size() {
		// return the number of items in the table
		// O(1)
		return 0;
	}
	
	public double getLoad() {
		// return the load on the table
		// O(1)
		return 0.0;
	}
	
	public double getAvgChainLength(){
		// return the average length of non-empty chains in the hash table
		// O(1) 
		return 0.0;
	}
	
	public Object[] valuesToArray() {
		// take all the values in the table and put them
		// into an array (the array should be the same
		// size returned by the size() method -- no extra space!).
		// Note: it doesn't matter what order the items are
		// returned in, this is a set rather than a list.
		
		// O(N) when N>M; O(M) otherwise
		// where N is the table length and M = size returned by size()

		return null;
	}
	
	// inefficiently finds the next prime number >= x
	// this is written for you
	public int nextPrime(int x) {
		while(true) {
			boolean isPrime = true;
			for(int i = 2; i <= Math.sqrt(x); i++) {
				if(x % i == 0) {
					isPrime = false;
					break;
				}
			}
			if(isPrime) return x;
			x++;
		}
	}
	
	//------------------------------------
	// example test code... edit this as much as you want!
	public static void main(String[] args) {
		HashTable<String> names = new HashTable<>();
		
		if(names.add("Alice") && names.add("Bob") && !names.add("Alice") 
			&& names.size() == 2 && names.getAvgChainLength() == 1) 	{
			System.out.println("Yay 1");
		}
		
		if(names.remove("Bob") && names.contains("Alice") && !names.contains("Bob")
			&& names.valuesToArray()[0].equals("Alice")) {
			System.out.println("Yay 2");
		}
		
		boolean loadOk = true;
		if(names.getLoad() != 1/11.0 || !names.rehash(10) || names.getLoad() != 1/10.0 || names.rehash(1)) {
			loadOk = false;
		}
		
		boolean avgOk = true;
		HashTable<Integer> nums = new HashTable<>();
		for(int i = 1; i <= 70 && avgOk; i++) {
			nums.add(i);
			double avg = nums.getAvgChainLength();
			if(avg> 1.2 || (i < 12 && avg != 1) || (i >= 14 && i <= 23 && avg != 1) || 
				(i >= 28 && i <= 47 && avg != 1) || (i >= 57 && i <= 70 && avg!= 1)) {
				avgOk = false;
			}
			
		}
		if(loadOk && avgOk) {
			System.out.println("Yay 3");
			
		}
		
	}
}