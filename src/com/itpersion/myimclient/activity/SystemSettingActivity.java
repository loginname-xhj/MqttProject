package com.itpersion.myimclient.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

/**
 * 软件设置界面
 * 
 * @author 机器人编写
 * 
 */
public class SystemSettingActivity extends Activity implements NetWorkStatusCallback{
	private RelativeLayout ll_syssetview;
	private TextView title_name_tv;
	private ImageButton ib_top_back;
	private ImageButton ib_settingview_vcard;
	private ImageView iv_settingview_icon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settingview);
		ll_syssetview = (RelativeLayout) findViewById(R.id.ll_syssetview);
		title_name_tv = (TextView) ll_syssetview
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("设置");
		ib_top_back = (ImageButton) ll_syssetview
				.findViewById(R.id.ib_top_back);
		ib_top_back.setVisibility(View.VISIBLE);
		ib_top_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		ib_settingview_vcard = (ImageButton) findViewById(R.id.ib_settingview_vcard);
		ib_settingview_vcard.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(SystemSettingActivity.this, VcardActivity.class);
				startActivity(intent);
			}
		});
		iv_settingview_icon = (ImageView) findViewById(R.id.iv_settingview_icon);
		iv_settingview_icon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Toast.makeText(SystemSettingActivity.this, "放大图片", 0).show();
			}
		});
	}

	@Override
	public void NetWork_conn_Error() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NetWork_conn_Sucess() {
		// TODO Auto-generated method stub
		
	}
}
