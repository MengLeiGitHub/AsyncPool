package com.async.pool.RecordCenter.before;

import java.util.LinkedList;

import com.async.pool.ConstructionCenter.TaskPriority;
import com.async.pool.msg.CustomMessage;

/**
 * 消息中心，管理问题
 * 
 * @author ml
 * 
 */
public class BeforeTaskCenter implements BeforeTaskHanlder {
	private LinkedList<CustomMessage> customMessagesQueue;

	private LinkedList<CustomMessage> highterMessagesQueue;

	private static BeforeTaskCenter beforeTaskCenter;

	public static synchronized BeforeTaskHanlder handler() {
		if (beforeTaskCenter == null) {
			beforeTaskCenter = new BeforeTaskCenter();
		}

		return beforeTaskCenter;
	}

	public BeforeTaskCenter() {
		customMessagesQueue = new LinkedList<CustomMessage>();
		highterMessagesQueue = new LinkedList<CustomMessage>();
	}

	public void add(CustomMessage msg) {
		// TODO Auto-generated method stub
		if (msg.getTask().getTaskPriority() == TaskPriority.HIGHEST.getValue())
			highterMessagesQueue.addFirst(msg);
		else {
			customMessagesQueue.add(msg);
		}

	}

	public void delete(CustomMessage msg) {
		// TODO Auto-generated method stub
		if (customMessagesQueue.contains(msg))
			customMessagesQueue.remove(msg);
		else if (highterMessagesQueue.contains(msg))
			highterMessagesQueue.remove(msg);

		else {
			if (msg.getTask().isRunning()) {
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
		return customMessagesQueue.size()+highterMessagesQueue.size();
	}

	public void add(LinkedList<? extends CustomMessage> msg) {
		// TODO Auto-generated method stub
		customMessagesQueue.addAll(msg);
	}

	@Override
	public LinkedList<? extends CustomMessage> getALLTask() {
		// TODO Auto-generated method stub
		return customMessagesQueue;
	}

	@Override
	public CustomMessage getHightMsg() {
		// TODO Auto-generated method stub
		if(highterMessagesQueue.size()!=0)
		return highterMessagesQueue.removeFirst();
		else return null;
	}

}
