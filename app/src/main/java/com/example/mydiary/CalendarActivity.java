package com.example.mydiary;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    private List<Diary> diaries = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);
        ListView listView = (ListView) findViewById(R.id.list_view2);
        DiaryAdapter adapter2 = new DiaryAdapter(CalendarActivity.this, R.layout.diary_item, diaries);
        listView.setAdapter(adapter2);
        //点击子项调用的方法
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diary diary2 = diaries.get(position);

            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //根据所给的日期查询对应的日记
                diaries = DataSupport.where("year = ? and month = ? and day = ?", String.valueOf(year)
                        , String.valueOf(month), String.valueOf(dayOfMonth)).find(Diary.class);

            }
        });
    }
}
