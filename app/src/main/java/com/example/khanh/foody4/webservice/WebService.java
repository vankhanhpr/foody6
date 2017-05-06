package com.example.khanh.foody4.webservice;

import android.util.Log;

import com.example.khanh.foody4.get_set.city;
import com.example.khanh.foody4.get_set.district;
import com.example.khanh.foody4.get_set.restaurant;
import com.example.khanh.foody4.get_set.street;
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

public class WebService
{

    //Lấy danh sách tỉnh thành
    public List<city>getCity()
    {
        List<city> listTinhThanh=new ArrayList<city>();


        SoapObject request=new SoapObject(StaticObject.NAME_SPACE,StaticObject.METHOD_GETALLTINHTHANH);
        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        envelope.setOutputSoapObject(request);
        HttpTransportSE HttpsTransport=new HttpTransportSE(StaticObject.URL);
        try {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETALLTINHTHANH,envelope);
            SoapObject arraySoapObject=(SoapObject) envelope.getResponse();


            for (int i=0;i<arraySoapObject.getPropertyCount();i++)
            {
                SoapObject item=(SoapObject)arraySoapObject.getProperty(i);

                city city =new city();

                String id= item.getProperty("City_ID").toString();
                String name=item.getProperty("City_Name").toString();


                city.setCity_ID(Integer.parseInt(id));
                city.setCity_Name(name);
                listTinhThanh.add(city);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        return listTinhThanh;
    }

    //Hàm lấy ảnh theo tên ảnh
    public String getImage(String filename)
    {

        String image="";
        SoapObject request=new SoapObject(StaticObject.NAME_SPACE,StaticObject.METHOD_GETIMAGE);
        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
       // String s="download";
        request.addProperty("imageName",filename);

        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport=new HttpTransportSE(StaticObject.URL);

        try
        {
            //Log.d("thu",StaticObject.SOAP_ACTION_GETIMAGE);
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETIMAGE,envelope);
            SoapPrimitive item= (SoapPrimitive)envelope.getResponse();
            image = item.toString();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        //Log.d("kashflasd;ads",image);
        return  image;
    }

    /*public List<String> getImage(String filename)
    {
        List<String>listImage = new ArrayList<>();
        String image="";
        SoapObject request = new SoapObject(StaticObject.NAME_SPACE, StaticObject.METHOD_GETIMAGE);
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;
        request.addProperty("filename", filename);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);

        HttpTransportSE HttpsTransport = new HttpTransportSE(StaticObject.URL);

        try
        {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETIMAGE,envelope);
            SoapObject arraySoapObject=(SoapObject) envelope.bodyIn;
            SoapObject item;
            for (int i=0;i<arraySoapObject.getPropertyCount();i++)
            {
                item=(SoapObject)arraySoapObject.getProperty(i);
                String image_name= item.getProperty("base64Binary").toString();


                listImage.add(image_name);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        return  listImage;
    }*/


    //Lấy danh sách quận huyện theo mã tỉnh thành
    public  List<district> getDistrict(int cityID)
    {
        List<district> listDistrict =new ArrayList<>();
        SoapObject request=new SoapObject(StaticObject.NAME_SPACE,StaticObject.METHOD_GETDISTRICT);
        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        request.addProperty("cityID",cityID);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        HttpTransportSE HttpsTransport=new HttpTransportSE(StaticObject.URL);

        try
        {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETDISTRICT,envelope);
            SoapObject arraySoapObject=(SoapObject) envelope.getResponse();
            SoapObject item;

            for (int i=0;i<arraySoapObject.getPropertyCount();i++)
            {
                district district =new district();
                item=(SoapObject)arraySoapObject.getProperty(i);
                String district_id= item.getProperty("District_ID").toString();
                String name=item.getProperty("District_Name").toString();
                String city_id=item.getProperty("City_ID").toString();
                String total_street= item.getProperty("Total_Street").toString();

                district.setDistrict_ID(Integer.parseInt(district_id));
                district.setDistrict_Name(name);
                district.setCity_ID(Integer.parseInt(city_id));
                district.setTotal_Street(Integer.parseInt(total_street));

                listDistrict.add(district);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        return  listDistrict;
    }

    //Load đường theo mã quyện huyện
    //Lấy danh sách quận huyện theo mã tỉnh thành
    public  List<street> getStreet(int districtID)
    {
        List<street> listStreet =new ArrayList<>();
        SoapObject request=new SoapObject(StaticObject.NAME_SPACE,StaticObject.METHOD_GETSTREET);
        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        request.addProperty("districtID",districtID);
        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        HttpTransportSE HttpsTransport=new HttpTransportSE(StaticObject.URL);

        try
        {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETSTREET,envelope);
            SoapObject arraySoapObject=(SoapObject) envelope.getResponse();
            SoapObject item;

            for (int i=0;i<arraySoapObject.getPropertyCount();i++)
            {
                street street =new street();
                item=(SoapObject)arraySoapObject.getProperty(i);
                String street_id= item.getProperty("Street_ID").toString();
                String name=item.getProperty("Street_Name").toString();
                String district_id=item.getProperty("District_ID").toString();

                street.setStreet_ID(Integer.parseInt(street_id));
                street.setStreet_Name(name);
                street.setDistrict_ID(Integer.parseInt(district_id));

                listStreet.add(street);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }
        return  listStreet;
    }
    //Hàm lấy thông tin nhà hàng theo mã tỉnh thành mà quận huyện và à danh mục

    public  List<restaurant>getRestaurant(int cityID, int districtID, int categoryID, int stressID)
    {
        List<restaurant> listRestaurant= new ArrayList<>();

        SoapObject request=new SoapObject(StaticObject.NAME_SPACE,StaticObject.MERTHOD_GETRESTAURANT);
        SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=true;
        request.addProperty("cityID",cityID);
        request.addProperty("districtID",districtID);
        request.addProperty("categoryID",categoryID);
        request.addProperty("stressID",stressID);



        envelope.setOutputSoapObject(request);

        MarshalFloat marshalFloat = new MarshalFloat();
        marshalFloat.register(envelope);
        HttpTransportSE HttpsTransport=new HttpTransportSE(StaticObject.URL);

        try
        {
            HttpsTransport.call(StaticObject.SOAP_ACTION_GETRESTAURANT,envelope);
            SoapObject arraySoapObject=(SoapObject) envelope.getResponse();
            SoapObject item;

            for (int i=0;i<arraySoapObject.getPropertyCount();i++)
            {
                restaurant restaurant =new restaurant();
                item=(SoapObject)arraySoapObject.getProperty(i);

                String res_ID= item.getProperty("Rest_ID").toString();
                String rest_Name= item.getProperty("Rest_Name").toString();

                String city_ID= item.getProperty("City_ID").toString();
                String district_ID= item.getProperty("District_ID").toString();
                String street_ID= item.getProperty("Stress_ID").toString();

                String address_Name= item.getProperty("Address_Name").toString();
                String phone= item.getProperty("Phone").toString();
                String photo= item.getProperty("Photo").toString();

                String totalView= item.getProperty("TotalView").toString();
                String where_Type= item.getProperty("Where_Type").toString();
                String rest_Type= item.getProperty("Rest_Type").toString();
                String categor_ID= item.getProperty("Category_ID").toString();

                restaurant.setRes_ID(Integer.parseInt(res_ID));
                restaurant.setRest_Name(rest_Name);
                restaurant.setCity_ID(Integer.parseInt(city_ID));

                restaurant.setDistrict_ID(Integer.parseInt(district_ID));
                restaurant.setStreet_ID(Integer.parseInt(street_ID));
                restaurant.setAddress_Name(address_Name);
                restaurant.setPhone(Integer.parseInt(phone));
                restaurant.setPhoto(photo);

                restaurant.setTotalView(Integer.parseInt(totalView));
                restaurant.setWhere_Type(Integer.parseInt(where_Type));
                restaurant.setRest_Type(Integer.parseInt(rest_Type));
                restaurant.setCategor_ID(Integer.parseInt(categor_ID));

                listRestaurant.add(restaurant);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (XmlPullParserException e)
        {
            e.printStackTrace();
        }



        return  listRestaurant;
    }


}
