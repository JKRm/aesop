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
	 *  ����������ע�᱾��
	 */
	
	public boolean loginin(String ip)throws RemoteException ;  
	
	 /*
	  *  �����������������˳�
	  */
	
	 public boolean signout(String ip)throws RemoteException ; 
	 
	 /*
	  *  ��õ�ǰ���ߵ�ip�б�
	  */
	 
	 public Vector getIPlist()throws RemoteException ;
	 
	 /*
	  *  �򱾵ض�̬ip�б�����������ߵ�ip��ַ
	  */
	 
	 public boolean addip(String ip) throws RemoteException ;  
	 
	 /*
	  *  �򱾵ض�̬ip�б����Ƴ����˳���ip��ַ
	  */
	 
	 public boolean removeip(String ip) throws RemoteException ;
	 
	 /*
	  *  ��ȡ��̬ip�б��е���������ip��ַ
	  */
	 
	 public String readRemote()throws RemoteException;
	 
	 /*
	  *  ��������
	  */
	 
	 public QueryResult search(String queryText, String wholeKey, String excludingText, String field, 
				String author, String publisher, String[] types, String[] ids, Date[] date)throws RemoteException;
	 
	 /*
	  *  ��ӱ����������������Է�������������ߣ��޷������Ϣ
	  */
	 
	 public void addListener(Listener listener)throws RemoteException;
	 
	 /*
	  *  �Ƴ��������ķ���������
	  */
	 
	 public void removeListener(Listener listener)throws RemoteException;
	 
	 /*
	  *  �Ŀ�Ԥ���Ķ�Ӧ����
	  */
	 
	 public Map<String, List<PreviewItem>> getPreviewItems() throws RemoteException;
	 
	 /*
	  *  ��ѯ��Դ��URL
	  */
	 
	 public ResourceItem getResource(String url) throws RemoteException;
	 
}

