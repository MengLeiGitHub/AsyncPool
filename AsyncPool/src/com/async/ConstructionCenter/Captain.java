package com.async.ConstructionCenter;

import java.util.LinkedList;

import com.async.AfterService.AfterDataManager;
import com.async.AfterService.AfterServiceManager;
import com.async.BeforeService.Observer;
import com.async.Log.Log;
import com.async.RecordCenter.RecordCenterHandler;
import com.async.RecordCenter.RecordManager;
import com.async.RecordCenter.after.AfterTaskManager;
import com.async.RecordCenter.before.BeforeTaskManager;
import com.async.msg.CustomMessage;
import com.async.msg.Result;

 

/**
 * 队长，负责的队员的准备工作的落实，和任务的具体派发，
 * 任务完成的情况，交付备案中心
 * 
 * @author ml
 *
 */
public class Captain implements Observer<Result> {

	private Thread  handler;
	private RecordCenterHandler recordCenterHandler;
	
	/*
	 * 工作队列
	 */
	private LinkedList<TeamMember> workQuece = null;
	
	public	static Captain  captain;
	
	public  synchronized static  Captain  Call(int memberNums){
		if(captain==null)captain=new Captain(memberNums);
		return captain;
	}
	
	
	public  Captain(int work_thead_num){
		recordCenterHandler=RecordManager.Call();
		workQuece=new LinkedList<TeamMember>();
		
 		creatWorkThead(work_thead_num);
		startWorkThead();
	}
	
	private void creatWorkThead(int work_thead_num) {
		// TODO Auto-generated method stub
 		while (work_thead_num-- > 0) {
			TeamMember teamworker = new TeamMember(work_thead_num);
			teamworker.registerObserver(this);
			Thread thread = new Thread(teamworker);
			workQuece.add(teamworker);
			thread.start();
		}
	}
	
	
	
	
	
	
	
	private void startWorkThead() {
		// TODO Auto-generated method stub
		handler = new Thread(new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					// synchronized (this) {
					if (recordCenterHandler.BeforeSize() == 0) {
						synchronized (this) {
							try {
								Log.e(" 施工队 队长  等待 ");
								
								wait(1000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					} else {
						while (recordCenterHandler.BeforeSize() != 0) {
							
							int workSize = workQuece.size();
							for (int worknum = 0; worknum < workSize; worknum++) {

								TeamMember teamworker = workQuece
										.get(worknum);

								synchronized (teamworker) {
									if (!teamworker.isRuning()
											&& recordCenterHandler.BeforeSize() > 0) {
										CustomMessage custommsg = recordCenterHandler.getMsg();
										
										teamworker.setCustomMessage(custommsg); 
										
										teamworker.setRuning(true);
										
										teamworker.notify();
									}
								}

							}

						}
					}
				}

			}
			// }

		});
		handler.start();
	}


	public synchronized void Observe(Result result) {
		// TODO Auto-generated method stub
		 	
		 AfterDataManager.Call().getResultQuece().add(result);
  		 
		 synchronized ( AfterDataManager.Call().getResultQuece()) {
			 try {
					AfterDataManager.Call().getResultQuece().wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		 }
		 
		
 		
		
	}


	public synchronized void Observe(LinkedList<Result> listmsg) {
		// TODO Auto-generated method stub
		
	}


	 
	
}
