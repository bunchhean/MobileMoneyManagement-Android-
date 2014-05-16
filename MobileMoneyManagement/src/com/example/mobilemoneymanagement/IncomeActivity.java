package com.example.mobilemoneymanagement;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class IncomeActivity extends Activity {
	ListView listviewincome;
	ImageButton btnadd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_income);
		 Weather weather_data[] = new Weather[]
			        {
					new Weather("Salary","2014/05/05 08:56:34","$200.95"),
		            new Weather("Bonus","2014/05/06 10:06:30","$80.95"),
		            new Weather("Borrow","2014/05/07 12:24:30","$8.95")
			        };
	        IncomeAdapter adapter = new IncomeAdapter(this, 
	                R.layout.listview_income, weather_data);
	        
	        
			listviewincome = (ListView)findViewById(R.id.listViewIncome);
	        listviewincome.setAdapter(adapter);
	        btnadd = (ImageButton)findViewById(R.id.imageadd);
	        btnadd.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent intent = new Intent(IncomeActivity.this,IncomeAddNewActivity.class);
					  startActivityForResult(intent, 0);
					  //Toast.makeText(IncomeActivity.this, "income", Toast.LENGTH_LONG).show();
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.income, menu);
		return true;
	}

}
