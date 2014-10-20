package cn.com.shanda.aesop.server;

import java.io.IOException;

import org.dom4j.DocumentException;

public class ClearIpList {
	
	/*
	 *  清空动态ip列表
	 */
	
	public static void clear(){
		
		IpXML xml=new IpXML();
		
		try {
			xml.clearips();
		} catch (IOException e) {
			System.out.println("清空动态ip列表时失败");
		} catch (DocumentException e) {
			System.out.println("清空动态ip列表时失败");
		}
	}
}
