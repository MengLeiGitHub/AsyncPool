package com.async.pool.BeforeService;

import java.util.LinkedList;

import com.async.pool.ConstructionCenter.TaskPriority;
import com.async.pool.RecordCenter.RecordCenterHandler;
import com.async.pool.RecordCenter.after.AfterTaskHandler;
import com.async.pool.msg.CustomMessage;
import com.async.pool.msg.MsgFilter;

/**
 * �ͻ����������쵼������Customer����������Ϣ ��������Ϣ
 * ���˺󣬽���Ч����Ϣ���͵�task����Ա�����б��� 
 * @author ml
 *
 */
public class BeforeServiceManager implements Observer<CustomMessage> {	
	private    int msgNums;

	MsgFilter  msgFilter;
	
	RecordCenterHandler  recordCenterHandler;
	
	private static BeforeServiceManager  beforeServiceManager;
	
	public  static synchronized BeforeServiceManager   Call(){
		if(beforeServiceManager==null){
			beforeServiceManager=new BeforeServiceManager();
		}
		
		return beforeServiceManager;
	}
	
	 public  BeforeServiceManager  addMsgFilter(MsgFilter  msgFilter){
		 this.msgFilter=msgFilter;
		 return this;
	 }
	 
	 
	 public BeforeServiceManager registerAfterTaskObsever(AfterTaskHandler afterTaskHandler) {
		return  this;
	 }
	
	 public BeforeServiceManager  registerRecordCenterHandlerObsever(RecordCenterHandler recordCenterHandler) {
		this.recordCenterHandler = recordCenterHandler;
		return this;
	}
	 
	 
	/**
	 * �۲����� ������Ϣ ���Զ����÷���
	 */
	public void Observe(CustomMessage msg) {
		// TODO Auto-generated method stub
 		msg=msgFilter(msg);//��Ϣ����
 		noticeAfterManager();//֪ͨ�Ժ����һ�� �ۺ���񣬽��б���
 		sendMsgToMsgManager(msg);//����Ϣ���͵�����Ա ���б���
 	}


	private void noticeAfterManager() {
		// TODO Auto-generated method stub
		
	}
 
	private CustomMessage msgFilter(CustomMessage customMessage) {
		// TODO Auto-generated method stub
		if(msgFilter!=null){
			customMessage=	msgFilter.isQualified(customMessage);
		}
		return customMessage;
		
	}

	private void sendMsgToMsgManager(CustomMessage customMessage) {
		// TODO Auto-generated method stub
		  recordCenterHandler.add(customMessage);
  	}

	public void Observe(LinkedList<CustomMessage> listmsg) {
		// TODO Auto-generated method stub
		for(CustomMessage customMessage:listmsg){
 			Observe(customMessage);
		}
 	}

	 
	 
	
	
}
