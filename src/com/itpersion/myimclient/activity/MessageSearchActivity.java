package com.itpersion.myimclient.activity;

import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.muc.MultiUserChat;

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
 * 最近消息的搜索
 * 
 * @author 机器人编写
 * 
 */
public class MessageSearchActivity extends Activity implements NetWorkStatusCallback{
	private RelativeLayout rl_search_title;
	private TextView title_name_tv;
	private ImageButton ib_top_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messagesearch);
		rl_search_title = (RelativeLayout) findViewById(R.id.rl_search_title);
		title_name_tv = (TextView) rl_search_title
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("搜索");
		ib_top_back = (ImageButton) rl_search_title
				.findViewById(R.id.ib_top_back);
		ib_top_back.setVisibility(View.VISIBLE);
		ib_top_back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		try {
			MultiUserChat muc = null;
			// 获得聊天室的配置表单
			Form form = muc.getConfigurationForm();
			// 根据原始表单创建一个要提交的新表单。
			Form submitForm = form.createAnswerForm();
			// 设置聊天室是持久聊天室，即将要被保存下来
			submitForm.setAnswer("muc#roomconfig_persistentroom", true);
		} catch (XMPPException e) {
			e.printStackTrace();
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
