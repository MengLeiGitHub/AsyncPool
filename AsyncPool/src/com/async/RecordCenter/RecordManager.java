package com.async.RecordCenter;

import java.util.LinkedList;

import com.async.RecordCenter.after.AfterTaskHandler;
import com.async.RecordCenter.after.AfterTaskManager;
import com.async.RecordCenter.before.BeforeTaskHanlder;
import com.async.RecordCenter.before.BeforeTaskManager;
import com.async.msg.CustomMessage;
import com.async.msg.ResultObsever;


/**
 * 备案中心  领导
 * 
 * @author ml
 *
 */

public class RecordManager  implements RecordCenterHandler {

    private static RecordManager recordManager;
	
	AfterTaskHandler afterTaskHandler;
	BeforeTaskHanlder beforeTaskHanlder;
	public synchronized static RecordManager Call() {

		if (recordManager == null)
			recordManager = new RecordManager();

		return recordManager;
	}
 	
	 public RecordManager(){
		 afterTaskHandler=AfterTaskManager.Call();
		 beforeTaskHanlder=BeforeTaskManager.Call();
	 }

	public void add(CustomMessage msg) {
		// TODO Auto-generated method stub
		beforeTaskHanlder.add(msg);
		afterTaskHandler.add(msg.getMid()+"", (ResultObsever<?>)msg.getObj());
	}

	public void add(LinkedList<? extends CustomMessage> msg) {
		// TODO Auto-generated method stub
		for(CustomMessage c:msg){
			add(c);
		}
	}

	public void delete(CustomMessage msg) {
		// TODO Auto-generated method stub
		beforeTaskHanlder.delete(msg);
		afterTaskHandler.delete(Integer.toString(msg.getMid()));
	}

	public CustomMessage getMsg() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.getMsg();
	}

	
	
	
	
	public void add(String id, ResultObsever<?> msg) {
		// TODO Auto-generated method stub
		afterTaskHandler.add(id, msg);
	}

	public void delete(String mid) {
		// TODO Auto-generated method stub
		afterTaskHandler.delete(mid);
	}

	public ResultObsever<?> getObsever(String mid) {
		// TODO Auto-generated method stub
		return afterTaskHandler.getObsever(mid);
	}

	public int AfterSize() {
		// TODO Auto-generated method stub
		return afterTaskHandler.AfterSize();
	}
	public int BeforeSize() {
		// TODO Auto-generated method stub
		return beforeTaskHanlder.BeforeSize();
	}
}
