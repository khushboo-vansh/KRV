package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MergingTwoStreams {

	public static void main(String[] args) {
		Stream<Integer> firstStream = Stream.of(1, 2, 3, 4);
		Stream<Integer> secondStream = Stream.of(3, 4, 5, 6);

		Stream<Integer> resultingStream = Stream.concat(firstStream, secondStream);
		System.out.println(resultingStream.collect(Collectors.toList()));

		Stream<Integer> stream1 = Stream.of(1, 2, 3, 4);
		Stream<Integer> stream2 = Stream.of(3, 4, 5, 6);
		Stream<Integer> distictElements = Stream.concat(stream1, stream2).distinct();
		System.out.println(distictElements.collect(Collectors.toList()));

		Stream<Integer> first = Stream.of(1, 2);
		Stream<Integer> second = Stream.of(3, 4);
		Stream<Integer> third = Stream.of(5, 6);
		Stream<Integer> fourth = Stream.of(7, 8);

		Stream<Integer> output = Stream.concat(first, Stream.concat(second, Stream.concat(third, fourth)));
		System.out.println(output.collect(Collectors.toList()));

		List<Employee> employeeList1 = new ArrayList<>(
				Arrays.asList(new Employee(1, "A", 100), new Employee(2, "A", 200), new Employee(3, "B", 300),
						new Employee(4, "B", 400), new Employee(5, "C", 500), new Employee(6, "C", 600)));

		List<Employee> employeeList2 = new ArrayList<>(
				Arrays.asList(new Employee(1, "A", 100), new Employee(2, "A", 200), new Employee(7, "G", 300),
						new Employee(4, "B", 400), new Employee(8, "H", 500), new Employee(9, "K", 600)));

		// Removed duplicates using Set
		Set<Employee> employeeStream = Stream.concat(employeeList1.stream(), employeeList2.stream())
				.sorted(Comparator.comparing(Employee::getId)).collect(Collectors.toSet());
		employeeStream.forEach(e -> System.out.println(e.getId() + " " + e.getName() + " " + e.getAge()));

		System.out.println("--------------------------------------------");

		// Removed duplicates with distinct
		List<Employee> eList = Stream.concat(employeeList1.stream(), employeeList2.stream()).distinct()
				.collect(Collectors.toList());
		eList.forEach(e -> System.out.println(e.getId() + " " + e.getName() + " " + e.getAge()));

		System.out.println("--------------------------------------------");

		List<Employee> tList = Stream.concat(employeeList1.stream(), employeeList2.stream())
				.sorted(Comparator.comparing(Employee::getId)).collect(Collectors.toList());
		tList.forEach(e -> System.out.println(e.getId() + " " + e.getName() + " " + e.getAge()));

	}

}
