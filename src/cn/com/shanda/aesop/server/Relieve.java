package cn.com.shanda.aesop.server;


import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import cn.com.shanda.aesop.init.*;

public class Relieve {
	
	private Registered registry = null;
	private GetHostIp host = new GetHostIp();
	private String hostip = host.hostIp();
	
	public void relieve(){
		
		RemoveRemoteIp reomteremove = new RemoveRemoteIp();
		
		try {
			reomteremove.removeRemoteIp();
		} catch (Exception e2) {
			System.out.println("清空本机动态列表中的主服务器ip地址失败");	
		}
		
		ClearIpList.clear();	
		
		if (!hostip.equals(RemoteIp.remoteip)) {
			try {
				registry = (Registered) Naming.lookup("rmi://"+ RemoteIp.remoteip + "/registryimpl");
				registry.signout(hostip);
				UnicastRemoteObject.unexportObject(Init.regist, true);
				System.out.println("rmi服务器关闭成功");
			} catch (Exception e) {
				System.out.println("退出时，主服务器连接失败，请等待服务器自动检测，本服务器退出成功");
			}
		} 
	}
}
