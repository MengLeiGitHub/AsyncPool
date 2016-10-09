##AsyncPool

�첽�̳߳أ����̳߳�Ϊ��ҵ���֪ͨ����̳߳ز����ˣ�ִ�к�֪ͨ���顣

*  1.ִ��������нṹ��
   * �����̣߳������̵߳Ĵ������Լ� task�Ļ�ȡ�ͷַ�
   * ִ���̣߳��������ҵ��Ĵ��� 
   * ��ִ�н�����͸� ֪ͨ�߳�
*  2.֪ͨ�߳� �ṹ�� ִ�ж��нṹ��ͬ,ֻ���ڲ��ֵ�ҵ������ϲ�һ����

�ÿ��ʵ������������ݷ��صĽ��ͬʱΪ�˸��õ���չ�ԣ���ʵ�ֽṹ���������������û������Զ���������� �ڸÿ�����û������Զ���ʵ�ֻ��ڶ��̵߳ĸ����ҵ��ֻ��Ҫʵ�ֿ�����������ӿں� ����۲� �ӿڼ��ɡ�


####����

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

#####��ʼ��
```java

SyncPoolExecutor.newFixedThreadPool(5, 2,null).isDebug(false);
//����������Ϊ��������� ��һ�㲻��Ҫʵ��

```

#####ʹ�÷���

��������

```java

    	LinkedList<CustomMessage > list=new LinkedList<CustomMessage >();
		 
		long  time1=System.currentTimeMillis();
		  
		for(int i=0;i<1000;i++){
			
			TaskWork taskWork=new TaskWork (i){

				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "���ؽ��"+objects[0] +" isStop="+objects[1];
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
������
```java

    	
		for(int i=0;i<23000000;i++){
			
  			TaskWork t=new TaskWork(i) {
				
				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					//System.out.println("TaskInterface ="	+Thread.currentThread().getClass().getSimpleName());

					return "���� ���� "+objects[0] +" isStop="+objects[1];

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

######�ϴ� �ļ� (���߳�)



```java


       LinkedList<CustomMessage > list=new LinkedList<CustomMessage >();
		 
		long  time1=System.currentTimeMillis();
		  
		for(int i=0;i<1000;i++){
			
			TaskWork taskWork=new TaskWork (i){

				@Override
				public Object run(Object... objects) {
					// TODO Auto-generated method stub
					return "���ؽ��"+objects[0] +" isStop="+objects[1];
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









##�����ⷴ��
��ʹ�������κ����⣬��ӭ�������ң�������������ϵ��ʽ���ҽ���

* �ʼ�:menglei0207@sina.cn
* QQȺ: 366802936
* github:https://github.com/MengLeiGitHub/)