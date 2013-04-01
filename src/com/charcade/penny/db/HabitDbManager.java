package com.charcade.penny.db;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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
	public long createHabit(String title, String value){	  
	  ContentValues values = new ContentValues();
	  values.put(HabitDbMap.COLUMN_NAME_NAME, title);
	  values.put(HabitDbMap.COLUMN_NAME_VALUE, value);

	  return db.insert(
			  HabitDbMap.TABLE_NAME,
			  "null",
	           values);
    }
	
    public ArrayList<Habit> getHabitList(){
    	ArrayList<Habit> habitList = new ArrayList<Habit>();
    	Cursor cursor = db.query(HabitDbMap.TABLE_NAME, new String[] {HabitDbMap.COLUMN_NAME_HID, HabitDbMap.COLUMN_NAME_NAME, HabitDbMap.COLUMN_NAME_VALUE}, 
                null, null, null, null, null);
    	
    	cursor.moveToFirst();
    	while (cursor.isAfterLast() == false) {
    		Habit currentHabit = new Habit();
    		currentHabit.setHid(cursor.getInt(cursor.getColumnIndex(HabitDbMap.COLUMN_NAME_HID)));
    		currentHabit.setName(cursor.getString(cursor.getColumnIndex(HabitDbMap.COLUMN_NAME_NAME)));
    		currentHabit.setValue(BigDecimal.valueOf(cursor.getInt(cursor.getColumnIndex(HabitDbMap.COLUMN_NAME_VALUE))));
    		habitList.add(currentHabit);
    	    cursor.moveToNext();
    	}
    	return habitList;
    }
}
