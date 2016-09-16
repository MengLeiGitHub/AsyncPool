package com.async.pool.ConstructionCenter;

import com.async.pool.BeforeService.Observer;
import com.async.pool.Log.Log;
import com.async.pool.msg.CustomMessage;
import com.async.pool.msg.Result;

/**
 * ����ľ���ʵʩ�ߣ��������������ϱ����ӳ�����
 * 
 * @author ml
 * 
 */
public class TeamMember implements Runnable {

	private boolean isRuning;
	private int index;

	Observer<Result> observer;

	public TeamMember(int index) {
		this.index = index;
	}

	/**
	 * ����
	 */
	private CustomMessage customMessage;

	public void setCustomMessage(CustomMessage customMessage) {
		this.customMessage = customMessage;
	}

	public synchronized boolean isRuning() {

		return isRuning;
	}

	public synchronized void setRuning(boolean isRuning) {
		this.isRuning = isRuning;

		if (isRuning) {
			Log.e("��Ա" + index + " notify");
			notify();
		}
	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (this) {
				if (!isRuning()) {

					try {
						Log.e("��Ա   " + index + " wait");
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} else {

					if (customMessage.getTask() != null) {
						customMessage.getTask().setRunning(true);
						Object res = customMessage.getTask().startTask();

						observer.Observe(new Result(Integer
								.toString(customMessage.getMid()), res));// ֪ͨ�ӳ�

					}
					setRuning(false);
				}
			}

		}
	}

	public void registerObserver(Observer<Result> observer) {
		// TODO Auto-generated method stub
		this.observer = observer;

	}
}