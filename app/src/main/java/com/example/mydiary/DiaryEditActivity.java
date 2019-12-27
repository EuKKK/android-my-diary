package com.example.mydiary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class DiaryEditActivity extends AppCompatActivity {

    private Diary editDiary;
    TextView date;
    EditText content;
    Button save;
    Button back;
    private List<Diary> diaryList1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diary_edit);

        //获取控件对象
        content = (EditText) findViewById(R.id.diary_content);
        date = (TextView) findViewById(R.id.today) ;
        back = (Button) findViewById(R.id.back);
        save = (Button) findViewById(R.id.save) ;

        //接收diaryView页面edit按钮跳转和scan页面add按钮跳转的数据
        Intent intent = getIntent();
        if(intent != null){
            if(intent.getSerializableExtra("diary_for_edit") != null){   //edit按钮跳转
                editDiary = (Diary)intent.getSerializableExtra("diary_for_edit");
                content.setText(editDiary.getSummary());
                date.setText(editDiary.getYear()+"."+editDiary.getMonth()+"."+editDiary.getDay());
            }else if(intent.getSerializableExtra("diary_create") != null){   //add按钮跳转
                editDiary = (Diary)intent.getSerializableExtra("diary_create");
                if(editDiary != null){
                    String str = String.valueOf(editDiary.getYear())+"."+
                            String.valueOf(editDiary.getMonth())+"."+
                            String.valueOf(editDiary.getDay());
                    date.setText(str);
                }
            }
        }

        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        //“保存”按钮监听器
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diaryList1 = LitePal.where("year = ? and month = ? and day = ? and userID = ?",
                        String.valueOf(editDiary.getYear()), String.valueOf(editDiary.getMonth()),
                        String.valueOf(editDiary.getDay()), editDiary.getUserID())
                        .find(Diary.class);
                editDiary.setSummary(content.getText().toString());
                if(diaryList1.size() == 0){
                    if(TextUtils.isEmpty(editDiary.getSummary())){
                        Toast.makeText(DiaryEditActivity.this, "日记内容不能为空", Toast.LENGTH_SHORT).show();
                    }else{
                        editDiary.save();
                    }
                }else{
                    System.out.println("检查数据");
                    System.out.println(String.valueOf(editDiary.getYear()));
                    System.out.println(editDiary.getMonth());
                    System.out.println(editDiary.getDay());
                    System.out.println(editDiary.getUserID());
                    editDiary.updateAll("year = ? and month = ? and day = ? and userID = ?",
                            String.valueOf(editDiary.getYear()), String.valueOf(editDiary.getMonth()),
                            String.valueOf(editDiary.getDay()), editDiary.getUserID());
                }
                Intent intent_x = new Intent(DiaryEditActivity.this, ScanActivity.class);
                startActivity(intent_x);
            }
        });

        //“返回”监听器
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
