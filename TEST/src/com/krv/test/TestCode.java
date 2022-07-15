package com.krv.test;

import java.util.Arrays;
import java.util.List;

public class TestCode {

	public static void calculate(int a, int b) {
		System.out.println("Result is: " + (a + b));
	}

	public static void calculate(Integer a, Integer b) {
		System.out.println("Result is: " + (a - b));
	}

	public static void main(String[] args) {
		calculate(10, 5);
		String var1 = "1";
		String var2 = "1.05";
		if (isInteger(var1)) {
			// System.out.println("Integer");
		}
		if (isDouble(var2)) {
			// System.out.println("Double");
		}

		printTheCalculation(4, 1, 2, 3);

		String str1 = "String";
		String str2 = new String("String");
		String str3 = new String("String").intern();

		System.out.println(str1 == str2); // false
		System.out.println(str1 == str3); // false
		System.out.println(str2 == str3); // true

		System.out.println(str2.equals(str3)); // true -- wrong answer

		Integer[] arr = new Integer[] { 10, 11, 9, 8, 20, 5, 7, 7, 38 };

		List<Integer> l1 = Arrays.asList(arr);
		float avg = l1.stream().map(x -> x * x).filter(x -> (x > 100)).reduce(0, (a, b) -> (a + b) / 2);
		System.out.println(avg);

	}

	public static boolean isInteger(String strNum) {
		try {
			Integer.parseInt(strNum);
		} catch (NumberFormatException | NullPointerException ne) {
			return false;
		}
		return true;
	}

	public static boolean isDouble(String strNum) {
		try {
			Double.parseDouble(strNum);
		} catch (NumberFormatException | NullPointerException nfe) {
			return false;
		}
		return true;
	}

	public static void printTheCalculation(int factor, int... number) {
		int result = 0;
		for (int i = 0; i < number.length; i++) {
			result += (number[i] * factor);
		}
		System.out.println("Result is: " + result);
	}

}
