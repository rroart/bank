package com.catware.service.model;

import java.util.List;
import java.util.Map;

public class Error {
	private Map<String, HrefType> links;
	
	private List<TppMessageGeneric> tppMessages;
	
	public Error() {
		super();
	}

	public Error(List<TppMessageGeneric> tppMessages) {
		super();
		this.tppMessages = tppMessages;
	}

	public Map<String, HrefType> getLinks() {
		return links;
	}

	public void setLinks(Map<String, HrefType> links) {
		this.links = links;
	}

	public List<TppMessageGeneric> getTppMessages() {
		return tppMessages;
	}

	public void setTppMessages(List<TppMessageGeneric> tppMessages) {
		this.tppMessages = tppMessages;
	}

}
