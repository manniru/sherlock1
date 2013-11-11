package com.mannir.zahramannir;

import java.util.ArrayList;
import java.util.List;

import com.mannir.sqlite.DBHandler;
import com.mannir.sqlite.Users;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class SearchActivity extends Activity {
	private ListView lv;
	private EditText et;
	private String listview_array2[] = { "ONE", "TWO", "THREE", "FOUR", "FIVE","SIX", "SEVEN", "EIGHT", "NINE", "TEN" };
	private String[] listview_array;
	private ArrayList<String> array_sort= new ArrayList<String>();
	int textlength=0;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searchactivity);
		
		//////////////////// main code here /////////////////////
		DBHandler db = new DBHandler(this);
		// this: Activity class
		// Inserting Car data
		///Log.d("Insert: ", "Inserting ..");

	///	db.add(new Users("Mannir", "Male", "168", "29", "Black", "Comment here", "mannir@gmail.com"));
	///	db.add(new Users("Zahra", "Female", "150", "27", "Black", "Comment here", "zahra@gmail.com"));
		//System.out.println("Records Added!");
		//db.add(new Car("Zahra", "Maigari"));
		//db.add(new Car("Auwal", "Sani"));
		//db.add(new Car("Salisu", "Rabiu"));
		// Reads all cars
		Log.d("Reading: ","Reading all users...");
		ArrayList<Users> users = db.getAll();
		
		List<String> a = new ArrayList<String>();
		for(Users c: users) {
		    String data = "ID: "+c.id+", Name: "+c.name+", Gender: "+c.gender;
		    ////////////Log.d("Car: ", data);
		    a.add(c.name);
		}		
		
	//	String[] arr = a.toArray(new String[a.size()]);
		
		listview_array = a.toArray(new String[a.size()]); //new String[] {"Muhammad", "Mannir", "Zahra" };
		////////////////////////////////////////////////////////////

		lv = (ListView) findViewById(R.id.ListView01);
		et = (EditText) findViewById(R.id.EditText01);
		lv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, listview_array));

		et.addTextChangedListener(new TextWatcher() {
			
			public void afterTextChanged(Editable s) { }
			public void beforeTextChanged(CharSequence s,int start, int count, int after) { }
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				textlength = et.getText().length();
				array_sort.clear();
				for (int i = 0; i < listview_array.length; i++) {
					if (textlength <= listview_array[i].length()) {
						if(et.getText().toString().equalsIgnoreCase((String)listview_array[i].subSequence(0,textlength))) {
							array_sort.add(listview_array[i]);
							}
						}
					}
				lv.setAdapter(new ArrayAdapter<String>(SearchActivity.this,android.R.layout.simple_list_item_1, array_sort));
				}
			});
		}
	}