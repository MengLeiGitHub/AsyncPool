package com.async.pool.AfterService;

import java.util.LinkedList;

import com.async.pool.BeforeService.Observer;
import com.async.pool.msg.Result;

public class AfterDataManager {

	private LinkedList<Result> resultQuece = null;

	private static AfterDataManager afterDataManager;

 	
	public  static AfterDataManager Call(   ) {

		if (afterDataManager == null)
			afterDataManager = new AfterDataManager();

		return afterDataManager;
	}

	public   AfterDataManager(){
		resultQuece = new LinkedList<Result>();
 	}
	public  void notice(Result result) {
		// TODO Auto-generated method stub
		if(result!=null&&result.getObj()!=null)//��ֹ ����ȡ��֮����ӵ� ��ɶ�����
		resultQuece.add(result);
 		
	}
	public LinkedList<Result> getResultQuece(){
		return resultQuece;
	}
	
	public void  remove(Result result){
		resultQuece.remove(result);
	}
 
	
	
}
