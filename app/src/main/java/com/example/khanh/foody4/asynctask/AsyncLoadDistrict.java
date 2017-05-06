package com.example.khanh.foody4.asynctask;

import android.os.AsyncTask;
import com.example.khanh.foody4.get_set.district;
import com.example.khanh.foody4.webservice.WebService;
import com.example.khanh.foody4.customadapter.getdata;
import java.util.List;

/**
 * Created by Khanh on 4/24/2017.
 */

public class AsyncLoadDistrict extends AsyncTask<Integer,Integer,List<district>>
{

    @Override
    protected List<district> doInBackground(Integer... params) {
        WebService sc = new WebService();
        return sc.getDistrict(params[0]);
    }
}
