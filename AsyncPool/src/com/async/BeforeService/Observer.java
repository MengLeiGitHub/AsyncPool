package com.async.BeforeService;

import java.util.LinkedList;


public interface Observer<T> {

	public void  Observe(T msg);
	
	public void  Observe(LinkedList<T> listmsg);
}
