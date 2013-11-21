package com.mannir.zahramannir;

import java.util.ArrayList;

//import com.mannir.zahramannir.Main_Screen.Person_Adapter;

//import com.mannir.zahramannir.Main_Screen.Person_Adapter;
//import com.mannir.zahramannir.Main_Screen.Person_Adapter.UserHolder;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class ReportsList extends Activity {
	Button addreport_btn;
    ListView Person_listview;
    ArrayList<Person> person_data = new ArrayList<Person>();
   // Person_Adapter cAdapter;
    DBHandler db;
    String Toast_msg;
    
    ListView lv; 
    Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportslist);
/**
		 for (Integer j = 0; j < 10; j++){ 


		        String lister = "mm"; // c.getString(c.getColumnIndex("names_of"));

		        String[] items = {lister};
		        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
		        layout.setAdapter(adapter);
		    c.moveToNext();

		    };

	
	try {
			addreport_btn = (Button) findViewById(R.id.addreport_btn);
			
		  //  Person_listview = (ListView) findViewById(R.id.listView1);
		    System.out.println("list view here");
		   // Person_listview.setItemsCanFocus(false);


		   // Set_Referash_Data();

		} catch (Exception e) { Log.e("some error", "" + e); }
		*/
		addreport_btn.setOnClickListener(new View.OnClickListener() {

		    @Override
		    public void onClick(View v) {
		           Intent intent = new Intent(ReportsList.this,ReportsAdd.class); 
		             startActivity(intent);

		    }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reports_list, menu);
		return true;
	}
	
	
   

}
