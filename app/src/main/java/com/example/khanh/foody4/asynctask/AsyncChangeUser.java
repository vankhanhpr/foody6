package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;

import com.example.khanh.foody4.webservice.WebService;

/**
 * Created by Khanh on 5/9/2017.
 */

public class AsyncChangeUser extends AsyncTask<String,String,Boolean>
{

    @Override
    protected Boolean doInBackground(String... params) {
        WebService ws= new WebService();
        return  ws.changeProfile(params[0],params[1],params[2],params[3],params[4],params[5]);
    }
}
