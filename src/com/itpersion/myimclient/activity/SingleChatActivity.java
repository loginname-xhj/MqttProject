package com.itpersion.myimclient.activity;



import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.app.Activity;
/**
 * ����������
 * @author home
 *
 */
public class SingleChatActivity extends Activity implements NetWorkStatusCallback{
	public static final String INTENT_EXTRA_USERNAME = SingleChatActivity.class.getName() + ".username";// �ǳƶ�Ӧ��key

	@Override
	public void NetWork_conn_Error() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void NetWork_conn_Sucess() {
		// TODO Auto-generated method stub
		
	}
}
