package calculator;

/**
 * 数値を表すノード
 * 子ノードは持たず、evalの結果は数値ノードの持つ数値そのものになる
 * @author a13456
 */
public class NumericalNode extends CalculatableNode {
	/**
	 * ノードの持つ数値。evalの結果は必ずfractionを返す
	 */
	Fraction fraction;

	/**
	 * 数値ノードのためのコンストラクタ
	 * @param number 保持する数値
	 */
	public NumericalNode(int number) {
		fraction = new Fraction(number);
	}
	
	/**
	 * 数値ノードのためのコンストラクタ
	 * @param fraction 保持する分数値
	 */
	public NumericalNode(Fraction fraction) {
		this.fraction = fraction;
	}

	@Override
	public String toString() {
		return "NumericalNode [fraction=" + fraction + "]";
	}

	@Override
	public Fraction eval() {
		return fraction;
	}
	
}
