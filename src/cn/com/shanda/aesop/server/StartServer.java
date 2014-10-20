package cn.com.shanda.aesop.server;


public class StartServer {
	
	/*
	 *	开启分布式选举模块 
	 */

	public  StartServer() {
		
		GetHostIp host = new GetHostIp();								//获取本机ip
		String hostip = host.hostIp();
		GetRemoteIp remote = new GetRemoteIp();
		Registered registry = null;
		WtiteRemoteIp write = new WtiteRemoteIp();
		
		RemoveRemoteIp reomteremove = new RemoveRemoteIp();
		
		try {
			reomteremove.removeRemoteIp();
		} catch (Exception e2) {
			System.out.println("清空本机动态列表中的主服务器ip地址失败");		//清空由于上一次非正常退出而记录的主服务器ip的垃圾数据
		}
		
		ClearIpList.clear();												//清空由于上一次非正常退出而记录的ip列表的垃圾数据
		
		try {
			
			Bind server = new Bind();											//开启本机服务，并开启监控线程，等待注册
			server.bind(hostip);	
			
			RemoteIp.remoteip = remote.getRemote();							//绑定主服务器，向主服务器注册，接受监控
			Lookingup client = new Lookingup();
			registry=client.lookingup(RemoteIp.remoteip);
         
			if(registry.loginin(hostip)){									//向主服务器注册
				System.out.println(hostip+"加入"+RemoteIp.remoteip+"主机成功");
				write.write(RemoteIp.remoteip);
			}

		}catch (Exception e){ 
			
			try{
				System.out.println("主服务器连接失败，开始选举服务器……");
				
				RemoveRemoteIp reomteremove1 = new RemoveRemoteIp();
				reomteremove1.removeRemoteIp();

				RemoteIp.remoteip = remote.getRemote();
				
				Lookingup client = new Lookingup();
				registry=client.lookingup(RemoteIp.remoteip);

				if(registry.loginin(hostip)){
					
					System.out.println(hostip+"加入主机成功"+RemoteIp.remoteip);
					write.write(RemoteIp.remoteip);
					
				}
				
			}catch(Exception e1){
				
				System.out.println("选举注册发生错误");
				
			}
		}
	}
	
}
