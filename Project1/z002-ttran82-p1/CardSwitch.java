/**************************************************************************
 * @author <TOAN TRAN>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: CardSwitch.java
 *
 * Description: <This program extends the abstract class Card and tells the user if a card equals another card, also returns the value of that card.>
 * 
 ***************************************************************************/

public class CardSwitch extends Card{

	// TO DO: fill the code below and add JavaDoc
	//CardSwitch inherits all the fields created in Card, so to initialize them, we can use super() to call the Card constructor.
	
	public CardSwitch(Rank r, Suit s){
		// constructor to create card for the game Switch
      // calling the Card constructor to initialize a card. Since CardSwitch extends Card.
      super(r,s);
	}
	
	@Override
	public boolean equals(Card anotherCard){
		// checks if two cards equals and returns a boolean
      // checking the rank and the suit will allow us to know if two cards equal
      // using .equals() allows for comparingt two objects, in this case two cards.
		if(anotherCard.rank.equals(rank) && anotherCard.suit.equals(suit)){
         return true;
      }
      else return false;
	}
	
	@Override
    public int getPoints(){
	    // return points of the card
       // points start from ace to nine 1-9 and 10 to king is 10 points
       int points=0;
       // I used a switch case for enums, because rank has precisely 13 values
      switch (rank){
      case ACE:   points = 1; break;
      case TWO:   points = 2; break;
      case THREE: points = 3; break;
      case FOUR:  points = 4; break;
      case FIVE:  points = 5; break;
      case SIX:   points = 6; break;
      case SEVEN: points = 7; break;
      case EIGHT: points = 8; break;
      case NINE:  points = 9; break;
      case TEN:   points = 10;break;
      case JACK:  points = 10;break;
      case QUEEN: points = 10;break;
      case KING:  points = 10;break;
      default: points =  -1;
    }
    return points;
    }
	
	@Override
	public String toString(){
		// convert card to string consisting of as "(rank,suit)"
		// see examples below for format
      // using enum's method name() we can display the name of the object
      return "("+rank.name()+","+suit.name()+")";

	}
	
	//----------------------------------------------------
	//example test code... edit this as much as you want!
	public static void main(String[] args) {
		CardSwitch card = new CardSwitch(Card.Rank.TWO, Card.Suit.SPADES);
		
		if (card.getRank().equals(Card.Rank.TWO)){
			System.out.println("Yay 1");
		}
		
		if (card.toString().equals("(TWO,SPADES)")){
			System.out.println("Yay 2");
		}
      System.out.println(card.toString());
      
		if (card.getPoints()==2){
			System.out.println("Yay 3");
		}
	}

}