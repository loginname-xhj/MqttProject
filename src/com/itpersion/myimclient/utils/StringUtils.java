package com.itpersion.myimclient.utils;

import android.text.TextUtils;

public class StringUtils {
	/**
	 * 获取字符串以特殊字符结尾后的字符串
	 * 
	 * @param format
	 *            依据要分割的字符
	 * @param chat
	 *            传入要分割的字符串
	 * @return 返回分割后的字符串
	 */
	public static String getFormatChat(String format, String chat) {
		if ((TextUtils.isEmpty(format)) && (TextUtils.isEmpty(chat))) {
			return "";
		} else {
			int index = chat.lastIndexOf(format);
			String temp = chat.substring(index + 1);
			return temp;
		}
	}
}
