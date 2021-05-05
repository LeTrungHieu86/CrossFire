package common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	
	// ma hoa MD5
	public static String MD5Code(String strParameter) {
		
		String result = "";
		MessageDigest digest;
		
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(strParameter.getBytes());
			BigInteger bigInteger = new BigInteger(1,digest.digest());
			result = bigInteger.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
