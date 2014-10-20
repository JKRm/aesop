package cn.com.shanda.aesop.server;

import java.rmi.Naming;

public class Notify implements Runnable {
	
	/*
	 *  ��Դ���������߻���֪ͨ
	 */
	
	private String hostip;				//����ip��ַ
	private String othersip;			//����ӵ���Դ������ip��ַ
	
	public Notify(String hostip, String othersip) {
		this.hostip=hostip;
		this.othersip=othersip;
	}

	@Override
	public void run() {
		
		System.out.println("֪ͨ"+hostip+"���"+othersip+"�����ض�̬ip�б�");
		
		try {
			
			Registered registry=(Registered)Naming.lookup("rmi://" +hostip + "/registryimpl");
						
			if(registry.addip(othersip)){
				
				System.out.println("��"+hostip+"���"+othersip+"�ɹ���");
				
			}
			
		} catch (Exception e) {
			
			System.out.println("��"+hostip+"���"+othersip+"ʧ�ܣ�");
			
		}
	}

}
