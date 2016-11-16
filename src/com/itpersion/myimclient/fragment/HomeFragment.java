package com.itpersion.myimclient.fragment;

import java.util.ArrayList;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.adapter.MyFragmentPagerAdapter;
import com.itpersion.myimclient.app.ConfigApplication;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @Description:登陆成功主页面
 */
public class HomeFragment extends FragmentActivity {
	private ViewPager mPager;
	private ArrayList<Fragment> fragmentsList;
	private TextView tvTabActivity, tvTabGroups, tvTabFriends, tvTabChat,
			tv_tab_unmesscount;
	private LinearLayout ll_message;
	private ImageView iv_message_icon, iv_contacts_icon, iv_friends_icon,
			iv_chat_icon;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		ConfigApplication config = (ConfigApplication) getApplication();
		config.isstart_service = true;
		InitTextView();
		InitViewPager();
	//	initDate();
		

	}
  

	@Override
	protected void onResume() {
		super.onResume();

	}

	private void InitTextView() {
		ll_message = (LinearLayout) findViewById(R.id.ll_message);
		tvTabGroups = (TextView) findViewById(R.id.tv_tab_groups);
		tvTabFriends = (TextView) findViewById(R.id.tv_tab_friends);
		tvTabChat = (TextView) findViewById(R.id.tv_tab_chat);
		tvTabActivity = (TextView) findViewById(R.id.tv_tab_activity);
		tv_tab_unmesscount = (TextView) findViewById(R.id.tv_tab_unmesscount);// 未读消息
		iv_message_icon = (ImageView) findViewById(R.id.iv_message_icon);
		iv_contacts_icon = (ImageView) findViewById(R.id.iv_contacts_icon);
		iv_friends_icon = (ImageView) findViewById(R.id.iv_friends_icon);
		iv_chat_icon = (ImageView) findViewById(R.id.iv_chat_icon);
		ll_message.setOnClickListener(new MyOnClickListener(0));
		tvTabGroups.setOnClickListener(new MyOnClickListener(1));
		tvTabFriends.setOnClickListener(new MyOnClickListener(2));
		tvTabChat.setOnClickListener(new MyOnClickListener(3));
	}

	private void InitViewPager() {
		mPager = (ViewPager) findViewById(R.id.vPager);
		// 设置viewpager保留界面不重新加载的页面数量
		mPager.setOffscreenPageLimit(4);
		fragmentsList = new ArrayList<Fragment>();
		//MessageFragment activityfragment1 = new MessageFragment();
		TestFragment1 activityfragment1 = new TestFragment1();
		TestFragment2 activityfragment2 = new TestFragment2();
		TestFragment3 activityfragment3 = new TestFragment3();
		TestFragment4 activityfragment4 = new TestFragment4();
/*		TestFragment5 activityfragment1 = new TestFragment5();
		TestFragment6 activityfragment2 = new TestFragment6();
		TestFragment7 activityfragment3 = new TestFragment7();
		TestFragment8 activityfragment4 = new TestFragment8();*/
		fragmentsList.add(activityfragment1);
		fragmentsList.add(activityfragment2);
		fragmentsList.add(activityfragment3);
		fragmentsList.add(activityfragment4);
		mPager.setAdapter(new MyFragmentPagerAdapter(
				getSupportFragmentManager(), fragmentsList));
		mPager.setCurrentItem(0);
		iv_message_icon.setImageResource(R.drawable.icon_info_press);
		tvTabActivity.setTextColor(Color.RED);
		tvTabGroups.setTextColor(Color.BLACK);
		tvTabFriends.setTextColor(Color.BLACK);
		tvTabChat.setTextColor(Color.BLACK);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());

	}

	public class MyOnClickListener implements View.OnClickListener {
		private int index = 0;

		public MyOnClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			mPager.setCurrentItem(index);
		}
	};

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			// 下面写出动态改变方法。
			switch (arg0) {
			case 0:
				tvTabActivity.setTextColor(Color.RED);
				tvTabGroups.setTextColor(Color.BLACK);
				tvTabFriends.setTextColor(Color.BLACK);
				tvTabChat.setTextColor(Color.BLACK);
				iv_message_icon.setImageResource(R.drawable.icon_info_press);
				iv_contacts_icon.setImageResource(R.drawable.icon_contacts);
				iv_friends_icon.setImageResource(R.drawable.yingji);
				iv_chat_icon.setImageResource(R.drawable.icon_me);
				//((MessageFragment) fragmentsList.get(arg0)).InItUI();
				break;
			case 1:
				tvTabActivity.setTextColor(Color.BLACK);
				tvTabGroups.setTextColor(Color.RED);
				tvTabFriends.setTextColor(Color.BLACK);
				tvTabChat.setTextColor(Color.BLACK);
				iv_message_icon.setImageResource(R.drawable.icon_info);
				iv_contacts_icon
						.setImageResource(R.drawable.icon_contacts_pressed);
				iv_friends_icon.setImageResource(R.drawable.yingji);
				iv_chat_icon.setImageResource(R.drawable.icon_me);
				break;
			case 2:
				tvTabActivity.setTextColor(Color.BLACK);
				tvTabGroups.setTextColor(Color.BLACK);
				tvTabFriends.setTextColor(Color.RED);
				tvTabChat.setTextColor(Color.BLACK);
				iv_message_icon.setImageResource(R.drawable.icon_info);
				iv_contacts_icon.setImageResource(R.drawable.icon_contacts);
				iv_friends_icon.setImageResource(R.drawable.yingji_pressed);
				iv_chat_icon.setImageResource(R.drawable.icon_me);
				break;
			case 3:
				tvTabActivity.setTextColor(Color.BLACK);
				tvTabGroups.setTextColor(Color.BLACK);
				tvTabFriends.setTextColor(Color.BLACK);
				tvTabChat.setTextColor(Color.RED);
				iv_message_icon.setImageResource(R.drawable.icon_info);
				iv_contacts_icon.setImageResource(R.drawable.icon_contacts);
				iv_friends_icon.setImageResource(R.drawable.yingji);
				iv_chat_icon.setImageResource(R.drawable.icon_me_pressed);
				break;
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		// ConfigApplication config = (ConfigApplication) getApplication();
		// HomeFragment.this.stopService(config.intent_service);
		//System.out.println("用户点击了返回键!!!");
		Log.d("config", "销毁主的");
	}

	// 用户点击了最小化窗口
	@Override
	protected void onUserLeaveHint() {
		super.onUserLeaveHint();

	}

}
