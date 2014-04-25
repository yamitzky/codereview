package calculator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import calculator.MathUtil;

public class MathUtilTest {

	@Test
	public void 最大公約数を返す() {
		assertThat(MathUtil.getGreatCommonDivisor(24, 40),
				is(8));
		assertThat(MathUtil.getGreatCommonDivisor(1, 10),
				is(1));
		assertThat(MathUtil.getGreatCommonDivisor(13, 12),
				is(1));
	}

}
