package com.itpersion.myimclient.utils;

import com.itpersion.myimclient.R;
import com.itpersion.myimclient.activity.GroupActivity;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

/**
 * �����Ի��򹤾���
 * 
 * @author �����˱�д
 * 
 */
public class AlertDialgUtils {
	public static void CreateAlertDialog(final Context context, final Intent intent) {
		AlertDialog.Builder builder = new Builder(context);
		builder.setIcon(R.drawable.ic_launcher);
		builder.setTitle("����Ⱥ");
		builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				context.startActivity(intent);
			}
		}).setNegativeButton("ȡ��", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		}).show();
	}
}
