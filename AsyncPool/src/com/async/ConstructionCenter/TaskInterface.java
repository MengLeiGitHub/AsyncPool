package com.async.ConstructionCenter;

public interface TaskInterface {
	/**
	 * ��һ��������ʾ ��ǰ������±�   �ڶ���������ʾ  ��ǰ�����Ƿ��� ȡ������
	 * @param objects
	 * @return
	 */
	
	public Object run(Object ...objects);
	
	public  void   registerObsever(TaskLife tasklife);
	
	
}
