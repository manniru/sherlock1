package com.zahra.sherlock;

import java.util.ArrayList;

import com.mannir.sqlite.DefaultDBHelper;
import com.mannir.zahramannir.R;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class JsonPayLoad extends ListActivity {
             
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dblist);
        ArrayList results = new ArrayList();
        // -- SQLiteOpenHelper dbHelper = new DefaultDBHelper(this, MY_DATABASE_NAME, null, 1);
        SQLiteDatabase myDB = this.openOrCreateDatabase("contactsManager", SQLiteDatabase.OPEN_READWRITE, null);
        try { 

                Cursor c = myDB.query("contacts", null, null, null, null, null, null);
            
                /* Check if our result was valid. */ 
            if (c != null) {
            
                c.moveToFirst(); // it's very important to do this action otherwise your Cursor object did not get work
                int firstNameColumn = c.getColumnIndex("name"); 
                int ageColumn = c.getColumnIndex("age"); 
                 /* Check if at least one Result was returned. */ 
                 if (c.isFirst()) { 
                      int i = 0; 
                      /* Loop through all Results */ 
                      do { 
                           i++; 
                           String firstName = c.getString(firstNameColumn); 
                           int age = c.getInt(ageColumn); 
                           String ageColumName = c.getColumnName(ageColumn); 
                            
                           /* Add current Entry to results. */ 
                           results.add("" + i + ": " + firstName + " (" + ageColumName + ": " + age + ")"); 
                      } while (c.moveToNext()); 
                 } 
            }
                
        } catch (SQLiteException e) { 
        } finally { 
             if (myDB != null) 
                  myDB.close(); 
        }
        
        // -- android.R.layout.simple_list_item_1 is object which belong to ListActivity itself
        // -- you only need to add list object in your main layout file
        this.setListAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, results)); 
    }
}