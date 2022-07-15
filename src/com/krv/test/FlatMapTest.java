package com.krv.test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FlatMapTest {

	public static void main(String[] args) {
		String[][] data = new String[][] { { "a", "b" }, { "c", "d" }, { "e", "f" } };
		System.out.println(Arrays.asList(data));
		List<String> list = Arrays.stream(data).flatMap(x -> Arrays.stream(x)).collect(Collectors.toList());
		System.out.println(list);

	}

}
