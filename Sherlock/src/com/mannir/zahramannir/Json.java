package com.mannir.zahramannir;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class Json extends Activity {
	TextView txtViewParsedValue;
	private JSONObject jsonObject;
	
	String strParsedValue = null;
	
	private String strJSONValue = "{\"FirstObject\":{\"name\":\"Zahra\" ,\"gender\":\"Mannir\","
			+"\"sub\": { \"sub1\":[ {\"sub1_attr\":\"Zahra Maigari\" },{\"sub1_attr\":\"Mannir Ahmad\" }]}}}";

	  static InputStream iStream = null;
	    static JSONArray jarray = null;
	    static String json = "";


	@Override
	protected void onCreate(Bundle savedInstanceState) {
	//	super.onCreate(savedInstanceState);
	//	setContentView(R.layout.json);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webservices);
        
        txtViewParsedValue = (TextView) findViewById(R.id.textView1);
        
        try {
			parseJSON();
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.json, menu);
		return true;
	}

	 public void parseJSON() throws JSONException    {
	    	jsonObject = new JSONObject(strJSONValue);
	    	
	    	JSONObject object = jsonObject.getJSONObject("FirstObject");
	    	String attr1 = object.getString("name");
	    	String attr2 = object.getString("gender");
	    	
	    	strParsedValue="Attribute 1 value => "+attr1;
	    	strParsedValue+="\n Attribute 2 value => "+attr2;
	    	
	    	JSONObject subObject = object.getJSONObject("sub");
	    	JSONArray subArray = subObject.getJSONArray("sub1");

	    	strParsedValue+="\n Array Length => "+subArray.length();
	    	
	    	for(int i=0; i<subArray.length(); i++)
	    	{
	    		strParsedValue+="\n"+subArray.getJSONObject(i).getString("sub1_attr").toString();
	    	}
	    	
	    	txtViewParsedValue.setText(strParsedValue);
	    }
	 public JSONArray getJSONFromUrl(String url) {
		 
         StringBuilder builder = new StringBuilder();
          HttpClient client = new DefaultHttpClient();
          HttpGet httpGet = new HttpGet(url);
          try {
            HttpResponse response = client.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) {
              HttpEntity entity = response.getEntity();
              InputStream content = entity.getContent();
              BufferedReader reader = new BufferedReader(new InputStreamReader(content));
              String line;
              while ((line = reader.readLine()) != null) {
                builder.append(line);
              }
            } else {
              Log.e("==>", "Failed to download file");
            }
          } catch (ClientProtocolException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
         
      // Parse String to JSON object
      try {
          jarray = new JSONArray( builder.toString());
      } catch (JSONException e) {
          Log.e("JSON Parser", "Error parsing data " + e.toString());
      }

      // return JSON Object
      return jarray;

  }
}
