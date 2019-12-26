package com.example.mydiary;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2019/12/23.
 * 用于存入数据库和显示数据的日记类
 */

public class Diary extends DataSupport {
    private String userID;
    private int year;
    private int month;
    private int day;
    private String weekday;
    private String summary;
    private int moodPic;
    private int weatherPic;

    public Diary(String userID, int year, int month, int day, String weekday,
                 String summary, int moodPic, int weatherPic){
        this.userID = userID;
        this.year = year;
        this.month = month;
        this.day = day;
        this.weekday = weekday;
        this.summary = summary;
        this.moodPic = moodPic;
        this.weatherPic = weatherPic;
    }

    public String getUserID(){return userID;}
    public void setUserID(String userID){
        this.userID = userID;
    }
    public int getYear(){return year;}
    public void setYear(int year){this.year = year;}
    public int getMonth(){
        return month;
    }
    public void setMonth(int month){this.month = month;}
    public int getDay(){
        return day;
    }
    public void setDay(int day){this.day = day;}
    public String getWeekday(){
        return weekday;
    }
    public void setWeekday(String weekday){this.weekday = weekday;}
    public String getSummary(){
        return summary;
    }
    public void setSummary(String summary){this.summary = summary;}
    public int getMoodPic(){return moodPic;}
    public void setMood(int moodPic){this.moodPic = moodPic;}
    public int getWeatherPic(){
        return weatherPic;
    }
    public void setWeatherPic(int weatherPic){this.weatherPic = weatherPic;}
}
