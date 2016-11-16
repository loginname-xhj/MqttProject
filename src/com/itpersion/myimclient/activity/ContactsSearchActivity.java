package com.itpersion.myimclient.activity;

import org.apache.harmony.javax.security.sasl.RealmChoiceCallback;

import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.app.Activity;
import android.os.Bundle;

/**
 * 联系人搜索
 * 
 * @author 机器人编写
 * 
 */
public class ContactsSearchActivity extends Activity implements NetWorkStatusCallback{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RealmChoiceCallback t=new RealmChoiceCallback(null, null, 0, false);
	}

	@Override
	public void NetWork_conn_Error() {
		
	}

	@Override
	public void NetWork_conn_Sucess() {
		
	}
}
