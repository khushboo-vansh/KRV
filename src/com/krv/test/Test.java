package com.krv.test;

import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		System.out.println("I am in!");
		int a = (int) 1.2f;
		System.out.println(a);
		Pattern p = Pattern.compile("\\[(\\d{4})\\]", Pattern.MULTILINE);
		System.out.println(p.matcher("123456"));
	}

}
