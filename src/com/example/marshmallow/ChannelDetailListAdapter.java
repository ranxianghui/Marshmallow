package com.example.marshmallow;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.property.ChannelDetialProperty;

public class ChannelDetailListAdapter extends BaseAdapter{
	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ArrayList<ChannelDetialProperty> mList;
	
	public ChannelDetailListAdapter( Context context ,List<ChannelDetialProperty>list){
		mContext = context;
		mLayoutInflater = LayoutInflater.from(mContext);
		mList = new ArrayList<ChannelDetialProperty>();
		if (list != null) {
			mList.addAll(list);
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if ( mList != null ) {
			return mList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		HolderView holder = new HolderView();
		convertView = mLayoutInflater.inflate(R.layout.list_view_item, null);
		holder.mTime = (TextView)convertView.findViewById(R.id.tv_time);
		holder.mTitle = (TextView)convertView.findViewById(R.id.tv_programname);
		holder.mInfor = (TextView)convertView.findViewById(R.id.tv_others);
		holder.mTitle.setText(mList.get(position).getTvProgram());
		holder.mTime.setText(mList.get(position).getTvTime());
		holder.mInfor.setText(mList.get(position).getTvType());
		convertView.setTag(holder);
		return convertView;
	}
	private class HolderView  {
		
	private TextView mTitle;
	private TextView mTime;
	private TextView mInfor;
	}

}
