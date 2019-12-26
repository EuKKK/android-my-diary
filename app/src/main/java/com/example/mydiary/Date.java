package com.example.mydiary;

import java.util.Calendar;

/**
 * Created by Administrator on 2019/12/25.
 * 提供日期获取的类
 */

public class Date {
    private static int year;
    private static int month;
    private static int day;
    private static String weekday;
    private static final Calendar c = Calendar.getInstance();

    public static int getYear(){
        year = c.get(Calendar.YEAR);
        return year;
    }

    public static int getMonth(){
        month = c.get(Calendar.MONTH)+1;
        return month;
    }

    public static int getDay(){
        day = c.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    public static String getWeekday(){
        String weekday1 = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        switch(weekday1){
            case "1":
                weekday="星期天";
                break;
            case "2":
                weekday="星期一";
                break;
            case "3":
                weekday="星期二";
                break;
            case "4":
                weekday="星期三";
                break;
            case "5":
                weekday="星期四";
                break;
            case "6":
                weekday="星期五";
                break;
            case "7":
                weekday="星期六";
                break;
        }
        return weekday;
    }
}
