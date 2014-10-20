package cn.com.shanda.aesop.server;


import java.rmi.Naming;

public class Warn implements Runnable {
	
	/*
	 *  主服务器方法，通知其他资源服务器，在动态ip列表中，移除退出的服务器的ip地址
	 */
	
	private String hostip;
	private String othersip;
	
	public Warn(String hostip, String othersip) {
		this.hostip=hostip;
		this.othersip=othersip;
	}

	@Override
	public void run() {
		
		System.out.println("通知"+hostip+"删除"+othersip);
		
		try {
			
			Registered registry=(Registered)Naming.lookup("rmi://" +hostip + "/registryimpl");
			
			if(registry.removeip(othersip))
				
				System.out.println("向"+hostip+"删除"+othersip+"成功！");
			
		} catch (Exception e) {
			
			System.out.println("向"+hostip+"删除"+othersip+"失败！");
			
		}
	}

}
