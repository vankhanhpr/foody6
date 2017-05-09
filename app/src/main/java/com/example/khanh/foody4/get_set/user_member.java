package com.example.khanh.foody4.get_set;

/**
 * Created by Khanh on 5/9/2017.
 */

public class user_member {

    String user_ID;
    String password;
    String user_Name;
    int user_Age;
    String user_Picture;
    String user_Mail;
    String user_FamilyName;
    String user_Sex;
    String use_Mari;
    byte[] avatar;

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getUser_ID() {
        return user_ID;
    }

    public void setUser_ID(String user_ID) {
        this.user_ID = user_ID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_Name() {
        return user_Name;
    }

    public void setUser_Name(String user_Name) {
        this.user_Name = user_Name;
    }

    public int getUser_Age() {
        return user_Age;
    }

    public void setUser_Age(int user_Age) {
        this.user_Age = user_Age;
    }

    public String getUser_Picture() {
        return user_Picture;
    }

    public void setUser_Picture(String user_Picture) {
        this.user_Picture = user_Picture;
    }

    public String getUser_Mail() {
        return user_Mail;
    }

    public void setUser_Mail(String user_Mail) {
        this.user_Mail = user_Mail;
    }

    public String getUser_FamilyName() {
        return user_FamilyName;
    }

    public void setUser_FamilyName(String user_FamilyName) {
        this.user_FamilyName = user_FamilyName;
    }

    public String getUser_Sex() {
        return user_Sex;
    }

    public void setUser_Sex(String user_Sex) {
        this.user_Sex = user_Sex;
    }

    public String getUse_Mari() {
        return use_Mari;
    }

    public void setUse_Mari(String use_Mari) {
        this.use_Mari = use_Mari;
    }
}