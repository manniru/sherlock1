package com.zahra.sherlock;

import java.util.ArrayList;

import com.mannir.zahramannir.Contact;

//import com.mannir.zahramannir.Contact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SherlockDB";
    private static final String TABLE_PERSON = "Person";
    private static final String TABLE_REPORTS = "Reports";
    private static final String PERSONID = "personid";
    private static final String TIME = "time";
    private static final String LOCATION = "location";
    private static final String NOTES = "notes";   
    private final ArrayList<Person> person_list = new ArrayList<Person>();
    private final ArrayList<Reports> reports_list = new ArrayList<Reports>();
    

    public DBHandler(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
    	db.execSQL("CREATE TABLE IF NOT EXISTS Person " +
        		"(personid INTEGER PRIMARY KEY, name VARCHAR, gender VARCHAR, height VARCHAR, age INT(3)," +
        		"haircolor VARCHAR, comments VARCHAR, email VARCHAR);");
        
        db.execSQL("CREATE TABLE IF NOT EXISTS Reports " +
        		"(reportid INTEGER, time VARCHAR, location VARCHAR, notes VARCHAR, personid INTEGER);");
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
	onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void Add_Person(Person person) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put("personid", person.get_id());
	values.put("name", person.get_name());
	values.put("age", person.get_age());
	values.put("email", person.get_email());
	values.put("gender", person.get_gender());
	values.put("height", person.get_height());
	values.put("haircolor", person.get_haircolor());
	values.put("comments", person.get_comments());
	db.insert(TABLE_PERSON, null, values);
	db.close(); // Closing database connection
    }
    
    public void Add_Reports(Reports reports) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put("reportsid", reports.getReportsid());
	values.put("time", reports.getTime());
	values.put("location", reports.getLocation());
	values.put("notes", reports.getNotes());
	values.put("personid", reports.getPersonid());
	db.insert(TABLE_REPORTS, null, values);
	db.close(); // Closing database connection
    }

    Person Get_Person(int id) {
	SQLiteDatabase db = this.getReadableDatabase();

	Cursor cursor = db.query(TABLE_PERSON, new String[] { "personid",
		"name", "age", "email", "gender", "height", "haircolor", "comments" }, "personid" + "=?",
		new String[] { String.valueOf(id) }, null, null, null, null);
	if (cursor != null)
	    cursor.moveToFirst();

	Person person = new Person(Integer.parseInt(cursor.getString(0)),
		cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
		 cursor.getString(5), cursor.getString(6), cursor.getString(7));
	// return contact
	cursor.close();
	db.close();

	return person;
    }

    Reports Get_Report(int id) {
	SQLiteDatabase db = this.getReadableDatabase();

	Cursor cursor = db.query(TABLE_PERSON, new String[] { PERSONID,
		TIME, LOCATION, NOTES }, PERSONID + "=?",
		new String[] { String.valueOf(id) }, null, null, null, null);
	if (cursor != null)
	    cursor.moveToFirst();
	//private final ArrayList<Reports> reports_list = new ArrayList<Reports>();

	Reports r = new Reports();
	r.setPersonid(Integer.parseInt(cursor.getString(0)));
	r.setTime(cursor.getString(1));
	r.setLocation(cursor.getString(2));
	r.setNotes(cursor.getString(3));
	cursor.close();
	db.close();

	return r;
    }


    
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
		    Person contact = new Person();
		    ///contact.setID(Integer.parseInt(cursor.getString(0)));
		    ///contact.setName(cursor.getString(1));
		    ///contact.setPhoneNumber(cursor.getString(2));
		    ///contact.setEmail(cursor.getString(3));
		    contact.set_id(Integer.parseInt(cursor.getString(0)));
		    contact.set_name(cursor.getString(1));
		    contact.set_age(cursor.getString(2));
		    contact.set_email(cursor.getString(3));
		    contact.set_gender(cursor.getString(4));
		    contact.set_height(cursor.getString(5));
		    contact.set_haircolor(cursor.getString(6));
		    contact.set_comments(cursor.getString(7));
		    // Adding contact to list
		    person_list.add(contact);
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
    
    public ArrayList<Reports> Get_Reports() {
	try {
	    reports_list.clear();

	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_PERSON;

	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
		do {
		    Reports rp = new Reports();
		    ///contact.setID(Integer.parseInt(cursor.getString(0)));
		    ///contact.setName(cursor.getString(1));
		    ///contact.setPhoneNumber(cursor.getString(2));
		    ///contact.setEmail(cursor.getString(3));
		    
		    rp.setPersonid(Integer.parseInt(cursor.getString(0)));
		    rp.setTime(cursor.getString(1));
		    rp.setLocation(cursor.getString(2));
		    rp.setNotes(cursor.getString(3));
		    
		    
		    // Adding contact to list
		    reports_list.add(rp);
		} while (cursor.moveToNext());
	    }

	    // return contact list
	    cursor.close();
	    db.close();
	    return reports_list;
	} catch (Exception e) {
	    // TODO: handle exception
	    Log.e("all_contact", "" + e);
	}

	return reports_list;
    }
    
    public int Update_Person(Person person) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put("name", person.get_name());
	values.put("age", person.get_age());
	values.put("email", person.get_email());
	values.put("gender", person.get_gender());
	values.put("height", person.get_height());
	values.put("haircolor", person.get_haircolor());
	values.put("comments", person.get_comments());
	return db.update(TABLE_PERSON, values, "personid = ?",
		new String[] { String.valueOf(person.get_id()) });
    }

    public int Update_Reports(Reports reports) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	values.put(PERSONID, reports.getPersonid());
	values.put(TIME, reports.getTime());
	values.put(LOCATION, reports.getLocation());
	values.put(NOTES, reports.getNotes());
	return db.update(TABLE_REPORTS, values, "reportsid = ?",
		new String[] { String.valueOf(reports.getPersonid()) });
    }
    
    public void Delete_Person(int id) {
	SQLiteDatabase db = this.getWritableDatabase();
	db.delete(TABLE_PERSON, "personid = ?",
		new String[] { String.valueOf(id) });
	db.close();
    }

    public void Delete_Reports(int id) {
	SQLiteDatabase db = this.getWritableDatabase();
	db.delete(TABLE_REPORTS, "reportsid = ?",
		new String[] { String.valueOf(id) });
	db.close();
    }
    
    public int Get_Total_Person() {
	String countQuery = "SELECT  * FROM " + TABLE_PERSON;
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.rawQuery(countQuery, null);
	cursor.close();
	return cursor.getCount();
    }

    public int Get_Total_Reports() {
	String countQuery = "SELECT  * FROM " + TABLE_REPORTS;
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.rawQuery(countQuery, null);
	cursor.close();
	return cursor.getCount();
    }

}
