package com.async.ConstructionCenter;


public  class TaskWork  implements  WorkTaskInterface  {
	
	private  int  index;
	
	boolean  isStop;
	
	int  priority;
	boolean  isRunning;
	 
	TaskInterface work; 
	 
	public  TaskWork( int  index){
		
		this.index=index;
		
	}
	public  TaskWork( TaskInterface work){
		
		this.work=work;
		this.work.registerObsever(this);
		
	}

	public void stop() {
		// TODO Auto-generated method stub
		isStop=true;
	}

	public void start() {
		// TODO Auto-generated method stub
		isStop=false;
	}

	public void remove() {
		// TODO Auto-generated method stub
		isStop=true;

	}

	public Object startTask() {
		// TODO Auto-generated method stub
 		if(isStop)return null;
  		 
 		Object obj=work.run(index ,isStop);
 		
 		
  		return     obj;
	}

	public void setTaskPriority(int priority) {
		// TODO Auto-generated method stub
		this.priority=priority;
	}

	public int getTaskPriority() {
		// TODO Auto-generated method stub
		return priority;
	}

	public boolean isRunning() {
		// TODO Auto-generated method stub
		return isRunning;
	}

	public void setRunning(boolean isruning) {
		// TODO Auto-generated method stub
		this.isRunning=isruning;
		this.isStop=false;
	}

 	public void setWork(TaskInterface work) {
		this.work = work;
	}
	 public void setIndex(int index) {
		this.index = index;
	}
}
