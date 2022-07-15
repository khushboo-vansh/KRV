package com.krv.test;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GenerateMD5Hash {

	public static void main(String[] args) {
		String message = "asn6sdfgfdst";
		MessageDigest md = getMessageDigest();

		byte[] md5Digest = md.digest(getBytesOfMessage(message));
		System.out.println(md5Digest);
	}

	private static MessageDigest getMessageDigest() {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return md;
	}

	private static byte[] getBytesOfMessage(String message) {
		byte[] bytesOfMessage = null;
		try {
			bytesOfMessage = message.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return bytesOfMessage;
	}

}
