package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;
import android.util.Log;

import com.example.khanh.foody4.webservice.WebService;

/**
 * Created by Khanh on 5/2/2017.
 */

public class AsyncLoadImage extends AsyncTask<String,String,String>
{
    @Override
    protected String doInBackground(String... params)
    {
        WebService ws= new WebService();

        return ws.getImage(params[0]);
    }
}
