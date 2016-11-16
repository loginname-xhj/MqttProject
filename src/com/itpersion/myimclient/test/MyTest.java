package com.itpersion.myimclient.test;

import android.os.AsyncTask;

public class MyTest extends AsyncTask<Void, Void, Void> {
    /**
     * 耗时操作
     */
	@Override
	protected Void doInBackground(Void... params) {
		publishProgress();
		return null;
	}
    /**
     * 耗时操作前执行
     */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
     
	@Override
	protected void onProgressUpdate(Void... values) {
		//更新进度。
		super.onProgressUpdate(values);
	}
	/**
	 * 耗时操作完了之后的调用。
	 */
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}
    
}
