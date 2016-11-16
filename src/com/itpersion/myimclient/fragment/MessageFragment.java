package com.itpersion.myimclient.fragment;

import java.util.ArrayList;
import java.util.List;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.activity.MessageSearchActivity;
import com.itpersion.myimclient.adapter.MessageAdapter;
import com.itpersion.myimclient.app.ConfigApplication;
import com.itpersion.myimclient.config.Config;
import com.itpersion.myimclient.domain.MyDefaultMessage;
import com.itpersion.myimclient.engine.ChartMessageProvider;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;
import com.itpersion.myimclient.utils.StringUtils;

public class MessageFragment extends BaseFragment implements
		NetWorkStatusCallback {
	private View rootView;
	private LinearLayout ll_message_toast, ll_message_search;
	private ListView iv_message_content;
	private List<MyDefaultMessage> list_date;
	private MessageAdapter mAdapter;
	private NetWorkConnContentProvider nccp;
	private ContentResolver mContentResolver;
	private MContentObserver mContentObserver;
	private boolean resisterContentObserver_flag = false;// �Ƿ�ע���������ṩ��
	private Message msg;
	private boolean list_flag = true;
	private Activity MyMessage_Activity;
	private ConfigApplication config;
	private Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			// ��������handler��
			Uri uri = (Uri) msg.obj;
			String temp = StringUtils.getFormatChat("/", uri.toString());
			MyDefaultMessage mess = ChartMessageProvider.getMessageByID(temp,
					getActivity().getContentResolver());
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
				list_flag = true;
			}
			mAdapter.notifyDataSetChanged();

		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		if (null != rootView) {
			ViewGroup parent = (ViewGroup) rootView.getParent();
			if (null != parent) {
				parent.removeView(rootView);
			}
		} else {
			config = (ConfigApplication) getActivity().getApplication();
			config.list.add(MessageFragment.this);
			isinitView_testFragment1 = true;
			rootView = inflater.inflate(R.layout.activity_message, container,
					false);
			ll_message_toast = (LinearLayout) rootView
					.findViewById(R.id.ll_message_toast);
			ll_message_search = (LinearLayout) rootView
					.findViewById(R.id.ll_message_search);
			iv_message_content = (ListView) rootView
					.findViewById(R.id.iv_message_content);
		}
		return rootView;

	}

	@Override
	public void InItUI() {
		if (isinitView_testFragment1) {
			Log.d(Config.TAG, "��ʼ��");
			MyMessage_Activity = getActivity();
			isinitView_testFragment1 = false;
			// �����Ϣ���������¼�
			ll_message_search.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent();
					intent.setClass(getActivity(), MessageSearchActivity.class);
					startActivity(intent);
				}
			});
			list_date = new ArrayList<MyDefaultMessage>();
			mAdapter = new MessageAdapter(MyMessage_Activity, list_date);
			iv_message_content.setAdapter(mAdapter);
			// ��̬ע������仯�㲥
			nccp = new NetWorkConnContentProvider();
			IntentFilter filter = new IntentFilter(
					"android.net.conn.CONNECTIVITY_CHANGE");
			MyMessage_Activity.registerReceiver(nccp, filter);
			mContentResolver = MyMessage_Activity.getContentResolver();
			mContentObserver = new MContentObserver();
			if (mContentResolver != null) {
				// System.out.println("ע�����ݼ�����");
				mContentResolver.registerContentObserver(
						Config.MyProvider.CHART_URI, true, mContentObserver);
				resisterContentObserver_flag = true;
			}
		} else {
			Log.d(Config.TAG, "���ø���");
		}
	}

	private class NetWorkConnContentProvider extends BroadcastReceiver {

		private ConnectivityManager connectivityManager;
		private NetworkInfo info;

		@Override
		public void onReceive(Context arg0, Intent arg1) {

			String action = arg1.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
				// System.out.println("����״̬���ڸı�");
				if (ll_message_toast.getVisibility() == View.VISIBLE) {

				} else {
					ll_message_toast.setVisibility(View.VISIBLE);
				}

				connectivityManager = (ConnectivityManager) arg0
						.getSystemService(Context.CONNECTIVITY_SERVICE);
				info = connectivityManager.getActiveNetworkInfo();
				if (info != null && info.isAvailable()) {
					String netname = info.getTypeName();
					// System.out.println("��ǰ������:" + netname);
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
					// System.out.println("��ǰû������");
					mContentResolver
							.unregisterContentObserver(mContentObserver);
					resisterContentObserver_flag = false;

				}
			}
		}
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

	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("����MessageActivity");
		if (MyMessage_Activity != null) {
			MyMessage_Activity.unregisterReceiver(nccp);
			MyMessage_Activity = null;
		}
		if (resisterContentObserver_flag) {
			System.out.println("ע�������ṩ��");
			mContentResolver.unregisterContentObserver(mContentObserver);
			resisterContentObserver_flag = false;
		}
		boolean b = config.list.remove(MessageFragment.this);
		if (b) {
			System.out.println("��ϢFragment�Ƴ��ɹ�");
		} else {
			System.out.println("��ϢFragment�Ƴ�ʧ��");
		}
	}

	@Override
	public void NetWork_conn_Error() {
		System.out.println("MessageFragment.NetWork_conn_Error()");
	}

	@Override
	public void NetWork_conn_Sucess() {
		System.out.println("����������");
	}
}
