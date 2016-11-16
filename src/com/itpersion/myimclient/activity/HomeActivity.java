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

//登陆成功
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
		// 定放一个放view的list，用于存放viewPager用到的view
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
		// 这儿主要是自定义一下tabhost中的tab的样式
		RelativeLayout tabIndicator1 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tvTab1 = (TextView) tabIndicator1.findViewById(R.id.tv_title);
		tvTab1.setText("消息");
		RelativeLayout tabIndicator2 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tvTab2 = (TextView) tabIndicator2.findViewById(R.id.tv_title);
		tvTab2.setText("通讯录");
		RelativeLayout tabIndicator3 = (RelativeLayout) LayoutInflater.from(
				this).inflate(R.layout.tabwidget, null);
		TextView tvTab3 = (TextView) tabIndicator3.findViewById(R.id.tv_title);
		tvTab3.setText("我的");
		Intent intent=new Intent(HomeActivity.this,EmptyActivity.class);
		// 注意这儿Intent中的activity不能是自身
		// 比如“A”对应的是T1Activity，后面intent就new的T3Activity的。
		// 定义的标准。
		tabhost.addTab(tabhost.newTabSpec("A").setIndicator(tabIndicator1)
				.setContent(intent));
		tabhost.addTab(tabhost.newTabSpec("B").setIndicator(tabIndicator2)
				.setContent(intent));
		tabhost.addTab(tabhost.newTabSpec("C").setIndicator(tabIndicator3)
				.setContent(intent));

		pager.setAdapter(new MyPageAdapter(listViews));
		// 三个方法的执行顺序为：用手指拖动翻页时，
		// 最先执行一遍onPageScrollStateChanged（1），然后不断执行onPageScrolled，
		// 放手指的时候，直接立即执行一次onPageScrollStateChanged（2），
		// 然后立即执行一次onPageSelected，然后再不断执行onPageScrollStateChanged，
		// 最后执行一次onPageScrollStateChanged（0）。
		// 其它的情况由这个可以推出来，不再赘述。
		pager.setOnPageChangeListener(new OnPageChangeListener() {
			// 这个方法有一个参数position，代表哪个页面被选中。
			// 当用手指滑动翻页的时候，如果翻动成功了（滑动的距离够长），手指抬起来就会立即执行这个方法，
			// position就是当前滑动到的页面。如果直接setCurrentItem翻页，那position就和setCurrentItem的参数一致，
			// 这种情况在onPageScrolled执行方法前就会立即执行。
			@Override
			public void onPageSelected(int position) {
				// 当viewPager发生改变时，同时改变tabhost上面的currentTab
				// tabhost的方法直接跳转到ActivityGroup的一个位置。
				tabhost.setCurrentTab(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
		// 点击tabhost中的tab时，要切换下面的viewPager
		tabhost.setOnTabChangedListener(new OnTabChangeListener() {
			@Override
			public void onTabChanged(String tabId) {

				if ("A".equals(tabId)) {
					pager.setCurrentItem(0);
				}
				if ("B".equals(tabId)) {
					// 类似于tabHost跳转到特殊指定的Activity。
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
		// 将Activity转换成View对象。
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
