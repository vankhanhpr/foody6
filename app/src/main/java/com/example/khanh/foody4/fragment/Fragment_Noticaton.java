package com.example.khanh.foody4.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.khanh.foody4.R;


/**
 * Created by Khanh on 4/12/2017.
 */

public class Fragment_Noticaton extends Fragment
{
    ImageView imv_error;
    TextView tv_exmple;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_notication,container,false);;
        imv_error=(ImageView) view.findViewById(R.id.imv_error);
        tv_exmple=(TextView) view.findViewById(R.id.tv_exmple);

        /*AsyncLoadImage asyncLoadImage = new AsyncLoadImage();
        String x= "";
        try
        {
            String s= "banam";
            x=asyncLoadImage.execute(s).get();
            //x=x.toString();
            byte[]image;
            //Toast.makeText(getActivity().getApplicationContext(), "csdf"+x, Toast.LENGTH_SHORT).show();\
             image= Base64.decode(x);
            //image= android.util.Base64.decode(x, android.util.Base64.NO_WRAP);
            *//*if(image==null){
                Toast.makeText(getContext(),"rổng",Toast.LENGTH_LONG).show();

            }
            else
            {

                tv_exmple.setText(image.toString());
                Toast.makeText(getContext(),image.toString(),Toast.LENGTH_LONG).show();
            }*//*
           Bitmap bitmap = BitmapFactory.decodeByteArray(image,0,image.length);
            imv_error.setImageBitmap(bitmap);
          //  Glide.with(getActivity().getApplicationContext()).load(image).into(imv_error);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

*/
//        AsyncLoadImage asyncLoadImage =new AsyncLoadImage();
//        try {
//
//            String x= asyncLoadImage.execute("banam").get();
//            Log.d("khanhâsfsdcsa",x);
//            Toast.makeText(getContext(), "dssd"+x, Toast.LENGTH_SHORT).show();
////            byte[]image;
////            image= Base64.decode(x);
////            Glide.with(getActivity().getApplicationContext()).load(image).into(imv_error);
//
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
        return view;



    }
}
