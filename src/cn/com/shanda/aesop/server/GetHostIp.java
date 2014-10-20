package cn.com.shanda.aesop.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostIp {
	
	/*
	 *  ��ȡ����ip��ַ
	 */
	
	public String hostIp(){
	
		InetAddress s = null;
		
		try {
		
			s = InetAddress.getLocalHost();
		
		} catch (UnknownHostException e) {
		
			System.out.println("����Ip��ȡʧ��");
		
		}
		
		return s.getHostAddress();
	
	}
}
