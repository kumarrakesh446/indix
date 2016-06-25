
package com.rak.indix.utils.xml;

import org.w3c.dom.NodeList;

public interface XMLResultSet {
	
	String getString(String string);
	int getInt(String string);
	long getLong(String string);
	float getFloat(String string);
	
	
	String getAttribute(String id);
	
	
	boolean next() throws XMLProceesingException;
	void setTagName(String tagName) throws XMLProceesingException;
	String getTagName();
	void start() throws XMLProceesingException;
	
	
	int getLength();
	NodeList getAllElement(String string);
}
