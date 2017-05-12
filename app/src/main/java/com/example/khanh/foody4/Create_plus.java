package com.example.khanh.foody4;

import android.app.Dialog;
import android.content.Intent;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;
import android.widget.LinearLayout;

import com.example.khanh.foody4.insert_restaurant.Insert_Restaurant;

/**
 * Created by Khanh on 4/3/2017.
 */

public class Create_plus extends BottomSheetDialogFragment implements View.OnClickListener {
    LinearLayout tab_insert_restaurant;
    //hàm này gọi activity tương ứng khi nhấn vào dấu cộng góc phải

    public void setupDialog(final Dialog dialog, int style)
    {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.layout_plus, null);
        dialog.setContentView(contentView);

        tab_insert_restaurant=(LinearLayout)contentView.findViewById(R.id.tab_insert_restaurant);


        tab_insert_restaurant.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.tab_insert_restaurant:
                Intent intent = new Intent(getActivity(),Insert_Restaurant.class);
                startActivityForResult(intent,555);
                break;
        }
    }

}
