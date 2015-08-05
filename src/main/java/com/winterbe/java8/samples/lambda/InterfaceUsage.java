package com.winterbe.java8.samples.lambda;

/**
 * @author Benjamin Winterberg
 */
public class InterfaceUsage {

	public static void main(String[] args) {
		Formula formula1 = new Formula() {
			@Override
			public double calculate(int a) {
				return sqrt(a * 100);
			}
		};

		double result1 = formula1.calculate(100); // 100.0
		double result2 = formula1.sqrt(-23); // 0.0
		int result3 = Formula.positive(-4); // 0.0

		// Formula formula2 = (a) -> sqrt(a * 100);

		System.out.println(result1);
		System.out.println(result2);
		System.out.println(result3);
	}
}

/**
 * can define function in a interface class; function can be static.
 */
interface Formula {

	double calculate(int a);

	default double sqrt(int a) {
		return Math.sqrt(positive(a));
	}

	static int positive(int a) {
		return a > 0 ? a : 0;
	}
}