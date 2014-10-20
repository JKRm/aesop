package cn.com.shanda.aesop.server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;



public class WriteIpList {
	public void update(Vector<String> list)throws DocumentException, IOException{
		
		ClearIpList.clear();

		IpXML xml =  new IpXML();
		xml.writeRemote(RemoteIp.remoteip);
		
		
		SAXReader saxReader = new SAXReader();
		
		SAXReader saxReader2 = new SAXReader();
		
		//读取静态IP列表
		Document doc = saxReader.read(new File(InitParam.xmlpath2));
		
		//读取动态IP列表
		Document dynamicDoc = saxReader2.read(new File(InitParam.xmlpath1));
		
		Element root = doc.getRootElement();
		
		Element dRoot = dynamicDoc.getRootElement();
		
		@SuppressWarnings("rawtypes")
		List dIPList= dRoot.elements("IP"); 
		
		@SuppressWarnings("rawtypes")
		List IPList = root.elements("IP");
		
		for (int i = 0; i < list.size(); ++i) {
			
			String Ip = list.get(i);
			
			String id = getId(IPList, Ip);
				
			writeIP(dIPList, id, Ip);
			
		}
		
		XMLWriter xmlWriter = new XMLWriter(new FileOutputStream(InitParam.xmlpath1));
		
		xmlWriter.write(dynamicDoc);
		
		xmlWriter.close();
		
	}
	/**
	 * 得到当前I地址对应服务器的编号
	 * @param IPList
	 * @param Ip
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public String getId (List IPList , String Ip) {
		
		for(int j = 0; j < IPList.size(); ++j) {
			
			String url = ((Element)IPList.get(j)).elementText("url");
			
			if (Ip.equals(url)) {
				
				return (String) ((Element)IPList.get(j)).elementText("id");
		
			}
		}
		return null;
	}
	
	/**
	 * 将编号为id的服务器的IP地址写入动态IP列表
	 * @param IPList
	 * @param id
	 * @param Ip
	 */
	@SuppressWarnings("rawtypes")
	public void writeIP (List IPList , String id, String Ip) {
		
		for(int j = 0; j < IPList.size(); ++j) {
			
			String idValue = ((Element)IPList.get(j)).elementText("id");
			
			if (id.equals(idValue)) {
				
				((Element)IPList.get(j)).element("url").setText(Ip);
		
			}
		}
	}
}
