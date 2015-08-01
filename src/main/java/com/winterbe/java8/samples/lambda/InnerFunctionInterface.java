package com.winterbe.java8.samples.lambda;

/**
 * @author Benjamin Winterberg
 */
public class InnerFunctionInterface {

	static int outerStaticNum;

	int outerNum;

	void testScopes() {
		int num = 1;
		FunctionInterface.Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);

		String convert = stringConverter.convert(2);
		System.out.println(convert); // 3

		FunctionInterface.Converter<Integer, String> stringConverter2 = (from) -> {
			outerNum = 13;
			return String.valueOf(from);
		};

		stringConverter2.convert(23);
		System.out.println(outerNum);// 13

		String[] array = new String[1];
		FunctionInterface.Converter<Integer, String> stringConverter3 = (from) -> {
			array[0] = "Hi there";
			return String.valueOf(from);
		};

		stringConverter3.convert(23);
		System.out.println(array[0]);// Hi there
	}

	public static void main(String[] args) {
		new InnerFunctionInterface().testScopes();
	}

}