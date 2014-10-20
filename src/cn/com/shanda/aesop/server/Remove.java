package cn.com.shanda.aesop.server;



import java.rmi.Naming;

import org.dom4j.DocumentException;

public class Remove {
	
	/*
	 *  非主服务器断线时，在本地动态ip列表中移除退出的服务器；
	 *  
	 *  主服务器断线时，开始选举主服务器
	 */
	
	private GetHostIp host = new GetHostIp();
	private String hostip = host.hostIp();
	private Registered registry = null;
	private GetRemoteIp remote = new GetRemoteIp();
	private WtiteRemoteIp write = new WtiteRemoteIp();
	private boolean temp = true;
	private String ip;
	
	public void remvoeip() {
		
		try {
			ReadIpList.read();
		} catch (DocumentException e2) {
			System.out.println("读取IpList时失败");
		}
		
		for(int i=0;i<IpList.list.size();i++)			
			if(IpList.list.get(i).equals(RemoteIp.remoteip))
				temp = false;

		
		if(IpList.list.size()==0||temp==true){
			
			try {
				
				System.out.println("主服务器连接失败，开始选举主服务器……");
				
				RemoveRemoteIp reomteremove = new RemoveRemoteIp();
				reomteremove.removeRemoteIp();
				ClearIpList.clear();
				IpList.list.clear();
				
				RemoteIp.remoteip = remote.getRemote();
			
				Lookingup client = new Lookingup();
				registry=client.lookingup(RemoteIp.remoteip);

				if(registry.loginin(hostip)){
					
					System.out.println(hostip+"加入主机成功"+RemoteIp.remoteip);
					write.write(RemoteIp.remoteip);
					
				}
				
			}catch (Exception e1) {
				
				System.out.println("选举注册发生错误，请维修后重新启动服务器");
				
			}
		}
		
		for(int i=0;i<IpList.list.size();i++){
			
			if(!IpList.list.get(i).equals(hostip)){
				
				ip=IpList.list.get(i);	
				
				try{
					
					registry = (Registered)Naming.lookup("rmi://" + IpList.list.get(i) + "/registryimpl");
					                                                           //如果可以正常连接，则测试下一台资源服务器
					continue;													
					
				}catch(Exception e){
					
					try {
						
						registry=(Registered)Naming.lookup("rmi://" + RemoteIp.remoteip + "/registryimpl");
						
														//非主服务器断线，通知主服务器，做相应处理
						
						registry.signout(IpList.list.get(i));
						
					} catch (Exception e3) {
						
					} 
					
					if(ip.equals(RemoteIp.remoteip)){
						
						try {
						
							System.out.println("主服务器连接失败，开始选举主服务器……");
							
							RemoveRemoteIp reomteremove = new RemoveRemoteIp();
							reomteremove.removeRemoteIp();
							ClearIpList.clear();
							IpList.list.clear();
							
							RemoteIp.remoteip = remote.getRemote();
						
							Lookingup client = new Lookingup();
							registry=client.lookingup(RemoteIp.remoteip);
	
							if(registry.loginin(hostip)){
								
								System.out.println(hostip+"加入主机成功"+RemoteIp.remoteip);
								write.write(RemoteIp.remoteip);
								
							}
							
						}catch (Exception e1) {
							
							System.out.println("选举注册发生错误，请维修后重新启动服务器");
							
						}
					}
					
				}
			}
		}
	}

}
