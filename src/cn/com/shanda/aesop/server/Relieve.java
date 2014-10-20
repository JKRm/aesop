package cn.com.shanda.aesop.server;


import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import cn.com.shanda.aesop.init.*;

public class Relieve {
	
	private Registered registry = null;
	private GetHostIp host = new GetHostIp();
	private String hostip = host.hostIp();
	
	public void relieve(){
		
		RemoveRemoteIp reomteremove = new RemoveRemoteIp();
		
		try {
			reomteremove.removeRemoteIp();
		} catch (Exception e2) {
			System.out.println("��ձ�����̬�б��е���������ip��ַʧ��");	
		}
		
		ClearIpList.clear();	
		
		if (!hostip.equals(RemoteIp.remoteip)) {
			try {
				registry = (Registered) Naming.lookup("rmi://"+ RemoteIp.remoteip + "/registryimpl");
				registry.signout(hostip);
				UnicastRemoteObject.unexportObject(Init.regist, true);
				System.out.println("rmi�������رճɹ�");
			} catch (Exception e) {
				System.out.println("�˳�ʱ��������������ʧ�ܣ���ȴ��������Զ���⣬���������˳��ɹ�");
			}
		} 
	}
}
