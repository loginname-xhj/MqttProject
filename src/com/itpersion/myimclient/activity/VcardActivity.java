package com.itpersion.myimclient.activity;

import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class VcardActivity extends Activity implements NetWorkStatusCallback {
	private RelativeLayout ll_vcard;
	private TextView title_name_tv, tv_vcard_telphone, tv_vcard_hometelphone;

	private ImageButton ib_top_back, btn_vcard_sms, btn_vcard_tel_call,
			btn_vcard_guding_call;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vcard);
		ll_vcard = (RelativeLayout) findViewById(R.id.ll_vcard);
		title_name_tv = (TextView) ll_vcard.findViewById(R.id.title_name_tv);
		title_name_tv.setText("��ϸ����");
		ib_top_back = (ImageButton) ll_vcard.findViewById(R.id.ib_top_back);
		ib_top_back.setVisibility(View.VISIBLE);
		ib_top_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		// ��ϵ�˵绰
		tv_vcard_telphone = (TextView) findViewById(R.id.tv_vcard_telphone);
		btn_vcard_sms = (ImageButton) findViewById(R.id.btn_vcard_sms);
		btn_vcard_sms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String vcard_sms = tv_vcard_telphone.getText().toString()
						.trim();
				if (TextUtils.isEmpty(vcard_sms)) {
					// ��ʼ���š�
					return;
				} else {
					// ��ʼ���Ų�Ϊ��
					// �������Ͷ���
					Intent intent = new Intent();
					intent.setAction("android.intent.action.SENDTO");
					intent.setData(Uri.parse("sms:" + vcard_sms));
					startActivity(intent);

				}
			}
		});
		btn_vcard_tel_call = (ImageButton) findViewById(R.id.btn_vcard_tel_call);
		btn_vcard_tel_call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String vcard_tel = tv_vcard_telphone.getText().toString()
						.trim();
				if (TextUtils.isEmpty(vcard_tel)) {
					// ��ʼ���绰Ϊ��
					Toast.makeText(VcardActivity.this, "�ƶ��绰Ϊ��", 0).show();
					return;
				} else {
					// ��ʼ���绰��Ϊ��
					// ��������绰����
					Intent intent = new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + vcard_tel));
					startActivity(intent);

				}
			}
		});
		btn_vcard_guding_call = (ImageButton) findViewById(R.id.btn_vcard_guding_call);
		tv_vcard_hometelphone = (TextView) findViewById(R.id.tv_vcard_hometelphone);
		btn_vcard_guding_call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String vcard_home_tel = tv_vcard_hometelphone.getText()
						.toString().trim();
				if (TextUtils.isEmpty(vcard_home_tel)) {
					// ��ʼ���绰Ϊ��
					Toast.makeText(VcardActivity.this, "�̶��绰Ϊ��", 0).show();
					return;
				} else {
					// ��ʼ���绰��Ϊ��
					// ��������绰����
					Intent intent = new Intent(Intent.ACTION_CALL, Uri
							.parse("tel:" + vcard_home_tel));
					startActivity(intent);

				}
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
