package cn.com.shanda.aesop.server;

import java.util.Date;

import org.dom4j.DocumentException;


public class SearchServer {
	
	/*
	 *后期需要改动，该方法应该是会有返回值的 
	 */
	
	public void search(String queryText, String wholeKey, String excludingText, String field, 
			String author, String publisher, String[] types, String[] ids, Date[] date){
		
		//新添测试
//		FinalResult.results.clear();
		
		try {
			ReadIpList.read();
		} catch (DocumentException e2) {
			System.out.println("读取IpList时失败");
		}
		
//		System.out.println("jinlaile 列表数目：" + IpList.list.size());
		
		for(int i=0;i<IpList.list.size();i++){
			System.out.println("执行搜索"+IpList.list.get(i).toString());
			SearchRunnable search = new SearchRunnable(IpList.list.get(i).toString(),queryText,wholeKey,excludingText,field,author,publisher,types,ids,date);
			Thread thread = new Thread(search);
			thread.start();
		}
    }   
}
