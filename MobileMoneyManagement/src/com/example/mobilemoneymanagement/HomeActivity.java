package com.example.mobilemoneymanagement;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ListView; 
import android.widget.TabHost;

public class HomeActivity extends Activity {
	ListView listView1;
	TabHost tabhost;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		DataMoney weather_data[] = new DataMoney[]
		        {
				new DataMoney("$12.34","$8.95"),
	            new DataMoney("$13.34","$9.56"),
	            new DataMoney("$14.34","$10.05")
		        };
		DataMoneyAdapter adapter = new DataMoneyAdapter(this, 
                R.layout.listview_all, weather_data);
        
        listView1 = (ListView)findViewById(R.id.listView1);
         
        View header = (View)getLayoutInflater().inflate(R.layout.listview_header, null);
        listView1.addHeaderView(header);
        listView1.setAdapter(adapter);
        
		
	}
	@Override
	public void onBackPressed() {
		System.out.println("***back*");
		HomeActivity.super.onBackPressed();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		System.out.println("****event****"+event+"****"+keyCode);
		if(keyCode == KeyEvent.KEYCODE_BACK)
		{

               finish();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

}
