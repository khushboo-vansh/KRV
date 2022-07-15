package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeTest {

	public static void main(String[] args) {
		Employee e1 = new Employee("Khushboo", "F", 35, 25000d);
		Employee e2 = new Employee("Aashutosh", "M", 10, 26000d);
		Employee e3 = new Employee("Anjali", "F", 36, 35000d);
		Employee e4 = new Employee("Ram", "M", 40, 45000d);
		Employee e5 = new Employee("Ram", "M", 40, 46000d);

		List<Employee> eList = new ArrayList<Employee>(Arrays.asList(e1, e2, e3, e4, e5));

		eList.stream().filter(e -> e.getSalary() > 25000).forEach(e -> System.out.println(e.getName()));

		System.out.println("------------------------------------------------");

		eList.stream().sorted(Comparator.comparing(Employee::getAge))
				.forEach(e -> System.out.println(e.getName() + " - " + e.getAge()));

		System.out.println("------------------------------------------------");

		eList.stream().sorted(Comparator.comparing(Employee::getAge).reversed())
				.forEach(e -> System.out.println(e.getName() + " - " + e.getAge()));

		System.out.println("------------------------------------------------");

		eList.stream().sorted(Comparator.comparing(Employee::getAge).thenComparing(Employee::getSalary))
				.forEach(e -> System.out.println(e.getName() + " - " + e.getAge() + " - " + e.getSalary()));

		System.out.println("------------------------------------------------");

		eList.stream().sorted(Comparator.comparing(Employee::getAge)).limit(1)
				.forEach(e -> System.out.println("Min age : " + e.getName() + " - " + e.getAge()));

		System.out.println("------------------------------------------------");

		eList.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).limit(1)
				.forEach(e -> System.out.println("Max age : " + e.getName() + " - " + e.getAge()));

		Optional<Employee> maxAge = eList.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).limit(1)
				.findFirst();

		eList.stream().sorted(Comparator.comparing(Employee::getAge).reversed()).limit(1).collect(Collectors.toList());
		System.out.println("Max Age: " + maxAge.get().getName());
		System.out.println("------------------------------------------------");

		// average of Employee salaries with DoubleStream average method
		double avSalary = eList.stream().mapToDouble(Employee::getSalary).average().getAsDouble();
		System.out.println("Average of Employees' salaries: " + avSalary);

		System.out.println("------------------------------------------------");

		// Average Age
		double avAge = eList.stream().mapToDouble(Employee::getAge).average().getAsDouble();
		System.out.println("Average of Employees' age: " + avAge);

		// Max Age
		int max = eList.stream().mapToInt(Employee::getAge).max().getAsInt();
		System.out.println("Maximum Employee age : " + max);
	}

}
