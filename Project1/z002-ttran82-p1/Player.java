/**************************************************************************
 * @author <TOAN TRAN>
 * CS310 Spring 2018
 * Project 1
 * George Mason University
 * 
 * File Name: Player.java
 *
 * Description: <Player is a LinkedList datatype, it links players in a game together. Using an arraylist object called hand to keep track of which cards a player has.>
 * 
 ***************************************************************************/
class Player <T extends Card> {
	
	// required fields
	private String name;
	private int points;
	private Hand<T> hand;	
	private Player<T> next;
	
	// TO DO: add your implementation and JavaDoc
   //Constructor used to initialize fields.
	public Player(String name){
		//constructor
		this.name = name;
      this.next = null;
      this.points = 0;
      this.hand = new Hand(); //initialize hand as a Hand object, calling the Hand default constructor
	}
   //method sets the next player in the linkedlist as the player being passed as the parameter
	public void setNext(Player<T> p){
		//set next player
      this.next = p;
	}
	
   //method returns the next player in the linkedlist
	public Player<T> getNext(){
		//return next player
		return next;
	}
	
   //method that checks linked list if there are any player after curent player.
	public boolean hasNext() {
		// whether there is a player after me
		if(next != null){
      return true;
      }else return false;     
	}
	
   //method that returns the total points a player possesses.
	public int getPoints(){
		// return points of this player
		// determined by cards in hand
		return points;
	}
	
   //method returns the name of the player	
	public String getName(){
		// return name of the player
      return name;
	}
	
   //method checks if a player can receieve a card, if player has the card return false, else add the card to current deck.
	public boolean receiveCard(T c){
		// receive a card and add it to hand
		// return?
      CardSwitch card = new CardSwitch(c.rank,c.suit);   //creating a CardSwitch object allows me to obtain the points that card has
      if(hasCard(c) == false){
         hand.addCard(c);
         this.points += card.getPoints();          //adds card's point value to total player points.
         return true;}
      return false;
	}
	
   //method checks if a player's hand contains the card
	public boolean hasCard(T c){
		// return checking: whether we have the card in hand
      for(int i =0; i< hand.numCards();i++){
         if(hand.getCard(i).equals(c))
         return true;
      }return false;      
	}
	
   //method plays a card or remove it from hand and loses points from total points based on card's rank.
	public boolean playCard(T c){
		// give away one card from hand
		// return false if card not present
      CardSwitch card = new CardSwitch(c.rank,c.suit);
		int index = hand.indexOf(c);
      if(index == -1){
         return false;}
         playCard(index);
         this.points -= card.getPoints();
      return true;
         
	}
   //method removes card from hand based on index
	public T playCard(int index){
		// give away the card at index
		// throw RuntimeException for invalid index
      T card;
         if(index >= 0 && index < hand.numCards()){
            card =hand.removeCard(index); //calls removeCard method in hand arraylist
         return card;
         }else throw new RuntimeException();        
	}
	
	

	//---------------------------------------------------
	//example test code... edit this as much as you want!
	// you will need working CardSwitch and Hand classes to run the given code
	
	
	public String toString(){
		// Not required; edit for your own testing 
		return "Player "+ name;
	}


	public static void main(String[] args) {
		CardSwitch card1 = new CardSwitch(Card.Rank.ACE, Card.Suit.SPADES);
		CardSwitch card2 = new CardSwitch(Card.Rank.JACK, Card.Suit.SPADES);
		CardSwitch card3 = new CardSwitch(Card.Rank.NINE, Card.Suit.HEARTS);
		Player<CardSwitch> player1 = new Player<CardSwitch>("Tom");
		Player<CardSwitch> player2 = new Player<CardSwitch>("Jerry");

		player1.receiveCard(card2);
		player1.receiveCard(card3);
		player2.receiveCard(card1);
		player1.setNext(player2);

		if (player1.getName().equals("Tom") && player1.getNext() == player2){
			System.out.println("Yay 1");
		}
      
		if (player1.hasCard(card2) == true && player1.getPoints() == 19){
			System.out.println("Yay 2");
		}
		if ((player2.hasNext()==false) && player1.playCard(0) == card2){
			System.out.println("Yay 3");
		}
      System.out.println(player2.getPoints());
	
	}


}