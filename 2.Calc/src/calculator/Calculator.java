package calculator;

import java.io.IOException;

public class Calculator {
	public static void main(String[] args) {
		boolean doubleMode = false;
		StringBuilder formulaBuilder = new StringBuilder();
		for (String arg : args) {
			if (arg.equals("-d")) {
				doubleMode = true;
			} else {
				formulaBuilder.append(arg);
			}
		}
		String formula = formulaBuilder.toString();
		
		if (formula.length() == 0) {
			System.err.println("数式を指定してください。");
			System.err.println("  例) java -jar calculator.jar \"2 * 3 / (3 + 2)\"");
			System.err.println("  例) java -jar calculator.jar -d \"2 * 3 / (3 + 2)\"");
			return;
		}
		
		FormulaParser parser = new FormulaParser(formula);
		CalculatableNode root;
		try {
			root = parser.parseNode();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} catch (IllegalFormulaException e) {
			System.err.println(e.getMessage());
			return;
		}
		
		Fraction fraction = root.eval();
		if (doubleMode) {
			System.out.println(fraction.toDouble());
		} else {
			System.out.println(fraction);
		}
		
	}
}
