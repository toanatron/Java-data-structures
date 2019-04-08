import java.util.Iterator;

/**************************************************************************
 * @author <TOAN TRAN>
 * CS310 Spring 2018
 * Project 2
 * George Mason University
 * 
 * File Name: HashTable.java
 *
 * Description: used to implement a simple hash table
 * 
 ***************************************************************************/

class HashTable < T > {
 // this is the class that you need to write to implement a simple hash table 
 // with separate chaining

 // you decide which additional attributes to include in this class but they should all be private
 //private aciveChains keep count of how many different chains there are
 private int size;
 public double activeChains;

 //constructor used to initialize the size and active chains.
 public HashTable() {
  //constructor
  this.size = 0;
  this.activeChains = 0;
 }
 //autoRehash used to buffer for when rehashing, so rehashing does not repeat until process is finished.
 private boolean autoRehash = true;
 @SuppressWarnings("unchecked")
 private SimpleList < T > [] table = new SimpleList[11];
 //tablelen used to keep track of the length of the table
 private double tablelen = table.length;

 //method adds a value to the hash table, if cannot be added such as the value already exists return false
 //also rehash when average chain length is greater than 1.2, preventing collision
 //@param the value to be added
 //@return if value was added, return true, if not return false
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
  if (contains(value) == true) {
   return false;
  }
  //a hash function that determines where the value should be put into the table      
  int pos = Math.abs(value.hashCode()) % table.length;
  //if the element of the table is empty, create a linked list and add value to that list
  if (table[pos] == null) {
   SimpleList < T > list = new SimpleList < T > ();
   list.add(value);
   table[pos] = list;
   this.activeChains++;
  } else {
   table[pos].add(value);
  }
  this.size++;
  //if after adding value t table, chain length is > 1.2 rehash.
  if (getAvgChainLength() > 1.2 && autoRehash) {
   rehash(nextPrime(2 * table.length));
  }
  return true;
 }

 //method determines if a value is removed.
 //@param a value to be removed
 //@return if value was not removed return false, other wise true
 public boolean remove(T value) {
  // removes a value from the hash table
  // returns true if you remove the item
  // returns false if the item could not be found

  // O(M) worst case, where M =  size returned by size()
  // O(1) or O(M/N) average case (where M/N is the load)

  //hash function used to determine where value is, if value is not currently at the spot, go loop through table
  int pos = Math.abs(value.hashCode()) % table.length;
  for (int i = 0; i < size(); i++) {
   int nloc = (pos + i) % table.length;
   if (table[nloc] == null) {
    return false;
   }
   //checks if there is separate chaining, remove the object using simplelist remove method
   else if (table[nloc].contains(value)) {
    table[nloc].remove(value);
    this.size--;
    if (table[nloc].size() == 0) {
     table[nloc] = null;
     this.activeChains--;
    }
    //indicates an entry has been removed
    return true;
   }

  }
  return false;
 }

 //method detemrines if the table contains a value
 //@param the value to searchfor
 //@return if value was found return true, otherwise false
 public boolean contains(T value) {
  // returns true if the item can be found in the table

  // O(M) worst case, where M = size returned by size()
  // O(1) or O(M/N) average case (where M/N is the load)
  int pos = Math.abs(value.hashCode()) % table.length; //searching start positon
  for (int i = 0; i < size(); i++) {
   int nloc = (pos + i) % table.length; //next location with wrapping around

   if (table[nloc] == null) {
    return false;
   } else if (table[nloc].contains(value))
    return true;
  }
  return false;
 }

 //method used to find a certain value and grab that value
 //@param value to search for
 //@return the value that was found inside the table, not the value passed by parameter.
 //@retunr null if value is not inside table
 public Tget(T value) {
  // return null if the item could not be found in hash table;
  // return the item FROM THE HASH TABLE if it was found.
  // NOTE: do NOT return the parameter value!!
  //       While "equal" they may not be the same.
  //       For example, When value is a PAIR<K,V>, 
  //       its "equals" methods returns true if just the keys are equal.

  // O(M) worst case, where M = size returned by size()
  // O(1) or O(M/N) average case (where M/N is the load)
  int pos = Math.abs(value.hashCode()) % table.length; //searching start positon
  for (int i = 0; i < table.length; i++) {
   int nloc = (pos + i) % table.length;
   if (table[nloc] == null) {
    return null;
   } else if (table[nloc].contains(value) == true) {
    return table[nloc].get(value);
   }
  }
  return null;

 }

 //method rehashes to a new capacity
 //@param the new capacity to rehash to
 //@return if rehashing was successful return true, if load is more thna 0.7 return false and do not resize
 @SuppressWarnings("unchecked")
 public boolean rehash(int newCapacity) {
  // rehash to a larger table size (specified as the
  // parameter to this method)

  // O(N) when N>M; O(M) otherwise
  // where N is the table length and M = size returned by size()

  // - return true if table gets resized
  // - if the newCapacity will make the load to be more than 0.7, do not resize
  //   and return false 
  if ((size() / newCapacity) > 0.7) {
   return false;
  }
  this.autoRehash = false;
  Object[] oldTable = valuesToArray();
  SimpleList < T > [] newHash = new SimpleList[newCapacity];
  table = newHash;
  //resetting size and active chains
  this.size = 0;
  this.activeChains = 0;
  //copying values into the new table
  for (Object d: oldTable) {
   this.add((T) d);
  }
  //updating the table length
  this.tablelen = table.length;
  this.autoRehash = true;
  return true;
 }

 //method returns the size of the table
 //@retur size of table
 public int size() {
  // return the number of items in the table
  // O(1)
  return size;
 }

 //method determines the load of the table
 //@return load of table
 public double getLoad() {
  // return the load on the table
  // O(1)
  return size / tablelen;
 }

 //method determines average chain length
 //@return the average chain length
 public double getAvgChainLength() {
  // return the average length of non-empty chains in the hash table
  // O(1) 
  if (activeChains == 0) {
   return -1;
  }
  return size / activeChains;
 }

 //method creates an object[] of values of the hash table
 //@return an object[] of all the values of the hash table
 public Object[] valuesToArray() {
  // take all the values in the table and put them
  // into an array (the array should be the same
  // size returned by the size() method -- no extra space!).
  // Note: it doesn't matter what order the items are
  // returned in, this is a set rather than a list.

  // O(N) when N>M; O(M) otherwise
  // where N is the table length and M = size returned by size()

  //counter used to index the array
  int counter = 0;
  //creating the object[] of the size of the hash table
  Object[] objects = new Object[size()];
  //loops through the table, find all the linked list and add linked list values to object array
  for (int i = 0; i < table.length; i++) {
   if (table[i] == null) {
    continue;
   }
   SimpleList < T > list = table[i];
   Iterator iter = list.iterator();
   while (iter.hasNext()) {
    objects[counter] = iter.next();
    counter++;
   }
  }

  return objects;
 }

 // inefficiently finds the next prime number >= x
 // this is written for you
 public int nextPrime(int x) {
  while (true) {
   boolean isPrime = true;
   for (int i = 2; i <= Math.sqrt(x); i++) {
    if (x % i == 0) {
     isPrime = false;
     break;
    }
   }
   if (isPrime) return x;
   x++;
  }
 }

 /*
 public String toString() {
      String ls = System.lineSeparator();
      StringBuilder sb = new StringBuilder();

      for (int i = 0; i < table.length; i++) {
          sb.append(i).append(" -> ").append(table[i]).append(ls);
          
      }
      return sb.toString();
  }
 */



 //------------------------------------
 // example test code... edit this as much as you want!
 public static void main(String[] args) {
  HashTable < String > names = new HashTable < > ();

  if (names.add("Alice") && names.add("Bob") && !names.add("Alice") && names.size() == 2 && names.getAvgChainLength() == 1) {
   System.out.println("Yay 1");
  }


  if (names.remove("Bob") && names.contains("Alice") && !names.contains("Bob") && names.valuesToArray()[0].equals("Alice")) {
   System.out.println("Yay 2");
  }

  boolean loadOk = true;
  if (names.getLoad() != 1 / 11.0 || !names.rehash(10) || names.getLoad() != 1 / 10.0 || names.rehash(1)) {
   loadOk = false;
  }

  boolean avgOk = true;
  HashTable < Integer > nums = new HashTable < > ();
  for (int i = 1; i <= 70 && avgOk; i++) {
   nums.add(i);
   double avg = nums.getAvgChainLength();
   if (avg > 1.2 || (i < 12 && avg != 1) || (i >= 14 && i <= 23 && avg != 1) ||
    (i >= 28 && i <= 47 && avg != 1) || (i >= 57 && i <= 70 && avg != 1)) {
    avgOk = false;
   }

  }
  if (loadOk && avgOk) {
   System.out.println("Yay 3");

  }

 }
}