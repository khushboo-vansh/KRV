package com.krv.test;

public class test3 {

	public static void calculate(int a, int b) {
		System.out.println("Result is: " + (a + b));
	}

	public static void calculate(Integer a, Integer b) {
		System.out.println("Result is: " + (a - b));
	}

	public static void main(String[] args) {

		calculate(10, 5);

	}

}
