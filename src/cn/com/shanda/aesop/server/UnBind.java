package cn.com.shanda.aesop.server;

import java.rmi.Naming;

public class UnBind {

	@SuppressWarnings("unused")
	public void unBind(String ip){
		
		try {
			
			RegisteredImpl registryimpl = new RegisteredImpl();
			
			Naming.unbind("rmi://" + ip + "/registryimpl");
			
			System.out.println("�ɹ��Ƴ�������"+ip);
			
			
		} catch (Exception e) {
			
			System.out.println("�Ƴ�������"+ip+"ʧ��");
			
		} 
		
	}
}
