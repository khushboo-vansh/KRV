package com.krv.test;

import java.util.HashSet;
import java.util.Set;

public class LargestCommonSubString {

	public static void main(String[] args) {
		String str1 = "gheese", str2 = "heet";
		Set<String> output = commonSubstrings(str1, str2);
		System.out.println(output);
	}

	private static Set<String> commonSubstrings(String str1, String str2) {
		int[][] table = new int[str1.length()][str2.length()];
		int longest = 0;
		Set<String> result = new HashSet<>();
		for (int i = 0; i < str1.length(); i++) {
			for (int j = 0; j < str2.length(); j++) {
				if (str1.charAt(i) != str2.charAt(j)) {
					continue;
				}
				table[i][j] = (i == 0 || j == 0) ? 1 : 1 + table[i - 1][j - 1];
				if (table[i][j] > longest) {
					longest = table[i][j];
					result.clear();
				}
				if (table[i][j] == longest) {
					result.add(str1.substring(i - longest + 1, i + 1));
				}
			}
		}
		return result;
	}

}
