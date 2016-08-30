package com.async;

import java.util.LinkedList;

import com.async.AfterService.AfterServiceManager;
import com.async.BeforeService.BeforeCustomerService;
import com.async.BeforeService.BeforeServiceManager;
import com.async.ConstructionCenter.Captain;
import com.async.ConstructionCenter.TaskInterface;
import com.async.RecordCenter.RecordManager;
import com.async.RecordCenter.after.AfterTaskCenter;
import com.async.RecordCenter.after.AfterTaskManager;
import com.async.RecordCenter.before.BeforeTaskCenter;
import com.async.RecordCenter.before.BeforeTaskManager;
import com.async.msg.CustomMessage;
import com.async.msg.CustomMessageBuilder;
import com.async.msg.DefaultMsgFilter;
import com.async.msg.MsgFilter;
import com.async.msg.ResultObsever;

public class SyncPoolExecutor {

	private static SyncPoolExecutor syncPoolExecutor;

	private static final int NOTICE_NUMS=2;
	
	private static final int WORKERS_NUMS=3;

	
	
	public SyncPoolExecutor(int WorkNum) {
		// TODO Auto-generated constructor stub
 		SyncPoolExecutor(WorkNum,0,null);
	}

	public SyncPoolExecutor(int WorkNum,int noticeNum) {
		// TODO Auto-generated constructor stub
 		SyncPoolExecutor(WorkNum,noticeNum,null);
	}
	public SyncPoolExecutor  isDebug(boolean isdebug) {
		// TODO Auto-generated constructor stub
		 com.async.Log.Log.setDebug(isdebug);
 		 return this;
	}
	
	
	public SyncPoolExecutor(int WorkNum,int noticeNum,MsgFilter msgFilter) {
		// TODO Auto-generated constructor stub
 		SyncPoolExecutor(WorkNum,noticeNum,msgFilter);
	}
	public void SyncPoolExecutor(int workNum, int noticeNum, MsgFilter msgFilter) {
		// TODO Auto-generated constructor stub

		/**
		 * ���� ������Ϣ����
		 */
		BeforeTaskManager.Call().setBeforeTaskHanlder(
				BeforeTaskCenter.handler());

		AfterTaskManager.Call().setAfterTaskHandler(AfterTaskCenter.Call());

		
		if(noticeNum<=0)noticeNum=NOTICE_NUMS;
		
		/**
		 * �����ۺ����
		 */
		AfterServiceManager.Call(noticeNum).setAfterTaskHandler(RecordManager.Call());

		/**
		 * ��Ϣ������
		 */

		MsgFilter filter = null;
		if (msgFilter == null) {
			filter = new DefaultMsgFilter();
		}

		/**
		 * ������ǰ�ͷ�����
		 */
		BeforeServiceManager beforeServiceManager = BeforeServiceManager.Call()
				.addMsgFilter(filter);

		beforeServiceManager.registerAfterTaskObsever(AfterTaskManager.Call());

		beforeServiceManager.registerRecordCenterHandlerObsever(RecordManager
				.Call());

		BeforeCustomerService.call().registerObserver(beforeServiceManager);

		if(workNum<=0)workNum=WORKERS_NUMS;
		
		Captain.Call(workNum);

	}

	public static SyncPoolExecutor newFixedThreadPool(int WorkNum,
			int noticeNum, MsgFilter msgFilter) {
		if (WorkNum <= 0)
			try {
				throw new Exception("WorkNum should be >=0");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (noticeNum <= 0)
			try {
				throw new Exception("noticeNum should be >=0");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		if (syncPoolExecutor == null)
			syncPoolExecutor = new SyncPoolExecutor(WorkNum, noticeNum,
					msgFilter);

		return syncPoolExecutor;

	}
	public static SyncPoolExecutor newFixedThreadPool(int WorkNum, MsgFilter msgFilter) {
		if (WorkNum <= 0)
			try {
				throw new Exception("WorkNum should be >=0");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 

		if (syncPoolExecutor == null)
			syncPoolExecutor = new SyncPoolExecutor(WorkNum,0,
					msgFilter);

		return syncPoolExecutor;

	}
	public static SyncPoolExecutor newFixedThreadPool( MsgFilter msgFilter) {
		 
 

		if (syncPoolExecutor == null)
			syncPoolExecutor = new SyncPoolExecutor(0,0,
					msgFilter);

		return syncPoolExecutor;

	}
	 
	public static  void  execute(CustomMessage customMessage){
		
		BeforeCustomerService.call().send(customMessage);
		
  	}
	
public static  void  execute(LinkedList<CustomMessage> customMessage){
		
		BeforeCustomerService.call().sendMsgs(customMessage);
		
  	}
	
	
	
	
	public static  void  remove(CustomMessage customMessage){
		
		RecordManager.Call().delete(customMessage);
		
  	}
	public static  void  cancle(CustomMessage customMessage){
		
		RecordManager.Call().delete(customMessage);
		
  	}
	public static  int  execute(TaskInterface taskInterface,ResultObsever resultObsever){
 		CustomMessage customMessage=CustomMessageBuilder.Call().builder(taskInterface, resultObsever);
 		BeforeCustomerService.call().send(customMessage);
 		return customMessage.getMid();
  	}
	public static  void  cancle(int mid){
 		CustomMessage customMessage=new CustomMessage(null);
 		customMessage.setMid(mid);
 		remove(customMessage);
   	}

	
	
	
	
}
