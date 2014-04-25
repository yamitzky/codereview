package poker;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class DeckTest {
	static final int NUM_OF_NUMBER_CARDS = 52;

	@Test
	public void Jokerの枚数を指定できる() {
		int numJoker = 5;
		Deck deck = new Deck(numJoker);
		assertThat(getNumOfJokers(deck), is(numJoker));
	}
	
	@Test
	public void Jokerの枚数は指定しないと0枚である() {
		Deck deck = new Deck();
		assertThat(getNumOfJokers(deck), is(0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void Jokerの枚数を0未満で指定すると例外が投げられる() {
		new Deck(-1);
	}
	
	@Test
	public void Deckの残り枚数を確認できる() {
		int numJoker = 2;
		int expected = NUM_OF_NUMBER_CARDS + numJoker; // must be 52 + 2
		Deck deck = new Deck(numJoker);
		assertThat(deck.getNumOfCards(), is(expected));
	}
	
	@Test
	public void 指定枚数のカードを引くことができる() {
		int numCards = 3;
		Deck deck = new Deck();
		Card[] cards = deck.draw(numCards);
		assertThat(cards.length, is(numCards));
	}
	
	@Test
	public void カードを引くとDeckの残り枚数が減る() {
		int numJoker = 2;
		int numDrawing = 3;
		int expected = NUM_OF_NUMBER_CARDS + numJoker - numDrawing; // must be 52 + 2 - 3
		
		Deck deck = new Deck(numJoker);
		deck.draw(numDrawing);
		assertThat(deck.getNumOfCards(), is(expected));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 残り枚数よりも多いカードを引くと例外が投げられる() {
		Deck deck = new Deck();
		deck.draw(deck.getNumOfCards() + 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 引くカードの枚数を0以下にすると例外が投げられる() {
		Deck deck = new Deck();
		deck.draw(0);
	}
	
	/**
	 * @return デッキに含まれるジョーカーの枚数を取得する
	 */
	private int getNumOfJokers(Deck deck) {
		int numOfJokers = 0;
		while (deck.getNumOfCards() > 0) {
			Card card = deck.draw(1)[0];
			if (card.getIsJoker()) {
				numOfJokers++;
			}
		}
		return numOfJokers;
	}
}
