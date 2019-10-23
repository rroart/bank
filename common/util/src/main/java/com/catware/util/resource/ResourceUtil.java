package com.catware.util.resource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ResourceUtil {
	public byte[] readResourceFile(String fileName) throws IOException {
		ResourceUtil.class.getResource(fileName);
		try (InputStream inputStream = getClass()
				.getClassLoader()
				.getResourceAsStream(fileName)) {
			return inputStream.readAllBytes();
		}
	}

	public Map<String, String> getProps() throws IOException {
		Map<String, String> map = new HashMap<>();
		System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		InputStream input = new FileInputStream("config.properties");

		Properties prop = new Properties();

		prop.load(input);

		// get the property value and print it out
		System.out.println(prop.getProperty("keyfile"));
		System.out.println(prop.getProperty("password"));

		map.put("keyfile", prop.getProperty("keyfile"));
		map.put("password", prop.getProperty("password"));
		return map;
	}
}
