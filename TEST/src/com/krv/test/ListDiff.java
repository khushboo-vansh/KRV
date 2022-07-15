package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class ListDiff {
	public static void main(String args[]) {

		// Chandu;Java developer;Java8,Spring Boot,REST API,Mongo DB,Micro service
		// spilt arr[0] --> name
		// arr[1] --? skill name
		// arr[2] --> skills

		// split arry 2 by , --? skills array []

		// Arrays.asList(arr)

		// match(skilllist, candidateskill);

		List<String> javaSkills = new ArrayList<>(Arrays.asList("Spring", "SpringBoot", "REST"));
		List<String> candidateSkills = new ArrayList<>(Arrays.asList("Spring", "SpringBoot", "Hibernate"));

		List<String> temp = new ArrayList<String>(javaSkills);
		temp.retainAll(candidateSkills);

		System.out.println(temp);

		if (temp.size() == javaSkills.size()) {
			System.out.println("Match");
		} else {
			System.out.println("Not Match");
		}

		HashMap<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "test");

		map.forEach((k, v) -> System.out.println("k :" + k + ", v:" + v));

		for (Integer key : map.keySet()) {
			System.out.println("key : " + key + " value:" + map.get(key));
		}

	}

}
