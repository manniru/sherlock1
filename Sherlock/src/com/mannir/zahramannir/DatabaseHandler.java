package com.mannir.zahramannir;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_CONTACTS = "contacts";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_AGE = "age";
    private static final String KEY_EMAIL = "email";
    
    private static final String KEY_GENDER = "gender";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_HAIRCOLOR = "haircolor";
    private static final String KEY_COMMENTS = "comments";
    		
    private final ArrayList<Contact> contact_list = new ArrayList<Contact>();

    public DatabaseHandler(Context context) {
	super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
	String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
		+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
		+ KEY_GENDER + " TEXT," + KEY_HEIGHT + " TEXT,"
		+ KEY_HAIRCOLOR + " TEXT," + KEY_COMMENTS + " TEXT,"
		+ KEY_AGE + " TEXT," + KEY_EMAIL + " TEXT" + ")";
	db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	// Drop older table if existed
	db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

	// Create tables again
	onCreate(db);
    }

    /**
     * All CRUD(Create, Read, Update, Delete) Operations
     */

    // Adding new contact
    public void Add_Contact(Contact contact) {
	SQLiteDatabase db = this.getWritableDatabase();
	ContentValues values = new ContentValues();
	///values.put(KEY_NAME, contact.getName()); // Contact Name
	///values.put(KEY_AGE, contact.getPhoneNumber()); // Contact Phone
	//values.put(KEY_EMAIL, contact.getEmail()); // Contact Email
	values.put(KEY_NAME, contact.get_name());
	values.put(KEY_AGE, contact.get_age());
	values.put(KEY_EMAIL, contact.get_email());
	values.put(KEY_GENDER, contact.get_gender());
	values.put(KEY_HEIGHT, contact.get_height());
	values.put(KEY_HAIRCOLOR, contact.get_haircolor());
	values.put(KEY_COMMENTS, contact.get_comments());
	// Inserting Row
	db.insert(TABLE_CONTACTS, null, values);
	db.close(); // Closing database connection
    }

    // Getting single contact
    Contact Get_Contact(int id) {
	SQLiteDatabase db = this.getReadableDatabase();

	Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
		KEY_NAME, KEY_AGE, KEY_EMAIL, KEY_GENDER, KEY_HEIGHT, KEY_HAIRCOLOR, KEY_COMMENTS }, KEY_ID + "=?",
		new String[] { String.valueOf(id) }, null, null, null, null);
	if (cursor != null)
	    cursor.moveToFirst();

	Contact contact = new Contact(Integer.parseInt(cursor.getString(0)),
		cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
		 cursor.getString(5), cursor.getString(6), cursor.getString(7));
	// return contact
	cursor.close();
	db.close();

	return contact;
    }

    // Getting All Contacts
    public ArrayList<Contact> Get_Contacts() {
	try {
	    contact_list.clear();

	    // Select All Query
	    String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

	    SQLiteDatabase db = this.getWritableDatabase();
	    Cursor cursor = db.rawQuery(selectQuery, null);

	    // looping through all rows and adding to list
	    if (cursor.moveToFirst()) {
		do {
		    Contact contact = new Contact();
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
		    contact_list.add(contact);
		} while (cursor.moveToNext());
	    }

	    // return contact list
	    cursor.close();
	    db.close();
	    return contact_list;
	} catch (Exception e) {
	    // TODO: handle exception
	    Log.e("all_contact", "" + e);
	}

	return contact_list;
    }

    // Updating single contact
    public int Update_Contact(Contact contact) {
	SQLiteDatabase db = this.getWritableDatabase();

	ContentValues values = new ContentValues();
	///values.put(KEY_NAME, contact.getName());
	///values.put(KEY_AGE, contact.getPhoneNumber());
	///values.put(KEY_EMAIL, contact.getEmail());
	values.put(KEY_NAME, contact.get_name());
	values.put(KEY_AGE, contact.get_age());
	values.put(KEY_EMAIL, contact.get_email());
	values.put(KEY_GENDER, contact.get_gender());
	values.put(KEY_HEIGHT, contact.get_height());
	values.put(KEY_HAIRCOLOR, contact.get_haircolor());
	values.put(KEY_COMMENTS, contact.get_comments());
	
	System.out.println(contact.get_id());
	System.out.println(contact.get_name());
	System.out.println(contact.get_age());
	System.out.println(contact.get_email());
	System.out.println(contact.get_gender());
	System.out.println(contact.get_height());
	System.out.println(contact.get_haircolor());
	System.out.println(contact.get_comments());

	// updating row
	return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
		new String[] { String.valueOf(contact.get_id()) });
    }

    // Deleting single contact
    public void Delete_Contact(int id) {
	SQLiteDatabase db = this.getWritableDatabase();
	db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
		new String[] { String.valueOf(id) });
	db.close();
    }

    // Getting contacts Count
    public int Get_Total_Contacts() {
	String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
	SQLiteDatabase db = this.getReadableDatabase();
	Cursor cursor = db.rawQuery(countQuery, null);
	cursor.close();

	// return count
	return cursor.getCount();
    }

}
