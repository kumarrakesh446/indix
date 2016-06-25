package com.rak.indix.utils.xml;

public class XMLProceesingException extends Exception{

	private String message;

	public XMLProceesingException(String msg) {
		this.message=msg;
	}
	
	@Override
	public String getMessage() {
		
		return message;
	}
}
