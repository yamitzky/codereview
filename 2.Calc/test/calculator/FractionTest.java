package calculator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

import calculator.Fraction;

public class FractionTest {

	@Test
	public void 小数にできる() {
		Fraction frac_3 = new Fraction(3);
		assertThat(frac_3.toDouble(), is(3.0));
		
		Fraction frac_2_3 = new Fraction(2, 3);
		assertThat(frac_2_3.toDouble(), is(2.0 / 3));
		
		Fraction frac_1_2 = new Fraction(2, 4);
		assertThat(frac_1_2.toDouble(), is(0.5));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 分母に0を指定すると例外が送出される() {
		new Fraction(1, 0);
	}
	
	@Test
	public void 分子と分母が等しい分数は等しい() {
		Fraction frac_1_m3 = new Fraction(1, -3);
		Fraction frac_m1_3 = new Fraction(-1, 3);
		assertThat(frac_1_m3.equals(frac_m1_3), is(true));
		
		Fraction frac_m1_m3 = new Fraction(-1, -3);
		Fraction frac_1_3 = new Fraction(1, 3);
		assertThat(frac_m1_m3.equals(frac_1_3), is(true));
	}
	
	@Test
	public void 約分すると等しい分数は等しい() {
		Fraction frac_2_6 = new Fraction(2, 6);
		Fraction frac_1_3 = new Fraction(1, 3);
		assertThat(frac_2_6.equals(frac_1_3), is(true));
	}
	
	@Test
	public void 分母か分子が等しくない分数は等しくない() {
		Fraction frac_1_2 = new Fraction(1, 2);
		Fraction frac_1_3 = new Fraction(1, 3);
		assertThat(frac_1_2.equals(frac_1_3), is(false));
	}
	
	@Test
	public void 和算ができる() {
		Fraction frac_5_6 = new Fraction(5, 6);
		Fraction frac_3_8 = new Fraction(3, 8);
		Fraction frac_29_24 = new Fraction(29, 24);
		assertThat(frac_5_6.add(frac_3_8), is(frac_29_24));
	}
	
	@Test
	public void 減算ができる() {
		Fraction frac_3_8 = new Fraction(3, 8);
		Fraction frac_5_6 = new Fraction(5, 6);
		Fraction frac_m11_24 = new Fraction(-11, 24);
		assertThat(frac_3_8.subtract(frac_5_6), is(frac_m11_24));		
	}
	
	@Test
	public void 乗算ができる() {
		Fraction frac_3_8 = new Fraction(3, 8);
		Fraction frac_5_6 = new Fraction(5, 6);
		Fraction frac_5_16 = new Fraction(5, 16);
		assertThat(frac_3_8.multiply(frac_5_6), is(frac_5_16));		
	}
	
	@Test
	public void 除算ができる() {
		Fraction frac_3_8 = new Fraction(3, 8);
		Fraction frac_5_6 = new Fraction(5, 6);
		Fraction frac_9_20 = new Fraction(9, 20);
		assertThat(frac_3_8.divide(frac_5_6), is(frac_9_20));		
	}
}
