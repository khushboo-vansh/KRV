package com.krv.test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ReverseString {

	public static void main(String[] args) {
		String str = "Khushboo";
		StringBuilder reverse = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			reverse.append(str.charAt(i));
		}
		System.out.println(reverse);

		System.out.println(
				Stream.of(str).map(x -> new StringBuilder(x).reverse().toString()).collect(Collectors.joining("")));
	}

}
