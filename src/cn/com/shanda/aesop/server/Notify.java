package cn.com.shanda.aesop.server;

import java.rmi.Naming;

public class Notify implements Runnable {
	
	/*
	 *  资源服务器上线互相通知
	 */
	
	private String hostip;				//本机ip地址
	private String othersip;			//被添加的资源服务器ip地址
	
	public Notify(String hostip, String othersip) {
		this.hostip=hostip;
		this.othersip=othersip;
	}

	@Override
	public void run() {
		
		System.out.println("通知"+hostip+"添加"+othersip+"至本地动态ip列表");
		
		try {
			
			Registered registry=(Registered)Naming.lookup("rmi://" +hostip + "/registryimpl");
						
			if(registry.addip(othersip)){
				
				System.out.println("向"+hostip+"添加"+othersip+"成功！");
				
			}
			
		} catch (Exception e) {
			
			System.out.println("向"+hostip+"添加"+othersip+"失败！");
			
		}
	}

}
