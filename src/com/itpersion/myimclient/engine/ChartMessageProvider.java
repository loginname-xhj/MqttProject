package com.itpersion.myimclient.engine;

import android.content.ContentResolver;
import android.database.Cursor;

import com.itpersion.myimclient.config.ChartMessageColumnName;
import com.itpersion.myimclient.config.Config;
import com.itpersion.myimclient.config.Config.MyDBConfig;
import com.itpersion.myimclient.config.Config.MyProvider;
import com.itpersion.myimclient.domain.MyDefaultMessage;

public class ChartMessageProvider {
	/**
	 * 依据数据的id获取出消息消息。
	 * 
	 * @return 返回消息实体
	 */
	public static MyDefaultMessage getMessageByID(String id,
			ContentResolver resolver) {

		MyDefaultMessage mymessage = new MyDefaultMessage();
		Cursor cursor = resolver.query(MyProvider.CHART_URI,
				Config.MyDBConfig.MyChartColumn, ChartMessageColumnName.ID
						+ "=?", new String[] { id }, null);

		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				String date = cursor.getString(1);
				mymessage.setMessage_time(date);
				String fromer = cursor.getString(3);
				mymessage.setMessage_fromer(fromer);
				String mess = cursor.getString(4);
				mymessage.setMessage_text(mess);

			}
			String str = getreadstatus(mymessage.message_fromer, resolver);
			mymessage.setMessage_unread(str);
			cursor.close();

		}

		return mymessage;

	}

	public static String getreadstatus(String sender, ContentResolver resolver) {
		int unread_counts = 0;
		Cursor cursor = resolver.query(MyProvider.CHART_URI,
				Config.MyDBConfig.MyChartColumn, ChartMessageColumnName.JID
						+ "=? and " + ChartMessageColumnName.READ + "=?",
				new String[] { sender, "0" }, null);
		if (cursor != null && cursor.getCount() > 0) {
			while (cursor.moveToNext()) {
				String read = cursor.getString(5);
				if (read.equals("0")) {
					unread_counts++;
				}

			}
		}
		return unread_counts + "";
	}
}
