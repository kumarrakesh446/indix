package com.rak.indix.utils.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLProcessing implements XMLResultSet {

	private NodeList nList;
	private String tagName;
	private Document doc;
	private int nLength;
	private int currPosition;
	private Node nNode;
	private Element eElement;

	public XMLProcessing(String XMLasSting) throws XMLProceesingException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

			// convert String into InputStream
			InputStream is = new ByteArrayInputStream(XMLasSting.getBytes());

			doc = dBuilder.parse(is);
			doc.getDocumentElement().normalize();

		} catch (ParserConfigurationException e) {

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public XMLProcessing(File file) {

		DocumentBuilder dBuilder;
		try {
			dBuilder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			 doc = dBuilder.parse(file);
			 doc.getDocumentElement().normalize();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	@Override
	public boolean next() throws XMLProceesingException {
		currPosition++;
		boolean isMore = true;
		if (currPosition >= nLength)
			isMore = false;
		else {
			nNode = nList.item(currPosition);
			if (nNode == null)
				throw new XMLProceesingException("No Node Found");
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				eElement = (Element) nNode;

			} else
				eElement = null;
		}
		return isMore;
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return tagName;
	}

	@Override
	public void setTagName(String tagName) throws XMLProceesingException {
		this.tagName = tagName;
		start();
	}

	@Override
	public void start() throws XMLProceesingException {
		if (tagName == null)
			throw new XMLProceesingException("Tag Name not set");
		nList = doc.getElementsByTagName(tagName);

		nLength = nList.getLength();
		currPosition = -1;

	}

	@Override
	public String getString(String string) {

		return eElement.getElementsByTagName(string).item(0).getTextContent();
	}

	@Override
	public NodeList getAllElement(String string) {

		return eElement.getElementsByTagName(string);
	}
	@Override
	public int getInt(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long getLong(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getFloat(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAttribute(String id) {

		return eElement.getAttribute(id);
	}

	@Override
	public int getLength() {

		return nLength;
	}

}
