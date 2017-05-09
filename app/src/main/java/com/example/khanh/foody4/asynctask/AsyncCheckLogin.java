package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.khanh.foody4.webservice.WebService;

/**
 * Created by Khanh on 5/9/2017.
 */

public class AsyncCheckLogin extends AsyncTask<String,String,Integer>
{
    @Override
    protected Integer doInBackground(String... params) {
        WebService ws= new WebService();
       return ws.setLogin(params[0],params[1]);
    }
}
