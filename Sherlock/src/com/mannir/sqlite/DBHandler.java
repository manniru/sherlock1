package com.mannir.sqlite;

import java.util.ArrayList;

import com.mannir.sqlite.Users;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHandler extends SQLiteOpenHelper {

	private String USERS = "contacts";
	private String KEY_ID = "id";
	private String NAME = "name";
	private String GENDER = "gender";
	private String HEIGHT = "height";
	private String AGE = "age";
	private String HAIRCOLOR = "haircolor";
	private String COMMENT = "comment";
	private String EMAIL = "email";

	private static String databaseName = "contactsManager";
	private static int version = 1;

	public DBHandler(Context context) {
		super(context, databaseName, null, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String CREATE_USER_TABLE = "CREATE TABLE " + USERS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + 
				NAME + " TEXT," + 
				GENDER + " TEXT," + 
				HEIGHT + " TEXT," + 
				AGE + " TEXT," + 
				HAIRCOLOR + " TEXT," + 
				COMMENT + " TEXT," + 
                EMAIL + " TEXT)";
                db.execSQL(CREATE_USER_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + USERS);
		this.onCreate(db);
	}
	
	public void add(Users user){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(NAME, user.name);
		values.put(GENDER, user.gender);
		values.put(HEIGHT, user.height);
		values.put(AGE, user.age);
		values.put(HAIRCOLOR, user.haircolor);
		values.put(COMMENT, user.comment);
		values.put(EMAIL, user.email);

		// Inserting Row
		db.insert(USERS, null, values);
		db.close(); // Closing database connection
	}

	public Users get(int id){
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(USERS, new String[] { KEY_ID,
	            NAME, GENDER, HEIGHT, AGE, HAIRCOLOR, COMMENT, EMAIL }, KEY_ID + "=?",
	            new String[] { String.valueOf(id) }, 
	            null, // group by 
	            null, // having
	            null, // order by
	            null); // limit

		if (cursor != null) cursor.moveToFirst();
		    Users car = new Users(
		    	Integer.parseInt(cursor.getString(0)),
		    	cursor.getString(1),
		    	cursor.getString(2),
		    	cursor.getString(3),
		    	cursor.getString(4),
		    	cursor.getString(5),
		    	cursor.getString(6),
		    	cursor.getString(7));
		    cursor.close();
		    db.close();
		    return car;
		}
	//}

	public int update(Users user) {

	    SQLiteDatabase db = this.getWritableDatabase();

	    ContentValues values = new ContentValues();
	    values.put(NAME, user.name);
	    values.put(GENDER, user.gender);
	    values.put(HEIGHT, user.height);
	    values.put(AGE, user.age);
	    values.put(HAIRCOLOR, user.haircolor);
	    values.put(COMMENT, user.comment);
	    values.put(EMAIL, user.email);

	    // updating row
	    return db.update(USERS, values, KEY_ID + " = ?",
	            new String[] { String.valueOf(user.id) });
	}
/**
	public int delete(PushNotification notification) {

	    SQLiteDatabase db = this.getWritableDatabase();
	    // deleting row
	    return db.delete(CAR_TABLE, KEY_ID + " = ?",
	        new String[] { String.valueOf(notification.getId()) });

	}
	*/
	
	public ArrayList getAll(){

		  ArrayList listCar = new ArrayList();

		  SQLiteDatabase db = this.getReadableDatabase();
		  Cursor cursor = db.rawQuery("SELECT * FROM " + USERS, null);   try{
		    if (cursor != null){
		      if(cursor.moveToFirst()){
		        do {
		  	  Users car = new Users(
		            Integer.parseInt(cursor.getString(0)),
		            cursor.getString(1),
		            cursor.getString(2),
		            cursor.getString(3),
		            cursor.getString(4),
		            cursor.getString(5),
		            cursor.getString(6),
		            cursor.getString(7) );
		            listCar.add(car);
		        } while(cursor.moveToNext());
		      }
		    }
		  } finally {
		    cursor.close();
		    db.close();
		  }
		  return listCar;
		}
}