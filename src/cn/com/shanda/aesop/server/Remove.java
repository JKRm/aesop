package cn.com.shanda.aesop.server;



import java.rmi.Naming;

import org.dom4j.DocumentException;

public class Remove {
	
	/*
	 *  ��������������ʱ���ڱ��ض�̬ip�б����Ƴ��˳��ķ�������
	 *  
	 *  ������������ʱ����ʼѡ����������
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
			System.out.println("��ȡIpListʱʧ��");
		}
		
		for(int i=0;i<IpList.list.size();i++)			
			if(IpList.list.get(i).equals(RemoteIp.remoteip))
				temp = false;

		
		if(IpList.list.size()==0||temp==true){
			
			try {
				
				System.out.println("������������ʧ�ܣ���ʼѡ��������������");
				
				RemoveRemoteIp reomteremove = new RemoveRemoteIp();
				reomteremove.removeRemoteIp();
				ClearIpList.clear();
				IpList.list.clear();
				
				RemoteIp.remoteip = remote.getRemote();
			
				Lookingup client = new Lookingup();
				registry=client.lookingup(RemoteIp.remoteip);

				if(registry.loginin(hostip)){
					
					System.out.println(hostip+"���������ɹ�"+RemoteIp.remoteip);
					write.write(RemoteIp.remoteip);
					
				}
				
			}catch (Exception e1) {
				
				System.out.println("ѡ��ע�ᷢ��������ά�޺���������������");
				
			}
		}
		
		for(int i=0;i<IpList.list.size();i++){
			
			if(!IpList.list.get(i).equals(hostip)){
				
				ip=IpList.list.get(i);	
				
				try{
					
					registry = (Registered)Naming.lookup("rmi://" + IpList.list.get(i) + "/registryimpl");
					                                                           //��������������ӣ��������һ̨��Դ������
					continue;													
					
				}catch(Exception e){
					
					try {
						
						registry=(Registered)Naming.lookup("rmi://" + RemoteIp.remoteip + "/registryimpl");
						
														//�������������ߣ�֪ͨ��������������Ӧ����
						
						registry.signout(IpList.list.get(i));
						
					} catch (Exception e3) {
						
					} 
					
					if(ip.equals(RemoteIp.remoteip)){
						
						try {
						
							System.out.println("������������ʧ�ܣ���ʼѡ��������������");
							
							RemoveRemoteIp reomteremove = new RemoveRemoteIp();
							reomteremove.removeRemoteIp();
							ClearIpList.clear();
							IpList.list.clear();
							
							RemoteIp.remoteip = remote.getRemote();
						
							Lookingup client = new Lookingup();
							registry=client.lookingup(RemoteIp.remoteip);
	
							if(registry.loginin(hostip)){
								
								System.out.println(hostip+"���������ɹ�"+RemoteIp.remoteip);
								write.write(RemoteIp.remoteip);
								
							}
							
						}catch (Exception e1) {
							
							System.out.println("ѡ��ע�ᷢ��������ά�޺���������������");
							
						}
					}
					
				}
			}
		}
	}

}
