package com.example.khanh.foody4.customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.khanh.foody4.R;
import com.example.khanh.foody4.get_set.district;
import com.example.khanh.foody4.get_set.street;
import com.example.khanh.foody4.myinterface.IChooseStreet;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Khanh on 5/2/2017.
 */

public class ExpandableListAdapterODau extends BaseExpandableListAdapter
{
    private Context _context;
    private List<district> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<district, List<street>> _listDataChild;
    LayoutInflater infalInflater;
    IChooseStreet iChooseStreet;
    public void setChooseStreet(IChooseStreet iChooseStreet)
    {
        this.iChooseStreet = iChooseStreet;
    }

    public ExpandableListAdapterODau(Context context, List<district> listDataHeader,
                                     HashMap<district, List<street>> listChildData) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        infalInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public street getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }
    public class ChildHolder
{
    TextView textItem;
    public ChildHolder(View view){
        this.textItem=(TextView) view.findViewById(R.id.tv_student_name);
    }
}
    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final street childText = getChild(groupPosition, childPosition);
        ChildHolder childHolder;
        if (convertView == null) {
            convertView = infalInflater.inflate(R.layout.tab_expandablelistview_odau, null);
            childHolder=new ChildHolder(convertView);
            convertView.setTag(childHolder);
        }
        childHolder=(ChildHolder) convertView.getTag();

        childHolder.textItem.setText(childText.getStreet_Name());
        childHolder.textItem.setTextColor(_context.getResources().getColor(R.color.black));

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public district getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }
    public  class  Holder
    {
        TextView tv_view_district,tv_view_street;
        ImageView imv_tv_check;
        LinearLayout tab_select_street;
        public Holder(View view)
        {
            tv_view_district=(TextView)view.findViewById(R.id.tv_view_district);
            tv_view_street=(TextView)view.findViewById(R.id.tv_view_street);
            imv_tv_check=(ImageView)view.findViewById(R.id.imv_lv_select_city);
            tab_select_street=(LinearLayout)view.findViewById(R.id.tab_select_street);
        }
    }
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        district headerTitle = getGroup(groupPosition);

        Holder holder;
        if(convertView==null)
        {
            convertView=infalInflater.inflate(R.layout.tab_texview_lv_district, null);
            holder=new Holder(convertView);
            convertView.setTag(holder);
        }
        holder=(Holder) convertView.getTag();

        holder.tv_view_district.setText(headerTitle.getDistrict_Name());
        holder.tab_select_street.setOnClickListener(new ExpandStreet(groupPosition));
        holder.tv_view_street.setText(_listDataHeader.get(groupPosition).getTotal_Street()+" đường");

        return convertView;
    }
    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class ExpandStreet implements  View.OnClickListener{
        int groupPosion;
        public ExpandStreet(int groupPosition){
            this.groupPosion=groupPosition;
        }

        @Override
        public void onClick(View v) {
            ExpandableListAdapterODau.this.iChooseStreet.onExpand(this.groupPosion);
        }
    }
}
