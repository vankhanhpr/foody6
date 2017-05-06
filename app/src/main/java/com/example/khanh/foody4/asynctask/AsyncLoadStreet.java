package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;

import com.example.khanh.foody4.get_set.street;
import com.example.khanh.foody4.webservice.WebService;

import java.util.List;

/**
 * Created by Khanh on 5/2/2017.
 */

public class AsyncLoadStreet extends AsyncTask<Integer,Integer,List<street>>
{
    @Override
    protected List<street> doInBackground(Integer... params) {
        WebService ws= new WebService();
        return ws.getStreet(params[0]);
    }
}
