package com.example.mydiary;

import java.util.Calendar;

/**
 * Created by Administrator on 2019/12/25.
 * 提供日期获取的类
 */

public class Date {
    private static final Calendar c = Calendar.getInstance();

    public static int getYear(){
        return c.get(Calendar.YEAR);
    }

    public static int getMonth(){
       return c.get(Calendar.MONTH)+1;
    }

    public static int getDay(){
        return c.get(Calendar.DAY_OF_MONTH);
    }

    public static String getWeekday(){
        String weekday1 = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
        switch(weekday1){
            case "1":
                weekday1="星期天";
                break;
            case "2":
                weekday1="星期一";
                break;
            case "3":
                weekday1="星期二";
                break;
            case "4":
                weekday1="星期三";
                break;
            case "5":
                weekday1="星期四";
                break;
            case "6":
                weekday1="星期五";
                break;
            case "7":
                weekday1="星期六";
                break;
        }
        return weekday1;
    }
}
