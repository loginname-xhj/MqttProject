package com.itpersion.myimclient.service;


import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.packet.VCard;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.app.ConfigApplication;
import com.itpersion.myimclient.asmack.SmackImpl;
import com.itpersion.myimclient.asmack.XmppConnection;
import com.itpersion.myimclient.broadcast.NetWorkChangeBroadReceiver;
import com.itpersion.myimclient.config.Config;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;
import com.itpersion.myimclient.utils.NetUtil;

public class ClientConnService extends Service implements NetWorkStatusCallback {
	public static final int CONNECTED = 0;
	public static final int DISCONNECTED = -1;
	public static final String PONG_TIMEOUT = "pong timeout";
	private Thread mConnectingThread;
	private SmackImpl mSmackable;
	private IConnectionStatusCallback myconnCallback;
	private Handler mMainHandler = new Handler();
	// �������
	public static final String NETWORK_ERROR = "network error";
	// ��¼ʧ��
	public static final String LOGIN_FAILED = "login failed";
	// �Ƿ��Ѿ�����
	private int mConnectedState = DISCONNECTED;
	private NetWorkChangeBroadReceiver networkchange;
	private ConfigApplication config;

	@Override
	public IBinder onBind(Intent arg0) {
		return new MyBinder();
	}

	public class MyBinder extends Binder {
		public ClientConnService getService() {
			return ClientConnService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		// �����γ�ʼ������ֶ�θ�ֵ��
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// �󶨽�����,start���������,
		// Service�󶨿����Ͷ����㲥��
		config = (ConfigApplication) getApplicationContext();
		if (config.isInitService) {
			// Service��ʼ��
			System.out.println("Service��Conmmand��ʼ����");
			config.list.add(ClientConnService.this);
			networkchange = new NetWorkChangeBroadReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
			registerReceiver(networkchange, filter);
			config.isInitService = false;// ��ֹ���μ��ء�
		} else {
			// Service��ʼ������ˡ�
			System.out.println("Service��Conmmand��ʼ���������");
		}

		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * ע������,��½,��Ա�仯�ص�����
	 * 
	 * @param cb
	 */
	public void registerConnectionStatusCallback(IConnectionStatusCallback cb) {
		myconnCallback = cb;
	}

	public void unRegisterConnectionStatusCallback() {
		myconnCallback = null;
	}

	/**
	 * ��½�������ķ���
	 * 
	 * @param loginname
	 *            ��½�û���
	 * @param passwrod
	 *            ��½�û�����
	 * @param status
	 *            ��½״̬,������û�������start,end,�������״̬�����û���½��ֹͣ��½��
	 */
	public void startlogin(final String loginname, final String passwrod,
			final String status) {
		if (NetUtil.getNetworkState(this) == NetUtil.NETWORN_NONE) {
			connectionFailed(NETWORK_ERROR);
			return;
		}
		mConnectingThread = new Thread() {
			@Override
			public void run() {
				super.run();
				if (status.equals(getResources().getString(
						R.string.client_login_flag))) {
					// ��ʾ�����ָ���ǵ�½��
					mSmackable = new SmackImpl(ClientConnService.this);
					if (mSmackable.login(loginname, passwrod,false)) {
						// ��½�ɹ�
						if(XmppConnection.getConnection()!=null){
							initDate();
						}
						
						postConnectionScuessed();
					} else {

						// ��½ʧ��
						postConnectionFailed(LOGIN_FAILED);
					}
				} else if (status.equals(getResources().getString(
						R.string.client_stoplogin_flag))) {
					// ֹͣ��½
					if (mSmackable != null) {
						mSmackable.stoplogin();

					}
				}

			}
		};
		mConnectingThread.start();
	}

	protected void postConnectionScuessed() {
		mMainHandler.post(new Runnable() {

			@Override
			public void run() {
				connectionScuessed();
			}
		});
	}

	private void connectionScuessed() {
		mConnectedState = CONNECTED;// �Ѿ�������

		if (myconnCallback != null)
			myconnCallback.connectionStatusChanged(mConnectedState, "");
	}

	/**
	 * UI�̷߳�������ʧ��
	 * 
	 * @param reason
	 */
	private void connectionFailed(String reason) {
		mConnectedState = DISCONNECTED;// ���µ�ǰ����״̬

		// �ص�
		if (myconnCallback != null) {
			myconnCallback.connectionStatusChanged(mConnectedState, reason);
			return;

		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		ConfigApplication config = (ConfigApplication) getApplication();
		if (config.isstart_service) {
			mMainHandler.removeCallbacks(connThread);
			config.isInitService = true;
			config.list.remove(ClientConnService.this);
			unregisterReceiver(networkchange);
			config.isstart_service = false;
			System.out.println("�û����ڴ����IMͨ���ĺ�̨����");
			mSmackable.logout();
		}
	}

	/**
	 * ��UI�߳�����ʧ�ܷ���
	 * 
	 * @param reason
	 */
	public void postConnectionFailed(final String reason) {
		mMainHandler.post(new Runnable() {
			@Override
			public void run() {
				connectionFailed(reason);
			}
		});
	}

	@Override
	public void NetWork_conn_Error() {
		// �������ӶϿ�
		System.out.println("����Ͽ�");
		
		
	}

	@Override
	public void NetWork_conn_Sucess() {
		// �������ӳɹ�
		mMainHandler.postDelayed(connThread, 2000);
		
	}
	Thread connThread=new Thread(){
		public void run() {
			
			XmppConnection.connection=null;
			mSmackable=new SmackImpl(ClientConnService.this);
			if(mSmackable.login(config.getUsername(), config.getPassword(), true)){
				mMainHandler.removeCallbacks(connThread);
				System.out.println("�����ɹ�");
			}else{
				System.out.println("����ʧ��");
				mMainHandler.postDelayed(connThread, 3000);
			}
			
		};
	};
	  /*
     * ��ȡvcard������Ϣ
     */
	private void initDate() {
				//Log.d(Config.TAG, XmppConnection.getConnection().getUser());
				//Log.d(Config.TAG, (String) XmppConnection.getConnection().getUser().subSequence(0,(XmppConnection.getConnection().getUser().lastIndexOf("/"))));
				VCard vcard=new VCard();
				try {
					vcard.load(XmppConnection.getConnection(), (String) XmppConnection.getConnection().getUser().subSequence(0,(XmppConnection.getConnection().getUser().lastIndexOf("/"))));
					if(vcard!=null){
					String str=vcard.getNickName();
					//Log.d(Config.TAG, vcard.toXML()+",str:"+str);
					}
				} catch (XMPPException e) {
					//Log.d(Config.TAG, "Vcard����ʧ��"+e.getMessage());
				}	
	
	
	}
}
