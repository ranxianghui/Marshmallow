package com.example.asynctask;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask<T> extends AsyncTask<T, T, T>{
	private final String TAG = "MyAsyncTask";
	@Override
	protected T doInBackground(T... params) {
		// TODO Auto-generated method stub
		for (int i = 0; i < 100; i++) {
			//这里因为AsyncTask<T, T, T>第二个参数的类型是t所以更新进度的时候publishProgress也需要传递一个t类型
			publishProgress(null);
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.i(TAG, "doInBackground");
		return null;
	}

	@Override
	protected void onPreExecute() {
		// 当AsyncTask执行开始的时候被调用
		Log.i(TAG, "onPreExecute");
		
		super.onPreExecute();
	}

	@Override
	protected void onPostExecute(T result) {
		// 当AsyncTask执行结束的时候被调用
		Log.i(TAG, "onPostExecute");
		super.onPostExecute(result);
	}

	@Override
	protected void onProgressUpdate(T... values) {
		// 当AsyncTask 执行过程中嗲用publishProgress后更新progressBar
		Log.i(TAG, "onProgressUpdate");
		super.onProgressUpdate(values);
	}

}
