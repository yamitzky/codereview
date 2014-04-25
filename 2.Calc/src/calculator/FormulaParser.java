package calculator;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * 文字列として与えられた式を構文木に変換するためのパーサー
 * 演算子順位構文解析のアルゴリズムで実装されている
 * nextで始まるprivateメソッドはtokenの位置が動かされる
 * @author a13456
 */
public class FormulaParser {
	/**
	 * トーカナイザー
	 * nextで始まるprivateメソッドを呼びだすことで、tokenの位置が移動する
	 */
	private StreamTokenizer tokenizer;
	
	/**
	 * パーサーを生成するためのコンストラクタ
	 * @param formula 計算式を表す文字列
	 */
	public FormulaParser(String formula) {
		tokenizer = getTokenizer(formula);
	}
	
	/**
	 * コンストラクタに与えた計算式文字列をパースして、構文木に変換する
	 * @return パースした構文木
	 * @throws IOException
	 */
	public CalculatableNode parseNode() throws IOException {
		CalculatableNode left = nextNode(); // expected number or parenthesis
		
		// 次のトークン(数字または括弧でくくられたノード)をくっつける
		return mergeNode(Operator.BEGIN, left);
	}
	
	private CalculatableNode mergeNode(final Operator criteria, CalculatableNode left) throws IOException {
		while (true) {
			// この関数は「演算子、数字(ノード)」の次に呼ばれるので、演算子であることが期待される
			// 例：1+2+3において、「(BEGIN),1」の後に呼ばれる
			// 例：*の後に+が呼ばれた場合、+以降は操作する必要がないので、そのまま返す
			// 例：(BEGIN)の後に(END)が呼ばれた場合、それ以降はトークンを持たないので、そのまま返す
			Operator currentOperator = nextOperator();
			if (criteria.priorThan(currentOperator)) {
				return left;
			}
			
			CalculatableNode currentNode = nextNode();
			
			// 来るべき演算子を先読みして、どちらを優先するかを決定する
			// 次の演算子の方が優先されるならば、マージして返却してもらう
			Operator rightOperator = nextOperator();
			tokenizer.pushBack();
			if (rightOperator.priorThan(currentOperator)) {
				currentNode = mergeNode(currentOperator, currentNode);
			}
			
			left = new OperationalNode(currentOperator, left, currentNode);
		}
	}
	
	private Operator nextOperator() throws IOException {
		int ttype = tokenizer.nextToken();
		
		if (ttype == StreamTokenizer.TT_EOF ||
				ttype == StreamTokenizer.TT_EOL) {
			return Operator.END;
		}
		if (ttype >= 0) {
			char sign = (char) tokenizer.ttype;
			if (sign == ')') {
				return Operator.END;
			}
			return Operator.parseOperator(sign);
		}
		throw new IllegalFormulaException("数式の終わり方が不正です");
	}
	
	private CalculatableNode nextNode() throws IOException {
		// expects number or "("
		int ttype;
		ttype = tokenizer.nextToken();
		if (ttype == StreamTokenizer.TT_NUMBER) {
			return new NumericalNode((int) tokenizer.nval);
		}
		if (ttype >= 0) {
			// sign character such as +-*/()
			char sign = (char) tokenizer.ttype;
			if (sign == '(') {
				CalculatableNode node = parseNode();
				return node;
			}
		}
		throw new IllegalFormulaException("数式が不正です");
	}
	
	private static StreamTokenizer getTokenizer(String formula) {
		StreamTokenizer tokenizer = new StreamTokenizer(new StringReader(formula));
		tokenizer.resetSyntax();
		tokenizer.parseNumbers();
		tokenizer.ordinaryChars('(', ')');
		tokenizer.ordinaryChar('+');
		tokenizer.ordinaryChar('-');
		tokenizer.ordinaryChar('*');
		tokenizer.ordinaryChar('/');
		tokenizer.whitespaceChars(' ', ' ');
		tokenizer.whitespaceChars('\t', '\t');
		return tokenizer;
	}
}
