package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;

import com.example.khanh.foody4.get_set.user_member;
import com.example.khanh.foody4.webservice.WebService;

/**
 * Created by Khanh on 5/9/2017.
 */

public class AsyncLoadUser extends AsyncTask<String ,String,user_member> {
    @Override
    protected user_member doInBackground(String... params) {
        WebService ws= new WebService();
        return ws.getUser(params[0]);
    }
}
