/**************************************************************************
 * @author <TOAN TRAN>
 * CS310 Spring 2018
 * Project 2
 * George Mason University
 * 
 * File Name: TentTree.java
 *
 * Description: this is the class for a simplified Tent-Tree puzzle
 * 
 ***************************************************************************/

class TentTree {

 private int numRows, numCols; // size of the 2D board
 private HashMap < Position, String > grid; // the board stored in a hash table
 private String treeSymbol, tentSymbol; // the string representing tree/tent on board


 public TentTree(int numRows, int numCols, String tent, String tree) {
  // constructor that initializes attributes
  this.numRows = numRows;
  this.numCols = numCols;
  this.treeSymbol = tree;
  this.tentSymbol = tent;
  grid = new HashMap < > ();

 }

 public TentTree(int numRows, int numCols) {
  // overloaded constructor that by default uses "X" for tent 
  // and "O" (capital O not 0) for tree
  this.numRows = numRows;
  this.numCols = numCols;
  this.treeSymbol = "O";
  this.tentSymbol = "X";
  grid = new HashMap < > ();
 }

 // accessors that return tree/tent representation, O(1)
 public String getTentSymbol() {
  return tentSymbol;
 }
 public String getTreeSymbol() {
  return treeSymbol;
 }

 // accessors that return number of rows/columns, O(1)
 public int numRows() {
  return numRows;
 }
 public int numCols() {
  return numCols;
 }

 //method used to determine is a position is valid
 //@param a position for determination
 //@return if position is valid or not
 public boolean isValidPosition(Position pos) {
  // check whether the specified position is a valid position for the board
  // return true for valid positions and false for invalid ones
  // O(1)
  return pos.getRow() > -1 && pos.getRow() < numRows() && pos.getCol() > -1 && pos.getCol() < numCols();
 }

 //method used to determine is string s is a valid symbol
 //@param a string
 //@ return if string s matches tent symbol or tree symbol
 public boolean isValidSymbol(String s) {
  // check whether the specified string s is a valid tent or tree symbol of the game
  // O(1)
  return s.equals(getTentSymbol()) || s.equals(getTreeSymbol());
 }

 //method used to set a position with string s
 //@param a position and a string
 //@return if position was set with string s, if either position or string is invalid, return false
 public boolean set(Position pos, String s) {
  // set the cell at the specified position pos to be the specified string s 
  // do not change the board if invalid position: return false
  // do not change the board if invalid symbol: return false
  // do not change the board if the position is already occupied (not empty): return false
  // return true if board changed successfully
  // assuming HashMap overhead constant, O(1)
  if (isValidSymbol(s) && isValidPosition(pos) && !grid.contains(pos)) {
   this.grid.add(pos, s);
   return true;
  }
  return false;
 }

 //method used to return the string at a certain cell
 //@param the cell position
 //@return the string at that cell
 public String get(Position pos) {
  // return the cell at the specified position pos 
  // if invalid position: return null
  // if empty cell, return null
  // assuming HashMap overhead constant, O(1)
  if (isValidPosition(pos) && grid.contains(pos)) {
   return grid.getValue(pos);
  }
  return null;
 }

 //method used to add a tent at a cell
 //@param cell position
 //@return if tent was added or not
 public boolean addTent(Position pos) {
  // add another tent at the specified position pos
  // return false if a new tent cannot be added at pos 
  //     (i.e. attempt fails if pos is already occupied)
  // return true otherwise
  // assuming HashMap overhead constant, O(1)
  if (isValidPosition(pos) && !grid.contains(pos)) {
   this.grid.add(pos, getTentSymbol());
   return true;
  }
  return false;
 }

 //method used to remove a tent at a cell
 //@param cell position
 //@return if tent was removed from cell or not
 public boolean removeTent(Position pos) {
  // remove the tent from position pos
  // return false if the attempt of removal cannot be performed
  // return true otherwise
  // assuming HashMap overhead constant, O(1)

  //if cell is not valid or it does not have tent symbol return false
  if (!isValidPosition(pos) || !grid.has(pos, getTentSymbol())) {
   return false;
  }
  this.grid.remove(pos);
  return true;
 }

 //method determine if tree can be added at a certain cell
 //@param cell position
 //@return if tree was added at cell position
 public boolean addTree(Position pos) {
  // add another tree at the specified position pos
  // return false if a new tree cannot be added at pos
  //     (i.e. attempt fails if pos is already occupied)
  // return true otherwise
  // assuming HashMap overhead constant, O(1)

  //if cell is valid, and cell does not contain anything
  if (isValidPosition(pos) && !grid.contains(pos)) {
   this.grid.add(pos, getTreeSymbol());
   return true;
  }
  return false;
 }

 //method determines if a cell has tent
 //@param cell position
 //@return if cell has tent
 public boolean hasTent(Position pos) {
  // check whether there is a tent at position pos
  // return true if yes and false otherwise
  // return false for invalid positions
  // assuming HashMap overhead constant, O(1)
  if (isValidPosition(pos) && grid.has(pos, getTentSymbol())) {
   return true;
  }
  return false;
 }
 private boolean hasTree(Position pos) {
  // check whether there is a tent at position pos
  // return true if yes and false otherwise
  // return false for invalid positions
  // assuming HashMap overhead constant, O(1)
  if (isValidPosition(pos) && grid.has(pos, getTreeSymbol())) {
   return true;
  }
  return false;
 }

 //method check whether there are any symbols in the 4 way neighbors
 //@param cell position and symbol to check for
 //@return if any of the 4 neighboring cells contain the symbol s return true, otherwise false
 public boolean posHasNbr(Position pos, String s) {
  // check whether at least one of the 4-way neighbors 
  // of the specified position pos has a symbol as the incoming string s
  //
  // The four direct neighbors of a pos is shown as below: up/down/left/right
  //       ---   U   ---
  //        L   pos   R
  //       ---   D   ---
  // 
  // if at least one of the four cells has string s as the symbol, return true;
  // return false otherwise
  // assuming HashMap overhead constant, O(1)


  Position u = new Position(pos.getRow() - 1, pos.getCol());
  Position l = new Position(pos.getRow(), pos.getCol() - 1);
  Position r = new Position(pos.getRow(), pos.getCol() + 1);
  Position d = new Position(pos.getRow() + 1, pos.getCol());
  if (isValidPosition(u) && grid.has(u, s) || isValidPosition(l) && grid.has(l, s) || isValidPosition(r) && grid.has(r, s) || isValidPosition(d) && grid.has(d, s)) {
   return true;
  }
  return false;
 }

 //method check whether there are any symbols in the 8 way neighbors
 //@param cell position and symbol to check for
 //@return if any of the 8 neighboring cells contain the symbol s return true, otherwise false
 public boolean posTouching(Position pos, String s) {
  // check whether at least one of the 8 (horizontal/vertical/diagonal) neighbors 
  // of the specified position pos has a symbol as the incoming string s
  //
  // The eight horizontal/vertical/diagonal neighbors of a pos is shown as below: 
  // up left / up / up right / left / right / down left/ down/ down right
  //
  //        UL   U   UR
  //        L   pos   R
  //        DL   D   DR
  // 
  // if at least one of the eight cells has string s as the symbol, return true;
  // return false otherwise
  // assuming HashMap overhead constant, O(1)
  Position ul = new Position(pos.getRow() - 1, pos.getCol() - 1);
  Position ur = new Position(pos.getRow() - 1, pos.getCol() + 1);
  Position dl = new Position(pos.getRow() + 1, pos.getCol() - 1);
  Position dr = new Position(pos.getRow() + 1, pos.getCol() + 1);
  Position u = new Position(pos.getRow() - 1, pos.getCol());
  Position l = new Position(pos.getRow(), pos.getCol() - 1);
  Position r = new Position(pos.getRow(), pos.getCol() + 1);
  Position d = new Position(pos.getRow() + 1, pos.getCol());
  if (isValidPosition(u) && grid.has(u, s) || isValidPosition(l) && grid.has(l, s) || isValidPosition(r) && grid.has(r, s) || isValidPosition(d) && grid.has(d, s) || isValidPosition(ul) && grid.has(ul, s) || isValidPosition(ur) && grid.has(ur, s) || isValidPosition(dl) && grid.has(dl, s) || isValidPosition(dr) && grid.has(dr, s)) {
   return true;
  }
  return false;
 }

 /***
  * methods that return a string of the board representation
  * this has been implemented for you: DO NOT CHANGE
  * @param no parameters
  * @return a string
  */
 @Override
 public String toString() {
  // return a string of the board representation following these rules:
  // - if printed, it shows the board in R rows and C cols
  // - every cell should be represented as a 5-character long right-aligned string
  // - there should be one space between columns
  // - use "-" for empty cells
  // - every row ends with a new line "\n"


  StringBuilder sb = new StringBuilder("");
  for (int i = 0; i < numRows; i++) {
   for (int j = 0; j < numCols; j++) {
    Position pos = new Position(i, j);

    // use the hash table to get the symbol at Position(i,j)
    if (grid.contains(pos))
     sb.append(String.format("%5s ", this.get(pos)));
    else
     sb.append(String.format("%5s ", "-")); //empty cell
   }
   sb.append("\n");
  }
  return sb.toString();

 }



 /***
  * EXTRA CREDIT:
  * methods that checks the status of the grid and returns:
  * 0: if the board is empty or with invalid symbols
  * 1: if the board is a valid and finished puzzle
  * 2: if the board is valid but not finished
  *     - should only return 2 if the grid missing some tent but otherwise valid
  *       i.e. no tent touching other tents; no 'orphan' tents not attached to any tree, etc. 
  * 3: if the board is invalid
  *     - note: only one issue need to be reported when the grid is invalid with multiple issues
  * @param no parameters
  * @return an integer to indicate the status
  * 
  * assuming HashMap overhead constant, O(R*C) 
  * where R is the number of rows and C is the number of columns
  * Note: feel free to add additional output to help the user locate the issue
  */
 public int checkStatus() {
  /* boolean tentValid = true;
      boolean treeValid = true;
      if(numRows() == 0 && numCols() == 0 || !isValidSymbol(getTentSymbol()) || !isValidSymbol(getTreeSymbol())){
         return 0;
      }
		for(int i = 0; i< numRows(); i++){
         for(int j =0; i< numCols(); j++){
            Position pos = new Position(i,j);
            if(pos != null){
            if(hasTent(pos)){
               if(!posTouching(pos,getTreeSymbol()) || posTouching(pos,getTentSymbol())){
                  tentValid = false;
                }
            }else if(hasTree(pos)){ // if position is tree
               if(!posTouching(pos,getTentSymbol())){ // if position is not touching any tent, tree is not valid
                  treeValid = false;
               }
            }
          }
         }
       }//end outer loop
          if(!tentValid && treeValid){
            return 3;
          }else if(tentValid && !treeValid){
            return 1;
          }else if(tentValid && treeValid){
            return 2;
          } */
  return 2;
 }



 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!

 // Note: you will need working Position and SimpleMap classes to make this class working

 public static void main(String[] args) {

  TentTree g1 = new TentTree(4, 5, "Tent", "Tree");
  if (g1.numRows() == 4 && g1.numCols() == 5 && g1.getTentSymbol().equals("Tent") && g1.getTreeSymbol().equals("Tree")) {
   System.out.println("Yay 1");
  }

  TentTree g2 = new TentTree(3, 3);

  if (g2.set(new Position(1, 0), "O") && !g2.set(new Position(1, 0), "O") &&
   g2.addTree(new Position(1, 2)) && !g2.addTree(new Position(1, 5))) {
   System.out.println("Yay 2");
  }



  if (g2.addTent((new Position(0, 0))) && g2.addTent(new Position(0, 1)) &&
   !g2.addTent((new Position(1, 0))) && g2.get((new Position(0, 0))).equals("X")) {
   System.out.println("Yay 3");

  }

  if (g2.hasTent(new Position(0, 0)) && g2.posHasNbr((new Position(0, 0)), "O") &&
   g2.posTouching((new Position(1, 2)), "X") && !g2.posHasNbr((new Position(1, 2)), "X")) {
   System.out.println("Yay 4");

  }
  if (g2.removeTent(new Position(0, 1)) && !g2.removeTent(new Position(2, 1)) && g2.get(new Position(2, 2)) == null) {
   System.out.println("Yay 5");
  }

 }


}