package com.mannir.zahramannir;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mannir.sqlite.Users;

public class SimpleJson extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String jString = "{\"username\": \"Zahra\", \"message\": \"Hi, I am Zahra\"}  ";

/**
        GsonBuilder gsonb = new GsonBuilder();
        Gson gson = gsonb.create();
        Post pst = null;

        pst = gson.fromJson(jString,  Post.class);
*/
      //  Log.d("TAggeD", pst.username + pst.message);

        
        
        
        
     //   Log.d("TAGGED", gson.toJson(pst));

        System.out.println("Record posted to web service json server!");
    }
    
    public void postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://esystems.me/json");

        try {
            // Add your data
            List<Users> user = new ArrayList<Users>(2);
            ///user.add(new BasicNameValuePair("id", "12345"));
            ///user.add(new BasicNameValuePair("dd", "Hi"));
            ///httppost.setEntity(new UrlEncodedFormEntity(user));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }   
    
}