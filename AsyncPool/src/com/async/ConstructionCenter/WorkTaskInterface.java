package com.async.ConstructionCenter;

/**
 * ���������
 * 
 * @author ml
 * 
 */

public interface WorkTaskInterface extends TaskLife{

	public Object startTask();

	/**
	 * ������������ȼ�
	 * @param priority
	 */
	public void setTaskPriority(int priority);

	/**
	 * ��ȡ��������ȼ�
	 * @return
	 */
	public int getTaskPriority();

	/**
	 * ����Ľ���
	 * @return
	 */
	public boolean isRunning();

	/**
	 * ��������Ľ���
	 * @param isruning
	 */
	public void setRunning(boolean isruning);

	
}
