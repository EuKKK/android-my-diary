package com.example.mydiary;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText register1;
    EditText register2;
    EditText register3;
    private String userID_re;
    private String password_re;
    private String password_con;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        Button re_login = (Button) findViewById(R.id.register_login);
        register1 = (EditText) findViewById(R.id.register_userID);
        register2 = (EditText) findViewById(R.id.register_password);
        register3 = (EditText) findViewById(R.id.confirm_password);

        re_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID_re = register1.getText().toString();
                password_re = register2.getText().toString();
                password_con = register3.getText().toString();
                if(TextUtils.isEmpty(userID_re)){
                    Toast.makeText(RegisterActivity.this, "请输入用户注册ID", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password_re)){
                    Toast.makeText(RegisterActivity.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password_con)){
                    Toast.makeText(RegisterActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                }else{
                    users = LitePal.where("userID = ?", userID_re)
                            .find(User.class);
                    if(!users.isEmpty()){
                        Toast.makeText(RegisterActivity.this, "用户ID已存在", Toast.LENGTH_SHORT).show();
                    }else if(!password_re.equals(password_con)){
                        Toast.makeText(RegisterActivity.this, "确认密码与原密码不一致", Toast.LENGTH_SHORT).show();
                    }else{
                        User user = new User(userID_re,password_re);
                        user.save();
                        Intent intent = new Intent(RegisterActivity.this, ScanActivity.class);
                        intent.putExtra("user_id",user.getUsername());
                        startActivity(intent);
                    }
                }
            }
        });
    }
}
