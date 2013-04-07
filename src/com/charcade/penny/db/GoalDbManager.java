package com.charcade.penny.db;

import java.math.BigDecimal;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.charcade.penny.entities.Goal;

public class GoalDbManager {
	private GoalDbHelper mDbHelper;
	private SQLiteDatabase db;
	private Context context;
	
	public GoalDbManager(Context context){
      this.context = context;
	  mDbHelper = new GoalDbHelper(context);
	  db = mDbHelper.getWritableDatabase();
	}
	/**
	 * Save a new Goal in the database.
	 */
	public long createGoal(String title, String value){	  
	  ContentValues values = new ContentValues();
	  values.put(GoalDbMap.COLUMN_NAME_NAME, title);
	  values.put(GoalDbMap.COLUMN_NAME_VALUE, value);

	  return db.insert(
			  GoalDbMap.TABLE_NAME,
			  "null",
	           values);
    }
	/**
	 * Get a Goal for a specific gid.
	 */
	public Goal getGoal(Integer gid){
		Goal Goal = new Goal();
		try {
			Cursor cursor = db.rawQuery("SELECT * FROM " + GoalDbMap.TABLE_NAME + " WHERE " + GoalDbMap.COLUMN_NAME_GID + " = '"+ gid + "'", null);
			cursor.moveToFirst();
			Goal = getGoalFromCursor(cursor);
		} catch (CursorIndexOutOfBoundsException e) {
			Toast.makeText(context, "Nothing was found.", Toast.LENGTH_SHORT).show();
		}
		return Goal;
	}
	/**
	 * Get list of current Goals in the database.
	 */
    public ArrayList<Goal> getGoalList(){
    	ArrayList<Goal> GoalList = new ArrayList<Goal>();
    	Cursor cursor = db.query(GoalDbMap.TABLE_NAME, new String[] {GoalDbMap.COLUMN_NAME_GID, GoalDbMap.COLUMN_NAME_NAME, GoalDbMap.COLUMN_NAME_VALUE}, 
                null, null, null, null, null);
    	
    	cursor.moveToFirst();
    	while (cursor.isAfterLast() == false) {
    		GoalList.add(getGoalFromCursor(cursor));
    	    cursor.moveToNext();
    	}
    	return GoalList;
    }
    /**
	 * Populates a Goal from a db cursor.
	 */
    private Goal getGoalFromCursor(Cursor cursor) {
    	Goal Goal = new Goal();
    	Goal.setGid(cursor.getInt(cursor.getColumnIndex(GoalDbMap.COLUMN_NAME_GID)));
    	Goal.setName(cursor.getString(cursor.getColumnIndex(GoalDbMap.COLUMN_NAME_NAME)));
    	Goal.setValue(BigDecimal.valueOf(cursor.getInt(cursor.getColumnIndex(GoalDbMap.COLUMN_NAME_VALUE))));
    	return Goal;
    }
}
