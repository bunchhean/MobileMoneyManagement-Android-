package com.example.mobilemoneymanagement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class ExpenseAddNewActivity extends Activity{

  private Spinner spinner;
  private Button button;
  EditText txtexpamount,txtexpnote;
  String date;

  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_expense_add_new);

	addItemsOnSpinner();
	addListenerOnButton();
  }

  // add items into spinner dynamically
  public void addItemsOnSpinner() {

	spinner = (Spinner) findViewById(R.id.spinner);
	List<String> list = new ArrayList<String>();
	list.add("Food");
	list.add("RentHouse");
	list.add("Closing");
	list.add("Party");
	list.add("Material");
	ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
	dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinner.setAdapter(dataAdapter);
 }
  public void addListenerOnButton() {
	spinner = (Spinner) findViewById(R.id.spinner);
	button = (Button) findViewById(R.id.btnexpense);
	txtexpamount = (EditText)findViewById(R.id.editexpamount);
	txtexpnote = (EditText)findViewById(R.id.editexpnote);
	date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	button.setOnClickListener(new OnClickListener() {
	  @Override
	  public void onClick(View v) {
		  if(txtexpamount.getText().toString().equals("")){
	      		Toast.makeText(getApplicationContext(), "Please enter you amount!",
	      				   Toast.LENGTH_LONG).show();
	      		}else if(String.valueOf(spinner.getSelectedItem()).equals("")){
	      			Toast.makeText(getApplicationContext(), "Please select your cagegory at least one!",
	       				   Toast.LENGTH_LONG).show();
	      		}else{
	      			new HttpAsyncTask().execute("http://api.gift-i.com/expenses/add.json");
	      		}

//	    Toast.makeText(ExpenseAddNewActivity.this,String.valueOf(spinner.getSelectedItem())+" is selected",
//			Toast.LENGTH_SHORT).show();
	  }
	});
  }
  /*=============================== Post Data To Server ================================*/
	
  private class HttpAsyncTask extends AsyncTask<String, Void, String> {
  ArrayList<NameValuePair> nameValuePair = new ArrayList<NameValuePair>();;

  @Override
  protected String doInBackground(String... urls) {

  try{

  HttpClient httpclient = new DefaultHttpClient();

  HttpPost httppost = new HttpPost("http://api.gift-i.com/expenses/add.json");

  httppost.setEntity(new UrlEncodedFormEntity(nameValuePair));

  httpclient.execute(httppost);

  }catch(Exception e){

  }

  return null;
  }
  // onPostExecute displays the results of the AsyncTask.
  @Override
  protected void onPostExecute(String result) {

  //etResponse.setText(result);
  	Toast.makeText(getBaseContext(), "Expense inserted sucessfully"+date.toString(), Toast.LENGTH_LONG).show(); 

  	Intent launchNewIntent = new Intent(ExpenseAddNewActivity.this,ExpenseActivity.class);
  startActivityForResult(launchNewIntent, 0);

  }

  protected void onPreExecute() {
  nameValuePair.add(new BasicNameValuePair("category", String.valueOf(spinner.getSelectedItem())));
  nameValuePair.add(new BasicNameValuePair("amount", txtexpamount.getText().toString()));
  nameValuePair.add(new BasicNameValuePair("note", txtexpnote.getText().toString()));
  nameValuePair.add(new BasicNameValuePair("dateexpense", date.toString()));
 
  }
  }
  @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_add_new, menu);
		return true;
	}
}
