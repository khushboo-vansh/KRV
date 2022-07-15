package com.krv.test;

import java.util.Arrays;
import java.util.List;

/**
 * @author Khushboo_Vansh
 *
 */
public class ReduceTest {

	public static void main(String[] args) {
		Integer[] arr1 = new Integer[] { 10, 11, 9, 8, 20, 5, 7, 7, 38 };
		List<Integer> l1 = Arrays.asList(arr1);
		l1.stream().reduce((x, y) -> x + y).ifPresent(s -> System.out.println("List to String: " + s));

		int sum = l1.stream().reduce(0, (x, y) -> x + y);
		System.out.println(sum);

	}
}
