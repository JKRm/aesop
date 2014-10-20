package cn.com.shanda.aesop.init;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import cn.com.shanda.aesop.action.ShowResultAction;
import cn.com.shanda.aesop.server.GetHostIp;
import cn.com.shanda.aesop.server.GetRemoteIp;
import cn.com.shanda.aesop.server.Lookingup;
import cn.com.shanda.aesop.server.Registered;
import cn.com.shanda.aesop.server.RemoteIp;
import cn.com.shanda.aesop.server.Start;

@SuppressWarnings("serial")
public class Init extends HttpServlet {
	
	public static Registry regist;
	
	public Init() {
		super();
		
		// ��ʼ���趨ÿҳ��ʾ��¼���Լ�ҳ����
		ShowResultAction.setRecordsPerPage(5);
		ShowResultAction.setNumbersPerPage(5);
	}
	
	public void destroy(){
		super.destroy(); 
		try {
			UnicastRemoteObject.unexportObject(regist,true);
			System.out.println("RMI�������ѹر�");
		} catch (NoSuchObjectException e) {
			System.out.println("�ر�rmi�������˿�ʧ��");
		}
	}
	
	public void init() throws ServletException {
		
		try {
			regist=LocateRegistry.createRegistry(1099);
			System.out.println("rmi�����������ɹ�");
		} catch (RemoteException e) {
			System.out.println("rmi����������ʧ��");
		}
		
		new Start();
	}
}
