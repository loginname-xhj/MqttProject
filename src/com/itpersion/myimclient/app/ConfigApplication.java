package com.itpersion.myimclient.app;

import java.util.ArrayList;

import com.itpersion.myimclient.asmack.SmackImpl;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.app.Application;
import android.content.Intent;

public class ConfigApplication extends Application {
	public static String username;// 登录名
	public static String password;// 登陆密码
    public static boolean isstart_service; //判断后台Service是否启动
    public static Intent intent_service;//用于全局去停止Service。
    //public static boolean is_network_error=true;//判断是否网络是否中断,默认网络是打开的
	public static ArrayList<NetWorkStatusCallback> list=new ArrayList<NetWorkStatusCallback>();//在oncreate里面多次被调用
	public static boolean isInitService;//是否启动startService过Service
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
