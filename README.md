##AsyncPool

异步线程池，该线程池为了业务和通知解耦，线程池采用了，执行和通知两块。

*  1.执行任务队列结构：
   * 管理线程：负责线程的创建，以及 task的获取和分发
   * 执行线程：负责具体业务的处理 
   * 将执行结果发送给 通知线程
*  2.通知线程 结构和 执行队列结构相同,只是在部分的业务代码上不一样。

该框架实现了任务和数据返回的解耦。同时为了更好的扩展性，在实现结构上引入过滤器概念。用户可以自定义过滤器。 在该框架上用户可以自定义实现基于多线程的更多的业务。只需要实现框架里面的任务接口和 结果观察 接口即可。


####接入

* maven

```java
<dependency>
  <groupId>com.ml.asyncpool</groupId>
  <artifactId>asyncpool</artifactId>
  <version>1.0.0</version>
  <type>pom</type>
</dependency>

```
* gradle


```java
compile 'com.ml.asyncpool:asyncpool:1.0.0'

```

#####初始化
```java

SyncPoolExecutor.newFixedThreadPool(5, 2,null).isDebug(false);
//第三个参数为任务过滤器 ，一般不需要实现

```

#####使用方法

批量任务

```java

    	LinkedList<CustomMessage > list=new LinkedList<CustomMessage >();
		 
		long  time1=System.currentTimeMillis();
		  
		for(int i=0;i<1000;i++){
			
			TaskWork taskWork=new TaskWork (i){

				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "返回结果"+objects[0] +" isStop="+objects[1];
				}

				 

				 
				
			};
			 
			CustomMessage  customMessage=new CustomMessage(taskWork);
 			customMessage.setObj(new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
				}
			});
 			customMessage.setMid(i);
 			list.add(customMessage);
		}
		long  time2=System.currentTimeMillis();
		System.out.println(time1-time2);
		
  		//BeforeCustomerService.call().sendMsgs(list);
		SyncPoolExecutor.newFixedThreadPool(5, 1, null).isDebug(true).execute(list);
	
		long  time3=System.currentTimeMillis();
		System.out.println(time3-time2);
	}

```
单任务
```java

    	
		for(int i=0;i<23000000;i++){
			
  			TaskWork t=new TaskWork(i) {
				
				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					//System.out.println("TaskInterface ="	+Thread.currentThread().getClass().getSimpleName());

					return "我是 任务 "+objects[0] +" isStop="+objects[1];

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


```

######上传 文件 (单线程)



```java


       LinkedList<CustomMessage > list=new LinkedList<CustomMessage >();
		 
		long  time1=System.currentTimeMillis();
		  
		for(int i=0;i<1000;i++){
			
			TaskWork taskWork=new TaskWork (i){

				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "返回结果"+objects[0] +" isStop="+objects[1];
				}

				 

				 
				
			};
			 
			CustomMessage  customMessage=new CustomMessage(taskWork);
 			customMessage.setObj(new ResultObsever<String>() {

				public void setResult(String result) {
					// TODO Auto-generated method stub
					System.out.println(result);
				}
			});
 			customMessage.setMid(i);
 			list.add(customMessage);
		}
		long  time2=System.currentTimeMillis();
		System.out.println(time1-time2);
		
  		//BeforeCustomerService.call().sendMsgs(list);
		SyncPoolExecutor.newFixedThreadPool(5, 1, null).isDebug(true).execute(list);
	
		long  time3=System.currentTimeMillis();
		System.out.println(time3-time2);
	}
 

```









##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流
* github:https://github.com/MengLeiGitHub/)
