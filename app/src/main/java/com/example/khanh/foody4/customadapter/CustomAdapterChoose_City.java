package com.example.khanh.foody4.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khanh.foody4.R;
import com.example.khanh.foody4.get_set.city;
import com.example.khanh.foody4.insert_restaurant.Insert_Restaurant;

import java.util.List;

/**
 * Created by Khanh on 5/12/2017.
 */

public class CustomAdapterChoose_City extends BaseAdapter {
    Context context;
    List<city> tinhThanhs;
    public static int vitri=0;
    private static LayoutInflater inflater=null;
    public CustomAdapterChoose_City(Insert_Restaurant mainActivity, List<city> tinhThanhs){
        context=mainActivity;
        this.tinhThanhs=tinhThanhs;

        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return tinhThanhs.size();
    }

    @Override
    public city getItem(int i) {
        return tinhThanhs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public  class  Holder
    {
        TextView tv_lv_select_city,tv_lv_select_macdinh;
        ImageView imv_lv_select_city;
        public Holder(View view)
        {
            tv_lv_select_city=(TextView)view.findViewById(R.id.tv_lv_select_city);
            imv_lv_select_city=(ImageView)view.findViewById(R.id.imv_lv_select_city);
            tv_lv_select_macdinh=(TextView)view.findViewById(R.id.tv_lv_select_macdinh);
        }
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CustomAdapterChoose_City.Holder holder;
        if(view==null)
        {
            view=inflater.inflate(R.layout.layout_header_item, null);
            holder=new CustomAdapterChoose_City.Holder(view);
            view.setTag(holder);
        }
        else
        {
            holder=(CustomAdapterChoose_City.Holder) view.getTag();
        }
        holder=(CustomAdapterChoose_City.Holder) view.getTag();
        holder.tv_lv_select_city.setText(tinhThanhs.get(i).getCity_Name());
        for(int j=0;j<getCount();j++)
        {
            holder.imv_lv_select_city.setVisibility(View.GONE);
            holder.tv_lv_select_macdinh.setVisibility(View.GONE);
            holder.tv_lv_select_city.setTextColor(context.getResources().getColor(R.color.black_text));
        }
        /*if(i==vitri)
        {
            holder.imv_lv_select_city.setVisibility(View.VISIBLE);
            holder.tv_lv_select_macdinh.setVisibility(View.VISIBLE);
            holder.tv_lv_select_city.setTextColor(context.getResources().getColor(R.color.black_text_list1));
        }*/
        return view;
    }
}
