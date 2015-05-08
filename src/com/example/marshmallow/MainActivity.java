package com.example.marshmallow;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	
	private ArrayList<String> list;
	private ListView mlistView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		getDate();
		initView();
		setEvent();
	}

	private void getDate() {
		// TODO Auto-generated method stub
		list = new ArrayList<String>();
		list.add(this.getResources().getString(R.string.cc_tv));
		list.add(this.getResources().getString(R.string.star_tv));
		list.add(this.getResources().getString(R.string.oversea_tv));
		list.add(this.getResources().getString(R.string.regional_tv));
	}

	private void initView() {
		// TODO Auto-generated method stub
		mlistView = (ListView)findViewById(R.id.listView1);
		mlistView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, list));
	}

	private void setEvent() {
		// TODO Auto-generated method stub
		mlistView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("type", position+1);
				intent.setClass(MainActivity.this, ChannelListActivity.class);
				startActivity(intent);
			}
		});
	}
}
