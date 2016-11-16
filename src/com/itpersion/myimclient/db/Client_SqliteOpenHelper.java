package com.itpersion.myimclient.db;

import com.itpersion.myimclient.config.Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * ���ݿ����������
 * 
 * @author �����˱�д
 * 
 */
public class Client_SqliteOpenHelper extends SQLiteOpenHelper {
	public Client_SqliteOpenHelper(Context context) {
		super(context, Config.MyDBConfig.MyDBname, null,
				Config.MyDBConfig.MyDBVersion);

	}

	public Client_SqliteOpenHelper(Context context, String name,
			CursorFactory factory, int version) {
		super(context, name, factory, version);

	}

	@Override
	public void onCreate(SQLiteDatabase arg0) {
		// primary key����Ϊ������
		// autoincrement����Ϊ�Զ�������

		// ���ѱ�
		arg0.execSQL("create table roster(_id Integer primary key autoincrement,jid text,alias text,status_mode text,status_message text,roster_group text);");
		// ����������Ϣ��
		arg0.execSQL("create table chart(_id Integer primary key autoincrement,date text,from_me text,jid text,message text,read text,pid text,user_jid text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
