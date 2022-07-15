package com.krv.test;

public class Factorial {

	public static void main(String[] args) {
		System.out.println(factorial(10));

		String str1 = "String";
		String str2 = new String("String");
		String str3 = new String("String").intern();

		System.out.println(str1 == str2);
		System.out.println(str1 == str3);
		System.out.println(str2 == str3);

		System.out.println(str2.equals(str3));

	}

	private static Integer factorial(int i) {
		Integer fact = 1;
		if (i == 1) {
			return fact;
		} else {
			return fact * factorial(i - 1);
		}
	}

}
