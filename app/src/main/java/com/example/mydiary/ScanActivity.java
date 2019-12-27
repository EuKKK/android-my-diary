package com.example.mydiary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.LitePal;
import java.util.ArrayList;
import java.util.List;

public class ScanActivity extends AppCompatActivity {
    private List<Diary> diaryList = new ArrayList<>();  //用于存储ListView所有数据的集合
    private List<Diary> diaryList1 = new ArrayList<>();  //用于存储查询到的数据的集合
    private static String userID_receive;
    private DiaryAdapter adapter;
    private ListView listView;

    public static String get_userID_receive(){
        return userID_receive;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scan);
        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }
        //获取LoginActivity传来的用户ID数据
        Intent intent = getIntent();
        if(intent != null){
            if(intent.getExtras() != null){
                userID_receive = intent.getStringExtra("user_id");
            }
        }
        //创建数据库
        LitePal.getDatabase();
        //初始化日记数据
        initDiaries();

        adapter = new DiaryAdapter(ScanActivity.this, R.layout.diary_item, diaryList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //点击子项调用的方法
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Diary diary = diaryList.get(position);
                Intent intent = new Intent(ScanActivity.this, DiaryViewActivity.class);
                intent.putExtra("diary_info", diary);
                startActivity(intent);
            }
        });
        //点击add按钮添加的方法
        Button add = (Button) findViewById(R.id.add_diary);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaryList1 = LitePal.where("year = ? and month = ? and day = ? and userID = ?"
                        , String.valueOf(Date.getYear()), String.valueOf(Date.getMonth()),
                        String.valueOf(Date.getDay()), userID_receive)
                        .find(Diary.class);
                if(diaryList1.size() != 0){
                    Toast.makeText(ScanActivity.this, "今天的日记已经创建", Toast.LENGTH_SHORT).show();
                }else{
                    int mPic = 0;
                    int wPic = 0;
                    Diary dia = new Diary(userID_receive, Date.getYear(),Date.getMonth(),Date.getDay(),
                            Date.getWeekday(), "",mPic, wPic);
                    Intent intent1 = new Intent(ScanActivity.this, DiaryEditActivity.class);
                    intent1.putExtra("diary_create", dia);
                    startActivity(intent1);
                }
                diaryList1.clear();
            }
        });

    }

    private void initDiaries(){
        //diaryList = DataSupport.findAll(Diary.class);
        diaryList = LitePal.where("userID = ?", userID_receive)
                .order("year desc,month desc,day desc")
                .find(Diary.class);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initDiaries();
        adapter = new DiaryAdapter(ScanActivity.this, R.layout.diary_item, diaryList);
        listView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }
}
