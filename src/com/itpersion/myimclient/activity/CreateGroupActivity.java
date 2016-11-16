package com.itpersion.myimclient.activity;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.imp.NetWorkStatusCallback;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 
 * @author 创建群
 * 
 */
public class CreateGroupActivity extends Activity implements OnClickListener,NetWorkStatusCallback{
	private RelativeLayout rl_creategroup_top;
	private TextView title_name_tv;
	private ImageButton ib_top_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creategroup);
		initView();
	}

	/**
	 * 初始化UI
	 */
	private void initView() {
		rl_creategroup_top = (RelativeLayout) findViewById(R.id.rl_creategroup_top);
		title_name_tv = (TextView) rl_creategroup_top
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("创建群");
		ib_top_back = (ImageButton) rl_creategroup_top
				.findViewById(R.id.ib_top_back);
		ib_top_back.setVisibility(View.VISIBLE);
		ib_top_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_top_back:
			finish();
			break;

		default:
			break;
		}
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
