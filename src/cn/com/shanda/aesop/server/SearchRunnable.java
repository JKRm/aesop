package cn.com.shanda.aesop.server;

import java.rmi.Naming;
import java.util.Date;

import cn.com.shanda.aesop.pojo.QueryResult;


public class SearchRunnable implements Runnable{
	
	private String remoteip;
	private String queryText;
	private String wholeKey;
	private String excludingText;
	private String field;
	private String author;
	private String publisher;
	private String[] types;
	private String[] ids;
	private Date[] date;
	private QueryResult result = new QueryResult();
	
	public SearchRunnable(String path,String queryText, String wholeKey, String excludingText, String field, 
			String author, String publisher, String[] types, String[] ids, Date[] date){
		this.remoteip = path;
		this.queryText = queryText;
		this.wholeKey = wholeKey;
		this.excludingText = excludingText;
		this.field = field;
		this.author = author;
		this.publisher = publisher;
		this.types = types;
		this.ids = ids;
		this.date = date;
	}
	
	@Override
	public void run() {
		try {
			
			Registered registry=(Registered) Naming.lookup("rmi://" + remoteip + "/registryimpl");	
			
			result=registry.search(queryText,wholeKey,excludingText,field,author,publisher,types,ids,date);
			
            FinalResult.results.merge(result);
             
            FinalResult.serverSize++;
            
            System.out.println("服务器"+remoteip+"得到结果数：" + result.getResultCount());
		 }catch (Exception e){
			System.out.println("检索"+remoteip+"失败,删除相应的地址");
			
			for(int i=0;i<IpList.list.size();i++){
				if(!(IpList.list.get(i).toString().equals(remoteip))){
					Warn remove=new Warn(IpList.list.get(i).toString(),remoteip);
					Thread thread=new Thread(remove);
					thread.start();
				}
			}
		 }
	}

}
