package com.example.khanh.foody4.staticobject;

/**
 * Created by kunsubin on 4/19/2017.
 */

public class StaticObject
{
    public static String NAME_SPACE="http://vankhanhpr.org/";
    public static String URL="http://10.0.3.2/Public/WebService3.asmx?WSDL";
    //
    public static String METHOD_GETALLTINHTHANH="getCity";
    public static String METHOD_GETIMAGE="getImage";
    public static String METHOD_GETDISTRICT="getDistrict";
    public static String METHOD_GETSTREET="getStreet";
    public  static String MERTHOD_GETRESTAURANT="getRestaurant";
    public  static String METHOD_GETFOOD="getFood";


    public static String SOAP_ACTION_GETALLTINHTHANH  = NAME_SPACE+METHOD_GETALLTINHTHANH;

    public  static  String SOAP_ACTION_GETIMAGE  = NAME_SPACE+METHOD_GETIMAGE;

    public  static  String SOAP_ACTION_GETDISTRICT = NAME_SPACE+METHOD_GETDISTRICT;

    public  static  String SOAP_ACTION_GETSTREET = NAME_SPACE+METHOD_GETSTREET;

    public  static String SOAP_ACTION_GETRESTAURANT=NAME_SPACE+MERTHOD_GETRESTAURANT;

    public  static String SOAP_ACTION_GETFOOD=NAME_SPACE+METHOD_GETFOOD;

}
