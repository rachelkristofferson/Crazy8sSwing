package Crazy8s;

import java.util.Arrays;

public class Bot extends Player{

	
	public Bot()
	{
		getHand();
		setName("Bot");
	}
	
	public int botPlay(DeckOfCards deck)
	{
		for (int i = 0; i < this.getNumCards(); i++)
		{
			if (getHand()[i].getSuit().equals(deck.getPile()[deck.getPileNumber()-1].getSuit()))
			{
				if (getHand()[i].getValue() != 8)
				{
				return i;
				}
			}
	
			if (getHand()[i].getValue() == (deck.getPile()[deck.getPileNumber()-1]).getValue())
			{
				if (getHand()[i].getValue() != 8)
				{
				return i;
				}
			}
		
			if (getHand()[i].getValue() == 8)
			{
				return i;
			}
		}
		return 14;	
	}
	
	public Suit prefered()
	{
		Suit prefered = Suit.Hearts;
		int heartsCount	= 0;
		int spadesCount = 0;
		int clubsCount = 0;
		int diamondsCount = 0;
		for (int i = 0; i <getNumCards(); i++)
		{
			if (getHand()[i].getSuit().equals(Suit.Hearts))
			{
				heartsCount++;
			}
			else if (getHand()[i].getSuit().equals(Suit.Spades))
			{
				spadesCount++;
			}
			else if (getHand()[i].getSuit().equals(Suit.Clubs))
			{
				clubsCount++;
			}
			else if (getHand()[i].getSuit().equals(Suit.Diamonds))
			{
				diamondsCount++;
			}
		}
		if ((heartsCount >= spadesCount) && (heartsCount >= clubsCount) && (heartsCount >= diamondsCount))
			{
				prefered = Suit.Hearts;
			}
		else if ((spadesCount >= heartsCount) && (spadesCount >= clubsCount) && (spadesCount >= diamondsCount)) 
			{
			prefered = Suit.Spades;
			}
		else if ((clubsCount >= heartsCount) && (clubsCount >= spadesCount) && (clubsCount >= diamondsCount)) 
			{
				prefered = Suit.Clubs;
			}
		else if ((diamondsCount >= heartsCount) && (diamondsCount >= spadesCount) && (diamondsCount >= clubsCount)) 
			{
				prefered = Suit.Diamonds;
			}
		return prefered;
		
	}
	@Override
	public String toString() 
	{
		return getName() + "'s hand: " + Arrays.toString(getHand());
	}
}
