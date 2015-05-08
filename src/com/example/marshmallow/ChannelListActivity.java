package com.example.marshmallow;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.View.ArcMenu;
import com.example.View.CustomProgressDialog;
import com.example.View.ArcMenu.OnMenuItemClickListener;
import com.example.property.ChannelListManager;
import com.example.until.HttpUtil;
import com.example.until.JsonTools;

public class ChannelListActivity extends Activity {
	private static final String CHANNEL = "getChannel";
	private static ChannelListManager mManager;
	private static ArrayList<String> mChannelList = new ArrayList<String>();;
	private ListView mlistView;
	private static ArrayAdapter<String> adapter;
	private static CustomProgressDialog mDialog;
	private ArcMenu mArcMenu;
	private String url;
	
	private int type;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_channel_top_list);
		getDate();
		initView();
		setEvent();
	}

	private void getDate() {
		// TODO Auto-generated method stub
		mManager = ChannelListManager.getObjManager();
		mManager.clear();
		mChannelList.clear();
		mChannelList = mManager.getChannleNameList();
		Intent intent = getIntent();
		type = intent.getIntExtra("type",0);
		url = HttpUtil.HTTPHEAD+HttpUtil.ACTION+CHANNEL+HttpUtil.AND+HttpUtil.TPYE+type;
		new Thread(networkTask).start();
		if (mDialog == null) {
			mDialog = CustomProgressDialog.createDialog(ChannelListActivity.this);
		}
		if (!mDialog.isShowing()) {
			mDialog.setMessage("Loading...");
			mDialog.show();
		}
	}

	private void initView() {
		// TODO Auto-generated method stub
		mlistView = (ListView)findViewById(R.id.listView1);
		mArcMenu = (ArcMenu) findViewById(R.id.id_menu);
		
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, mChannelList);
		mlistView.setAdapter(adapter);
	}
	private void setEvent() {
		// TODO Auto-generated method stub
		mlistView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String index = mManager.getChannleIdList().get(position);
				Intent  intent =  new Intent(ChannelListActivity.this, ChannelDetailActivity.class);
				intent.putExtra("id", index);
				startActivity(intent);
			}
		});
		mlistView.setOnScrollListener(new OnScrollListener()
		{

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState)
			{

			}

			@Override
			public void onScroll(AbsListView view, int firstVisibleItem,
					int visibleItemCount, int totalItemCount)
			{
				if (mArcMenu.isOpen())
					mArcMenu.toggleMenu(600);
			}
		});
		
		mArcMenu.setOnMenuItemClickListener(new OnMenuItemClickListener()
		{
			@Override
			public void onClick(View view, int pos) {
				// TODO Auto-generated method stub
			}
		});
	}
	
	private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	if (msg.obj.equals("refresh")) {
        		adapter.notifyDataSetChanged();
        		mDialog.dismiss();
        		mDialog = null;
			}
        }
	};
	
	Runnable networkTask = new Runnable() {  
		  
	    @Override  
	    public void run() { 
			List<Map<String, Object>> list = new ArrayList<Map<String , Object >>();
			String data = HttpUtil.executeHttpGet(url);
			list = JsonTools.getList(data) ;
			mManager.setChannelList(list);
			mChannelList.addAll(mManager.getChannleNameList());
			Message msg = new Message();
            msg.obj = "refresh";
            handler.sendMessage(msg);
	    }  
	};
}
