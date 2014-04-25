package poker;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import poker.Card.Suit;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class CardTest {
	private Card joker;
	private Card clubs1;
	private Card diamonds1;
	private Card hearts1;
	private Card spades1;
	
	@Before
	public void prepareCards() {
		joker = new Card();
		
		clubs1 = new Card(Suit.CLUBS, 1);
		diamonds1 = new Card(Suit.DIAMONDS, 1);
		hearts1 = new Card(Suit.HEARTS, 1);
		spades1 = new Card(Suit.SPADES, 1);
	}

	@Test
	public void JokerのCardを作れる() {
		Card joker = new Card(); 
		assertThat(joker.getIsJoker(), is(true));
		assertThat(joker.getSuit(), is(Suit.NONE));
		assertThat(joker.getNumber(), is(Card.NO_NUMBER));
	}
	
	@Test
	public void 数字のCardを作れる() {
		int number = 3;
		Card hearts = new Card(Suit.HEARTS, number);
		
		assertThat(hearts.getIsJoker(), is(false));
		assertThat(hearts.getNumber(), is(number));
		assertThat(hearts.getSuit(), is(Suit.HEARTS));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 数字が0以下のCardのインスタンス化は例外が発生する() {
		new Card(Suit.HEARTS, 0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 数字が14以上のCardのインスタンス化は例外が発生する() {
		new Card(Suit.HEARTS, 14);
	}
	
	@Test
	public void Jokerが最も順序が高い() {
		assertThat(isGreaterThan(joker, clubs1), is(true));
		assertThat(isGreaterThan(joker, diamonds1), is(true));
		assertThat(isGreaterThan(joker, hearts1), is(true));
		assertThat(isGreaterThan(joker, spades1), is(true));
	}
	
	@Test
	public void Suitの順序が正しい() {
		assertThat(isGreaterThan(spades1, hearts1), is(true));
		assertThat(isGreaterThan(hearts1, diamonds1), is(true));
		assertThat(isGreaterThan(diamonds1, clubs1), is(true));
	}
	
	@Test
	public void 数字の順序が正しい() {
		Card spades2 = new Card(Suit.SPADES, 2);
		assertThat(isGreaterThan(spades2, spades1), is(true));
	}
	
	@Test
	public void Suitの順序は数字の順序より優先される() {
		Card hearts2 = new Card(Suit.HEARTS, 2);
		assertThat(isGreaterThan(spades1, hearts2), is(true));
	}
	
	@Test
	public void 同じCardの順序は等しい() {
		Card spades1 = new Card(Suit.SPADES, 1);
		Card joker = new Card();
		assertThat(isEqualTo(spades1, this.spades1), is(true));
		assertThat(isEqualTo(joker, this.joker), is(true));
	}
	
	@Test
	public void Cardはソートできる() {
		Card[] array = {hearts1, spades1, diamonds1, joker, spades1, clubs1};
		List<Card> list = Arrays.asList(array);
		
		Card[] exptectedArray = {clubs1, diamonds1, hearts1, spades1, spades1, joker};
		List<Card> expected = Arrays.asList(exptectedArray);
		Collections.sort(list);
		
		assertThat(list, is(expected));
	}
	
	@Test
	public void 同じCardのequalsはtrue() {
		Card clubs1_2 = new Card(Card.Suit.CLUBS, 1);
		Card joker_2 = new Card();
		
		assertThat(clubs1.equals(clubs1_2), is(true));
		assertThat(joker.equals(joker_2), is(true));
	}
	
	private boolean isGreaterThan(Comparable<Card> a, Card b) {
		return a.compareTo(b) > 0;
	}
	
	private boolean isEqualTo(Comparable<Card> a, Card b) {
		return a.compareTo(b) == 0;
	}
}
