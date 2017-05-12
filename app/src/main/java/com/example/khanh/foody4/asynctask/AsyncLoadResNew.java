package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;

import com.example.khanh.foody4.get_set.restaurant;
import com.example.khanh.foody4.webservice.WebService;

import java.util.List;

/**
 * Created by Khanh on 5/12/2017.
 */

public class AsyncLoadResNew extends AsyncTask <String,String,List<restaurant>> {
    @Override
    protected List<restaurant> doInBackground(String... params) {
        WebService ws= new WebService();
        return  ws.getResNew();
    }
}
