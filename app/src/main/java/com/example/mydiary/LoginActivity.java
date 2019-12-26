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

import org.litepal.crud.DataSupport;

import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText  userIDText;
    EditText passwordText;
    private String userID;
    private String password;
    private List<User> users;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        //隐藏默认标题栏
        ActionBar actionbar = getSupportActionBar();
        if(actionbar != null){
            actionbar.hide();
        }

        Button login = (Button) findViewById(R.id.button_login);
        Button register = (Button) findViewById(R.id.button_register);
        userIDText = (EditText) findViewById(R.id.edit_userID);
        passwordText = (EditText) findViewById(R.id.edit_password);

        //点击登录按钮触发事件
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userID = userIDText.getText().toString();
                password = passwordText.getText().toString();
                //判断用户ID和密码是否为空
                if(TextUtils.isEmpty(userID)){
                    Toast.makeText(LoginActivity.this, "用户ID不可为空！", Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(password)){
                    Toast.makeText(LoginActivity.this, "密码不可为空！", Toast.LENGTH_SHORT).show();
                }else{  //验证账号是否存在及密码是否正确
                    users = DataSupport.where("userID = ? ", String.valueOf(userID)).find(User.class);
                    if (users.isEmpty()){
                        Toast.makeText(LoginActivity.this, "账号不存在", Toast.LENGTH_SHORT).show();
                    }else{
                        users = DataSupport.where("userID = ? and password = ?", String.valueOf(userID),
                                String.valueOf(password)).find(User.class);
                        if (users.isEmpty()){
                            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
                        }else{
                            Intent intent1 = new Intent("com.example.mydiary.LOGIN");
                            user = users.get(0);
                            String userID_send = user.getUsername();
                            intent1.putExtra("user_id",userID_send);
                            startActivity(intent1);
                        }
                    }
                }
            }
        });

        //点击注册按钮触发事件
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent2);
            }
        });

    }
}
