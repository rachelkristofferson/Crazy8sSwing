package Crazy8s;

import java.util.*;
public class Player extends DeckOfCards{

	private Card[] hand;
	private String name;
	private int numCards;//Cards in your hand
	private int maxCards = 13;
	
	/**
	 * Creates a player with the ability to name within making the object. Defaults the 
	 * hand array to 13 cards per hand max. Each player begins with an empty hand.
	 */
	public Player(){
		hand = new Card [13];//instantiates hand to hold a max of 13 cards
		numCards = 0;
		this.clear();//Player's hand is empty
		this.name = "Player";
		
	}
	
	public Player(String name) {
	    this();
	    this.name = name;
	}
	
	/**
	 * Clears the hand
	 */
	public void clear(){//Clears the hand
		
		for(int i = 0; i < 13; i++){
			this.hand[i] = null;//Runs through the hand (13 cards), and sets each spot to null.
		}	
	}

	/**
	 * Sorts hand by suit, then value/rank
	 */
	public void sortHand()
	{
		Card Lowest = new Card(14, Suit.Hearts);
		Card Initial1 = new Card(15, Suit.Hearts);
		Card Initial2 = new Card(15, Suit.Hearts);
		Card Initial3 = new Card(15, Suit.Hearts);
		Card Initial4 = new Card(15, Suit.Hearts);
		Card Initial5 = new Card(15, Suit.Hearts);
		Card Initial6 = new Card(15, Suit.Hearts);
		Card Initial7 = new Card(15, Suit.Hearts);
		Card Initial8 = new Card(15, Suit.Hearts);
		Card Initial9 = new Card(15, Suit.Hearts);
		Card Initial10 = new Card(15, Suit.Hearts);
		Card Initial11 = new Card(15, Suit.Hearts);
		Card Initial12 = new Card(15, Suit.Hearts);
		Card Initial13 = new Card(15, Suit.Hearts);
		Card[] hold = null;
		hold = new Card [13];
		int holdCount = 0;
		int holder = 0;
		int temp = 0;
		int heartsCount	= 0;
		int spadesCount = 0;
		int clubsCount = 0;
		int diamondsCount = 0;
		hold[0] = Initial1;
		hold[1] = Initial2;
		hold[2] = Initial3;
		hold[3] = Initial4;
		hold[4] = Initial5;
		hold[5] = Initial6;
		hold[6] = Initial7;
		hold[7] = Initial8;
		hold[8] = Initial9;
		hold[9] = Initial10;
		hold[10] = Initial11;
		hold[11] = Initial12;
		hold[12] = Initial13;
		for (int i = 0; i <numCards; i++)
		{
			if (hand[i].getSuit().equals(Suit.Hearts))
			{
				heartsCount++;
			}
			else if (hand[i].getSuit().equals(Suit.Spades))
			{
				spadesCount++;
			}
			else if (hand[i].getSuit().equals(Suit.Clubs))
			{
				clubsCount++;
			}
			else if (hand[i].getSuit().equals(Suit.Diamonds))
			{
				diamondsCount++;
			}
		}
		for (holdCount = 0; holdCount < heartsCount; holdCount++)
		{
		for (int i =0; i < numCards; i++)
		{
			if (hand[i].getSuit().equals(Suit.Hearts))
			{
				if (Lowest.getValue() > hand[i].getValue())
				{
					Lowest.setValue(hand[i].getValue());
					temp = i;
				}			
			}	
		}
		hold[holder].setValue(Lowest.getValue());
		hold[holder].setSuit(Lowest.getSuit());
		holder++;
		hand[temp].setValue(14);
		Lowest.setValue(14);
		}
		Lowest.setSuit(Suit.Spades);
		for (holdCount = 0; holdCount < spadesCount; holdCount++)
		{
		for (int i =0; i < numCards; i++)
		{
			if (hand[i].getSuit().equals(Suit.Spades))
			{
				if (Lowest.getValue() > hand[i].getValue())
				{
					Lowest.setValue(hand[i].getValue());
					temp = i;
				}			
			}	
		}
		hold[holder].setValue(Lowest.getValue());
		hold[holder].setSuit(Lowest.getSuit());
		holder++;
		hand[temp].setValue(14);
		Lowest.setValue(14);
		}
		Lowest.setSuit(Suit.Clubs);
		for (holdCount = 0; holdCount < clubsCount; holdCount++)
		{
		for (int i =0; i < numCards; i++)
		{
			if (hand[i].getSuit().equals(Suit.Clubs))
			{
				if (Lowest.getValue() > hand[i].getValue())
				{
					Lowest.setValue(hand[i].getValue());
					temp = i;
				}			
			}	
		}
		hold[holder].setValue(Lowest.getValue());
		hold[holder].setSuit(Lowest.getSuit());
		holder++;
		hand[temp].setValue(14);
		Lowest.setValue(14);
		}
		Lowest.setSuit(Suit.Diamonds);
		for (holdCount = 0; holdCount < diamondsCount; holdCount++)
		{
		for (int i =0; i < numCards; i++)
		{
			if (hand[i].getSuit().equals(Suit.Diamonds))
			{
				if (Lowest.getValue() > hand[i].getValue())
				{
					Lowest.setValue(hand[i].getValue());
					temp = i;
				}			
			}	
		}
		hold[holder].setValue(Lowest.getValue());
		hold[holder].setSuit(Lowest.getSuit());
		holder++;
		hand[temp].setValue(14);
		Lowest.setValue(14);
		}
		for (int i =0; i < numCards; i++)
		{
			hand[i] = hold[i]; 
		}
	}
	
	/**
	 * Returns the hand of the player
	 * @return An array of Card
	 */
	public Card[] getHand() {
		return hand;
	}
	
	/**
	 * Sets the hand of the player
	 * @param hand 
	 */
	public void setHand(Card[] hand) {
		this.hand = hand;
	}

	/**
	 * Gets the name of the player object
	 * @return A String representing the name of the player
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the player
	 * @param name A String that will represent a player
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the number of cards in the player's hand
	 * @return Number of cards in the player's hand
	 */
	public int getNumCards() {
		return numCards;
	}

	/**
	 * Sets the number of cards in the player's hand
	 * @param numCards New value of numCards
	 */
	public void setNumCards(int numCards) {
		this.numCards = numCards;
	}

	/**
	 * Gets the max number of cards that are allowed in a player's hand
	 * @return The max number of cards
	 */
	public int getMaxCards() {
		return maxCards;
	}

	/**
	 * Sets the max number of cards that are allowed in a player's hand
	 * @param maxCards New value of maxCards
	 */
	public void setMaxCards(int maxCards) {
		this.maxCards = maxCards;
	}


	@Override
	public String toString() {
		return getName() + "'s hand: " + Arrays.toString(hand) + " " + numCards + " Cards in hand";
	}


	

}
