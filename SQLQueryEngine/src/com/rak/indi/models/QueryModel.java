package com.rak.indi.models;

import java.util.ArrayList;
import java.util.List;

public class QueryModel {

	private String query;
	private List<Parameter>  parameters=new ArrayList<>();
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public List<Parameter> getParameters() {
		return parameters;
	}
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
	
	@Override
	public String toString() {
		
		return query;
	}
}
