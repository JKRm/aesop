package cn.com.shanda.aesop.server;

import java.rmi.Naming;


public class Feedback {
	
	/*
	 *  �������������������������뱻������Դ�������������Է�������������
	 */
	
	private Registered registry;
	
	public void feeback(String ip){
		
		try {
			
			registry=(Registered) Naming.lookup("rmi://" + ip + "/registryimpl");
			
			ListenerImpl listener = new ListenerImpl();
			
			registry.addListener(listener);
			
		} catch (Exception e) {
			
			System.out.println("��������������ʱʧ��");
			
		} 
	}
}
