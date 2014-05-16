package com.example.mobilemoneymanagement;
import android.content.Intent;
import android.os.Bundle;

import com.manish.util.AnimatedActivity;

public class MoreGroupActivity extends AnimatedActivity{
	public static MoreGroupActivity MoreGroupStack;
	@Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        MoreGroupStack = MoreGroupActivity.this;
        
       startChildActivity("MoreGroupActivity", new Intent(this, MoreActivity.class));
    }

}
