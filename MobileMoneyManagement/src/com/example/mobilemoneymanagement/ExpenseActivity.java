package com.example.mobilemoneymanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ListView;

public class ExpenseActivity extends Activity {
	ImageButton btnadd;
	ListView listviewexpense;

    //private ListView listView1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense); 
        Weather weather_data[] = new Weather[]
		        {
				new Weather("Food","2014/05/05 08:56:34","$8.95"),
	            new Weather("RentHouse","2014/05/06 10:06:30","$8.95"),
	            new Weather("School","2014/05/07 12:24:30","$8.95")
		        };
     ExpenseAdapter adapter = new ExpenseAdapter(this, 
                R.layout.listview_expense, weather_data);
        
        
		listviewexpense = (ListView)findViewById(R.id.listViewExpense);
         
        //View header = (View)getLayoutInflater().inflate(R.layout.listview_header, null);
        //listviewexpense.addHeaderView(header);
        listviewexpense.setAdapter(adapter);

        btnadd = (ImageButton)findViewById(R.id.imageadd);
        btnadd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ExpenseActivity.this,ExpenseAddNewActivity.class);
				  startActivityForResult(intent, 0);
				  //Toast.makeText(ExpenseActivity.this, "expense", Toast.LENGTH_LONG).show();
			}
		});
    }
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense, menu);
		return true;
	}
}