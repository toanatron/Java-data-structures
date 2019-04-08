/**
 * This class is used to build a first child next sibling tree
 *@author Toan Tran
 *@version April 08, 2018
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ExpressionFCNSTree {

	//==========================
	// DO NOT CHANGE


	FCNSTreeNode root;

	public ExpressionFCNSTree() {
		root = null;
	}

	public ExpressionFCNSTree(FCNSTreeNode root) {
		this.root = root;
	}

	public boolean equals(ExpressionFCNSTree another) {
		return root.equals(another.root);

	}

	// END OF DO NOT CHANGE
	//==========================

	/**
	 *Method returns the size of the tree
	 * @return calls a helper method to return the size of the tree
	 * O(N)
	 */
	public int size() {
		// return how many nodes are there in the tree
		//System.out.println(size(this.root));

		return size(this.root);

	}

	/**
	 * a recursive method that returns the size of the tree
	 * @param t the root of a FCNST tree node
	 * @return return the size of the tree
	 * O(N)
	 */
	private static int size(FCNSTreeNode t) {
		if (t == null) {
			return 0;
		}
		int fC = size(t.firstChild);
		int nS = size(t.nextSibling);
		return 1 + fC + nS;
	}

	/**
	 *this method returns the height of the tree
	 * @return calls a helper method
	 * O(N)
	 */
	public int height() {
		// return the height of the tree
		// return -1 for null tree
		if (size() == 0)
			return -1;
		//System.out.print(height(this.root));
		return height(this.root);

	}

	/**
	 *recursive method that traverse through a tree and returns height
	 * @param t FCNS tree root
	 * @return returns the height of the tree
	 * O(N)
	 */
	private  int height(FCNSTreeNode t) {
		if (t == null) {
			return -1;
		}
		int hFC = height(t.firstChild);
		int hNS = height(t.nextSibling);
		int bigger = Math.max(hFC, hNS);
		return 1 + bigger;
	}

	/**
	 *this method returns the amount of nodes wih symbol s
	 * @param s symbol to look for
	 * @return returns how many node with symbol s inside tree
	 * O(N)
	 */
	public int countNode(String s) {
		// count how many nodes in the tree are with the specified symbol s
		return countNode(this.root, s);
	}
	/**
	 *Overloaded method used to implement a stack to search for nodes with string s
	 * @param t FCNS root node
	 * @param s symbol to look for
	 * @return how many nodes with symbol s
	 * O(N)
	 */
	private int countNode(FCNSTreeNode t, String s) {
		//uses a stack implementation to traverse through the nodes
		Stack<FCNSTreeNode> iter = new Stack<FCNSTreeNode>();
		iter.push(t);
		int counter = 0;
		while (!iter.isEmpty()){
			FCNSTreeNode check = iter.pop();		//check each node by popping the stack
			if (check == null) {
				continue;
			}
			if (check.element.equals(s)) {
				counter++;
			}									  //adds objects to stack to check
			iter.push(check.firstChild);
			iter.push(check.nextSibling);
		}
		return counter;
	}

	/**
	 *method using stack to check how many non numbers there are inside tree
	 * @return returns how many non numbers there are inside tree
	 * O(N)
	 */
	public int countNan() {
		// count and return how many nodes are marked as not-a-number
		Stack<FCNSTreeNode> iter = new Stack<FCNSTreeNode>();
		iter.push(root);
		int counter = 0;
		while (!iter.isEmpty()) {
			FCNSTreeNode check = iter.pop();
			if (check == null) continue;
			if (check.nan) counter++;
			iter.push(check.firstChild);
			iter.push(check.nextSibling);
		}
		return counter;
	}

	/**
	 *Method returns the string of a tree in PreFix form
	 * @return
	 * O(N)
	 */
	public String toStringPreFix() {
		// return a string representation of pre-order tree traversal
		// there should be exactly one single space after each tree node
		// see main method below for examples
		return toStringPreFix(this.root);
	}

	/**
	 *	Helper method that returns the String of tree in PreFix form
	 * @param t FCNS tree root
	 * @return String of tree in prefix form
	 * O(N)
	 */
	private String toStringPreFix(FCNSTreeNode t) {
		Stack<FCNSTreeNode> iter = new Stack<FCNSTreeNode>();
		iter.push(t);
		String toReturn = "";
		while (!iter.isEmpty()) {
			FCNSTreeNode current = iter.pop();
			if (current == null) {
				continue;
			}
			toReturn += current.element + " ";
			iter.push(current.firstChild);
			iter.push(current.nextSibling);
		}
		return toReturn;
	}

	/**
	 *Method returns the String of tree in PostFix form
	 * @return String of tree's PostFix form
	 * O(N)
	 */
	public String toStringPostFix() {
		// return a string representation of post-order tree traversal
		// there should be exactly one single space after each tree node
		// see main method below for examples
		//using two stacks set one stack to be with new String reversed, then pop that stack and add to String
		Stack<FCNSTreeNode> iter = new Stack<FCNSTreeNode>();
		Stack<FCNSTreeNode> iter2 = new Stack<FCNSTreeNode>();
		iter.push(this.root);
		while (!iter.isEmpty()) {
			FCNSTreeNode current = iter.pop();
			if (current == null) {
				continue;
			}
			iter2.push(current);
			iter.push(current.firstChild);
			iter.push(current.nextSibling);
		}
		String toReturn = "";
		while (!iter2.isEmpty()) {
			FCNSTreeNode returning = iter2.pop();
			toReturn += returning.element + " ";
		}
		return toReturn;
	}

	/**
	 *Method returns the string of a tree level by level
	 * @return String of String level order form
	 * O(N)
	 */
	public String toStringLevelOrder() {
		// return a string representation of level-order (breadth-first) tree traversal
		// there should be exactly one single space after each tree node
		// see main method below for examples
		Stack<FCNSTreeNode> iter = new Stack<FCNSTreeNode>();
		Queue<FCNSTreeNode> iter2 = new Queue<FCNSTreeNode>();
		iter.push(root);
		FCNSTreeNode headRoot = root;
		if (headRoot != null) {
			iter2.enqueue(headRoot);
		}
		while (!iter.isEmpty()) {
			FCNSTreeNode current = iter.pop();
			if (current == null) {
				continue;
			}
			//adding popped stack object to queue, making sure to go in order of First child next sibling
			if (current.firstChild != null) {
				iter2.enqueue(current.firstChild);
			}
			if (current.nextSibling != null) {
				iter2.enqueue(current.nextSibling);
			}
			iter.push(current.firstChild);
			iter.push(current.nextSibling);
		}
		String toReturn = "";
		while (!iter2.isEmpty()) {
			FCNSTreeNode returning = iter2.dequeue();
			toReturn += returning.element + " ";
		}
		return toReturn;
	}

	/**
	 *This method builds a new FCNS from a file
	 * @param fileName	Name of the file
	 * @throws FileNotFoundException	Throws an expception if the file is not found
	 * O(N)
	 */
	public void buildTree(String fileName) throws FileNotFoundException {
		// This method need to open file specified by the string fileName,
		// read in a one-line numeric expression in prefix notation, and
		// construct a first-child-next-sibling expression tree base on the input

		// set root to be the newly constructed tree root
		// if there is any exception, root should be null
		try {
			File file = new File(fileName);
			if (file == null) {

				this.root = null;
			}
			Scanner scan = new Scanner(file);
			String expression = scan.nextLine();
			String[] exArray = expression.split("\\s+");
			boolean rootCreated = false;

			//keeps track of which strings have been used
			Queue<String> queueString = new Queue<String>();
			for (int i = 0; i < exArray.length; i++) {
				queueString.enqueue(exArray[i]);

			}
			this.root = buildTreeHelper(queueString);
		} catch (FileNotFoundException ex) {				//instead of throwing an exception, we set the root to null
			System.out.print("root set to null");
			this.root = null;
		}
	}

	/**
	 *	Helper method that recursively add to tree
	 * @param strings	String from file
	 * @return	Returns the root node
	 * O(N)
	 */
	private FCNSTreeNode buildTreeHelper(Queue<String> strings) {
		if (strings.isEmpty()) {
			return null;
		}
		FCNSTreeNode node = new FCNSTreeNode(strings.dequeue());
		//if it's a symbol make sure the first child and next sibling is set
		if (isSymbol(node.element) || isNegate(node.element)) {
			if(isNegate(node.element)) {
				node.firstChild = buildTreeHelper(strings);
			}else{
				node.firstChild = buildTreeHelper(strings);
				node.firstChild.nextSibling = buildTreeHelper(strings);
			}
		}
		return node;
	}

	/**
	 *this helper method checks if a string is an operator
	 * @param s	String to be analyzed
	 * @return returns if string is a symbol or not
	 * O(1)
	 */
	private  boolean isSymbol(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("/") || s.equals("*") || s.equals("%")) {
			return true;
		}
		return false;
	}

	/**
	 *this helper method checks if a char is a operator
	 * @param c	charto be checked
	 * @return	return if char is a symbol
	 * O(1)
	 */
	private boolean isSymbolChar(char c) {
		if (c == '+' || c == '-' || c == '/' || c == '*' || c == '%') {
			return true;
		}
		return false;
	}

	/**
	 *	Method checks if symbol is a ~
	 * @param s	String to check
	 * @return	returns if symbol is a ~
	 * O(1)
	 */
	private  boolean isNegate(String s) {
		if (s.equals("~")) return true;
		return false;
	}

	/**
	 * This method builds a binary tree of given FCNS tree
	 * @return return the binary tree
	 * O(N)
	 */
	public ExpressionBinaryTree buildBinaryTree() {
		// construct the binary tree representation of this expression
		// and return the tree root
		BinaryTreeNode binNode = buildBinaryTreeHelper(root);
		return new ExpressionBinaryTree(binNode);
	}

	/**
	 *Recursive method that adds left and right of binary tree
	 * @param t   FCNS root
	 * @return returns binary root
	 * O(N)
	 */
	private BinaryTreeNode buildBinaryTreeHelper(FCNSTreeNode t) {
		if (t == null) {
			return null;
		}
		BinaryTreeNode Node = new BinaryTreeNode(t.element);
		//checks if tree is symbol, add left, and right according to children
		if (isSymbol(Node.element)) {
			Node.left = buildBinaryTreeHelper(t.firstChild);
			Node.right = buildBinaryTreeHelper(t.firstChild.nextSibling);
		} else if (isNegate(t.element)) {
			Node.left = new BinaryTreeNode(t.firstChild.element);
		}
		return Node;
	}

	/**
	 *This method is used to add parenthesis to potray the tree expression in a friendly infix expression
	 * @return calls a helper method to perform the task
	 * O(N)
	 */
	public String toStringPrettyInFix() {
		// return a string representation as the normal human-friendly infix expression
		// it is like we are simulating in-order tree traversal of the binary tree
		// Format of the infix string:
		//		- there should be no space, but
		//		- parenthesizes must be inserted wrapping around the sub-expression of each operator
		// see main method below for examples
		String inFix = toStringPrettyInFixHelper(root);
		return inFix;
	}

	/**
	 *Returns the String of a "Pretty" inFix
	 * @param t root tree nod
	 * @return	returns String with parenthesis around math operations
	 * O(N)
	 */
	private  String toStringPrettyInFixHelper(FCNSTreeNode t) {
		if (t == null) {
			return "";
		}
		String toReturn = "";
		if (isSymbol(t.element)) {
			toReturn += "(" + toStringPrettyInFixHelper(t.firstChild) + t.element + toStringPrettyInFixHelper(t.firstChild.nextSibling) + ")";
		} else if(isNegate(t.element)) {
			t.firstChild.nextSibling = null;
			toReturn += "-" + toStringPrettyInFixHelper(t.firstChild);

		}else {
			toReturn += t.element;
		}
		return toReturn;
	}

	/**
	 *	A method that evaluates an expression recursively
	 * @return	returns the value of the expression
	 * O(N)
	 */
	public Integer evaluate() {
		// This method evaluates the expression and marks every tree node with:
		//    - operand node: node.value should be the integer value of the operand
		//    - operator node: node.value should be the integer value associated with
		//                     the sub-expression rooted at the node
		// Return: the integer value of the root node

		// return null for null tree
		// if there is a division by zero: keep node.value to be null and set node.nan to be true.
		// not-a-number should be propagated: for an operator, if any of its operand is not-a-number,
		//			then the node of this operator should also be marked as not-a-number

		return evaluateHelper(this.root);
	}

	/**
	 * this method recursively check each node for an operator calculate it then operation and then set it's node value
	 * @param t FCNS root
	 * @return	returns the root's value
	 * O(N)
	 */
	private  Integer evaluateHelper(FCNSTreeNode t) {
		if (t.nan == true || t == null)
			return null ;
		switch(t.element){
			case "+": t.value = evaluateHelper(t.firstChild)+evaluateHelper(t.firstChild.nextSibling);
				return t.value;
			case "-": t.value = evaluateHelper(t.firstChild)-evaluateHelper(t.firstChild.nextSibling);
				return t.value;
			case "/":
				//if diving by 0, catch the exception, return the null value and set nan to be true
				try {
					t.value = evaluateHelper(t.firstChild) / evaluateHelper(t.firstChild.nextSibling);
					return t.value;
				}catch(ArithmeticException e){
					t.nan= true;
					return t.value;
				}
			case "*": t.value = evaluateHelper(t.firstChild)*evaluateHelper(t.firstChild.nextSibling);
				return t.value;
			case "~": t.value = -evaluateHelper(t.firstChild);
				return t.value;
			case "%": t.value = evaluateHelper(t.firstChild)%evaluateHelper(t.firstChild.nextSibling);
				return t.value;
			default:
				t.value = Integer.parseInt(t.element);
				return t.value;
		}
	}

	/**
	 *Calculates the expression without using recursion
	 * @return	the reslt of the expression
	 * O(N^2)
	 */
	public Integer evaluateNonRec() {
		// This method evaluates the expression leaving the tree unchanged
		// You must implement it as a non-recursive method
		// Return: the integer value of answer
		// return null for null tree
		// For this method only, assume there are no division-by-zero issues in the input
		String eval = toStringPrettyInFix();
		char [] elements = eval.toCharArray();
		Stack<Integer> numbers = new Stack<>();				//stack keeps track of numbers we have calculated
		Stack<Character> operators = new Stack<>();			//stack keeps track of what operators we have to do
		boolean negate = false;
		boolean multiply = false;
		for(int i = 0; i < elements.length;i++) {
			if (isNumber(elements[i])) {
				StringBuilder sb = new StringBuilder();
				while (i < elements.length && isNumber(elements[i])) {                //in case number is more than length one
					sb.append(elements[i]);
					i++;
				}
				//if number operator is a ~ sign push next number as a negative value
				if(negate == true){
					numbers.push(-Integer.parseInt(sb.toString()));
					negate = false;
				}else {
					numbers.push(Integer.parseInt(sb.toString()));
				}
			}
			if (elements[i] == '(') {
				operators.push(elements[i]);
			}
			if (elements[i] == ')') {                //if it is the end of a math expression, solve the expression
													//( indicates the end of an expression
				while (operators.peek() != '(') {
					numbers.push(calculate(numbers.pop(), operators.pop(), numbers.pop()));
				}
				operators.pop();	//pops the "("
				//if there is a negative expression
				if(multiply){
					int pop =numbers.pop();
					numbers.push(-1*pop);
					multiply=false;
				}
			}
			//the - sign indicates a negative number if previous character is not a number
			if (isSymbolChar(elements[i])) {
				if (elements[i] == '-' && checkLastIndex(i,elements)) {
					if(checkNextIndex(i,elements)) {
						negate = true;
						continue;
					}else{
						multiply = true;
						continue;
					}
				} else{
					//if order of operation is not true, do not calculate number and push to number stack
					while (!operators.isEmpty() && checkPrecedence(elements[i], operators.peek())) {
						numbers.push(calculate(numbers.pop(), operators.pop(), numbers.pop()));
					}
				operators.push(elements[i]);// push operator to operator stack
				}
			}

		}
		return numbers.pop();	//the last number in the number stack is the result
	}

	/**
	 *This helper method checks if the last index was a number
	 * @param i 		the index the - sign si currently at
	 * @param elements	the char array
	 * @return			if number is a negative number
	 * O(1)
	 */
	private boolean checkLastIndex(int i, char[] elements){
		try{
			if(isNumber(elements[i-1])){
				return false;
			}
			return true;
		}catch (ArrayIndexOutOfBoundsException ex){
			return true;
		}
	}

	/**
	 * checks if the next char is a number or not. This helps calculating if number is negative or expression is negative
	 * @param i			index of elements
	 * @param elements	char array
	 * @return	if next number is a number
	 * O(1)
	 */
	private boolean checkNextIndex(int i, char[] elements){
		try{
			if(isNumber(elements[i+1])){
				return true;
			}
			return false;
		}catch (ArrayIndexOutOfBoundsException ex){
			return false;
		}
	}

	/**
	 *	Checks if order of operation is true
	 * @param top	current char operator
	 * @param next	opperator after char
	 * @return	if current operator can occur before operator after it
	 * O(1)
	 */
	private boolean checkPrecedence(char top, char next){
		if(next == '(' || next == ')'){
			return false;
		}else if(top == '*' || top == '/' && next =='+' || next == '-')
			return false;
		return true;
	}

	/**
	 * if char is a number or not
	 * @param s	current char
	 * @return if char is a number
	 * O(1)
	 */
	private boolean isNumber(char s){
		if(s >= '0' && s<= '9') return true;
		return false;
	}

	/**
	 * this method calculates the number of an operation consisting of an operator and two numbers
	 * @param operator "+" "-" "*" "/" "%" are operators
	 * @param num1 the number number pushed last
	 * @param num2 the more significant number
	 * @return	a number ater calculations
	 * O(1)
	 */
	private int calculate(int num1,char operator, int num2){
		int toReturn =0;
		if(operator == '+'){
			return num2 + num1;
		}else if(operator == '/'){
			return num2/ num1;
		}else if(operator == '*'){
			return num2 * num1;
		}else if(operator == '-'){
			return num2 - num1;
		}else if(operator == '%') {
			return num2 % num1;
		} else {
			throw new RuntimeException();
		}
	}

	//----------------------------------------------------
	// example testing code... make sure you pass all ...
	// and edit this as much as you want!

	public static void main(String[] args) throws FileNotFoundException {

		//     *					*
		//   /  \				   /
		//  /    \                1
		//  1     +			==>    \
		//       / \                +
		//      2   3			   /
		//                        2
		//						   \
		//                          3
		//
		// prefix: * 1 + 2 3 (expr1.txt)

		FCNSTreeNode n1 = new FCNSTreeNode("3");
		FCNSTreeNode n2 = new FCNSTreeNode("2", null, n1);
		FCNSTreeNode n3 = new FCNSTreeNode("+", n2, null);
		FCNSTreeNode n4 = new FCNSTreeNode("1", null, n3);
		FCNSTreeNode n5 = new FCNSTreeNode("*", n4, null);
		ExpressionFCNSTree etree = new ExpressionFCNSTree(n5);

		if (etree.size() == 5 && etree.height() == 4 && etree.countNan() == 0 && etree.countNode("3") == 1) {
			System.out.println("Yay 1");
		}
		if (etree.toStringPreFix().equals("* 1 + 2 3 ") && etree.toStringPrettyInFix().equals("(1*(2+3))")) {
			System.out.println("Yay 2");}
		if (etree.toStringPostFix().equals("3 2 + 1 * ") && etree.toStringLevelOrder().equals("* 1 + 2 3 ")) {
			System.out.println("Yay 3");
			}
		if (etree.evaluateNonRec() == 5)
			System.out.println("Yay 4");

		if (etree.evaluate() == 5  && n4.value==1 && n3.value==5 && !n5.nan){
			System.out.println("Yay 5");
		}
			ExpressionFCNSTree etree2 = new ExpressionFCNSTree();
			etree2.buildTree("expressions/expr1.txt"); // construct expression tree from pre-fix notation
			if (etree2.equals(etree)) {
				System.out.println("Yay 6");
			}
			BinaryTreeNode bn1 = new BinaryTreeNode("1");
			BinaryTreeNode bn2 = new BinaryTreeNode("2");
			BinaryTreeNode bn3 = new BinaryTreeNode("3");
			BinaryTreeNode bn4 = new BinaryTreeNode("+", bn2, bn3);
			BinaryTreeNode bn5 = new BinaryTreeNode("*", bn1, bn4);
			ExpressionBinaryTree btree = new ExpressionBinaryTree(bn5);

			//construct binary tree from first-child-next-sibling tree
			ExpressionBinaryTree btree2 = etree.buildBinaryTree();
			if (btree2.equals(btree)) {
				System.out.println("Yay 7");
			}
			ExpressionFCNSTree etree3 = new ExpressionFCNSTree();
			etree3.buildTree("expressions/expr5.txt"); // an example of an expression with division-by-zero
			if (etree3.evaluate() == null && etree3.countNan() == 1) {
				System.out.println("Yay 8");

			}
		}
	}


//=======================================
// Tree node class implemented for you
// DO NOT CHANGE
class FCNSTreeNode{

	//members
	String element;	//symbol represented by the node, can be either operator or operand (integer)
	Boolean nan;	//boolean flag, set to be true if the expression is not-a-number
	Integer value;  //integer value associated with the node, used in evaluation
	FCNSTreeNode firstChild;
	FCNSTreeNode nextSibling;

	//constructors
	public FCNSTreeNode(String el){
		element = el;
		nan = false;
		value = null;
		firstChild = null;
		nextSibling = null;
	}

	//constructors
	public FCNSTreeNode(String el,FCNSTreeNode fc, FCNSTreeNode ns ){
		element = el;
		nan = false;
		value = null;
		firstChild = fc;
		nextSibling = ns;
	}


	// toString
	@Override
	public String toString(){
		return element.toString();
	}

	// compare two nodes
	// return true if: 1) they have the same element; and
	//                 2) their have matching firstChild (subtree) and nextSibling (subtree)
	public boolean equals(FCNSTreeNode another){
		if (another==null)
			return false;

		if (!this.element.equals(another.element))
			return false;

		if (this.firstChild==null){
			if (another.firstChild!=null)
				return false;
		}
		else if (!this.firstChild.equals(another.firstChild))
			return false;

		if (this.nextSibling==null){
			if (another.nextSibling!=null)
				return false;
		}
		else if (!this.nextSibling.equals(another.nextSibling))
			return false;

		return true;

	}

}