package com.catware.util.http;

public class MyResponse {
	private int code;
	
	private String body;

	public MyResponse(int code, String body) {
		super();
		this.code = code;
		this.body = body;
	}

	public MyResponse() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
	
	@Override
	public String toString() {
		return "" + code + " " + body;
	}
}
