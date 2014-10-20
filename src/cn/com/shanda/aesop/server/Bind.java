package cn.com.shanda.aesop.server;


import java.rmi.Naming;


public class Bind {
	
	/*
	 *  开启本机服务，并开启监控线程，等待其他服务器的注册
	 */
	
	public void bind(String ip) {
		
		RegisteredImpl registryimpl;
				
		try {
			
			registryimpl = new RegisteredImpl();
			
			Naming.rebind("rmi://" + ip + "/registryimpl", registryimpl);
			
			System.out.println("RMI服务绑定成功");
						
			Thread thread = new Thread(registryimpl);
			
			thread.start();
			
			
		} catch (Exception e) {
			
			System.out.println("RMI服务绑定失败");
			
		} 
		
	}
}
