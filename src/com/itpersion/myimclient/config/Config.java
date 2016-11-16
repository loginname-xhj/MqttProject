package com.itpersion.myimclient.config;

import android.net.Uri;

//基础信息配置。
public class Config {
	public final static String TAG="Config";
	// 数据库配置信息。
	public static class MyDBConfig {
		// 数据库名称
		public static String MyDBname = "IMClient.db";
		// 数据库版本
		public static int MyDBVersion = 1;
		// 朋友表名
		public static String MyRoster = "roster";
		// 朋友表下面对应的列名。
		public static String[] MyRosterColumn = RosterColumnName.lanname;
		// 聊天表名
		public static String MyChart = "chart";
		public static String[] MyChartColumn = ChartMessageColumnName.lanname;

	}

	public static class MyProvider {
		// 角色的provider。
		public static String AUTHORITY = "com.taiji.cn.provider.Rosters";
		// 角色对应的url。
		public static final Uri ROSTER_URI = Uri.parse("content://" + AUTHORITY
				+ "/" + MyDBConfig.MyRoster);
		// 聊天的provider。
		public static String CHART_AUTHORITY = "com.taiji.cn.provider.chart";
		// 聊天记录对应的url。
		public static final Uri CHART_URI = Uri.parse("content://"
				+ CHART_AUTHORITY + "/" + MyDBConfig.MyChart);
	}

}
