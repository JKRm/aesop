package cn.com.shanda.aesop.server;


import java.rmi.Naming;

public class Warn implements Runnable {
	
	/*
	 *  ��������������֪ͨ������Դ���������ڶ�̬ip�б��У��Ƴ��˳��ķ�������ip��ַ
	 */
	
	private String hostip;
	private String othersip;
	
	public Warn(String hostip, String othersip) {
		this.hostip=hostip;
		this.othersip=othersip;
	}

	@Override
	public void run() {
		
		System.out.println("֪ͨ"+hostip+"ɾ��"+othersip);
		
		try {
			
			Registered registry=(Registered)Naming.lookup("rmi://" +hostip + "/registryimpl");
			
			if(registry.removeip(othersip))
				
				System.out.println("��"+hostip+"ɾ��"+othersip+"�ɹ���");
			
		} catch (Exception e) {
			
			System.out.println("��"+hostip+"ɾ��"+othersip+"ʧ�ܣ�");
			
		}
	}

}
