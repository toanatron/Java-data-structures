// your implementation of Sequence interface
// @Author Toan Tran
// @Version 5/1/2018

public class MySequence<T extends Comparable<T>> implements Sequence<T>{

	// you decide the internal design of your class:
	//  - it must implement the provided Sequence interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in Sequence interface
	private int size =0;
	private static final int ALLOWED_IMBALANCE = 1;

	/**
	 * inner data structure that helps keep track of data
	 * @param <AnyType>
	 */
	private static class AvlNode<AnyType>
	{
		// Constructors
		AvlNode( AnyType theElement )
		{
			this( theElement, null, null );
		}

		AvlNode(AnyType theElement, AvlNode<AnyType> lt, AvlNode<AnyType> rt )
		{
			element  = theElement;
			left     = lt;
			right    = rt;
			height   = 0;
		}

		AnyType           element;      // The data in the node
		AvlNode<AnyType> left;         // Left child
		AvlNode<AnyType> right;        // Right child
		int               height;       // Height
	}

	private AvlNode<T> root;

	/**
	 * empty constructor
	 */
	public MySequence(){

	}

	/**
	 * add a new value v into the collection
	 O(logN) given N as the number of values stored in sequence
	 * @param v	data to be stored
	 * @return	return true if added successfully return false if value is already present (no duplicate added)
	 */
	@Override
	public boolean insert (T v){
		if(this.contains(v)) {
			return false;
		}
			AvlNode<T> insert = insert(v,root);
			this.root = insert;
			this.size++;
			return true;
	}

	/**
	 * Class recursively traverse tree insert and balance
	 * @param v
	 * @param root
	 * @return an updated tree
	 */
	private AvlNode<T> insert(T v, AvlNode<T> root){
		if( root == null )
			return new AvlNode<>( v, null, null );

		int compareResult = v.compareTo( root.element );

		if( compareResult < 0 )
			root.left = insert( v, root.left );
		else if( compareResult > 0 )
			root.right = insert( v, root.right );
		else
			return root;  // Duplicate; do nothing
		return balance(root);
	}

	/**
	 * balance the tree to make it an AVL tree
	 * @param t root
	 * @return	updated tree
	 */
	private AvlNode<T> balance( AvlNode<T> t )
	{
		if( t == null )
			return t;

		if( height( t.left ) - height( t.right ) > ALLOWED_IMBALANCE )
			if( height( t.left.left ) >= height( t.left.right ) )
				t = rotateWithLeftChild( t );
			else
				t = doubleWithLeftChild( t );
		else
		if( height( t.right ) - height( t.left ) > ALLOWED_IMBALANCE )
			if( height( t.right.right ) >= height( t.right.left ) )
				t = rotateWithRightChild( t );
			else
				t = doubleWithRightChild( t );

		t.height = Math.max( height( t.left ), height( t.right ) ) + 1;
		return t;
	}

	/**
	 *  traverse through tree fnding height of tree
	 * @param t root
	 * @return the heightof the tree
	 */
	private int height( AvlNode<T> t )
	{
		return t == null ? -1 : t.height;
	}

	/**
	 * Rotate binary tree node with left child.
	 * For AVL trees, this is a single rotation for case 1.
	 * Update heights, then return new root.
	 */
	private AvlNode<T> rotateWithLeftChild( AvlNode<T> k2 )
	{
		AvlNode<T> k1 = k2.left;
		k2.left = k1.right;
		k1.right = k2;
		k2.height = Math.max( height( k2.left ), height( k2.right ) ) + 1;
		k1.height = Math.max( height( k1.left ), k2.height ) + 1;
		return k1;
	}
	/**
	 * Rotate binary tree node with right child.
	 * For AVL trees, this is a single rotation for case 4.
	 * Update heights, then return new root.
	 */
	private AvlNode<T> rotateWithRightChild( AvlNode<T> k1 )
	{
		AvlNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
		k2.height = Math.max( height( k2.right ), k1.height ) + 1;
		return k2;
	}

	/**
	 * Double rotate binary tree node: first left child
	 * with its right child; then node k3 with new left child.
	 * For AVL trees, this is a double rotation for case 2.
	 * Update heights, then return new root.
	 */
	private AvlNode<T> doubleWithLeftChild( AvlNode<T> k3 )
	{
		k3.left = rotateWithRightChild( k3.left );
		return rotateWithLeftChild( k3 );
	}

	/**
	 * Double rotate binary tree node: first right child
	 * with its left child; then node k1 with new right child.
	 * For AVL trees, this is a double rotation for case 3.
	 * Update heights, then return new root.
	 */
	private AvlNode<T> doubleWithRightChild( AvlNode<T> k1 )
	{
		k1.right = rotateWithLeftChild( k1.right );
		return rotateWithRightChild( k1 );
	}

	/*
	* delete value v from the collection
	* @param root
	* @return true if removed successfully
	* @return false if value is not present
	*
	* O(logN) given N as the number of values stored in sequence
	*/
	@Override
	public boolean remove (T v){
		if(!contains(v)) {
			return false;
		}
		AvlNode removed = remove(v,root);
		this.root = removed;
		this.size--;
		return true;
	}

	/**
	 * Internal method to remove from a subtree.
	 * @param x the item to remove.
	 * @param t the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private AvlNode<T> remove( T x, AvlNode<T> t )
	{
		if( t == null )
			return t;   // Item not found; do nothing

		int compareResult = x.compareTo( t.element );

		if( compareResult < 0 )
			t.left = remove( x, t.left );
		else if( compareResult > 0 )
			t.right = remove( x, t.right );
		else if( t.left != null && t.right != null ) // Two children
		{
			t.element = findMin( t.right ).element;
			t.right = remove( t.element, t.right );
		}
		else
			t = ( t.left != null ) ? t.left : t.right;
		return balance( t );
	}

	/**
	 *Find the minimum value inside tree
	 * @param t
	 * @return
	 */
	private AvlNode<T> findMin( AvlNode<T> t )
	{
		if( t == null )
			return t;

		while( t.left != null )
			t = t.left;
		return t;
	}


	/**
	 * find whether value v is present in collection
	 *
	 * @param v data
	 * @return true if value is present,false otherwise
	 * O(logN) given N as the number of values stored in sequence
	 */
	@Override
	public boolean contains (T v){
		return contains(v,root);
	}

	/**
	 *  helper method for contains
	 * @param v	data to find
	 * @param root	root of tree
	 * @return	if root was found or not
	 */
	private boolean contains(T v, AvlNode<T> root){
		while( root != null )
		{
			int compareResult = v.compareTo( root.element );

			if( compareResult < 0 )
				root = root.left;
			else if( compareResult > 0 )
				root = root.right;
			else
				return true;    // Match
		}

		return false;   // No match
	}




	/**
	 * count how many values in collection are greater than or equal to v
	 *
	 * @param v
	 * @return the integer count
	 * O(logN) given N as the number of values stored in sequence
	 */
	@Override
	public int countNoSmallerThan (T v){
		return countNoSmallerThan(v,root);
	}

	/**
	 * countNoSmallerThan helper method
	 * @param v	data to find
	 * @param t root if tre
	 * @return	the number of nodes with data v
	 */
	private int countNoSmallerThan(T v, AvlNode<T> t){
		if(t == null){
			return 0;
		}
		int countLeft = countNoSmallerThan(v,t.left);
		int countRight = countNoSmallerThan(v,t.right);
		if(v.compareTo(t.element)<=0){
			return 1 + countLeft + countRight;
		}else{
			return 0 + countLeft + countRight;
		}

	}



	/**
	 * count how many values present in collection
	 * @returnthe integer count
	 * O(1)
	 */
	@Override
	public int size(){
		return size;
	}


	/**
	 * method returns a string representation of all values in collection
	 * @return a string representation of all values in collection, in ascending order
	 * O(N) given N as the number of values stored in sequence
	 */
	@Override
	public String toStringAscendingOrder(){
		return AscendOrder(root);
	}
	private String AscendOrder(AvlNode<T>t){
		if(t== null){
			return "";
		}
		return AscendOrder(t.left) + t.element+ " "+ AscendOrder(t.right);
	}
	// make sure you implement all methods in Sequence interface


	//------------------------------------
	// example test code... edit this as much as you want!
	public static void main(String[] args){
		MySequence<Integer> seq = new MySequence<Integer>();
		
		if(seq.size()==0 && !seq.contains(11) && seq.countNoSmallerThan(10) == 0 
			&& seq.toStringAscendingOrder().equals("")) 	{
			System.out.println("Yay 1");
		}
		
		seq.insert(11);
		seq.insert(5);
      if(seq.insert(200) && seq.size()==3 && seq.contains(11)) 	{
			System.out.println("Yay 2");
         
		}
		if(seq.insert(200) && seq.size()==3 && seq.contains(11) 
			&& seq.countNoSmallerThan(10) == 2 && !seq.remove(20)) 	{
			System.out.println("Yay 2");
		}
		
		seq.insert(112);
		seq.insert(50);
		seq.insert(20);
		
		if(seq.remove(20) && !seq.contains(20) && !seq.insert(200)
			&& seq.countNoSmallerThan(50) == 3 
			&& seq.toStringAscendingOrder().equals("5 11 50 112 200 ")) 	{
			System.out.println("Yay 3");
		}
		seq.remove(112);
		System.out.println(seq.contains(112));
		seq.remove(50);

	}
}


