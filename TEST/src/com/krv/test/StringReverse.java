package com.krv.test;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringReverse {
	public static void main(String[] args) {
		String str = "abcd efg";

		String abc = Arrays.asList(str).stream().map(s -> new StringBuilder(s).reverse().toString())
				.collect(Collectors.toList()).get(0);
		System.out.println(abc);

		int len = str.length();
		IntStream.range(0, len).map(i -> len - 1 - i).mapToObj(j -> str.charAt(j)).forEach(System.out::print);
	}

}
