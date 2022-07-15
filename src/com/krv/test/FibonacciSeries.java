package com.krv.test;

import java.util.ArrayDeque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FibonacciSeries {

	public static void main(String[] args) {
		Stream.iterate(new int[] { 0, 1 }, n -> new int[] { n[1], n[0] + n[1] }).limit(20).map(n -> n[0])
				.forEach(x -> System.out.println(x));

		int sum = Stream.iterate(new int[] { 0, 1 }, n -> new int[] { n[1], n[0] + n[1] }).limit(20).map(n -> n[0])
				.mapToInt(n -> n).sum();
		System.out.println("Fibonacci Sum : " + sum);

		Stream.of("1", "2", "20", "3").collect(Collectors.toCollection(ArrayDeque::new)) // or LinkedList
				.descendingIterator().forEachRemaining(System.out::println);

		int i = IntStream.range(1, 6).reduce((a, b) -> a * b).orElse(-1);
		System.out.println("Product of n numbers : " + i);
	}

}
