package com.example.mobilemoneymanagement;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ExpenseAdapter extends ArrayAdapter<Weather>{

    Context context; 
    int layoutResourceId;    
    Weather data[] = null;
    
    public ExpenseAdapter(Context context, int layoutResourceId, Weather[] data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        WeatherHolder holder = null;
        
        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            
            holder = new WeatherHolder();
            holder.txtAmount = (TextView)row.findViewById(R.id.catexpamount);
            holder.txtCategory = (TextView)row.findViewById(R.id.txtcategory);
            holder.txtDate = (TextView)row.findViewById(R.id.catexpdate);
            
            row.setTag(holder);
        }
        else
        {
            holder = (WeatherHolder)row.getTag();
        }
        
        Weather weather = data[position];
        holder.txtAmount.setText(weather.amount);
        holder.txtCategory.setText(weather.category);
        holder.txtDate.setText(weather.date);
        
        return row;
    }
    
    static class WeatherHolder
    {
        
        TextView txtDate,txtAmount,txtCategory;
    }
}