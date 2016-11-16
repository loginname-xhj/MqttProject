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
	private boolean resisterContentObserver_flag = false;// �Ƿ�ע���������ṩ��
	private ListView iv_message_content;
	private List<MyDefaultMessage> list_date;
	private MessageAdapter mAdapter;
	private Message msg;
	private boolean list_flag = true;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// ��������handler��
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
				System.out.println("������");
				list_date.add(mess);
				
			} else {
				System.out.println("���µ�");
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
		// ��̬ע������仯�㲥
		nccp = new NetWorkConnContentProvider();
		IntentFilter filter = new IntentFilter(
				"android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(nccp, filter);
		mContentResolver = getContentResolver();
		mContentObserver = new MContentObserver();
		if (mContentResolver != null) {
			System.out.println("ע�����ݼ�����");
			mContentResolver.registerContentObserver(
					Config.MyProvider.CHART_URI, true, mContentObserver);
			resisterContentObserver_flag = true;
		}
	}

	/**
	 * ��ʼ�������
	 */
	private void initView() {
		rl_message_top = (RelativeLayout) findViewById(R.id.rl_message_top);
		title_name_tv = (TextView) rl_message_top
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("��Ϣ");
		ll_message_toast = (LinearLayout) findViewById(R.id.ll_message_toast);
		ll_message_search = (LinearLayout) findViewById(R.id.ll_message_search);
		// �����Ϣ���������¼�
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
			System.out.println("���µ���ϢonChange");
		}

		@SuppressLint("NewApi")
		@Override
		public void onChange(boolean selfChange, Uri uri) {
			super.onChange(selfChange, uri);
			System.out.println("���µ���Ϣ");
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
				System.out.println("����״̬���ڸı�");
				if (ll_message_toast.getVisibility() == View.VISIBLE) {

				} else {
					ll_message_toast.setVisibility(View.VISIBLE);
				}

				connectivityManager = (ConnectivityManager) arg0
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				info = connectivityManager.getActiveNetworkInfo();
				if (info != null && info.isAvailable()) {
					String netname = info.getTypeName();
					System.out.println("��ǰ������:" + netname);
					ll_message_toast.setVisibility(View.GONE);
					if (resisterContentObserver_flag) {
						// �Ѿ�ע�����
					} else {
						// û��ע�������
						mContentResolver.registerContentObserver(
								Config.MyProvider.CHART_URI, true,
								mContentObserver);
						resisterContentObserver_flag = true;
					}

				} else {
					System.out.println("��ǰû������");
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
		System.out.println("����MessageActivity");
		unregisterReceiver(nccp);
		if (resisterContentObserver_flag) {
			System.out.println("ע�������ṩ��");
			mContentResolver.unregisterContentObserver(mContentObserver);
			resisterContentObserver_flag = false;
		}
	}
}
