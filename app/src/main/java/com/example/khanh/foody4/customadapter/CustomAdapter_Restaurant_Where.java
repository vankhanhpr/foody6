package com.example.khanh.foody4.customadapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.khanh.foody4.MainActivity;
import com.example.khanh.foody4.R;
import com.example.khanh.foody4.asynctask.AsyncLoadImage;
import com.example.khanh.foody4.get_set.restaurant;

import org.kobjects.base64.Base64;

import java.util.List;
import java.util.concurrent.ExecutionException;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * Created by Khanh on 5/3/2017.
 */

public class CustomAdapter_Restaurant_Where extends BaseAdapter
{
    Context context;
    List<restaurant> listRestaurant;
    private static LayoutInflater inflater=null;
    public CustomAdapter_Restaurant_Where(MainActivity mainActivity, List<restaurant> listRestaurant)
    {
        context=mainActivity;
        this.listRestaurant=listRestaurant;
        inflater = (LayoutInflater)context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return listRestaurant.size();
    }

    @Override
    public restaurant getItem(int position) {
        return listRestaurant.get(position);
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
        View item;
        //tạo các view

        //tổng hợp
        LinearLayout tab_main;
        //--------------------------
        //phần ảnh và tiêu đề
        public LinearLayout home_ln_info;

        public LinearLayout home_ln_point_border;
        public LinearLayout home_ln_point;
        public TextView tv_diem;
        //
        public LinearLayout home_ln_name;
        public  TextView tv_tennhahang;

        public RelativeLayout rlt_diadiem;
        public  TextView tv_diachi;

        public  LinearLayout tab_khoangcach;
        public ImageView imageView2;
        public  TextView textView3;
        //ảnh nhà hàng
        public  ImageView home_imageview_album;
        //comment
        public  LinearLayout home_ln_coment;
        public  LinearLayout home_ln_coment_child_1;

        public CircleImageView home_imv_user_1;
        //Bình luận
        public  LinearLayout home_ln_detailcoment_1;
        public  TextView home_username_1;
        public  TextView home_pointcoment_1;

        public  TextView home_comenttext_1;

        //----
        public  LinearLayout home_ln_coment_child_2;
        public  CircleImageView home_imv_user_2;

        public  LinearLayout home_ln_detailcoment_2;
        public  TextView home_username_2;
        public  TextView home_pointcoment_2;
        public  TextView home_comenttext_2;

        //luot xem
        public  LinearLayout home_ln_bottom;



        public Holder(View view){
            this.item=view;
            init(view);
        }
        //ánh xạ các view
        public void init(View view)
        {
            home_ln_info=(LinearLayout)item.findViewById(R.id.home_ln_info);
            home_ln_point_border=(LinearLayout)item.findViewById(R.id.home_ln_point_border);
            home_ln_point=(LinearLayout)item.findViewById(R.id.home_ln_point);

            tv_diem=(TextView)item.findViewById(R.id.tv_diem);
            home_ln_name=(LinearLayout)item.findViewById(R.id.home_ln_name);
            tv_tennhahang=(TextView)item.findViewById(R.id.tv_tennhahang);
            rlt_diadiem=(RelativeLayout)item.findViewById(R.id.rlt_diadiem);
            tv_diachi=(TextView)item.findViewById(R.id.tv_diachi);

            tab_khoangcach=(LinearLayout)item.findViewById(R.id.tab_khoangcach);
            imageView2=(ImageView)item.findViewById(R.id.imageView2);
            textView3=(TextView)item.findViewById(R.id.textView3);

            home_imageview_album=(ImageView)item.findViewById(R.id.home_imageview_album);
            home_ln_coment=(LinearLayout)item.findViewById(R.id.home_ln_coment);
            home_ln_coment_child_1=(LinearLayout)item.findViewById(R.id.home_ln_coment_child_1);

            home_imv_user_1=(CircleImageView)item.findViewById(R.id.home_imv_user_1);
            home_ln_detailcoment_1=(LinearLayout)item.findViewById(R.id.home_ln_detailcoment_1);
            home_username_1=(TextView)item.findViewById(R.id.home_username_1);
            home_pointcoment_1=(TextView)item.findViewById(R.id.home_pointcoment_1);
            home_comenttext_1=(TextView)item.findViewById(R.id.home_comenttext_1);

            home_ln_coment_child_2=(LinearLayout)item.findViewById(R.id.home_ln_coment_child_2);
            home_imv_user_2=(CircleImageView)item.findViewById(R.id.home_imv_user_2);
            home_ln_detailcoment_2=(LinearLayout)item.findViewById(R.id.home_ln_detailcoment_2);

            home_username_2=(TextView)item.findViewById(R.id.home_username_2);
            home_pointcoment_2=(TextView)item.findViewById(R.id.home_pointcoment_2);
            home_comenttext_2=(TextView)item.findViewById(R.id.home_comenttext_2);

            home_ln_bottom=(LinearLayout)item.findViewById(R.id.home_ln_bottom);

        }
        //show dữ liệu lên theo từng item trong listNhaHang lên khung màn hình chính
        public void showHolder(restaurant restaurant)
        {
            showHeader(restaurant);
            //showMoreImage(restaurant);
            //showBinhLuan(restaurant);
            //updateStatusNhaHang(restaurant);
        }
        //hiểu thị tiêu đề nhà hàng, địa chỉ..
        private void showHeader(restaurant restaurant)
        {
            tv_tennhahang.setText(restaurant.getRest_Name());
            tv_diachi.setText(restaurant.getAddress_Name());

            if (restaurant.getPhoto()!= null)
            {

                byte[] valueDecoded = Base64.decode(restaurant.getPhoto().toString().trim());
                //
                Glide.with(context).load(valueDecoded).into(home_imageview_album);
                //Log.d("lan 2",restaurant.getPhoto().toString());
               // Glide.with(context).load(restaurant.getPhoto().toString()).into(home_imageview_album);
            }
        }
        /*//hình ảnh khác của nhà hàng
        public void showMoreImage(restaurant restaurant) {
            if (restaurant.getPhoto() == null || restaurant.getPhoto().length()<= 0) {
                //linear_layout_sub_img_restaurant.setVisibility(View.GONE);
                return;
            }
            else
            {

            }
        }*/
        //show các mở rộng  của nhà hàng
        /*public void showImageSmall(int index, NhaHang nhaHang) {
            //có 1 hình
            if (index == 1) {
                image_view_sub_img_res_2.setVisibility(View.GONE);
                image_view_sub_img_res_3.setVisibility(View.GONE);

                image_view_sub_img_res_1.setVisibility(View.VISIBLE);

                Glide.with(context).load(nhaHang.getListHinh().get(0)).into(image_view_sub_img_res_1);

                return;
            }
            // //có 2 hình
            if (index == 2) {
                image_view_sub_img_res_3.setVisibility(View.GONE);

                image_view_sub_img_res_1.setVisibility(View.VISIBLE);
                image_view_sub_img_res_2.setVisibility(View.VISIBLE);

                Glide.with(context).load(nhaHang.getListHinh().get(0)).into(image_view_sub_img_res_1);
                Glide.with(context).load(nhaHang.getListHinh().get(1)).into(image_view_sub_img_res_2);

                return;
            }
            //có 3 hình
            if (index == 3) {
                image_view_sub_img_res_1.setVisibility(View.VISIBLE);
                image_view_sub_img_res_2.setVisibility(View.VISIBLE);
                image_view_sub_img_res_3.setVisibility(View.VISIBLE);

                Glide.with(context).load(nhaHang.getListHinh().get(0)).into(image_view_sub_img_res_1);
                Glide.with(context).load(nhaHang.getListHinh().get(1)).into(image_view_sub_img_res_2);
                Glide.with(context).load(nhaHang.getListHinh().get(2)).into(image_view_sub_img_res_3);

                return;

            }
        }*/
        //hiển thị bình luận của nhà cho hiển thị tối đa 2 bình luận
        /*public void showBinhLuan(NhaHang nhaHang) {
            if (nhaHang.getListBinhLuan() == null||nhaHang.getListBinhLuan().size() == 0)
            {
                linear_layout_parent_comment_res.setVisibility(View.GONE);
            } else {
                linear_layout_parent_comment_res.setVisibility(View.VISIBLE);

                if (nhaHang.getListBinhLuan().size() == 1) {
                    linear_layout_sub_comment_res_1.setVisibility(View.VISIBLE);
                    linear_layout_sub_comment_res_2.setVisibility(View.GONE);

                    BinhLuan binhLuan1 = nhaHang.getListBinhLuan().get(0);

                    showBinhLuan1(binhLuan1);


                } else {
                    linear_layout_sub_comment_res_1.setVisibility(View.VISIBLE);
                    linear_layout_sub_comment_res_2.setVisibility(View.VISIBLE);

                    BinhLuan binhLuan1 = nhaHang.getListBinhLuan().get(0);
                    BinhLuan binhLuan2 = nhaHang.getListBinhLuan().get(1);

                    showBinhLuan1(binhLuan1);
                    showBinhLuan2(binhLuan2);

                }
            }

        }*/
        //bình luận 1
       /* public void showBinhLuan1(BinhLuan binhLuan) {
            if(binhLuan.getObjectInfoUser().getHinh()!=null)
                Glide.with(context).load(binhLuan.getObjectInfoUser().getHinh()).into(image_view_avatar_comment_1);

            text_view_name_user_1.setText(binhLuan.getObjectInfoUser().getHoTen());
            text_view_user_rate_1.setText(binhLuan.getDanhGia() + "");
            text_view_comment_1.setText(binhLuan.getNoiDung());
        }
        //bình luận 2
        public void showBinhLuan2(BinhLuan binhLuan) {
            if(binhLuan.getObjectInfoUser().getHinh()!=null)
                Glide.with(context).load(binhLuan.getObjectInfoUser().getHinh()).into(image_view_avatar_comment_2);

            text_view_name_user_2.setText(binhLuan.getObjectInfoUser().getHoTen());
            text_view_user_rate_2.setText(binhLuan.getDanhGia() + "");
            text_view_comment_2.setText(binhLuan.getNoiDung());
        }
        //set trạng thái của nhà hàng
        public void updateStatusNhaHang(NhaHang nhaHang) {
            String numOfReview = nhaHang.getListBinhLuan().size() + "";
            String numOfPhoto = nhaHang.getListHinh().size()+"";

            if(numOfPhoto.equals("0") && numOfReview.equals("0")){
                linear_layout_num_of_photo.setVisibility(View.GONE);
                text_view_num_of_review.setText("Bạn hãy là người đầu tiên đánh giá");
            }else{
                linear_layout_num_of_photo.setVisibility(View.VISIBLE);
                text_view_num_of_review.setText(numOfReview);
                text_view_num_of_photo.setText(numOfPhoto);
            }


        }*/
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        restaurant restaurant=listRestaurant.get(position);
        if(convertView==null)
        {
            convertView=inflater.inflate(R.layout.tab_view_foody, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }
        else
        {
            holder=(Holder) convertView.getTag();
        }
        holder=(Holder) convertView.getTag();
        holder.showHolder(restaurant);
        return convertView;
    }
}
