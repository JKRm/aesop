package cn.com.shanda.aesop.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Listener extends Remote{
	
	/*
	 *  ¼àÌý·½·¨
	 */
	
	public void listener()throws RemoteException;
	
}
