package com.zahra.sherlock;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import com.mannir.sqlite.DefaultDBHelper;
import com.mannir.zahramannir.R;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.webkit.WebView;
import android.widget.Toast;

public class JsonOneActivity extends Activity {
private WebView browser;
private HttpClient client;
@Override
public void onCreate(Bundle icicle) {
super.onCreate(icicle);
setContentView(R.layout.webview);


if( Build.VERSION.SDK_INT >= 9){
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
    StrictMode.setThreadPolicy(policy); 
}

String pageURL = getString(R.string.url);
String jsonString = getString(R.string.json);

browser = (WebView) findViewById(R.id.webkit);

client = new DefaultHttpClient();
sendJsonAndProcessReply(pageURL, jsonString);
        }

private void sendJsonAndProcessReply(String pageURL, String jsonString) {
HttpPost postMethod = new HttpPost(pageURL);
try {
	
	JSONObject jObject;
	JSONArray jArray = new JSONArray();
	
	
	SQLiteDatabase myDB = this.openOrCreateDatabase("contactsManager", SQLiteDatabase.OPEN_READWRITE, null);

    Cursor c = myDB.query("contacts", null, null, null, null, null, null);
    
    jObject = new JSONObject();
    
    if (c != null) {
        
        c.moveToFirst(); // it's very important to do this action otherwise your Cursor object did not get work
        int firstNameColumn = c.getColumnIndex("name"); 
        int ageColumn = c.getColumnIndex("gender"); 
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
                 ///  results.add("" + i + ": " + firstName + " (" + ageColumName + ": " + age + ")");
                   
           	   
        	    jObject.put("personid", c.getString(1));
        	    jObject.put("name", c.getString(2));
        	    jObject.put("gender", c.getString(3));
        	    jObject.put("height", c.getString(4));
        	    jObject.put("age", c.getString(5));
        	    jObject.put("haircolor", c.getString(6));
        	    jObject.put("comments", c.getString(7));
        	   // jObject.put("email", c.getString(8));
        	    System.out.println(c.getColumnName(2));
        	    jArray.put(jObject);
        	    
                   System.out.println(firstName);
              } while (c.moveToNext()); 
         } 
    }
/**
	while(c.moveToNext()) {

	    jObject = new JSONObject();
	    jObject.put("personid", c.getColumnName(1));
	    jObject.put("name", c.getColumnName(2));
	    jObject.put("gender", c.getColumnName(3));
	    jObject.put("height", c.getColumnName(4));
	    jObject.put("age", c.getColumnName(5));
	    jObject.put("haircolor", c.getColumnName(6));
	    jObject.put("comments", c.getColumnName(7));
	   // jObject.put("email", c.getColumnName(8));
	    System.out.println(c.getColumnName(2));
	    jArray.put(jObject);

	}	
*/	
    String json1 = "{\"userid\":\"12345\", \"personList\":[{\"name\":\"zahra\",\"gender\":\"female\"},{\"name\":\"mannir\",\"gender\":\"male\"},{\"name\":\"lawal\",\"gender\":\"male\"}]}";
    
    String json = "{\"userid\":\"12345\", \"personList\":"+jArray.toString()+"}";
	
    System.out.println("json1:"+json1);	
    System.out.println("json2:"+json);	
	
ResponseHandler<String> responseHandler = new BasicResponseHandler();
List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
nameValuePairs.add(new BasicNameValuePair("jsonpayload", json));
postMethod.setEntity(new UrlEncodedFormEntity(nameValuePairs));

String responseBody = client.execute(postMethod, responseHandler);
String page = generatePage(responseBody);
browser.loadData(page, "text/html", "UTF-8");


} catch (Throwable t) {
	Toast.makeText(this, "Request failed: " + t.toString(),Toast.LENGTH_LONG).show();
}
}

private String generatePage(String content) {
		return "<html><body><p>" + content + "</p></body></html>";
}

}
