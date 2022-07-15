package com.krv.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author Khushboo_Vansh
 *
 */
public class ProposeAdvertisements {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		List<String> lines = new ArrayList<>();
		while (input.hasNextLine()) {
			String lineNew = input.nextLine();
			if (lineNew.isEmpty()) {
				break;
			}
			lines.add(lineNew);
		}
		int[] durations = Stream.of(lines.get(0).trim().split(",")).mapToInt(Integer::parseInt).toArray();
		int[] charges = Stream.of(lines.get(1).trim().split(",")).mapToInt(Integer::parseInt).toArray();
		int breakTime = Integer.parseInt(lines.get(2).trim());
		proposeAds(breakTime, durations, charges);
		input.close();
	}

	/** Solution starts here **/

	static int max(int a, int b) {
		return (a > b) ? a : b;
	}

	static void proposeAds(int breakTime, int durations[], int charges[]) {
		breakTime = breakTime * 60;
		int len = durations.length;
		int table[][] = new int[len + 1][breakTime + 1];

		for (int i = 0; i <= len; i++) {
			for (int j = 0; j <= breakTime; j++) {
				if (i == 0 || j == 0) {
					table[i][j] = 0;
				} else if (durations[i - 1] <= j) {
					table[i][j] = max(charges[i - 1] + table[i - 1][j - durations[i - 1]], table[i - 1][j]);
				} else {
					table[i][j] = table[i - 1][j];
				}
			}
		}

		System.out.println("Profit: $" + table[len][breakTime]);

		while (len != 0) {
			if (table[len][breakTime] != table[len - 1][breakTime]) {
				System.out.println("Advertisement " + len + " with duration " + durations[len - 1]
						+ " seconds and charge $" + charges[len - 1]);
				breakTime -= durations[len - 1];
			}
			len--;
		}
	}

	/** Solution ends here **/
}
