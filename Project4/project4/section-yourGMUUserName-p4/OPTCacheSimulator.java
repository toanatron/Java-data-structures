import java.io.File;
import java.io.IOException;
import java.util.Scanner;

// class that simulates a cache with OPT replacement policy

public class OPTCacheSimulator{


	public boolean access(int address, int stackDist){
		// Only method you need to implement 
		// The method determines whether a reference is a cache hit or a cache miss
		// return true if cache hit
		// return false otherwise
		//
		// Hint: get the help from symbolTable
		// Note: also need to perform maintenance tasks before return, including:
		// 	1. update symbolTable 
		//  2. update cache
		//
		// symbolTable:
		//  - must only contain addresses that are currently in cache
		//
		// cache:
		//  - the number of addresses stored can not be more than cacheCapacity 
		//  - the address being accessed must always be kept in cache with the stackDist as its priority
		//  - remove the top item (with the highest priority) from the cache to make space if needed
		//  - need to update the priority of existing items (other than address) in cache:
		//      - For any item x with a priority <= stackDist, decrement x's priority by 1
		//      - Otherwise, do not change x's priority
		
			
	}

	// DO NOT MODIFY BELOW THIS LINE
    //-----------------------------------------------
    //-----------------------------------------------
	MySymbolTable<Integer, Integer> symbolTable;  
	// symbol table to remember whether the symbol is in cache or not

	MyPriorityQueue<Integer> cache;
	// storage that simulate an OPT cache
	
	private int cacheCapacity;  
	// how many items can be stored in cache
	
	private int numHits;
	// number of cache hits
	
	private int numMisses;
	// number of cache misses
	
	// constructor
	public OPTCacheSimulator(int cacheCapacity){
		this.cacheCapacity = cacheCapacity;
		numHits = numMisses = 0;
		symbolTable = new MySymbolTable<Integer, Integer>();
		cache = new	MyPriorityQueue<Integer>();	
	}

	//accessors
	public int getCapacity(){
		return cacheCapacity;
	}

	public int getNumHits(){
		return numHits;
	}

	public int getNumMisses(){
		return numMisses;
	}

	// method that accepts two file names:
	//		1. a file with a sequence of references; and 
	//		2. a file with the corresponding sequence of stack distances 
	//			(as collected by StackDistCollector)
	//  
	// the method simulates the OPT cache to process the references one by one
	//  and accumulates the number of cache hits / misses
	// 
	// This method has been implemented for you.
	// Do NOT modify this method.
	public void processSequence(String seqFileName, String distFileName) throws IOException{
		
		// reset everything to start over
		numHits = numMisses = 0;
		symbolTable = new MySymbolTable<Integer, Integer>();
		cache = new MyPriorityQueue<Integer>();	

		// open the file
		Scanner input = new Scanner(new File(seqFileName));
		Scanner input2 = new Scanner(new File(distFileName));
		
		// read in from the file and process accesses one by one
		while(input.hasNext()) {
			int address = Integer.parseInt(input.next());
			int stackDist;
			String nextDist = input2.next();
			if (!nextDist.equals("inf"))
				stackDist = Integer.parseInt(nextDist);
			else
				stackDist = MyPriorityQueue.INF;
				
			
			//System.out.print("["+address+", "+stackDist+"]");

			// check hit/miss and update cache as well as symbolTable
			if(access(address, stackDist)){
				numHits++;
				//System.out.println(" Hit!");
			}
			else{
				numMisses++;
				//System.out.println(" Miss!");
			}
				
			//cache.printPQ();
				
		}
		
	}

	
	//------------------------------------
	// example test code... edit this as much as you want!

	public static void main(String[] args) throws IOException{
			
		OPTCacheSimulator simulator = new OPTCacheSimulator(4);
		
		if(!simulator.access(16,1) &&  simulator.cache.contains(16,1) 
			&& simulator.symbolTable.hasSymbol(16)){
			System.out.println("Yay 1");
		}
		
		if(simulator.access(16,5) &&  simulator.cache.contains(16,5) &&  !simulator.cache.contains(16,1)
			&& simulator.cache.size()==1){
			System.out.println("Yay 2");
		}

		if(!simulator.access(20,5) &&  simulator.cache.contains(16,4) 
			&&  simulator.cache.peek().equals(new Integer(20)) && simulator.cache.size()==2){
			System.out.println("Yay 3");
		}
		
		if(!simulator.access(24,4) && !simulator.access(28,5) && !simulator.access(32,5) 
			&& simulator.cache.size()==4 && !simulator.symbolTable.hasSymbol(28)){
			System.out.println("Yay 4");
		}
		
		if(simulator.cache.contains(16,1) && simulator.cache.contains(24,2)
			&& simulator.cache.contains(20,3) && simulator.cache.contains(32,5) ){
			System.out.println("Yay 5");
		}
		
		simulator = new OPTCacheSimulator(3);
		simulator.processSequence("inputs/input1.txt", "inputs/input1_distance.txt");
		if (simulator.getNumHits() == 5 && simulator.getNumMisses() == 5){
			System.out.println("Yay 6");
		}
		
		
		// testing both steps together
		// use it when you are ready
		
		/*
		StackDistCollector collector = new StackDistCollector();
		collector.processSequence("inputs/input1_reverse.txt", "inputs/input1_my_distance.txt");
		simulator = new OPTCacheSimulator(3);
		simulator.processSequence("inputs/input1.txt", "inputs/input1_my_distance.txt");
		if (simulator.getNumHits() == 5 && simulator.getNumMisses() == 5){
			System.out.println("Yay 7");
		}
		*/
		
	}

	

}