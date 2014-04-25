package calculator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

public class NumericalNodeTest {

	@Test
	public void evalは分数値を返す() {
		NumericalNode node = new NumericalNode(3);
		assertThat(node.eval(), is(new Fraction(3)));
	}

}
