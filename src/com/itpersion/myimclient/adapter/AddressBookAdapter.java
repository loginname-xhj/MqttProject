package com.itpersion.myimclient.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

/**
 * 通讯录适配器
 * 
 * @author 机器人编写
 * 
 */
public class AddressBookAdapter extends BaseExpandableListAdapter {
	private Context context;

	public AddressBookAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getGroupCount() {
		return 8;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return (groupPosition + 1) * (groupPosition + 1);
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		TextView tv = new TextView(context);
		tv.setText("          第" + groupPosition + "节点");
		tv.setTextColor(Color.RED);
		return tv;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		TextView tv = new TextView(context);
		tv.setText("        第" + groupPosition + "父节点" + "第" + childPosition
				+ "孩子节点");
		return tv;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
