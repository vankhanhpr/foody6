package com.example.khanh.foody4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khanh.foody4.asynctask.AsyncLoadDistrict;
import com.example.khanh.foody4.asynctask.AsyncLoadFood;
import com.example.khanh.foody4.asynctask.AsyncLoadImage;
import com.example.khanh.foody4.asynctask.AsyncLoadStreet;
import com.example.khanh.foody4.bao.TestAdapter_angi_monan;
import com.example.khanh.foody4.bao.TestAdapter_district;
import com.example.khanh.foody4.customadapter.CustomAdapter_District;
import com.example.khanh.foody4.customadapter.ExpandableListAdapterODau;
import com.example.khanh.foody4.get_set.connect_database_district;
import com.example.khanh.foody4.get_set.district;
import com.example.khanh.foody4.get_set.food;
import com.example.khanh.foody4.get_set.monan_getset;
import com.example.khanh.foody4.customadapter.CustomAdapter_angi_monan;
import com.example.khanh.foody4.customadapter.getdata;
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


public class angi extends Fragment implements View.OnClickListener,IChooseStreet
{

    /*odau odau;

    public odau getODau() {
        return odau;
    }

    public void setODau(odau odau) {
        this.odau = odau;
    }*/


    public  static  LinearLayout tab_new,tab_listview1,tab_listview_angi_danhmuc,tab_danh_muc_angi,tab_listview_angi_thanhpho,tab_thanh_pho_angi1;
    LinearLayout tab_chinh1,tab_select_province_angi;//thanh cuộn phần danh mục
    Timer timer;
    public  static boolean flag_moinhat=true,flag_danhmuc=true,flag_thanhpho=true;//Cờ để xét đóng mở các Layout
    List<String>arr_moinhat1;//Danh sách chứa các chuỗi của phần mới nhất
    List<String>arr_danhmuc;//Danh sách chuỗi trong tab danh mục
    List<Integer>image_moinhat1;//Danh sách chứa các icon phần mới nhất
    List<Integer>image_danhmuc;//Danh sách chứa các icon phần danh mục
    TestAdapter_district distric;
    List<String>arr_district;
    public  static ArrayAdapter arrayAdapterhuyen1;
    public TestAdapter_angi_monan ta_monan;
    CustomAdapter CAMoinhat1;//Tạo custom để hiện ra phần text và phần icon của mới nhất
    CustomAdapter_angi_danhmuc CADanhmuc4;
    MainActivity mainActivity;
    ListView lv_moinhat1,lv_danhmuc;//Listview của mới nhất
    Context context;//Khai báo một đối tượng contenxt
    LayoutInflater mainA=null;
    int page = 1;//chạy bức ảnh đầu tiên trong View Page phần hiển thị chính
    ViewPager pager_angi_1234;//Khai báo một viewPage
    //Khai báo biến tĩnh để sử dụng trong class khác
    public  static  TextView tv_listview_thanhpho_angi,tv_listview_angi_thanhpho1;
    public  static ExpandableListView lv_moinhat_angi_thanhpho;
    LinearLayout header_angi;
    AndroidImageAdapter adapterView2;
    public  static  ListView lv_angi_nhahang;
    private ArrayList<monan_getset> listMonAn;
    public  static TextView tv_angi_danhmuc;
    public  static Button button_huy_angi;


    List<district> listDistrict;
    List<food> listFood;
    List<street> listStreet;
    HashMap<district,List<street>>listHashMap;
    ExpandableListAdapterODau expandableListAdapterODau;
    //CustomAdapter_District customAdapter_district;

    public angi(MainActivity mainActivity)
    {//tạo một hàm khởi tạo hàm main ở đây và context
        this.mainActivity=mainActivity;
        context=mainActivity;
    }
    public  static  LayoutInflater inflater;


    /*hàm này sẽ chứa các hàm bắt sự kiện trên các tab mới nhất danh mục tỉnh thành
    * nó gọi các hàm lấy dữ liệu đổ dữ liệu vào trong listview */
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState)
    {

        //khởi tạo gọi các hàm lấy dữ liệu và đổ dữ liệu vào list lúc ban đầu
        distric= new TestAdapter_district(context);//Khởi tạo một adapter để hiển thị listview quận và huyện
        View v =inflater.inflate(R.layout.tab_layout2,container,false);//Link sang layout để gọi các thuộc tính
        this.inflater=inflater;
        unit(v);//gọi hàm khởi tạo các giá trị
        setItem1(v);//Gọi hàm setItem
        load_District();
        load_Food();
        //bắt sự kiện khi nhấn vào nút mới nhất nó sẽ đóng tab chính và mở tav listview mới nhất
        tab_new.setOnClickListener(this);
        //bắt sự kiện khi nhấn vào nút danh mục nó sẽ đóng tab chính và mở tav listview danh mục
        //hàm này sẽ đổ dữ liệu nhà hàng và quán ăn theo danh mục

        tab_danh_muc_angi.setOnClickListener(this);
        //bắt sự kiện khi nhấn vào nút thành phố nó sẽ đóng tab chính và mở tav listview thành phố
        tab_thanh_pho_angi1.setOnClickListener(this);
        //lựa chọn thay đổi quận huyện set texview tỉnh thành thành quận huyện tương ứng
        //load lại danh sách nhà hàng và quán ăn theo quận huyện tương ưung
        //bắt sự kiện khi lựa chọn các quận huyện trong list
        //Khi đó sẽ set các textviewt theo tên quận huyện tương ứng và đổi danh sách nhà hàng theo quận huyện
        lv_moinhat_angi_thanhpho.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener()
        {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View view, int groupPosition, long id) {
                getdata.setDanhmuc_huyen(groupPosition);
                tv_listview_angi_thanhpho1.setText(listDistrict.get(groupPosition).getDistrict_Name().toString());
                tv_listview_thanhpho_angi.setText(listDistrict.get(groupPosition).getDistrict_Name().toString());

                tab_thanh_pho_angi1.setBackgroundResource(R.color.colorWhite);
                tab_listview_angi_thanhpho.setVisibility(View.GONE);
                tab_chinh1.setVisibility(View.VISIBLE);
                button_huy_angi.setVisibility(view.GONE);
                mainActivity.tab_button_nagi.setVisibility(view.VISIBLE);
                tv_listview_angi_thanhpho1.setTextColor(context.getResources().getColor(R.color.red1));
                flag_thanhpho=true;

                getdata.setFood_City(0);
                getdata.setFood_Disttrict(listDistrict.get(groupPosition).getDistrict_ID());
                getdata.setFood_Street(0);
                getdata.setFood_Catalory(0);
                load_Food();


                return  false;
            }
        });
        //Load danh sách đường khi nhấn vào tổng số đường
        lv_moinhat_angi_thanhpho.setOnChildClickListener(new ExpandableListView.OnChildClickListener()
        {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                return false;
            }
        });

        //set sự kiện khi click vào listview danh mục
        lv_danhmuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {;
                button_huy_angi.setVisibility(View.GONE);

                tab_danh_muc_angi.setBackgroundResource(R.color.colorWhite);
                tab_listview_angi_danhmuc.setVisibility(view.GONE);
                tab_chinh1.setVisibility(view.VISIBLE);
                button_huy_angi.setVisibility(view.GONE);
                tv_angi_danhmuc.setText(arr_danhmuc.get(position).toString());
                tv_angi_danhmuc.setTextColor(context.getResources().getColor(R.color.red1));

                tab_danh_muc_angi.setBackgroundResource(R.color.colorWhite);
                flag_danhmuc=true;

                getdata.setFood_City(0);
                getdata.setFood_Disttrict(0);
                getdata.setFood_Street(0);
                getdata.setFood_Catalory(position);
                Toast.makeText(mainActivity, ""+position, Toast.LENGTH_SHORT).show();
                load_Food();

            }
        });

        /*Chọn tỉnh or thành phố khác mở activity tỉnh thành để chọn lại tương ứng*/
        tab_select_province_angi.setOnClickListener(this);
        //Khi chọn nút hủy trong tab danh mục và mới nhất thì mở tab chính và đóng tất cả các tab
        button_huy_angi.setOnClickListener(this);
        //*nhấn vào tên thành phố trong list quận huyện và nó sẽ set lại các text view và load lại danh sách nahf hàng quân ăn*/
        tv_listview_thanhpho_angi.setOnClickListener(this);

            /*Gọi hàm chuyển ảnh*/
            pageSwitcher(4);//Gọi hàm chạy ảnh trong ViewPager ở giữa với thời gian chuyển một ảnh là 4s
            return  v;
    }


    //hàm xử lí các click vào các layout
    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tab_listview_angi_moinhat2:
                //Bắt sự kiện nhấn vào ô mới  Mới nhất
                if(flag_moinhat==true||flag_danhmuc==false||flag_thanhpho==false)
                {
                    /*Đóng các list khác và cả tab chính mở tab mới nhất*/
                    tab_new.setBackgroundResource(R.color.colorGray);
                    tab_danh_muc_angi.setBackgroundResource(R.color.colorWhite);
                    tab_thanh_pho_angi1.setBackgroundResource(R.color.colorWhite);
                    if(lv_moinhat1.getChildCount()==0)
                        lv_moinhat1.setAdapter(CAMoinhat1);

                    tab_listview_angi_thanhpho.setVisibility(View.GONE);
                    tab_listview_angi_danhmuc.setVisibility(View.GONE);
                    tab_chinh1.setVisibility(View.GONE);
                    tab_listview1.setVisibility(View.VISIBLE);

                    button_huy_angi.setVisibility(View.VISIBLE);
                    mainActivity.tab_button_nagi.setVisibility(View.GONE);

                    flag_thanhpho=true;
                    flag_moinhat=false;
                    flag_danhmuc=true;
                }
                else
                {
                    /*Đóng các tab mới nhất và mở tab chính*/
                    tab_new.setBackgroundResource(R.color.colorWhite);
                    tab_chinh1.setVisibility(View.VISIBLE);
                    tab_listview1.setVisibility(View.GONE);
                    button_huy_angi.setVisibility(View.GONE);
                    mainActivity.tab_button_nagi.setVisibility(View.VISIBLE);
                    flag_moinhat=true;
                }
                break;
            case R.id.tab_danh_muc_angi:
                if(flag_danhmuc==true||flag_moinhat==false||flag_thanhpho==false)
                {
                    /*Đóng các list khác và cả tab chính mở tab danh mục*/
                    tab_new.setBackgroundResource(R.color.colorWhite);
                    tab_danh_muc_angi.setBackgroundResource(R.color.colorGray);
                    tab_thanh_pho_angi1.setBackgroundResource(R.color.colorWhite);

                    if (lv_danhmuc.getChildCount() == 0)
                        lv_danhmuc.setAdapter(CADanhmuc4);

                    tab_listview_angi_thanhpho.setVisibility(View.GONE);
                    tab_listview_angi_danhmuc.setVisibility(View.VISIBLE);
                    tab_chinh1.setVisibility(View.GONE);
                    tab_listview1.setVisibility(View.GONE);;
                    button_huy_angi.setVisibility(v.VISIBLE);
                    mainActivity.tab_button_nagi.setVisibility(v.GONE);

                    flag_thanhpho=true;
                    flag_danhmuc=false;
                    flag_moinhat=true;
                }
                else
                {
                    /*Đóng tab danh mục và mở tab chính*/
                    tab_danh_muc_angi.setBackgroundResource(R.color.colorWhite);
                    tab_listview_angi_danhmuc.setVisibility(v.GONE);
                    tab_chinh1.setVisibility(v.VISIBLE);
                    button_huy_angi.setVisibility(v.GONE);
                    mainActivity.tab_button_nagi.setVisibility(v.VISIBLE);
                    flag_danhmuc=true;
                }
                break;
            case R.id.tab_thanh_pho_angi1:
                if(flag_thanhpho==true)
                {
                    /*Đóng các list khác và cả tab chính mở tab thành phố*/
                    tab_new.setBackgroundResource(R.color.colorWhite);
                    tab_danh_muc_angi.setBackgroundResource(R.color.colorWhite);
                    tab_thanh_pho_angi1.setBackgroundResource(R.color.colorGray);

                    tab_listview_angi_thanhpho.setVisibility(View.VISIBLE);
                    tab_listview_angi_danhmuc.setVisibility(View.GONE);
                    tab_chinh1.setVisibility(View.GONE);
                    tab_listview1.setVisibility(View.GONE);
                    button_huy_angi.setVisibility(View.VISIBLE);
                    mainActivity.tab_button_nagi.setVisibility(View.GONE);
                    flag_thanhpho=false;
                }
                else
                {
                    /*Đóng tab thành phố và mở tab chính*/
                    tab_thanh_pho_angi1.setBackgroundResource(R.color.colorWhite);
                    tab_listview_angi_thanhpho.setVisibility(View.GONE);
                    button_huy_angi.setVisibility(View.GONE);
                    mainActivity.tab_button_nagi.setVisibility(View.VISIBLE);
                    tab_chinh1.setVisibility(View.VISIBLE);
                    flag_thanhpho=true;
                }
                break;
            case R.id.tab_select_province_angi:
                Intent intent = new Intent(getActivity(),Select_province.class);
//                startActivity(intent);gọi activity tương ứng qua hàm
                startActivityForResult(intent,456);
                break;
            case R.id.button_huy_angi:
                mainActivity.tab_button_nagi.setVisibility(View.VISIBLE);

                tab_chinh1.setVisibility(View.VISIBLE);
                tab_listview_angi_thanhpho.setVisibility(View.GONE);
                tab_listview_angi_danhmuc.setVisibility(View.GONE);
                tab_listview1.setVisibility(View.GONE);
                flag_thanhpho=true;
                flag_moinhat=true;
                flag_moinhat=true;
                tab_new.setBackgroundResource(R.color.colorWhite);
                tab_thanh_pho_angi1.setBackgroundResource(R.color.colorWhite);
                tab_danh_muc_angi.setBackgroundResource(R.color.colorWhite);
                break;



            case R.id.tv_listview_thanhpho_angi_khanh:
                tv_listview_angi_thanhpho1.setText(tv_listview_thanhpho_angi.getText());
                tab_chinh1.setVisibility(View.VISIBLE);
                tab_thanh_pho_angi1.setBackgroundResource(R.color.colorWhite);
                flag_thanhpho=true;
                getdata.setAngi_danhmuc(-1);
                getdata.setAngi_huyen(0);
                break;
        }
    }

    public void unit(View v)
    {
        arr_moinhat1=new ArrayList<>();
        image_moinhat1=new ArrayList<>();
        arr_danhmuc=new ArrayList<>();
        image_danhmuc =new ArrayList<>();
        arr_district=new ArrayList<>();

        listStreet= new ArrayList<>();

        lv_angi_nhahang=(ListView)v.findViewById(R.id.lv_angi_nhahang);
        tv_angi_danhmuc=(TextView)v.findViewById(R.id.tv_angi_danhmuc);

        //Add header cho listView
        View headerListView = inflater.inflate(R.layout.layout_main_header_angi,lv_angi_nhahang,false);
        lv_angi_nhahang.addHeaderView(headerListView);
        header_angi=(LinearLayout) headerListView.findViewById(R.id.header_angi);


        tv_listview_thanhpho_angi=(TextView)v.findViewById(R.id.tv_listview_thanhpho_angi_khanh);
        tv_listview_angi_thanhpho1=(TextView)v.findViewById(R.id.tv_listview_angi_thanhpho1);
        //list view chứa danh sách nhà hàng


        tab_new=(LinearLayout)v.findViewById(R.id.tab_listview_angi_moinhat2);
        tab_chinh1=(LinearLayout)v.findViewById(R.id.tab_chinh1);
        lv_moinhat1=(ListView)v.findViewById(R.id.lv_moinhat_angi);
        lv_danhmuc=(ListView)v.findViewById(R.id.lv_moinhat_angi_danhmuc);
        lv_moinhat_angi_thanhpho=(ExpandableListView)v.findViewById(R.id.lv_moinhat_angi_thanhpho);

        tab_thanh_pho_angi1=(LinearLayout)v.findViewById(R.id.tab_thanh_pho_angi1);
        tab_listview_angi_thanhpho=(LinearLayout)v.findViewById(R.id.tab_listview_angi_thanhpho);
        tab_select_province_angi=(LinearLayout)v.findViewById(R.id.tab_select_province_angi);
        tab_listview1=(LinearLayout)v.findViewById(R.id.tab_listview1);
        tab_listview_angi_danhmuc=(LinearLayout)v.findViewById(R.id.tab_listview_angi_danhmuc);
        tab_danh_muc_angi=(LinearLayout)v.findViewById(R.id.tab_danh_muc_angi);


        button_huy_angi=(Button)v.findViewById(R.id.button_huy_angi);


        pager_angi_1234=(ViewPager)v.findViewById(R.id.pager_angi_1234);

        adapterView2 = new AndroidImageAdapter(this.getContext());
        pager_angi_1234.setAdapter(adapterView2);


        listDistrict= new ArrayList<>();
        listFood= new ArrayList<>();


        //load lại danh sách món ăn nhà hàng khi chọn tỉnh thành bên tab ở đâu

        tv_listview_angi_thanhpho1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //load lại danh sách món ăn nhà hàng khi chọn tỉnh thành bên tab ở đâu
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    //Nhận giá trị trả về khi chọn xong tỉnh thành
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Hàm trả về giá trị khi chọn xong tỉnh hoặc thành phố khác
        //Sau khi trả về thì trả về quận huyện tương ứng và cả danh sách nhà hàng theo quận và huyện
        if(data==null)
            return;
        if (requestCode == 456)
        {
            // lấy giá trị kết quả
            Bundle bundle = data.getBundleExtra("TapTin");//lấy theo bundle
            int kq = bundle.getInt("KetQua");//lấy mã tỉnh hoặc thành phố được trả về

            tv_listview_thanhpho_angi.setText(getdata.getTen_tp());
            tv_listview_angi_thanhpho1.setText(getdata.getTen_tp());
            load_District();
            odau.button_huy.setVisibility(View.GONE);
            mainActivity.tab_button_nagi.setVisibility(View.VISIBLE);
            button_huy_angi.setVisibility(View.GONE);


            tab_thanh_pho_angi1.setBackgroundResource(R.color.colorWhite);
            tab_listview_angi_thanhpho.setVisibility(View.GONE);
            tab_chinh1.setVisibility(View.VISIBLE);
            flag_thanhpho=true;

            //set danh sách quận huyên tương ứng bên tab ở đâu
            odau.tv_examp11.setText(getdata.getTen_tp());
            odau.textView_odau_thanhpho_hcm.setText(getdata.getTen_tp());
            odau.lv_moinhat_odau_thanhpho.setAdapter(expandableListAdapterODau);

            getdata.setFood_City(getdata.getCity_ID());
            getdata.setFood_Disttrict(0);
            getdata.setFood_Street(0);
            getdata.setFood_Catalory(0);
            load_Food();
        }
    }
    //Hàm load danh sách quận huyện
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
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        expandableListAdapterODau=
                new ExpandableListAdapterODau(context,listDistrict,listHashMap);
        expandableListAdapterODau.setChooseStreet(this);
        lv_moinhat_angi_thanhpho.setAdapter(expandableListAdapterODau);
    }

    public  void load_Food()
    {
        AsyncLoadFood asyncLoadFood = new AsyncLoadFood();
        try
        {
            listFood= asyncLoadFood.execute(getdata.getFood_City(),getdata.getFood_Disttrict(),getdata.getFood_Catalory(),getdata.getFood_Street()).get();
            if(listFood.size()>0)
            {
                for(int i=0;i<listFood.size();i++)
                {
                    String image="";
                    AsyncLoadImage asyncLoadImage = new AsyncLoadImage();
                    //Log.d("khanh5",listRestaurant.get(0).getPhoto().toString());
                    image= asyncLoadImage.execute(listFood.get(i).getPicture().toString()).get();
                    if(image!=null)
                    {
                        byte[] valueDecoded = Base64.decode(image);
                        listFood.get(i).setAnh(valueDecoded);
                        // Toast.makeText(mainActivity, "khanh"+valueDecoded, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        CustomAdapter_angi_monan customAdapter_angi_monan= new CustomAdapter_angi_monan(inflater,context,listFood);
        lv_angi_nhahang.setAdapter(customAdapter_angi_monan);
    }
    //Hàm sẽ tạo danh sách các danh mục theo mới nhất và danh mục
    //Gọi hàm adapter để trả về list tương ứng
    public void setItem1(View v)
    {
        //add các icon vào trong list image_moinhat1
        image_moinhat1.add(R.drawable.icon_fiture_new);
        image_moinhat1.add(R.drawable.icon_fiture_gantoi);
        image_moinhat1.add(R.drawable.icon_fiture_sao);
        image_moinhat1.add(R.drawable.icon_fiture_dukhach);
        //Add các text tương ứng vào list ar_moinhat1
        arr_moinhat1.add("Mới nhất");
        arr_moinhat1.add("Gần tôi");
        arr_moinhat1.add("Phổ biến");
        arr_moinhat1.add("Du khách");
        //Gọi hàm CustonAdapter trộn ảnh và text để hiện thị giao diện
        //Add text vào trong list arr_danhmuc
        arr_danhmuc.add("Danh mục");
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
//Add icon vào list image_danhmuc
        image_danhmuc.add(R.drawable.icon_depkhoe);
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
        CAMoinhat1 = new CustomAdapter(mainActivity, arr_moinhat1, image_moinhat1);
        CADanhmuc4 = new CustomAdapter_angi_danhmuc(mainActivity, arr_danhmuc, image_danhmuc);
    }


    //Hàm này là để chạy ảnh trong ViewPager
    public void pageSwitcher(int seconds)
    {
        timer = new Timer();
        timer.scheduleAtFixedRate(new RemindTask(), 2000, seconds * 1000);
        // đơn vị milliseconds
    }

    //Hàm lấy món ăn và đổ vào list các món ăn
   /* public  void  getMonAn()
    {
        listMonAn=new ArrayList<>();
        ta_monan=new TestAdapter_angi_monan(context);
        listMonAn=ta_monan.getListMonAn(getdata.getAngi_danhmuc(),getdata.getAngi_huyen(),getdata.getAngi_tinh());
    }
    //Bây giờ gọi lớp Adapter
    public void LayMonAn()
    {
            CustomAdapter_angi_monan csmonan = new CustomAdapter_angi_monan(inflater, mainActivity, listMonAn);
            lv_angi_nhahang.setAdapter(csmonan);
    }*/

    @Override
    public void onExpand(int groupPosition)
    {
        if (this.lv_moinhat_angi_thanhpho.isGroupExpanded(groupPosition)) {
            this.lv_moinhat_angi_thanhpho.collapseGroup(groupPosition);
        } else {
            this.lv_moinhat_angi_thanhpho.expandGroup(groupPosition);
        }
    }


    // một clss để chạy các bức ảnh trong viewpager
    class RemindTask extends TimerTask
    {
        @Override
        public void run()
        {
            //Hàm này để chạy ảnh trong ViewPager
            mainActivity.runOnUiThread(new Runnable()
            {
                public void run()
                {
                    //Nếu số lượng ảnh đã hết thì quay về bức ảnh đầu tiên

                    if (page > 7)
                    {
                        page=1;
                    } else {//Load bức ảnh tiếp theo nêu chưa hết 7 bức
                        pager_angi_1234.setCurrentItem(page++);
                    }
                }
            });
        }
    }

}

