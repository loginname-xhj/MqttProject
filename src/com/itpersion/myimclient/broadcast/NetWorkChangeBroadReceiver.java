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
 * @Description: ����仯�㲥������
 * @author �����˱�д
 * @date 2015��10��27�� ����3:17:53
 */
public class NetWorkChangeBroadReceiver extends BroadcastReceiver {
	private ConnectivityManager connectivityManager;
	private NetworkInfo info;
	private boolean is_network_error = true;
	private boolean is_send_network_error = false;// �������,����һ�ξ͹���,�����η��͡�
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
				// ��ǰ��������
				String netname = info.getTypeName();
				/**
				 * �״ε�½����ʾ�����硣 �˴�����������ж�,��Service�ں�̨����,ͻȻ����֮��������,
				 * ��һ����Service��������,���ε��á� ����ֻ�����һ�������
				 * ��ϵͳ��֪�Լ���ͨ���������ֻ��ն˶����ж������������������, ֮�����½����ն�����,���ҽ����������ӻ���,
				 * ֪ͨ����ϵͳ�Լ�������������ӡ�
				 */
				if (is_network_error) {
					// ��������

				} else {
					// ����ͻȻ�Ͽ���,�����������ˡ�
					// ֪ͨȫ��,����Ͽ�
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
				 * ��ǰ����Ͽ� ����Ͽ���,֪ͨ����ϵͳ��ǰ��������,�������еĳ�ʼ�����ӶϿ�,
				 */
				if (is_send_network_error) {
					// �Ѿ����͡�

				} else {
					// û�з���
					is_send_network_success=false;
					System.out.println("�������");
					is_network_error = false;
					// ֪ͨȫ��,����Ͽ�
					ConfigApplication config = (ConfigApplication) context
							.getApplicationContext();
					ArrayList<NetWorkStatusCallback> temp_list = config.list;
					for (int i = 0; i < temp_list.size(); i++) {
						temp_list.get(i).NetWork_conn_Error();
					}
					// ��ʼ�����ӵĲ�������
					is_send_network_error = true;

				}

			}
		}
	}

}
