package com.example.mobilemoneymanagement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataMoney {
    public String income,expense;
    public String dateStop;
    public Date d1 = null;
    public Date d2 = null;
    long diff,diffDays,diffHours,diffMinutes,diffSeconds;	
    String strdiffDays,strdiffHours,strdiffMinutes,strdiffSeconds,strdate;
    public DataMoney(){
        super();
    }
    public DataMoney(String income, String expense) {
        super();
        this.income = income;
        this.expense = expense;
    }
  
  
}
