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
import android.text.TextUtils;

/**
 * 联系人的ContentProvider.
 * 
 * @author 机器人编写。
 * 
 */
public class RosterProvider extends ContentProvider {

	private Client_SqliteOpenHelper sdb;
	private SQLiteDatabase db;

	public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.yaxim.roster";
	public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.yaxim.roster";
	private static final UriMatcher URI_MATCHER = new UriMatcher(
			UriMatcher.NO_MATCH);
	private static final int CONTACTS = 1;
	private static final int CONTACT_ID = 2;

	static {
		URI_MATCHER.addURI(Config.MyProvider.AUTHORITY, "roster", CONTACTS);
		URI_MATCHER.addURI(Config.MyProvider.AUTHORITY, "roster/#", CONTACT_ID);

	}

	@Override
	public int delete(Uri arg0, String arg1, String[] arg2) {
		int num = 0;
		int match = URI_MATCHER.match(arg0);
		switch (match) {
		case CONTACTS:
			num = db.delete(Config.MyDBConfig.MyRoster, arg1, arg2);
			break;
		case CONTACT_ID:
			String segment = arg0.getPathSegments().get(1);

			if (TextUtils.isEmpty(arg1)) {
				arg1 = "_id=" + segment;
			} else {
				arg1 = "_id=" + segment + " AND (" + arg1 + ")";
			}
			num = db.delete(Config.MyDBConfig.MyRoster, arg1, arg2);
			break;
		default:
			break;
		}
		getContext().getContentResolver().notifyChange(arg0, null);
		return 0;
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

		long rowId = db.insert(Config.MyDBConfig.MyRoster, "", values);
		if (rowId > 0) {
			Uri rowUri = ContentUris.withAppendedId(uri, rowId);
			getContext().getContentResolver().notifyChange(rowUri, null);
			return rowUri;
		}
		return null;

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

		return null;

	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		int match = URI_MATCHER.match(uri);
		int count;
		long rowId = 0;
		switch (match) {
		case CONTACTS:
			count = db.update(Config.MyDBConfig.MyRoster, values, selection,
					selectionArgs);
			break;
		case CONTACT_ID:
			String segment = uri.getPathSegments().get(1);
			rowId = Long.parseLong(segment);
			count = db.update(Config.MyDBConfig.MyRoster, values, "_id="
					+ rowId, selectionArgs);
			break;
		default:
			throw new UnsupportedOperationException("Cannot update URL: " + uri);
		}
		getContext().getContentResolver().notifyChange(uri, null);
		return count;
	}

	@Override
	public int bulkInsert(Uri uri, ContentValues[] values) {
		int numValues = 0;
		db.beginTransaction(); // 开始事务
		try {
			// 数据库操作
			numValues = values.length;
			for (int i = 0; i < numValues; i++) {
				insert(uri, values[i]);
			}
			db.setTransactionSuccessful();
		} finally {
			db.endTransaction(); // 结束事务
		}
		return numValues;
	}

}
