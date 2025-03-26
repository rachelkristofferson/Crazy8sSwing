package Crazy8s;

import java.util.Arrays;
import java.util.Random;

public class DeckOfCards {
	
	private Card[] deck;
	private int numberOfCards;//Number of cards in the deck
	private Card[] pile;
	private int pileNumber = 0;
	private Card top;
	
	/**
	 * Defaults the number of decks to 1 deck.
	 */
	public DeckOfCards(){
		
		this(1);	
	}
	
	/**
	 * Creates a deck of cards, with the option to have multiple decks.
	 * @param numberOfDecks Number of decks you would like to play with
	 */
	public DeckOfCards(int numberOfDecks){//multiple decks in case of more people
		int index = 0;
		
		this.numberOfCards = numberOfDecks * 52;
		this.deck = new Card[this.numberOfCards];
		this.pile = new Card[this.numberOfCards];
		
		for(int i = 0; i < numberOfDecks; i++){//deck
			
			for(int s = 0; s < 4; s++){//suit
				
				for(int n = 1; n<= 13; n++){//value
					
					this.deck[index] = new Card(n, Suit.values()[s]);
					index++;
				}
			}
			
		}

	}//end constructor
	
	
	/**
	 * Shuffles the deck of cards
	 */
	public void shuffle(){
		Card hold;//Place holder for swap
		int h; 
		
		Random shuffling = new Random();

		for(int i = 0; i< this.numberOfCards; i++){
			h = shuffling.nextInt(this.numberOfCards);//Randomly assigns the index of h.
			
			//Swapping the cards
			hold = this.deck[i];
			this.deck[i] = this.deck[h];
			this.deck[h] = hold;
		}
	}

	
	/**
	 * Deals the top card of a deck of cards
	 * @return The top card
	 */
	public Card deal(){//deals the top card

		//this.shuffle();
		
		top = this.deck[0];
		
		/*
		if (this.deck[0].getValue() == 8){//Avoids beginning with an 8
			top = this.deck[1];
			
			if(this.deck[1].getValue() == 8){
				top = this.deck[2];
				
				if(this.deck[2].getValue() == 8){
					top = this.deck[3];
				
					if(this.deck[3].getValue() == 8){
						top = this.deck[4];
					}
				}
					
			}
				
		}//End of 8 check*/
		
		
		if(numberOfCards != 0){//Checkpoint to avoid out of bounds
			for(int i = 1; i < this.numberOfCards; i++){//shifts all cards over
				this.deck[i-1] = this.deck[i];
			}
			
			this.deck[numberOfCards - 1] = null;//reduces the index by 1
			numberOfCards--;	
		}
		
		else{//In case you try to deal when you've reached the end
			System.out.println("You've reached the end of the deck");
		}
		
		return top;
	
	}
	
	
	/**
	 * Turns over the first card on the deck and begins the pile and the game.
	 */
	public void playFirst()
	{
	
		pile[pileNumber] = deal();
		pileNumber++;
	}
	
	
	/**
	 * Plays a card from the player's hand
	 * @param p1 Player object
	 * @param num Index of the card being played
	 * @param hand Player's hand
	 */
	public void play(Player p1, int num, Card[] hand)
	{
		pile[pileNumber] = hand[num];
		pileNumber++;
		int temp = num;
		while (hand[p1.getNumCards()-1] != null)
		{
		hand[temp] = hand[temp + 1];
		temp++;
		}
			p1.setNumCards(p1.getNumCards() - 1);
	}
	
	
	
	/**
	 * Deals out the hand of each player
	 * @param p1 player 1
	 * @param p2 player 2
	 */
	public void dealOut(Player p1, Player p2)
	{
		for (int count = 0; count < 8; count++)
		{
			
			shuffle();
			if(p1.getNumCards() < p1.getMaxCards()){
				p1.getHand()[p1.getNumCards()] = this.deal();
				p1.setNumCards(p1.getNumCards() + 1);
			}
			
			if(p2.getNumCards() < p2.getMaxCards()){
				p2.getHand()[p2.getNumCards()] = this.deal();
				p2.setNumCards(p2.getNumCards() + 1);
			}
		}
	}
	
	
	/**
	 * Deals out the hands of each player.
	 * @param p1 player 1
	 * @param p2 player 2
	 * @param p3 player 3
	 */
	public void dealOut(Player p1, Player p2, Player p3)
	{
		for (int count = 0; count < 8; count++)
		{
			shuffle();
			
			if(p1.getNumCards() < p1.getMaxCards()){
				p1.getHand()[p1.getNumCards()] = this.deal();
				p1.setNumCards(p1.getNumCards() + 1);
			}
			if(p2.getNumCards() < p2.getMaxCards()){
				p2.getHand()[p2.getNumCards()] = this.deal();
				p2.setNumCards(p2.getNumCards() + 1);
			}
			if(p3.getNumCards() < p3.getMaxCards()){
				p3.getHand()[p3.getNumCards()] = this.deal();
				p3.setNumCards(p3.getNumCards() + 1);
			}
			
		}
	}
	
	/**
	 * Deals out the hands of each player
	 * @param p1 player 1
	 * @param p2 player 2
	 * @param p3 player 3
	 * @param p4 player 4
	 */
	public void dealOut(Player p1, Player p2, Player p3, Player p4)
	{
		for (int count = 0; count < 8; count++)
		{
			shuffle();
			
			if(p1.getNumCards() < p1.getMaxCards()){
				p1.getHand()[p1.getNumCards()] = this.deal();
				p1.setNumCards(p1.getNumCards() + 1);
			}
			
			if(p2.getNumCards() < p2.getMaxCards()){
				p2.getHand()[p2.getNumCards()] = this.deal();
				p2.setNumCards(p2.getNumCards() + 1);
			}
			
			if(p3.getNumCards() < p3.getMaxCards()){
				p3.getHand()[p3.getNumCards()] = this.deal();
				p3.setNumCards(p3.getNumCards() + 1);
			}
			
			if(p4.getNumCards() < p4.getMaxCards()){
				p4.getHand()[p4.getNumCards()] = this.deal();
				p4.setNumCards(p4.getNumCards() + 1);
			}
		}
	}
	
	/**
	 * Gets the deck of cards that is being used
	 * @return The deck of cards
	 */
	public Card[] getDeck() {
		return deck;
	}
	
	/**
	 * Sets the deck
	 * @param deck
	 */
	public void setDeck(Card[] deck) {
		this.deck = deck;
	}

	/**
	 * Gets the number of cards in the deck
	 * @return The number of cards that are remaining in the deck.
	 */
	public int getNumberOfCards() {
		return numberOfCards;
	}

	/**
	 * Sets the number of cards in the deck
	 * @param numberOfCards The new value of numberOfCards
	 */
	public void setNumberOfCards(int numberOfCards) {
		this.numberOfCards = numberOfCards;
	}

	/**
	 * Gets the pile array
	 * @return The cards that are in the pile
	 */
	public Card[] getPile() {
		return pile;
	}

	/**
	 * Sets the pile Array
	 * @param pile
	 */
	public void setPile(Card[] pile) {
		this.pile = pile;
	}

	/**
	 * Retrieves the number of cards in the pile
	 * @return Number of cards in the pile
	 */
	public int getPileNumber() {
		return pileNumber;
	}

	/**
	 * Sets the number of cards in the pile
	 * @param pileNumber New value of pileNumber
	 */
	public void setPileNumber(int pileNumber) {
		this.pileNumber = pileNumber;
	}

	/**
	 * Top card from the deck.
	 * @return The next card to be drawn.
	 */
	public Card getTop() {
		return top;
	}

	/**
	 * Sets the top card to a different card
	 * @param top New card value of top
	 */
	public void setTop(Card top) {
		this.top = top;
	}

	@Override
	public String toString() {
		return "DeckOfCards [deck=" + Arrays.toString(deck)
				+ ", numberOfCards=" + numberOfCards + ", pile="
				+ Arrays.toString(pile) + ", pileNumber=" + pileNumber + "]";
	}
	
	
	
	
}
	

