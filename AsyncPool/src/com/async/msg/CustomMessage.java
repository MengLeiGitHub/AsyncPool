package com.async.msg;

import com.async.ConstructionCenter.TaskWork;

/**
 * �û�����Ϣ
 * @author m
 * @param <T>
 *
 */
public class CustomMessage   {
	/*
	 * ����id
	 */
	private int mid;
	/**
	 * ����
	 */
	private TaskWork   task;  
	
 	public  CustomMessage(TaskWork  task){
		this.task=task;
 	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public TaskWork  getTask() {
		
		return task;
	}

	public void setTask(TaskWork  task) {
		this.task = task;
	}

	private Object obj;
	public void setObj(Object obj) {
		this.obj = obj;
	}
	public Object getObj() {
		return obj;
	}
	 
}
