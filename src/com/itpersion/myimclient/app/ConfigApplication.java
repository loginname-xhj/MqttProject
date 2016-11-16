package com.itpersion.myimclient.app;

import java.util.ArrayList;

import com.itpersion.myimclient.asmack.SmackImpl;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.app.Application;
import android.content.Intent;

public class ConfigApplication extends Application {
	public static String username;// ��¼��
	public static String password;// ��½����
    public static boolean isstart_service; //�жϺ�̨Service�Ƿ�����
    public static Intent intent_service;//����ȫ��ȥֹͣService��
    //public static boolean is_network_error=true;//�ж��Ƿ������Ƿ��ж�,Ĭ�������Ǵ򿪵�
	public static ArrayList<NetWorkStatusCallback> list=new ArrayList<NetWorkStatusCallback>();//��oncreate�����α�����
	public static boolean isInitService;//�Ƿ�����startService��Service
	public static SmackImpl smackImpl;
	public void onCreate() {
		super.onCreate();
		
	}

	public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		ConfigApplication.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		ConfigApplication.password = password;
	}
      
}
