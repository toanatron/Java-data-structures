/**************************************************************************
 * @author <TOAN TRAN>
 * CS310 Spring 2018
 * Project 2
 * George Mason University
 * 
 * File Name: Position.java
 *
 * Description: Position represents one cell position ina 2D grid
 * 
 ***************************************************************************/
class Position {
 // this is the class that represent one cell position in a 2D grid

 // row and column
 private int row;
 private int col;

 public Position(int row, int col) {
  // constructor to initialize your attributes
  this.row = row;
  this.col = col;
 }

 // accessors of row and column
 public int getRow() {
  return row;
 }
 public int getCol() {
  return col;
 }

 public String toString() {
  // return string representation of a position
  // row R and col C must be represented as <R,C> with no spaces
  return "<" + getRow() + "," + getCol() + ">";
 }

 //methods determines if two positions are the same
 //@param another position
 //@return if position is the same as parameter or not
 @Override
 public boolean equals(Object obj) {
   // check whether two positions are the same
   // return true if they are of the same row and the same column
   // return false otherwise
   if (this == obj) {
    return true;
    //if object is an instance of Positon cast it
   } else if (obj instanceof Position) {
    Position test = (Position) obj;
    //using obj's row and col to see if they're equal
    if (test.row == getRow() && test.col == getCol()) {
     return true;
    }
   }
   return false;
  }
  //this method create's a custom hashcode
  //@return a new hashCode
 @Override
 public int hashCode() {
  // compute an integer hash code for this object
  // must follow hash contract and distribute well
  int result = 17;
  result = 31 * result + row;
  result = 31 * result + col;
  return result;
 }



 //----------------------------------------------------
 // example testing code... make sure you pass all ...
 // and edit this as much as you want!


 public static void main(String[] args) {
  Position p1 = new Position(3, 5);
  Position p2 = new Position(3, 6);
  Position p3 = new Position(2, 6);

  if (p1.getRow() == 3 && p1.getCol() == 5 && p1.toString().equals("<3,5>")) {
   System.out.println("Yay 1");
  }

  if (!p1.equals(p2) && !p2.equals(p3) && p1.equals(new Position(3, 5))) {
   System.out.println("Yay 2");
  }

  if (p1.hashCode() != p3.hashCode() && p1.hashCode() != (new Position(5, 3)).hashCode() &&
   p1.hashCode() == (new Position(3, 5)).hashCode()) {
   System.out.println("Yay 3");
  }


 }

}