package com.krv.cowin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.experimental.UtilityClass;

/**
 * @author Khushboo_Vansh
 *
 */
@UtilityClass
public class MobileNumberValidationUtility {

	/**
	 * @param str
	 * @return
	 */
	public static boolean isValidMobileNo(String str) {
		Pattern ptrn = Pattern.compile("(0/91)?[7-9][0-9]{9}");
		Matcher match = ptrn.matcher(str);
		return (match.find() && match.group().equals(str));
	}

}