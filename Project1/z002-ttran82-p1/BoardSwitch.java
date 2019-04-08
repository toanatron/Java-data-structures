/**************************************************************************
 * @author <INSERT YOUR NAME>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: BoardSwitch.java
 *
 * Description: <INSERT DESCRIPTION>
 * 
 ***************************************************************************/
public class BoardSwitch<T extends Card> extends Board<T>{
	
	// TO DO: add your implementation and JavaDoc
	private Player<T> head = null;      //a pointer used to help with addPlayer method
	public BoardSwitch(Deck<T> deck){   //constructor used to initialize fields
		//constructor
		//start with zero players
      super(deck);                     //calls Board() constructor
	}
   
   //method returns current player
	@Override
	public Player<T> getCurrentPlayer() {
		// return the current player
		// O(1)
      return currentPlayer;
	}
   
   //method returns total number of players
	@Override
	public int getNumPlayers() {
		// return how many players 
		// O(1)
      return numPlayer;
	}
	
   //method returns the deck of cards
	@Override
	public Deck<T> getDeck(){
		//return the current deck
		// O(1)
      return deck;
	}
   //method used to change player's turns, by moving current player to the next player in circular linked list
	@Override
	public boolean changeTurn() {
		// move the current player to the next one in the linked list
		// return false if cannot change
		// O(1)
      if(head == null){         //if there are no players, return false.
      return false;
      }else{
         currentPlayer = currentPlayer.getNext();
         return true;
      }
    }
	
	//method adds a player to the board(linked list)
	@Override
	public void addPlayer(Player<T> x) {
		// add another player in the linked list
		// should add to the left of currentPlayer
		// O(N)
      if(numPlayer == 0){  //case for the first player, currentPlayer points to the head Player.
         head = x;
         currentPlayer = x;
         x.setNext(head);
      }else{            
         Player<T> tmp = head;      
         for(int i =0 ; i< numPlayer;i++){
            if(tmp.getNext().equals(currentPlayer)){  //loops through the entire linked list, so the new player will be to the left of currentPlayer.
               tmp.setNext(x);                        //link the player to the left of currentPlayer to new player, the new player link to currentPlayer
               x.setNext(currentPlayer);
               break;
            }
            tmp = tmp.getNext();
         }
      }
      numPlayer++;                              //increment total number of players, when new player is added
   }	
   //determine the winner of the game, based on highest points. If points are equal use lexicographical order to compare.
	public Player<T> findWinner(){
		// return the player with the highest point
		// O(N)
      int high =0;
      Player<T> winner = head;
      Player<T> current = head;
      for(int i =0; i<numPlayer;i++){     //loops through the circular linked list to find highest point.
		   if(current.getPoints() > high){
            high = current.getPoints();
            winner = current;
         }else if(current.getPoints() == high){             //determine tied winner based on lexicographical order.
            int decide = winner.getName().compareTo(current.getName());
            if(decide >0){winner = current;}
         }current = current.getNext();
      }
      return winner;
	}

	//-----------------------------------------------------
	// example test code... edit this as much as you want!
	// you will need working CardSwitch, Hand, Player, Deck and PlaySwitch classes to run the given code
	
	public static void main(String[] args) {
		Deck<CardSwitch> deck = new Deck<CardSwitch>();
		PlaySwitch.init_deck(deck);
			
		BoardSwitch<CardSwitch> myBoard = new BoardSwitch<CardSwitch>(deck);
		Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
		Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");
      
      
		myBoard.addPlayer(player1);
		
		if (myBoard.getNumPlayers() ==1  && myBoard.getCurrentPlayer() == player1
			&& player1.getNext() == player1){
			System.out.println("Yay 1");
		}

		myBoard.addPlayer(player2);
		if (myBoard.getNumPlayers() ==2  && myBoard.getCurrentPlayer() == player1
			&& (myBoard.changeTurn()==true) && myBoard.getCurrentPlayer() == player2){
			System.out.println("Yay 2");
		}
		
		player1.receiveCard(new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES));
		player1.receiveCard(new CardSwitch(Card.Rank.JACK, Card.Suit.CLUBS));
		player2.receiveCard(new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS));
		player2.receiveCard(new CardSwitch(Card.Rank.THREE, Card.Suit.SPADES));

		if (player1.getNext() == player2 && player2.getNext() == player1
			&& myBoard.findWinner() == player2){
			System.out.println("Yay 3");
		}
      
		
	
	}
	

}
