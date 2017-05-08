package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;

import com.example.khanh.foody4.get_set.city;
import com.example.khanh.foody4.webservice.WebService;

import java.util.List;

/**
 * Created by Khanh on 4/20/2017.
 */

public class  AsyncLoadCity extends AsyncTask<Void,Void,List<city>> {
    public AsyncLoadCity() {

    }

    @Override
    protected List<city> doInBackground(Void... params) {
        WebService sv = new WebService();
        return sv.getCity();
    }
}

