package com.catware.service.model;

public class TppMessageGeneric {
	private String category;
	
	private Object code;
		
	private String path;
	
	private String text;

	public TppMessageGeneric() {
		super();
	}

	public String getCategory() {
		return category;
	}

	public TppMessageGeneric(String category, String text) {
		super();
		this.category = category;
		this.text = text;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Object getCode() {
		return code;
	}

	public void setCode(Object code) {
		this.code = code;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}
