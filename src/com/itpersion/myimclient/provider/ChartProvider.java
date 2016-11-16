package com.itpersion.myimclient.provider;

import com.itpersion.myimclient.config.Config;
import com.itpersion.myimclient.db.Client_SqliteOpenHelper;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

/**
 * 聊天记录
 * 
 * @author 机器人编写
 * 
 */
public class ChartProvider extends ContentProvider {
	private Client_SqliteOpenHelper sdb;
	private SQLiteDatabase db;
	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.yaxim.chart";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.yaxim.chart";
	private static final UriMatcher URI_MATCHER = new UriMatcher(
			UriMatcher.NO_MATCH);
	private static final int CONTACTS = 1;
	private static final int CONTACT_ID = 2;

	static {
		URI_MATCHER
				.addURI(Config.MyProvider.CHART_AUTHORITY, "chart", CONTACTS);
		URI_MATCHER.addURI(Config.MyProvider.CHART_AUTHORITY, "chart/#",
				CONTACT_ID);

	}

	@Override
	public boolean onCreate() {
		sdb = new Client_SqliteOpenHelper(getContext());
		db = sdb.getWritableDatabase();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		int match = URI_MATCHER.match(uri);

		if (match == CONTACTS) {
			return db.query("chart", projection, selection, selectionArgs,
					null, null, sortOrder);
		} else if (match == CONTACT_ID) {

			return db.query("chart", projection, selection, selectionArgs,
					null, null, sortOrder);
		} else {
			System.out.println("ContentProvider操作查找失败,原因url不合法");
			throw new IllegalArgumentException("Unknown URL");

		}
	}

	@Override
	public String getType(Uri uri) {
		int match = URI_MATCHER.match(uri);
		switch (match) {
		case CONTACTS:
			return RosterProvider.CONTENT_TYPE;
		case CONTACT_ID:
			return RosterProvider.CONTENT_ITEM_TYPE;
		default:
			throw new IllegalArgumentException("Unknown URL");
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
          
		long rowId = db.insert(Config.MyDBConfig.MyChart, "", values);
		if (rowId > 0) {
			Uri rowUri = ContentUris.withAppendedId(uri, rowId);
			getContext().getContentResolver().notifyChange(rowUri, null);
			return rowUri;
		}
		return null;
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		return 0;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		return 0;
	}

}
