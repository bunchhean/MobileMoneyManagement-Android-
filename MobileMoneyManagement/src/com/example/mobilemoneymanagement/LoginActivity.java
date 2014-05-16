package com.example.mobilemoneymanagement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends Activity {
	Button btnLogin;
	EditText txtUsername, txtPassword;
	// User Session Manager Class
    UserSessionManager session;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        
        session = new UserSessionManager(getApplicationContext()); 
        if(session.checkLogin() == false){
	        Intent launchNewIntent = new Intent(LoginActivity.this,TabHostActivity.class);
	    	startActivityForResult(launchNewIntent, 0);
        }
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	txtUsername = (EditText) findViewById(R.id.txtUsername);
            	txtPassword = (EditText) findViewById(R.id.txtPassword);
            	String usernameValue = "";
            	String passwordValue = "";
            	
            	if(txtUsername.getText().toString().equals(usernameValue)){
            		Toast.makeText(getApplicationContext(), "Please enter username!",
            				   Toast.LENGTH_LONG).show();
            	}else if(txtPassword.getText().toString().equals(passwordValue)){
            		Toast.makeText(getApplicationContext(), "Please enter password!",
         				   Toast.LENGTH_LONG).show();
            	}else{
            		
            		String pass_data = "username="+txtUsername.getText().toString()+ "&password="+ txtPassword.getText().toString();
            		new HttpAsyncTask().execute("http://api.gift-i.com/users/login.json?" + pass_data);
            	 
            	}
            }
        });

    }
    
    public static String GET(String url){
        InputStream inputStream = null;
        String result = "";
        try {
 
            // create HttpClient
            HttpClient httpclient = new DefaultHttpClient();
 
            // make GET request to the given URL
            HttpResponse httpResponse = httpclient.execute(new HttpGet(url));
 
            // receive response as inputStream
            inputStream = httpResponse.getEntity().getContent();
 
            // convert inputstream to string
            if(inputStream != null)
                result = convertInputStreamToString(inputStream);
            else
                result = "Did not work!";
 
        } catch (Exception e) {
            Log.d("InputStream", e.getLocalizedMessage());
        }
 
        return result;
    }
    
    private static String convertInputStreamToString(InputStream inputStream) throws IOException{
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;
 
        inputStream.close();
        return result;
 
    }
 
    public boolean isConnected(){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Activity.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) 
                return true;
            else
                return false;   
    }

    public void onClickToRegister(View arg0) {
    	Intent launchNewIntent = new Intent(LoginActivity.this,RegisterActivity.class);
    	startActivityForResult(launchNewIntent, 0);
	}
    
 
   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.login, menu);
        return true;
    } 
    
    
    private class HttpAsyncTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {

            return GET(urls[0]);
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
           
            //etResponse.setText(result);
            try {
            	JSONObject json = new JSONObject(result); // convert String to JSONObject
            	//JSONArray articles = json.getJSONArray("login"); // get articles array 
            	/*articles.length(); // --> 2
            	articles.getJSONObject(0); // get first article in the array
            	articles.getJSONObject(0).names(); // get first article keys [title,url,categories,tags]*/
            	//String loginStatus = articles.getJSONObject(1).toString(); // return an article url
            	
            	String responseString = json.getString("login");
            	JSONObject json2 = new JSONObject(responseString); 
            	String loginStatus = json2.getString("status");
            	
            	if(loginStatus.equals("success")){
            		session.createUserLoginSession(result);
            		
            		Intent launchNewIntent = new Intent(LoginActivity.this,TabHostActivity.class);
                	startActivityForResult(launchNewIntent, 0);
            	}
            	Toast.makeText(getBaseContext(), loginStatus, Toast.LENGTH_LONG).show();
            	
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Toast.makeText(getBaseContext(), "Hello Not Found", Toast.LENGTH_LONG).show();
			}
       }
    }
}







