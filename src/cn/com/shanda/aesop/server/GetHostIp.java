package cn.com.shanda.aesop.server;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class GetHostIp {
	
	/*
	 *  获取本机ip地址
	 */
	
	public String hostIp(){
	
		InetAddress s = null;
		
		try {
		
			s = InetAddress.getLocalHost();
		
		} catch (UnknownHostException e) {
		
			System.out.println("本机Ip获取失败");
		
		}
		
		return s.getHostAddress();
	
	}
}
