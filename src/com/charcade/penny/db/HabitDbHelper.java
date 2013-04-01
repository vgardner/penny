package com.charcade.penny.db;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class HabitDbHelper extends PennyDbHelper {
    public HabitDbHelper(Context context) {
        super(context);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(HabitDbMap.SQL_CREATE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over.
        db.execSQL(HabitDbMap.SQL_DELETE);
        onCreate(db);
    }
}
