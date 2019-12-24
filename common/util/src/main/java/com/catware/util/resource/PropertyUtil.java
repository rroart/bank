package com.catware.util.resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class PropertyUtil {
	public Map<String, Object> getProps() throws IOException {
		Map<String, Object> map = new HashMap<>();
		String password = System.getProperty("password");
		map.put("password", password);
		String keyfile = System.getProperty("keyfile");
		System.out.println("kf0 " + keyfile.length());
		byte[] decodedBytes = Base64.getDecoder().decode(keyfile);
		//keyfile = new String(decodedBytes);		
		System.out.println("kf1 " + decodedBytes.length);
	    Files.write(Paths.get("/tmp/private1.p12"), decodedBytes);
		byte[] encodedBytes = Base64.getEncoder().encode(decodedBytes);
		System.out.println("kf2 " + encodedBytes.length);
		byte[] encodedBytes2 = Base64.getDecoder().decode(encodedBytes);
		//String keyfile3 = new String(encodedBytes2);
		System.out.println("kf3 " + encodedBytes2.length);
		map.put("keyfile", decodedBytes);
		return map;
	}
}
