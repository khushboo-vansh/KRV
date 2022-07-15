package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Khushboo_Vansh
 *
 */
class SimilarSearch {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		List<String> lines = new ArrayList<>();
		while (input.hasNextLine()) {
			String lineNew = input.nextLine();
			if (lineNew.isEmpty()) {
				break;
			}
			lines.add(lineNew);
		}
		groupSearchKeys(lines.get(0).trim(), lines.get(1).trim(), lines.get(2).trim());
		input.close();
	}

	/** The Solution Starts here **/
	public static void groupSearchKeys(String inputStrRaw, String inputStrKeyWords, String searchQuery) {

		String[] rawKeywords = inputStrRaw.toLowerCase().split("\\s");
		List<String> keywords = Arrays.asList(inputStrKeyWords.toLowerCase().split("\\s"));
		Map<String, List<String>> groupedMap = new HashMap<>();
		LinkedHashSet<String> withoutDuplicates = new LinkedHashSet<>(Arrays.asList(rawKeywords));
		List<String> keyWordsWithoutDuplicates = new ArrayList<>(withoutDuplicates);
		int[] intArr = new int[26];
		for (String keyword : keyWordsWithoutDuplicates) {
			Arrays.fill(intArr, 0);
			for (char ch : keyword.toCharArray()) {
				int index = ch - 'a';
				intArr[index]++;
			}

			StringBuilder keyBuilder = new StringBuilder("");
			for (int i = 0; i < 26; i++) {
				keyBuilder.append('#');
				keyBuilder.append(intArr[i]);
			}

			String key = keyBuilder.toString();
			if (!groupedMap.containsKey(key)) {
				groupedMap.put(key, new ArrayList<>());
			}
			groupedMap.get(key).add(keyword);
		}
		System.out.println("The grouped keywords:");
		for (Map.Entry<String, List<String>> entry : groupedMap.entrySet()) {
			List<String> group = entry.getValue();
			Collections.sort(group, String.CASE_INSENSITIVE_ORDER);
			System.out.println(group);
		}

		for (List<String> subGroup : new ArrayList<>(groupedMap.values())) {
			if (subGroup.contains(searchQuery.toLowerCase())) {
				subGroup.retainAll(keywords);
				System.out.println("The correct keyword for the search key " + searchQuery + " is " + subGroup.get(0));

			}
		}
	}

	/** Solution ends here **/

}