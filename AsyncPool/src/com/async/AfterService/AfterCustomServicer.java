package com.async.AfterService;

import com.async.Log.Log;
import com.async.msg.Result;
import com.async.msg.ResultObsever;

/**
 * ��������ɺ�������������������
 * 
 * @author ml
 *
 */
public class AfterCustomServicer implements  Runnable{
	
	private boolean isRuning;
	private int index;
	
 	

	public AfterCustomServicer(int index) {
		this.index = index;
	}

	/**
	 * ����
	 */
	private ResultObsever resultObsever;

	/**
	 * ���
 	 */
	private Result  result;
	
	
	public void setResultObsever(
			ResultObsever resultObsever) {
		this.resultObsever = resultObsever;
	}
	public void setResult(Result result) {
		this.result = result;
	}

	public synchronized boolean isRuning() {
		
 			return isRuning;
 	}

	public synchronized void setRuning(boolean isRuning) {
 			this.isRuning = isRuning;

			if (isRuning) {
				Log.e(" �ۺ�ͷ� " + index + " notify");

				notify();
			}
 	}

	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			synchronized (this) {
			if (!isRuning()) {
				
					try {
 						Log.e( "�ۺ�ͷ� "+ index + " wait");

 						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			} else {
				Log.e( "�ۺ�ͷ� "+ index + " run");

				resultObsever.setResult(result.getObj());
				
				setRuning(false);
			}
		 }

		}
	}
 

}
