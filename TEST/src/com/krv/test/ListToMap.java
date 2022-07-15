package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListToMap {
	public static void main(String[] args) {

		List<Employee> employeeList = new ArrayList<>(
				Arrays.asList(new Employee(1, "A", 100), new Employee(2, "A", 200), new Employee(3, "B", 300),
						new Employee(4, "B", 400), new Employee(5, "C", 500), new Employee(6, "C", 600)));
		Map<Integer, Employee> employeesMap = employeeList.stream()
				.collect(Collectors.toMap(Employee::getId, Function.identity()));
		System.out.println(employeesMap);
	}
}
