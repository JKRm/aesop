package cn.com.shanda.aesop.server;

public class WtiteRemoteIp {
	
	/*
	 *  写入动态ip列表
	 */
	
	public void write(String remoteip) throws Exception {
		
		IpXML xml = new IpXML();
		
		xml.writeRemote(remoteip);
		
	}
}
