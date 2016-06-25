package com.rak.indi.models;

public class Parameter {

	private String name;
	private ParameterType parameterType;
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ParameterType getParameterType() {
		return parameterType;
	}
	public void setParameterType(ParameterType parameterType) {
		this.parameterType = parameterType;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
}
