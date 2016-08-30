package com.async.msg;

import com.async.ConstructionCenter.TaskInterface;
import com.async.ConstructionCenter.TaskWork;

/**
 * 用户的消息
 * 
 * @author m
 * @param <T>
 * 
 */
public class CustomMessageBuilder {
	
	private int num;
	
	public static CustomMessageBuilder customMessageBuilder;

	public synchronized static CustomMessageBuilder Call() {

		if (customMessageBuilder == null)
			customMessageBuilder = new CustomMessageBuilder();
		return customMessageBuilder;
	}

	public CustomMessage builder(TaskInterface taskInterface,
			ResultObsever resultObsever) {
		
		TaskWork taskWork = new TaskWork(taskInterface);
		CustomMessage customMessage = new CustomMessage(taskWork);
		customMessage.setObj(resultObsever);
		customMessage.setMid(num++);
		return customMessage;

	}

}
