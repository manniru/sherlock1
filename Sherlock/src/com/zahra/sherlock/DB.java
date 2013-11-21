package com.zahra.sherlock;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.util.Log;

public class DB extends SQLiteOpenHelper {
         
        public DB(Context context, String name, CursorFactory factory, int version) {
                super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
                
                db.execSQL("CREATE TABLE IF NOT EXISTS Person " +
                		"(personid INTEGER PRIMARY KEY, name VARCHAR, gender VARCHAR, height VARCHAR, age INT(3)," +
                		"haircolor VARCHAR, comments VARCHAR, email VARCHAR);");
                
                db.execSQL("CREATE TABLE IF NOT EXISTS Reports " +
                		"(reportid INTEGER, time VARCHAR, location VARCHAR, notes VARCHAR, personid INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                Log.w("My DB", "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS notes");
        onCreate(db);
        }
}