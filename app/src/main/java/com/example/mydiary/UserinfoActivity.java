package com.example.mydiary;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static org.litepal.LitePalApplication.getContext;

public class UserinfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo);
        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null) {
            actionbar.hide();
        }
        Button change = (Button) findViewById(R.id.change);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.mydiary.HEADCHANGE");
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch(requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    Bundle bundle=data.getExtras();
                    Bitmap temphead=bundle.getParcelable("data");
                    RoundedImageView head = (RoundedImageView) findViewById(R.id.ivAvatar1);
                    head.setImageBitmap(temphead);
                }
        }
    }

    public void alert_edit(View v){
        final EditText et = new EditText(this);
        final Button name = (Button) v;
        new AlertDialog.Builder(this).setTitle("请输入消息")
                .setIcon(android.R.drawable.sym_def_app_icon)
                .setView(et)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //按下确定键后的事件
                        name.setText(et.getText().toString());

                    }
                }).setNegativeButton("取消",null).show();
    }

}

