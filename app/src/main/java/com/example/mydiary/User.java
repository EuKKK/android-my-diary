package com.example.mydiary;

import org.litepal.crud.LitePalSupport;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/12/26.
 * 用于存入数据库和显示数据的用户类
 */

public class User extends LitePalSupport implements Serializable {
    private String userID;
    private String password;

    public User(String userID, String password){
        this.userID = userID;
        this.password = password;
    }

    public String getUsername(){
        return userID;
    }

    public void setUsername(String userID){
        this.userID = userID;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
