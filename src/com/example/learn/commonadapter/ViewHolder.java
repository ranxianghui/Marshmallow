package com.example.learn.commonadapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewHolder {
	private SparseArray<View> mViews;
	private int mPositon;
	private View mConvertView;

	public ViewHolder(Context context, ViewGroup parent, int layoutId,
			int position) {
		this.mPositon = position;
		mViews = new SparseArray<View>();
		mConvertView = LayoutInflater.from(context).inflate(layoutId, parent);
		mConvertView.setTag(this);

	}

	public View getmConvertView() {
		return mConvertView;
	}

	/**
	 * 通过viewID获取控件
	 * 
	 * @param viewId
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId) {
		View view = mViews.get(viewId);
		if (view == null) {
			view = mConvertView.findViewById(viewId);
			mViews.put(viewId, view);
		}
		return (T) view;

	}

	public static ViewHolder get(Context context, View convertView,
			ViewGroup parent, int layoutId, int position) {
		if (convertView == null) {
			return new ViewHolder(context, parent, layoutId, position);
		} else {
			ViewHolder holder = (ViewHolder) convertView.getTag();
			holder.mPositon = position;
			return holder;
		}
	}
	/**
	 * 给textview设定值
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setText(int viewId, String text){
		
		TextView tv = getView(viewId);
		tv.setText(text);
		return this;
	}
	
	/**
	 * 给ImagetView设定背景图片
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setImageResource(int viewId, int resid){
		
		ImageView imview = getView(viewId);
		imview.setImageResource(resid);
		return this;
	}
	
	/**
	 * 给ImagetView设定背景图片
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setImageBitmap(int viewId, Bitmap bitmap){
		
		ImageView imview = getView(viewId);
		imview.setImageBitmap(bitmap);
		return this;
	}
	/**
	 * 给ImagetView设定背景图片
	 * @param viewId
	 * @param text
	 * @return
	 */
	public ViewHolder setImageURI(int viewId, String url){
		
		ImageView imview = getView(viewId);
//		通过网络图片获取方法获取的urlImage
//		imview.setImageURI(urlImage);
		return this;
	}
}
