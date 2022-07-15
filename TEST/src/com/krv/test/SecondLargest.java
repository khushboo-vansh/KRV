package com.krv.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecondLargest {
	public static void main(String args[]) {
		int[] arr = new int[] { 2, 1, 4, 5, 3 };

		int sum = Arrays.stream(arr).sum();
		System.out.println("Sum : " + sum);

		Arrays.stream(arr).forEach(a -> System.out.print(a + " "));

		System.out.println(Arrays.stream(arr).allMatch(a -> a % 2 == 0));

		System.out.println("Average : " + Arrays.stream(arr).average());
		System.out.println("Max : " + Arrays.stream(arr).max());
		System.out.println("Min : " + Arrays.stream(arr).min());
		System.out.println("Reduce : " + Arrays.stream(arr).reduce((x, y) -> x + y));
		System.out.println("Average : " + Arrays.stream(arr).reduce(0, (x, y) -> (x + y) / 2));

		Integer[] arr1 = new Integer[] { 10, 11, 9, 8, 20, 5, 7, 7, 38 };
		List<Integer> l1 = Arrays.asList(arr1);

		System.out.println(
				"Second Largest: " + l1.stream().sorted(Comparator.reverseOrder()).limit(2).min(Integer::compare));

		System.out.println(
				"Third Largest: " + l1.stream().sorted(Comparator.reverseOrder()).limit(3).min(Integer::compare));

		// Write a Java 8 program to square the list of numbers and then filter out the
		// numbers greater than 100 and then find the average?

		System.out.println("Conditional Average "
				+ l1.stream().map(x -> x * x).filter(x -> (x > 100)).mapToDouble(a -> a).average());

		System.out.println("Conditional Average "
				+ l1.stream().map(x -> x * x).filter(x -> (x > 100)).collect(Collectors.averagingDouble(Double::new)));

		System.out.println(" ");
		System.out.println(Stream.of(-3, -2, -1, 0, 1, 2, 3).max(Math::max).get());

		System.out.println(Stream.of(-3, -2, -1, 0, 1, 2, 3).max(Integer::compare));
	}
}
