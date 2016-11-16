package com.itpersion.myimclient.config;

/**
 * 角色列名。
 * 
 * @author 机器人编写
 * 
 */
public class ChartMessageColumnName {
	// 全部列名
	public static String[] lanname = { "_id", "date", "from_me", "jid",
			"message", "read", "pid", "user_jid" };
	public static String ID = "_id";// 数据表的id。
	public static String DATE = "date";// 消息发送时间
	public static String FROME = "from_me";// 消息来自
	public static String JID = "jid";// 当前用户的jid
	public static String MESSAGE = "message";// 用户发送的消息
	public static String READ = "read";// 用户阅读状态
	public static String PID = "pid";// 消息唯一标识符
	public static String USER_JID = "user_jid";// 消息是当前人的。
}
