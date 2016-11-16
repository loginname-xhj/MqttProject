package com.itpersion.myimclient.activity;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 一键反馈
 * 
 * @author 机器人编写
 * 
 */

public class FeedBackActivity extends Activity implements NetWorkStatusCallback{
	private RelativeLayout rl_feedback_title;
	private TextView title_name_tv;
	private ImageButton ib_top_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feedback);
		rl_feedback_title = (RelativeLayout) findViewById(R.id.rl_feedback_title);
		title_name_tv = (TextView) rl_feedback_title
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("意见反馈");
		ib_top_back = (ImageButton) findViewById(R.id.ib_top_back);
		ib_top_back.setVisibility(View.VISIBLE);
		ib_top_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
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
