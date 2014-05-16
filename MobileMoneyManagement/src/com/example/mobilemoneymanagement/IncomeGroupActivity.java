package com.example.mobilemoneymanagement;
import android.content.Intent;
import android.os.Bundle;

import com.manish.util.AnimatedActivity;

public class IncomeGroupActivity extends AnimatedActivity{
public static IncomeGroupActivity IncomeGroupStack;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        IncomeGroupStack = IncomeGroupActivity.this;
        
        startChildActivity("IncomeGroupActivity", new Intent(this, IncomeActivity.class));
    }

}
