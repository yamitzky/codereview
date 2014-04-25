package poker;

/**
 * トランプのCardを表すクラス
 * @author Mitsuki Ogasahara
 */
public class Card implements Comparable<Card> {
	enum Suit {
		NONE, // Jokerなどカードがスーツを持たないことを表す
		CLUBS, DIAMONDS, HEARTS, SPADES;
		public static final int NUM_OF_SUITS = 4;
	}
	
	/**
	 * Jokerなど、カードが数値を持たないことを表す定数
	 */
	public static final int NO_NUMBER = 0;
	public static final int NUMBER_MAX = 13;
	public static final int NUMBER_MIN = 1;
	
	/**
	 * カードに指定された数字(JokerならNO_NUMBER)
	 */
	private int number;
	
	/**
	 * カードのスーツ(JokerならSuit.NONE)
	 */
	private Suit suit;
	
	/**
	 * jokerを作るためのコンストラクタ
	 */
	public Card() {
		number = NO_NUMBER;
		suit = Suit.NONE;
	}
	
	/**
	 * 数値カードを作るためのコンストラクタ
	 * @param suit カードのスーツ(マーク)
	 * @param number カードの数字(1-13)
	 */
	public Card(Suit suit, int number) {
		if (suit == Suit.NONE) {
			throw new IllegalArgumentException("suitを指定する必要があります。");
		}
		if (number < NUMBER_MIN || number > NUMBER_MAX) {
			throw new IllegalArgumentException("numberの指定が不正です。(number：" + number + ")");
		}
		
		this.suit = suit;
		this.number = number;
	}
	
	/**
	 * @return カードがjokerならtrue
	 */
	public boolean getIsJoker() {
		return (number == NO_NUMBER && suit == Suit.NONE);
	}
	
	/**
	 * @return カードのマーク
	 */
	public Suit getSuit() {
		return suit;
	}
	
	/**
	 * @return カードのマークを日本語文字列にしたもの
	 */
	public String getSuitString() {
		if (suit == Suit.CLUBS) {
			return "クラブ";
		} else if (suit == Suit.DIAMONDS) {
			return "ダイヤ";
		} else if (suit == Suit.HEARTS) {
			return "ハート";
		} else if (suit == Suit.SPADES) {
			return "スペード";
		}
		return "";
	}
	
	/**
	 * @return カードの数字
	 */
	public int getNumber() {
		return number;
	}
	
	/**
	 * @return カードの数をトランプで使われる文字列にしたもの
	 */
	public String getNumberString() {
		if (number == 1) {
			return "A";
		} else if (number == 13) {
			return "K";
		} else if (number == 12) {
			return "Q";
		} else if (number == 11) {
			return "J";
		} else {
			return Integer.toString(number);
		}
	}
	
	@Override
	public String toString() {
		if (getIsJoker()) {
			return "ジョーカー";
		}
		return getSuitString() + "の" + getNumberString();
	}

	@Override
	public boolean equals(Object obj) {
		Card card = (Card) obj;
		return (suit == card.getSuit()) && (number == card.getNumber());
	}

	@Override
	public int compareTo(Card card) {
		// are they joker?
		if (getIsJoker() || card.getIsJoker()) {
			Boolean isJoker = new Boolean(getIsJoker());
			return isJoker.compareTo(card.getIsJoker());
		}
		
		// same suit?
		if (suit == card.getSuit()) {
			return number - card.getNumber();
		}
		
		// which suit is greater?
		return suit.compareTo(card.getSuit());
	}
}
