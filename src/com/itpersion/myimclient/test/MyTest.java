package com.itpersion.myimclient.test;

import android.os.AsyncTask;

public class MyTest extends AsyncTask<Void, Void, Void> {
    /**
     * ��ʱ����
     */
	@Override
	protected Void doInBackground(Void... params) {
		publishProgress();
		return null;
	}
    /**
     * ��ʱ����ǰִ��
     */
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}
     
	@Override
	protected void onProgressUpdate(Void... values) {
		//���½��ȡ�
		super.onProgressUpdate(values);
	}
	/**
	 * ��ʱ��������֮��ĵ��á�
	 */
	@Override
	protected void onPostExecute(Void result) {
		super.onPostExecute(result);
	}
    
}
