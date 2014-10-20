package cn.com.shanda.aesop.server;

import java.rmi.Naming;

public class GetRemoteIp {
	
	/*
	 * 获取主服务器的ip地址 
	 */
	
	private GetHostIp host = new GetHostIp();
	private String hostip = host.hostIp();
	private Registered registry = null;
	public  String remoteip = null;

	public String getRemote(){
		
		try {
			IpXML.read();											//读取本地ip列表，保存至IpXML.list
		} catch (Exception e1) {
			System.out.println("读取本地服务器网内ip列表时失败");
		}
		
		boolean temp = false;
		
		for(int i=0;i<IpXML.list.size();i++){
			
			if((!IpXML.list.get(i).equals(hostip)) && (temp==false)){
				
				try{
					registry=(Registered)Naming.lookup("rmi://" + IpXML.list.get(i) + "/registryimpl");
					
					remoteip=registry.readRemote();						
														//如果找到任意一台已启动的服务器，则读取其动态ip列表中的主服务器地址
					
					if(remoteip==null)
						continue;
					
					System.out.println("本机非主服务器，找到主服务器"+remoteip+"启动成功");
					
					temp=true;
					
					break;
					
				}catch(Exception e){
					System.out.println("向"+IpXML.list.get(i)+"绑定失败");
				}
			}
		}
		
		
		if(temp==false){							//如果没找到启动着的服务器，则本机为主服务器
			
			try {
				
				registry = (Registered) Naming.lookup("rmi://" + hostip + "/registryimpl");
				
				System.out.println("本机为主服务器，启动成功");
				
				remoteip=hostip;
				
			} catch (Exception e) {
				
				System.out.println("本机为主服务器，但启动失败，请维修后重新启动");
				
			}
			
		}
		
		return remoteip;
	}
	
}
