package com.itpersion.myimclient.activity;



import com.itpersion.myimclient.R;
import com.itpersion.myimclient.app.ConfigApplication;
import com.itpersion.myimclient.asmack.XmppConnection;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;
import com.itpersion.myimclient.utils.NetUtil;





import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PersonActivity extends Activity implements NetWorkStatusCallback {
	private RelativeLayout rl_person_top;
	private TextView title_name_tv;
	private ImageButton set_my;
	private TextView tv_person_name, tv_person_createrooms_counts,
			tv_person_createrooms_cardname, tv_person_askandanswer_counts,
			tv_person_askandanswer_cardname, tv_person_collection_counts,
			tv_person_collection_cardname;
	private ImageView show_user_head;
	private LinearLayout ll_person_createrooms, ll_person_askandanswer,
			ll_person_collection, ll_person_feedback;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		System.out.println("PersonActivity.java");
		setContentView(R.layout.activity_person);
		// ͨ�����翪�����̻߳�ȡ��ϵ������
		// ͨ�����翪�����̻߳�ȡ��ϵ��ͷ��
		// ���ð�ť����Ӧ��
		initSetButton();

		// �����ĸ���ǩ����Ӧ��
		initSetCard();
		// initView();

	}

	/**
	 * ��ʼ����ǩ��
	 */

	private void initSetCard() {
		ll_person_createrooms = (LinearLayout) findViewById(R.id.ll_person_createrooms);
		tv_person_createrooms_counts = (TextView) findViewById(R.id.tv_person_createrooms_counts);
		tv_person_createrooms_cardname = (TextView) findViewById(R.id.tv_person_createrooms_cardname);
		ll_person_askandanswer = (LinearLayout) findViewById(R.id.ll_person_askandanswer);
		tv_person_askandanswer_counts = (TextView) findViewById(R.id.tv_person_askandanswer_counts);
		tv_person_askandanswer_cardname = (TextView) findViewById(R.id.tv_person_askandanswer_cardname);

		ll_person_collection = (LinearLayout) findViewById(R.id.ll_person_collection);
		tv_person_collection_counts = (TextView) findViewById(R.id.tv_person_collection_counts);
		tv_person_collection_cardname = (TextView) findViewById(R.id.tv_person_collection_cardname);
		ll_person_feedback = (LinearLayout) findViewById(R.id.ll_person_feedback);
		// �Խ�Ⱥ��ǩ�ĵ���¼�
		ll_person_createrooms.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �����Խ�Ⱥ��ǩ����ɫ
				tv_person_createrooms_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_press));
				tv_person_createrooms_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_press));
				tv_person_createrooms_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_press));
				// �������ʱش��ǩ����ɫ
				tv_person_askandanswer_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				tv_person_askandanswer_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				// �����ղر�ǩ����ɫ
				tv_person_collection_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				tv_person_collection_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
			}
		});
		// ���ʱش��ǩ�ĵ���¼�
		ll_person_askandanswer.setOnClickListener(new OnClickListener() {

			@SuppressLint("ResourceAsColor")
			@Override
			public void onClick(View v) {
				// �����Խ�Ⱥ��ǩ����ɫ
				tv_person_createrooms_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				tv_person_createrooms_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				// �������ʱش��ǩ����ɫ
				tv_person_askandanswer_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_press));
				tv_person_askandanswer_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_press));
				// �����ղر�ǩ����ɫ
				tv_person_collection_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				tv_person_collection_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
			}
		});
		// �ҵ��ղر�ǩ�ĵ���¼�
		ll_person_collection.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// �����Խ�Ⱥ��ǩ����ɫ
				tv_person_createrooms_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				tv_person_createrooms_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				// �������ʱش��ǩ����ɫ
				tv_person_askandanswer_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				tv_person_askandanswer_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_normal));
				// �����ղر�ǩ����ɫ
				tv_person_collection_counts.setTextColor(getResources()
						.getColor(R.color.tv_person_press));
				tv_person_collection_cardname.setTextColor(getResources()
						.getColor(R.color.tv_person_press));
			}
		});
		//�������
		ll_person_feedback.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				startActivity(new Intent(PersonActivity.this, FeedBackActivity.class));
			}
		});
	}

	/**
	 * ��ʼ�����ð�ť
	 */
	private void initSetButton() {
		set_my = (ImageButton) findViewById(R.id.set_my);
		set_my.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ��ת������ҳ��
				Intent intent = new Intent();
				intent.setClass(PersonActivity.this,
						SystemSettingActivity.class);
				startActivity(intent);
			}
		});
	}

	/**
	 * ��ʼ�������
	 */
	private void initView() {
		tv_person_name = (TextView) findViewById(R.id.tv_person_name);
		show_user_head = (ImageView) findViewById(R.id.show_user_head);
		ConfigApplication application = (ConfigApplication) getApplication();
		if (NetUtil.getNetworkState(PersonActivity.this) == 0) {
			// û������

			// �����û���
			tv_person_name.setText(application.getUsername());
		} else {
			// ������
			//VCard vcard = new VCard();
		/*	try {
				vcard.load(XmppConnection.getConnection());
				String str = vcard.getNickName();
				if (str != null && (!TextUtils.isEmpty(str))) {
					tv_person_name.setText(vcard.getNickName());
				} else {
					tv_person_name.setText(application.getUsername());
				}

			} catch (XMPPException e) {
				e.printStackTrace();
			}*/

		}

	}
	@Override
	protected void onDestroy() {
		super.onDestroy();
		System.out.println("����PersonActivity");
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
