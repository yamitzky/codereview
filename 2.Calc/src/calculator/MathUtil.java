package calculator;

public class MathUtil {
	/**
	 * ２つの数値の最大公約数を探索する
	 * @param a 数値
	 * @param b 別の数値
	 * @return aとbの最大公約数
	 */
	public static int getGreatCommonDivisor(int a, int b) {
		int greater = Math.max(a, b); // m, will be GCM
		int less = Math.min(a, b); // n
		
		while (less != 0) {
			int tmp = less;
			less = greater % less;
			greater = tmp;
		}
		
		return greater;
	}
}
