package calculator;

/**
 * 括弧が対応していないときに発生する例外
 * @author a13456
 */
public class IllegalFormulaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public IllegalFormulaException(String message) {
		super(message);
	}

}
