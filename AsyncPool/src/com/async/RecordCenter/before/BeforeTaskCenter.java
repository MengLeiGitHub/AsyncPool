package com.async.RecordCenter.before;

import java.util.LinkedList;

import com.async.msg.CustomMessage;


/**
 * 消息中心，管理问题
 * 
 * @author ml
 *
 */
public class BeforeTaskCenter implements BeforeTaskHanlder {
	private  LinkedList<CustomMessage> customMessagesQueue;
 	private  static BeforeTaskCenter  beforeTaskCenter;
	
 	
	
	public  static synchronized   BeforeTaskHanlder   handler(){
		if(beforeTaskCenter==null){
			beforeTaskCenter=new BeforeTaskCenter();
		}
		
 		return beforeTaskCenter;
	}
	
	public  BeforeTaskCenter(){
		customMessagesQueue=new LinkedList<CustomMessage>();
	}
		
	
	public void add(CustomMessage msg) {
		// TODO Auto-generated method stub
		customMessagesQueue.add(msg);
	}

	public void delete(CustomMessage msg) {
		// TODO Auto-generated method stub
		if(customMessagesQueue.contains(msg))
		customMessagesQueue.remove(msg);
		else{
			if(msg.getTask().isRunning()){
				msg.getTask().stop();
			}
		}
	}

	public CustomMessage getMsg() {
		// TODO Auto-generated method stub
		return customMessagesQueue.removeFirst();
	}

	public int BeforeSize() {
		// TODO Auto-generated method stub
		return customMessagesQueue.size();
	}

	public void add(LinkedList<? extends CustomMessage> msg) {
		// TODO Auto-generated method stub
		customMessagesQueue.addAll(msg);
	}

}
