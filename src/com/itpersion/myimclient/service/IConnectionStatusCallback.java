package com.itpersion.myimclient.service;
/**
 * �������ص�����
 * @author home
 *
 */
public interface IConnectionStatusCallback {
	/**
	 * 
	 * @param connectedState ���ӱ�ʶ��
	 * @param reason �������ɡ�
	 */
	public void connectionStatusChanged(int connectedState, String reason);
}
