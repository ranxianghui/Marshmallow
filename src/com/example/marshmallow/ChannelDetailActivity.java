package com.example.marshmallow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.View.CustomProgressDialog;
import com.example.property.ChannelDetailMananger;
import com.example.until.HttpUtil;
import com.example.until.JsonTools;

public class ChannelDetailActivity extends Activity {
	private String mId;
	private String mWeek;
	private ActionBar mActionBar;
	private static CustomProgressDialog mDialog = null;
	private ArrayList<ListView> mListViewlit;
	private ArrayList<View> mPagerViews;
	private MyViewPagerAdapter mPagerAdapter;
	private ChannelDetailMananger mChannelDetailMananger;
	private ViewPager mViewPager;
	private static String mUrl;
	private String[] mTitlelist;
	private List<Tab> mTablist = new ArrayList<ActionBar.Tab>();
	private static final String CHANNEL = "getChannelDetail";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channlelist);
		init();
		getData();
		initViewpager();
		geturl(mWeek);
	}

	private void initViewpager() {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) findViewById(R.id.vp_channelpager);
		mPagerViews = new ArrayList<View>();
		mListViewlit = new ArrayList<ListView>();
		LayoutInflater inflater = getLayoutInflater();
		for (int i = 0; i < 7; i++) {
			View view = inflater.inflate(R.layout.pager_item, null);
			ListView listView = (ListView) view.findViewById(R.id.detaillist);
			listView.setOnItemClickListener(new ListSelectListener());
			mListViewlit.add(listView);
			mPagerViews.add(view);
		}
		mPagerAdapter = new MyViewPagerAdapter(mPagerViews,
				ChannelDetailActivity.this, 0);
		mViewPager.setAdapter(mPagerAdapter);
		mViewPager.setOnPageChangeListener(new PagerChangeListener());
		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		mActionBar.setDisplayShowTitleEnabled(false);
		mActionBar.setDisplayShowHomeEnabled(false);
		for (int i = 0; i != mTitlelist.length; i++) {
			mTablist.add(mActionBar.newTab().setText(mTitlelist[i])
					.setTabListener(mTabListener));
			mActionBar.addTab(mTablist.get(i));
		}

	}

	private void init() {
		// TODO Auto-generated method stub
		if (mChannelDetailMananger != null) {
			mChannelDetailMananger.clear();
		} else {
			mChannelDetailMananger = ChannelDetailMananger.getObjManager();
		}
		mTitlelist = this.getResources().getStringArray(R.array.weeks);
	}

	private void getData() {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		mId = intent.getStringExtra("id");
		mWeek = "1";
	}

	private void geturl(String week) {
		// TODO Auto-generated method stub
		mUrl = HttpUtil.HTTPHEAD + HttpUtil.ACTION + CHANNEL + HttpUtil.AND
				+ HttpUtil.ID + mId + HttpUtil.AND + HttpUtil.WEEK + week;
		new Thread(networkTask).start();
		if (mDialog == null) {
			mDialog = CustomProgressDialog
					.createDialog(ChannelDetailActivity.this);
		}
		if (!mDialog.isShowing()) {
			mDialog.setMessage("Loading...");
			mDialog.show();
		}
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.obj.equals("refresh")) {
				mListViewlit.get(mViewPager.getCurrentItem()).setAdapter(
						new ChannelDetailListAdapter(
								ChannelDetailActivity.this,
								mChannelDetailMananger.getChannelList()));
				mPagerAdapter.notifyDataSetChanged();
				if ((mDialog != null)) {
					if (mDialog.isShowing()) {
						mDialog.dismiss();
						mDialog = null;
					}
				}
			}
		}
	};

	Runnable networkTask = new Runnable() {

		@Override
		public void run() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
			String data = HttpUtil.executeHttpGet(mUrl);
			list = JsonTools.getList(data);
			mChannelDetailMananger.clear();
			mChannelDetailMananger.setChannelList(list);
			Message msg = new Message();
			msg.obj = "refresh";
			handler.sendMessage(msg);
		}
	};

	class PagerChangeListener implements OnPageChangeListener {

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			mActionBar.setSelectedNavigationItem(arg0);
			geturl(String.valueOf(arg0));
		}
	}

	private TabListener mTabListener = new TabListener() {

		@Override
		public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
			for (int i = 0; i < mTablist.size(); i++) {
				if (tab == mTablist.get(i)) {
					mViewPager.setCurrentItem(i);
					geturl(String.valueOf(i));
				}
			}
		}

		@Override
		public void onTabUnselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabReselected(Tab tab, android.app.FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}
	};

	private class ListSelectListener implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(ChannelDetailActivity.this,
					PlayerActivity.class);
			intent.putExtra("URL", "http://tiantian.tv/channel/cctv5plus.html");
			startActivity(intent);
		}

	}

}
