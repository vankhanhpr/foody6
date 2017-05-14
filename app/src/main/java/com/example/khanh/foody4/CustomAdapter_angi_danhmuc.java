package com.example.khanh.foody4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Khanh on 4/3/2017.
 */

public class CustomAdapter_angi_danhmuc extends BaseAdapter
{
    //khởi tạo các đối tượng
    List<String> result;
    Context context;
    List<Integer> imageId;
    private static LayoutInflater inflater=null;
    MainActivity mn;

      public static  int vitri =0;

    //hàm này gọi hàm trộn các đối tượng trong textview(imageView và textView) và đổ vào listview
    public CustomAdapter_angi_danhmuc (MainActivity mainActivity,List<String> prgmNameList, List<Integer> prgmImages)
    {
        // TODO Auto-generated constructor stub
        result=prgmNameList;
        context=mainActivity;
        mn=mainActivity;
        imageId=prgmImages;
        inflater = ( LayoutInflater )context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    //trả về độ dài  danh sách
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return result.size();
    }

    //trả về vị trí click trong textview
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
    //chứa 1or nhiều đối tượng trong listview là textview và imageview
    public class Holder
    {
        TextView tv;
        ImageView img,imv_check_a;

    }
    //hàm này  trộn texview với imageview
    //Xử lý khi nhấn vào một dòng trong textview
    //mặc định giá trị ban đầu
    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent)
    {
        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.layout_listview, null);
        holder.tv=(TextView) rowView.findViewById(R.id.textView10);
        holder.img=(ImageView) rowView.findViewById(R.id.imageView10);
        holder.imv_check_a=(ImageView)rowView.findViewById(R.id.imv_check);
        holder.tv.setText(result.get(position));
        holder.img.setImageResource(imageId.get(position));

        if(position==0)
        {
            holder.img.setVisibility(View.GONE);
        }
        if(vitri==position)
        {
            holder.tv.setTextColor(context.getResources().getColor(R.color.red1));
            holder.imv_check_a.setImageResource(R.drawable.icon_check);
            holder.imv_check_a.setColorFilter(context.getResources().getColor(R.color.red1));
        }
        else {
            holder.imv_check_a.setImageResource(R.drawable.icon_check);
            holder.tv.setTextColor(context.getResources().getColor(R.color.black_text));
            holder.imv_check_a.setVisibility(View.GONE);
        }

        return rowView;
    }
}
