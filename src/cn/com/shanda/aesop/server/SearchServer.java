package cn.com.shanda.aesop.server;

import java.util.Date;

import org.dom4j.DocumentException;


public class SearchServer {
	
	/*
	 *������Ҫ�Ķ����÷���Ӧ���ǻ��з���ֵ�� 
	 */
	
	public void search(String queryText, String wholeKey, String excludingText, String field, 
			String author, String publisher, String[] types, String[] ids, Date[] date){
		
		//�������
//		FinalResult.results.clear();
		
		try {
			ReadIpList.read();
		} catch (DocumentException e2) {
			System.out.println("��ȡIpListʱʧ��");
		}
		
//		System.out.println("jinlaile �б���Ŀ��" + IpList.list.size());
		
		for(int i=0;i<IpList.list.size();i++){
			System.out.println("ִ������"+IpList.list.get(i).toString());
			SearchRunnable search = new SearchRunnable(IpList.list.get(i).toString(),queryText,wholeKey,excludingText,field,author,publisher,types,ids,date);
			Thread thread = new Thread(search);
			thread.start();
		}
    }   
}
