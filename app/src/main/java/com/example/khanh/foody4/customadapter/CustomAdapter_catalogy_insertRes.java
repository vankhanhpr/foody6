package com.example.khanh.foody4.customadapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khanh.foody4.CustomAdapter_odau_danhmuc;
import com.example.khanh.foody4.MainActivity;
import com.example.khanh.foody4.R;
import com.example.khanh.foody4.odau;

import java.util.List;

/**
 * Created by Khanh on 5/16/2017.
 */

public class CustomAdapter_catalogy_insertRes extends BaseAdapter {
    List<String> result;
    List<Integer> imageId;


    private static LayoutInflater inflater=null;
    Activity mn;
    public  static  int vitri=0;
    public MainActivity mainActivity;
    public static int positionselected=0;

    Context context;
    public CustomAdapter_catalogy_insertRes (Context context,List<String> prgmNameList, List<Integer> prgmImages)
    {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        //context=context;
        //this.mn=mainActivity;
        imageId=prgmImages;
        this.context = context;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //this.mainActivity = mainActivity;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }
    @Override
    public int getItemViewType(int i) {
        return i;
    }
    public class Holder
    {
        TextView tv;
        ImageView img,imv_check_a;

    }
    public static void setIsselected(int posotion){

    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        CustomAdapter_catalogy_insertRes.Holder holder = new CustomAdapter_catalogy_insertRes.Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.layout_listview, null);
        holder.tv = (TextView) rowView.findViewById(R.id.textView10);
        holder.img = (ImageView) rowView.findViewById(R.id.imageView10);
        holder.tv.setText(result.get(position));
        holder.imv_check_a = (ImageView) rowView.findViewById(R.id.imv_check);
        holder.img.setImageResource(imageId.get(position));
        holder.img.setVisibility(View.GONE);

        if (position == 0) {
            holder.tv.setTextColor(context.getResources().getColor(R.color.red1));
            holder.imv_check_a.setImageResource(R.drawable.icon_check);
            holder.imv_check_a.setColorFilter(context.getResources().getColor(R.color.red1));
            holder.img.setVisibility(View.GONE);
            holder.imv_check_a.setVisibility(View.VISIBLE);
        }

        if (positionselected == position) {
            holder.tv.setTextColor(context.getResources().getColor(R.color.red1));
            holder.imv_check_a.setImageResource(R.drawable.icon_check);
            holder.imv_check_a.setVisibility(View.VISIBLE);
        } else {
            holder.imv_check_a.setImageResource(R.drawable.icon_check);
            holder.tv.setTextColor(context.getResources().getColor(R.color.black_text));
            holder.imv_check_a.setVisibility(View.GONE);
        }
        rowView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                TextView tv1;
                ImageView img1,imv_check1;
                LinearLayout tab_liner;
                LinearLayout tab_chinh_odau;
                // TODO Auto-generated method stub
                /*Toast.makeText(context, "You Clicked "+result.get(position), Toast.LENGTH_LONG).show();*/
                /*holderimv_check=(ImageView)v.findViewById(R.id.imv_check);.*/

                for(int i=0;i<parent.getChildCount();i++)
                {
                    tv1=(TextView)parent.getChildAt(i).findViewById(R.id.textView10);
                    imv_check1=(ImageView)parent.getChildAt(i).findViewById(R.id.imv_check);

                    tv1.setTextColor(context.getResources().getColor(R.color.black_text));
                    imv_check1.setImageResource(0);
                    imv_check1.setVisibility(View.GONE);
                }

                vitri=position;
                positionselected=position;
                tv1=(TextView)v.findViewById(R.id.textView10);
                img1=(ImageView)v.findViewById(R.id.imageView10);
                imv_check1=(ImageView)v.findViewById(R.id.imv_check);
                tv1.setTextColor(context.getResources().getColor(R.color.red1));
                img1.setVisibility(View.GONE);
                imv_check1.setImageResource(R.drawable.icon_check);
                imv_check1.setVisibility(View.VISIBLE);
                //imv_check1.setColorFilter(context.getResources().getColor(R.color.red1));

/*
                TextView tv_moinhat_odau;
                tv_moinhat_odau=(TextView)mn.findViewById(R.id.tv_moinhat_odau);
                tv_moinhat_odau.setText(tv1.getText());*/

                getdata.setChoose_catalogyName(result.get(position).toString());
                getdata.setChoose_catalogy(position+1);

            }
        });

        return rowView;
    }
}
