package com.krv.test;

import java.util.stream.IntStream;

public class PrimeTest {

	public static void main(String[] args) {
		int num = 121;
		boolean isPrime = true;
		for (int i = 2; i < num / 2; i++) {
			if (num % i == 0) {
				System.out.println("NOT PRIME");
				isPrime = false;
				break;
			}

		}
		if (isPrime) {
			System.out.println("PRIME");
		}

		System.out.println(isPrime(num) ? "PRIME" : "NOT PRIME");
		printPrime(10);
	}

	private static boolean isPrime(int num) {
		return !IntStream.rangeClosed(2, num / 2).anyMatch(i -> num % i == 0);
	}

	private static void printPrime(int count) {
		int i = 1;
		int num = 3;
		System.out.print(1 + " ");
		while (i < count) {
			if (isPrime(num)) {
				System.out.print(num + " ");
				num++;
				i++;
			} else {
				num++;
			}
		}
	}

}
