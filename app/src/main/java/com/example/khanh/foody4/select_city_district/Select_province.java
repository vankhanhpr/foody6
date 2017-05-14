package com.example.khanh.foody4.select_city_district;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.khanh.foody4.asynctask.AsyncLoadCity;
import com.example.khanh.foody4.customadapter.CustomAdapter_City;
import com.example.khanh.foody4.get_set.city;
import com.example.khanh.foody4.get_set.connect_database_city;
import com.example.khanh.foody4.customadapter.getdata;
import com.example.khanh.foody4.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


/**
 * Created by Khanh on 3/31/2017.
 */

//class giúp đổi tỉnh thành khác lấy mã tỉnh để gửi về 2 class ăn gì và ở đâu
public class Select_province extends AppCompatActivity implements AdapterView.OnItemClickListener
{
    //Khai báo các đối tượng
    ImageView imv_lv_back,imv_lv_search_top;
    TextView tv_lv_xong;

    List<Integer> image_danhmuc;
    Context context;
    //MainActivity mainActivity;
    ListView listview_province;
    connect_database_city Tinh;
    Select_header_provice Customadapter;
    EditText edittext_lv;
    LinearLayout tab_lv_xacdinhvitri,tab_lv_xacdinhquocgia;
    private ArrayList<connect_database_city>listThanhPho;
    int k=0;
    List<city>listCity;
    CustomAdapter_City customAdapter_city;
    /*gọi hàm khởi tạo lấy danh sách tỉnh thành từ csdl và load danh sách lên*/
    protected void onCreate(Bundle savedInstanceState)
    {
        setContentView(R.layout.activity_select_city);//link qua giao diện
        context = getApplicationContext();//khởi tạo context
        unit();//hàm khởi tạo
        super.onCreate(savedInstanceState);//kế thừa

        //Lấy cơ sở dữ liệu từ service và đổ vào listView
        listCity=new ArrayList<>();
        AsyncLoadCity asyncLoadCity = new AsyncLoadCity();
        try
        {
            listCity=asyncLoadCity.execute().get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        //Log.d("kahnh2",listCity.get(0).getCity_Name().toString());
        customAdapter_city=new CustomAdapter_City(this,listCity);
        listview_province.setAdapter(customAdapter_city);

        //bắt sự kiện khi nhấn vào một tỉnh thành bất kì
        listview_province.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                ImageView imv_dt;
                TextView tv_lv_select_macdinh,tv_lv_select_city;
                CustomAdapter_City.vitri=position;
                for(int i=1;i<parent.getChildCount();i++)
                {
                    imv_dt=(ImageView)parent.getChildAt(i).findViewById(R.id.imv_lv_select_city);
                    tv_lv_select_macdinh=(TextView)parent.getChildAt(i).findViewById(R.id.tv_lv_select_macdinh);
                    tv_lv_select_city=(TextView)parent .getChildAt(i).findViewById(R.id.tv_lv_select_city);

                    tv_lv_select_macdinh.setVisibility(View.GONE);
                    imv_dt.setVisibility(View.GONE);
                    tv_lv_select_city.setTextColor(context.getResources().getColor(R.color.black_text));

                   /* if(i==1)
                    {
                        tv_lv_select_city.setTextColor(context.getResources().getColor(R.color.black_text_list1));

                    }*/
                }
                imv_dt=(ImageView)view.findViewById(R.id.imv_lv_select_city);
                tv_lv_select_macdinh=(TextView)view.findViewById(R.id.tv_lv_select_macdinh);
                tv_lv_select_city=(TextView)view.findViewById(R.id.tv_lv_select_city);

                imv_dt.setVisibility(View.VISIBLE);
                tv_lv_select_macdinh.setVisibility(View.VISIBLE);

                tv_lv_select_city.setTextColor(context.getResources().getColor(R.color.black_text_list1));
                //set lại mã  thành phố
                getdata.setCity_ID(listCity.get(position-1).getCity_ID());
                getdata.setTen_tp(tv_lv_select_city.getText().toString());


                tv_lv_select_macdinh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //int x=getdata.getCity_ID();
                        sendToMain(getdata.getCity_ID(),123);//về ở đâu
                        sendToMain(getdata.getCity_ID(),456);//về ăn gì
                    }
                });
            }
        });
        //đóng activity tỉnh thành và chuyển về activity ở đâu hay ăn gì tương ứng với nơi chọn
        imv_lv_back.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                finish();
            }
        });

        //khi chọn xong thì gọi hàm gửi mã tỉnh thành về 2 class ăn gì ở đâu


        tv_lv_xong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int x=getdata.getCity_ID();
                //Toast.makeText(context, ""+getdata.getCity_ID().toString(), Toast.LENGTH_SHORT).show();
                sendToMain(x,123);//về ở đâu
                sendToMain(x,456);//về ăn gì
            }
        });
    }

    //hàm khởi tạo các đối tượng
    public void unit()
    {
        imv_lv_back=(ImageView)findViewById(R.id.imv_lv_back);
        tv_lv_xong=(TextView)findViewById(R.id.tv_lv_xong);

        image_danhmuc=new ArrayList<>();
        listview_province=(ListView)findViewById(R.id.listview_province);



        View headerListView = LayoutInflater.from(this).inflate(R.layout.layout_header_listview, null);
        listview_province.addHeaderView(headerListView);




        imv_lv_search_top=(ImageView)headerListView.findViewById(R.id.imv_lv_search_top);
        edittext_lv=(EditText)headerListView.findViewById(R.id.edittext_lv);
        tab_lv_xacdinhvitri=(LinearLayout)headerListView.findViewById(R.id.tab_lv_xacdinhvitri);
        tab_lv_xacdinhquocgia=(LinearLayout)headerListView.findViewById(R.id.tab_lv_xacdinhquocgia);

        listview_province.setOnItemClickListener(this);

    }
    //hàm đóng gói giá trị vào bubdle và gửi giá trị đi qua intent
    public  void sendToMain(int temp,int position )
    {
        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putInt("KetQua",temp);
        intent.putExtra("TapTin", bundle);
        setResult(position,intent);
        finish(); // Đóng Activity hiện tại
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id)
    {

    }

    //hàm này giúp hàm khác gọi được hàm gửi tin về qua interface cụ thể là hàm customadapter
    /*@Override
    public void onSetDefaultCity()
    {
        sendToMain(getdata.getCity_ID(),123);
    }*/
}
