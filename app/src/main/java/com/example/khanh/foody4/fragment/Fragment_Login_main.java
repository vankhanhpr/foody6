package com.example.khanh.foody4.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.khanh.foody4.R;
import com.example.khanh.foody4.angi;
import com.example.khanh.foody4.asynctask.AsyncLoadImage;
import com.example.khanh.foody4.asynctask.AsyncLoadUser;
import com.example.khanh.foody4.customadapter.getdata;
import com.example.khanh.foody4.get_set.user_member;
import com.example.khanh.foody4.login.Edit_Profile;
import com.example.khanh.foody4.login.Login;

import org.kobjects.base64.Base64;

import java.util.concurrent.ExecutionException;

/**
 * Created by Khanh on 4/12/2017.
 */

public class Fragment_Login_main extends Fragment implements View.OnClickListener {
    LinearLayout tab_login;
    RelativeLayout tab_view_all_profile,tab_logout,setting_user;

    user_member user;
    TextView tv_name_user;
    ImageView imv_user;
    String kq="ERROR";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_login,container,false);

        tab_login= (LinearLayout) view.findViewById(R.id.tab_login);
        tv_name_user=(TextView)view.findViewById(R.id.tv_name_user);
        imv_user=(ImageView)view.findViewById(R.id.imv_user);
        tab_view_all_profile=(RelativeLayout)view.findViewById(R.id.tab_view_all_profile);
        tab_logout=(RelativeLayout)view.findViewById(R.id.tab_logout);
        setting_user=(RelativeLayout)view.findViewById(R.id.setting_user);

        tab_login.setOnClickListener(this);
        tab_view_all_profile.setOnClickListener(this);
        tab_logout.setOnClickListener(this);
        setting_user.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.tab_login:
                Intent intent = new Intent(getActivity(),Login.class);
                startActivityForResult(intent,789);
                break;
            case R.id.tab_view_all_profile:
                sendToProfile(kq,111);
                break;
            case R.id.tab_logout:
                tab_logout.setVisibility(View.GONE);
                break;
            case R.id.setting_user:

                break;
        }

    }
    //Hàm gửi kết quả cho activity edit thông tin tài khoản
    public  void sendToProfile(String temp,int position )
    {

        Intent intent = new Intent(getActivity().getApplicationContext(),Edit_Profile.class);
        Bundle bundle = new Bundle();
        bundle.putString("KetQua",temp);
        intent.putExtra("GoiTin", bundle);
        startActivityForResult(intent,111);
    }

    //Lấy dữ liệu truyền về khi đăng nhập thành công
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(data==null)
            return;
        if (requestCode == 789)
        {
            // lấy giá trị kết quả
            Bundle bundle = data.getBundleExtra("TapTin");
            kq = bundle.getString("KetQua");
            loadUser(kq);
            tab_logout.setVisibility(View.VISIBLE);

        }
        if(requestCode==111)
        {
            loadUser(kq);
        }
    }
    //Khi nhấn logout
    public  void logOut()
    {
        tab_logout.setVisibility(View.GONE);
        tv_name_user.setText("Đăng nhâp");
        imv_user.setBackgroundResource(R.drawable.ic_tab_profile);
    }

    //hàm load thông tin tài khoản sau khi đăng nhập theo Email
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
        tv_name_user.setText(user.getUser_FamilyName()+" "+user.getUser_Name());
        loadImage(user.getUser_Picture());
        Glide.with(getContext()).load(user.getAvatar()).into(imv_user);
    }
    //load anh qua ten anh
    public  void loadImage(String image)
    {
        String s="";
        AsyncLoadImage asyncLoadImage = new AsyncLoadImage();
        try {
            s=asyncLoadImage.execute(image).get();
            if(s!=null)
            {
                byte[] valueDecoded = Base64.decode(s);
                user.setAvatar(valueDecoded);
                // Toast.makeText(mainActivity, "khanh"+valueDecoded, Toast.LENGTH_SHORT).show();
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}
