package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;
import android.widget.ImageView;

import com.example.khanh.foody4.get_set.food;
import com.example.khanh.foody4.webservice.WebService;

import java.util.List;

/**
 * Created by Khanh on 5/8/2017.
 */

public class AsyncLoadFood extends AsyncTask <Integer,Integer,List<food>>
{
    @Override
    protected List<food> doInBackground(Integer... params) {
        WebService ws = new WebService();
        return ws.getFood(params[0] ,params[1] ,params[2] ,params[3]);
    }
}
