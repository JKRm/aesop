package cn.com.shanda.aesop.server;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.ParseException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.com.shanda.aesop.dao.PreviewXMLParserDao;
import cn.com.shanda.aesop.dao.ResourceXMLParserDao;
import cn.com.shanda.aesop.dao.impl.PreviewXMLParserDaoImpl;
import cn.com.shanda.aesop.dao.impl.ResourceXMLParserDaoImpl;
import cn.com.shanda.aesop.pojo.PreviewItem;
import cn.com.shanda.aesop.pojo.QueryResult;
import cn.com.shanda.aesop.pojo.ResourceItem;
import cn.com.shanda.aesop.service.QueryService;
import cn.com.shanda.aesop.service.impl.QueryServiceImpl;


@SuppressWarnings("serial")
public class RegisteredImpl extends UnicastRemoteObject implements Registered,Runnable{
	
	private WriteIpList writelist = new WriteIpList();
	private Vector<Listener> list = new Vector<Listener>();			//�����������������б�
	private GetHostIp host = new GetHostIp();
	private String hostip = host.hostIp();
	
	
	
	
	public RegisteredImpl() throws RemoteException {
		
	}

	
	
	
	@Override
	public boolean loginin(String ip) throws RemoteException {
		
		if(!IpList.list.contains(ip)){
			
			IpList.list.add(ip);
			
			try {
				writelist.update(IpList.list);
			} catch (Exception e) {
				System.out.println("�������������Դ������ʱ����Ip�б�ʧ��");
			} 
			
		}
		
		System.out.println("��ӷ������󣬵�ǰ�ķ���������Ϊ��"+IpList.list.size());
		
		if(!ip.equals(hostip)){
			Feedback belistened=new Feedback();							//�������������������Դ������ע�ᱻ�������Է�������������
			belistened.feeback(ip);
		}
		
		for(int i=0;i<IpList.list.size();i++){							//��������������֪ͨ������Դ����������ע��
			
			if(!IpList.list.get(i).equals(hostip)){
				
				Notify others = new Notify(IpList.list.get(i),ip);
				Thread thread = new Thread(others);
				thread.start();
				
				if(!IpList.list.get(i).equals(ip)){
					
					Notify theothers = new Notify(ip,IpList.list.get(i));
					Thread thethread = new Thread(theothers);
					thethread.start();
					
				}
			}
			
		}
		
		return true;
	}

	
	
	
	
	@Override
	public boolean signout(String ip) throws RemoteException {
		
		for(int i=0;i<IpList.list.size();i++){
			
		    if(IpList.list.get(i).toString().equals(ip)){
		    
		    	IpList.list.remove(i);
		    	
		    	try {
		    		
					writelist.update(IpList.list);
				
		    	} catch (Exception e) {
				
		    		System.out.println("�˳���������ʱ����Ip�б�ʧ��");
				
		    	} 
		    	
		    	break;
		    
		    }
		}
		
		System.out.println("�Ƴ��������󣬵�ǰ�ķ���������Ϊ��"+IpList.list.size());
		
		for(int i=0;i<IpList.list.size();i++){
			
			Warn others=new Warn(IpList.list.get(i),ip);
			Thread thread=new Thread(others);
			thread.start();
			
		}
		
		return true;
		
	}

	
	
	
	@Override
	public Vector<String> getIPlist() throws RemoteException {	
		
		return IpList.list;
	}

	
	
	
	@Override
	public boolean addip(String ip) throws RemoteException {

		if(!(IpList.list.contains(ip))){
			
			System.out.println(" ��� ip - "+ip); 
			
			IpList.list.add(ip);
			
			try {
				
				writelist.update(IpList.list);
				
			} catch (DocumentException e) {
				System.out.println("���"+ip+"����̬�б�ʧ��");
			} catch (IOException e1) {
				System.out.println("���"+ip+"����̬�б�ʧ��");
			}
			
		}
		
		return true;
	}

	
	
	
	
	@Override
	public boolean removeip(String ip) throws RemoteException {
		
			for(int i=0;i<IpList.list.size();i++){
				
			    if(IpList.list.get(i).toString().equals(ip)){
			    	
			    	System.out.println("ɾ�� ip -"+ip);
			    	
			    	IpList.list.remove(i);
			    	
					try {
						
						writelist.update(IpList.list);
						
					} catch (DocumentException e) {
						System.out.println("�Ƴ���̬�б���"+ip+"ʧ��");
					} catch (IOException e) {
						System.out.println("�Ƴ���̬�б���"+ip+"ʧ��");
					}
					
			    	break;
			    }
			    
			}
			
		return true;
		
	}


	
	
	

	@Override
	public String readRemote() throws RemoteException {

		SAXReader saxReader = new SAXReader();
		
		//��ȡ��̬IP�б�
		Document dynamicDoc = null;
		
		try {
			
			dynamicDoc = saxReader.read(new File(InitParam.xmlpath1));
			
		} catch (DocumentException e) {
			
			System.out.println("��ȡ��������ip��ַʧ��");
			
		}
		
		Element dRoot = dynamicDoc.getRootElement();
		
		Element remoteElement= dRoot.element("remote"); 
		
		if (remoteElement.element("url").getText() != null && !remoteElement.element("url").getText().equals("")){
			
			return remoteElement.element("url").getText();
			
		}
		
		return null;
	}

	
	
	
	@Override
	public QueryResult search(String queryText, String wholeKey, String excludingText, String field, 
			String author, String publisher, String[] types, String[] ids, Date[] date) throws RemoteException {
		
		QueryService queryservice = new QueryServiceImpl();
		
		QueryResult result = queryservice.search(queryText, wholeKey, excludingText, field, author, publisher, types, date);
		
		return result;
		
	}



	

	@Override
	public void run() {
		
		Random r =new Random();
		
		for(;;){								
				try {
					
					int duration=r.nextInt()%10000+20000;
					
					if(duration<0)
						duration=duration*-1;
					
					Thread.sleep(duration);
					
				} catch (InterruptedException e) {
					
				}
			
				notifyListeners();
		
		}
		
	}

	
	
	
	
	private void notifyListeners() {
		
		for(Enumeration<Listener> e=list.elements();e.hasMoreElements();){
			
			Listener listener=(Listener)e.nextElement();
			
			try {
				
				listener.listener();
				
			} catch (RemoteException e1) {
				
				System.out.println("�з������˳�����ʼ���¶�̬Ip�б�");
				list.remove(listener);
				Remove remove = new Remove();
				remove.remvoeip();
			}
		}
		
	}

	
	
	
	
	@Override
	public void addListener(Listener listener) throws RemoteException {
		
		System.out.println("��� ������ -"+listener);
		
		list.add( listener );
		
	}

	
	
	
	
	@Override
	public void removeListener(Listener listener) throws RemoteException {
		
		System.out.println("�Ƴ� ������ -"+listener);
		
		list.remove( listener );
		
	}




	@Override
	public Map<String, List<PreviewItem>> getPreviewItems() throws RemoteException {
		
		PreviewXMLParserDao previewservice = new PreviewXMLParserDaoImpl();
		
		Map<String, List<PreviewItem>>map = previewservice.getPreviewItems();
		
		return map;
	}




	@Override
	public ResourceItem getResource(String url) throws RemoteException {
		
		ResourceXMLParserDao rxpd = new ResourceXMLParserDaoImpl();
		
		ResourceItem item = null;
		try {
			item = rxpd.getResource(url);
		} catch (DocumentException e) {

		} catch (ParseException e) {

		}
		
		return item;
	}
	
	
}
