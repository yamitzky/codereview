package calculator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class FormulaParserTest {
	
	@Test
	public void 構文木が正しく作られる() throws IOException {
		FormulaParser parser = new FormulaParser("2/3*(3+4)+2");
		OperationalNode root = (OperationalNode) parser.parseNode();
//		OperationalNode root = (OperationalNode) FormulaParser.parse(
//				new String[]{"2", "/", "3", "*", "(", "3", "+", "4", ")", "+", "2"});
		
		/* + 
		 * ├── *
		 * │   ├── /
		 * │   │   ├── 2
		 * │   │   └── 3
		 * │   └── +
		 * │       ├── 3
		 * │       └── 4
		 * └── 2
		 */
		
		assertThat(root.getOperator(), is(Operator.ADD));
		
		// leaf_0 for 0th leaf of root
		OperationalNode leaf_0 = (OperationalNode) root.getChildren()[0];
		NumericalNode leaf_1 = (NumericalNode) root.getChildren()[1];
		assertThat(leaf_0.getOperator(), is(Operator.MULTIPLY));
		assertThat(leaf_1.eval(), is(new Fraction(2)));
		
		// leaf_0_0 for 0th leaf of 0th leaf of root
		OperationalNode leaf_0_0 = (OperationalNode) leaf_0.getChildren()[0];
		OperationalNode leaf_0_1 = (OperationalNode) leaf_0.getChildren()[1];
		assertThat(leaf_0_0.getOperator(), is(Operator.DIVIDE));
		assertThat(leaf_0_1.getOperator(), is(Operator.ADD));
		
		NumericalNode leaf_0_1_0 = (NumericalNode) leaf_0_1.getChildren()[0];
		NumericalNode leaf_0_1_1 = (NumericalNode) leaf_0_1.getChildren()[1];
		assertThat(leaf_0_1_0.eval(), is(new Fraction(3)));
		assertThat(leaf_0_1_1.eval(), is(new Fraction(4)));
		
		// Omitted assertions for leaf_0_0_0 and leaf_0_0_1  
	}
	
	@Test
	public void 数字のみの構文木が正しく作られる() throws IOException {
		FormulaParser parser = new FormulaParser("2");
		NumericalNode root = (NumericalNode) parser.parseNode();

		assertThat(root.eval(), is(new Fraction(2)));
	}
	
	@Test
	public void 数字と括弧のみの構文木が正しく作られる() throws IOException {
		FormulaParser parser = new FormulaParser("(3)");
		NumericalNode root = (NumericalNode) parser.parseNode();
		assertThat(root.eval(), is(new Fraction(3)));
	}

}
