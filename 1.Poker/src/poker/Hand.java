package poker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 手札を表すクラス
 * @author a13456
 */
public class Hand {
	private Card[] cards;
	
	/**
	 * 手札に含まれるカードの枚数
	 * 5枚であることを前提にテストされているため、定数としている
	 */
	public final static int NUM_CARDS = 5;
	
	/**
	 * カードを指定して手札を生成するためのコンストラクタ
	 * @param cards 手札に含まれる5枚のカード
	 */
	public Hand(Card[] cards) {
		if (cards.length != NUM_CARDS) {
			throw new IllegalArgumentException("カードの枚数は5枚である必要があります");
		}
		this.cards = cards;
	}
	
	/**
	 * カードを１枚入れ替える
	 * @param id　捨てたいカードのindex
	 * @param card 代わりに入れたいカードのインスタンス
	 */
	public void replace(int id, Card card) {
		cards[id] = card;
	}

	/**
	 * カードを取得する
	 * @return 手札に含まれる5枚のカード
	 */
	public Card[] getCards() {
		return cards;
	}
	
	/**
	 * 役の名前を日本語で取得する
	 * {@link #getCategory()}で役の種類を計算し、その名前を返す
	 * @return 手札の役名
	 */
	public String getName() {
		return getCategory().getName();
	}
	
	/**
	 * 手札の役のカテゴリー(種類)を計算する
	 * @return 役のカテゴリー(種類)
	 */
	public HandCategory getCategory() {
		Card[] sortedCards = cards.clone();
		Arrays.sort(sortedCards);
		
		boolean isStraight = isStraight();
		boolean isFlush = isFlush();
		boolean isRoyal = isRoyal(); 
		if (isStraight && isFlush) {
			return (isRoyal) ? HandCategory.ROYAL_STRAIGHT_FLUSH : HandCategory.STRAIGHT_FLUSH;  
		}
		if (isFlush) {
			return HandCategory.FLUSH;
		}
		if (isStraight) {
			return (isRoyal) ? HandCategory.ROYAL_STRAIGHT : HandCategory.STRAIGHT;  
		}

		// ペアはフラッシュとストレートを壊すので、上記条件と下記条件は両立しない(ワイルドカードがないとき)
		int[] numSameCards = getNumbersOfSameCards();
		int numPairs = numSameCards.length;

		if (numPairs == 1) {
			// ペアが1つならば、フォーカード、スリーカード、ワンペアになる
			if (numSameCards[0] == 4) {
				return HandCategory.FOUR_OF_KIND;
			} else if (numSameCards[0] == 3) {
				return HandCategory.THREE_OF_KIND;
			} else {
				return HandCategory.ONE_PAIR;
			}
		}
		
		if (numPairs == 2) {
			// ペアが２つならば、フルハウスかツーペアになる
			if (numSameCards[0] == 3 || numSameCards[1] == 3) {
				return HandCategory.FULL_HOUSE;
			} else {
				return HandCategory.TWO_PAIR;
			}
		}
		
		return HandCategory.NO_PAIR;
	}
	
	private boolean isStraight() {
		int[] numbers = getSortedNumbers(cards);
		
		// {1, 10, 11, 12, 13}のとき、1を14扱いにする
		if (numbers[0] == Card.NUMBER_MIN && numbers[numbers.length - 1] == Card.NUMBER_MAX) {
			numbers[0] += Card.NUMBER_MAX;
			Arrays.sort(numbers);
		}

		// １つでも隣り合うカードの差分が1でなければ、ストレートにはならない
		for (int i = 0; i < numbers.length - 1; i++) {
			if (numbers[i + 1] - numbers[i] != 1) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isFlush() {
		// １つでも隣り合うカードのマークが異なれば、フラッシュにはならない
		for (int i = 0; i < cards.length - 1; i++) {
			if (cards[i + 1].getSuit() != cards[i].getSuit()) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isRoyal() {
		int[] numbers = getSortedNumbers(cards);
		int[] royalOrder = {1, 10, 11, 12, 13};
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] != royalOrder[i]) {
				return false;
			}
		}
		return true;
	}
	
	private int[] getNumbersOfSameCards() {
		int[] numbers = getSortedNumbers(cards);
		List<Integer> pairs = new ArrayList<>();
		int sameCount = 1; // ペアがあったとき、ペアの持つ枚数を格納
		for (int i = 1; i < numbers.length; i++) {
			int current = numbers[i];
			int prev = numbers[i - 1];
			
			if (current == prev) {
				sameCount++;
			} else if (sameCount >= 2) {
				// 等しくなくて、前の数枚が同じカードであるとき
				pairs.add(sameCount);
				sameCount = 1;
			}
		}
		if (sameCount >= 2) {
			pairs.add(sameCount);
		}
		
		return toIntArray(pairs);
	}
	
	private int[] toIntArray(List<Integer> list) {
		int[] array = new int[list.size()]; 
		for (int i = 0; i < array.length; i++) {
			array[i] = list.get(i);
		}
		return array;
	}
	
	private int[] getSortedNumbers(Card[] cards) {
		int[] numbers = new int[cards.length];
		for (int i = 0; i < cards.length; i++) {
			numbers[i] = cards[i].getNumber();
		}
		Arrays.sort(numbers);
		return numbers;
	}
	
}
