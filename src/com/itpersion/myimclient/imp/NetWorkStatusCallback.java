package com.itpersion.myimclient.imp;

/**
 * 
 * @ClassName: NetWorkStatusCallback
 * @Description: ����仯�ص�
 * @author �����˱�д
 * @date 2015��10��27�� ����4:30:53
 * 
 */
public interface NetWorkStatusCallback {
	// ��������ʧ��
	public void NetWork_conn_Error();

	// �������ӳɹ�
	public void NetWork_conn_Sucess();
}
