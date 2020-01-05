package com.catware.util.http.dnb;

public class RedirectUtil {
	public static String getUrl() {
		if (System.getProperty("dev") != null) {
			return "http://localhost:3083/";
		} else {
			return "http://www.catwarebank.tk/";
		}
	}
	
	public static String getUrl(String psu, int go) {
		return getUrl() + "?psu=" + psu + "&go=" + go;
	}
}