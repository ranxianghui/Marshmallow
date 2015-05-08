package com.example.learn.commonadapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdaper<T> extends BaseAdapter {
	protected Context mContext;
	protected List<T> mDatas;
	protected LayoutInflater mLInflater;
	protected int mLayoutId;
	public CommonAdaper(Context context, List<T> datas, int LayoutId){
		this.mContext = context;
		mDatas = new ArrayList<T>();
		this.mLayoutId = LayoutId;
		mDatas.addAll(datas);
		mLInflater = LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDatas.size();
	}

	@Override
	public T getItem(int position) {
		// TODO Auto-generated method stub
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent,
				mLayoutId, position);
		convert(viewHolder, getItem(position));
		return viewHolder.getmConvertView();
	}
	public abstract void convert(ViewHolder holder, T t);
}
