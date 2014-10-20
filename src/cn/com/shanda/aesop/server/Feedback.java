package cn.com.shanda.aesop.server;

import java.rmi.Naming;


public class Feedback {
	
	/*
	 *  主服务器方法：主服务器申请被各个资源服务器监听，以防主服务器断线
	 */
	
	private Registered registry;
	
	public void feeback(String ip){
		
		try {
			
			registry=(Registered) Naming.lookup("rmi://" + ip + "/registryimpl");
			
			ListenerImpl listener = new ListenerImpl();
			
			registry.addListener(listener);
			
		} catch (Exception e) {
			
			System.out.println("主服务器被监听时失败");
			
		} 
	}
}
