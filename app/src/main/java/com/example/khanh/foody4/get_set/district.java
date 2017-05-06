package com.example.khanh.foody4.get_set;

/**
 * Created by Khanh on 4/24/2017.
 */

public class district
{
    int district_ID;
    String district_Name;
    int city_ID;

    public int getTotal_Street() {
        return total_Street;
    }

    public void setTotal_Street(int total_Street) {
        this.total_Street = total_Street;
    }

    int total_Street;

    public int getDistrict_ID() {
        return district_ID;
    }

    public void setDistrict_ID(int district_ID) {
        this.district_ID = district_ID;
    }

    public String getDistrict_Name() {
        return district_Name;
    }

    public void setDistrict_Name(String district_Name) {
        this.district_Name = district_Name;
    }

    public int getCity_ID() {
        return city_ID;
    }

    public void setCity_ID(int city_ID) {
        this.city_ID = city_ID;
    }
}
