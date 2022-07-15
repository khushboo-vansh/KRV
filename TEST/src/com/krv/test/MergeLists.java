package com.krv.test;

public class MergeLists {

	/* Link list Node */
	static class Node {
		int key;
		Node next;
	};

	static Node reverseList(Node head) {
		if (head.next == null) {
			return head;
		}
		Node rest = reverseList(head.next);
		head.next.next = head;
		head.next = null;
		return rest;
	}

	static Node sortedListMerge(Node a, Node b) {
		a = reverseList(a);
		b = reverseList(b);
		Node head = null;
		Node temp;
		while (a != null && b != null) {
			if (a.key >= b.key) {
				temp = a.next;
				a.next = head;
				head = a;
				a = temp;
			} else {
				temp = b.next;
				b.next = head;
				head = b;
				b = temp;
			}
		}
		while (a != null) {
			temp = a.next;
			a.next = head;
			head = a;
			a = temp;
		}
		while (b != null) {
			temp = b.next;
			b.next = head;
			head = b;
			b = temp;
		}
		return head;
	}

	static void printList(Node node) {
		while (node != null) {
			System.out.print(node.key + " ");
			node = node.next;
		}
	}

	static Node newNode(int key) {
		Node temp = new Node();
		temp.key = key;
		temp.next = null;
		return temp;
	}

	public static void main(String[] args) {

		Node a = newNode(7);
		a.next = newNode(10);
		a.next.next = newNode(16);
		a.next.next.next = newNode(30);

		Node b = newNode(8);
		b.next = newNode(9);
		b.next.next = newNode(14);

		System.out.print("\nMerged Linked List is: \n");
		printList(sortedListMerge(a, b));
	}
}
