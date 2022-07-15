package com.krv.my.app.authenticate.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import lombok.experimental.UtilityClass;

/**
 * @author Khushboo_Vansh
 *
 */
@UtilityClass
public class EncryptionUtil {

	private static final String UTF8 = "UTF-8";
	private static final int TWENTY = 20;
	private static final byte[] PBE = { 51, 52, 102, 115, 37, 102, 100, 38, 94, 38, 51, 110, 98, 110, 114, 119, 38, 57,
			55, 36, 37, 36, 106, 115, 115, 102, 97, 115, 36, 103, 102, 100, 32 };
	private static final String INSTANCE = "PBEWithMD5AndDES";
	private static final byte[] SALT = { (byte) 0xde, (byte) 0x33, (byte) 0x10, (byte) 0x12, (byte) 0xde, (byte) 0x33,
			(byte) 0x10, (byte) 0x12, };

	/**
	 * @param property
	 * @return
	 * @throws GeneralSecurityException
	 * @throws UnsupportedEncodingException
	 */
	public String encrypt(String property) throws GeneralSecurityException, UnsupportedEncodingException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(INSTANCE);
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(new String(PBE).toCharArray()));
		Cipher pbeCipher = Cipher.getInstance(INSTANCE);
		pbeCipher.init(Cipher.ENCRYPT_MODE, key, new PBEParameterSpec(SALT, TWENTY));
		return base64Encode(pbeCipher.doFinal(property.getBytes(UTF8)));
	}

	private String base64Encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * @param property
	 * @return
	 * @throws GeneralSecurityException
	 * @throws IOException
	 */
	public String decrypt(String property) throws GeneralSecurityException, IOException {
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(INSTANCE);
		SecretKey key = keyFactory.generateSecret(new PBEKeySpec(new String(PBE).toCharArray()));
		Cipher pbeCipher = Cipher.getInstance(INSTANCE);
		pbeCipher.init(Cipher.DECRYPT_MODE, key, new PBEParameterSpec(SALT, TWENTY));
		return new String(pbeCipher.doFinal(base64Decode(property)), UTF8);
	}

	/**
	 * @param property
	 * @return
	 */
	public byte[] base64Decode(String property) {
		return Base64.getDecoder().decode(property);
	}

	/**
	 * @param input
	 * @return
	 */
	public String createSHASign(String input) {
		MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException(e);
		}
		return Base64.getEncoder().encodeToString(digest.digest(input.getBytes(StandardCharsets.UTF_8)));
	}
}
