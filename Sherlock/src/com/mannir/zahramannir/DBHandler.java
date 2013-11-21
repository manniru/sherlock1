package com.mannir.zahramannir;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "PersonDB";

    // Contacts table name
    private static final String TABLE_PERSON = "Person";

    // Contacts Table Columns names
    private static final String PERSONID = "personid";
    private static final String TIME = "time";
    private static final String LOCATION = "location";
    private static final String NOTES = "notes";
    
    private final ArrayList<Person> person_list = new ArrayList<Person>();

    public DBHandler(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
	String sql = "CREATE TABLE " + TABLE_PERSON + "("
		+ PERSONID + " INTEGER PRIMARY KEY," + TIME + " TEXT,"
		+ LOCATION + " TEXT," + NOTES + " TEXT" + ")";
	db.execSQL(sql);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// Drop older table if existed
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);

	// Create tables again
	onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void Add_Person(Person person) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	///values.put(KEY_NAME, contact.getName()); // Contact Name
	///values.put(KEY_AGE, contact.getPhoneNumber()); // Contact Phone
	//values.put(KEY_EMAIL, contact.getEmail()); // Contact Email
	values.put(PERSONID, person.getPersonid());
	values.put(TIME, person.getTime());
	values.put(LOCATION, person.getLocation());
	values.put(NOTES, person.getNotes());

	// Inserting Row
	db.insert(TABLE_PERSON, null, values);
	db.close(); // Closing database connection
    }

    // Getting single contact
    Person Get_Person(int id) {
	SQLiteDatabase db = this.getReadableDatabase();

	Cursor cursor = db.query(TABLE_PERSON, new String[] { PERSONID,
		TIME, LOCATION, NOTES }, PERSONID + "=?",
		new String[] { String.valueOf(id) }, null, null, null, null);
	if (cursor != null)
	    cursor.moveToFirst();


	Person p = new Person();
	p.setPersonid(Integer.parseInt(cursor.getString(0)));
	p.setTime(cursor.getString(1));
	p.setLocation(cursor.getString(2));
	p.setNotes(cursor.getString(3));
	cursor.close();
	db.close();

	return p;
    }

    // Getting All Contacts
    public ArrayList<Person> Get_Persons() {
	try {
	    person_list.clear();

	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_PERSON;

	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
		do {
		    Person ps = new Person();
		    ///contact.setID(Integer.parseInt(cursor.getString(0)));
		    ///contact.setName(cursor.getString(1));
		    ///contact.setPhoneNumber(cursor.getString(2));
		    ///contact.setEmail(cursor.getString(3));
		    
		    ps.setPersonid(Integer.parseInt(cursor.getString(0)));
		    ps.setTime(cursor.getString(1));
		    ps.setLocation(cursor.getString(2));
		    ps.setNotes(cursor.getString(3));
		    
		    
		    // Adding contact to list
		    person_list.add(ps);
		} while (cursor.moveToNext());
	    }

	    // return contact list
	    cursor.close();
	    db.close();
	    return person_list;
	} catch (Exception e) {
	    // TODO: handle exception
	    Log.e("all_contact", "" + e);
	}

	return person_list;
    }

    // Updating single contact
    public int Update_Person(Person person) {
	SQLiteDatabase db = this.getWritableDatabase();

	ContentValues values = new ContentValues();
	///values.put(KEY_NAME, contact.getName());
	///values.put(KEY_AGE, contact.getPhoneNumber());
	///values.put(KEY_EMAIL, contact.getEmail());
	values.put(PERSONID, person.getPersonid());
	values.put(TIME, person.getTime());
	values.put(LOCATION, person.getLocation());
	values.put(NOTES, person.getNotes());


	// updating row
	return db.update(TABLE_PERSON, values, PERSONID + " = ?",
		new String[] { String.valueOf(person.getPersonid()) });
    }

    // Deleting single contact
    public void Delete_Person(int id) {
	SQLiteDatabase db = this.getWritableDatabase();
	db.delete(TABLE_PERSON, PERSONID + " = ?",
		new String[] { String.valueOf(id) });
	db.close();
    }

    // Getting contacts Count
    public int Get_Total_Persons() {
	String countQuery = "SELECT  * FROM " + TABLE_PERSON;
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.rawQuery(countQuery, null);
	cursor.close();

	// return count
	return cursor.getCount();
    }

}
