package com.example.khanh.foody4.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khanh.foody4.R;
import com.example.khanh.foody4.asynctask.AsyncCheckLogin;

import java.util.concurrent.ExecutionException;

/**
 * Created by Khanh on 5/9/2017.
 */

public class Login extends AppCompatActivity implements View.OnClickListener
{
    TextView tv_login_email;
    LinearLayout tab_login_select,tab_login_email,tab_imv_login_email,tab_login_back;

    EditText edit_text_username,edit_text_password;
    TextView tv_login;
    String user,pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tab_login_back=(LinearLayout) findViewById(R.id.tab_login_back);
        tv_login_email=(TextView)findViewById(R.id.tv_login_email);
        tab_login_select=(LinearLayout)findViewById(R.id.tab_login_select);
        tab_login_email=(LinearLayout)findViewById(R.id.tab_login_email);
        tab_imv_login_email=(LinearLayout)findViewById(R.id.tab_imv_login_email);

        edit_text_username=(EditText)findViewById(R.id.edit_text_username);
        edit_text_password=(EditText)findViewById(R.id.edit_text_password);
        tv_login=(TextView)findViewById(R.id.tv_login);


        tab_login_back.setOnClickListener(this);
        tv_login_email.setOnClickListener(this);
        tab_imv_login_email.setOnClickListener(this);
        tv_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tab_login_back:
                finish();
                break;
            case R.id.tv_login_email:
                tab_login_select.setVisibility(View.GONE);
                tab_login_email.setVisibility(View.VISIBLE);

                break;
            case R.id.tab_imv_login_email:
                tab_login_select.setVisibility(v.VISIBLE);
                tab_login_email.setVisibility(v.GONE);
                break;
            case R.id.tv_login:
                int temp=-1;
                user= edit_text_username.getText().toString().trim();
                pass=edit_text_password.getText().toString().trim();
                AsyncCheckLogin asyncCheckLogin= new AsyncCheckLogin();
                try
                {
                    temp= asyncCheckLogin.execute(user,pass).get();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
                catch (ExecutionException e) {
                    e.printStackTrace();
                }

                if(temp==1) {
                    Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    sendToMain(user,789);
                }
                else {
                    Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    //Lấy user đã đang nhập
    public  void sendToMain(String temp,int position )
    {

        Intent intent = getIntent();
        Bundle bundle = new Bundle();
        bundle.putString("KetQua",temp);
        intent.putExtra("TapTin", bundle);
        setResult(position,intent);
        finish(); // Đóng Activity hiện tại
    }

}
