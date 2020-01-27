package com.catware.util.http.dnb;

public class RedirectUtil {
	public static String getUrl() {
		if (System.getProperty("dev") != null) {
			return "http://localhost:3083/";
		} else {
			return System.getProperty("catwareserver");
		}
	}
	
	public static String getUrl(String psu, int go) {
		return getUrl() + "?psu=" + psu + "&go=" + go;
	}
}