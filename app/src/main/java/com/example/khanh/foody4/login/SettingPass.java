package com.example.khanh.foody4.login;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khanh.foody4.R;
import com.example.khanh.foody4.asynctask.AsyncChangePass;
import com.example.khanh.foody4.asynctask.AsyncLoadUser;
import com.example.khanh.foody4.get_set.user_member;

import java.util.concurrent.ExecutionException;

/**
 * Created by Khanh on 5/10/2017.
 */

public class SettingPass extends AppCompatActivity implements View.OnClickListener {

    EditText edit_text_current_password,edit_text_new_password,edit_text_confirm_new_password;
    TextView text_view_save_change;
    String email,pass, newpass, configpass;
    user_member user;
    LinearLayout bnt_changepass_back;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("GoiTin");
        email = bundle.getString("KetQua");


        edit_text_current_password=(EditText)findViewById(R.id.edit_text_current_password);
        edit_text_new_password=(EditText)findViewById(R.id.edit_text_new_password);
        edit_text_confirm_new_password=(EditText)findViewById(R.id.edit_text_confirm_new_password);
        text_view_save_change=(TextView)findViewById(R.id.text_view_save_change);
        bnt_changepass_back=(LinearLayout)findViewById(R.id.bnt_changepass_back);

        text_view_save_change.setOnClickListener(this);
        bnt_changepass_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.text_view_save_change:
                pass= edit_text_current_password.getText().toString();
                newpass= edit_text_new_password.getText().toString();
                configpass=edit_text_confirm_new_password.getText().toString();
                boolean f=checkPass(pass,newpass,configpass);
                if(f==false)
                {
                    Toast.makeText(this, "Mật khẩu không hợp lệ hoặc nhập lại mật khẩu chưa trùng", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean flag1;
                    flag1= changePass(email,newpass);
                    if(flag1==true)
                    {
                        changePassSuccess();
                    }
                    else
                        Toast.makeText(this, "Đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bnt_changepass_back:
                finish();
                break;
        }
    }
    public  boolean checkPass(String password ,String newPass,String configPass)
    {
        AsyncLoadUser asyncLoadUser= new AsyncLoadUser();
        try {
            user= asyncLoadUser.execute(email).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(!newPass.equals(configPass)) {
            return false;
        }
        if(!user.getPassword().equals(password)) {
            return false;
        }
        return true;
    }
    //Xư lý khi đổi mật khẩu
    public boolean changePass(String email1,String pass1)
    {
        boolean flag= false;
        AsyncChangePass asyncChangePass= new AsyncChangePass();
        try {
            flag= asyncChangePass.execute(email1,pass1).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        return flag;
    }
    //Thông báo sau khi thay đổi password
    //Hàm thông báo sau khi thay đổi thông tin
    private void changePassSuccess() {
        new AlertDialog.Builder(this)
                .setTitle("Cập nhật mật khẩu thành công")
                .setMessage("")
                .setPositiveButton("Xong", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        }

                ).show();
    }
}
