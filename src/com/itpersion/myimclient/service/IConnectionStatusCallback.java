package com.itpersion.myimclient.service;
/**
 * 服务器回调函数
 * @author home
 *
 */
public interface IConnectionStatusCallback {
	/**
	 * 
	 * @param connectedState 连接标识符
	 * @param reason 连接理由。
	 */
	public void connectionStatusChanged(int connectedState, String reason);
}
