package calculator;

import java.io.IOException;

public class Calculator {
	public static void main(String[] args) {
		boolean doubleMode = false;
		// might generate exception
		if (args[0].equals("-d")) {
			doubleMode = true;
		}
		
		int start = (doubleMode) ? 1 : 0; // -dオプションが指定されている場合は1以降
		StringBuilder formulaBuilder = new StringBuilder();
		for (int i = start; i < args.length; i++) {
			formulaBuilder.append(args[i]);
		}
		String formula = formulaBuilder.toString();
		
		FormulaParser parser = new FormulaParser(formula);
		CalculatableNode root;
		try {
			root = parser.parseNode();
		} catch (IOException e) {
			e.printStackTrace();
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
