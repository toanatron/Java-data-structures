/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Deck.java
 *
 * Description: Class representing a deck of cards with basic functionalities
 * of shuffling, adding, dealing, etc.
 *
 * TASK: Comment using JavaDoc and show the Big-O runtime of each method.
 * Code on this file should NOT be modified.
 * 
 ***************************************************************************/

public class Deck<T extends Card> {
	
	private Hand<T> setOfCards;   //deck creates a hand of cards
	
	public Deck(){
		setOfCards = new Hand<T>();
	}
	
   
	public boolean addCard(T c){//checks deck(setOfCards) and see if it contains a card, if not then add card if yes return false.
		if (hasCard(c))
			return false;
		setOfCards.addCard(c);
		return true;
      //O(N)
	}
	//method checks if setOfCards contains c
	public boolean hasCard(T c){
		return (setOfCards.indexOf(c)!=-1);
	   //O(N)
	}
   
   //method shuffles setOfCards
	public void shuffle() {
		for ( int i = setOfCards.numCards()-1; i >= 0; i-- ) {
			int rand = (int)(Math.random()*(i+1));
	        T temp = setOfCards.getCard(i);
            	setOfCards.setCard(i, setOfCards.getCard(rand));
            	setOfCards.setCard(rand, temp);
	    }
       //O(N)
	}
	//method takes a card from setOfCards and gives it to a player, decrement number of cards contained by setOfCards
	public T dealNextCard() {
		if(setOfCards.numCards()==0) return null; //if setOfCards is empty return null
		T temp = this.setOfCards.removeCard(setOfCards.numCards()-1);
		return temp;
      //O(1)
	}
   //method checks if setOfCards is empty.
	public boolean isEmpty() {
		return this.setOfCards.numCards() == 0;
      //O(1)
	}
   
   //method returns the number of cards in setOfCards
	public int cardCount(){
		return this.setOfCards.numCards();
      //O(1)
	}
	
   //method returns the current state of the deck, it's amount of cards
	public String toString(){
		StringBuilder sb = new StringBuilder("Deck ");
    		int numCards = cardCount();
    		if (numCards ==0){
    			sb.append("currently with no cards.");
    		}
    		else{
    			sb.append("with "+numCards+ " cards:\n");
    			sb.append(setOfCards.toString());
    		}
    		return sb.toString();
         //O(1)
  	}

}
