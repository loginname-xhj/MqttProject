package com.itpersion.myimclient.db;

import com.itpersion.myimclient.config.Config;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库操作工具类
 * 
 * @author 机器人编写
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
		// primary key设置为主键，
		// autoincrement设置为自动增长。

		// 朋友表。
		arg0.execSQL("create table roster(_id Integer primary key autoincrement,jid text,alias text,status_mode text,status_message text,roster_group text);");
		// 单人聊天消息表
		arg0.execSQL("create table chart(_id Integer primary key autoincrement,date text,from_me text,jid text,message text,read text,pid text,user_jid text);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

}
