package com.itpersion.myimclient.activity;

import java.util.ArrayList;
import java.util.List;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.adapter.MessageAdapter;
import com.itpersion.myimclient.config.Config;
import com.itpersion.myimclient.domain.MyDefaultMessage;
import com.itpersion.myimclient.engine.ChartMessageProvider;
import com.itpersion.myimclient.utils.StringUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MessageActivity extends Activity {
	private RelativeLayout rl_message_top;
	private LinearLayout ll_message_toast, ll_message_search;
	private TextView title_name_tv;
	private NetWorkConnContentProvider nccp;
	private ContentResolver mContentResolver;
	private MContentObserver mContentObserver;
	private boolean resisterContentObserver_flag = false;// 是否注册了内容提供者
	private ListView iv_message_content;
	private List<MyDefaultMessage> list_date;
	private MessageAdapter mAdapter;
	private Message msg;
	private boolean list_flag = true;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// 处理界面的handler。
			Uri uri = (Uri) msg.obj;
			String temp = StringUtils.getFormatChat("/", uri.toString());
			MyDefaultMessage mess = ChartMessageProvider.getMessageByID(temp,
					getContentResolver());
			System.out.println(mess.toString());
			int temp_i = 0;
			for (int i = 0; i < list_date.size(); i++) {

				MyDefaultMessage temp_mess = list_date.get(i);
				if (temp_mess.getMessage_fromer().equals(
						mess.getMessage_fromer())) {
					temp_i = i;
					list_flag = false;
					break;
				}
			}
			if (list_flag) {
				System.out.println("新增的");
				list_date.add(mess);
				
			} else {
				System.out.println("更新的");
				list_date.set(temp_i, mess);
				list_flag=true;
			}
			mAdapter.notifyDataSetChanged();

		};
	};

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		initView();
		list_date = new ArrayList<MyDefaultMessage>();
		mAdapter = new MessageAdapter(MessageActivity.this, list_date);
		iv_message_content.setAdapter(mAdapter);
		// 动态注册网络变化广播
		nccp = new NetWorkConnContentProvider();
		IntentFilter filter = new IntentFilter(
				"android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(nccp, filter);
		mContentResolver = getContentResolver();
		mContentObserver = new MContentObserver();
		if (mContentResolver != null) {
			System.out.println("注册内容监听者");
			mContentResolver.registerContentObserver(
					Config.MyProvider.CHART_URI, true, mContentObserver);
			resisterContentObserver_flag = true;
		}
	}

	/**
	 * 初始化组件。
	 */
	private void initView() {
		rl_message_top = (RelativeLayout) findViewById(R.id.rl_message_top);
		title_name_tv = (TextView) rl_message_top
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("消息");
		ll_message_toast = (LinearLayout) findViewById(R.id.ll_message_toast);
		ll_message_search = (LinearLayout) findViewById(R.id.ll_message_search);
		// 最近消息搜索框点击事件
		ll_message_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(MessageActivity.this,
						MessageSearchActivity.class);
				startActivity(intent);
			}
		});
		iv_message_content = (ListView) findViewById(R.id.iv_message_content);
	}

	private class MContentObserver extends ContentObserver {

		public MContentObserver() {
			super(new Handler());
		}
          
		@Override
		public void onChange(boolean selfChange) {
			super.onChange(selfChange);
			System.out.println("有新的消息onChange");
		}

		@SuppressLint("NewApi")
		@Override
		public void onChange(boolean selfChange, Uri uri) {
			super.onChange(selfChange, uri);
			System.out.println("有新的消息");
			msg = new Message();
			msg.obj = uri;
			handler.sendMessage(msg);
            
		}
	}

	private class NetWorkConnContentProvider extends BroadcastReceiver {

		private ConnectivityManager connectivityManager;
		private NetworkInfo info;

		@Override
		public void onReceive(Context arg0, Intent arg1) {

			String action = arg1.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				System.out.println("网络状态正在改变");
				if (ll_message_toast.getVisibility() == View.VISIBLE) {

				} else {
					ll_message_toast.setVisibility(View.VISIBLE);
				}

				connectivityManager = (ConnectivityManager) arg0
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				info = connectivityManager.getActiveNetworkInfo();
				if (info != null && info.isAvailable()) {
					String netname = info.getTypeName();
					System.out.println("当前网络是:" + netname);
					ll_message_toast.setVisibility(View.GONE);
					if (resisterContentObserver_flag) {
						// 已经注册过了
					} else {
						// 没有注册监听器
						mContentResolver.registerContentObserver(
								Config.MyProvider.CHART_URI, true,
								mContentObserver);
						resisterContentObserver_flag = true;
					}

				} else {
					System.out.println("当前没有网络");
					mContentResolver
							.unregisterContentObserver(mContentObserver);
					resisterContentObserver_flag = false;

				}
			}
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("销毁MessageActivity");
		unregisterReceiver(nccp);
		if (resisterContentObserver_flag) {
			System.out.println("注销内容提供者");
			mContentResolver.unregisterContentObserver(mContentObserver);
			resisterContentObserver_flag = false;
		}
	}
}
