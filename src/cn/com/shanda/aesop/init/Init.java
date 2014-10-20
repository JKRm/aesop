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
		
		// 初始化设定每页显示记录数以及页码数
		ShowResultAction.setRecordsPerPage(5);
		ShowResultAction.setNumbersPerPage(5);
	}
	
	public void destroy(){
		super.destroy(); 
		try {
			UnicastRemoteObject.unexportObject(regist,true);
			System.out.println("RMI服务器已关闭");
		} catch (NoSuchObjectException e) {
			System.out.println("关闭rmi服务器端口失败");
		}
	}
	
	public void init() throws ServletException {
		
		try {
			regist=LocateRegistry.createRegistry(1099);
			System.out.println("rmi服务器启动成功");
		} catch (RemoteException e) {
			System.out.println("rmi服务器启动失败");
		}
		
		new Start();
	}
}
