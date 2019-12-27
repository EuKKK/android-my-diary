package com.example.mydiary;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * Created by Administrator on 2019/12/24.
 * 自定义控件——导航条
 */

public class NavigationBar extends LinearLayout {

    public NavigationBar(Context context, AttributeSet attrs){
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.navigationbar, this);
        Button scanPage = (Button) findViewById(R.id.scan);
        Button calendarPage = (Button) findViewById(R.id.calendar);
        Button infoPage = (Button) findViewById(R.id.info);

        scanPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.mydiary.LOGIN");
                getContext().startActivity(intent);
            }
        });

        calendarPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent("com.example.mydiary.CALENDAR");
                getContext().startActivity(intent);
            }
        });

        infoPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.mydiary.USERINFO");
                getContext().startActivity(intent);
            }
        });
    }
}
