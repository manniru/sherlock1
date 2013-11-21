package com.mannir.zahramannir;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReportsAdd extends Activity {
    EditText personid_txt, time_txt, location_txt, notes_txt;
    Button addreports_btn;
    DBHandler dbhandler = new DBHandler(this);
    String Toast_msg = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reportsadd);
		
		addreports_btn = (Button) findViewById(R.id.addreports_btn);
		personid_txt = (EditText) findViewById(R.id.personid_txt);
		time_txt = (EditText) findViewById(R.id.time_txt);
		location_txt = (EditText) findViewById(R.id.location_txt);
		notes_txt = (EditText) findViewById(R.id.notes_txt);
		
		addreports_btn.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	int personid = Integer.parseInt(personid_txt.getText().toString());
		    	String time = time_txt.getText().toString();
		    	String location = location_txt.getText().toString();
		    	String notes = notes_txt.getText().toString();
		    	
		    	Person person = new Person();
		    	person.setPersonid(personid);
		    	person.setTime(time);
		    	person.setLocation(location);
		    	person.setNotes(notes);
		    	
		    	dbhandler.Add_Person(person);
				    Toast_msg = "Data inserted successfully";
				   Show_Toast(Toast_msg);
				 //   Reset_Text();
				    
					Intent view_user = new Intent(ReportsAdd.this, ReportsList.class);
					view_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
						| Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(view_user);
					finish();
		    	
			//Intent add_user = new Intent(Main_Screen.this,Add_Update_User.class);
			//add_user.putExtra("called", "add");
			//add_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK);
			//startActivity(add_user);
			//finish();
		    }
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reports_add, menu);
		return true;
	}

    public void Show_Toast(String msg) {
	Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
