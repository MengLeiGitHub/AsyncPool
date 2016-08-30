package com.async.BeforeService;

import java.util.LinkedList;

import com.async.msg.CustomMessage;


/**
 * 接受请求，上交到客服中心，任务管理中心
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
	 * 客户将任务发送给客服人员
	 * @param msg
	 */
	public void  send(CustomMessage  msg){
		this.customMessage=msg;
  		//通知观察者，（客服中心领导）
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
