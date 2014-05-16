package com.example.mobilemoneymanagement;

import android.os.Bundle;

import android.view.Menu;
import android.app.TabActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabWidget;
import android.widget.TextView;
@SuppressWarnings("deprecation")
public class TabHostActivity extends TabActivity implements OnTabChangeListener
{ 
	 private static final String[] TABS = { "HomeGroupActivity", "IncomeGroupActivity", "ExpenseGroupActivity","MoreGroupActivity" };
	 private static final String[] TAB_NAMES = { "Home", "Income", "Expense","More"};
	 public static TabHost tabs ;
	    public static TabWidget tabWidget ;    
	 protected Bitmap roundedImage;
	    public boolean checkTabsListener = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab_host);
		 Bitmap roundedImage = BitmapFactory.decodeResource(getResources(),R.drawable.ic_tab_background);
	        roundedImage = getRoundedCornerBitmap(roundedImage,3);
	        tabs = getTabHost();
	        tabWidget = tabs.getTabWidget();               
	     tabs.setOnTabChangedListener(this);
	     for (int i = 0; i < TABS.length; i++){
	         TabHost.TabSpec tab = tabs.newTabSpec(TABS[i]);
	         
	         //Asociating Components
	         ComponentName oneActivity = new ComponentName("com.example.mobilemoneymanagement", "com.example.mobilemoneymanagement." + TABS[i]);
	         Intent intent = new Intent().setComponent(oneActivity);           
	         intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	         tab.setContent(intent);         
	         //Setting the Indicator
	         MyTabIndicator myTab = new MyTabIndicator(this, TAB_NAMES[i],(i+1), roundedImage); 
	         tab.setIndicator(myTab); 
	         tabs.addTab(tab);
        }
	     checkTabsListener = true;
   	        for(int i=0;i<tabs.getTabWidget().getChildCount();i++){
	         tabs.getTabWidget().getChildAt(i).setBackgroundColor(Color.TRANSPARENT);
	        }   
	  tabs.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.ic_tab_background); 
	  tabWidget.getChildAt(0).setOnClickListener(new OnClickListener()
      {   
 @Override
 public void onClick(View v)
 {     
   if(HomeGroupActivity.HomeGroupStack != null && HomeGroupActivity.HomeGroupStack.mIdList.size()>1)
   {      
    HomeGroupActivity.HomeGroupStack.getLocalActivityManager().removeAllActivities();
    HomeGroupActivity.HomeGroupStack.mIdList.clear();
    HomeGroupActivity.HomeGroupStack.mIntents.clear();
    HomeGroupActivity.HomeGroupStack.mAnimator.removeAllViews();
    HomeGroupActivity.HomeGroupStack.startChildActivity("CareGroupActivity", new Intent(HomeGroupActivity.HomeGroupStack, HomeActivity.class));
   
   }
   
   tabWidget.setCurrentTab(0);
   tabs.setCurrentTab(0);
   tabs.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.ic_tab_background);  
 }
      });
	// Income tab Click
	  
	  tabWidget.getChildAt(1).setOnClickListener(new OnClickListener()
	        {   
	   public void onClick(View v)
	   {     
	    if(IncomeGroupActivity.IncomeGroupStack != null && IncomeGroupActivity.IncomeGroupStack.mIdList.size()>0)
	    {
	    	IncomeGroupActivity.IncomeGroupStack.getLocalActivityManager().removeAllActivities();
	    	IncomeGroupActivity.IncomeGroupStack.mIdList.clear();      
	    	IncomeGroupActivity.IncomeGroupStack.mIntents.clear();
	     IncomeGroupActivity.IncomeGroupStack.mAnimator.removeAllViews();            
	     IncomeGroupActivity.IncomeGroupStack.startChildActivity("TrackingGroupActivity", new Intent(IncomeGroupActivity.IncomeGroupStack, IncomeActivity.class));           
	    }     
	             
	    tabWidget.setCurrentTab(1);
	    tabs.setCurrentTab(1);
	    tabs.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.ic_tab_background);       
	   }
    });
	// Expense tab click
	  
	  tabWidget.getChildAt(2).setOnClickListener(new OnClickListener()
	        {   
	   public void onClick(View v)
	   {     
	    if(ExpenseGroupActivity.ExpenseGroupStack != null && ExpenseGroupActivity.ExpenseGroupStack.mIdList.size()>0)
	    {
	      
	    	ExpenseGroupActivity.ExpenseGroupStack.getLocalActivityManager().removeAllActivities();
	    	ExpenseGroupActivity.ExpenseGroupStack.mIdList.clear();      
	    	ExpenseGroupActivity.ExpenseGroupStack.mIntents.clear();
	    	ExpenseGroupActivity.ExpenseGroupStack.mAnimator.removeAllViews();            
	    	ExpenseGroupActivity.ExpenseGroupStack.startChildActivity("DashboardGroupActivity", new Intent(ExpenseGroupActivity.ExpenseGroupStack, ExpenseActivity.class));           
	    }     
	             
	    tabWidget.setCurrentTab(2);
	    tabs.setCurrentTab(2);
	    tabs.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.ic_tab_background);       
	   }
	        });
	// More tab Click
	  
		  tabWidget.getChildAt(3).setOnClickListener(new OnClickListener()
		        {   
		   public void onClick(View v)
		   {     
		    if(MoreGroupActivity.MoreGroupStack != null && MoreGroupActivity.MoreGroupStack.mIdList.size()>0)
		    {
		    	MoreGroupActivity.MoreGroupStack.getLocalActivityManager().removeAllActivities();
		    	MoreGroupActivity.MoreGroupStack.mIdList.clear();      
		    	MoreGroupActivity.MoreGroupStack.mIntents.clear();
		    	MoreGroupActivity.MoreGroupStack.mAnimator.removeAllViews();            
		    	MoreGroupActivity.MoreGroupStack.startChildActivity("MoreGroupActivity", new Intent(MoreGroupActivity.MoreGroupStack, MoreActivity.class));           
		    }     
		             
		    tabWidget.setCurrentTab(3);
		    tabs.setCurrentTab(3);
		    tabs.getTabWidget().getChildAt(3).setBackgroundResource(R.drawable.ic_tab_background);       
		   }
	    });
	  
	}
	   public class MyTabIndicator extends LinearLayout 
	    {
	  public MyTabIndicator(Context context, String label, int tabId, Bitmap bgImg)
	  {
	   super(context);
	   LinearLayout tab = null;
	   TextView tv;
	   this.setGravity(Gravity.CENTER);
	   
	   if(tabId == 1)
	   {
	    tab = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.home_tab, null);
	    tv = (TextView)tab.findViewById(R.id.tab_label);
	    tv.setText(label);
	   }
	   
	   
	   
	   else if(tabId == 2)
	   {
	    tab = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.income_tab, null);
	    tv = (TextView)tab.findViewById(R.id.tab_label);
	    tv.setText(label);
	   }
	   else if(tabId == 3)
	   {
	    tab = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.expense_tab, null);
	    tv = (TextView)tab.findViewById(R.id.tab_label);
	    tv.setText(label);
	   }
	   else if(tabId == 4)
	   {
	    tab = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.more_tab, null);
	    tv = (TextView)tab.findViewById(R.id.tab_label);
	    tv.setText(label);
	   }
	       
	   this.addView(tab, new LinearLayout.LayoutParams(320/4,55));   
	  }  
	    }
	   public void onTabChanged(String tabId) 
	   {      
	    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
	          imm.hideSoftInputFromWindow(tabs.getApplicationWindowToken(), 0);
	            
	          for(int i=0; i<tabs.getTabWidget().getChildCount(); i++)
	    {                             
	           if(tabId.equalsIgnoreCase(TABS[i]))
	     {            
	      tabs.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.ic_tab_background);     
	     }
	     else
	     {
	      tabs.getTabWidget().getChildAt(i).setBackgroundColor((Color.TRANSPARENT));
	     }     
	      }  
	   }
	   public static Bitmap getRoundedCornerBitmap(Bitmap bitmap,float roundPxRadius)
	   {
	          Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Config.ARGB_8888);
	          Canvas canvas = new Canvas(output);
	          
	          final int color = 0xff424242;
	          final Paint paint = new Paint();
	          final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
	          final RectF rectF = new RectF(rect);
	          final float roundPx =roundPxRadius;
	       
	          paint.setAntiAlias(true);
	          canvas.drawARGB(0, 0, 0, 0);
	          paint.setColor(color);
	          canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	       
	          paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	          canvas.drawBitmap(bitmap, rect, rect, paint);
	       
	          return output;
	   }
	   public void onResume()
	   {
	    super.onResume();
	    
	    
	    //ReConstructing TabViews
	    reDesignTabViews(); 
	   }
	   
	   public void onPause()
	   {
	    super.onPause();     
	   }
	   /**
	    * Method used to re constructing the Views at tab bar. This solves tabs disappearing issue.
	    */
	   public void reDesignTabViews()
	   {
	    MyTabIndicator myIndicator;
	    
	    
	    //Construction of tab views....
	    for(int i=0 ; i< tabWidget.getChildCount() ; i++)
	    {
	     myIndicator = (MyTabIndicator) tabWidget.getChildAt(i);
	     myIndicator.removeAllViews();
	     
	     switch (i) 
	     {

	       case 0:
	       myIndicator.addView((LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.home_tab, null));
	       break;
	      case 1:    
	       myIndicator.addView((LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.income_tab, null));    
	       break;
	      case 2:    
	       myIndicator.addView((LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.expense_tab, null));    
	       break;
	      case 3:    
	       myIndicator.addView((LinearLayout) LayoutInflater.from(getApplicationContext()).inflate(R.layout.more_tab, null));    
	       break;
	      
	     }   
	    }  
	   }

	private Bitmap getRoundedCornerBitmap(Bitmap roundedImage2, int i) {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_host, menu);
		return true;
	}

	

}
