package cn.com.shanda.aesop.server;

public class WtiteRemoteIp {
	
	/*
	 *  д�붯̬ip�б�
	 */
	
	public void write(String remoteip) throws Exception {
		
		IpXML xml = new IpXML();
		
		xml.writeRemote(remoteip);
		
	}
}
