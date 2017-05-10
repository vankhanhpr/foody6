package com.example.khanh.foody4.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.khanh.foody4.R;

/**
 * Created by Khanh on 5/10/2017.
 */

public class NoDataAdapter extends BaseAdapter {
    Context context;
    public NoDataAdapter(Context context){
        this.context=context;
    }
    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       convertView= LayoutInflater.from(context).inflate(R.layout.layout_header_item,parent, false);
        return convertView;
    }
}
