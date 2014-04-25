package calculator;

/**
 * 演算子を表すノード
 * 必ず２つの子を持つ 
 * @author a13456
 */
public class OperationalNode extends CalculatableNode {
	private Operator operator;
	private CalculatableNode[] children;
	
	/**
	 * 演算子ノードのためのコンストラクタ
	 * @param operator 演算子の記号("+", "-", "*", "/")
	 * @param left
	 * @param right
	 */
	public OperationalNode(String operator, CalculatableNode left, CalculatableNode right) {
		this.operator = Operator.parseOperator(operator);
		children = new CalculatableNode[]{left, right};
	}
	
	/**
	 * 演算子ノードのためのコンストラクタ
	 * @param operator 演算子
	 * @param left
	 * @param right
	 */
	public OperationalNode(Operator operator, CalculatableNode left, CalculatableNode right) {
		this.operator = operator;
		children = new CalculatableNode[]{left, right};
	}
	
	/**
	 * 子ノードのためのgetter
	 * @return 子ノード
	 */
	public CalculatableNode[] getChildren() {
		return children;
	}
	
	/**
	 * 演算子を取得するためのgetter
	 * @return ノードが持つ演算子
	 */
	public Operator getOperator() {
		return operator;
	}

	@Override
	public Fraction eval() {
		Fraction left = children[0].eval();
		Fraction right = children[1].eval();
		// リフレクションでもいい気がするけど、演算子の数が少ないのでif節にする
		// 演算子の数が増えたらテストコード込みでリフレクションを検討する
		if (operator == Operator.ADD) {
			return left.add(right);
		} else if (operator == Operator.SUBTRACT) {
			return left.subtract(right);
		} else if (operator == Operator.MULTIPLY) {
			return left.multiply(right);
		} else if (operator == Operator.DIVIDE) {
			return left.divide(right);
		}
		return null; // コンストラクタでチェックしているので、呼ばれることはない
	}
}
