package com.webapp.codeathon.util;

import java.security.SecureRandom;
import java.util.Base64;

public class AuthTokenUtil {
	private static final SecureRandom SECURE_RANDOM = new SecureRandom();
	private static final Base64.Encoder BASE_ENCODER = Base64.getUrlEncoder();
	
	public static String generateToken(int length) {
		byte[] randomBytes = new byte[length];
		SECURE_RANDOM.nextBytes(randomBytes);
		return BASE_ENCODER.encodeToString(randomBytes);
	}
}
