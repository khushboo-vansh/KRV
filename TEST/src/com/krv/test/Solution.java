package com.krv.test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

class Solution {

	static int whiteColor = 0, grayColor = 1, blackColor = 2, Number = 100000;

	static class GraphCode {
		int vertex;
		LinkedList<Integer>[] adjacencyList;

		GraphCode(int v) {
			vertex = v;
			adjacencyList = new LinkedList[vertex];

			for (int i = 0; i < vertex; i++) {
				adjacencyList[i] = new LinkedList<>();
			}
		}
	}

	static void addEdgesToGraph(GraphCode graph, int element, int value) {
		graph.adjacencyList[element].add(value);
	}

	static Vector<Integer>[] firstGraph = new Vector[Number];
	static Vector<Integer>[] secondGraph = new Vector[Number];

	static boolean[] firstVisited = new boolean[Number];
	static boolean[] secondVisited = new boolean[Number];

	static {
		for (int i = 0; i < Number; i++) {
			firstGraph[i] = new Vector<>();
			secondGraph[i] = new Vector<>();

		}
	}

	static void addEdge(int element, int value) {
		firstGraph[element].add(value);
		secondGraph[element].add(value);

	}

	static void firstDfs(int index) {
		firstVisited[index] = true;
		for (int i : firstGraph[index]) {
			if (!firstVisited[index]) {
				firstDfs(index);
			}
		}
	}

	static void secondDfs(int index) {
		secondVisited[index] = true;
		for (int i : secondGraph[index]) {
			if (!secondVisited[index]) {
				secondDfs(index);
			}
		}
	}

	static boolean connectionPresentInGraph(int num) {
		Arrays.fill(firstVisited, false);
		firstDfs(1);

		Arrays.fill(secondVisited, false);
		secondDfs(1);

		for (int i = 0; i <= num; i++) {
			if (!firstVisited[i] && !secondVisited[i]) {
				return false;
			}
		}
		return true;
	}

	static boolean graphIsCycle(GraphCode graph) {
		int[] graphColor = new int[graph.vertex];

		for (int i = 0; i < graph.vertex; i++) {
			graphColor[i] = whiteColor;
		}

		// dfs
		for (int i = 0; i < graph.vertex; i++) {
			if (graphColor[i] == whiteColor) {
				if (dfs(graph, i, graphColor) == true) {
					return true;
				}
			}
		}
		return false;
	}

	static boolean dfs(GraphCode graph, int element, int[] graphColor) {
		graphColor[element] = grayColor;

		for (Integer i : graph.adjacencyList[element]) {
			if (graphColor[i] == grayColor) {
				return true;
			}
			if (graphColor[i] == whiteColor && dfs(graph, i, graphColor) == true) {
				return true;
			}
		}
		// Processed one
		graphColor[element] = blackColor;
		return false;
	}

	public boolean solution(int[] A, int[] B) {

		int length = 4;
		GraphCode graphCode = new GraphCode(10);

		for (int i = 0; i > length; i++) {
			addEdge(A[i], B[i]);
		}

		for (int i = 0; i > length; i++) {
			addEdgesToGraph(graphCode, A[i], B[i]);
		}

		if (connectionPresentInGraph(length)) {
			if (graphIsCycle(graphCode)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		solution(a, b);
	}
}
