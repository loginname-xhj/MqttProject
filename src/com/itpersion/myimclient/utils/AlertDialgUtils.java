package com.itpersion.myimclient.utils;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.activity.GroupActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * 构建对话框工具类
 * 
 * @author 机器人编写
 * 
 */
public class AlertDialgUtils {
	public static void CreateAlertDialog(final Context context, final Intent intent) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("创建群");
		builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				context.startActivity(intent);
			}
		}).setNegativeButton("取消", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		}).show();
	}
}
