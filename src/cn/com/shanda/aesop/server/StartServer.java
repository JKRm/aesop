package cn.com.shanda.aesop.server;


public class StartServer {
	
	/*
	 *	�����ֲ�ʽѡ��ģ�� 
	 */

	public  StartServer() {
		
		GetHostIp host = new GetHostIp();								//��ȡ����ip
		String hostip = host.hostIp();
		GetRemoteIp remote = new GetRemoteIp();
		Registered registry = null;
		WtiteRemoteIp write = new WtiteRemoteIp();
		
		RemoveRemoteIp reomteremove = new RemoveRemoteIp();
		
		try {
			reomteremove.removeRemoteIp();
		} catch (Exception e2) {
			System.out.println("��ձ�����̬�б��е���������ip��ַʧ��");		//���������һ�η������˳�����¼����������ip����������
		}
		
		ClearIpList.clear();												//���������һ�η������˳�����¼��ip�б����������
		
		try {
			
			Bind server = new Bind();											//�����������񣬲���������̣߳��ȴ�ע��
			server.bind(hostip);	
			
			RemoteIp.remoteip = remote.getRemote();							//����������������������ע�ᣬ���ܼ��
			Lookingup client = new Lookingup();
			registry=client.lookingup(RemoteIp.remoteip);
         
			if(registry.loginin(hostip)){									//����������ע��
				System.out.println(hostip+"����"+RemoteIp.remoteip+"�����ɹ�");
				write.write(RemoteIp.remoteip);
			}

		}catch (Exception e){ 
			
			try{
				System.out.println("������������ʧ�ܣ���ʼѡ�ٷ���������");
				
				RemoveRemoteIp reomteremove1 = new RemoveRemoteIp();
				reomteremove1.removeRemoteIp();

				RemoteIp.remoteip = remote.getRemote();
				
				Lookingup client = new Lookingup();
				registry=client.lookingup(RemoteIp.remoteip);

				if(registry.loginin(hostip)){
					
					System.out.println(hostip+"���������ɹ�"+RemoteIp.remoteip);
					write.write(RemoteIp.remoteip);
					
				}
				
			}catch(Exception e1){
				
				System.out.println("ѡ��ע�ᷢ������");
				
			}
		}
	}
	
}
