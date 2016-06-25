package com.rak.indix.dao;

import java.util.ArrayList;
import java.util.Map;

public class QueryResult {

	private ArrayList<String> columnList=new ArrayList<>();
	private ArrayList<ArrayList< String>> queryResultList=new ArrayList<>();
	public ArrayList<String> getColumnList() {
		return columnList;
	}
	public void setColumnList(ArrayList<String> columnList) {
		this.columnList = columnList;
	}
	public ArrayList<ArrayList< String>> getQueryResultList() {
		return queryResultList;
	}
	public void setQueryResultList(ArrayList<ArrayList< String>> queryResultList) {
		this.queryResultList = queryResultList;
	}
	
	 
}
