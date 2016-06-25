package com.rak.indix.utils.consoletable;

public class CellValue {
	String cellValue="";
	int row;
	int column;
	
	
	public CellValue(String cellValue, int row, int column) {
		super();
		this.cellValue = cellValue;
		this.row = row;
		this.column = column;
	}
	public String getCellValue() {
		return cellValue;
	}
	public void setCellValue(String cellValue) {
		this.cellValue = cellValue;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getColumn() {
		return column;
	}
	public void setColumn(int column) {
		this.column = column;
	}
	
	
	
}
