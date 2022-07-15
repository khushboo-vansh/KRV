package com.krv.test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MapTransformations {

	public static void main(String[] args) {
		Map<String, String> myMap = new HashMap<String, String>();
		Map<String, String> sortedMap = new LinkedHashMap<String, String>();
		myMap.put("5", "Khushboo");
		myMap.put("4", "Aashutosh");
		myMap.put("1", "Agnus");
		myMap.put("2", "Aurora");
		myMap.put("3", "Sheeja");
		myMap.entrySet().stream().forEach(a -> System.out.println(a.getKey() + " - " + a.getValue()));

		myMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);

		myMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);

		sortedMap = myMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).collect(Collectors
				.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
		System.out.println("Map Sorted by values : " + sortedMap);

		sortedMap = myMap.entrySet().stream().sorted(Map.Entry.comparingByKey()).collect(Collectors
				.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, HashMap::new));
		System.out.println("Map Sorted by keys : " + sortedMap);

		// Print first 10 even numbers
		Stream.iterate(0, n -> n + 2).limit(10).forEach(x -> System.out.println(x));

		// Print first 10 odd numbers
		Stream.iterate(1, n -> n + 2).limit(10).forEach(x -> System.out.println(x));
	}

}
