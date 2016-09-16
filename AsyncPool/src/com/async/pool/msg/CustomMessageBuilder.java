package com.async.pool.msg;

import com.async.pool.ConstructionCenter.TaskInterface;
import com.async.pool.ConstructionCenter.TaskWork;

/**
 * �û�����Ϣ
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

	public CustomMessage builder(TaskWork taskWork,
			ResultObsever resultObsever) {
		
 		CustomMessage customMessage = new CustomMessage(taskWork);
		customMessage.setObj(resultObsever);
		customMessage.setMid(num++);
		return customMessage;

	}

}
