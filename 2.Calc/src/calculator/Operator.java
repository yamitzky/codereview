package calculator;

/**
 * 演算子を表す列挙型
 * @author a13456
 */
enum Operator {
	/**
	 * 
	 */
	END("", -1),
	
	/**
	 * 
	 */
	BEGIN("", 0),
	
	/**
	 * 和算
	 */
	ADD("+", 10),
	
	/**
	 * 減算 
	 */
	SUBTRACT("-", 10),
	
	/**
	 * 乗算 
	 */
	MULTIPLY("*", 20),
	
	/**
	 * 除算 
	 */
	DIVIDE("/", 20);
	
	/**
	 * 演算子を表す記号
	 */
	private String symbol;
	
	private int precedence;
	
	private Operator(String symbol, int precedence) {
		this.symbol = symbol;
		this.precedence = precedence;
	}
	
	/**
	 * 記号の文字列をパースして対応する演算子を返す
	 * @param symbol 演算子を表す記号
	 * @return 演算子を表すOperator型のインスタンス
	 */
	public static Operator parseOperator(String symbol) {
		for (Operator operator : Operator.values()) {
			if (operator.symbol.equals(symbol)) {
				return operator;
			}
		}
		throw new IllegalArgumentException("演算子が不正です。:" + symbol);
	}
	
	/**
	 * 記号の文字列をパースして対応する演算子を返す
	 * @param symbol 演算子を表す記号
	 * @return 演算子を表すOperator型のインスタンス
	 */
	public static Operator parseOperator(char symbol) {
		return parseOperator(Character.toString(symbol));
	}
	
	public boolean priorThan(Operator o) {
		return precedence > o.precedence;
	}
}
