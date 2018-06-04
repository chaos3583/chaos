package com.chaos.util;

import java.util.UUID;

public class TokenClient {

	public static String getToken(Integer userId) {
		String token= UUID.randomUUID().toString();
		if(userId!=null) {
			token +=userId;
		}
		return token;
	}
}
