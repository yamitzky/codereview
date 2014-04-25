package calculator;

/**
 * 分数を表す
 * @author a13456
 */
public class Fraction {
	/**
	 * 分子
	 */
	private int numerator;
	
	/**
	 * 分母 
	 */
	private int denominator;
	
	/**
	 * 分数を作るためのコンストラクタ
	 * 分母は1になる
	 * @param numerator 分数にしたい整数値
	 */
	public Fraction(int decimal) {
		this(decimal, 1);
	}
	
	/**
	 * 分数を作るためのコンストラクタ
	 * @param numerator 分子
	 * @param denominator 分母
	 */
	public Fraction(int numerator, int denominator) {
		if (denominator == 0) {
			throw new IllegalArgumentException("分母の値が不正です:" + denominator);
		}
		this.numerator = numerator;
		this.denominator = denominator;
		reduce();
	}
	
	public double toDouble() {
		return (double) numerator / denominator;
	}
	
	/**
	 * 分数への和算を提供する
	 * @param other 足す数 
	 * @return 和(this + other)
	 */
	public Fraction add(Fraction other) {
		int newNumerator = numerator * other.denominator +
				other.numerator * denominator;
		int newDenominator = other.denominator * denominator;
		return new Fraction(newNumerator, newDenominator);
	}
	
	/**
	 * 分数への減算を提供する
	 * @param other 引く数
	 * @return 差(this - other)
	 */
	public Fraction subtract(Fraction other) {
		return this.add(other.negate());
	}
	
	/**
	 * 分数への乗算を提供する
	 * @param multiplier 乗数
	 * @return 積(this * multiplier)
	 */
	public Fraction multiply(Fraction multiplier) {
		Fraction product = new Fraction(
				numerator * multiplier.numerator,
				denominator * multiplier.denominator);
		return product;
	}
	
	/**
	 * 分数への除算を提供する
	 * @param divisor 除数
	 * @return 商(this / divisor)
	 */
	public Fraction divide(Fraction divisor) {
		return this.multiply(divisor.inverse());
	}
	
	/**
	 * 分数を負にしたものを返す
	 * @return -this
	 */
	private Fraction negate() {
		return new Fraction(-numerator, denominator);
	}
	
	/**
	 * 分数を逆数にしたものを返す
	 * @return 1 / this
	 */
	private Fraction inverse() {
		return new Fraction(denominator, numerator);
	}
	
	/**
	 * 分数を約分する
	 */
	private void reduce() {
		int gcm = MathUtil.getGreatCommonDivisor(numerator, denominator);
		numerator /= gcm;
		denominator /= gcm;
		if (denominator < 0) {
			numerator = -numerator;
			denominator = -denominator;
		}
	}

	@Override
	public String toString() {
		if (denominator == 1) {
			return Integer.toString(numerator);
		}
		return numerator + " / " + denominator;
	}

	@Override
	public boolean equals(Object obj) {
		Fraction other = (Fraction) obj;
		return (numerator == other.numerator &&
				denominator == other.denominator);
	}
}
