package cn.com.shanda.aesop.server;

import java.io.IOException;

import org.dom4j.DocumentException;

public class ClearIpList {
	
	/*
	 *  ��ն�̬ip�б�
	 */
	
	public static void clear(){
		
		IpXML xml=new IpXML();
		
		try {
			xml.clearips();
		} catch (IOException e) {
			System.out.println("��ն�̬ip�б�ʱʧ��");
		} catch (DocumentException e) {
			System.out.println("��ն�̬ip�б�ʱʧ��");
		}
	}
}
