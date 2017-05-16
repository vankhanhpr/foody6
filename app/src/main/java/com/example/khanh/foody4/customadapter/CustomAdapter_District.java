package com.example.khanh.foody4.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.khanh.foody4.MainActivity;
import com.example.khanh.foody4.R;
import com.example.khanh.foody4.get_set.city;
import com.example.khanh.foody4.get_set.district;
import com.example.khanh.foody4.select_city_district.Select_province;

import java.util.List;

/**
 * Created by Khanh on 4/25/2017.
 */

public class CustomAdapter_District extends BaseAdapter
{
    int vitri=0;
    Context context;
    List<district> listDistricts;
    private static LayoutInflater inflater=null;
    public CustomAdapter_District(Context context, List<district>listDistricts)
    {
        context=context;
        this.listDistricts=listDistricts;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listDistricts.size();
    }

    @Override
    public district getItem(int i)
    {
        return listDistricts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public  class  Holder
    {
        TextView tv_view_dt;
        ImageView imv_tv_check;
        public Holder(View view)
        {
            tv_view_dt=(TextView)view.findViewById(R.id.tv_view_dt);
            imv_tv_check=(ImageView)view.findViewById(R.id.imv_lv_select_city);
        }
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CustomAdapter_District.Holder holder;
        if(view==null)
        {
            view=inflater.inflate(R.layout.layout_textview_lv_changedistrict, null);
            holder=new CustomAdapter_District.Holder(view);
            view.setTag(holder);
        }
        else
        {
            holder=(CustomAdapter_District.Holder) view.getTag();
        }
        holder=(CustomAdapter_District.Holder) view.getTag();
        holder.tv_view_dt.setText(listDistricts.get(i).getDistrict_Name());
        return view;
    }

}
