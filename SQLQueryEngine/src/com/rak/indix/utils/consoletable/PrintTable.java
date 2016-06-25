package com.rak.indix.utils.consoletable;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class PrintTable<T>{

	ArrayList<ColumnHeader> columnHeaders = new ArrayList<ColumnHeader>();
	private int columnLength;
	private int rowLength;
	private int currColumn;
	private int currRow=-1;
	private int totalcolumnLength;

	char borderHorzontal = '*';
	char bordervertical='*';
	public static final int LEFT_PADDING = 0;
	public static final int RIGHT_PADDING = 1;
	public static final int MIDDLE_PADDING = 2;
	private int padding = LEFT_PADDING;

	public char getBorderHorzontal() {
		return borderHorzontal;
	}

	public void setBorderHorzontal(char borderHorzontal) {
		this.borderHorzontal = borderHorzontal;
	}

	public int getPadding() {
		return padding;
	}

	public char getBordervertical() {
		return bordervertical;
	}
	public void setBordervertical(char bordervertical) {
		this.bordervertical = bordervertical;
	}
	public void setPadding(int padding) {
		if (padding >= 0 && padding < 3)
			this.padding = padding;
		else
			throw new IllegalArgumentException("Pading value in not correct");
	}

	Map<Integer, ArrayList<String>> rowsValues = new TreeMap<Integer, ArrayList<String>>();
	Map<Integer, ArrayList<T>> rowObectValues = new TreeMap<Integer, ArrayList<T>>();
	private PrintStream out=System.out;
	private boolean isPrintSequence;
	private String sequenceColumn="SR NO.";
	private ColumnHeader sequenceHeader;

	public void addColumnHeader(String columnHeaderName) {

		columnHeaders.add(new ColumnHeader(columnHeaderName, columnHeaderName
				.length()));
		columnLength++;
		// totalcolumnLength += columnHeaderName.length() + 2;

	}

	public void addColumnHeader(String columnHeader, int columnSize) {
		columnHeaders.add(new ColumnHeader(columnHeader, columnSize));
		columnLength++;
		// totalcolumnLength += columnSize + 2;

	}

	public void addColumnHeader(ColumnHeader columnHeader) {
		columnHeaders.add(columnHeader);
		columnLength++;
		// totalcolumnLength += columnHeader.getLength() + 2;

	}

	public void addNewRow() {

		for (int i = currColumn; i < columnLength; i++)
			addRowValue("");

		currRow++;
		currColumn = 0;

	}

	public void addNewRow(ArrayList<String> row) {
		if (row.size() > columnLength)
			throw new RuntimeException("exciding column...");
		/*rowsValues.put(rowLength, row);
		rowLength++;*/
		for (String string : row) {
			addRowValue(string);
		}
		addNewRow();
	}

	public int getRowLength() {
		return rowsValues.size();
	}
	public void addRowValue(T t) {
		if(t==null){
			addRowValue("");
		}else{
			addRowValue(t.toString());
			ArrayList<T> tempRow = rowObectValues.get(currRow);
			if (tempRow == null) {
				tempRow = new ArrayList<T>();
				rowObectValues.put(currRow, tempRow);
				
			}
			
				tempRow.add(t);
		}
	}
	public ArrayList<T> getRowObectValue(Integer row){
		return rowObectValues.get( row);
	}
	public T getRowObectValue(Integer row,Integer column){
		return rowObectValues.get(row).get(column);
	}
	public void addRowValue(String rowValue) {

		ArrayList<String> tempRow = rowsValues.get(currRow);
		if(currColumn>=columnLength)
			throw new ArrayIndexOutOfBoundsException("Column is greater...");
		ColumnHeader header = columnHeaders.get(currColumn);

		currColumn++;

		if (tempRow == null) {
			tempRow = new ArrayList<String>();
			rowsValues.put(currRow, tempRow);
			header.setLength(rowValue.length());

		}
		if (tempRow.size() < columnLength)
			tempRow.add(rowValue);
		else
			throw new RuntimeException("exciding column...");

		// out.println(rowsValues);
		// rowsValues.put(currRow, value);

	}

	public void updateColumnValue(int rowNum, int columnNum, String values) {

	}

	public void print() {
		print(false);
	}

	public PrintStream getOutPutStream() {
		return out;
	}
	public void print(boolean isPrintSequence) {

		this.isPrintSequence=isPrintSequence;
		
		if(isPrintSequence)
			 sequenceHeader=new ColumnHeader( sequenceColumn);
		out.println();
		setTotalcolumnLength();
		printHeader();
		printRows();
		out.println();
		printFooter();
		out.println();
		

	}
	
	public void print(OutputStream outputStream) {
		out=new PrintStream(outputStream, true);
		
		print(false);
	}
	public void print(boolean isPrintSequence,OutputStream outputStream) {
		out=new PrintStream(outputStream, true);
		
		/*out.println();
		setTotalcolumnLength();
		printHeader();
		printRows();
		printFooter();*/
		
		print(isPrintSequence);

	}


	private void printFooter() {
		printHorizontalLine(totalcolumnLength);

	}

	private void printRows() {
		Set<Integer> rowKeys = rowsValues.keySet();
		int i;
		int rowSequence=1;
		for (Iterator<Integer> iterator = rowKeys.iterator(); iterator
				.hasNext();) {
			Integer integer = (Integer) iterator.next();

			ArrayList<String> rowValueList = rowsValues.get(integer);
			i = 0;
			out.println();
			if (isPrintSequence) {
				printColumnValue(""+rowSequence++, calculateSequenceLength());
			}
			for (Iterator<String> iterator1 = rowValueList.iterator(); iterator1
					.hasNext();) {
				String string = iterator1.next();

				printColumnValue(string, columnHeaders.get(i).getLength());
				i++;
			}
		

		}

	}

	public void setTotalcolumnLength() {

		totalcolumnLength = 0;
		for (int i = 0; i < columnHeaders.size(); i++)
			this.totalcolumnLength += columnHeaders.get(i).getLength() + 2;
		
		if(isPrintSequence)
			totalcolumnLength+=calculateSequenceLength()+2;
		
		
	}

	private void printHeader() {
		int noOfStar = totalcolumnLength;
		printHorizontalLine(noOfStar);
		out.println();
		printColumnNames();
		out.println();
		printHorizontalLine(noOfStar);
	

	}

	private void printColumnNames() {
		if(isPrintSequence)
		{
			printColumnValue(sequenceColumn, calculateSequenceLength());
		}
		for (Iterator<ColumnHeader> iterator = columnHeaders.iterator(); iterator
				.hasNext();) {
			ColumnHeader column = iterator.next();
			// out.println("*"+col);
			printColumnValue(column.getColumnName(), column.getLength());

		}

	}

	private int calculateSequenceLength() {
	
		int noOfRow=currRow;
		int count=0;
		while (noOfRow>0) {
			noOfRow/=10;
			count++;
		}
		if(sequenceColumn.length()>=count)
			count=sequenceColumn.length();
		return count;
	}

	private void printColumnValue(String columnName, int size) {
		columnName = trim(columnName, size);
		out.print(bordervertical + columnName + bordervertical);
	}

	private String trim(String columnName, int length) {
		String trimColumnName = " ";

		if (columnName == null)
			columnName = " ";

		if (length < columnName.length())
			trimColumnName = columnName.substring(0, length);
		else {
			if (padding == LEFT_PADDING)
				trimColumnName = lPad(columnName, length - columnName.length());
			if (padding == RIGHT_PADDING)
				trimColumnName = rPad(columnName, length - columnName.length());
			if (padding == MIDDLE_PADDING)
				trimColumnName = mPad(columnName, length - columnName.length());
		}
		return trimColumnName;

	}

	private String mPad(String columnName, int pad) {
		for (int i = 0; i < pad; i++)
			if (i % 2 == 0)
				columnName = " " + columnName;
			else
				columnName += " ";
		return columnName;
	}

	private String rPad(String columnName, int pad) {
		for (int i = 0; i < pad; i++)
			columnName = " " + columnName;
		return columnName;
	}

	private String lPad(String columnName, int pad) {

		for (int i = 0; i < pad; i++)
			columnName += " ";
		return columnName;
	}

	private void printHorizontalLine(int noOfStar) {
		for (int i = 0; i < noOfStar; i++)
			out.print(borderHorzontal);

	}
}
