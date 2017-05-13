package com.example.khanh.foody4.upload_image;

import android.os.AsyncTask;

import com.google.gson.JsonObject;

/**
 * Created by Khanh on 5/13/2017.
 */

public class AsynChangeAvatar extends AsyncTask<Object,Object,JsonObject> {
    JsonObject jsonObject;
    public AsynChangeAvatar(JsonObject jsonObject){
        this.jsonObject=jsonObject;
    }
    @Override
    protected JsonObject doInBackground(Object... params) {
        return JsonHTTPHelper.makeHttpResponse(StaticJSON.URL+StaticJSON.CHANGE_AVATAR, true,jsonObject);
    }
}
