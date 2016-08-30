package com.async;

import java.util.LinkedList;

import com.async.AfterService.AfterServiceManager;
import com.async.BeforeService.BeforeCustomerService;
import com.async.BeforeService.BeforeServiceManager;
import com.async.ConstructionCenter.Captain;
import com.async.ConstructionCenter.TaskInterface;
import com.async.ConstructionCenter.TaskLife;
import com.async.ConstructionCenter.TaskWork;
import com.async.ConstructionCenter.WorkTaskInterface;
import com.async.Log.Log;
import com.async.RecordCenter.RecordCenterHandler;
import com.async.RecordCenter.RecordManager;
import com.async.RecordCenter.after.AfterTaskCenter;
import com.async.RecordCenter.after.AfterTaskManager;
import com.async.RecordCenter.before.BeforeTaskCenter;
import com.async.RecordCenter.before.BeforeTaskManager;
import com.async.msg.CustomMessage;
import com.async.msg.DefaultMsgFilter;
import com.async.msg.MsgFilter;
import com.async.msg.ResultObsever;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//run3();
		
		System.out.println(Integer.MAX_VALUE+"");
		
	}

	
	static	void run1(){
		/**
		 * 开启 备案消息中心
		 */
		BeforeTaskManager.Call().setBeforeTaskHanlder(BeforeTaskCenter.handler());
		
		AfterTaskManager.Call().setAfterTaskHandler(AfterTaskCenter.Call());
		
		
		/**
		 * 开启售后服务
		 */
		AfterServiceManager.Call(2).setAfterTaskHandler(RecordManager.Call());
		
 		
		/**
		 * 消息过滤器
		 */
		MsgFilter filter=new DefaultMsgFilter();

		

		/**
		 * 开启售前客服管理
		 */
		BeforeServiceManager beforeServiceManager=	BeforeServiceManager.Call().addMsgFilter(filter);
		
		beforeServiceManager.registerAfterTaskObsever(AfterTaskManager.Call());
		
		beforeServiceManager.registerRecordCenterHandlerObsever(RecordManager.Call());
		
		BeforeCustomerService.call().registerObserver(beforeServiceManager);
		
		
		
		LinkedList<CustomMessage > list=new LinkedList<CustomMessage >();
		for(int i=0;i<20;i++){
			TaskWork taskWork=new TaskWork (i);
			taskWork.setWork(new TaskInterface() {
				
				 

				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "我想草拟"+objects[0] +" isStop="+objects[1];

 				}

				@Override
				public void registerObsever(TaskLife tasklife) {
					// TODO Auto-generated method stub
					
				}
			});
			CustomMessage  customMessage=new CustomMessage(taskWork);
			customMessage.setObj(new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					Log.e(result);
				}
			});
 			list.add(customMessage);
		}
	
		BeforeCustomerService.call().sendMsgs(list);
		/**
		 * 开启  工作队
		 */
 		Captain.Call(5);
 		
		list.clear();
		for(int i=0;i<20;i++){
			
			TaskWork taskWork=new TaskWork (i);
			taskWork.setWork(new TaskInterface() {
				
				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "我想草拟"+objects[0] +" isStop="+objects[1];

 				}

				@Override
				public void registerObsever(TaskLife tasklife) {
					// TODO Auto-generated method stub
					
				}
			});
			CustomMessage  customMessage=new CustomMessage(taskWork);
 			customMessage.setObj(new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
				}
			});
 			list.add(customMessage);
		}
	
  		//BeforeCustomerService.call().sendMsgs(list);
		SyncPoolExecutor.newFixedThreadPool(4, 2, null).execute(list);
		
	}
	
	static void  run2(){
		SyncPoolExecutor.newFixedThreadPool(5, 2,null).isDebug(false);
		
		for(int i=0;i<23000000;i++){
			
			
		
			
 			TaskInterface t=new TaskInterface() {
				
				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					//System.out.println("TaskInterface ="	+Thread.currentThread().getClass().getSimpleName());

					return "我是 任务 "+objects[0] +" isStop="+objects[1];

 				}

				@Override
				public void registerObsever(TaskLife tasklife) {
					// TODO Auto-generated method stub
					
				}
			};
			 
			ResultObsever<String> r=	new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
					//System.out.println("ResultObsever ="	+Thread.currentThread().getClass().getSimpleName());
					
				}
			};
			SyncPoolExecutor.execute(t, r); 
		}
	}
	
	static void  run3(){
	
		
		LinkedList<CustomMessage > list=new LinkedList<CustomMessage >();
		 
		long  time1=System.currentTimeMillis();
		  
		for(int i=0;i<22330;i++){
			
			TaskWork taskWork=new TaskWork (i);
			taskWork.setWork(new TaskInterface() {
				
				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "我想草拟"+objects[0] +" isStop="+objects[1];

 				}

				@Override
				public void registerObsever(TaskLife tasklife) {
					// TODO Auto-generated method stub
					
				}
			});
			CustomMessage  customMessage=new CustomMessage(taskWork);
 			customMessage.setObj(new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
				}
			});
 			list.add(customMessage);
		}
		long  time2=System.currentTimeMillis();
		System.out.println(time1-time2);
		
  		//BeforeCustomerService.call().sendMsgs(list);
		SyncPoolExecutor.newFixedThreadPool(2, 1, null).isDebug(false).execute(list);
	
		long  time3=System.currentTimeMillis();
		System.out.println(time3-time2);
	}
	
}
