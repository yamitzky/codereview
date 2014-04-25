package poker;

import java.util.ArrayList;
import java.util.List;

public class Player {
	private Hand hand;
	private Deck deck;
	
	public Player() {
		deck = new Deck();
		hand = new Hand(deck.draw(Hand.NUM_CARDS));
	}
	
	/**
	 * @return プレイヤーの持っているカード
	 */
	public Card[] getCards() {
		return hand.getCards();
	}
	
	/**
	 * @return プレイヤーのカードの役
	 */
	public Hand getHand() {
		return hand;
	}

	/**
	 * 手持ちのカードを捨てて、交換する
	 * @param cardIdx 捨てたいカードのindex
	 */
	public void draw(int... cardIdx) {
		if (!isUnique(cardIdx)) {
			throw new IllegalArgumentException("指定が重複しています");
		}
		
		for (int index : cardIdx) {
			Card card = deck.draw();
			hand.replace(index, card);
		}
		return;
	}
	
	/**
	 * 数値の列がユニークであるかを確認する
	 * @param numbers 重複を含むかもしれない数値列
	 * @return 数値列に重複がなければtrue
	 */
	private boolean isUnique(int[] numbers) {
		List<Integer> uniqList = new ArrayList<>();
		for (int number : numbers) {
			if (uniqList.contains(number)) {
				return false;
			}
			uniqList.add(number);
		}
		return true;
	}
}
