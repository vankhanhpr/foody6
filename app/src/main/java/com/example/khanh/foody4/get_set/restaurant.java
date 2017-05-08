package com.example.khanh.foody4.get_set;

/**
 * Created by Khanh on 5/3/2017.
 */

public class restaurant
{
    int res_ID;
    String rest_Name;
    int city_ID;
    int district_ID;
    int street_ID;
    String address_Name;
    String phone;
    String photo;
    int totalView;
    float point;
    int where_Type;
    int rest_Type;
    int categor_ID;
    byte[]image_res;

    public float getPoint()
    {
        return point;
    }

    public void setPoint(float point) {
        this.point = point;
    }

    public byte[] getImage_res() {
        return image_res;
    }

    public void setImage_res(byte[] image_res) {
        this.image_res = image_res;
    }

    public int getRes_ID() {
        return res_ID;
    }

    public void setRes_ID(int res_ID) {
        this.res_ID = res_ID;
    }

    public String getRest_Name() {
        return rest_Name;
    }

    public void setRest_Name(String rest_Name) {
        this.rest_Name = rest_Name;
    }

    public int getCity_ID() {
        return city_ID;
    }

    public void setCity_ID(int city_ID) {
        this.city_ID = city_ID;
    }

    public int getDistrict_ID() {
        return district_ID;
    }

    public void setDistrict_ID(int district_ID) {
        this.district_ID = district_ID;
    }

    public int getStreet_ID() {
        return street_ID;
    }

    public void setStreet_ID(int street_ID) {
        this.street_ID = street_ID;
    }

    public String getAddress_Name() {
        return address_Name;
    }

    public void setAddress_Name(String address_Name) {
        this.address_Name = address_Name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getTotalView() {
        return totalView;
    }

    public void setTotalView(int totalView) {
        this.totalView = totalView;
    }

    public int getWhere_Type() {
        return where_Type;
    }

    public void setWhere_Type(int where_Type) {
        this.where_Type = where_Type;
    }

    public int getRest_Type() {
        return rest_Type;
    }

    public void setRest_Type(int rest_Type) {
        this.rest_Type = rest_Type;
    }

    public int getCategor_ID() {
        return categor_ID;
    }

    public void setCategor_ID(int categor_ID) {
        this.categor_ID = categor_ID;
    }
}
