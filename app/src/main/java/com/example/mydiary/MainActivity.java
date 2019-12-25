package com.example.mydiary;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Diary> diaryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }
        //创建数据库
        LitePal.getDatabase();
        //初始化日记数据
        initDiaries();
        DiaryAdapter adapter = new DiaryAdapter(MainActivity.this, R.layout.diary_item, diaryList);
        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        //点击子项调用的方法
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diary diary = diaryList.get(position);

            }
        });
        //点击add按钮添加的方法
        Button add = (Button) findViewById(R.id.add_diary);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void initDiaries(){
        diaryList = DataSupport.order("year desc,month desc,day desc")
                .find(Diary.class);
        Diary diary1 = new Diary(2017, 7, 21, 1, "愿中国的年轻人都摆脱冷气", R.drawable.first, R.drawable.second);
        Diary diary2 = new Diary(2018, 8, 22, 2, "不必听自暴自弃者的话", R.drawable.first, R.drawable.second);
        diaryList.add(diary1);
        diaryList.add(diary2);
    }
}
