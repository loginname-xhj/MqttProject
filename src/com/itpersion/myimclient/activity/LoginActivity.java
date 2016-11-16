package com.itpersion.myimclient.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.app.ConfigApplication;
import com.itpersion.myimclient.db.Client_SqliteOpenHelper;
import com.itpersion.myimclient.fragment.CopyOfHomeFragment;
import com.itpersion.myimclient.fragment.HomeFragment;
import com.itpersion.myimclient.service.ClientConnService;
import com.itpersion.myimclient.service.IConnectionStatusCallback;

/**
 * ��½ҳ��
 * 
 * @author �����˱�д
 * 
 */
public class LoginActivity extends Activity implements
		IConnectionStatusCallback {
	private EditText username, password;
	private String input_username, input_password;
	private ClientConnService myService;

	private Dialog mLoginDialog;
	private CheckBox auto_save_password, hide_login;
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sp = getSharedPreferences("config", MODE_PRIVATE);
		initView();
		boolean auto_save = sp.getBoolean("auto_save_password", false);
		auto_save_password.setChecked(auto_save);// ��ʼ�����ñ�������״̬
		if (auto_save) {
			username.setText(sp.getString("user_name", ""));
			password.setText(sp.getString("pass_word", ""));
		}
		boolean hide_login_stats = sp.getBoolean("hide_login", false);
		hide_login.setChecked(hide_login_stats);
		bindService();
		// ��ʼ���������ݿ�����ݱ�
		initDB();
	}

	// ��ʼ�����ݿ⡣
	private void initDB() {
		Client_SqliteOpenHelper my_csop = new Client_SqliteOpenHelper(
				LoginActivity.this);

	}

	ServiceConnection conn = new ServiceConnection() {

		@Override
		public void onServiceDisconnected(ComponentName name) {
			myService.unRegisterConnectionStatusCallback();
			myService = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			myService = ((ClientConnService.MyBinder) service).getService();
			myService.registerConnectionStatusCallback(LoginActivity.this);
		}
	};

	/**
	 * ��Service��
	 */
	private void bindService() {
		Intent bindIntent = new Intent(LoginActivity.this,
				ClientConnService.class);
		bindService(bindIntent, conn, Context.BIND_AUTO_CREATE);
	}

	/**
	 * ��ʼ��һЩView���
	 */
	private void initView() {
		username = (EditText) findViewById(R.id.username);
		password = (EditText) findViewById(R.id.password);
		// ��ס����
		auto_save_password = (CheckBox) findViewById(R.id.auto_save_password);
		// �����½
		hide_login = (CheckBox) findViewById(R.id.hide_login);

		final ProgressDialog pd = new ProgressDialog(LoginActivity.this);
		pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		pd.setTitle("���ڵ�½.....");
		pd.setCanceledOnTouchOutside(false);
		pd.setOnCancelListener(new OnCancelListener() {

			@Override
			public void onCancel(DialogInterface dialog) {
				myService.startlogin(input_username, input_password,
						getResources()
								.getString(R.string.client_stoplogin_flag));
				pd.dismiss();
			}
		});

		mLoginDialog = pd;// ��ʼ���Ի���
	}

	public void register(View view) {

	}

	public void onLoginClick(View view) {
	
			input_username = username.getText().toString();
			input_password = password.getText().toString();
			if (TextUtils.isEmpty(input_username)) {
				Toast.makeText(LoginActivity.this, "�������û���", 0).show();
				return;
			}
			if (TextUtils.isEmpty(input_password)) {
				Toast.makeText(LoginActivity.this, "����������", 0).show();
				return;
			}

			if (myService != null) {
				if (mLoginDialog != null && !mLoginDialog.isShowing())
					mLoginDialog.show();
				SharedDate(input_username, input_password);
				myService.startlogin(input_username, input_password,
						getResources().getString(R.string.client_login_flag));
			}
	
	}

	/**
	 * ��������
	 * 
	 * @param name
	 *            ��½�û�����
	 * @param pwd
	 *            ��½���롣
	 */
	private void SharedDate(String name, String pwd) {
		ConfigApplication application = (ConfigApplication) getApplication();
		application.setUsername(name);
		application.setPassword(pwd);
	}

	@Override
	public void connectionStatusChanged(int connectedState, String reason) {
		mLoginDialog.dismiss();
		Editor editor = sp.edit();
		if (connectedState == ClientConnService.CONNECTED) {
			// ��½�ɹ���
			// ��ʼ��������
			if (auto_save_password.isChecked()) {

				editor.putBoolean("auto_save_password", true);
				editor.putString("user_name", username.getText().toString()
						.trim());
				editor.putString("pass_word", password.getText().toString()
						.trim());

				editor.commit();
			} else {

				editor.putBoolean("auto_save_password", false);

				editor.commit();
			}
			// �����½�趨
			editor.putBoolean("hide_login", hide_login.isChecked());

			editor.commit();
			ConfigApplication config = (ConfigApplication) getApplication();
			config.isInitService = true;
			config.intent_service = new Intent(LoginActivity.this,
					ClientConnService.class);
			startService(config.intent_service);
			// ��½�ɹ�
			startActivity(new Intent(this, CopyOfHomeFragment.class));
			finish();
		} else if (connectedState == ClientConnService.DISCONNECTED) {
			if (TextUtils.equals(reason, ClientConnService.NETWORK_ERROR)) {
				Toast.makeText(LoginActivity.this, "�������", 0).show();
			} else if (TextUtils.equals(reason, ClientConnService.PONG_TIMEOUT)) {
				Toast.makeText(LoginActivity.this, "��½��ʱ", 0).show();
			} else {
				Toast.makeText(LoginActivity.this, "�������쳣", 0).show();
			}

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unbindService(conn);
		// �����½�趨
		if (hide_login.isChecked()) {
			Editor editor = sp.edit();
			editor.putBoolean("hide_login", true);
			editor.commit();
		} else {
			Editor editor = sp.edit();
			editor.putBoolean("hide_login", false);
			editor.commit();
		}
	}

}
