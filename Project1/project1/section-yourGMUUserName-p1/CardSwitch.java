/**************************************************************************
 * @author <INSERT YOUR NAME>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: CardSwitch.java
 *
 * Description: <INSERT DESCRIPTION>
 * 
 ***************************************************************************/

public class CardSwitch extends Card{

	// TO DO: fill the code below and add JavaDoc
	
	
	public CardSwitch(Rank r, Suit s){
		// constructor to create card for the game Switch

	}
	
	@Override
	public boolean equals(Card anotherCard){
		// checks if two cards equals and returns a boolean
		
	}
	
	@Override
    public int getPoints(){
	    // return points of the card

    }
	
	@Override
	public String toString(){
		// convert card to string consisting of as "(rank,suit)"
		// see examples below for format

	}
	
	//----------------------------------------------------
	//example test code... edit this as much as you want!
	public static void main(String[] args) {
		CardSwitch card = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		
		if (card.getRank().equals(Card.Rank.ACE)){
			System.out.println("Yay 1");
		}
		
		if (card.toString().equals("(ACE,SPADES)")){
			System.out.println("Yay 2");
		}

		if (card.getPoints()==1){
			System.out.println("Yay 3");
		}
	}

}