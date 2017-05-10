package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;

import com.example.khanh.foody4.webservice.WebService;

/**
 * Created by Khanh on 5/10/2017.
 */

public class AsyncChangePass extends AsyncTask<String,String,Boolean> {
    @Override
    protected Boolean doInBackground(String... params) {
        WebService ws= new WebService();
        return  ws.changePass(params[0],params[1]);
    }
}
