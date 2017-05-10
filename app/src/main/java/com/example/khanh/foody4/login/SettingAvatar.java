package com.example.khanh.foody4.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.khanh.foody4.R;

/**
 * Created by Khanh on 5/10/2017.
 */

public class SettingAvatar extends AppCompatActivity implements View.OnClickListener
{
    String email;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("GoiTin");
        email = bundle.getString("KetQua");
    }

    @Override
    public void onClick(View v) {

    }
}
