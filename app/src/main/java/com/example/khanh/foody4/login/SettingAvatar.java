package com.example.khanh.foody4.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.khanh.foody4.R;
import com.example.khanh.foody4.upload_image.Change_Avatar;

/**
 * Created by Khanh on 5/10/2017.
 */

public class SettingAvatar extends AppCompatActivity implements View.OnClickListener
{
    String email;
    LinearLayout linear_layout_change_avatar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("GoiTin");
        email = bundle.getString("KetQua");

        linear_layout_change_avatar=(LinearLayout)findViewById(R.id.linear_layout_change_avatar);

        linear_layout_change_avatar.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.linear_layout_change_avatar:
                Intent intent = new Intent(this, Change_Avatar.class);
                startActivityForResult(intent, 999);
                break;
        }

    }
}
