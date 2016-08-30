package com.async.Log;

public class Log {

	static  boolean  isWrite=false;
	public static void  e(String msg){
		if(isWrite)
		System.out.println(msg);;
	}
	public static  void  setDebug(boolean is){
		isWrite=is;
	}
}
