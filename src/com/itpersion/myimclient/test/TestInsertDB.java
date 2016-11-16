package com.itpersion.myimclient.test;

import java.util.ArrayList;
import java.util.List;

import com.itpersion.myimclient.config.Config;
import com.itpersion.myimclient.config.RosterColumnName;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.test.AndroidTestCase;
import android.util.Log;

public class TestInsertDB extends AndroidTestCase {
	public ContentResolver resolver;
	private Uri uri = Config.MyProvider.ROSTER_URI;
	private boolean one = false;
	private boolean two = false;
	private boolean three = false;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);

			if (msg.arg1 == 1) {
				one = true;
			} else if (msg.arg1 == 2) {
				two = true;
			} else if (msg.arg1 == 3) {
				three = true;
			}
			if (one && two && three) {
				Log.d("config", "插入完毕");
			}
		}

	};

	// 检查批量分段插入数据库
	public void test() {
		resolver = getContext().getContentResolver();
		ArrayList<String> listone = new ArrayList<String>();
		ArrayList<String> listtwo = new ArrayList<String>();
		ArrayList<String> listthree = new ArrayList<String>();
		for (int i = 0; i < 900; i++) {
			listone.add(i + "测试");
		}
		for (int j = 900; j < 1500; j++) {
			listtwo.add(j + "测试");
		}
		for (int j = 0; j < 2000; j++) {
			listthree.add(j + "测试");
		}
		new MyThreadInsertOne(listone).start();
		//new MyThreadInsertTwo(listone).start();
		//new MyThreadInsertThree(listone).start();
	}

	private class MyThreadInsertOne extends Thread {
		private List<String> onelist;

		public MyThreadInsertOne(List<String> onelist) {
			super();
			this.onelist = onelist;
		}

		@Override
		public void run() {
			super.run();
			Log.d("config", onelist.size()+"插入线程1的集合大小");
			if (onelist != null && onelist.size() > 0) {
				for (int i = 0; i < onelist.size(); i++) {
					Log.d("config", "开始插入:"+i);
					ContentValues values = new ContentValues();
					values.put(RosterColumnName.JID, onelist.get(i));
					Uri one_uri = resolver.insert(uri, values);
					Log.d("config", "线程1开始插入:" + one_uri);
				}
				// 通知插入完毕
				Message msg = Message.obtain();
				msg.arg1 = 1;
				handler.sendMessage(msg);
			}
		}
	}

	private class MyThreadInsertTwo extends Thread {
		private List<String> twolist;

		public MyThreadInsertTwo(List<String> onelist) {
			super();
			this.twolist = onelist;

		}

		@Override
		public void run() {
			super.run();
			if (twolist != null && twolist.size() > 0) {
				for (int i = 0; i < twolist.size(); i++) {
					ContentValues values = new ContentValues();
					values.put(RosterColumnName.JID, twolist.get(i));
					Uri two_url = resolver.insert(uri, values);
					Log.d("config", "线程2开始插入:" + two_url);
				}
				// 通知插入完毕
				Message msg = Message.obtain();
				msg.arg1 = 2;
				handler.sendMessage(msg);
			}
		}
	}

	private class MyThreadInsertThree extends Thread {
		private List<String> threelist;

		public MyThreadInsertThree(List<String> onelist) {
			super();
			this.threelist = onelist;
		}

		@Override
		public void run() {
			super.run();
			if (threelist != null && threelist.size() > 0) {
				for (int i = 0; i < threelist.size(); i++) {
					ContentValues values = new ContentValues();
					values.put(RosterColumnName.JID, threelist.get(i));
					Uri uri_three = resolver.insert(uri, values);
					Log.d("config", "线程3开始插入:" + uri_three);
				}
				// 通知插入完毕
				Message msg = Message.obtain();
				msg.arg1 = 3;
				handler.sendMessage(msg);
			}
		}
	}
}
