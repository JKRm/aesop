package cn.com.shanda.aesop.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Listener extends Remote{
	
	/*
	 *  ��������
	 */
	
	public void listener()throws RemoteException;
	
}
