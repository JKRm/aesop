package cn.com.shanda.aesop.server;

import java.rmi.Naming;

public class GetRemoteIp {
	
	/*
	 * ��ȡ����������ip��ַ 
	 */
	
	private GetHostIp host = new GetHostIp();
	private String hostip = host.hostIp();
	private Registered registry = null;
	public  String remoteip = null;

	public String getRemote(){
		
		try {
			IpXML.read();											//��ȡ����ip�б�������IpXML.list
		} catch (Exception e1) {
			System.out.println("��ȡ���ط���������ip�б�ʱʧ��");
		}
		
		boolean temp = false;
		
		for(int i=0;i<IpXML.list.size();i++){
			
			if((!IpXML.list.get(i).equals(hostip)) && (temp==false)){
				
				try{
					registry=(Registered)Naming.lookup("rmi://" + IpXML.list.get(i) + "/registryimpl");
					
					remoteip=registry.readRemote();						
														//����ҵ�����һ̨�������ķ����������ȡ�䶯̬ip�б��е�����������ַ
					
					if(remoteip==null)
						continue;
					
					System.out.println("�����������������ҵ���������"+remoteip+"�����ɹ�");
					
					temp=true;
					
					break;
					
				}catch(Exception e){
					System.out.println("��"+IpXML.list.get(i)+"��ʧ��");
				}
			}
		}
		
		
		if(temp==false){							//���û�ҵ������ŵķ��������򱾻�Ϊ��������
			
			try {
				
				registry = (Registered) Naming.lookup("rmi://" + hostip + "/registryimpl");
				
				System.out.println("����Ϊ���������������ɹ�");
				
				remoteip=hostip;
				
			} catch (Exception e) {
				
				System.out.println("����Ϊ����������������ʧ�ܣ���ά�޺���������");
				
			}
			
		}
		
		return remoteip;
	}
	
}
