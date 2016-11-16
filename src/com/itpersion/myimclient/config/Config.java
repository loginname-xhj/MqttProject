package com.itpersion.myimclient.config;

import android.net.Uri;

//������Ϣ���á�
public class Config {
	public final static String TAG="Config";
	// ���ݿ�������Ϣ��
	public static class MyDBConfig {
		// ���ݿ�����
		public static String MyDBname = "IMClient.db";
		// ���ݿ�汾
		public static int MyDBVersion = 1;
		// ���ѱ���
		public static String MyRoster = "roster";
		// ���ѱ������Ӧ��������
		public static String[] MyRosterColumn = RosterColumnName.lanname;
		// �������
		public static String MyChart = "chart";
		public static String[] MyChartColumn = ChartMessageColumnName.lanname;

	}

	public static class MyProvider {
		// ��ɫ��provider��
		public static String AUTHORITY = "com.taiji.cn.provider.Rosters";
		// ��ɫ��Ӧ��url��
		public static final Uri ROSTER_URI = Uri.parse("content://" + AUTHORITY
				+ "/" + MyDBConfig.MyRoster);
		// �����provider��
		public static String CHART_AUTHORITY = "com.taiji.cn.provider.chart";
		// �����¼��Ӧ��url��
		public static final Uri CHART_URI = Uri.parse("content://"
				+ CHART_AUTHORITY + "/" + MyDBConfig.MyChart);
	}

}
