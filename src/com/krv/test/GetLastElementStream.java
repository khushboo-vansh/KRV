package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GetLastElementStream {

	public static void main(String[] args) {
		Stream<Integer> stream = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9).stream();
		Integer lastElement = stream.reduce((first, second) -> second).orElse(-1);
		System.out.println(lastElement);

		List<String> memberNames = new ArrayList<>();
		memberNames.add("Amitabh");
		memberNames.add("Shekhar");
		memberNames.add("Aman");
		memberNames.add("Rahul");
		memberNames.add("Shahrukh");
		memberNames.add("Salman");
		memberNames.add("Yana");
		memberNames.add("Lokesh");

		Stream<Integer> emptyStream = Stream.empty();

		// Return -1 if stream is empty
		Integer lastElement1 = emptyStream.reduce((first, second) -> second).orElse(-1);
		System.out.println(lastElement1); // -1

		// Throw IllegalStateException if stream is empty
		Integer lastElement2 = emptyStream.reduce((first, second) -> second)
				.orElseThrow(() -> new IllegalStateException("no last element"));
	}

}
