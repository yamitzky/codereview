package poker;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class HandTest {

	@Test
	public void カードを５枚持つ() {
		Card[] cards = new Card[5];
		Hand hand = new Hand(cards);

		assertThat(hand.getCards().length, is(5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void カードは6枚以上で指定できない() {
		Card[] cards = new Card[6];
		new Hand(cards);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void カードは4枚以下で指定できない() {
		Card[] cards = new Card[4];
		new Hand(cards);
	}
	
	@Test
	public void カードを交換できる() {
		Card[] cards = new Card[5];
		Hand hand = new Hand(cards);
		Card club1 = new Card(Card.Suit.CLUBS, 1);
		
		hand.replace(0, club1);
		
		List<Card> newCards = Arrays.asList(hand.getCards());
		assertThat(newCards.contains(club1), is(true));
	}
	
	@Test
	public void 順序は保たれる() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.HEARTS, 12),
				new Card(Card.Suit.HEARTS, 1),
				new Card(Card.Suit.HEARTS, 11),
				new Card(Card.Suit.HEARTS, 10),
		};
		Hand hand = new Hand(cards);
		hand.getName();
		assertThat(cards, is(hand.getCards()));
	}

	@Test
	public void ロイヤルストレートフラッシュを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.HEARTS, 12),
				new Card(Card.Suit.HEARTS, 1),
				new Card(Card.Suit.HEARTS, 11),
				new Card(Card.Suit.HEARTS, 10),
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("ロイヤルストレートフラッシュ"));
	}
	
	@Test
	public void ストレートフラッシュを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.HEARTS, 11),
				new Card(Card.Suit.HEARTS, 12),
				new Card(Card.Suit.HEARTS, 10),
				new Card(Card.Suit.HEARTS, 9)
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("ストレートフラッシュ"));		
	}
	
	@Test
	public void フォーカードを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.CLUBS, 13),
				new Card(Card.Suit.HEARTS, 9),
				new Card(Card.Suit.DIAMONDS, 13),
				new Card(Card.Suit.SPADES, 13),
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("フォーカード"));
	}
	
	@Test
	public void フルハウスを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.CLUBS, 13),
				new Card(Card.Suit.SPADES, 9),
				new Card(Card.Suit.DIAMONDS, 13),
				new Card(Card.Suit.HEARTS, 9)
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("フルハウス"));
	}
	
	@Test
	public void フラッシュを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.HEARTS, 1),
				new Card(Card.Suit.HEARTS, 5),
				new Card(Card.Suit.HEARTS, 8),
				new Card(Card.Suit.HEARTS, 9)
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("フラッシュ"));
	}
	
	@Test
	public void ロイヤルストレートを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.HEARTS, 12),
				new Card(Card.Suit.CLUBS, 11),
				new Card(Card.Suit.HEARTS, 1),
				new Card(Card.Suit.HEARTS, 10)
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("ロイヤルストレート"));
	}
	
	@Test
	public void ストレートを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.CLUBS, 11),
				new Card(Card.Suit.HEARTS, 12),
				new Card(Card.Suit.HEARTS, 10),
				new Card(Card.Suit.HEARTS, 9),
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("ストレート"));
	}
	
	@Test
	public void スリーカードを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.SPADES, 13),
				new Card(Card.Suit.HEARTS, 10),
				new Card(Card.Suit.CLUBS, 13),
				new Card(Card.Suit.HEARTS, 9),
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("スリーカード"));
	}
	
	@Test
	public void ツーペアを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.SPADES, 13),
				new Card(Card.Suit.CLUBS, 10),
				new Card(Card.Suit.HEARTS, 9),
				new Card(Card.Suit.HEARTS, 10),
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("ツーペア"));
	}
	
	@Test
	public void ワンペアを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.CLUBS, 8),
				new Card(Card.Suit.SPADES, 13),
				new Card(Card.Suit.HEARTS, 10),
				new Card(Card.Suit.HEARTS, 9),
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("ワンペア"));
	}
	
	@Test
	public void ノーペアを持つ() {
		Card[] cards = {
				new Card(Card.Suit.HEARTS, 13),
				new Card(Card.Suit.SPADES, 1),
				new Card(Card.Suit.CLUBS, 8),
				new Card(Card.Suit.HEARTS, 10),
				new Card(Card.Suit.HEARTS, 9),
		};
		Hand hand = new Hand(cards);
		assertThat(hand.getName(), is("ノーペア"));
	}
}
