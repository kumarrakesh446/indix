package com.rak.indix.utils.consoletable;

public class ColumnHeader {
	private String columnName;
	private int length;

	private int fullViewLength;
	private int fullHeaderView;
	public static final int COLUMN_SIZE_FULL_VIEW = 2;
	public static final int COLUMN_SIZE_FULL_HEADER = 1;
	public static final int COLUMN_SIZE_FULL_DEFAULT = 0;

	private int viewStyle = COLUMN_SIZE_FULL_DEFAULT;

	public int getFullViewLength() {
		return fullViewLength;
	}

	public ColumnHeader(String columnName) {
		this(columnName, columnName.length(), COLUMN_SIZE_FULL_HEADER);
	}
	public void setFullViewLength(int fullViewLength) {
		if (this.fullViewLength < fullViewLength)
			this.fullViewLength = fullViewLength;
	}

	public int getFullHeaderView() {
		return fullHeaderView;
	}

	public void setFullHeaderView(int fullHeaderView) {

		this.fullHeaderView = fullHeaderView;
	}

	public ColumnHeader(String columnName, int length) {
		this(columnName, length, COLUMN_SIZE_FULL_DEFAULT);

	}

	public ColumnHeader(int viewStyle, String columnName) {
		this(columnName, columnName.length(), viewStyle);

	}

	private ColumnHeader(String columnName, int length, int viewStyle) {

		fullViewLength =columnName.length();
		fullHeaderView = columnName.length();
		this.columnName = columnName;
		this.length = length;
		this.viewStyle = viewStyle;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public int getLength() {
		int length = 0;
		if (viewStyle == COLUMN_SIZE_FULL_DEFAULT)
			length = this.length;

		if (viewStyle == COLUMN_SIZE_FULL_HEADER)
			length = this.fullHeaderView;
		if (viewStyle == COLUMN_SIZE_FULL_VIEW)
			length = this.fullViewLength;
		return length;

	}

	public void setLength(int length) {
		
		if (viewStyle == COLUMN_SIZE_FULL_DEFAULT)
			this.length = length;


		if (viewStyle == COLUMN_SIZE_FULL_VIEW)
			setFullViewLength(length);
		
	}

}
