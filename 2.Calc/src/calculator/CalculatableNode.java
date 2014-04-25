package calculator;

public abstract class CalculatableNode {
	/**
	 * ノードの計算結果を返す
	 * 子ノードを持つ場合は、子ノードの結果も計算される
	 * @return ノードの計算結果
	 */
	public abstract Fraction eval();
}
