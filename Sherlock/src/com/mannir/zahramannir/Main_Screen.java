package com.mannir.zahramannir;

import java.util.ArrayList;

import org.json.JSONArray;

import com.zahra.camera.MakePhotoActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main_Screen extends Activity {
	// url to make request
	private static String url = "http://esystems.me/json";
	 
	// JSON Node names
	private static final String TAG_CONTACTS = "contacts";
	private static final String TAG_ID = "id";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_GENDER = "gender";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_PHONE_MOBILE = "mobile";
	private static final String TAG_PHONE_HOME = "home";
	private static final String TAG_PHONE_OFFICE = "office";
	 
	// contacts JSONArray
	JSONArray contacts = null;
	///////////////////////////////////////
    Button add_btn, search_btn, json_btn, reports_btn, camera_btn;
    ListView Contact_listview;
    ArrayList<Contact> contact_data = new ArrayList<Contact>();
    Contact_Adapter cAdapter;
    DatabaseHandler db;
    String Toast_msg;

    @Override
    public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.main);
	try {
	    Contact_listview = (ListView) findViewById(R.id.list);
	    Contact_listview.setItemsCanFocus(false);
	    add_btn = (Button) findViewById(R.id.add_btn);
	    search_btn = (Button) findViewById(R.id.search_btn);
	    json_btn = (Button) findViewById(R.id.json_btn);
	    reports_btn = (Button) findViewById(R.id.reports_btn);
	    camera_btn = (Button) findViewById(R.id.camera_btn);

	    Set_Referash_Data();

	} catch (Exception e) {
	    Log.e("some error", "" + e);
	}
	add_btn.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
		Intent add_user = new Intent(Main_Screen.this,
			Add_Update_User.class);
		add_user.putExtra("called", "add");
		add_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
			| Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(add_user);
		finish();
	    }
	});
	
	search_btn.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
	           Intent intent = new Intent(Main_Screen.this,SearchActivity.class); 
	             startActivity(intent);
	    	/**
		Intent add_user = new Intent(Main_Screen.this,Add_Update_User.class);
		add_user.putExtra("called", "add");
		add_user.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(add_user);
		finish();
		*/
	    }
	});


	
	json_btn.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
	           Intent intent = new Intent(Main_Screen.this,Json.class); 
	             startActivity(intent);

	    }
	});
	
	reports_btn.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
	           Intent intent = new Intent(Main_Screen.this,ReportsList.class); 
	             startActivity(intent);

	    }
	});
	
	camera_btn.setOnClickListener(new View.OnClickListener() {

	    @Override
	    public void onClick(View v) {
	           Intent intent = new Intent(Main_Screen.this,MakePhotoActivity.class); 
	             startActivity(intent);

	    }
	});
	
	
	
    }


    public void Set_Referash_Data() {
	contact_data.clear();
	db = new DatabaseHandler(this);
	ArrayList<Contact> contact_array_from_db = db.Get_Contacts();

	for (int i = 0; i < contact_array_from_db.size(); i++) {
/**
	    int tidno = contact_array_from_db.get(i).getID();
	    String name = contact_array_from_db.get(i).getName();
	    String mobile = contact_array_from_db.get(i).getPhoneNumber();
	    String email = contact_array_from_db.get(i).getEmail();
	    */
		
	    int tidno = contact_array_from_db.get(i).get_id();
	    String name = contact_array_from_db.get(i).get_name();
	    String mobile = contact_array_from_db.get(i).get_age();
	    String email = contact_array_from_db.get(i).get_email();
	    
	    String gender = contact_array_from_db.get(i).get_gender();
	    String height = contact_array_from_db.get(i).get_height();
	    String haircolor = contact_array_from_db.get(i).get_haircolor();
	    String comments = contact_array_from_db.get(i).get_comments();
	    
	    Contact cnt = new Contact();
	    /**
	    cnt.setID(tidno);
	    cnt.setName(name);
	    cnt.setEmail(email);
	    cnt.setPhoneNumber(mobile);
	    */
	    cnt.set_id(tidno);
	    cnt.set_name(name);
	    cnt.set_email(email);
	    cnt.set_age(mobile);
	    cnt.set_gender(gender);
	    cnt.set_height(height);
	    cnt.set_haircolor(haircolor);
	    cnt.set_comments(comments);
	    

	    contact_data.add(cnt);
	    
	    System.out.println(mobile);
	}
	db.close();
	cAdapter = new Contact_Adapter(Main_Screen.this, R.layout.listview_row,	contact_data);
	Contact_listview.setAdapter(cAdapter);
	cAdapter.notifyDataSetChanged();
    }

    public void Show_Toast(String msg) {
	Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResume() {
	// TODO Auto-generated method stub
	super.onResume();
	Set_Referash_Data();

    }

    public class Contact_Adapter extends ArrayAdapter<Contact> {
	Activity activity;
	int layoutResourceId;
	Contact user;
	ArrayList<Contact> data = new ArrayList<Contact>();

	public Contact_Adapter(Activity act, int layoutResourceId,
		ArrayList<Contact> data) {
	    super(act, layoutResourceId, data);
	    this.layoutResourceId = layoutResourceId;
	    this.activity = act;
	    this.data = data;
	    notifyDataSetChanged();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
	    View row = convertView;
	    UserHolder holder = null;

	    if (row == null) {
		LayoutInflater inflater = LayoutInflater.from(activity);

		row = inflater.inflate(layoutResourceId, parent, false);
		holder = new UserHolder();
		holder.name = (TextView) row.findViewById(R.id.user_name_txt);
		holder.gender = (TextView) row.findViewById(R.id.user_gender_txt);
		holder.height = (TextView) row.findViewById(R.id.user_height_txt);
		holder.age = (TextView) row.findViewById(R.id.user_age_txt);
		holder.haircolor = (TextView) row.findViewById(R.id.user_haircolor_txt);
		holder.comments = (TextView) row.findViewById(R.id.user_comment_txt);
		holder.email = (TextView) row.findViewById(R.id.user_email_txt);		
		holder.edit = (Button) row.findViewById(R.id.btn_update);
		holder.delete = (Button) row.findViewById(R.id.btn_delete);
		

	
		
		row.setTag(holder);
	    } else {
		holder = (UserHolder) row.getTag();
	    }
	    user = data.get(position);
	    /**
	    holder.edit.setTag(user.getID());
	    holder.delete.setTag(user.getID());
	    holder.name.setText(user.getName());
	    holder.email.setText(user.getEmail());
	    holder.number.setText(user.getPhoneNumber());
		*/
	    holder.edit.setTag(user.get_id());
	    holder.delete.setTag(user.get_id());
	    holder.name.setText("Name: " + user.get_name());
	    holder.email.setText("Height: " + user.get_email());
	    holder.age.setText("Gender: " + user.get_age());
	    
	    holder.gender.setText("Hair color: " + user.get_gender());
	    holder.height.setText("Comment: " + user.get_height());
	    holder.haircolor.setText("Age: " + user.get_haircolor());
	    holder.comments.setText("Email: " + user.get_comments());
	    
	    holder.edit.setOnClickListener(new OnClickListener() {
		@Override
		public void onClick(View v) {
		    // TODO Auto-generated method stub
		    Log.i("Edit Button Clicked", "**********");

		    Intent update_user = new Intent(activity,
			    Add_Update_User.class);
		    update_user.putExtra("called", "update");
		    update_user.putExtra("USER_ID", v.getTag().toString());
		    activity.startActivity(update_user);

		}
	    });
	    holder.delete.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(final View v) {
		    // TODO Auto-generated method stub

		    // show a message while loader is loading

		    AlertDialog.Builder adb = new AlertDialog.Builder(activity);
		    adb.setTitle("Delete?");
		    adb.setMessage("Are you sure you want to delete ");
		    final int user_id = Integer.parseInt(v.getTag().toString());
		    adb.setNegativeButton("Cancel", null);
		    adb.setPositiveButton("Ok",
			    new AlertDialog.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog,
					int which) {
				    // MyDataObject.remove(positionToRemove);
				    DatabaseHandler dBHandler = new DatabaseHandler(
					    activity.getApplicationContext());
				    dBHandler.Delete_Contact(user_id);
				    Main_Screen.this.onResume();

				}
			    });
		    adb.show();
		}

	    });
	    return row;

	}

	class UserHolder {
	    TextView name;
	    TextView email;
	    TextView age;
	    Button edit;
	    Button delete;
	    
	    TextView gender;
	    TextView height;
	    TextView haircolor;
	    TextView comments;

	    
	}

    }

}
