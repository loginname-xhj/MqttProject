package com.itpersion.myimclient.activity;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.provider.ContactsContract;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 通讯录页面
 * 
 * @author 机器人编写
 * 
 */
public class ContactsActivity extends Activity implements OnClickListener,NetWorkStatusCallback {
	private RelativeLayout rl_contacts_top;
	private TextView title_name_tv;
	private RelativeLayout online_rl1, online_rl2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("ContactsActivity.java");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacts);
		initView();
	    
	}

	/**
	 * 初始化组件。
	 */
	private void initView() {
		rl_contacts_top = (RelativeLayout) findViewById(R.id.rl_contacts_top);
		title_name_tv = (TextView) rl_contacts_top
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("通讯录");
		online_rl1 = (RelativeLayout) findViewById(R.id.online_rl1);
		online_rl1.setOnClickListener(this);
		online_rl2 = (RelativeLayout) findViewById(R.id.online_rl2);
		online_rl2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.online_rl1:
			intent = new Intent();
			intent.setClass(ContactsActivity.this, AddressBookActivity.class);
			startActivity(intent);
			break;
		case R.id.online_rl2:
			intent = new Intent();
			intent.setClass(ContactsActivity.this, GroupActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("销毁ContactsActivity");
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
