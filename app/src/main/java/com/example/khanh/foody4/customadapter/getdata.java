package com.example.khanh.foody4.customadapter;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Khanh on 4/2/2017.
 */

public class getdata
{

    static  Integer city_ID=1;

    public  static  int res_City=1;
    public  static  int res_Disttrict=1;
    public  static  int rest_Street=1;
    public  static  int rest_Catalory=1;

    public  static  int food_City=1;
    public  static  int food_Disttrict=1;
    public  static  int food_Street=1;
    public  static  int food_Catalory=1;

    public static int choose_city=1;
    public static  int choose_district;
    public  static  String choose_catalogyName="";
    public  static  int choose_catalogy;
    public  static  String email="";


    public static final LatLng getDefaultMyLocation(){
        return new LatLng(10.851035, 106.772001);//
    }


    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        getdata.email = email;
    }

    public static String getChoose_catalogyName() {
        return choose_catalogyName;
    }

    public static void setChoose_catalogyName(String choose_catalogyName) {
        getdata.choose_catalogyName = choose_catalogyName;
    }

    public static int getChoose_catalogy() {
        return choose_catalogy;
    }

    public static void setChoose_catalogy(int choose_catalogy) {
        getdata.choose_catalogy = choose_catalogy;
    }

    public static int getChoose_district() {
        return choose_district;
    }

    public static void setChoose_district(int choose_district) {
        getdata.choose_district = choose_district;
    }

    public static int getChoose_city() {
        return choose_city;
    }

    public static void setChoose_city(int choose_city) {
        getdata.choose_city = choose_city;
    }

    public static int getFood_City() {
        return food_City;
    }

    public static void setFood_City(int food_City) {
        getdata.food_City = food_City;
    }

    public static int getFood_Disttrict() {
        return food_Disttrict;
    }

    public static void setFood_Disttrict(int food_Disttrict) {
        getdata.food_Disttrict = food_Disttrict;
    }

    public static int getFood_Street() {
        return food_Street;
    }

    public static void setFood_Street(int food_Street) {
        getdata.food_Street = food_Street;
    }

    public static int getFood_Catalory() {
        return food_Catalory;
    }

    public static void setFood_Catalory(int food_Catalory) {
        getdata.food_Catalory = food_Catalory;
    }

    public static int getRes_City() {
        return res_City;
    }

    public static void setRes_City(int res_City) {
        getdata.res_City = res_City;
    }

    public static int getRes_Disttrict() {
        return res_Disttrict;
    }

    public static void setRes_Disttrict(int res_Disttrict) {
        getdata.res_Disttrict = res_Disttrict;
    }

    public static int getRest_Street() {
        return rest_Street;
    }

    public static void setRest_Street(int rest_Street) {
        getdata.rest_Street = rest_Street;
    }

    public static int getRest_Catalory() {
        return rest_Catalory;
    }

    public static void setRest_Catalory(int rest_Catalory) {
        getdata.rest_Catalory = rest_Catalory;
    }

    public static Integer getCity_ID() {
        return city_ID;
    }

    public static void setCity_ID(Integer city_ID) {
        getdata.city_ID = city_ID;
    }
//----------------------------------------
    static  Integer S=0;
    static Integer vitri;
    static  String ten_tp="TP.HCM";
    static  Integer danhmuc=1;
    static  Integer danhmuc_huyen=1;
    static  Integer danhmuc_tinh=1;


    static  Integer angi_danhmuc=-1;
    static  Integer angi_huyen=0;
    static  Integer angi_tinh=1;

    public static void setDanhmuc(Integer danhmuc) {
        getdata.danhmuc = danhmuc;
    }

    public static Integer getAngi_danhmuc() {
        return angi_danhmuc;
    }

    public static void setAngi_danhmuc(Integer angi_danhmuc) {
        getdata.angi_danhmuc = angi_danhmuc;
    }

    public static Integer getAngi_huyen() {
        return angi_huyen;
    }

    public static void setAngi_huyen(Integer angi_huyen) {
        getdata.angi_huyen = angi_huyen;
    }

    public static Integer getAngi_tinh() {
        return angi_tinh;
    }

    public static void setAngi_tinh(Integer angi_tinh) {
        getdata.angi_tinh = angi_tinh;
    }

    public static Integer getVitri() {
        return vitri;
    }

    public static void setVitri(Integer vitri) {
        getdata.vitri = vitri;
    }

    public static Integer getDanhmuc_tinh()
    {
        return danhmuc_tinh;
    }

    public static void setDanhmuc_tinh(Integer danhmuc_tinh) {
        getdata.danhmuc_tinh = danhmuc_tinh;
    }

    public static Integer getDanhmuc_huyen() {
        return danhmuc_huyen;
    }

    public static void setDanhmuc_huyen(Integer danhmuc_huyen)
    {
        getdata.danhmuc_huyen = danhmuc_huyen;
    }



    public static Integer getS()
    {
        return S;
    }

    public static void setS(Integer s)
    {
        S = s;
    }

    public static String getTen_tp()
    {
        return ten_tp;
    }

    public static void setTen_tp(String ten_tp)
    {
        getdata.ten_tp = ten_tp;
    }
    public static  int angi_thanhpho;

    public static int getAngi_thanhpho()
    {
        return angi_thanhpho;
    }
    public static void setAngi_thanhpho(int angi_thanhpho)
    {
        getdata.angi_thanhpho = angi_thanhpho;
    }
    public static int getDanhmuc()
    {
        return danhmuc;
    }
    public static void setDanhmuc(int danhmuc) {
        getdata.danhmuc = danhmuc;
    }

}
