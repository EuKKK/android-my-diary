package com.example.mydiary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;
import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class CalendarActivity extends AppCompatActivity {
    private List<Diary> diaries = new ArrayList<>();
    private String userID_receive;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar);

        CalendarView calendarView = (CalendarView) findViewById(R.id.calendar_view);
        listView = (ListView) findViewById(R.id.list_view2);

        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        userID_receive = ScanActivity.get_userID_receive();

        diaries = LitePal.where("year = ? and month = ? and day = ? and userID = ?",
                String.valueOf(Date.getYear()), String.valueOf(Date.getMonth()),
                String.valueOf(Date.getDay()), userID_receive).find(Diary.class);
        DiaryAdapter adapter1 = new DiaryAdapter(CalendarActivity.this, R.layout.diary_item, diaries);
        listView.setAdapter(adapter1);

        //点击子项调用的方法
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diary diary2 = diaries.get(position);
                Intent intent = new Intent(CalendarActivity.this, DiaryViewActivity.class);
                intent.putExtra("diary_info", diary2);
                startActivity(intent);
            }
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //根据所给的日期查询对应的日记
                diaries = LitePal.where("year = ? and month = ? and day = ? and userID = ?",
                        String.valueOf(year), String.valueOf(month+1), String.valueOf(dayOfMonth),
                        userID_receive).find(Diary.class);
                DiaryAdapter adapter2 = new DiaryAdapter(CalendarActivity.this, R.layout.diary_item, diaries);
                listView.setAdapter(adapter2);
            }
        });
    }
}
