package com.itpersion.myimclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.animation.AlphaAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.app.ConfigApplication;
import com.itpersion.myimclient.fragment.HomeFragment;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

/**
 * ��ʼ������
 * 
 * @author home
 * 
 */
public class SplashActivity extends Activity {
	private TextView tv_splash_version;
	private RelativeLayout rl_splash_bg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ConfigApplication App_Config=(ConfigApplication)getApplication();
		if(App_Config.isstart_service){
			//��̨��½����,ֱ�ӽ����½����
			startActivity(new Intent(this, HomeFragment.class));
			finish();
			return;
	    }else{
	    	//��̨��½û�п���,�����½
	    }
		setContentView(R.layout.activity_splash);
		tv_splash_version = (TextView) findViewById(R.id.tv_splash_version);
		tv_splash_version.setText("�汾:" + GetAppVersion());
		rl_splash_bg = (RelativeLayout) findViewById(R.id.rl_splash_bg);
		AlphaAnimation aa = new AlphaAnimation(0.2f, 1.0f);
		aa.setDuration(3000);
		rl_splash_bg.startAnimation(aa);
		new Thread() {
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Intent intent = new Intent();
				intent.setClass(SplashActivity.this, LoginActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
			};
		}.start();

	}

	/**
	 * ��ȡ����汾��
	 * 
	 * @return ���ذ汾��
	 */
	private String GetAppVersion() {
		PackageManager pm = getPackageManager();
		try {
			PackageInfo info = pm.getPackageInfo(getPackageName(), 0);
			return info.versionName;
		} catch (Exception e) {
			return "";
		}

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		}
		return super.onKeyDown(keyCode, event);

	}


}
