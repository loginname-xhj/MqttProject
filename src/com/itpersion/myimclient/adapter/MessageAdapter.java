package com.itpersion.myimclient.adapter;

import java.util.List;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.domain.MyDefaultMessage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {
	private Context context;
	private List<MyDefaultMessage> list;

	public MessageAdapter(Context context, List<MyDefaultMessage> list) {
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view;
		ViewHolder holder;
		if (convertView != null && convertView instanceof LinearLayout) {
			view = convertView;
			holder = (ViewHolder) view.getTag();
		} else {
			view = View.inflate(context, R.layout.message_item, null);
			holder = new ViewHolder();

			holder.iv_icon = (ImageView) view.findViewById(R.id.icon);
			holder.recent_list_item_name = (TextView) view
					.findViewById(R.id.recent_list_item_name);
			holder.recent_list_item_msg = (TextView) view
					.findViewById(R.id.recent_list_item_msg);
			holder.recent_list_item_time = (TextView) view
					.findViewById(R.id.recent_list_item_time);
			holder.unreadmsg = (TextView) view.findViewById(R.id.unreadmsg);
			view.setTag(holder);
		}
		
		holder.recent_list_item_name.setText(list.get(position)
				.getMessage_fromer());
		holder.recent_list_item_msg.setText(list.get(position)
				.getMessage_text());
		holder.recent_list_item_time.setText(list.get(position)
				.getMessage_time());
		if (list.get(position).getMessage_unread().equals("0")) {
			holder.unreadmsg.setVisibility(View.INVISIBLE);
		} else {
			holder.unreadmsg.setVisibility(View.VISIBLE);
			holder.unreadmsg.setText(list.get(position).getMessage_unread());
		}

		return view;
	}

	static class ViewHolder {
		public ImageView iv_icon;// 聊天里面的头像
		public TextView recent_list_item_name; // 聊天里面的名称
		public TextView recent_list_item_msg;// 聊天里面的信息
		public TextView recent_list_item_time;// 聊天里面的时间
		public TextView unreadmsg;// 聊天里面的未读条数
	}
}
