package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;

import com.example.khanh.foody4.get_set.restaurant;
import com.example.khanh.foody4.webservice.WebService;

/**
 * Created by Khanh on 5/14/2017.
 */

public class AsyncGetResFood extends AsyncTask<Integer,Integer,restaurant> {
    @Override
    protected restaurant doInBackground(Integer... params) {
        WebService ws= new WebService();
        return ws.getResFood(params[0]);
    }
}
