package com.example.khanh.foody4.insert_restaurant;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khanh.foody4.CustomAdapter_odau_danhmuc;
import com.example.khanh.foody4.MainActivity;
import com.example.khanh.foody4.MapsActivity;
import com.example.khanh.foody4.R;
import com.example.khanh.foody4.asynctask.AsyncLoadCity;
import com.example.khanh.foody4.asynctask.AsyncLoadDistrict;
import com.example.khanh.foody4.customadapter.CustomAdapterChoose_City;
import com.example.khanh.foody4.customadapter.CustomAdapter_City;
import com.example.khanh.foody4.customadapter.CustomAdapter_District;
import com.example.khanh.foody4.customadapter.CustomAdapter_catalogy_insertRes;
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

    LinearLayout linear_layout_map_location,tab_back_place,linear_layout_choose_type_res;
    TextView text_view_choose_province,tv_no_select_city,text_view_choose_district,tv_no_select_district,tv_catalogy,tv_ok,tv_catalogy_back;
    Dialog dialog;
    ListView list_view_choose_province,list_view_choose_district,list_view_choose_dialogy;
    List<city>listCity;
    List<district>listDistrict;
    MainActivity mainActivity;
    List<String>arr_danhmuc;
    List<Integer>image_danhmuc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_restaurant);
        linear_layout_map_location=(LinearLayout)findViewById(R.id.linear_layout_map_location);
        linear_layout_choose_type_res=(LinearLayout)findViewById(R.id.linear_layout_choose_type_res);


        tab_back_place=(LinearLayout)findViewById(R.id.tab_back_place);
        text_view_choose_province=(TextView)findViewById(R.id.text_view_choose_province);
        text_view_choose_district=(TextView)findViewById(R.id.text_view_choose_district);
        tv_catalogy=(TextView)findViewById(R.id.tv_catalogy);

        linear_layout_map_location.setOnClickListener(this);
        tab_back_place.setOnClickListener(this);
        text_view_choose_province.setOnClickListener(this);
        text_view_choose_district.setOnClickListener(this);
        linear_layout_choose_type_res.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.linear_layout_map_location:
                Intent intentMap = new Intent(this,MapsActivity.class);
                startActivity(intentMap);
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
                list_view_choose_province.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                        text_view_choose_province.setText(listCity.get(position).getCity_Name());
                        getdata.setChoose_city(listCity.get(position).getCity_ID());

                        //Log.d("khanhcbgfngngnhg",""+getdata.getChoose_city());
                        dialog.cancel();
                    }
                });
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
                tv_no_select_district=(TextView)dialog.findViewById(R.id.tv_no_select_district);
                // xét tiêu đề cho dialog
                AsyncLoadDistrict asyncLoadDistrict = new AsyncLoadDistrict();

                try {
                        listDistrict = asyncLoadDistrict.execute(getdata.getChoose_city()).get();
                    //Log.d("khanh2",""+listDistrict.get(1).getDistrict_Name());
                        CustomAdapter_District customAdapter_district= new CustomAdapter_District(getApplicationContext(),listDistrict);
                        list_view_choose_district.setAdapter(customAdapter_district);
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                dialog.show();

                tv_no_select_district.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                list_view_choose_district.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        text_view_choose_district.setText(listDistrict.get(position).getDistrict_Name());
                        getdata.setChoose_district(listDistrict.get(position).getCity_ID());
                        dialog.cancel();
                    }
                });
                break;
            case R.id.linear_layout_choose_type_res:
                dialog = new Dialog(this);
                dialog.getWindow();
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.choose_catalogy);
                list_view_choose_dialogy=(ListView)dialog.findViewById(R.id.list_view_choose_dialogy);
                tv_catalogy_back=(TextView)dialog.findViewById(R.id.tv_catalogy_back);
                tv_ok=(TextView)dialog.findViewById(R.id.tv_ok);

                // xét layout cho dialog
                setItem_danhmuc(v);
                CustomAdapter_catalogy_insertRes customAdapter_catalogy_insertRes= new CustomAdapter_catalogy_insertRes(getApplicationContext(),arr_danhmuc,image_danhmuc);
                list_view_choose_dialogy.setAdapter(customAdapter_catalogy_insertRes);



                dialog.show();
               /* list_view_choose_dialogy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        getdata.setChoose_catalogyName(arr_danhmuc.get(position).toString());
                        Log.d("khanh3",""+getdata.getChoose_catalogyName());
                        getdata.setChoose_catalogy(position+1);
                    }
                });*/
                tv_catalogy_back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.cancel();
                    }
                });
                tv_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        tv_catalogy.setText(getdata.getChoose_catalogyName());
                        dialog.cancel();
                    }
                });

                break;


            default:
                break;
        }
    }
    public void setItem_danhmuc(View v)
    {
        arr_danhmuc= new ArrayList<>();
        image_danhmuc= new ArrayList<>();
        //Ở đây sẻ đưa các text vào trong arr_dannhmuc
        arr_danhmuc.add("Danh mục");
        arr_danhmuc.add("Sang trọng");
        arr_danhmuc.add("Buffet");
        arr_danhmuc.add("Nhà hàng");
        arr_danhmuc.add("Ăn vặt/Vỉa hè");
        arr_danhmuc.add("Ăn chay");
        arr_danhmuc.add("Cafe/Dessert");
        arr_danhmuc.add("Quán ăn");
        arr_danhmuc.add("Bar/Pub");
        arr_danhmuc.add("Quán nhậu");
        arr_danhmuc.add("Beer Club");
        arr_danhmuc.add("Tiệm bánh");
        arr_danhmuc.add("Tiệc tận nơi");
        arr_danhmuc.add("Shop Online");
        arr_danhmuc.add("Giao cơm văn phòng");
        arr_danhmuc.add("Khu ẩm thực");

        arr_danhmuc.add("Việt Nam");
        arr_danhmuc.add("Châu Mỹ");
        arr_danhmuc.add("Mỹ");
        arr_danhmuc.add("Tây Âu");
        arr_danhmuc.add("Ý");
        arr_danhmuc.add("Pháp");
        arr_danhmuc.add("Đức");
        arr_danhmuc.add("Tây Ban Nha");
        arr_danhmuc.add("Trung Hoa");
        arr_danhmuc.add("Ấn Độ");
        arr_danhmuc.add("Thái Lan");
        arr_danhmuc.add("Nhật Bản");
        arr_danhmuc.add("Hàn Quốc");
        arr_danhmuc.add("Malaysia");
        arr_danhmuc.add("Quốc tế");



        //Add icon vào list image_danhmuc
        image_danhmuc.add(R.drawable.monan_amthuc);
        image_danhmuc.add(R.drawable.monan_amthuc);
        image_danhmuc.add(R.drawable.monan_buffet);
        image_danhmuc.add(R.drawable.monan_nha_hang);
        image_danhmuc.add(R.drawable.monan_viahe_anvat);
        image_danhmuc.add(R.drawable.monan_chay);
        image_danhmuc.add(R.drawable.monan_cafe);
        image_danhmuc.add(R.drawable.monan_quanan);
        image_danhmuc.add(R.drawable.monan_bar);
        image_danhmuc.add(R.drawable.monan_quannhau);
        image_danhmuc.add(R.drawable.monan_beerclip);
        image_danhmuc.add(R.drawable.monan_tiembanh);
        image_danhmuc.add(R.drawable.monan_tiecanchoi);
        image_danhmuc.add(R.drawable.monan_shop_online);
        image_danhmuc.add(R.drawable.monan_giaocomvanphong);
        image_danhmuc.add(R.drawable.monan_khuamthuc);

        image_danhmuc.add(R.drawable.monan_viet_nam);
        image_danhmuc.add(R.drawable.monan_chaumy);
        image_danhmuc.add(R.drawable.monan_my);
        image_danhmuc.add(R.drawable.monan_tayau);
        image_danhmuc.add(R.drawable.monan_y);
        image_danhmuc.add(R.drawable.monan_phap);
        image_danhmuc.add(R.drawable.monan_duc);
        image_danhmuc.add(R.drawable.monan_tay_ban_nha);
        image_danhmuc.add(R.drawable.monan_trungquoc);
        image_danhmuc.add(R.drawable.monan_ando);
        image_danhmuc.add(R.drawable.monan_thai_lan);
        image_danhmuc.add(R.drawable.monan_nhatban);
        image_danhmuc.add(R.drawable.monan_hanquoc);
        image_danhmuc.add(R.drawable.monan_malaysia);
        image_danhmuc.add(R.drawable.monan_quocte);
        //Khởi tạo hàm CustomAdapter_odau_danhmuc
        //Hàm này sẽ trộn image với text để đổ vào trong listVie

    }
}
