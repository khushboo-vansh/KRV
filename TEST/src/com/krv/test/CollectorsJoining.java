package com.krv.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CollectorsJoining {
	public static void main(String[] args) {
		List<String> words = Arrays.asList("A", "B", "C", "D");

		String joinedString1 = words.stream().collect(Collectors.joining()); // ABCD
		System.out.println(joinedString1);

		String joinedString2 = words.stream().collect(Collectors.joining(",")); // A,B,C,D
		System.out.println(joinedString2);

		String joinedString3 = words.stream().collect(Collectors.joining(",", "{", "}")); // {A,B,C,D}
		System.out.println(joinedString3);
	}
}
