package com.example.khanh.foody4.login;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.khanh.foody4.R;
import com.example.khanh.foody4.asynctask.AsyncLoadUser;
import com.example.khanh.foody4.get_set.user_member;
import com.example.khanh.foody4.upload_image.AsynChangeAvatar;
import com.example.khanh.foody4.upload_image.Change_Avatar;
import com.example.khanh.foody4.upload_image.ImageGalleryBean;
import com.example.khanh.foody4.upload_image.PermissionUtil;
import com.example.khanh.foody4.upload_image.StaticData;
import com.example.khanh.foody4.upload_image.StaticObjectJSON;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static com.example.khanh.foody4.R.id.cancel_action;
import static com.example.khanh.foody4.R.id.cover_image;
import static com.example.khanh.foody4.R.id.profile_image;

/**
 * Created by Khanh on 5/10/2017.
 */

public class SettingAvatar extends AppCompatActivity implements View.OnClickListener
{
    ImageGalleryBean uploadAvatar = null;
    ImageGalleryBean uploadCover = null;
    String email;
    LinearLayout linear_layout_change_avatar;
    TextView text_view_save_change;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_avatar);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("GoiTin");
        email = bundle.getString("KetQua");

        linear_layout_change_avatar=(LinearLayout)findViewById(R.id.linear_layout_change_avatar);
        text_view_save_change=(TextView)findViewById(R.id.text_view_save_change);

        linear_layout_change_avatar.setOnClickListener(this);
        text_view_save_change.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.linear_layout_change_avatar:
                /*Intent intent = new Intent(this, Change_Avatar.class);
                startActivityForResult(intent, 999);*/

                showPopup(v);

                break;
            case R.id.text_view_save_change:
                uploadAvatar(uploadAvatar);
                break;
        }

    }
    private void showPopup(View v)
    {
        PopupMenu popup = new PopupMenu(this, v);
        popup.getMenuInflater().inflate(R.menu.select_photo_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(onMenuItemClickListener);
        popup.show();
    }

    PopupMenu.OnMenuItemClickListener onMenuItemClickListener = new PopupMenu.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            switch (item.getItemId()) {
                case R.id.select_from_gallery:
                    if(PermissionUtil.isReadWritePermission(SettingAvatar.this.getApplicationContext())){
                        Intent intent=new Intent(SettingAvatar.this, Change_Avatar.class);
                        intent.putExtra("mode",Change_Avatar.SINGLE_SELECT);
                        startActivityForResult(intent,17);
                        return true;
                    }
                    PermissionUtil.marshmallowReadWritePermissionCheck(SettingAvatar.this);
                    return true;
                case R.id.select_from_camera:
                    break;
            }
            return false;
        }
    };


    //Lấy dữ liệu truyền về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 17) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<ImageGalleryBean> dataReponse = new ArrayList<>();
                dataReponse = data.getParcelableArrayListExtra("images");
                if(dataReponse!=null && dataReponse.size()>0){
                    updateImageView(dataReponse.get(0));
                }
            }
        }
    }

    //Thay đổi hình ảnh
    boolean changeavatar = false;
    boolean changecover = false;
    public void updateImageView(ImageGalleryBean image) 
    {
        if (this.changeavatar) 
        {
            this.changeavatar = false;
            this.uploadAvatar = image;
            //Glide.with(this.getApplicationContext()).load("file://" + uploadAvatar.getPath()).into(profile_image);
        }
        if (this.changecover) {
            this.changecover = false;
            this.uploadCover = image;
            //Glide.with(this.getApplicationContext()).load("file://" + uploadCover.getPath()).into(cover_image);
        }
    }

    public void uploadAvatar(ImageGalleryBean uploadAvatar){

        JsonObject input= StaticObjectJSON.createImageInputObject(uploadAvatar.getPath());
        AsynChangeAvatar asynChangeAvatar=new AsynChangeAvatar(input);
        try {
            JsonObject object= asynChangeAvatar.execute().get();
            //Log.d("hạhfds",object.toString());

            String bool=object.get("success").toString();
            Boolean f=Boolean.parseBoolean(bool);

            if(f){
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingAvatar.this);
                builder.setMessage("Thay đổi avatar thành công!");
                /*builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    *//*public void onClick(DialogInterface dialog, int id) {
                        //do things
                        AsyncLoadUser usern=new AsyncLoadUser();
                        try {
                            user_member user1=usern.execute(StaticData.getObjectInfoUser().getUser_Name()).get();
                            AsynGetImage asynGetImage=new AsynGetImage();
                            String stringImage=asynGetImage.execute(user1.getAvatar()).get();
                            byte[] valueDecoded = Base64.decode(stringImage);
                            if(valueDecoded!=null)
                                user1.setHinh(valueDecoded);
                            if(user1!=null)
                                StaticData.setObjectInfoUser(user1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                        Intent returnIntent = new Intent();
                        setResult(Activity.RESULT_OK, returnIntent);
                        finish();
                    }*//*
                });*/
                AlertDialog alert = builder.create();
                alert.show();
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingAvatar.this);
                builder.setMessage("Thay đổi avatar thất bại!");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
