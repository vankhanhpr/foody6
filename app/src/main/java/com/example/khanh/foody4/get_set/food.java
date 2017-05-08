package com.example.khanh.foody4.get_set;

/**
 * Created by Khanh on 5/8/2017.
 */

public class food {
    int food_ID;
    String food_Name;
    String picture;
    int res_ID;
    byte[] anh;

    public byte[] getAnh() {
        return anh;
    }

    public void setAnh(byte[] anh) {
        this.anh = anh;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getFood_ID() {
        return food_ID;
    }

    public void setFood_ID(int food_ID) {
        this.food_ID = food_ID;
    }

    public String getFood_Name() {
        return food_Name;
    }

    public void setFood_Name(String food_Name) {
        this.food_Name = food_Name;
    }

    public int getRes_ID() {
        return res_ID;
    }

    public void setRes_ID(int res_ID) {
        this.res_ID = res_ID;
    }
}