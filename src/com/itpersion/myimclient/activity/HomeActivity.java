package com.itpersion.myimclient.activity;

import java.util.ArrayList;
import java.util.List;
import com.itpersion.myimclient.R;
import com.itpersion.myimclient.app.ConfigApplication;
import com.itpersion.myimclient.service.ClientConnService;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.OnTabChangeListener;

//��½�ɹ�
public class HomeActivity extends Activity {
	/*List<View> listViews;
	LocalActivityManager manager = null;
	private ViewPager pager = null;
	private TabHost tabhost;
	private Intent intent_service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		pager = (ViewPager) findViewById(R.id.viewpager);
		// ����һ����view��list�����ڴ��viewPager�õ���view
		listViews = new ArrayList<View>();
		manager = new LocalActivityManager(this, true);
		manager.dispatchCreate(savedInstanceState);
		Intent i1 = new Intent(HomeActivity.this, MessageActivity.class);
		listViews.add(getView("A", i1));
		Intent i2 = new Intent(HomeActivity.this, ContactsActivity.class);
		listViews.add(getView("B", i2));
		Intent i3 = new Intent(HomeActivity.this, PersonActivity.class);
		listViews.add(getView("C", i3));
		tabhost = (TabHost) findViewById(R.id.tabhost);
		tabhost.setup();
		tabhost.setup(manager);
		// �����Ҫ���Զ���һ��tabhost�е�tab����ʽ
		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tvTab1 = (TextView) tabIndicator1.findViewById(R.id.tv_title);
		tvTab1.setText("��Ϣ");
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tvTab2 = (TextView) tabIndicator2.findViewById(R.id.tv_title);
		tvTab2.setText("ͨѶ¼");
		RelativeLayout tabIndicator3 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tvTab3 = (TextView) tabIndicator3.findViewById(R.id.tv_title);
		tvTab3.setText("�ҵ�");
		Intent intent=new Intent(HomeActivity.this,EmptyActivity.class);
		// ע�����Intent�е�activity����������
		// ���硰A����Ӧ����T1Activity������intent��new��T3Activity�ġ�
		// ����ı�׼��
		tabhost.addTab(tabhost.newTabSpec("A").setIndicator(tabIndicator1)
				.setContent(intent));
		tabhost.addTab(tabhost.newTabSpec("B").setIndicator(tabIndicator2)
				.setContent(intent));
		tabhost.addTab(tabhost.newTabSpec("C").setIndicator(tabIndicator3)
				.setContent(intent));

		pager.setAdapter(new MyPageAdapter(listViews));
		// ����������ִ��˳��Ϊ������ָ�϶���ҳʱ��
		// ����ִ��һ��onPageScrollStateChanged��1����Ȼ�󲻶�ִ��onPageScrolled��
		// ����ָ��ʱ��ֱ������ִ��һ��onPageScrollStateChanged��2����
		// Ȼ������ִ��һ��onPageSelected��Ȼ���ٲ���ִ��onPageScrollStateChanged��
		// ���ִ��һ��onPageScrollStateChanged��0����
		// �������������������Ƴ���������׸����
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			// ���������һ������position�������ĸ�ҳ�汻ѡ�С�
			// ������ָ������ҳ��ʱ����������ɹ��ˣ������ľ��빻��������ָ̧�����ͻ�����ִ�����������
			// position���ǵ�ǰ��������ҳ�档���ֱ��setCurrentItem��ҳ����position�ͺ�setCurrentItem�Ĳ���һ�£�
			// ���������onPageScrolledִ�з���ǰ�ͻ�����ִ�С�
			@Override
			public void onPageSelected(int position) {
				// ��viewPager�����ı�ʱ��ͬʱ�ı�tabhost�����currentTab
				// tabhost�ķ���ֱ����ת��ActivityGroup��һ��λ�á�
				tabhost.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		// ���tabhost�е�tabʱ��Ҫ�л������viewPager
		tabhost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {

				if ("A".equals(tabId)) {
					pager.setCurrentItem(0);
				}
				if ("B".equals(tabId)) {
					// ������tabHost��ת������ָ����Activity��
					pager.setCurrentItem(1);
				}
				if ("C".equals(tabId)) {
					pager.setCurrentItem(2);
				}
			}
		});
		intent_service = new Intent(HomeActivity.this, ClientConnService.class);
		startService(intent_service);
		ConfigApplication config = (ConfigApplication) getApplication();
		config.isstart_service = true;
		pager.setCurrentItem(0);
		tabhost.setCurrentTab(0);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		stopService(intent_service);

	}

	private View getView(String id, Intent intent) {
		// ��Activityת����View����
		return manager.startActivity(id, intent).getDecorView();
	}

	private class MyPageAdapter extends PagerAdapter {

		private List<View> list;

		private MyPageAdapter(List<View> list) {
			this.list = list;
		}

		@Override
		public void destroyItem(View view, int position, Object arg2) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.removeView(list.get(position));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object instantiateItem(View view, int position) {
			ViewPager pViewPager = ((ViewPager) view);
			pViewPager.addView(list.get(position));
			return list.get(position);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}

	}*/
}
