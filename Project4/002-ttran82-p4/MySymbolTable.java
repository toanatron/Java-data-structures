// your implementation of Sequence interface
// @Author Toan Tran
// @Version 5/1/2018

import java.util.HashMap;
import java.util.Map;
public class MySymbolTable<SymbolType, RecordType> implements SymbolTable<SymbolType, RecordType>{

	// you decide the internal design of your class:
	//  - it must implement the provided SymbolTable interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in SymbolTable interface

	private Map<SymbolType,RecordType> map = new HashMap<>();	//uses a map as inner data structure
	public MySymbolTable(){

	}

	/**
	 * count how many symbols present in table
	 * Average O(1) worst case O(N)
	 * @return the integer count
	 */
	@Override
	public int size(){
		return map.size();
	}

	/**
	 * check whether the symbol s is present in table*
	 * @param s symbol to check
	 * @return	return true if symbol is in table, false if not
	 */
	@Override
	public boolean hasSymbol(SymbolType s){
		return map.containsKey(s);
	}

	/**
	 * find and return the record we store in tabel for symbol s
	 * if symbol not present, return null
	 * Average O(1)
	 * @param s return the recordtype associated with s
	 * @return
	 */
	@Override
	public RecordType getRecord(SymbolType s){
		return map.get(s);
	}

	/**
	 * for symbol s, set the record fo that symbol s as r
	 * if s not present before, add s into table,
	 * if s alreay in table, replace the record for s to be r
	 * @param s
	 * @param r
	 */
	@Override
	public  void putRecord(SymbolType s, RecordType r){
		if(hasSymbol(s)){
			map.replace(s,r);
		}else{
			map.put(s,r);
		}
	}

	/**
	 *removes a RecordType with SymbolType s
	 * @param s SymbolType object
	 * @return	RecordType hat was removed
	 */
	@Override
	public RecordType removeSymbol(SymbolType s) {
		if(hasSymbol(s)){
			return map.remove(s);
		}return null;
	}
	// make sure you implement all methods in SymbolTable interface
	

	
	//------------------------------------
	// example test code... edit this as much as you want!
	public static void main(String[] args){
		MySymbolTable<String,Integer> table = new MySymbolTable<String,Integer>();
		
		if(table.size()==0 && !table.hasSymbol("a")) 	{
			System.out.println("Yay 1");
		}

		table.putRecord("a",new Integer(136));
		table.putRecord("b",new Integer(112));
		
      if(table.size()==2 && table.hasSymbol("a") && table.getRecord("b").equals(new Integer(112))) 	{
		   System.out.println("Yay 2");
      }

		table.putRecord("b",new Integer(211));
		Integer v = table.removeSymbol("a");
		if(table.size()==1 && v.equals(new Integer(136)) && !table.hasSymbol("a") 
			&& table.getRecord("b").equals(new Integer(211))) 	{
			System.out.println("Yay 3");
		}
	
	}


}


