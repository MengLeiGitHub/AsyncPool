package com.async.BeforeService;

import java.util.LinkedList;

import com.async.msg.CustomMessage;


/**
 * ���������Ͻ����ͷ����ģ������������
 * @author ml
 *
 */
public class BeforeCustomerService   {
 	
	private  CustomMessage   customMessage;
	private  Observer<CustomMessage>     observer;
	
	private static BeforeCustomerService  beforeCustomerService;
	
	public  synchronized static  BeforeCustomerService call(){
		if(beforeCustomerService==null){
			beforeCustomerService=new BeforeCustomerService();
		}
		
		return beforeCustomerService;
	}
	
	public  void  registerObserver(Observer<CustomMessage>     observer){
		this.observer=observer;
	}
	
	
	/**
	 * �ͻ��������͸��ͷ���Ա
	 * @param msg
	 */
	public void  send(CustomMessage  msg){
		this.customMessage=msg;
  		//֪ͨ�۲��ߣ����ͷ������쵼��
		notifyObserver();
 	}
	
	public  void  sendMsgs(LinkedList<CustomMessage  > msglist){
		observer.Observe(msglist);
	}
	
	
	
	
	
	
	private void notifyObserver() {
		// TODO Auto-generated method stub
		observer.Observe(customMessage);
	}
 	
	
	
	public CustomMessage getCustomMessage() {
		return customMessage;
	}

	 
}
