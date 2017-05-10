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
import com.example.khanh.foody4.asynctask.AsyncChangeUser;
import com.example.khanh.foody4.asynctask.AsyncLoadUser;
import com.example.khanh.foody4.get_set.user_member;

import java.util.concurrent.ExecutionException;

/**
 * Created by Khanh on 5/9/2017.
 */

public class Edit_Profile extends AppCompatActivity implements View.OnClickListener
{
    TextView text_view_user_name,tv_save;
    String email;
    user_member user;
    boolean flag=false;

    LinearLayout imv_select_back_profile;
    EditText edit_text_login_name,edit_text_last_name,edit_text_first_name,edit_text_email,edit_text_marry_status,edit_text_sex;
    String userID,name,familyName,maria,sex;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_profile);

        text_view_user_name= (TextView)findViewById(R.id.text_view_user_name);
        imv_select_back_profile=(LinearLayout)findViewById(R.id.imv_select_back_profile);
        edit_text_login_name=(EditText)findViewById(R.id.edit_text_login_name);
        edit_text_last_name=(EditText)findViewById(R.id.edit_text_last_name);

        edit_text_login_name=(EditText)findViewById(R.id.edit_text_login_name);
        edit_text_last_name=(EditText)findViewById(R.id.edit_text_last_name);
        edit_text_first_name=(EditText)findViewById(R.id.edit_text_first_name);
        edit_text_email=(EditText)findViewById(R.id.edit_text_email);
        edit_text_marry_status=(EditText)findViewById(R.id.edit_text_marry_status);
        edit_text_sex=(EditText)findViewById(R.id.edit_text_sex);

        tv_save=(TextView)findViewById(R.id.tv_save);



        imv_select_back_profile.setOnClickListener(this);
        tv_save.setOnClickListener(this);


        // Lấy intent của Activity này
        Intent intent = getIntent();
        // lấy thùng chứa Bundle với tên giao dịch là "GoiTin"
        Bundle bundle = intent.getBundleExtra("GoiTin");
        email=bundle.getString("KetQua");
        //Lấy thông tin trong của user đổ ra các text
        loadUser(email);




    }
    //load thông tin tài khoản
    public void loadUser(String s)
    {
        AsyncLoadUser asyncLoadUser = new AsyncLoadUser();
        try {
            user= asyncLoadUser.execute(s).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }
        text_view_user_name.setText(user.getUser_FamilyName()+" "+user.getUser_Name());
        edit_text_login_name.setText(user.getUser_ID());
        edit_text_last_name.setText(user.getUser_Name());
        edit_text_first_name.setText(user.getUser_FamilyName());
        edit_text_email.setText(user.getUser_Mail());
        edit_text_marry_status.setText(user.getUse_Mari());
        edit_text_sex.setText(user.getUser_Sex());
    }
    //Thay đổi thông tin tài khoản
    public void changeUser()
    {
        userID=edit_text_login_name.getText().toString();
        name=edit_text_last_name.getText().toString();
        familyName=edit_text_first_name.getText().toString();
        maria=edit_text_marry_status.getText().toString();
        sex=edit_text_sex.getText().toString();
        AsyncChangeUser asyncChangeUser= new AsyncChangeUser();
        try
        {
            flag= asyncChangeUser.execute(email,userID,name,familyName,maria,sex).get();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.imv_select_back_profile:
                finish();
                break;
            case R.id.tv_save:
                changeUser();
                if(flag==true)
                {
                    editSuccess();

                }
                else
                    Toast.makeText(this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //Hàm thông báo sau khi thay đổi thông tin
    private void editSuccess() {
        new AlertDialog.Builder(this)
                .setTitle("Thông tin của bạn đã được cập nhật!")
                .setMessage("")
                .setPositiveButton("Xong", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                loadUser(email);
                                sendToMain(email,111);
                                finish();
                            }
                        }


              ).show();
    }


    //Gửi dữ liệu về lại hàm main
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
