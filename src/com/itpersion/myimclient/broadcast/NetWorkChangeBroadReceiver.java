package com.itpersion.myimclient.broadcast;

import java.util.ArrayList;

import com.itpersion.myimclient.app.ConfigApplication;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @ClassName: NetWorkChangeBroadReceiver
 * @Description: 网络变化广播接受者
 * @author 机器人编写
 * @date 2015年10月27日 下午3:17:53
 */
public class NetWorkChangeBroadReceiver extends BroadcastReceiver {
	private ConnectivityManager connectivityManager;
	private NetworkInfo info;
	private boolean is_network_error = true;
	private boolean is_send_network_error = false;// 网络错误,发送一次就够了,避免多次发送。
	private boolean is_send_network_success = false;

	@Override
	public void onReceive(Context context, Intent intent) {
		if (connectivityManager == null) {
			connectivityManager = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);

		}
		String action = intent.getAction();
		if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
			info = connectivityManager.getActiveNetworkInfo();
			if (info != null && info.isAvailable()) {
				// 当前存在网络
				String netname = info.getTypeName();
				/**
				 * 首次登陆会提示又网络。 此处分两种情况判断,是Service在后台运行,突然断网之后连接上,
				 * 另一种是Service初次启动,初次调用。 这里只处理第一种情况。
				 * 从系统得知自己是通过连网后被手机终端断网中断了与服务器心跳连接, 之后重新建立终端连接,并且建立心跳连接机制,
				 * 通知整个系统自己与服务建立了连接。
				 */
				if (is_network_error) {
					// 网络正常

				} else {
					// 网络突然断开了,重新连接上了。
					// 通知全局,网络断开
					if (is_send_network_success) {

					} else {
						ConfigApplication config = (ConfigApplication) context
								.getApplicationContext();
						ArrayList<NetWorkStatusCallback> temp_list = config.list;
						for (int i = 0; i < temp_list.size(); i++) {
							temp_list.get(i).NetWork_conn_Sucess();
						}
						is_send_network_success=true;
						is_send_network_error=false;
					}
					
				}
			} else {
				/**
				 * 当前网络断开 网络断开后,通知整个系统当前处于离线,并且所有的初始化连接断开,
				 */
				if (is_send_network_error) {
					// 已经发送。

				} else {
					// 没有发送
					is_send_network_success=false;
					System.out.println("网络错误！");
					is_network_error = false;
					// 通知全局,网络断开
					ConfigApplication config = (ConfigApplication) context
							.getApplicationContext();
					ArrayList<NetWorkStatusCallback> temp_list = config.list;
					for (int i = 0; i < temp_list.size(); i++) {
						temp_list.get(i).NetWork_conn_Error();
					}
					// 初始化连接的参数设置
					is_send_network_error = true;

				}

			}
		}
	}

}
