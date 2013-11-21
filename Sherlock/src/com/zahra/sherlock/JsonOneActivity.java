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

import com.mannir.zahramannir.R;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
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
ResponseHandler<String> responseHandler = new BasicResponseHandler();
List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
nameValuePairs.add(new BasicNameValuePair("jsonpayload", jsonString));
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
