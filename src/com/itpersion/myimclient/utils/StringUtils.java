package com.itpersion.myimclient.utils;

import android.text.TextUtils;

public class StringUtils {
	/**
	 * ��ȡ�ַ����������ַ���β����ַ���
	 * 
	 * @param format
	 *            ����Ҫ�ָ���ַ�
	 * @param chat
	 *            ����Ҫ�ָ���ַ���
	 * @return ���طָ����ַ���
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
