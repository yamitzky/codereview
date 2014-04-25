package calculator;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.*;

import org.junit.Test;

public class OperationalNodeTest {

	@Test
	public void 子ノードを2つ持つ() {
		CalculatableNode num_3 = new NumericalNode(3);
		CalculatableNode num_5 = new NumericalNode(5);
		OperationalNode root = new OperationalNode("+", num_3, num_5);
		
		CalculatableNode[] children = root.getChildren();
		assertThat(children.length, is(2));
		assertThat(children[0], notNullValue());
		assertThat(children[1], notNullValue());
 	}
	
	@Test
	public void 計算結果が正しい() {
		Fraction frac_1_3 = new Fraction(1, 3);
		Fraction frac_1_4 = new Fraction(1, 4);
		OperationalNode root = new OperationalNode(
				"+",
				new NumericalNode(frac_1_3),
				new NumericalNode(frac_1_4));
		
		Fraction frac_7_12 = new Fraction(7, 12);
		assertThat(root.eval(), is(frac_7_12));
	}
	
	@Test
	public void 演算子を取得できる() {
		OperationalNode node = new OperationalNode(
				"+", new NumericalNode(1), new NumericalNode(3));
		assertThat(node.getOperator(), is(Operator.ADD));
	}
}
