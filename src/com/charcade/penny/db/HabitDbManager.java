package com.charcade.penny.db;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.charcade.penny.entities.Habit;

public class HabitDbManager {
	private HabitDbHelper mDbHelper;
	private SQLiteDatabase db;
	private Context context;
	
	public HabitDbManager(Context context){
      this.context = context;
	  mDbHelper = new HabitDbHelper(context);
	  db = mDbHelper.getWritableDatabase();
	}
	/**
	 * Save a new habit in the database.
	 */
	public long createHabit(String title, String value){	  
	  ContentValues values = new ContentValues();
	  values.put(HabitDbMap.COLUMN_NAME_NAME, title);
	  values.put(HabitDbMap.COLUMN_NAME_VALUE, value);

	  return db.insert(
			  HabitDbMap.TABLE_NAME,
			  "null",
	           values);
    }
	/**
	 * Get a habit for a specific hid.
	 */
	public Habit getHabit(Integer hid){
		Habit habit = new Habit();
		try {
			Cursor cursor = db.rawQuery("SELECT * FROM " + HabitDbMap.TABLE_NAME + " WHERE " + HabitDbMap.COLUMN_NAME_HID + " = '"+ hid + "'", null);
			cursor.moveToFirst();
			habit = getHabitFromCursor(cursor);
		} catch (CursorIndexOutOfBoundsException e) {
			Toast.makeText(context, "Nothing was found.", Toast.LENGTH_SHORT).show();
		}
		return habit;
	}
	/**
	 * Get list of current habits in the database.
	 */
    public ArrayList<Habit> getHabitList(){
    	ArrayList<Habit> habitList = new ArrayList<Habit>();
    	Cursor cursor = db.query(HabitDbMap.TABLE_NAME, new String[] {HabitDbMap.COLUMN_NAME_HID, HabitDbMap.COLUMN_NAME_NAME, HabitDbMap.COLUMN_NAME_VALUE}, 
                null, null, null, null, null);
    	
    	cursor.moveToFirst();
    	while (cursor.isAfterLast() == false) {
    		habitList.add(getHabitFromCursor(cursor));
    	    cursor.moveToNext();
    	}
    	return habitList;
    }
    /**
	 * Populates a habit from a db cursor.
	 */
    private Habit getHabitFromCursor(Cursor cursor) {
    	Habit habit = new Habit();
    	habit.setHid(cursor.getInt(cursor.getColumnIndex(HabitDbMap.COLUMN_NAME_HID)));
    	habit.setName(cursor.getString(cursor.getColumnIndex(HabitDbMap.COLUMN_NAME_NAME)));
    	habit.setValue(BigDecimal.valueOf(cursor.getInt(cursor.getColumnIndex(HabitDbMap.COLUMN_NAME_VALUE))));
    	return habit;
    }
}
