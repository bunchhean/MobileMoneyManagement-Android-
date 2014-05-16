package com.example.mobilemoneymanagement;
public class Weather {
    public String category;
    public String date;
    public String amount;
    public Weather(){
        super();
    }
    
    public Weather(String category, String date, String amount) {
        super();
        this.category = category;
        this.date = date;
        this.amount = amount;
    }
}