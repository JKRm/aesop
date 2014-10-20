package cn.com.shanda.aesop.server;


import java.rmi.Naming;

public class Lookingup {
	
	/*
	 * 向主服务器，并允许主服务器进行监控
	 */
	
	private Registered registry;
	private GetHostIp host = new GetHostIp();
	private String hostip = host.hostIp();
	
	public Registered lookingup(String ip){
		
		try {
			
			registry = (Registered) Naming.lookup("rmi://" + ip + "/registryimpl");
			
			if(!ip.equals(hostip)){
			
				ListenerImpl listener = new ListenerImpl();
			
				registry.addListener(listener);
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
			System.out.println("主服务器添加监听时失败");
			
		}
		
		return registry;			
	}
}
