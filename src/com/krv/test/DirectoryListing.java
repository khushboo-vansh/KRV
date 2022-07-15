package com.krv.test;

import java.io.File;

public class DirectoryListing {

	public static void main(String[] args) {
		File directory = new File("C:\\KRV");
		System.out.println(directory);
		searchRecursively(directory);
	}

	private static void searchRecursively(File directory) {
		if (null == directory) {
			return;
		}
		if (directory.isFile()) {
			System.out.println("File : " + directory);
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file != null && file.isDirectory()) {
				System.out.println("Directory : " + file);
				searchRecursively(file);
			} else {
				System.out.println("File : " + file);
			}
		}
	}

}
