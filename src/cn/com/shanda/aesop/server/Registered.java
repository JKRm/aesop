package cn.com.shanda.aesop.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import cn.com.shanda.aesop.pojo.PreviewItem;
import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.pojo.ResourceItem;

@SuppressWarnings("rawtypes")
public interface Registered extends Remote{
	
	/*
	 *  向主服务器注册本机
	 */
	
	public boolean loginin(String ip)throws RemoteException ;  
	
	 /*
	  *  向主服务器，申请退出
	  */
	
	 public boolean signout(String ip)throws RemoteException ; 
	 
	 /*
	  *  获得当前在线的ip列表
	  */
	 
	 public Vector getIPlist()throws RemoteException ;
	 
	 /*
	  *  向本地动态ip列表中添加新上线的ip地址
	  */
	 
	 public boolean addip(String ip) throws RemoteException ;  
	 
	 /*
	  *  向本地动态ip列表中移除刚退出的ip地址
	  */
	 
	 public boolean removeip(String ip) throws RemoteException ;
	 
	 /*
	  *  读取动态ip列表中的主服务器ip地址
	  */
	 
	 public String readRemote()throws RemoteException;
	 
	 /*
	  *  搜索方法
	  */
	 
	 public QueryResult search(String queryText, String wholeKey, String excludingText, String field, 
				String author, String publisher, String[] types, String[] ids, Date[] date)throws RemoteException;
	 
	 /*
	  *  添加被监听服务器对象，以防服务器意外短线，无法获得消息
	  */
	 
	 public void addListener(Listener listener)throws RemoteException;
	 
	 /*
	  *  移除被监听的服务器对象
	  */
	 
	 public void removeListener(Listener listener)throws RemoteException;
	 
	 /*
	  *  文库预览的对应方法
	  */
	 
	 public Map<String, List<PreviewItem>> getPreviewItems() throws RemoteException;
	 
	 /*
	  *  查询资源的URL
	  */
	 
	 public ResourceItem getResource(String url) throws RemoteException;
	 
}

