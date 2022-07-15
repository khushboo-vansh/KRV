package com.krv.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @author Khushboo_Vansh
 *
 */
public class FolderNaming {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		proposeDirectoryNames(input.trim());
		sc.close();
	}

	/** Solution Part 1 Starts here : Starts removable code **/
	private static void proposeDirectoryNames(String input) {
		String[] instructions = input.split("\\s");
		PriorityQueue<Integer> pQueue = new PriorityQueue<Integer>(Collections.reverseOrder());

		for (int i = 0; i < instructions.length; i++) {
			String inst = instructions[i];
			String code = inst.substring(0, 2);
			int num = Integer.valueOf(inst.substring(2, inst.length()));
			if ("IN".equals(code)) {
				pQueue = insertItem(num, pQueue);
			} else {
				pQueue = deleteItem(num, pQueue);
			}
		}

		Object[] arr = pQueue.stream().sorted().toArray();
		Arrays.sort(arr);
		for (int i = 0; i < arr.length; i++) {
			if (Integer.valueOf(arr[i].toString()) == 1) {
				System.out.println("New Folder");
			} else {
				System.out.println("New Folder(" + arr[i] + ")");
			}
		}
	}

	private static PriorityQueue<Integer> deleteItem(int num, PriorityQueue<Integer> pQueue) {
		pQueue.remove(num);
		return pQueue;
	}

	private static PriorityQueue<Integer> insertItem(int num, PriorityQueue<Integer> pQueue) {
		if (pQueue.isEmpty()) {
			for (int i = 1; i <= num; i++) {
				pQueue.add(i);
			}
		} else {
			int size = pQueue.size();
			int max = pQueue.peek();
			if (size == max) {
				for (int i = size + 1; i <= size + num; i++) {
					pQueue.add(i);
				}
			} else {
				List<Integer> missingNumber = findMissingElements(pQueue, max);
				int count = 0;
				for (Integer number : missingNumber) {
					if (count < num) {
						pQueue.add(number);
						count++;
					}
				}
				while (count < num) {
					pQueue.add(pQueue.size() + 1);
					count++;
				}
			}
		}
		return pQueue;
	}

	static List<Integer> findMissingElements(PriorityQueue<Integer> pQueue, int max) {
		Object[] arr = pQueue.stream().sorted().toArray();
		List<Integer> missingNums = new ArrayList<>();
		int currentValue = 1;
		for (int i = 0; i < arr.length; i++) {
			int num = (int) arr[i];
			if (num != currentValue) {
				for (int j = currentValue; j < num; j++) {
					missingNums.add(j);
				}
			}
			currentValue = num + 1;
		}
		return missingNums;
	}
	/** Solution end here : Ends removable code **/
}
