package com.itpersion.myimclient.imp;

/**
 * 首先定义一些接口，需要实现一些什么样的功能，
 * 
 * 
 * 
 */
public interface Smack {
	/**
	 * 登陆
	 * 
	 * @param account
	 *            账号
	 * @param password
	 *            密码
	 * @return 是否登陆成功
	 */
	public boolean login(String account, String password,boolean isconn);

	/**
	 * 停止登陆。
	 */
	public void stoplogin();

	/**
	 * 注销登陆
	 * 
	 * @return 是否成功
	 */
	public boolean logout();

	/**
	 * 是否已经连接上服务器
	 * 
	 * @return
	 */
	public boolean isAuthenticated();



	/**
	 * 设置当前在线状态
	 */
	public void setStatusFromConfig();

	/**
	 * 发送消息
	 * 
	 * @param user
	 * @param message
	 */
	public void sendMessage(String user, String message);

	/**
	 * 向服务器发送心跳包，保持长连接 通过一个闹钟控制，定时发送，
	 */
	public void sendServerPing();

	/**
	 * 从jid中获取好友名
	 * 
	 * @param jid
	 * @return
	 */
	public String getNameForJID(String jid);
}
