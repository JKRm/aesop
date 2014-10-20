package cn.com.shanda.aesop.server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class ListenerImpl extends UnicastRemoteObject implements Listener{

	public ListenerImpl() throws RemoteException {
	
	}

	@Override
	public void listener() throws RemoteException {
		
//		System.out.println(s);
		
	}
	
}
