package poker;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Player player;

	@Test
	public void カードを5枚持っている() {
		Card[] cards = player.getCards();
		assertEquals(cards.length, 5);
	}
	
	@Test
	public void カードを取り替えできる() {
		Card[] lastCards = player.getCards().clone();
		player.draw(3);
		Card[] newCards = player.getCards();
		assertThat(getNumOfSameCards(lastCards, newCards), is(4));	
	}
	
	@Test
	public void 複数のカードを取り替えできる() {
		Card[] lastCards = player.getCards().clone();
		player.draw(3, 1, 2);
		Card[] newCards = player.getCards();
		assertThat(getNumOfSameCards(lastCards, newCards), is(2));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 重複のあるカードを取り替えようとすると例外が送出される() {
		player.draw(3, 1, 2, 2);
	}
	
	@Test(expected=ArrayIndexOutOfBoundsException.class)
	public void 存在しないカードを取り替えようとすると例外が送出される() {
		player.draw(3, 9, 2);
	}
	
	@Before
	public void preparePlayer() {
		player = new Player();
	}
	
	private int getNumOfSameCards(Card[] cardsA, Card[] cardsB) {
		int num = 0;
		
		for (Card a : cardsA) {
			for (Card b : cardsB) {
				if (a.equals(b)) {
					num++;
				}
			}
		}
		return num;
	}
}
