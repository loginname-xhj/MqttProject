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
	// 网络错误
	public static final String NETWORK_ERROR = "network error";
	// 登录失败
	public static final String LOGIN_FAILED = "login failed";
	// 是否已经连接
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
		// 这里多次初始化会出现多次赋值。
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// 绑定结束后,start开启服务后,
		// Service绑定开机和断网广播。
		config = (ConfigApplication) getApplicationContext();
		if (config.isInitService) {
			// Service初始化
			System.out.println("Service的Conmmand初始化绑定");
			config.list.add(ClientConnService.this);
			networkchange = new NetWorkChangeBroadReceiver();
			IntentFilter filter = new IntentFilter();
			filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
			registerReceiver(networkchange, filter);
			config.isInitService = false;// 防止二次加载。
		} else {
			// Service初始化完毕了。
			System.out.println("Service的Conmmand初始化绑定完毕了");
		}

		return super.onStartCommand(intent, flags, startId);
	}

	/**
	 * 注册聊天,登陆,人员变化回调函数
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
	 * 登陆服务器的方法
	 * 
	 * @param loginname
	 *            登陆用户名
	 * @param passwrod
	 *            登陆用户密码
	 * @param status
	 *            登陆状态,这个有用户决定，start,end,这个两个状态代表用户登陆和停止登陆。
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
					// 表示传入的指令是登陆。
					mSmackable = new SmackImpl(ClientConnService.this);
					if (mSmackable.login(loginname, passwrod,false)) {
						// 登陆成功
						if(XmppConnection.getConnection()!=null){
							initDate();
						}
						
						postConnectionScuessed();
					} else {

						// 登陆失败
						postConnectionFailed(LOGIN_FAILED);
					}
				} else if (status.equals(getResources().getString(
						R.string.client_stoplogin_flag))) {
					// 停止登陆
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
		mConnectedState = CONNECTED;// 已经连接上

		if (myconnCallback != null)
			myconnCallback.connectionStatusChanged(mConnectedState, "");
	}

	/**
	 * UI线程反馈连接失败
	 * 
	 * @param reason
	 */
	private void connectionFailed(String reason) {
		mConnectedState = DISCONNECTED;// 更新当前连接状态

		// 回调
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
			System.out.println("用户从内存清除IM通话的后台服务");
			mSmackable.logout();
		}
	}

	/**
	 * 非UI线程连接失败反馈
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
		// 网络连接断开
		System.out.println("网络断开");
		
		
	}

	@Override
	public void NetWork_conn_Sucess() {
		// 网络连接成功
		mMainHandler.postDelayed(connThread, 2000);
		
	}
	Thread connThread=new Thread(){
		public void run() {
			
			XmppConnection.connection=null;
			mSmackable=new SmackImpl(ClientConnService.this);
			if(mSmackable.login(config.getUsername(), config.getPassword(), true)){
				mMainHandler.removeCallbacks(connThread);
				System.out.println("重连成功");
			}else{
				System.out.println("重连失败");
				mMainHandler.postDelayed(connThread, 3000);
			}
			
		};
	};
	  /*
     * 获取vcard卡的信息
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
					//Log.d(Config.TAG, "Vcard加载失败"+e.getMessage());
				}	
	
	
	}
}
