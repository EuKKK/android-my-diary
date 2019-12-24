package com.example.mydiary;

/**
 * Created by Administrator on 2019/12/23.
 */

public class Diary {
    private int year;
    private int month;
    private int day;
    private int weekday;
    private String summary;
    private int moodPic;
    private int weatherPic;

    public Diary(int year, int month, int day, int weekday,
                 String summary, int moodPic, int weatherPic){
        this.year = year;
        this.month = month;
        this.day = day;
        this.weekday = weekday;
        this.summary = summary;
        this.moodPic = moodPic;
        this.weatherPic = weatherPic;
    }

    public int getYear(){
        return year;
    }
    public int getMonth(){
        return month;
    }
    public int getDay(){
        return day;
    }
    public int getWeekday(){
        return weekday;
    }
    public String getSummary(){
        return summary;
    }
    public int getMoodPic(){
        return moodPic;
    }
    public int getWeatherPic(){
        return weatherPic;
    }
}
