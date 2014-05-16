package com.example.mobilemoneymanagement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DataMoneyAdapter extends ArrayAdapter<DataMoney>{
	private int layoutResourceId;
	Context context;    
    DataMoney data[] = null;
	public DataMoneyAdapter(Context context, int layoutResourceId, DataMoney[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
	 @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View row = convertView;
	        DataHolder holder = null;
	        
	        if(row == null)
	        {
	            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	            row = inflater.inflate(layoutResourceId, parent, false);
	            
	            holder = new DataHolder();
	            holder.incomeamount = (TextView)row.findViewById(R.id.incomeamount);
	            holder.expenseamount = (TextView)row.findViewById(R.id.expenseamount);
	            
	            row.setTag(holder);
	        }
	        else
	        {
	            holder = (DataHolder)row.getTag();
	        }
	        
	        DataMoney weather = data[position];
	        holder.incomeamount.setText(weather.income);
	        holder.expenseamount.setText(weather.expense);
	        
	        return row;
	    }
	 static class DataHolder
	    {
	        TextView expenseamount;
	        TextView incomeamount;
	    }
}
