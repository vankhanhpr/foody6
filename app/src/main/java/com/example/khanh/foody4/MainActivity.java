package com.example.khanh.foody4;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.*;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.khanh.foody4.customadapter.getdata;
import com.example.khanh.foody4.fragment.Fragment_Collage;
import com.example.khanh.foody4.fragment.Fragment_Login_main;
import com.example.khanh.foody4.fragment.Fragment_Noticaton;
import com.example.khanh.foody4.fragment.Fragment_Search;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    public  MainActivity()
    {

    }

    //Khai báo các ImageView của các button phía dưới giao diện
    ImageButton imv_home,imv_luutru,imv_timkiem,imv_thongbao,imv_dangnhap;
    ImageView imv_f,imv_mainplus;
    ViewPager paper,viewPager;//Khai báo một viewPage
    public  static LinearLayout tab_button_nagi;
    TabLayout tabLayout;
    private Fragment fragment_Collage,fragment_Login_main,fragment_Notication,fragment_Search;
    private   FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    LinearLayout tab_fragment_main,tab_main_home;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //link các imageview  giữa phần giao diện và phần xử lí
        imv_home=(ImageButton)findViewById(R.id.imv_home);
        imv_luutru=(ImageButton)findViewById(R.id.imv_luutru);
        imv_thongbao=(ImageButton)findViewById(R.id.imv_thongbao);
        imv_timkiem=(ImageButton)findViewById(R.id.imv_timkiem);
        imv_dangnhap=(ImageButton)findViewById(R.id.imv_dangnhap);
        imv_f=(ImageView)findViewById(R.id.imv_f);
        tab_fragment_main=(LinearLayout)findViewById(R.id.tab_fragment_main);
        tab_main_home=(LinearLayout)findViewById(R.id.tab_main_home);
        imv_mainplus=(ImageView)findViewById(R.id.imv_mainplus);
        tab_button_nagi=(LinearLayout)findViewById(R.id.tab_button_nagi);
        FragmentManager frament= getSupportFragmentManager();

        fragment_Search=new Fragment_Search();
        fragment_Collage= new Fragment_Collage();
        fragment_Login_main= new Fragment_Login_main();
        fragment_Notication= new Fragment_Noticaton();


        fragmentManager =getSupportFragmentManager();
        fragmentTransaction=fragmentManager.beginTransaction();

        //add frament transaction
        fragmentTransaction.add(R.id.tab_fragment_main,fragment_Search);
        fragmentTransaction.add(R.id.tab_fragment_main,fragment_Notication);
        fragmentTransaction.add(R.id.tab_fragment_main,fragment_Login_main);
        fragmentTransaction.add(R.id.tab_fragment_main,fragment_Collage);


        fragmentTransaction.hide(fragment_Search);
        fragmentTransaction.hide(fragment_Notication);
        fragmentTransaction.hide(fragment_Login_main);
        fragmentTransaction.hide(fragment_Collage);
        fragmentTransaction.commit();


        //Khởi tạo các ViewPager
        viewPager =(ViewPager)findViewById(R.id.viewPager_main);
        PagerAdapter adapter2 =new PagerAdapter(frament, this);//KHởi tạo một pager
        viewPager.setAdapter(adapter2);
        tabLayout=(TabLayout)findViewById(R.id.tablayout) ;
        getdata.setS(1);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
        {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
            {

            }



            /*Xử lí phần vuốt của tab layout bên trái là tab ở đâu, bên phải là ăn gì
            *    Hai Class tương ứng là ăn gì và ở đâu  Class angi,odau*/
            @Override
            public void onPageSelected(int position)
            {
                if(position==0)
                {
                    tabLayout.getTabAt(0).select();
                }
                else
                    tabLayout.getTabAt(1).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab)
            {
                int temp=tabLayout.getSelectedTabPosition();
                viewPager.setCurrentItem(temp);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //Bắt sự kiện khi nhấn vào chữ f trên giao diện mở activity mới
        imv_f.setOnClickListener(this);
         /*Xứ lí phần nhấn vào dấu cộng trên giao diện*/
        imv_mainplus.setOnClickListener(this);
        //Sự kiện khi nhấn vào image home trên tool đổi màu tương ứng
        imv_home.setOnClickListener(this);
        imv_luutru.setOnClickListener(this);
        imv_timkiem.setOnClickListener(this);
        imv_thongbao.setOnClickListener(this);
        imv_dangnhap.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragment_Search);
        fragmentTransaction.hide(fragment_Notication);
        fragmentTransaction.hide(fragment_Login_main);
        fragmentTransaction.hide(fragment_Collage);


        switch (v.getId())
        {
            case R.id.imv_home:
                imv_home.setBackgroundResource(R.drawable.iconhome1);
                imv_luutru.setBackgroundResource(R.drawable.icon1);
                imv_timkiem.setBackgroundResource(R.drawable.icon4);
                imv_thongbao.setBackgroundResource(R.drawable.icon3);
                imv_dangnhap.setBackgroundResource(R.drawable.icon5);
                tab_main_home.setVisibility(View.VISIBLE);
                tab_fragment_main.setVisibility(View.GONE);
                fragmentTransaction.hide(fragment_Search);
                fragmentTransaction.hide(fragment_Notication);
                fragmentTransaction.hide(fragment_Login_main);
                fragmentTransaction.hide(fragment_Collage);

                break;
            case R.id.imv_luutru:
                imv_luutru.setBackgroundResource(R.drawable.iconluutru1);
                imv_home.setBackgroundResource(R.drawable.icon2);
                imv_timkiem.setBackgroundResource(R.drawable.icon4);
                imv_thongbao.setBackgroundResource(R.drawable.icon3);
                imv_dangnhap.setBackgroundResource(R.drawable.icon5);
                tab_main_home.setVisibility(View.GONE);
                tab_fragment_main.setVisibility(View.VISIBLE);
                fragmentTransaction.hide(fragment_Search);
                fragmentTransaction.hide(fragment_Notication);
                fragmentTransaction.hide(fragment_Login_main);
                fragmentTransaction.show(fragment_Collage);

                break;
            case R.id.imv_timkiem:
                imv_timkiem.setBackgroundResource(R.drawable.icontimkiem1);
                imv_home.setBackgroundResource(R.drawable.icon2);
                imv_luutru.setBackgroundResource(R.drawable.icon1);
                imv_thongbao.setBackgroundResource(R.drawable.icon3);
                imv_dangnhap.setBackgroundResource(R.drawable.icon5);
                tab_main_home.setVisibility(View.GONE);
                tab_fragment_main.setVisibility(View.VISIBLE);
                fragmentTransaction.show(fragment_Search);
                fragmentTransaction.hide(fragment_Notication);
                fragmentTransaction.hide(fragment_Login_main);
                fragmentTransaction.hide(fragment_Collage);
                break;
            case R.id.imv_thongbao:
                imv_thongbao.setBackgroundResource(R.drawable.iconthongbao1);
                imv_home.setBackgroundResource(R.drawable.icon2);
                imv_luutru.setBackgroundResource(R.drawable.icon1);
                imv_timkiem.setBackgroundResource(R.drawable.icon4);
                imv_dangnhap.setBackgroundResource(R.drawable.icon5);
                tab_main_home.setVisibility(View.GONE);
                tab_fragment_main.setVisibility(View.VISIBLE);
                fragmentTransaction.hide(fragment_Search);
                fragmentTransaction.show(fragment_Notication);
                fragmentTransaction.hide(fragment_Login_main);
                fragmentTransaction.hide(fragment_Collage);
                break;
            case R.id.imv_dangnhap:
                imv_dangnhap.setBackgroundResource(R.drawable.icondangnhap1);
                imv_home.setBackgroundResource(R.drawable.icon2);
                imv_luutru.setBackgroundResource(R.drawable.icon1);
                imv_timkiem.setBackgroundResource(R.drawable.icon4);
                imv_thongbao.setBackgroundResource(R.drawable.icon3);
                tab_main_home.setVisibility(View.GONE);
                tab_fragment_main.setVisibility(View.VISIBLE);
                fragmentTransaction.hide(fragment_Search);
                fragmentTransaction.hide(fragment_Notication);
                fragmentTransaction.show(fragment_Login_main);
                fragmentTransaction.hide(fragment_Collage);
                break;
            case R.id. imv_f:
                Intent intent = new Intent(getApplicationContext(), mainf.class);
                // gọi activity.
                startActivity(intent);
                break;
            case R.id.imv_mainplus:

                break;
            default:
                break;
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
