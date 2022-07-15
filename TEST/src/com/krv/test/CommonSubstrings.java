package com.krv.test;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class CommonSubstrings {

	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		findCommonSubStr(input);
		sc.close();
	}

	/** Solution Starts here : Starts removable code **/
	public static void findCommonSubStr(String input) {
		String arr[] = input.trim().toLowerCase().split("\\s");
		String s = arr[0];
		int len = s.length();
		String res = "";
		Set<String> commons = new TreeSet<String>();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {
				String common = s.substring(i, j);
				int k = 1;
				for (k = 1; k < arr.length; k++) {
					if (!arr[k].contains(common)) {
						break;
					}
				}
				if (k == arr.length) {
					if (common.length() >= 3) {
						commons.add(common);
					}
					if (common.length() >= 3 && res.length() < common.length()) {
						res = common;
					}
				}
			}
		}
		if (commons.isEmpty()) {
			System.out.println("NO COMMON STRINGS AVAILABLE");
		} else {
			System.out.println("Longest Common Substring : " + res);
			if (commons.size() > 1) {
				commons.remove(res);
				System.out.print("Common Substrings : ");
				commons.forEach(c -> System.out.print(c + " "));
			}
		}
	}
	/** Solution ends here : Starts removable code **/

}
