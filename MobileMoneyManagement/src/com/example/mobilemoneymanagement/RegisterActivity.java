package com.example.mobilemoneymanagement;

import java.util.ArrayList;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity {
	
	Button btnRegister;
	EditText txtUsername, txtPassword, txtEmail, txtPhone;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		setTitle("Register");
		
		
		 btnRegister = (Button) findViewById(R.id.btnRegister);
		 btnRegister.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	            	txtUsername = (EditText) findViewById(R.id.txtUsername);
	            	txtPassword = (EditText) findViewById(R.id.txtPassword);
	            	txtEmail = (EditText) findViewById(R.id.txtEmail);
	            	txtPhone = (EditText) findViewById(R.id.txtPhone);
	            	
	            	if(txtUsername.getText().toString().equals("")){
	            		Toast.makeText(getApplicationContext(), "Please enter username!",
	            				   Toast.LENGTH_LONG).show();
	            	}else if(txtPassword.getText().toString().equals("")){
	            		Toast.makeText(getApplicationContext(), "Please enter password!",
	         				   Toast.LENGTH_LONG).show();
	            	}else if(txtEmail.getText().toString().equals("")){
	            		Toast.makeText(getApplicationContext(), "Please enter email!",
		         				   Toast.LENGTH_LONG).show();
		            }else if(!isEmailValid(txtEmail.getText().toString())){
	            		Toast.makeText(getApplicationContext(), "Wrong email formart!",
		         				   Toast.LENGTH_LONG).show();
		            }else if(txtPhone.getText().toString().equals("")){
	            		Toast.makeText(getApplicationContext(), "Please enter phone number!",
		         				   Toast.LENGTH_LONG).show();
		            }else{
		            	
		            	new HttpAsyncTask().execute("http://api.gift-i.com/users/register.json");
		            }
	            }
	        });

	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}
	
	boolean isEmailValid(CharSequence email) {
		   return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}
	
	
	
	
	//////////////////////////////////// Post Data To Server ////////////////////////////////////////////////


	
	private class HttpAsyncTask extends AsyncTask<String, Void, String> {
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();;
		
        @Override
        protected String doInBackground(String... urls) {

        	try{

        		HttpClient httpclient = new DefaultHttpClient();

        		HttpPost httppost = new HttpPost("http://api.gift-i.com/users/register.json");

        		httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

        		httpclient.execute(httppost);

        		}catch(Exception e){

        			//Log.e(���������log_tag���������, ���������Error in http connection ���������+e.toString());

        		}

        		return null;
        }
        // onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(String result) {
           
            //etResponse.setText(result);
            Toast.makeText(getBaseContext(), "You are successfuly register", Toast.LENGTH_LONG).show(); 
            
            Intent launchNewIntent = new Intent(RegisterActivity.this,LoginActivity.class);
        	startActivityForResult(launchNewIntent, 0);
			
       }
        
        protected void onPreExecute() {

        	nameValuePairs.add(new BasicNameValuePair("username", txtUsername.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("password", txtPassword.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("email", txtEmail.getText().toString()));
            nameValuePairs.add(new BasicNameValuePair("phone", txtPhone.getText().toString()));
        }
    }
}

