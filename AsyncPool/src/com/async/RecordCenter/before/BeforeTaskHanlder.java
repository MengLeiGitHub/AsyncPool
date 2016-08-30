package com.async.RecordCenter.before;

import java.util.LinkedList;

import com.async.msg.CustomMessage;

public interface BeforeTaskHanlder {

	public  void  add(CustomMessage msg);
	public  void  add(LinkedList<? extends CustomMessage> msg);

	
	public  void  delete(CustomMessage msg);

	public  CustomMessage  getMsg();

	public  int   BeforeSize();
	
}
