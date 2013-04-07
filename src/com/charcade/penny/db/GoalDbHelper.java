package com.charcade.penny.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GoalDbHelper extends PennyDbHelper {
    public GoalDbHelper(Context context) {
        super(context);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
    	super.onCreate(db);
        db.execSQL(GoalDbMap.SQL_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    	super.onUpgrade(db, oldVersion, newVersion);
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over.
        db.execSQL(GoalDbMap.SQL_DELETE);
        onCreate(db);
    }
}