package poker;

import poker.Card.Suit;

import java.util.Collections;
import java.util.LinkedList;

/**
 * 52枚+ジョーカーのカードを持つデッキを表すクラス
 */
public class Deck {
	/**
	 * デッキが保持するカード
	 */
	private LinkedList<Card> cards;
	
	/**
	 * デッキのコンストラクタ(ジョーカーは0枚)
	 */
	public Deck() {
		this(0);
	}
	
	/**
	 * @param numJokers デッキに含めるジョーカーの枚数
	 */
	public Deck(int numJokers) {
		if (numJokers < 0) {
			throw new IllegalArgumentException("Jokerの枚数は0以上です(numJokers:" + numJokers + ")");
		}
		
		cards = new LinkedList<>();
		for (Suit suit : Suit.values()) {
			if (suit == Card.Suit.NONE) {
				continue;
			}
			
			// 1 to 13
			for (int n = Card.NUMBER_MIN; n <= Card.NUMBER_MAX; n++) {
				cards.addLast(new Card(suit, n));
			}
		}
		for (int i = 0; i < numJokers; i++) {
			cards.addLast(new Card());
		}
		Collections.shuffle(cards);
	}
	
	/**
	 * デッキからカードを指定枚数引き、配列にして返す
	 * @param num 引きたいカードの枚数
	 * @return 引いたカードを表す配列
	 */
	public Card[] draw(int num) {
		if (num <= 0) {
			throw new IllegalArgumentException("引くカードの枚数は1以上です(num:" + num + ")");
		}
		if (num > cards.size()) {
			throw new IllegalArgumentException("残り枚数より多いカードは引けません(" + num  + ")");
		}
		
		Card[] drawnCards = new Card[num];
		for (int i = 0; i < num; i++) {
			drawnCards[i] = cards.pop();
		}
		return drawnCards;
	}
	
	/**
	 * デッキから１枚だけカードを引き、Cardを返す
	 * @return 引いたカードを表すインスタンス
	 */
	public Card draw() {
		return draw(1)[0];
	}
	
	/**
	 * @return 残りのカードの枚数
	 */
	public int getNumOfCards() {
		return cards.size();
	}
}
