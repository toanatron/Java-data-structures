/**************************************************************************
 * @author <TOAN TRAN>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Hand.java
 *
 * Description: Hand is to be used as an arraylist that holds a hand of cards.
 * 
 ***************************************************************************/

public class Hand<T extends Card>{

	// TO DO: add your implementation and JavaDoc
   // fields needed to implement an arraylist, an array and the number of cards.
   
	private T [] cards;
	private int numCards;
	@SuppressWarnings("unchecked")
	public Hand(){
		// constructor
		// initial size of cards must be no greater than 5
      // we set the number of cards to 0, because players need to draw cards to obtain more cards, and each player start with 0 cards.
      cards = (T[]) new Card[5];
      numCards = 0;
	}
	
   //method returns the number of cards a certain "hand" has
	public int numCards(){
      // return the number of cards
		// O(1)
      return numCards; 
	}	
	
	//method used to return a card at a certain index
	public T getCard(int index){
		// return card at index 
		// throw RuntimeException for invalid index
		// O(1)
      // look through the array and return the element of a certain index.
      if(index >=0 && index< numCards){
         return cards[index];
      }else
      throw new RuntimeException();
      
	}
   
   //method used to change an index's value to the card passed in the paramter.
	public void setCard(int index, T c){
		// change the card at index to be c	
		// throw RuntimeException for invalid index
		// O(1)
      
      if(index >=0 && index< numCards){
         cards[index] = c;
      }
      else throw new RuntimeException();

	}

   //method used to add a card to the end of an array, if the array is too small, create another array twice the size, and copy old array to new array.
	public void addCard(T c){
		// add card c at the end 
		// O(N)
      if (numCards == cards.length){
         T[] cards2 = (T[]) new Card[numCards *2];
         for(int i = 0; i< numCards; i++){   //after creating an array twice the size, copy the old array into the new one
            cards2[i] = cards[i];
         }
         cards = cards2;                     //changing the old array to the new one.
      }
      cards[numCards] = c;
      numCards++;                            //increment the total number of cards in a hand
	}
	
	//this method loops through a hand to find the index of a certain card and return that index. If the card is not inside the hand return -1.
	public int indexOf(T c){
       // find the index of a given card c, 
		 // returns -1 if not found	
		 // O(N)
       int index =-1; 
       for(int i = 0; i< numCards; i++){     //looping through the array
         if(cards[i].equals(c)){
            index = i;
            break;                  
         }
       }return index;
	}
		
	//this method removes a card based on the index.
   //returns the card that has been removed.
	public T removeCard(int index){
		// remove the card at index, 
		// throw RuntimeException for invalid index
		// O(N)
      if(index >=0 && index<numCards){
         T temp = cards[index];
         for(int i = index; i< numCards-1; i++){  //at the index, tell the array to skip the index value, thus removing the card at the index   
            cards[i]= cards[i+1];
         }
         numCards--;                            //decrement total number of cards in hand
         return temp;
      }else
      throw new RuntimeException();        

	}
   
	//method used to check if a card can be removed, if no such card is found, return false, if card can be removed, remove the card and return true.
	public boolean removeCard(T c){
		// remove card c, 
		// returns false if no such card
		// O(N)
      boolean cardRemoved = false;
      for(int i=0; i<numCards;i++){
         if(cards[i].equals(c)){
            int index = numCards - i -1;
            if(index > 0){
               System.arraycopy(cards,i +1,cards,i,index);} //I used the arraycopy method to keep the O(N) complexity. Calling the method removeCard and passing i would've worked, but it would've been O(N^2)
               cards[--numCards] = null;                    
            cardRemoved = true;
            break;
         }
      }return cardRemoved;
	}
	
	
  
	// --------------------------------------------------------
	// example test code... edit this as much as you want!
	// you will need a working CardSwitch class to run the given code


	// Not required, update for your testing purpose
	@Override
	public String toString(){
		// return string representation of hand
		// update if you want to include information for all cards in hand
		return "Hand with "+numCards+" cards";
		
		
  	}


	public static void main(String[] args) {
	
		CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
		CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
		
		
		Hand<CardSwitch> myHand = new Hand<CardSwitch>();
		myHand.addCard(card1);
		myHand.addCard(card2);
		
		if ((myHand.numCards() == 2) && (myHand.getCard(0).equals(card1))){
			System.out.println("Yay 1");
		}
		
		myHand.addCard(card3);
		
		if ( card2.equals(myHand.removeCard(1)) && myHand.getCard(1).equals(card3)){
			System.out.println("Yay 2");
		}

		if ((myHand.indexOf(card1)==0) && (myHand.indexOf(card2) == -1 )){
			System.out.println("Yay 3");
		}
      boolean remove = myHand.removeCard(card3);
      System.out.print(remove);
      int index =myHand.indexOf(card3);
      System.out.print(index);
	}
   


}