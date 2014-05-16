package com.example.mobilemoneymanagement;
import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class ExpenseModifierActivity extends Activity {
	private Spinner spinner;
	  private Button button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense_modifier);
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
		button = (Button) findViewById(R.id.btnexpenseupdate);
		button.setOnClickListener(new OnClickListener() {
		  @Override
		  public void onClick(View v) {
			  
		    Toast.makeText(ExpenseModifierActivity.this,String.valueOf(spinner.getSelectedItem())+" is selected",
				Toast.LENGTH_SHORT).show();
		  }
		});
	  }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.expense_modifier, menu);
		return true;
	}

}
