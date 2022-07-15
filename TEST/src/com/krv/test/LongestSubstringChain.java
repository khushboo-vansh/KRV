package com.krv.test;

import java.util.List;

/**
 * @author Khushboo_Vansh
 *
 */
public class LongestSubstringChain {
	public static void main(String[] args) {
		// try (Scanner scan = new Scanner(System.in)) {
		// String inputStrings = scan.next();
		// List<String> strings = Arrays.asList(inputStrings.split(","));
		// System.out.println(longestStringChain(strings));

		// }
		String str = "abcdef";
		// Stream.of(str).map(null)
		for (int i = 2; i < str.length(); i++) {
			System.out.println(str.substring(0, str.length() - i));
		}
	}

	private static List<String> longestStringChain(List<String> strings) {
		for (String str : strings) {
			if (str.chars().anyMatch(Character::isDigit)) {
				System.out.println(str);
				// strings.remove(str);
			}
		}
		return strings;
	}

}
