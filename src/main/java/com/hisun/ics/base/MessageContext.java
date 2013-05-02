package com.hisun.ics.base;

public class MessageContext {
	private DataContext req = new DataContext();
	private DataContext rsp = new DataContext(); 
	
	public DataContext getReqDataContext() {
		return req;
	}

	public DataContext getRspDataContext() {
		return rsp;
	}
}
