package com.async.RecordCenter.before;

import java.util.LinkedList;

import com.async.msg.CustomMessage;


/**
 * ��Ϣ�ľ�����������ˣ�������Ϣ�Ĵ浵����  ��Ϣ���ͳ���
 * 
 * @author ML
 *
 */
public class BeforeTaskManager implements BeforeTaskHanlder{


	private static BeforeTaskManager  beforeTaskManager;
	
	private  BeforeTaskHanlder  beforeTaskHanlder;
	
	public  static synchronized  BeforeTaskManager   Call(){
		if(beforeTaskManager==null){
			beforeTaskManager=new BeforeTaskManager();
		}
		
		return beforeTaskManager;
	}
	
	public  void  setBeforeTaskHanlder(BeforeTaskHanlder  beforeTaskHanlder){
		this.beforeTaskHanlder=beforeTaskHanlder;
	}
	
	
	

	public void add(CustomMessage msg) {
		// TODO Auto-generated method stub
		beforeTaskHanlder.add(msg);
	}

	public void delete(CustomMessage msg) {
		// TODO Auto-generated method stub
		beforeTaskHanlder.delete(msg);
	}

	public CustomMessage getMsg() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.getMsg();
	}

	public int BeforeSize() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.BeforeSize();
	}

	public void add(LinkedList<? extends CustomMessage> msg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
