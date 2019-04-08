
/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Card.java
 *
 * Description: Abstract Card class from which a card class specific to
 * any game can be constructed. This file SHOULD NOT be modified.
 * 
 ***************************************************************************/

public abstract class Card {
	//create a type Rank with constants of values of a card.
	enum Rank{
		ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING;
	}
   //create a type Suit of the 4 suits a deck of card has
	enum Suit{
		HEARTS, CLUBS, DIAMONDS, SPADES;
	}
	//fields used to store a card's rank and suit
	protected Rank rank;
	protected Suit suit;
	
   //constructor used to set field values.
	public Card(Rank r, Suit s){
		rank = r;
		suit = s;
	}
   //method used to return a card's rank value ACE,TWO,THREE,FOUR etc.
	public Rank getRank(){
		return rank;
	}
  
	//method used o return a card's sui value HEART, CLUBS, DIAMONDS, SPADES
	public Suit getSuit(){
		return suit;
	}
	
   //an abstract method that needs to be implemented by a subclass.
   //this method is used to compare if a card's value is equal to another card passed as the parameter
	abstract boolean equals(Card c);
   
   //an abstract method that needs to be implemented, it returns the card's Points based on rank. ACE =1, TWO = 2,etc..TEN and all face cards return 10. 
   abstract int getPoints();
	
   
   //an abstract method that needs to be implemented, it returns the rank and suit of the card.	
	@Override
	public abstract String toString();

}
