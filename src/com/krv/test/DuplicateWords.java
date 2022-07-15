package com.krv.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DuplicateWords {

	public static void main(String[] args) {
		String test = "This sentense contains two works, one and two one";
		Set<String> duplicates = duplicateWords(test);
		System.out.println("output : " + duplicates);
	}

	private static Set<String> duplicateWords(String input) {
		if (input == null || input.isEmpty()) {
			Collections.emptySet();
		}
		Set<String> duplicates = new HashSet<>();
		Set<String> allWords = new HashSet<>();
		String[] words = input.split("\\s+");
		for (String word : words) {
			if (!allWords.contains(word)) {
				allWords.add(word);
			} else {
				duplicates.add(word);
			}
		}
		return duplicates;
	}

}
