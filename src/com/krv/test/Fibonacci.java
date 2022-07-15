package com.krv.test;

public class Fibonacci {

	public static void main(String[] args) {
		int n = 5;
		System.out.println(fibonacci(n));

	}

	private static int fibonacci(int n) {
		if (n == 0 || n == 1) {
			return n;
		} else if (n == 2) {
			return 1;
		}
		return fibonacci(n - 1) + fibonacci(n - 2);
	}

}
