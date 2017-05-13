package com.example.khanh.foody4.insert_restaurant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khanh.foody4.MainActivity;
import com.example.khanh.foody4.MapsActivity;
import com.example.khanh.foody4.R;
import com.example.khanh.foody4.asynctask.AsyncLoadCity;
import com.example.khanh.foody4.asynctask.AsyncLoadDistrict;
import com.example.khanh.foody4.customadapter.CustomAdapterChoose_City;
import com.example.khanh.foody4.customadapter.CustomAdapter_City;
import com.example.khanh.foody4.customadapter.CustomAdapter_District;
import com.example.khanh.foody4.customadapter.getdata;
import com.example.khanh.foody4.get_set.city;
import com.example.khanh.foody4.get_set.district;
import com.example.khanh.foody4.select_city_district.Select_province;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Khanh on 5/12/2017.
 */

public class Insert_Restaurant extends AppCompatActivity implements View.OnClickListener {

    LinearLayout linear_layout_map_location,tab_back_place;
    TextView text_view_choose_province,tv_no_select_city,text_view_choose_district;
    Dialog dialog;
    ListView list_view_choose_province,list_view_choose_district;
    List<city>listCity;
    List<district>listDistrict;
    MainActivity mainActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_restaurant);
        linear_layout_map_location=(LinearLayout)findViewById(R.id.linear_layout_map_location);
        tab_back_place=(LinearLayout)findViewById(R.id.tab_back_place);
        text_view_choose_province=(TextView)findViewById(R.id.text_view_choose_province);
        text_view_choose_district=(TextView)findViewById(R.id.text_view_choose_district);

        linear_layout_map_location.setOnClickListener(this);
        tab_back_place.setOnClickListener(this);
        text_view_choose_province.setOnClickListener(this);
        text_view_choose_district.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.linear_layout_map_location:
                Intent intentMap = new Intent(this,MapsActivity.class);
                startActivityForResult(intentMap,555);
                break;
            case R.id.tab_back_place:
                finish();
                break;
            case R.id.text_view_choose_province:
                dialog = new Dialog(this);
                listCity= new ArrayList<>();
                // khởi tạo dialog
                dialog.setContentView(R.layout.choose_province);
                // xét layout cho dialog
                dialog.setTitle("Chọn thành phố");
                // xét tiêu đề cho dialog

                list_view_choose_province=(ListView)dialog.findViewById(R.id.list_view_choose_province);
                tv_no_select_city=(TextView)dialog.findViewById(R.id.tv_no_select_city);
                tv_no_select_city.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });

                AsyncLoadCity asyncLoadCity= new AsyncLoadCity();
                try {
                    listCity= asyncLoadCity.execute().get();
                    CustomAdapterChoose_City customAdapterChoose_city;
                    customAdapterChoose_city= new CustomAdapterChoose_City(this,listCity);
                    list_view_choose_province.setAdapter(customAdapterChoose_city);

                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (ExecutionException e) {
                    e.printStackTrace();
                }


                // Sự kiện nhấn vào listView và nhấn vào nút bỏ qua
                //list_view_choose_province.setOnClickListener(this);
                // bắt sự kiện cho nút đăng kí
                dialog.show();
                break;
            case R.id.text_view_choose_district:

                dialog = new Dialog(this);
                listDistrict= new ArrayList<>();
                // khởi tạo dialog
                dialog.setContentView(R.layout.choose_district);
                // xét layout cho dialog
                dialog.setTitle("Chọn Quận/Huyện");
                list_view_choose_district=(ListView)dialog.findViewById(R.id.list_view_choose_district);
                // xét tiêu đề cho dialog
                AsyncLoadDistrict asyncLoadDistrict = new AsyncLoadDistrict();

                try {
                    listDistrict= asyncLoadDistrict.execute(getdata.getCity_ID()).get();
                    Toast.makeText(mainActivity, ""+listDistrict.get(1).getDistrict_Name().toString(), Toast.LENGTH_SHORT).show();
                    //CustomAdapter_District customAdapter_district= new CustomAdapter_District(this.mainActivity,listDistrict);
                   // list_view_choose_district.setAdapter(customAdapter_district);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (ExecutionException e) {
                    e.printStackTrace();
                }
                dialog.show();

                break;
            default:
                break;
        }
    }
}
