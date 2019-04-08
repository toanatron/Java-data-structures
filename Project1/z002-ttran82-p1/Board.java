

/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Board.java
 *
 * Description: Abstract Board class from which a board class specific to
 * any game can be constructed. This file SHOULD NOT be modified.
 * 
 ***************************************************************************/

public abstract class Board<T extends Card> {
	
	protected Player<T> currentPlayer;  //the currentPlayer that can either playcard or addcard 
	protected int numPlayer;            //total number of players in game
	protected Deck<T> deck;             //deck of card
	
	   
	public Board(Deck<T> deck){      //constructor used to initialize the game
		this.currentPlayer = null;    //sets currentPlayer to no one
		this.numPlayer = 0;           //setting number of players to 0, because no one has entered the game.
		this.deck = deck;             //contains all 52 cards
	}
	
	abstract Player<T> getCurrentPlayer(); //retrieves the currentPlayer
	
	abstract int getNumPlayers();          //returns total players
	
	abstract Deck<T> getDeck();            //returns the deck of cards
	
	abstract boolean changeTurn();         //changes player's turns
	
	abstract void addPlayer(Player<T> x);   //adding a new player to the left of currentPlayer

}
