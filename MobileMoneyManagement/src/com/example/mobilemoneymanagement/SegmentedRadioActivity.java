package com.example.mobilemoneymanagement;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

@SuppressLint("ShowToast")
public class SegmentedRadioActivity extends Activity implements OnCheckedChangeListener {
	SegmentedRadioGroup segmentText;
	//SegmentedRadioGroup segmentImg;
	Toast mToast;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_expense);
		segmentText = (SegmentedRadioGroup) findViewById(R.id.segment_text);
        segmentText.setOnCheckedChangeListener(this);

        mToast = Toast.makeText(this, "Hello Data", Toast.LENGTH_SHORT);
       
	}
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		if (group == segmentText) {
			if (checkedId == R.id.button_one) {
				mToast.setText("Today");
				mToast.show();
				
			} else if (checkedId == R.id.button_three) {
				mToast.setText("This month");
				mToast.show();
			}

		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.segmented_radio, menu);
		return true;
	}

}
