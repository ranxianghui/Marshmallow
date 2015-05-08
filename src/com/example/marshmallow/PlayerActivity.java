package com.example.marshmallow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PlayerActivity extends Activity{
	private WebView mWebview;
	private String url;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_palyer);
		Intent intent = getIntent();
		url = intent.getStringExtra("URL");
	}
	 @Override  
	    protected void onStart() {  
	        super.onStart();    
	        this.mWebview = (WebView) this.findViewById(R.id.wv_player);  
	        this.mWebview.loadUrl(url);  
	        this.mWebview.setWebViewClient(new WebViewClientDemo());  
	    }  
	  
	  
	    private class WebViewClientDemo extends WebViewClient {  
	        @Override  
	        // 在WebView中不在默认浏览器下显示页面  
	        public boolean shouldOverrideUrlLoading(WebView view, String url) {  
	            view.loadUrl(url);  
	            return true;  
	        }  
	    }  
}
