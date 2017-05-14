package com.example.khanh.foody4.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.khanh.foody4.MainActivity;
import com.example.khanh.foody4.R;
import com.example.khanh.foody4.asynctask.AsyncGetResFood;
import com.example.khanh.foody4.get_set.food;
import com.example.khanh.foody4.get_set.quanan_getset;
import com.example.khanh.foody4.get_set.restaurant;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kunsubin on 4/10/2017.
 */
//đưa nội dung lên khung chính bên ăn gì
public class AdapterGridViewAnGi extends BaseAdapter{
    Context context;
    List<food> listNhaHang;
    //restaurant res;
    private static LayoutInflater inflater=null;
    //IChooseItemNhaHang iChoose;
   /* public void setChooseNhaHang(IChooseItemNhaHang iChoose) {
        this.iChoose = iChoose;
    }*/
    public AdapterGridViewAnGi(MainActivity mainActivity, List<food> listNhaHang) {
        this.listNhaHang=listNhaHang;
        context=mainActivity;
        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listNhaHang.size();
    }

    @Override
    public food getItem(int position) {
        return listNhaHang.get(position);
    }

    @Override
    public long getItemId(int position) {
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
        LinearLayout ln_food1,ln_info1;
        ImageView imv_info_1,imv_user1;
        TextView food_name1,rest_name1,tv_address1,tv_username1,tv_des1;
        //
        public Holder(View view){
            ln_food1 = (LinearLayout)view.findViewById(R.id.food_ln_food1);
            ln_info1 = (LinearLayout)view.findViewById(R.id.food_ln_info1);
            imv_info_1 = (ImageView)view.findViewById(R.id.food_imv_info1);
            imv_user1 = (ImageView)view.findViewById(R.id.food_imv_user1);
            food_name1 = (TextView)view.findViewById(R.id.food_tv_foodname1);
            rest_name1 = (TextView)view.findViewById(R.id.food_tv_restname1);
            tv_address1 = (TextView)view.findViewById(R.id.food_tv_address1);
            tv_username1 = (TextView)view.findViewById(R.id.food_tv_username1);
            tv_des1 = (TextView)view.findViewById(R.id.food_tv_des1);
        }
        //show dữ liệu lên grid view tab Ăn Gi
        public void showNoiDung(food nhaHang,restaurant res){

            food_name1.setText(nhaHang.getFood_Name());

            if(res!=null)
            {
                tv_address1.setText(res.getAddress_Name());
                rest_name1.setText(res.getRest_Name());
            }

            if (nhaHang.getPicture()!= null) {
                Glide.with(context).load(nhaHang.getAnh()).into(imv_info_1);
            }
        }

    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        food item=listNhaHang.get(position);
        restaurant res;
        res=getRes(item.getRes_ID());

        if(convertView==null){
            convertView=inflater.inflate(R.layout.layout_view_angi_nhahang, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }
        else{
            holder=(Holder) convertView.getTag();
        }

        holder=(Holder) convertView.getTag();

        holder.showNoiDung(item,res);

        return convertView;
    }

    public  restaurant getRes(int resID)
    {
        restaurant res=null;
        AsyncGetResFood asyncGetResFood = new AsyncGetResFood();
        try {
            res= asyncGetResFood.execute(resID).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return res;

    }
    public class ItemNhaHang implements  View.OnClickListener{
        food nhaHang;
        public ItemNhaHang(food nhaHang){
            this.nhaHang=nhaHang;
        }

        @Override
        public void onClick(View v) {
           // AdapterGridViewAnGi.this.iChoose.ChooseItemNhaHang(this.nhaHang);
        }
    }
}
