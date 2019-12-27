package com.example.mydiary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.LitePal;

public class DiaryViewActivity extends AppCompatActivity {

    private Diary oneDiary;
    Button back;
    Button delete;
    Button edit;
    TextView date;
    TextView day;
    TextView weekday;
    TextView content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_view);

        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        back = (Button) findViewById(R.id.back);
        delete = (Button) findViewById(R.id.delete);
        edit = (Button) findViewById(R.id.edit);
        date = (TextView) findViewById(R.id.date);
        day = (TextView) findViewById(R.id.day) ;
        weekday = (TextView) findViewById(R.id.weekday);
        content = (TextView) findViewById(R.id.content);

        //获取ScanActivity传来的用户ID数据
        Intent intent = getIntent();
        if(intent != null){
            if(intent.getSerializableExtra("diary_info") != null){
                oneDiary = (Diary)intent.getSerializableExtra("diary_info");
                date.setText(oneDiary.getYear()+"年 "+oneDiary.getMonth()+"月");
                day.setText(String.valueOf(oneDiary.getDay()));
                weekday.setText(oneDiary.getWeekday());
                content.setText(oneDiary.getSummary());
            }
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DiaryViewActivity.this, DiaryEditActivity.class);
                if(oneDiary !=  null){
                    intent1.putExtra("diary_for_edit", oneDiary);
                }
                startActivityForResult(intent1,1);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.deleteAll(Diary.class, "year = ? and month = ? and day = ? and userID = ?",
                        String.valueOf(oneDiary.getYear()), String.valueOf(oneDiary.getMonth()),
                        String.valueOf(oneDiary.getDay()), oneDiary.getUserID());
                finish();
            }
        });
    }
}
