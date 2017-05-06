package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;

import com.example.khanh.foody4.get_set.restaurant;
import com.example.khanh.foody4.webservice.WebService;

import java.util.List;

/**
 * Created by Khanh on 5/3/2017.
 */

public class AsyncLoadRest extends AsyncTask<Integer,Integer,List<restaurant>>
{
    @Override
    protected List<restaurant> doInBackground(Integer... params) {
        WebService ws= new WebService();
        return ws.getRestaurant(params[0],params[1],params[2],params[3]);
    }
}