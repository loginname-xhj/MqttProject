package com.itpersion.myimclient.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.domain.Person;
import com.itpersion.myimclient.utils.AlertDialgUtils;

/**
 * Ⱥ����ʾ����
 * 
 * @author �����˱�д
 * 
 */
public class GroupActivity extends Activity implements OnClickListener {
	private RelativeLayout rl_addressbook_top;
	private TextView title_name_tv;
	private ImageButton ib_top_sysset, ib_top_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_group);
		initView();
		initData();
		List<Person> listA = new ArrayList<Person>();
		Person p1 = new Person();
		Person p2 = new Person();
		Person p3 = new Person();
		Person p4 = new Person();
		Person p5 = new Person();
		p1.setName("name1");
		p1.setOrder(1);
		p2.setName("name2");
		p2.setOrder(2);
		p3.setName("name3");
		p3.setOrder(3);
		p4.setName("name5");
		p4.setOrder(5);
		p5.setName("name9");
		p5.setOrder(9);
		listA.add(p2);
		listA.add(p1);
		listA.add(p3);
		listA.add(p5);
		Collections.sort(listA, new Comparator<Person>() {
			public int compare(Person arg0, Person arg1) {
				System.out.println("����");
				return arg0.getOrder().compareTo(arg1.getOrder());
			}
		});

		for (Person p : listA) {
			System.out.println(p.getName());
		}
	}

	/**
	 * ��ʼ������
	 */
	private void initData() {
		/*ServiceDiscoveryManager discoManager = ServiceDiscoveryManager.getInstanceFor(XmppConnection.getConnection());
		DiscoverItems discoItems = null;
		try {
			discoItems = discoManager.discoverItems("conference.www.taiji.com.cn");
			Iterator it = discoItems.getItems();
			// ��ʾԶ��XMPPʵ�����Ŀ
			while (it.hasNext()) {
				DiscoverItems.Item item = (DiscoverItems.Item) it.next();
				listDiscoverItems.add(item);
						}
		} catch (NotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XMPPException e) {
			// ��ñ���ѯ��XMPPʵ���Ҫ�鿴����Ŀ
			
			e.printStackTrace();
		}*/
	}

	/**
	 * ��ʼ��UI
	 */
	private void initView() {
		rl_addressbook_top = (RelativeLayout) findViewById(R.id.rl_group_top);
		title_name_tv = (TextView) rl_addressbook_top
				.findViewById(R.id.title_name_tv);
		title_name_tv.setText("Ⱥ��");
		ib_top_sysset = (ImageButton) rl_addressbook_top
				.findViewById(R.id.ib_top_sysset);
		ib_top_sysset.setVisibility(View.VISIBLE);
		ib_top_sysset.setOnClickListener(this);
		ib_top_back = (ImageButton) rl_addressbook_top
				.findViewById(R.id.ib_top_back);
		ib_top_back.setVisibility(View.VISIBLE);
		ib_top_back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_top_sysset:
			Intent intent = new Intent(GroupActivity.this,
					CreateGroupActivity.class);

			AlertDialgUtils.CreateAlertDialog(GroupActivity.this, intent);
			break;
		case R.id.ib_top_back:
			finish();
			break;
		default:
			break;
		}
	}
}
