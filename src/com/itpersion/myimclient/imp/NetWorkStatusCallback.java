package com.itpersion.myimclient.imp;

/**
 * 
 * @ClassName: NetWorkStatusCallback
 * @Description: 网络变化回调
 * @author 机器人编写
 * @date 2015年10月27日 下午4:30:53
 * 
 */
public interface NetWorkStatusCallback {
	// 网络连接失败
	public void NetWork_conn_Error();

	// 网络连接成功
	public void NetWork_conn_Sucess();
}
