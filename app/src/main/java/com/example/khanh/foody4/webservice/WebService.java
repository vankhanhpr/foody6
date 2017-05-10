package com.example.khanh.foody4.webservice;

import com.example.khanh.foody4.get_set.city;
import com.example.khanh.foody4.get_set.district;
import com.example.khanh.foody4.get_set.food;
import com.example.khanh.foody4.get_set.restaurant;
import com.example.khanh.foody4.get_set.street;
import com.example.khanh.foody4.get_set.user_member;
import com.example.khanh.foody4.staticobject.StaticObject;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.MarshalFloat;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khanh on 4/12/2017.
 */

public class WebService {

    //Lấy danh sách tỉnh thành
    public List<city> getCity() {
        List<city> listTinhThanh = new ArrayList<city>();


        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETALLTINHTHANH);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);
        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETALLTINHTHANH, envelope);
            SoapObject arraySoapObject = (SoapObject) envelope.getResponse();


            for (int i = 0; i < arraySoapObject.getPropertyCount(); i++) {
                SoapObject item = (SoapObject) arraySoapObject.getProperty(i);

                city city = new city();

                String id = item.getProperty("City_ID").toString();
                String name = item.getProperty("City_Name").toString();


                city.setCity_ID(Integer.parseInt(id));
                city.setCity_Name(name);
                listTinhThanh.add(city);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return listTinhThanh;
    }

    //Hàm lấy ảnh theo tên ảnh
    public String getImage(String filename) {

        String image = "";
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETIMAGE);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("filename", filename);

        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETIMAGE, envelope);
            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            image = item.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        //Log.d("kashflasd;ads",image);
        return image;
    }


    //Lấy danh sách quận huyện theo mã tỉnh thành
    public List<district> getDistrict(int cityID) {
        List<district> listDistrict = new ArrayList<>();
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETDISTRICT);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("cityID", cityID);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETDISTRICT, envelope);
            SoapObject arraySoapObject = (SoapObject) envelope.getResponse();
            SoapObject item;

            for (int i = 0; i < arraySoapObject.getPropertyCount(); i++) {
                district district = new district();
                item = (SoapObject) arraySoapObject.getProperty(i);
                String district_id = item.getProperty("District_ID").toString();
                String name = item.getProperty("District_Name").toString();
                String city_id = item.getProperty("City_ID").toString();
                String total_street = item.getProperty("Total_Street").toString();

                district.setDistrict_ID(Integer.parseInt(district_id));
                district.setDistrict_Name(name);
                district.setCity_ID(Integer.parseInt(city_id));
                district.setTotal_Street(Integer.parseInt(total_street));

                listDistrict.add(district);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return listDistrict;
    }

    //Load đường theo mã quyện huyện
    //Lấy danh sách quận huyện theo mã tỉnh thành
    public List<street> getStreet(int districtID) {
        List<street> listStreet = new ArrayList<>();
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETSTREET);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("districtID", districtID);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETSTREET, envelope);
            SoapObject arraySoapObject = (SoapObject) envelope.getResponse();
            SoapObject item;

            for (int i = 0; i < arraySoapObject.getPropertyCount(); i++) {
                street street = new street();
                item = (SoapObject) arraySoapObject.getProperty(i);
                String street_id = item.getProperty("Street_ID").toString();
                String name = item.getProperty("Street_Name").toString();
                String district_id = item.getProperty("District_ID").toString();

                street.setStreet_ID(Integer.parseInt(street_id));
                street.setStreet_Name(name);
                street.setDistrict_ID(Integer.parseInt(district_id));

                listStreet.add(street);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return listStreet;
    }
    //Hàm lấy thông tin nhà hàng theo mã tỉnh thành mà quận huyện và à danh mục

    public List<restaurant> getRestaurant(int cityID, int districtID, int categoryID, int streetID) {
        List<restaurant> listRestaurant = new ArrayList<>();

        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.MERTHOD_GETRESTAURANT);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("cityID", cityID);
        request.addProperty("districtID", districtID);
        request.addProperty("categoryID", categoryID);
        request.addProperty("stressID", streetID);


        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETRESTAURANT, envelope);
            SoapObject arraySoapObject = (SoapObject) envelope.getResponse();
            SoapObject item;

            for (int i = 0; i < arraySoapObject.getPropertyCount(); i++) {
                restaurant restaurant = new restaurant();
                item = (SoapObject) arraySoapObject.getProperty(i);

                String res_ID = item.getProperty("Rest_ID").toString();
                String rest_Name = item.getProperty("Rest_Name").toString();

                String city_ID = item.getProperty("City_ID").toString();
                String district_ID = item.getProperty("District_ID").toString();
                String street_ID = item.getProperty("Stress_ID").toString();

                String address_Name = item.getProperty("Address_Name").toString();
                String phone = item.getProperty("Phone").toString();
                String photo = item.getProperty("Photo").toString();

                String totalView = item.getProperty("TotalView").toString();
                String point = item.getProperty("Point").toString();

                String categor_ID = item.getProperty("Category_ID").toString();
                String where_Type = item.getProperty("Where_Type").toString();
                String rest_Type = item.getProperty("Rest_Type").toString();


                restaurant.setRes_ID(Integer.parseInt(res_ID));
                restaurant.setRest_Name(rest_Name);
                restaurant.setCity_ID(Integer.parseInt(city_ID));

                restaurant.setDistrict_ID(Integer.parseInt(district_ID));
                restaurant.setStreet_ID(Integer.parseInt(street_ID));
                restaurant.setAddress_Name(address_Name);
                restaurant.setPhone(phone);
                restaurant.setPhoto(photo);
                restaurant.setPoint(Float.parseFloat(point));

                restaurant.setTotalView(Integer.parseInt(totalView));
                restaurant.setWhere_Type(Integer.parseInt(where_Type));
                restaurant.setRest_Type(Integer.parseInt(rest_Type));
                restaurant.setCategor_ID(Integer.parseInt(categor_ID));

                listRestaurant.add(restaurant);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }


        return listRestaurant;
    }

    //Hàm lấy thông tin món ăn của nhà hàng
    public List<food> getFood(int cityID, int districtID, int categoryID, int streetID) {
        List<food> listFood = new ArrayList<>();
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETFOOD);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("cityID", cityID);
        request.addProperty("districtID", districtID);
        request.addProperty("categoryID", categoryID);
        request.addProperty("streetID", streetID);


        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);


        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETFOOD, envelope);
            SoapObject arraySoapObject = (SoapObject) envelope.getResponse();
            SoapObject item;
            for (int i = 0; i < arraySoapObject.getPropertyCount(); i++) {
                food food = new food();
                item = (SoapObject) arraySoapObject.getProperty(i);

                String food_ID = item.getProperty("Food_ID").toString();
                String food_Name = item.getProperty("Food_Namme").toString();
                String foodPict = item.getProperty("Food_Picture").toString();
                String res_ID = item.getProperty("Res_ID").toString();


                food.setRes_ID(Integer.parseInt(res_ID));
                food.setFood_Name(food_Name);
                food.setFood_ID(Integer.parseInt(food_ID));
                food.setPicture(foodPict);
                listFood.add(food);
            }
        }
        catch(IOException e)
            {
                e.printStackTrace();
            }
        catch(XmlPullParserException e)
            {
                e.printStackTrace();
            }

        return listFood;

    }

    //Hàm kiểm tra đăng nhập
    public  int setLogin(String email,String password)
    {
        int temp=-1;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_CHECKLOGIN);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("email", email);
        request.addProperty("pass",password);

        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_CHECKLOGIN,envelope);
            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            String s= item.toString();
            temp= Integer.parseInt(s);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        return temp;
    }
    //Hàm lấy thông tin tài khoản
    public user_member getUser(String email)
    {
        user_member user=null;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETUSER);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("email",email);

        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);
        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETUSER,envelope);
            SoapObject arraySoapObject = (SoapObject) envelope.bodyIn;
            SoapObject item;



                user= new user_member();

                item = (SoapObject) arraySoapObject.getProperty(0);

                String user_ID = item.getProperty("User_Menber_ID").toString();
                String pass = item.getProperty("User_Password").toString();
                String userName = item.getProperty("User_Member_Name").toString();
                String age = item.getProperty("User_Member_Age").toString();
                String avatar = item.getProperty("User_Menber_Avatar").toString();
                String mail = item.getProperty("User_Mail").toString();
                String familyName = item.getProperty("User_FamilyName").toString();
                String userSex = item.getProperty("User_Sex").toString();
                String maria = item.getProperty("User_Marital").toString();


                user.setUser_ID(user_ID);
                user.setPassword(pass);
                user.setUser_Name(userName);
                user.setUser_Age(Integer.parseInt(age));
                user.setUser_Picture(avatar);
                user.setUser_Mail(mail);
                user.setUser_FamilyName(familyName);
                user.setUser_Sex(userSex);
                user.setUse_Mari(maria);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
        catch (XmlPullParserException e) {
            e.printStackTrace();
        }
        return  user;
    }

    //Thay đổi thông tin tài khoản
    public boolean changeProfile(String email,String userID,String name,String familyName,String maria,String sex)
    {
        boolean resuft=false;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_CHANGEUSER);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("email", email);
        request.addProperty("userID", userID);
        request.addProperty("name", name);
        request.addProperty("familyName", familyName);
        request.addProperty("maria", maria);
        request.addProperty("sex", sex);



        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {

            HttpsTransport.call(StaticObject.SOAP_ACTION_CHANGEUSER, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            String kq=item.toString();
            resuft = Boolean.parseBoolean(kq);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return resuft;
    }

    //Thay đổi mật khẩu
    public boolean changePass(String email,String pass)
    {
        boolean resuft=false;
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_CHANGEPASS);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("email",email);
        request.addProperty("password",pass);

        envelope.setOutputSoapObject(request);
        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try {

            HttpsTransport.call(StaticObject.SOAP_ACTION_CHANGEPASS, envelope);

            SoapPrimitive item = (SoapPrimitive) envelope.getResponse();
            String temp=item.toString();
            resuft = Boolean.parseBoolean(temp);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return resuft;
    }
}
