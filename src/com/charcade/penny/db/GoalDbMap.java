package com.charcade.penny.db;

import java.math.BigDecimal;
import java.security.Timestamp;

import android.provider.BaseColumns;

public abstract class GoalDbMap implements BaseColumns {
	public static final String TABLE_NAME = "habit";
	public static final String COLUMN_NAME_HID = "hid";
	public static final String COLUMN_NAME_NAME = "name";
	public static final String COLUMN_NAME_DECRIPTION = "description";
	public static final String COLUMN_NAME_VALUE = "value";
	public static final String COLUMN_NAME_WEIGHT = "weight";
	public static final String COLUMN_NAME_VISIBLE = "visible";
	public static final String COLUMN_NAME_CREATED = "created";
	public static final String COLUMN_NAME_UPDATED = "updated";
	public static final String COLUMN_NAME_ICON = "icon";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String AUTOINCREMENT_TYPE = " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL";
	private static final String COMMA_SEP = ",";
	static final String SQL_CREATE =
	    "CREATE TABLE " + TABLE_NAME + " (" +
	    COLUMN_NAME_HID + AUTOINCREMENT_TYPE + COMMA_SEP +
	    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    COLUMN_NAME_VALUE + TEXT_TYPE +
	    /*COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
	    COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +*/
	    " )";

	static final String SQL_DELETE =
	    "DROP TABLE IF EXISTS " + TABLE_NAME;
}
