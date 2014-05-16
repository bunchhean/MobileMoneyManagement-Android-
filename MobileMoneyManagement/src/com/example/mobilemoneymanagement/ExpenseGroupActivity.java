package com.example.mobilemoneymanagement;
import android.content.Intent;
import android.os.Bundle;
import com.manish.util.AnimatedActivity;

public class ExpenseGroupActivity extends AnimatedActivity{
public static ExpenseGroupActivity ExpenseGroupStack;
	
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        ExpenseGroupStack = ExpenseGroupActivity.this;
        
       startChildActivity("ExpenseGroupActivity", new Intent(this, ExpenseActivity.class));
    }
}
