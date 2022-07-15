package com.krv.test;

import java.util.stream.IntStream;

public class Palindrome {

	public static void main(String[] args) {
		System.out.println(checkPalindrome("abcdcba"));
		System.out.println(checkPalindromeStreams("abcdcba"));
		System.out.println(checkPalindromeByReversal("abcdcba"));
		System.out.println(checkPalindromeNumber(1234321));

	}

	private static boolean checkPalindromeByReversal(String str) {
		return !(new StringBuilder(str).reverse().equals(str));
	}

	private static boolean checkPalindromeNumber(int num) {
		String str = new Integer(num).toString();
		return !(new StringBuilder(str).reverse().equals(str));

	}

	private static boolean checkPalindrome(String str) {
		char[] charArr = str.toCharArray();
		boolean isPlaindrome = true;
		int len = charArr.length / 2;
		for (int i = 0; i < len; i++) {
			if (charArr[i] == charArr[charArr.length - 1 - i]) {
				continue;
			} else {
				isPlaindrome = false;
				break;
			}
		}
		return isPlaindrome;
	}

	private static boolean checkPalindromeStreams(String str) {
		return IntStream.range(0, str.length() / 2).noneMatch(i -> str.charAt(i) != str.charAt(str.length() - 1 - i));
	}

}
