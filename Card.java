package Crazy8s;


public class Card {

	private int value;
	private Suit suit;
	
	/**
	 * Creates a card
	 * @param value The value of the card. 
	 * @param suit The suit of the card.
	 */
	public Card(int value, Suit suit){
		this.suit = suit;
		this.value = value;
		
	}

	/**
	 * Gets the value of the card that called it.
	 * @return Returns the int value of the card. Values will be 1-13, beginning with the Ace
	 */
	public int getValue() {
		return value;
	}

	/**
	 * Sets the value of a card
	 * @param value Desired value (int) of the card. 
	 */
	public void setValue(int value) {
		this.value = value;
	}

	/**
	 * Gets the suit of the card that called it.
	 * @return The suit of the card.
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Sets the suit of the card object that called it.
	 * @param suit Either hearts, diamonds, clubs, or spades
	 */
	public void setSuit(Suit suit) {
		this.suit = suit;
	}


	@Override
	public String toString() {
		
		String valueOfCard = "";
		
		switch(value){
		
		case 1:
			valueOfCard = "Ace";
			break;
		case 2:
			valueOfCard = "2";
			break;
		case 3:
			valueOfCard = "3";
			break;
		case 4:
			valueOfCard = "4";
			break;
		case 5:
			valueOfCard = "5";
			break;
		case 6:
			valueOfCard = "6";
			break;
		case 7:
			valueOfCard = "7";
			break;
		case 8:
			valueOfCard = "8";
			break;
		case 9:
			valueOfCard = "9";
			break;
		case 10:
			valueOfCard = "10";
			break;
		case 11:
			valueOfCard = "Jack";
			break;
		case 12:
			valueOfCard = "Queen";
			break;
		case 13:
			valueOfCard = "King";
			break;
		}
		
		return valueOfCard + " of " + suit.toString();
				
	}



	
}
