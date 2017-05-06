package com.example.khanh.foody4.fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.khanh.foody4.R;

/**
 * Created by Khanh on 4/12/2017.
 */

public class Fragment_Collage extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fram=  inflater.inflate(R.layout.fragment_collection,container,false);
        return fram;
    }
}
