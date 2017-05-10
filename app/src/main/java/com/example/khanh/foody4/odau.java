package com.example.khanh.foody4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.khanh.foody4.asynctask.AsyncLoadDistrict;
import com.example.khanh.foody4.asynctask.AsyncLoadImage;
import com.example.khanh.foody4.asynctask.AsyncLoadRest;
import com.example.khanh.foody4.asynctask.AsyncLoadStreet;
import com.example.khanh.foody4.bao.TestAdapter_district;
import com.example.khanh.foody4.bao.TestAdapter_restaurant;
import com.example.khanh.foody4.bao.TestAdapter_restaurant_city;
import com.example.khanh.foody4.bao.TestAdapter_restaurant_province;
import com.example.khanh.foody4.customadapter.CustomAdapter_District;
import com.example.khanh.foody4.customadapter.CustomAdapter_Restaurant_Where;
import com.example.khanh.foody4.customadapter.ExpandableListAdapterODau;
import com.example.khanh.foody4.get_set.district;
import com.example.khanh.foody4.get_set.quanan_getset;
import com.example.khanh.foody4.customadapter.CustomAdapter_odau_nhahang;
import com.example.khanh.foody4.customadapter.getdata;
import com.example.khanh.foody4.get_set.restaurant;
import com.example.khanh.foody4.get_set.street;
import com.example.khanh.foody4.myinterface.IChooseStreet;
import com.example.khanh.foody4.select_city_district.Select_province;

import org.kobjects.base64.Base64;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

/**
 * Created by Khanh on 4/1/2017.
 */

public class odau extends Fragment implements View.OnClickListener,IChooseStreet
{
   /* angi Angi;

    public angi getAngi() {
        return Angi;
    }

    public void setAngi(angi angi) {
        Angi = angi;
    }*/

    //Phần khai báo các đối tượng
    List<String >arr_moinhat,arr_danhmuc,arr_district,arr_tennhahang,arr_diachi;
    List<Float>arr_diem;

    //Phần khai các đối tượng để lấy dữ liệu
    TestAdapter_restaurant  ta_nhahang;
    TestAdapter_restaurant_city ta_nhahang_city;
    TestAdapter_restaurant_province ta_nhahang_tinh;
    ArrayList<quanan_getset> listTenNhaHang;
    TestAdapter_district distric;
    public static CustomAdapter_odau_nhahang cs;
    List<Integer>image_moinhat,image_danhmuc;
    CustomAdapter_odau CAMoinhat;
    List<district> listDistrict;
    List<street>listStreet;

    //Khai báo các customAdapter để tạo các listView có dữ liệu
    CustomAdapter_odau_danhmuc CADanhmuc;
    //Khái báo mainactivity và context
    MainActivity mainActivity;
    Context context;
    //Khai báo button hủy tĩnh để xử lý khí nhấn tắt các tab listview
    public  static  Button button_huy;

    //Các linnearLayout cho xử lí sự kiện và đóng mở
    LinearLayout tab_listview_odau,tab_listview_odau_danhmuc,tab_thanh_pho_odau,tab_select_province,
            tab_chinh_odau,tab_listview_odau_thanhpho,myheader;
    ListView lv_moinhat,lv_moinhat_odau_danhmuc;

    //các biến tĩnh để dễ xử lý ở nhiều class khác nhau
    public static LinearLayout tab_danhmuc,tab_moinhat;
    public  static ListView lv_nhahang_odau;
    public  static  ExpandableListView lv_moinhat_odau_thanhpho;
    public  static  TextView tv_examp11,textView_odau_thanhpho_hcm,tv_odau_danhmuc;
    //Các cờ cho biết trang thái bật mở của các tab ăn gì ở đâu tỉnh thành
    public  static boolean flag_odau=true,flag_danhmuc=true,flag_thanhpho=true;
    ExpandableListAdapterODau expandableListAdapterODau;

    //Một biến cho chạy viewPager
    int page = 1;//chạy bức ảnh đầu tiên
    ViewPager pager1;
    Timer timer;//Khai báo để tự động chạy viewPager

    public static ArrayAdapter arrayAdapterhuyen;

    //khởi tạo đói tượng mainActiviti và context
    public odau (MainActivity mainActivity)
    {
        this.mainActivity=mainActivity;
        context= mainActivity;
    }
    LayoutInflater inflater;
    CustomAdapter_District customAdapter_district;
    HashMap <district,List<street> > listHashMap;
    CustomAdapter_Restaurant_Where customAdapter_restaurant_where;
    List<restaurant>listRestaurant;
    ImageView imv_thunghiem;

    //Phần thân bắt các sự kiện click trên LinearLayout, listview .....
    //gọi các hàm lấy dữ liệu và hàm adapter đổ dữ liệu và list...
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState)

    {
        //Khởi tạo các class lấy dữ liệu nhà hàng
        ta_nhahang=new TestAdapter_restaurant(context);
        ta_nhahang_city=new TestAdapter_restaurant_city(context);
        ta_nhahang_tinh=new TestAdapter_restaurant_province(context);
        View v= inflater.inflate(R.layout.tab_layout_odau,container, false);
        this.inflater = inflater;
        unit(v);
        setItem(v);//hmaf đổ dữ liệu và listview mới nhất
        setItem_danhmuc(v);//đổ dữ liệu và listview danh mục
        load_District();


        loadRestaurant();


        //getListTenNhaHang();
       // setItem_nhahang();//đổ dữ liệu vào trong listview nhà hàng
        //tab_chinh_odau.setOnClickListener(this);
        tab_moinhat.setOnClickListener(this);
        tab_danhmuc.setOnClickListener(this);
        tab_thanh_pho_odau.setOnClickListener(this);
        //Khi muốn đổi tỉnh thành trong listview sẽ gọi activity mới
        //sự kiện được bắt ở class khác
        tab_select_province.setOnClickListener(this);

        //bắt sự kiện khi lựa chọn các quận huyện trong list
        //Khi đó sẽ set các textviewt theo tên quận huyện tương ứng và đổi danh sách nhà hàng theo quận huyện
        lv_moinhat_odau_thanhpho.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                getdata.setDanhmuc_huyen(groupPosition);
                textView_odau_thanhpho_hcm.setText(listDistrict.get(groupPosition).getDistrict_Name().toString());
                textView_odau_thanhpho_hcm.setTextColor(context.getResources().getColor(R.color.red1));
                tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);
                tab_chinh_odau.setVisibility(view.VISIBLE);
                tab_listview_odau_thanhpho.setVisibility(view.GONE);
                mainActivity.tab_button_nagi.setVisibility(view.VISIBLE);
                button_huy.setVisibility(view.GONE);

                flag_thanhpho=true;

                getdata.setRes_City(0);
                getdata.setRes_Disttrict(listDistrict.get(groupPosition).getDistrict_ID());
                getdata.setRest_Catalory(0);
                getdata.setRest_Street(0);
                Toast.makeText(mainActivity,""+groupPosition, Toast.LENGTH_SHORT).show();
                loadRestaurant();


                return  false;
            }
        });

        //Load danh sách đường khi nhấn vào tổng số đường
        lv_moinhat_odau_thanhpho.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                //textView_odau_thanhpho_hcm.setText(listStreet.get(groupPosition).getStreet_Name().toString());
                //textView_odau_thanhpho_hcm.setTextColor(context.getResources().getColor(R.color.red1));

                tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);
                tab_chinh_odau.setVisibility(View.VISIBLE);
                tab_listview_odau_thanhpho.setVisibility(View.GONE);
                mainActivity.tab_button_nagi.setVisibility(View.VISIBLE);
                button_huy.setVisibility(View.GONE);

                flag_thanhpho=true;
                return false;
            }
        });
        //Xử lí khi chọn danh muc trong tab danh mục

        lv_moinhat_odau_danhmuc.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {


                tv_odau_danhmuc.setText(arr_danhmuc.get(position).toString());
                tv_odau_danhmuc.setTextColor(context.getResources().getColor(R.color.red1));

                getdata.setRes_City(0);
                getdata.setRes_Disttrict(0);
                getdata.setRest_Catalory(position);
                Toast.makeText(mainActivity, ""+position, Toast.LENGTH_SHORT).show();
                getdata.setRest_Street(0);
                loadRestaurant();

                tab_moinhat.setBackgroundResource(R.color.colorWhite);
                tab_danhmuc.setBackgroundResource(R.color.colorWhite);
                tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);

                tab_chinh_odau.setVisibility(View.VISIBLE);

                tab_listview_odau_danhmuc.setVisibility(View.GONE);
                button_huy.setVisibility(View.VISIBLE);

                flag_danhmuc=false;

            }
        });


        //Xử lí nút hủy
        //Bật tab chính của giao diện và tắt tất cả các tab khác
        button_huy.setOnClickListener(this);

        //sự kiện khi nhấn vào thành phố trong list view quận huyện
        //set textview thành phố là tên thành phố đồng thời đổi danh sách nhà hàng theo tên thành phố
        //Mở tab chính và đóng các tab khác
        tv_examp11.setOnClickListener(this);
        pageSwitcher(4);
        return v;
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tab_moi_nhat:
                if(flag_odau==true||flag_thanhpho==false||flag_danhmuc==false)
                {
                    tab_moinhat.setBackgroundResource(R.color.colorGray);
                    tab_danhmuc.setBackgroundResource(R.color.colorWhite);
                    tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);
                    if(lv_moinhat.getChildCount()==0)
                        lv_moinhat.setAdapter(CAMoinhat);


                    tab_chinh_odau.setVisibility(v.GONE);
                    tab_listview_odau.setVisibility(v.VISIBLE);
                    tab_listview_odau_thanhpho.setVisibility(v.GONE);
                    tab_listview_odau_danhmuc.setVisibility(v.GONE);
                    mainActivity.tab_button_nagi.setVisibility(v.GONE);
                    button_huy.setVisibility(v.VISIBLE);

                    flag_odau=false;
                    flag_thanhpho=true;
                    flag_danhmuc=true;
                }
                else
                {
                    tab_moinhat.setBackgroundResource(R.color.colorWhite);
                    tab_chinh_odau.setVisibility(v.VISIBLE);
                    tab_listview_odau.setVisibility(v.GONE);
                    flag_odau=true;
                    mainActivity.tab_button_nagi.setVisibility(v.VISIBLE);
                    button_huy.setVisibility(v.GONE);
                }
                break;
            case R.id.tab_danh_muc500:
                if(flag_danhmuc==true||flag_odau==false||flag_thanhpho==false)
                {
                    if(lv_moinhat_odau_danhmuc.getChildCount()==0)
                    {
                        lv_moinhat_odau_danhmuc.setAdapter(CADanhmuc);
                    }
                    tab_moinhat.setBackgroundResource(R.color.colorWhite);
                    tab_danhmuc.setBackgroundResource(R.color.colorGray);
                    tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);

                    tab_chinh_odau.setVisibility(v.GONE);
                    tab_listview_odau.setVisibility(v.GONE);
                    tab_listview_odau_thanhpho.setVisibility(v.GONE);
                    tab_listview_odau_danhmuc.setVisibility(v.VISIBLE);
                    mainActivity.tab_button_nagi.setVisibility(v.GONE);
                    button_huy.setVisibility(v.VISIBLE);


                    flag_danhmuc=false;
                    flag_thanhpho=true;
                    flag_odau=true;
                }
                else
                {
                    tab_danhmuc.setBackgroundResource(R.color.colorWhite);
                    tab_chinh_odau.setVisibility(v.VISIBLE);
                    tab_listview_odau_danhmuc.setVisibility(v.GONE);
                    mainActivity.tab_button_nagi.setVisibility(v.VISIBLE);
                    button_huy.setVisibility(v.GONE);
                    flag_danhmuc=true;
                }
                break;
            case R.id.tab_thanh_pho_odau:
                if(flag_thanhpho==true||flag_odau==false||flag_odau==false)
                {
                    tab_moinhat.setBackgroundResource(R.color.colorWhite);
                    tab_danhmuc.setBackgroundResource(R.color.colorWhite);
                    tab_thanh_pho_odau.setBackgroundResource(R.color.colorGray);
                    if(lv_moinhat_odau_danhmuc.getChildCount()==0)
                    {
                        lv_moinhat_odau_danhmuc.setAdapter(CADanhmuc);
                    }

                    tab_chinh_odau.setVisibility(v.GONE);
                    tab_listview_odau.setVisibility(v.GONE);
                    tab_listview_odau_thanhpho.setVisibility(v.VISIBLE);
                    tab_listview_odau_danhmuc.setVisibility(v.GONE);
                    mainActivity.tab_button_nagi.setVisibility(v.GONE);
                    button_huy.setVisibility(v.VISIBLE);
                    load_District();

                    flag_thanhpho=false;
                    flag_odau=true;
                    flag_danhmuc=true;
                }
                else
                {
                    tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);
                    tab_chinh_odau.setVisibility(v.VISIBLE);
                    tab_listview_odau_thanhpho.setVisibility(v.GONE);
                    mainActivity.tab_button_nagi.setVisibility(v.VISIBLE);
                    button_huy.setVisibility(v.GONE);
                    flag_thanhpho=true;
                }
                break;
            case R.id.tab_select_province:
                Intent intent = new Intent(getActivity(),Select_province.class);
//                startActivity(intent);gọi activity tương ứng qua hàm
                startActivityForResult(intent,123);
                break;
            case R.id.button_huy:
                tab_chinh_odau.setVisibility(v.VISIBLE);
                tab_listview_odau.setVisibility(v.GONE);
                tab_listview_odau_thanhpho.setVisibility(v.GONE);
                tab_listview_odau_danhmuc.setVisibility(v.GONE);
                mainActivity.tab_button_nagi.setVisibility(v.VISIBLE);
                button_huy.setVisibility(View.GONE);

                tab_moinhat.setBackgroundResource(R.color.colorWhite);
                tab_danhmuc.setBackgroundResource(R.color.colorWhite);
                tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);

                flag_danhmuc=true;
                flag_odau=true;
                flag_thanhpho=true;
                break;
            case R.id.tv_listview2_thanhpho_khanh:
                tab_chinh_odau.setVisibility(v.VISIBLE);
                tab_listview_odau_thanhpho.setVisibility(v.GONE);
                mainActivity.tab_button_nagi.setVisibility(v.VISIBLE);
                button_huy.setVisibility(View.GONE);
                tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);
                textView_odau_thanhpho_hcm.setText(getdata.getTen_tp());
                //getListTenNhaHang_TheoTinh();
                //setItem_nhahang_TheoTinh();
                flag_danhmuc=true;
                flag_odau=true;
                flag_thanhpho=true;
                break;
        }
    }

    //Hàm trả về nhà hàng
    public  void loadRestaurant()
    {
       //listRestaurant= new ArrayList<>();

        AsyncLoadRest asyncLoadRest= new AsyncLoadRest();
        try
        {
            listRestaurant=asyncLoadRest.execute(getdata.getRes_City(),getdata.getRes_Disttrict(),getdata.getRest_Catalory(),getdata.getRest_Street()).get();

            //Log.d("res",listRestaurant.get(0).getRest_Name().toString());
            if(listRestaurant.size()>0)
            {
                for(int i=0;i<listRestaurant.size();i++)
                {
                    String image="";
                    AsyncLoadImage asyncLoadImage = new AsyncLoadImage();
                    //Log.d("khanh5",listRestaurant.get(0).getPhoto().toString());
                    image= asyncLoadImage.execute(listRestaurant.get(i).getPhoto().toString()).get();
                    if(image!=null)
                    {
                        byte[] valueDecoded = Base64.decode(image);
                        listRestaurant.get(i).setImage_res(valueDecoded);
                       // Toast.makeText(mainActivity, "khanh"+valueDecoded, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
            Log.d("Loi","Loi rui!!!");
        }
        catch (ExecutionException e)
        {
            Log.d("Loi","Loi rui2");
            e.printStackTrace();
        }
        customAdapter_restaurant_where= new CustomAdapter_Restaurant_Where(mainActivity,listRestaurant);
        lv_nhahang_odau.setAdapter(customAdapter_restaurant_where);
    }

    /*Hàm này nhận giá trị trả về khi chọn xong thành phố , giá trị trả về là mã thành phố
    * và sẽ trả về danh sách quận huyện tương ứng với mã thành phố đồng thời trả về list nhà hàng theo mã tỉnh or thành phố*/
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data==null)
            return;
        if (requestCode == 123)
        {
            // lấy giá trị kết quả
            Bundle bundle = data.getBundleExtra("TapTin");
            int kq = bundle.getInt("KetQua");

            //Lấy tên tỉnh thành bỏ vào texView
            tv_examp11.setText(getdata.getTen_tp());
            textView_odau_thanhpho_hcm.setText(getdata.getTen_tp());


            tab_listview_odau_thanhpho.setVisibility(View.GONE);
            tab_chinh_odau.setVisibility(View.VISIBLE);
            flag_thanhpho=true;
            button_huy.setVisibility(View.GONE);
            mainActivity.tab_button_nagi.setVisibility(View.VISIBLE);
           //\ angi.button_huy_angi.setVisibility(View.GONE);

            //angi.lv_moinhat_angi_thanhpho.setAdapter(arrayAdapterhuyen);

            tab_thanh_pho_odau.setBackgroundResource(R.color.colorWhite);

            getdata.setRes_City(1);
            getdata.setRes_Disttrict(0);
            getdata.setRest_Catalory(0);
            getdata.setRest_Street(0);
            //loadRestaurant();


            load_District();
            angi.tv_listview_thanhpho_angi.setText(getdata.getTen_tp());
            angi.tv_listview_angi_thanhpho1.setText(getdata.getTen_tp());
            angi.lv_moinhat_angi_thanhpho.setAdapter(expandableListAdapterODau);

        }
    }

    //Load quận huyện theo mã tỉnh thành

    public  void load_District()
    {
        AsyncLoadDistrict asyncLoadDistrict= new AsyncLoadDistrict();


        listDistrict= new ArrayList<>();
        listStreet= new ArrayList<>();
        listHashMap= new HashMap<>();
        try
        {
            listDistrict= asyncLoadDistrict.execute(getdata.getCity_ID()).get();

            for (int i = 0; i < listDistrict.size(); i++)
            {
                AsyncLoadStreet asyncLoadStreet= new AsyncLoadStreet();
                List<street> duongList = asyncLoadStreet.execute(listDistrict.get(i).getDistrict_ID()).get();
                listHashMap.put(listDistrict.get(i), duongList);

            }
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        expandableListAdapterODau=
               new ExpandableListAdapterODau(context,listDistrict,listHashMap);
        expandableListAdapterODau.setChooseStreet(this);
        lv_moinhat_odau_thanhpho.setAdapter(expandableListAdapterODau);
    }

    /*Hàm trả khởi tạo tất cả các giá trị đã khai báo adđ header vào listView và set lại danh sách nhà hàng theo tỉnh*/
    public void unit(View v)
    {
        //imv_thunghiem=(ImageView)v.findViewById(R.id.imv_thunghiem);



        arr_tennhahang=new ArrayList<>();
        arr_diachi=new ArrayList<>();
        arr_diem=new ArrayList<>();
       // arr_picture=new ArrayList<>();


        //add header
        lv_nhahang_odau = (ListView)v.findViewById(R.id.anhkhanheptrai);
        View headerListView = inflater.inflate(R.layout.layout_main_header,lv_nhahang_odau,false);
        lv_nhahang_odau.addHeaderView(headerListView);
        myheader=(LinearLayout) headerListView.findViewById(R.id.myheader);




        //Hàm khởi tạo giúp link các đối tượng với giao diện tương ứng



        arr_moinhat=new ArrayList<>();
        arr_danhmuc=new ArrayList<>();
        image_moinhat=new ArrayList<>();
        image_danhmuc=new ArrayList<>();
        arr_district=new ArrayList<>();
        //tar_district= new TestAdapter_district(context);
        tab_moinhat=(LinearLayout)v.findViewById(R.id.tab_moi_nhat);
        tab_danhmuc=(LinearLayout)v.findViewById(R.id.tab_danh_muc500);
        tab_listview_odau=(LinearLayout)v.findViewById(R.id.tab_listview_odau);

        tab_listview_odau_danhmuc=(LinearLayout)v.findViewById(R.id.tab_listview_odau_danhmuc);
        tab_thanh_pho_odau=(LinearLayout)v.findViewById(R.id.tab_thanh_pho_odau);
        tab_select_province=(LinearLayout)v.findViewById(R.id.tab_select_province);
        tab_listview_odau_thanhpho=(LinearLayout)v.findViewById(R.id.tab_listview_odau_thanhpho);
        lv_moinhat=(ListView)v.findViewById(R.id.lv_moinhat_odau);
        textView_odau_thanhpho_hcm=(TextView) v.findViewById(R.id.textView_odau_thanhpho_hcm);
        tv_odau_danhmuc=(TextView)v.findViewById(R.id.tv_odau_danhmuc);

        lv_moinhat_odau_danhmuc=(ListView)v.findViewById(R.id.lv_moinhat_odau_danhmuc);
        button_huy=(Button)v.findViewById(R.id.button_huy);


        tv_examp11=(TextView) v.findViewById(R.id.tv_listview2_thanhpho_khanh);
        tab_chinh_odau=(LinearLayout) v.findViewById(R.id.tab_chinh_odau);
        lv_moinhat_odau_thanhpho=(ExpandableListView) v.findViewById(R.id.lv_moinhat_odau_thanhpho);
        pager1=(ViewPager)v.findViewById(R.id.pager3);
        AndroidImageAdapter adapterView = new AndroidImageAdapter(this.getContext());
        pager1.setAdapter(adapterView);

        //Xét lại danh sách nhà hàng khi chọn tỉnh ở tab ăn gì
        textView_odau_thanhpho_hcm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //getListTenNhaHang_TheoTinh();
                //setItem_nhahang_TheoTinh();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //thêm các đối tượng vào list và gọi hàm adapter để trả về list mới nhất
    public void setItem(View v)
    {
        //add các image vào trong image_moinhat
        image_moinhat.add(R.drawable.icon_fiture_new);
        image_moinhat.add(R.drawable.icon_fiture_gantoi);
        image_moinhat.add(R.drawable.icon_fiture_sao);
        image_moinhat.add(R.drawable.icon_fiture_dukhach);
        image_moinhat.add(R.drawable.icon_fiture_ecard);
        image_moinhat.add(R.drawable.icon_fiture_datcho);
        image_moinhat.add(R.drawable.icon_fiture_bank);
        image_moinhat.add(R.drawable.icon_fiture_delivery);
        //Add text vào trong arr_moinhat
        arr_moinhat.add("Mới nhất");
        arr_moinhat.add("Gần tôi");
        arr_moinhat.add("Phổ biến");
        arr_moinhat.add("Du khách");
        arr_moinhat.add("Ưu đãi E-card");
        arr_moinhat.add("Đặt chỗ");
        arr_moinhat.add("Ưu đãi thẻ");
        arr_moinhat.add("Đặt giao hàng");
        CAMoinhat=new CustomAdapter_odau(mainActivity,arr_moinhat,image_moinhat);
    }
    //thêm các đối tượng vào list và gọi hàm adapter để trả về list danh mục
    public void setItem_danhmuc(View v)
    {
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
        //Hàm này sẽ trộn image với text để đổ vào trong listView
        CADanhmuc =new CustomAdapter_odau_danhmuc(mainActivity,arr_danhmuc,image_danhmuc);

    }
    //Hàm lấy ra danh sách đối tượng nhà hàng
    public   List<quanan_getset> getListTenNhaHang()
    {
        listTenNhaHang = new ArrayList<>();
        listTenNhaHang = ta_nhahang.getListNhaHang(getdata.getDanhmuc());
        return  listTenNhaHang;
    }
    //Lấy danh sách đường theo quận huyện
    @Override
    public void onExpand(int groupPosition)
    {
        if (this.lv_moinhat_odau_thanhpho.isGroupExpanded(groupPosition)) {
            this.lv_moinhat_odau_thanhpho.collapseGroup(groupPosition);
        } else {
            this.lv_moinhat_odau_thanhpho.expandGroup(groupPosition);
        }
    }
    //hàm này chạy các bức ảnh trong viewPager theo giây
    public void pageSwitcher(int seconds)
    {
        //Hàm Timer để set thời gian chạy các ảnh trong viewPager một cách tự động
        timer = new Timer();
        timer.scheduleAtFixedRate(new odau.RemindTask(), 2000, seconds * 1000);
        // đơn vị milliseconds
    }

    // đây là một class giúp chạy các bắc ảnh trong trong ViewPaper.
    class RemindTask extends TimerTask
    {
        @Override
        public void run()
        {
            //Sét quá trình chạy .
            mainActivity.runOnUiThread(new Runnable()
            {
                public void run()
                {

                    if (page > 7)//Nếu số ảnh lớn hơn 7 thì chuyển về ảnh ban đầu
                    {
                        page=1;
                    } else
                    {//Không phải thì tiếp tục chạy
                       pager1.setCurrentItem(page++);
                    }
                }
            });
        }
    }

}