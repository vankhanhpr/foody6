package com.example.khanh.foody4.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.khanh.foody4.R;

/**
 * Created by Khanh on 5/10/2017.
 */

public class SettingProfile extends AppCompatActivity implements OnClickListener
{
    LinearLayout tab_setting_password,bnt_changeprofile_back,tab_change_avatar;
    String kq;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("GoiTin");
        kq = bundle.getString("KetQua");

        tab_setting_password=(LinearLayout)findViewById(R.id.tab_setting_password);
        bnt_changeprofile_back=(LinearLayout)findViewById(R.id.bnt_changeprofile_back);
        tab_change_avatar=(LinearLayout)findViewById(R.id.tab_change_avatar);

        tab_setting_password.setOnClickListener(this);
        bnt_changeprofile_back.setOnClickListener(this);
        tab_change_avatar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tab_setting_password:
                sendToPass(kq,333);
                break;
            case R.id.bnt_changeprofile_back:
                finish();
                break;
            case R.id.tab_change_avatar:
                sendToAvatar(kq,444);
                break;
        }
    }

    //Lấy dữ liệu truyền qua
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data==null)
            return;
        if (requestCode == 222)
        {
            // lấy giá trị kết quả
            Bundle bundle = data.getBundleExtra("GoiTin");
            kq = bundle.getString("KetQua");

        }
    }

    //Hàm gọi activity thay đổi mật khẩu và gửi dữ liệu là email đi
    public  void sendToPass(String temp,int position )
    {

        Intent intent = new Intent(this,SettingPass.class);
        Bundle bundle = new Bundle();
        bundle.putString("KetQua",temp);
        intent.putExtra("GoiTin", bundle);
        startActivityForResult(intent,position);
    }
    //Hàm gọi activity thay đổi ảnh đại diện và gửi thông tin là mail qua
    public  void sendToAvatar(String temp,int position )
    {
        Intent intent = new Intent(this,SettingPass.class);
        Bundle bundle = new Bundle();
        bundle.putString("KetQua",temp);
        intent.putExtra("GoiTin", bundle);
        startActivityForResult(intent,position);
    }
}
