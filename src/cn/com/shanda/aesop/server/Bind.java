package cn.com.shanda.aesop.server;


import java.rmi.Naming;


public class Bind {
	
	/*
	 *  �����������񣬲���������̣߳��ȴ�������������ע��
	 */
	
	public void bind(String ip) {
		
		RegisteredImpl registryimpl;
				
		try {
			
			registryimpl = new RegisteredImpl();
			
			Naming.rebind("rmi://" + ip + "/registryimpl", registryimpl);
			
			System.out.println("RMI����󶨳ɹ�");
						
			Thread thread = new Thread(registryimpl);
			
			thread.start();
			
			
		} catch (Exception e) {
			
			System.out.println("RMI�����ʧ��");
			
		} 
		
	}
}
