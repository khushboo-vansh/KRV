package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * @author Khushboo_Vansh
 *
 */
public class CompanyRatings {

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		rateCompanies(input);
		input.close();
	}

	/** Solution Starts here **/

	private static void rateCompanies(Scanner input) {
		Map<String, List<String>> empReviews = new HashMap<String, List<String>>();
		List<String> lines = new ArrayList<String>();
		while (input.hasNextLine()) {
			String lineNew = input.nextLine();
			if (lineNew.isEmpty()) {
				break;
			}
			List<String> items = Arrays.asList(lineNew.split(","));
			empReviews.put(items.get(0), items.subList(1, items.size()));
			lines.add(lineNew);
		}

		Map<String, Integer> weightageMap = new HashMap<>();
		weightageMap.put("good", 3);
		weightageMap.put("excellent", 5);
		weightageMap.put("great", 5);
		weightageMap.put("awesome", 6);
		weightageMap.put("fantastic", 6);
		weightageMap.put("best", 5);
		weightageMap.put("super", 5);
		weightageMap.put("no", -1);
		weightageMap.put("not", -2);
		weightageMap.put("bad", -3);
		weightageMap.put("worse", -5);
		weightageMap.put("worst", -6);
		weightageMap.put("poor", -3);
		weightageMap.put("hard", -4);

		TreeSet<CompanyScore> output = new TreeSet<>();

		for (Map.Entry<String, List<String>> entry : empReviews.entrySet()) {
			int scoreTotal = 0;
			List<String> reviews = new ArrayList<>(entry.getValue());

			for (String review : reviews) {
				List<String> items = Arrays.asList(review.toLowerCase().split("\\s"));
				int score = items.stream().filter(p -> weightageMap.containsKey(p.trim()))
						.mapToInt(i -> weightageMap.get(i)).sum();
				scoreTotal += score;
			}
			if (scoreTotal > 0) {
				output.add(new CompanyScore(entry.getKey(), scoreTotal));
			}
		}
		output.forEach(rating -> System.out.println(rating.companyName + " : " + rating.score));
	}

	static class CompanyScore implements Comparable<CompanyScore> {
		String companyName;
		Integer score;

		public CompanyScore(String companyName, int score) {
			this.companyName = companyName;
			this.score = score;
		}

		@Override
		public int compareTo(CompanyScore other) {
			int i = Integer.compare(other.score, score);
			if (i != 0)
				return i;

			return companyName.compareTo(other.companyName);
		}

		@Override
		public int hashCode() {
			return Objects.hash(companyName, score);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			CompanyScore other = (CompanyScore) obj;
			return Objects.equals(companyName, other.companyName) && Objects.equals(score, other.score);
		}

	}

	/** Solution ends here **/
}
