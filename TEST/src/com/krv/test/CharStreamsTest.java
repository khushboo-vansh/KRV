package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CharStreamsTest {

	public static void main(String[] args) {
		IntStream stream = "12345_abcdefg".chars();
		stream.forEach(p -> System.out.println(p));

		Stream<String> st = Stream.of("A$B$C".split("\\$"));
		st.forEach(p -> System.out.println(p));

		List<String> memberNames = new ArrayList<>();
		memberNames.add("Amitabh");
		memberNames.add("Shekhar");
		memberNames.add("Aman");
		memberNames.add("Rahul");
		memberNames.add("Shahrukh");
		memberNames.add("Salman");
		memberNames.add("Yana");
		memberNames.add("Lokesh");
		memberNames.stream().filter((s) -> s.startsWith("A")).map(String::toUpperCase).forEach(System.out::println);

		memberNames.stream().sorted().map(String::toUpperCase).forEach(System.out::println);

		long totalMatched = memberNames.stream().filter((s) -> s.startsWith("A")).count();
		System.out.println(totalMatched);

		Optional<String> reduced = memberNames.stream().reduce((s1, s2) -> s1 + "#" + s2);
		reduced.ifPresent(System.out::println);

		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> newList = list.stream().peek(System.out::println).collect(Collectors.toList());
		System.out.println(newList);

	}

}
