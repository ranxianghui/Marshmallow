package com.example.marshmallow;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ListView;

public class MyViewPagerAdapter extends PagerAdapter{
	private List<View> mListViews;
	private Context mContext;
	private View mPInflater;
	public MyViewPagerAdapter(List<View> mListViews, Context context, int i) {
		this.mListViews = mListViews;//构造方法，参数是我们的页卡，这样比较方便。
		mContext = context;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) 	{	
		container.removeView(mListViews.get(position));//删除页卡
	}


	@Override
	public Object instantiateItem(ViewGroup container, int position) {	//这个方法用来实例化页卡	
		 container.addView(mListViews.get(position), 0);//添加页卡
		 return mListViews.get(position);
	}

	@Override
	public int getCount() {			
		return  mListViews.size();//返回页卡的数量
	}
	
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {			
		return arg0==arg1;//官方提示这样写
	}
	   private class ListSelectListener implements OnItemSelectedListener{

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
					Intent intent = new Intent(mContext, PlayerActivity.class);
					intent.putExtra("URL", "http://tiantian.tv/channel/cctv5plus.html");
					mContext.startActivity(intent);
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
	    	 
	     }
}