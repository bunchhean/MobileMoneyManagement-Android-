package com.example.mobilemoneymanagement;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MoreActivity extends Activity {
	UserSessionManager objSession;
	TextView btnHelpCenter,tvLogout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_more);
		btnHelpCenter = (TextView) findViewById(R.id.tvHelpCenter);
		tvLogout = (TextView) findViewById(R.id.tvLogout);
		btnHelpCenter.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	Intent intent = new Intent(MoreActivity.this,HelpActivity.class);
	            startActivity(intent); 
	           
		    }
	  });
		tvLogout.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {
		    	objSession = new UserSessionManager(getApplicationContext()); 
		    	objSession.logoutUser();
		    	Intent intent = new Intent(MoreActivity.this,LoginActivity.class);
	            startActivity(intent); 
	           
		    }
	  });
	

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.more, menu);
		return true;
	}

}
